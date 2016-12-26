package org.abhi.promotion.dataload;

import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.PromotionsDataBean;
import org.abhi.promotion.engine.OrderCalculator;
import org.abhi.promotion.engine.PromotionCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to read the promotion and order data.
 * 
 * @author ashu10
 *
 */
public class OrderPromotionDataLoader {

	private Logger logger = LoggerFactory
			.getLogger(OrderPromotionDataLoader.class);

	/**
	 * Method to read and calculate the order total after applying promotions.
	 */
	public void calculateOrderTotal() {
		final String orderDataFilePath = System.getProperty("user.dir")
				+ "//src//main//resources//OrderData.txt";
		final String promotionFilePath = System.getProperty("user.dir")
				+ "//src//main//resources//PromotionData.txt";

		final OrderDataReader orderDataReader = new OrderDataReader();
		final PromotionDataReader promoDataReader = new PromotionDataReader();

		final OrderDataBean orderDataBean = new OrderDataBean();
		orderDataReader.readOrderData(orderDataBean, orderDataFilePath);

		// Call order calculator to calculate order without promotion.
		final OrderCalculator orderCalculator = new OrderCalculator();
		orderCalculator.calculateCurrentOrderTotal(orderDataBean);

		logger.debug("Current order total without promotions is",
				orderDataBean.getOrderTotal());

		final PromotionsDataBean promoDataBean = promoDataReader
				.readPromotionData(promotionFilePath);

		orderCalculator.calculateOrder(orderDataBean, promoDataBean);

	}
}
