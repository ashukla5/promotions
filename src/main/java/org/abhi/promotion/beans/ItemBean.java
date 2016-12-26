package org.abhi.promotion.beans;

/**
 * Bean class for itesm which will be added to the cart.
 * @author ashu10
 */
public class ItemBean {

    /**
     * skuId for item added in the cart.
     */
    private String skuId;
    /**
     * price of item which is being added to the cart.
     */
    private double itemCurrentPrice;
    /**
     * item's price after applying promotion.
     */
    private double itemOfferPrice;
    /**
     * how many % off on itemprice after promotion.
     */
    private double percentOff;

    /**
     * getPercentOff()
     * @return percentOff
     */
    public double getPercentOff() {
        return percentOff;
    }

    /**
     * setPercentOff()
     * @param percentOff
     */
    public void setPercentOff(
            final double percentOff) {
        this.percentOff = percentOff;
    }

    /**
     * getSkuId()
     * @return skuId
     */
    public String getSkuId() {
        return skuId;
    }

    /**
     * setSkuId()
     * @param skuId
     */
    public void setSkuId(
            final String skuId) {
        this.skuId = skuId;
    }

    /**
     * getItemCurrentPrice()
     * @return itemCurrentPrice
     */
    public double getItemCurrentPrice() {
        return itemCurrentPrice;
    }

    /**
     * setItemCurrentPrice()
     * @param itemCurrentPrice
     */
    public void setItemCurrentPrice(
            final double itemCurrentPrice) {
        this.itemCurrentPrice = itemCurrentPrice;
    }

    /**
     * getItemOfferPrice
     * @return itemOfferPrice
     */
    public double getItemOfferPrice() {
        return itemOfferPrice;
    }

    /**
     * setItemOfferPrice()
     * @param itemOfferPrice
     */
    public void setItemOfferPrice(
            final double itemOfferPrice) {
        this.itemOfferPrice = itemOfferPrice;
    }

}
