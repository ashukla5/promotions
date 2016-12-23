package org.abhi.promotion.beans;

public class PromotionsDataBean {

	private BuyQtyXInPriceOfQtyYPromoBean buyXInYPromo;
	private PercentOffOnCategotryPromoBean percentOffPromo;
	private SKUCombinationOrderPromoBean skuCombinationPromo;

	public BuyQtyXInPriceOfQtyYPromoBean getBuyXInYPromo() {
		return buyXInYPromo;
	}

	public void setBuyXInYPromo(BuyQtyXInPriceOfQtyYPromoBean buyXInYPromo) {
		this.buyXInYPromo = buyXInYPromo;
	}

	public PercentOffOnCategotryPromoBean getPercentOffPromo() {
		return percentOffPromo;
	}

	public void setPercentOffPromo(
			PercentOffOnCategotryPromoBean percentOffPromo) {
		this.percentOffPromo = percentOffPromo;
	}

	public SKUCombinationOrderPromoBean getSkuCombinationPromo() {
		return skuCombinationPromo;
	}

	public void setSkuCombinationPromo(
			SKUCombinationOrderPromoBean skuCombinationPromo) {
		this.skuCombinationPromo = skuCombinationPromo;
	}

}
