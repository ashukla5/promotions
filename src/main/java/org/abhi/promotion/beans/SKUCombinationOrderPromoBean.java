package org.abhi.promotion.beans;

import java.util.List;

/**
 * Bean class for SKUCombinationOrder Promotion
 * 
 * @author ashu10
 *
 */
public class SKUCombinationOrderPromoBean {

	/**
	 * promotionId
	 */
	private String promotionId;

	/**
	 * orderitems participating in this promotion
	 */
	private List<OrderItemBean> orderitems;

	/**
	 * qualifying order amount in this promotion
	 */
	private double offerOrderAmount;

	/**
	 * priority of the promotion
	 */
	private int promotionPriority;

	/**
	 * @return the promotionId
	 */
	public String getPromotionId() {
		return promotionId;
	}

	/**
	 * @param promotionId
	 *            the promotionId to set
	 */
	public void setPromotionId(final String promotionId) {
		this.promotionId = promotionId;
	}

	/**
	 * @return the orderitems
	 */
	public List<OrderItemBean> getOrderitems() {
		return orderitems;
	}

	/**
	 * @param orderitems
	 *            the orderitems to set
	 */
	public void setOrderitems(final List<OrderItemBean> orderitems) {
		this.orderitems = orderitems;
	}

	/**
	 * @return the offerOrderAmount
	 */
	public double getOfferOrderAmount() {
		return offerOrderAmount;
	}

	/**
	 * @param offerOrderAmount
	 *            the offerOrderAmount to set
	 */
	public void setOfferOrderAmount(final double offerOrderAmount) {
		this.offerOrderAmount = offerOrderAmount;
	}

	/**
	 * @return the promotionPriority
	 */
	public int getPromotionPriority() {
		return promotionPriority;
	}

	/**
	 * @param promotionPriority
	 *            the promotionPriority to set
	 */
	public void setPromotionPriority(final int promotionPriority) {
		this.promotionPriority = promotionPriority;
	}

}
