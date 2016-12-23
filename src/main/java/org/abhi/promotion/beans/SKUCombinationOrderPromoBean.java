package org.abhi.promotion.beans;

import java.util.List;

public class SKUCombinationOrderPromoBean {

    private String promotionId;
    private List<OrderItemBean> orderitems;
    private double qualifyingOrderAmount;
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

    public List<OrderItemBean> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(
            List<OrderItemBean> orderitems) {
        this.orderitems = orderitems;
    }

    public double getQualifyingOrderAmount() {
        return qualifyingOrderAmount;
    }

    public void setQualifyingOrderAmount(
            double qualifyingOrderAmount) {
        this.qualifyingOrderAmount = qualifyingOrderAmount;
    }

}
