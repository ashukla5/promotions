package org.abhi.promotion.beans;

import java.util.List;

public class OrderDataBean {

    private String orderId;
    private double orderTotal;
    private double orderTotalWithPromotion;
    List<OrderItemBean> orderItems;
    private PromotionsDataBean promotions;

    public PromotionsDataBean getPromotions() {
        return promotions;
    }

    public void setPromotions(
            PromotionsDataBean promotions) {
        this.promotions = promotions;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(
            String orderId) {
        this.orderId = orderId;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(
            double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public double getOrderTotalWithPromotion() {
        return orderTotalWithPromotion;
    }

    public void setOrderTotalWithPromotion(
            double discountTotal) {
        this.orderTotalWithPromotion = discountTotal;
    }

    public List<OrderItemBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(
            List<OrderItemBean> orderItems) {
        this.orderItems = orderItems;
    }

}
