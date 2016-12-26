package org.abhi.promotion.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.OrderItemBean;
import org.abhi.promotion.beans.PromotionsDataBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to calculate the order and promotion data and provide optimal order
 * total to user.
 * 
 * @author ashu10
 *
 */
public class OrderCalculator {

	private Logger logger = LoggerFactory.getLogger(OrderCalculator.class);

	/**
	 * Method to calculate the promotions on order and provide best order total
	 * to client.
	 * 
	 * @param orderDataBean
	 * @param promoDataBean
	 */
	public void calculateOrder(final OrderDataBean orderDataBean,
			final PromotionsDataBean promoDataBean) {

		// Calculate the percentage off on SKU for all the promotions,
		// %off information cab ne utilized in strking out the promotion
		// permutations.
		// percentageOffOnAllPromotions();

		final Map<String, Double> orderDataMap = calculatePossiblePromotionCombinations(
				orderDataBean, promoDataBean);

		// sort the map based on orderTotalWithPromotion
		final double finalOrderTotal = finalOrderTotal(orderDataMap);

		logger.info("Final Order Total after promotions is " + finalOrderTotal);

	}

	/**
	 * Method to create map which will have the key as priority order of
	 * promotions and value as OrderDataBean.
	 * 
	 * @param orderDataBean
	 * @param promotionsDataBean
	 * @return
	 */
	private Map<String, Double> calculatePossiblePromotionCombinations(
			final OrderDataBean orderDataBean,
			final PromotionsDataBean promoBean) {

		// There would be 3P2 permutations for promotions would be there in case
		// there are 3 promotions to be applied.

		// TODO Hardcoded number of combinations of promotions so far this logic
		// need to be generalized.
		final String[] promoPriorities = getPromotionPriorityCombinaions();

		// final Map<String, OrderDataBean> orderMapWithPromo =
		// calculateOrderForPromoPriorityOrder(
		// promoPriorities, orderDataBean, promoBean);
		final PromotionCalculator promoCalculator = new PromotionCalculator();
		final Map<String, Double> orderMapWithPromo = promoCalculator
				.calculateOrderForPromoPriorityOrder(promoPriorities,
						orderDataBean, promoBean);
		/*
		 * for (final String key : orderMapWithPromo.keySet()) {
		 * logger.info("Promotion order " + key + " and Final Order Total is " +
		 * orderMapWithPromo.get(key)); }
		 */
		return orderMapWithPromo;

	}

	// this is a dummy method which is so far mocking the promotions priority
	// combinations.
	// This logic need to be generalized.
	private String[] getPromotionPriorityCombinaions() {

		// If there are P1, P2 and P3 promotions available then in there would
		// be following possible promotions orders and possibilities.
		// e.g 132 means promotion order is P1, P3 then P2 or if promotion order
		// is 103 which means apply promotion P1 then dont apply P2 promotion
		// then
		// apply P3 promotion.

		final String[] priorityOrders = { "321", "132", "231", "213", "123",
				"312" };

		return priorityOrders;

	}

	/**
	 * Method to calculate the current order total without promotion.
	 * 
	 * @param orderDataBean
	 */
	public void calculateCurrentOrderTotal(final OrderDataBean orderDataBean) {
		final List<OrderItemBean> orderItems = orderDataBean.getOrderItems();

		double currentOrderTotal = 0.0;

		for (final OrderItemBean orderItem : orderItems) {
			currentOrderTotal = currentOrderTotal
					+ orderItem.getOrderItemsTotal();
		}

		orderDataBean.setOrderTotal(currentOrderTotal);
	}

	private double finalOrderTotal(final Map<String, Double> orderDataMap) {

		final List<Double> mapValues = new ArrayList<Double>();
		for (String key : orderDataMap.keySet()) {
			mapValues.add(orderDataMap.get(key));
		}

		Collections.sort(mapValues);

		return mapValues.get(0);
	}
}
