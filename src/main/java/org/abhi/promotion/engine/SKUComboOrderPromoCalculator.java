package org.abhi.promotion.engine;

import java.util.ArrayList;
import java.util.List;

import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.OrderItemBean;
import org.abhi.promotion.beans.PromotionsDataBean;
import org.abhi.promotion.constants.OrderPromotionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SKUComboOrderPromoCalculator {

	private Logger logger = LoggerFactory
			.getLogger(SKUComboOrderPromoCalculator.class);

	/**
	 * Method to apply categoryCombo promotion applicability and logic based on
	 * promotion priority.
	 * 
	 * @param orderDataBean
	 * @param promoBean
	 */
	public void applyCategoryComboPromotion(final OrderDataBean orderDataBean,
			final PromotionsDataBean promoBean) {
		logger.info("Method Start >>" + "applyCategoryComboPromotion()");

		final List<OrderItemBean> orderItems = orderDataBean.getOrderItems();
		final List<OrderItemBean> comboPromoOrder = promoBean
				.getSkuComboPromo().getOrderitems();

		final int promotionPriority = promoBean.getSkuComboPromo()
				.getPromotionPriority();

		// To avoid 1 for loop read the SKUs and quantities for
		// SKUCombopromotion is an array.
		final List<String> comboPromoSKUs = new ArrayList<String>();
		final List<Integer> comboPromoSKUsQty = new ArrayList<Integer>();

		for (final OrderItemBean skuOrderItem : comboPromoOrder) {
			comboPromoSKUs.add(skuOrderItem.getItems().getSkuId());
			comboPromoSKUsQty.add(skuOrderItem.getItemQunatity());
		}

		logger.info("Combo Promotion SKUs" + comboPromoSKUs
				+ " SKUs quantities" + comboPromoSKUsQty);

		final List<String> orderSKUs = new ArrayList<String>();
		final List<Integer> orderSKUQty = new ArrayList<Integer>();
		final List<Double> orderItemPrices = new ArrayList<Double>();

		// read orderitems from orderdatabean and get the ordered SKUs to check
		// promotion eligibility
		for (final OrderItemBean orderItem : orderItems) {
			// TODO check this variable again why this is needed.
			// itemOfferQuantity = orderItem.getItemOfferQuantity();
			// TODO check this variable again why this is needed.
			orderSKUs.add(orderItem.getSkuId());
			orderSKUQty.add(orderItem.getItemQunatity());

			orderItemPrices.add(orderItem.getItems().getItemCurrentPrice());
		}

		logger.info("order SKUs" + orderSKUs + " quantities of SKUs"
				+ orderSKUQty + " order item prices" + orderItemPrices);

		// comboCount varriable will inform how many pairs of qualifying SKUs
		// from this promotion will appear in user's order.
		final double comboPromoPrice = promoBean.getSkuComboPromo()
				.getOfferOrderAmount();
		// double finalOrderTotal = 0.0;
		if (comboPromoSKUs.containsAll(orderSKUs)) {
			if (promotionPriority == OrderPromotionConstants.PRIORITY_ONE) {

				applyComboPromoOnPriorityOne(orderDataBean, orderItems,
						orderSKUQty, orderItemPrices, comboPromoPrice);

			} else if (promotionPriority == OrderPromotionConstants.PRIORITY_TWO) {

				applyComboPromoOnPriorityTwo(orderDataBean, orderItems,
						orderSKUQty, orderItemPrices, comboPromoPrice);

			} else {

				applyComboPromoOnPriorityThree(orderDataBean, orderItems);

			}
		}
		logger.info("Method End >>" + "applyCategoryComboPromotion()");
	}

	/**
	 * @param orderDataBean
	 * @param orderItems
	 */
	private void applyComboPromoOnPriorityThree(
			final OrderDataBean orderDataBean,
			final List<OrderItemBean> orderItems) {
		double finalOrderTotal;
		if (orderItems.get(0).getRemainingItems() == 0) {
			double orderItemsTotal = orderItems.get(1).getItemQunatity()
					* orderItems.get(1).getItems().getItemCurrentPrice();
			finalOrderTotal = orderItemsTotal
					+ orderDataBean.getOrderFinalTotal();
			orderDataBean.setOrderFinalTotal(finalOrderTotal);
			logger.info("applyComboPromoOnPriorityThree y SKUs count if order has x + y items of x and y SKUs"
					+ orderItems.get(1).getItemQunatity()
					+ " y SKUs current price is "
					+ orderItems.get(1).getItems().getItemCurrentPrice());
		}
	}

	/**
	 * @param orderDataBean
	 * @param orderItems
	 * @param orderSKUQty
	 * @param orderItemPrices
	 * @param comboPromoPrice
	 * @param finalOrderTotal
	 */
	private void applyComboPromoOnPriorityTwo(
			final OrderDataBean orderDataBean,
			final List<OrderItemBean> orderItems,
			final List<Integer> orderSKUQty,
			final List<Double> orderItemPrices, final double comboPromoPrice) {
		logger.info("Method start >>> applyComboPromoOnPriorityTwo()");
		int comboCount;
		// This scenario can appear only if priority 1 applied promotion
		// is either BuyXInYPromo or percentageOff
		// promotion. Check the qualifying condition for this
		// promotion's applicability.
		final int promoOrderItems = orderItems.get(0).getRemainingItems();
		double finalOrderTotal = orderDataBean.getOrderFinalTotal();

		comboCount = calulateCombosCount(orderItems, orderSKUQty,
				promoOrderItems);
		if (0 == promoOrderItems) {
			comboCount = 0;
		}

		if ((comboCount == 0 && null != orderItems.get(1))
				|| 0 == orderItems.get(0).getRemainingItems()) {
			logger.info("inside no combocount " + comboCount + "SKU price"
					+ orderItemPrices.get(1) + " SKU Qty" + orderSKUQty.get(1)
					+ "final total " + finalOrderTotal);

			// orderItems.get(1).setOfferItemsTotal(
			// orderItemPrices.get(1) * orderSKUQty.get(1));
			// orderItems.get(1).setItemOfferQuantity(orderSKUQty.get(1));
			// finalOrderTotal = finalOrderTotal
			// + orderItems.get(1).getOfferItemsTotal();
			finalOrderTotal = finalOrderTotal
					+ (orderItems.get(1).getRemainingItems() * orderItems
							.get(1).getItems().getItemCurrentPrice());

			orderDataBean.setOrderFinalTotal(finalOrderTotal);
		} else if (comboCount != 0) {
			proportionateDiscountInOrderItems(orderItems, orderItemPrices,
					comboPromoPrice, comboCount);
			for (OrderItemBean orderItem : orderItems) {
				finalOrderTotal = finalOrderTotal
						+ orderItem.getOfferItemsTotal();

			}
			// logic for n X and m Y order scenario where n < m and combo
			// promotion is at priority one
			if (0 == orderItems.get(0).getRemainingItems()) {
				finalOrderTotal = finalOrderTotal
						+ (orderItems.get(1).getRemainingItems() * orderItems
								.get(1).getItems().getItemCurrentPrice());

			}
			orderDataBean.setOrderFinalTotal(orderDataBean.getOrderFinalTotal()
					+ finalOrderTotal);
		}

		logger.info("Method End >>> applyComboPromoOnPriorityTwo()");
	}

	/**
	 * @param orderDataBean
	 * @param orderItems
	 * @param orderSKUQty
	 * @param orderItemPrices
	 * @param comboPromoPrice
	 * @param finalOrderTotal
	 */
	private void applyComboPromoOnPriorityOne(
			final OrderDataBean orderDataBean,
			final List<OrderItemBean> orderItems,
			final List<Integer> orderSKUQty,
			final List<Double> orderItemPrices, final double comboPromoPrice) {
		int comboCount;

		// Check the comboCount for x+y combination e.g if order has 9
		// Orange + 3 Apples then Combo Count will
		// be 3 in case of Priority One for this promotion.
		comboCount = calulateCombosCount(orderDataBean.getOrderItems(),
				orderSKUQty, 0);

		proportionateDiscountInOrderItems(orderItems, orderItemPrices,
				comboPromoPrice, comboCount);
		double finalOrderTotal = 0.0;
		for (OrderItemBean orderItem : orderItems) {
			finalOrderTotal = finalOrderTotal + orderItem.getOfferItemsTotal();
		}
		orderDataBean.setOrderFinalTotal(finalOrderTotal);

		// logic for n X and m Y order scenario where n < m and combo promotion
		// is at priority one
		comboCount = calulateCombosCount(orderDataBean.getOrderItems(),
				orderSKUQty, 0);
		if (0 == orderItems.get(0).getRemainingItems()) {
			finalOrderTotal = finalOrderTotal
					+ orderItems.get(1).getRemainingItems()
					* orderItems.get(1).getItems().getItemCurrentPrice();
			orderDataBean.setOrderFinalTotal(finalOrderTotal);
		}

		logger.info(" applyComboPromoOnPriorityOne number of combos for this promotion in order are "
				+ comboCount
				+ "combo offer price is"
				+ comboPromoPrice
				+ "final Order total" + finalOrderTotal);

	}

	// x-x(x+y -z)/x+y will give the discounted price of x in case of
	// promotion3
	private void proportionateDiscountInOrderItems(
			final List<OrderItemBean> orderItems,
			final List<Double> orderItemPrices, final double comboPromoPrice,
			final int comboCount) {
		double proportionDscnt = 0.0; // Discount in item level proportion
		for (final OrderItemBean orderItem : orderItems) {
			final double itemCurrentPrice = orderItem.getItems()
					.getItemCurrentPrice();
			// x-x(x+y -z)/x+y will give the discounted price of x in case of
			// promotion3
			if (comboCount != 0) {
				proportionDscnt = comboCount
						* (itemCurrentPrice - (itemCurrentPrice
								* (orderItemPrices.get(0)
										+ orderItemPrices.get(1) - comboPromoPrice) / (orderItemPrices
								.get(0) + orderItemPrices.get(1))));
				// 3(120 - (120 * (120 + 180 - 200)/(120+180)))
				logger.info(" Combo Order SKUs"
						+ orderItem.getItems().getSkuId()
						+ "item current price is " + itemCurrentPrice
						+ " orderItemPrices.get(0) " + orderItemPrices.get(0)
						+ "orderItemPrices.get(1) " + orderItemPrices.get(1)
						+ "proportionate price for one qualifying SKU is "
						+ proportionDscnt);
				orderItem.setOfferItemsTotal(proportionDscnt);
				orderItem.setItemOfferQuantity(comboCount);
				orderItem.setRemainingItems(orderItem.getRemainingItems()
						- comboCount);

			}
		}

	}

	private int calulateCombosCount(final List<OrderItemBean> orderItems,
			final List<Integer> orderSKUQty, final int itemsForPromo) {
		int comboCount = 0;

		for (final OrderItemBean orderItem : orderItems) {
			if (orderItem.getItemOfferQuantity() == 0) {
				if (orderSKUQty.get(0) > orderSKUQty.get(1)) {
					comboCount = orderSKUQty.get(1);
				} else {
					comboCount = orderSKUQty.get(0);
				}
			} else {
				if (orderSKUQty.get(0) > orderSKUQty.get(1)
						&& itemsForPromo < orderSKUQty.get(1)) {
					comboCount = itemsForPromo;
				} else if (orderSKUQty.get(0) > orderSKUQty.get(1)
						&& itemsForPromo > orderSKUQty.get(1)) {
					comboCount = orderSKUQty.get(1);
				} else {
					comboCount = orderSKUQty.get(1);
				}
			}
		}

		return comboCount;
	}
}
