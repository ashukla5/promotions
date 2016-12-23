package org.abhi.promotion.beans;

public class PercentOffOnCategotryPromoBean {

    private String promotionId;
    private String skuId;
    private double percentOff;
    private int promotionPriority;

    public int getPromotionPriority() {
        return promotionPriority;
    }

    public void setPromotionPriority(
            int promotionPriority) {
        this.promotionPriority = promotionPriority;
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

    public double getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(
            double percentOff) {
        this.percentOff = percentOff;
    }

}
