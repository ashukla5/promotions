package org.abhi.promotion.beans;

import java.util.List;

/**
 * Bean class to hold the Order data once items are added to the cart.
 * 
 * @author ashu10
 */
public class OrderDataBean {

	/**
	 * OrderId
	 */
	private String orderId;
	/**
	 * Final cart total before promotion
	 */
	private double orderTotal;
	/**
	 * Final cart/order total after applying promotion
	 */
	private double orderFinalTotal;
	/**
	 * Order line items added to the order.
	 */
	private List<OrderItemBean> orderItems;

	/**
	 * getOrderId()
	 * 
	 * @return orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * setOrderId()
	 * 
	 * @param orderId
	 */
	public void setOrderId(final String orderId) {
		this.orderId = orderId;
	}

	/**
	 * getOrderTotal()
	 * 
	 * @return orderTotal
	 */
	public double getOrderTotal() {
		return orderTotal;
	}

	/**
	 * setOrderTotal()
	 * 
	 * @param orderTotal
	 */
	public void setOrderTotal(final double orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * getOrderTotalWithPromotion()
	 * 
	 * @return orderTotalWithPromotion
	 */
	public double getOrderFinalTotal() {
		return orderFinalTotal;
	}

	/**
	 * setOrderTotalWithPromotion()
	 * 
	 * @param discountTotal
	 */
	public void setOrderFinalTotal(final double discountTotal) {
		this.orderFinalTotal = discountTotal;
	}

	/**
	 * getOrderItems()
	 * 
	 * @return orderItems
	 */
	public List<OrderItemBean> getOrderItems() {
		return orderItems;
	}

	/**
	 * setOrderItems()
	 * 
	 * @param orderItems
	 */
	public void setOrderItems(final List<OrderItemBean> orderItems) {
		this.orderItems = orderItems;
	}

}
