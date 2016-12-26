package org.abhi.promotion.engine;

import java.util.List;

import org.abhi.promotion.beans.ItemBean;
import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.OrderItemBean;
import org.abhi.promotion.beans.PercentOffOnCategotryPromoBean;
import org.abhi.promotion.beans.PromotionsDataBean;
import org.abhi.promotion.constants.OrderPromotionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PercentOffPromoCalculator {
	private Logger logger = LoggerFactory
			.getLogger(PercentOffPromoCalculator.class);

	/**
	 * Mehtod to check the applicability of percentOff promotion and logic as
	 * per priority of this promotion.
	 * 
	 * @param orderDataBean
	 * @param promoBean
	 */
	public void applyPercentageOffOnCategoryPromo(
			final OrderDataBean orderDataBean,
			final PromotionsDataBean promoBean) {

		logger.info("Method Start >>" + "applyPercentageOffOnCategoryPromo()");

		final List<OrderItemBean> orderItems = orderDataBean.getOrderItems();
		final PercentOffOnCategotryPromoBean percentPromo = promoBean
				.getPercentOffPromo();

		final String promoSKUId = percentPromo.getSkuId();
		final double percentOff = percentPromo.getPercentOff();
		final int promotionPriority = percentPromo.getPromotionPriority();

		// check applicability of the promotion
		for (final OrderItemBean orderItem : orderItems) {
			final int orderItemQuantity = orderItem.getItemQunatity();
			final String orderItemSKUId = orderItem.getSkuId();
			final ItemBean items = orderItem.getItems();
			if (promoSKUId.equals(orderItemSKUId)) {
				if (promotionPriority == OrderPromotionConstants.PRIORITY_ONE) {

					applyPercentOffOnPriorityOne(orderDataBean, percentOff,
							orderItem, orderItemQuantity, items);

				} else {

					applyPercentOffOnPriorityTwoOrHigh(orderDataBean,
							percentOff, orderItem);

				}
			}

		}
		logger.info("Method End >>" + "applyPercentageOffOnCategoryPromo()");
	}

	/**
	 * Method will take care of PRIORITY_TWO and _PRIORITY_THREE for
	 * percentageOff promotion
	 * 
	 * @param orderDataBean
	 * @param percentOff
	 * @param orderItem
	 */
	private void applyPercentOffOnPriorityTwoOrHigh(
			final OrderDataBean orderDataBean, final double percentOff,
			final OrderItemBean orderItem) {
		double orderItemTotal;

		final int promoOrderItems = orderItem.getRemainingItems();

		if (promoOrderItems > 0) {
			orderItemTotal = promoOrderItems
					* (orderItem.getItems().getItemCurrentPrice() * (percentOff / 100));
			orderItem.setOfferItemsTotal(orderItemTotal);

			orderDataBean.setOrderFinalTotal(orderDataBean.getOrderFinalTotal()
					+ orderItemTotal);
			orderItem.setRemainingItems(orderItem.getRemainingItems()
					- promoOrderItems);
		}
		logger.info("applyPercentOffOnPriorityTwoOrHigh promotion eligible items "
				+ promoOrderItems
				+ "remaining count "
				+ orderItem.getRemainingItems()
				+ " offer items total"
				+ orderItem.getOfferItemsTotal()
				+ " Order final total"
				+ orderDataBean.getOrderFinalTotal());
	}

	/**
	 * Method has logic to apply percentOff promotion at priority 1.
	 * 
	 * @param orderDataBean
	 * @param percentOff
	 * @param orderItem
	 * @param orderItemQuantity
	 * @param items
	 */
	private void applyPercentOffOnPriorityOne(
			final OrderDataBean orderDataBean, final double percentOff,
			final OrderItemBean orderItem, final int orderItemQuantity,
			final ItemBean items) {
		double orderItemTotal;
		orderItemTotal = orderItem.getItemQunatity()
				* (items.getItemCurrentPrice() * (percentOff / 100));
		orderItem.setOfferItemsTotal(orderItemTotal);
		orderItem.setRemainingItems(orderItem.getRemainingItems()
				- orderItemQuantity);
		orderDataBean.setOrderFinalTotal(orderItemTotal);

		logger.info("applyPercentOffOnPriorityOne Final Order total"
				+ orderDataBean.getOrderFinalTotal() + "remaining items are "
				+ orderItem.getRemainingItems());
	}

}
