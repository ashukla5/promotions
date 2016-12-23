package org.abhi.promotion.beans;

public class OrderItemBean {

    private String orderItemId;
    private ItemBean items;
    private int itemQunatity;
    private int itemOfferQuantity;
    private double orderItemsTotal;
    private double itemTotalPostDiscount;
    private String skuId;

    public int getItemOfferQuantity() {
        return itemOfferQuantity;
    }

    public void setItemOfferQuantity(
            int itemOfferQuantity) {
        this.itemOfferQuantity = itemOfferQuantity;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(
            String skuId) {
        this.skuId = skuId;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(
            String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public double getOrderItemsTotal() {
        return orderItemsTotal;
    }

    public void setOrderItemsTotal(
            double orderItemsTotal) {
        this.orderItemsTotal = orderItemsTotal;
    }

    public double getItemTotalPostDiscount() {
        return itemTotalPostDiscount;
    }

    public void setItemTotalPostDiscount(
            double itemsDiscountTotal) {
        this.itemTotalPostDiscount = itemsDiscountTotal;
    }

    public ItemBean getItems() {
        return items;
    }

    public void setItems(
            ItemBean items) {
        this.items = items;
    }

    public int getItemQunatity() {
        return itemQunatity;
    }

    public void setItemQunatity(
            int itemQunatity) {
        this.itemQunatity = itemQunatity;
    }

}
