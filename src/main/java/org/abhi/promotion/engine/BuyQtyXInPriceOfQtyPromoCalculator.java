package org.abhi.promotion.engine;

import java.util.List;

import org.abhi.promotion.beans.ItemBean;
import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.OrderItemBean;
import org.abhi.promotion.beans.PromotionsDataBean;
import org.abhi.promotion.constants.OrderPromotionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuyQtyXInPriceOfQtyPromoCalculator {

	private Logger logger = LoggerFactory
			.getLogger(BuyQtyXInPriceOfQtyPromoCalculator.class);

	/**
	 * This promotion will be applied as per user cart items hence applying the
	 * business rules and then cart calculation based on that.
	 * 
	 * @param orderDataBean
	 * @param promoBean
	 */
	public void applyBuyXInPriceOfYPromo(final OrderDataBean orderDataBean,
			final PromotionsDataBean promoBean) {
		logger.info("Method Start >>" + "applyBuyXInPriceOfYPromo()");

		// check applicability of this promotion
		final List<OrderItemBean> orderItems = orderDataBean.getOrderItems();
		final int promotionPriority = promoBean.getBuyXInYPromo()
				.getPromotionPriority();

		for (final OrderItemBean orderItem : orderItems) {
			final String promotionSKUId = promoBean.getBuyXInYPromo()
					.getSkuId();
			final String orderItemSKUId = orderItem.getSkuId();
			final int orderItemQuantity = orderItem.getItemQunatity();

			// get the offer quantity of SKU which is applicable for this
			// promotion
			final int offerQty = promoBean.getBuyXInYPromo().getOfferQuantity();
			final int appliedQty = promoBean.getBuyXInYPromo()
					.getCurrentQuantity();

			if (promotionSKUId.equals(orderItemSKUId)) {
				if (promotionPriority == OrderPromotionConstants.PRIORITY_ONE) {

					applyBuyXInPriceOfYPromoPriorityOne(orderDataBean,
							orderItem, orderItemQuantity, offerQty, appliedQty);

				} else if (promotionPriority == OrderPromotionConstants.PRIORITY_TWO) {
					applyBuyXInYPromoPriorityTwoOrHigh(orderDataBean,
							promotionPriority, orderItem, offerQty, appliedQty);

				}
			}
		}

		logger.info("Method End >>" + "applyBuyXInPriceOfYPromo()");
	}

	private void applyBuyXInYPromoPriorityTwoOrHigh(
			final OrderDataBean orderDataBean, final int promotionPriority,
			final OrderItemBean orderItem, final int offerQty,
			final int appliedQty) {
		double orderItemTotal;
		// BuyXinY promotion can come on priority order 2 only if
		// percentageOff promo or comboCategoryPromo
		// come before this. If comboCategoryPromo is at priority 1
		// before BuyXinY
		// get the number of times BuyXInY promotion can be applied
		// now.

		final int promoOrderItems = orderItem.getRemainingItems();

		// this multiplier will tell how many sets of SKUs will be
		// applicable for this promotion.
		if (offerQty <= promoOrderItems) {
			final int multiplier = promoOrderItems / offerQty;

			logger.info("applyBuyXInYPromoPriorityTwoOrHigh is at priority "
					+ promotionPriority + " and promoOrderItems count is"
					+ promoOrderItems + " and multiplier is" + multiplier);

			final ItemBean items = orderItem.getItems();

			orderItemTotal = multiplier
					* (appliedQty * items.getItemCurrentPrice());
			orderItem.setOfferItemsTotal(orderItemTotal);
			orderItem.setItemOfferQuantity(offerQty * multiplier);
			orderItem.setRemainingItems(orderItem.getRemainingItems()
					- (offerQty * multiplier));
			orderDataBean.setOrderFinalTotal(orderDataBean.getOrderFinalTotal()
					+ orderItemTotal);

		}
	}

	/**
	 * Method containing logic for BuyXInY Promotion if its priority is 1.
	 * 
	 * @param orderDataBean
	 * @param orderItem
	 * @param orderItemQuantity
	 * @param offerQty
	 * @param appliedQty
	 */
	private void applyBuyXInPriceOfYPromoPriorityOne(
			final OrderDataBean orderDataBean, final OrderItemBean orderItem,
			final int orderItemQuantity, final int offerQty,
			final int appliedQty) {
		double orderItemTotal;
		// get the number of times BuyXInY promotion will be
		// applied.
		final int multiplier = orderItemQuantity / offerQty;

		if (offerQty <= orderItemQuantity) {
			orderItemTotal = multiplier
					* (appliedQty * orderItem.getItems().getItemCurrentPrice());
			orderItem.setOfferItemsTotal(orderItemTotal);
			orderItem.setItemOfferQuantity(offerQty * multiplier);
			orderItem.setRemainingItems(orderItem.getRemainingItems()
					- (offerQty * multiplier));
			orderDataBean.setOrderFinalTotal(orderDataBean.getOrderFinalTotal()
					+ orderItemTotal);

			logger.info("1 ...Multiplier is " + multiplier
					+ " order item offer quantity" + offerQty * multiplier
					+ "order final total after this promo is"
					+ orderDataBean.getOrderFinalTotal()
					+ "remaining order item" + orderItem.getRemainingItems());
		}
	}
}
