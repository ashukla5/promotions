package org.abhi.promotion.beans;

/**
 * Bean class for Promotions.
 * 
 * @author ashu10
 *
 */
public class PromotionsDataBean {

	/**
	 * BuyQtyXInPriceOfQtyY Promotion field
	 */
	private BuyQtyXInPriceOfQtyYPromoBean buyXInYPromo;

	/**
	 * PercentOffOnCategotry Promotion field
	 */
	private PercentOffOnCategotryPromoBean percentOffPromo;

	/**
	 * SKUCombinationOrder Promotion field
	 */
	private SKUCombinationOrderPromoBean skuComboPromo;

	/**
	 * BuyQtyXInPriceOfQtyY Promotion getter
	 * 
	 * @return buyXInYPromo
	 */
	public BuyQtyXInPriceOfQtyYPromoBean getBuyXInYPromo() {
		return buyXInYPromo;
	}

	/**
	 * setBuyXInYPromo
	 * 
	 * @param buyXInYPromo
	 */
	public void setBuyXInYPromo(final BuyQtyXInPriceOfQtyYPromoBean buyXInYPromo) {
		this.buyXInYPromo = buyXInYPromo;
	}

	/**
	 * getPercentOffPromo
	 * 
	 * @return percentOffPromo
	 */
	public PercentOffOnCategotryPromoBean getPercentOffPromo() {
		return percentOffPromo;
	}

	/**
	 * setPercentOffPromo
	 * 
	 * @param percentOffPromo
	 */
	public void setPercentOffPromo(
			final PercentOffOnCategotryPromoBean percentOffPromo) {
		this.percentOffPromo = percentOffPromo;
	}

	/**
	 * @return the skuComboPromo
	 */
	public SKUCombinationOrderPromoBean getSkuComboPromo() {
		return skuComboPromo;
	}

	/**
	 * @param skuComboPromo
	 *            the skuComboPromo to set
	 */
	public void setSkuComboPromo(final SKUCombinationOrderPromoBean skuComboPromo) {
		this.skuComboPromo = skuComboPromo;
	}

}
