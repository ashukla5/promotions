package org.abhi.promotion.testing;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.OrderItemBean;
import org.abhi.promotion.engine.OrderCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(OrderCalculator.class)
public class OrderCalculatorTest {

	@Test
	public void calculateCurrentOrderTotalTest() {
		OrderCalculator ordCalc = new OrderCalculator();
		OrderDataBean ord = new OrderDataBean();
		OrderItemBean orderItem1 = new OrderItemBean();
		orderItem1.setOrderItemsTotal(1.1d);
		OrderItemBean orderItem2 = new OrderItemBean();
		orderItem2.setOrderItemsTotal(2.0d);
		List<OrderItemBean> orderItems = new ArrayList<OrderItemBean>();
		orderItems.add(orderItem1);
		orderItems.add(orderItem2);
		ord.setOrderItems(orderItems);
		ordCalc.calculateCurrentOrderTotal(ord);
		Assert.assertEquals(3.1d, ord.getOrderTotal());
	}
	
}
