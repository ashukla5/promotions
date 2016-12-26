package org.abhi.promotion.beans;

/**
 * Bean class for percentage off promotion
 * 
 * @author ashu10
 *
 */
 
public class PercentOffOnCategotryPromoBean {

	/**
	 * promotionId
	 */
	private String promotionId;

	/**
	 * skuId
	 */
	private String skuId;

	/**
	 * percentOff
	 */
	private double percentOff;

	/**
	 * promotionPriority
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
	 * @return the percentOff
	 */
	public double getPercentOff() {
		return percentOff;
	}

	/**
	 * @param percentOff
	 *            the percentOff to set
	 */
	public void setPercentOff(final double percentOff) {
		this.percentOff = percentOff;
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
