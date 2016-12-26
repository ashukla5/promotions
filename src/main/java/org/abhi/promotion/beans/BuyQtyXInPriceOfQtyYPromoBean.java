package org.abhi.promotion.beans;

/**
 * Bean class for BuyQtyXInPriceOfQtyY
 * 
 * @author ashu10
 */
public class BuyQtyXInPriceOfQtyYPromoBean {
	
	/**
	 * promotionId
	 */

	private String promotionId;

	/**
	 * Item Id on which promo will be applied.
	 */
	private String skuId;

	/**
	 * purchase quantity
	 */
	private int offerQuantity;

	/**
	 * quantity whose price will be given as per promo.
	 */
	private int currentQuantity;

	/**
	 * derived percent off after promotion on single item price.
	 */
	private double derivedPercentOff;

	/**
	 * promotion priority
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
	 * @return the offerQuantity
	 */
	public int getOfferQuantity() {
		return offerQuantity;
	}

	/**
	 * @param offerQuantity
	 *            the offerQuantity to set
	 */
	public void setOfferQuantity(final int offerQuantity) {
		this.offerQuantity = offerQuantity;
	}

	/**
	 * @return the currentQuantity
	 */
	public int getCurrentQuantity() {
		return currentQuantity;
	}

	/**
	 * @param currentQuantity
	 *            the currentQuantity to set
	 */
	public void setCurrentQuantity(final int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	/**
	 * @return the derivedPercentOff
	 */
	public double getDerivedPercentOff() {
		return derivedPercentOff;
	}

	/**
	 * @param derivedPercentOff
	 *            the derivedPercentOff to set
	 */
	public void setDerivedPercentOff(final double derivedPercentOff) {
		this.derivedPercentOff = derivedPercentOff;
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
