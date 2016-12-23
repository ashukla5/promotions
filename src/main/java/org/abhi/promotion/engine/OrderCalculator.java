package org.abhi.promotion.engine;

import java.util.List;

import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.OrderItemBean;

public class OrderCalculator {

	public void calculateCurrentOrderTotal(OrderDataBean orderDataBean) {
		List<OrderItemBean> orderItems = orderDataBean.getOrderItems();

		double currentOrderTotal = 0.0;

		for (OrderItemBean orderItem : orderItems) {
			currentOrderTotal = currentOrderTotal
					+ orderItem.getOrderItemsTotal();
		}

		orderDataBean.setOrderTotal(currentOrderTotal);
	}
}
