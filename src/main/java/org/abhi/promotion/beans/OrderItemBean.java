package org.abhi.promotion.beans;

/**
 * Bean class for Order lines in the cart.
 * 
 * @author ashu10
 *
 */
 
public class OrderItemBean {

	/**
	 * orderItemId
	 */
	private String orderItemId;

	/**
	 * items associated with order line
	 */
	private ItemBean items;

	/**
	 * itemQunatity
	 */
	private int itemQunatity;

	/**
	 * itemOfferQuantity
	 */
	private int itemOfferQuantity;

	/**
	 * order line total
	 */
	private double orderItemsTotal;

	/**
	 * itemTotalPostDiscount
	 */
	private double offerItemsTotal;

	/**
	 * remainingItems
	 * 
	 */
	private int remainingItems;

	/**
	 * skuId
	 */
	private String skuId;

	/**
	 * @return the offerItemTotal
	 */
	public double getOfferItemsTotal() {
		return offerItemsTotal;
	}

	/**
	 * @param offerItemTotal
	 *            the offerItemTotal to set
	 */
	public void setOfferItemsTotal(final double offerItemsTotal) {
		this.offerItemsTotal = offerItemsTotal;
	}

	/**
	 * @return the orderItemId
	 */
	public String getOrderItemId() {
		return orderItemId;
	}

	/**
	 * @param orderItemId
	 *            the orderItemId to set
	 */
	public void setOrderItemId(final String orderItemId) {
		this.orderItemId = orderItemId;
	}

	/**
	 * @return the items
	 */
	public ItemBean getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(final ItemBean items) {
		this.items = items;
	}

	/**
	 * @return the itemQunatity
	 */
	public int getItemQunatity() {
		return itemQunatity;
	}

	/**
	 * @param itemQunatity
	 *            the itemQunatity to set
	 */
	public void setItemQunatity(final int itemQunatity) {
		this.itemQunatity = itemQunatity;
	}

	/**
	 * @return the itemOfferQuantity
	 */
	public int getItemOfferQuantity() {
		return itemOfferQuantity;
	}

	/**
	 * @param itemOfferQuantity
	 *            the itemOfferQuantity to set
	 */
	public void setItemOfferQuantity(final int itemOfferQuantity) {
		this.itemOfferQuantity = itemOfferQuantity;
	}

	/**
	 * @return the orderItemsTotal
	 */
	public double getOrderItemsTotal() {
		return orderItemsTotal;
	}

	/**
	 * @param orderItemsTotal
	 *            the orderItemsTotal to set
	 */
	public void setOrderItemsTotal(final double orderItemsTotal) {
		this.orderItemsTotal = orderItemsTotal;
	}

	/**
	 * @return the skuId
	 */
	public String getSkuId() {
		return skuId;
	}

	/**
	 * @param skuId
	 *            the skuId to set
	 */
	public void setSkuId(final String skuId) {
		this.skuId = skuId;
	}

	/**
	 * @return the remainingItems
	 */
	public int getRemainingItems() {
		return remainingItems;
	}

	/**
	 * @param remainingItems
	 *            the remainingItems to set
	 */
	public void setRemainingItems(final int remainingItems) {
		this.remainingItems = remainingItems;
	}

}
