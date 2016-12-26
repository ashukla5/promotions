package org.abhi.promotion.client;

import org.abhi.promotion.dataload.OrderPromotionDataLoader;

/**
 * Client class to run the application
 * 
 * @author ashu10
 */
public final class OrderCalculateClient {

	private OrderCalculateClient() {
	}

	/**
	 * Client main method to invoke the application
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {

		final OrderPromotionDataLoader dataloader = new OrderPromotionDataLoader();
		dataloader.calculateOrderTotal();
	}
}
