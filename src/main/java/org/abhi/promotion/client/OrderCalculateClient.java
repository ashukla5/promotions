package org.abhi.promotion.client;

import org.abhi.promotion.dataload.OrderPromotionDataLoader;

public class OrderCalculateClient {

	public static void main(String[] args) {

		OrderPromotionDataLoader orderPromoDataLoader = new OrderPromotionDataLoader();
		orderPromoDataLoader.calculateOrderTotal();
		
	}
}
