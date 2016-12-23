package org.abhi.promotion.beans;

public class ItemBean {

	private String skuId;
	private double itemCurrentPrice;
	private double itemOfferPrice;
	private double percentOff;

	public double getPercentOff() {
		return percentOff;
	}

	public void setPercentOff(double percentOff) {
		this.percentOff = percentOff;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public double getItemCurrentPrice() {
		return itemCurrentPrice;
	}

	public void setItemCurrentPrice(double itemCurrentPrice) {
		this.itemCurrentPrice = itemCurrentPrice;
	}

	public double getItemOfferPrice() {
		return itemOfferPrice;
	}

	public void setItemOfferPrice(double itemOfferPrice) {
		this.itemOfferPrice = itemOfferPrice;
	}

}
