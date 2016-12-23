package org.abhi.promotion.dataload;

import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.PromotionsDataBean;
import org.abhi.promotion.engine.OrderCalculator;
import org.abhi.promotion.engine.PromotionCalculator;


public class OrderPromotionDataLoader {
	
	public void calculateOrderTotal(){
		final String orderDataFilePath = "C://PromotionEngine//src//main//resources//OrderData.txt";
		final String promotionDataFilePath = "C://PromotionEngine//src//main//resources//PromotionData.txt";

		double finalOrderTotal = 0.0;

		OrderDataReader orderDataReader = new OrderDataReader();
		PromotionDataReader promotionDataReader = new PromotionDataReader();

		OrderDataBean orderDataBean = orderDataReader
				.readOrderData(orderDataFilePath);
		PromotionsDataBean promotionsDataBean = promotionDataReader
				.readPromotionData(promotionDataFilePath);
		
		//Call order calculator to calculate order without promotion.
		OrderCalculator orderCalculator = new OrderCalculator();
		orderCalculator.calculateCurrentOrderTotal(orderDataBean);
		
		PromotionCalculator promotionCalculator = new PromotionCalculator();
		promotionCalculator.calculatePromotions(orderDataBean, promotionsDataBean);

		System.out.println("Current order total before promotions is"
				+ orderDataBean.getOrderTotal());
	}

}
