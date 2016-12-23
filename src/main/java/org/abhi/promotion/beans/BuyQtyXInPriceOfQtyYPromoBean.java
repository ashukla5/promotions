package org.abhi.promotion.beans;

public class BuyQtyXInPriceOfQtyYPromoBean {

    private String promotionId;
    private String skuId;
    private int offerQuantity;
    private int qualifiyingQuantity;
    private double derivedPercentOff;
    private int promotionPriority;

    public int getPromotionPriority() {
        return promotionPriority;
    }

    public void setPromotionPriority(
            int promotionPriority) {
        this.promotionPriority = promotionPriority;
    }

    public double getDerivedPercentOff() {
        return derivedPercentOff;
    }

    public void setDerivedPercentOff(
            double derivedPercentOff) {
        this.derivedPercentOff = derivedPercentOff;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(
            String promotionId) {
        this.promotionId = promotionId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(
            String skuId) {
        this.skuId = skuId;
    }

    public int getOfferQuantity() {
        return offerQuantity;
    }

    public void setOfferQuantity(
            int offerQuantity) {
        this.offerQuantity = offerQuantity;
    }

    public int getQualifiyingQuantity() {
        return qualifiyingQuantity;
    }

    public void setQualifiyingQuantity(
            int qualifiyingQuantity) {
        this.qualifiyingQuantity = qualifiyingQuantity;
    }

}
