package org.abhi.promotion.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.OrderItemBean;
import org.abhi.promotion.beans.PromotionsDataBean;
import org.abhi.promotion.constants.OrderPromotionConstants;

public class PromotionCalculator {

    public void calculatePromotions(
            OrderDataBean orderDataBean, PromotionsDataBean promotionsDataBean) {

        // Calculate the percentage off on SKU for all the promotions,
        // %off information cab ne utilized in strking out the promotion
        // permutations.
        // percentageOffOnAllPromotions();

        Map<String, OrderDataBean> orderDataMap =
                calculatePossiblePromotionCombinations(orderDataBean, promotionsDataBean);

        // sort the map based on orderTotalWithPromotion
        double finalOrderTotal = sortAndBestOrderTotal(orderDataMap);

    }

    /**
     * Method to create map which will have the key as priority order of promotions and value as OrderDataBean based on
     * calculation of that promotion calculation order.
     * @param orderDataBean
     * @param promotionsDataBean
     * @return
     */
    private Map<String, OrderDataBean> calculatePossiblePromotionCombinations(
            OrderDataBean orderDataBean, PromotionsDataBean promotionsDataBean) {

        // There would be 3P2 permutations for promotions would be there in case
        // there are 3 promotions to be applied.

        // TODO Hardcoded number of combinations of promotions so far this logic
        // need to be generalized.
        int numberOfPromo = 3;
        String[] promoPriorityOrder = getPromotionPriorityCombinaions(numberOfPromo);

        Map<String, OrderDataBean> orderDataMapPostPromotions =
                calculateOrderForPromoPriorityOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);

        return orderDataMapPostPromotions;

    }

    // this is a dummy method which is so far mocking the promotions priority
    // combinations.
    // This logic need to be generalized.
    private String[] getPromotionPriorityCombinaions(
            int numberOfPromos) {

        // If there are P1, P2 and P3 promotions available then in there would
        // be following possible promotions orders and possibilities.
        // e.g 132 means promotion order is P1, P3 then P2 or if promotion order
        // is 103 which means apply promotion P1 then dont apply P2 promotion
        // then
        // apply P3 promotion.

        String[] possiblePromoPriorityOrder =
                {"123", "132", "213", "231", "312", "321", "23", "32", "13", "31", "12", "21", "1", "2", "3"};

        return possiblePromoPriorityOrder;

    }

    private Map<String, OrderDataBean> calculateOrderForPromoPriorityOrder(
            String[] promoCombinations, OrderDataBean orderDataBean, PromotionsDataBean promotionsDataBean) {

        Map<String, OrderDataBean> orderDataMapPostPromotions = new HashMap<String, OrderDataBean>();

        for (String promoPriorityOrder : promoCombinations) {

            switch (promoPriorityOrder) {
            case "123":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("123", orderDataBean);
            case "132":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("132", orderDataBean);
            case "213":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("213", orderDataBean);
            case "231":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("231", orderDataBean);
            case "321":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("321", orderDataBean);
            case "312":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("312", orderDataBean);
            case "23":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("23", orderDataBean);
            case "32":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("32", orderDataBean);
            case "13":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("13", orderDataBean);
            case "31":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("31", orderDataBean);
            case "12":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("12", orderDataBean);
            case "21":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("21", orderDataBean);
            case "1":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("1", orderDataBean);
            case "2":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("2", orderDataBean);
            case "3":
                applyPromotionOnOrder(promoPriorityOrder, orderDataBean, promotionsDataBean);
                orderDataMapPostPromotions.put("3", orderDataBean);
            }
        }
        return orderDataMapPostPromotions;
    }

    // method to apply promotion based on promotion priority.
    private void applyPromotionOnOrder(
            String promoPriorityOder, OrderDataBean orderDataBean, PromotionsDataBean promotionsDataBean) {
        char[] promos = promoPriorityOder.toCharArray();
        for (int i = 0; i < promos.length; i++) {
            int promotionPriority = i + 1;
            if (promos[i] == '1') {
                promotionsDataBean.getBuyXInYPromo().setPromotionPriority(promotionPriority);
                applyBuyXInPriceOfYPromo(orderDataBean, promotionsDataBean);
            }
            if (promos[i] == '2') {
                promotionsDataBean.getPercentOffPromo().setPromotionPriority(promotionPriority);
                applyPercentageOffOnCategoryPromo(orderDataBean, promotionsDataBean);
            }
            if (promos[i] == '3') {
                promotionsDataBean.getSkuCombinationPromo().setPromotionPriority(promotionPriority);
                if (null != orderDataBean.getOrderItems() && 1 < orderDataBean.getOrderItems().size()) {
                    applyCategoryComboPromotion(orderDataBean, promotionsDataBean);
                }
            }
            double orderTotal = 0.0;
            for (OrderItemBean orderItemBean : orderDataBean.getOrderItems()) {

                orderTotal = orderTotal + orderItemBean.getItemTotalPostDiscount();
            }
            orderDataBean.setOrderTotalWithPromotion(orderTotal);
        }
    }

    // This promotion will be applied as per user cart items hence applying the
    // business rules and then cart calculation based on that.
    private void applyBuyXInPriceOfYPromo(
            OrderDataBean orderDataBean, PromotionsDataBean promotionsDataBean) {

        // check applicability of this promotion
        List<OrderItemBean> orderItems = orderDataBean.getOrderItems();
        final int promotionPriority = promotionsDataBean.getBuyXInYPromo().getPromotionPriority();
        for (OrderItemBean orderItem : orderItems) {
            final String promotionSKUId = promotionsDataBean.getBuyXInYPromo().getSkuId();
            final String orderItemSKUId = orderItem.getSkuId();
            final int orderItemQuantity = orderItem.getItemQunatity();
            final int promotionQualifyingQty = promotionsDataBean.getBuyXInYPromo().getOfferQuantity();
            double orderItemTotal = 0.0;
            if (promotionSKUId.equals(orderItemSKUId)) {

                if (promotionPriority == OrderPromotionConstants.PROMOTION_PRIORITY_ONE) {
                    // get the number of times BuyXInY promotion will be applied.
                    int multiplier = orderItemQuantity / promotionQualifyingQty;

                    if (promotionQualifyingQty <= orderItemQuantity) {
                        orderItemTotal =
                                multiplier * (promotionQualifyingQty * orderItem.getItems().getItemCurrentPrice());
                        orderItem.setItemTotalPostDiscount(orderItemTotal);
                        orderItem.setItemOfferQuantity(promotionQualifyingQty * multiplier);
                    }
                } else if (promotionPriority == OrderPromotionConstants.PROMOTION_PRIORITY_TWO) {
                    // BuyXinY promotion can come on priority order 2 only if percentageOff promo or comboCategoryPromo
                    // come before this. If comboCategoryPromo is at priority 1 before BuyXinY
                    // get the number of times BuyXInY promotion can be applied now.
                    int orderItemLeftForPromo = orderItemQuantity - orderItem.getItemOfferQuantity();
                    int multiplier = orderItemLeftForPromo / promotionQualifyingQty;

                    if (promotionQualifyingQty <= orderItemLeftForPromo) {
                        orderItemTotal =
                                multiplier * (promotionQualifyingQty * orderItem.getItems().getItemCurrentPrice());
                        orderItem.setItemTotalPostDiscount(orderItemTotal);
                        orderItem.setItemOfferQuantity(promotionQualifyingQty * multiplier);

                    } else {

                    }

                }
            }
        }

    }

    private void applyPercentageOffOnCategoryPromo(
            OrderDataBean orderDataBean, PromotionsDataBean promotionsDataBean) {
        List<OrderItemBean> orderItems = orderDataBean.getOrderItems();
        final String promoSKUId = promotionsDataBean.getPercentOffPromo().getSkuId();
        final double percentOff = promotionsDataBean.getPercentOffPromo().getPercentOff();
        final int promotionPriority = promotionsDataBean.getPercentOffPromo().getPromotionPriority();
        double orderItemTotal = 0.0;
        // check applicability of the promotion
        for (OrderItemBean orderItem : orderItems) {
            int orderItemQuantity = orderItem.getItemQunatity();
            final String orderItemSKUId = orderItem.getSkuId();
            if (promoSKUId.equals(orderItemSKUId)) {
                if (promotionPriority == OrderPromotionConstants.PROMOTION_PRIORITY_ONE) {
                    orderItemTotal =
                            orderItem.getItemQunatity()
                                    * (orderItem.getItems().getItemCurrentPrice() * (percentOff / 100));
                    orderItem.setItemTotalPostDiscount(orderItemTotal);

                } else { // this else will take care of PROMOTION_PRIORITY_TWO and PROMOTION_PRIORITY_THREE for
                         // percentageOff promotion
                    int orderItemLeftForPromo = orderItemQuantity - orderItem.getItemOfferQuantity();
                    if (orderItemLeftForPromo > 0) {
                        orderItemTotal =
                                orderItemLeftForPromo
                                        * (orderItem.getItems().getItemCurrentPrice() * (percentOff / 100));
                        orderItem.setItemTotalPostDiscount(orderItemTotal);
                    }

                }
            }

        }
    }

    private void applyCategoryComboPromotion(
            OrderDataBean orderDataBean, PromotionsDataBean promotionsDataBean) {
        List<OrderItemBean> orderItems = orderDataBean.getOrderItems();
        List<OrderItemBean> skuComboOrderItems = promotionsDataBean.getSkuCombinationPromo().getOrderitems();
        final int promotionPriority = promotionsDataBean.getSkuCombinationPromo().getPromotionPriority();
        // check applicability of the promotion
        List<String> comboPromoSKUs = new ArrayList<String>();
        List<Integer> comboPromoSKUsQty = new ArrayList<Integer>();
        for (OrderItemBean skuOrderItem : skuComboOrderItems) {
            comboPromoSKUs.add(skuOrderItem.getItems().getSkuId());
            comboPromoSKUsQty.add(skuOrderItem.getItemQunatity());
        }
        List<String> orderSKUs = new ArrayList<String>();
        List<Integer> orderSKUQty = new ArrayList<Integer>();
        List<Double> orderItemPrices = new ArrayList<Double>();
        int itemOfferQuantity = 0;
        int orderItemsQuantity = 0;
        for (OrderItemBean orderItem : orderItems) {
            itemOfferQuantity = orderItem.getItemOfferQuantity();
            orderItemsQuantity = orderItem.getItemQunatity();
            orderSKUs.add(orderItem.getSkuId());
            orderSKUQty.add(orderItem.getItemQunatity());
            orderItemPrices.add(orderItem.getItems().getItemCurrentPrice());
        }
        int comboCount = 0;
        double comboPromotionPrice = promotionsDataBean.getSkuCombinationPromo().getQualifyingOrderAmount();
        if (comboPromoSKUs.containsAll(orderSKUs)) {
            if (promotionPriority == OrderPromotionConstants.PROMOTION_PRIORITY_ONE) {
                // Check the comboCount for x+y combination e.g if order has 9 Orange + 3 Apples then Combo Count will
                // be 3 in case of Priority One for this promotion.
                comboCount = calulateCombosCount(orderItems, orderSKUQty, 0, promotionsDataBean);
                portionateandApplyComboPromotion(orderItems, orderItemPrices, comboCount, comboPromotionPrice);

                // TODO Calculate left over which is not falling under any of the promotion
            } else if (promotionPriority == OrderPromotionConstants.PROMOTION_PRIORITY_TWO) {

                // This scenario can appear only if priority 1 applied promotion is either BuyXInYPromo or percentageOff
                // promotion. Check the qualifying condition for this promotion's applicability.
                int orderItemLeftForPromo = orderItemsQuantity - itemOfferQuantity;
                comboCount = calulateCombosCount(orderItems, orderSKUQty, orderItemLeftForPromo, promotionsDataBean);
                if (comboCount == 0 && null != orderItems.get(1)) {
                    // calculate order item price individually without promotion
                    orderItems.get(1).setItemTotalPostDiscount((orderItemPrices.indexOf(1) * comboPromoSKUsQty.get(1)));
                    orderItems.get(1).setItemOfferQuantity(comboPromoSKUsQty.get(1));
                } else if (comboCount != 0) {
                    portionateandApplyComboPromotion(orderItems, orderItemPrices, comboCount, comboPromotionPrice);

                    // TODO Calculate left over which is not falling under any of the promotion
                }
            }

            else {

            }
        }

    }

    private void portionateandApplyComboPromotion(
            List<OrderItemBean> orderItems, List<Double> orderItemPrices, int comboCount, double comboPromotionPrice) {

        // x-x(x+y -z)/x+y will give the discounted price of x in case of promotion3
        proportionateDiscountInOrderItems(orderItems, orderItemPrices, comboPromotionPrice, comboCount);
        // set the remaining orderItems on which rest of the promotion can be applied.
        for (OrderItemBean orderItem : orderItems) {
            orderItem.setItemOfferQuantity(comboCount);
        }
    }

    private void proportionateDiscountInOrderItems(
            List<OrderItemBean> orderItems, List<Double> orderItemPrices, final double comboPromotionPrice,
            final int comboCount) {
        double proportionateTotalDiscount = 0.0;
        for (OrderItemBean orderItem : orderItems) {
            double itemCurrentPrice = orderItem.getItems().getItemCurrentPrice();
            // x-x(x+y -z)/x+y will give the discounted price of x in case of promotion3
            if (comboCount != 0) {
                proportionateTotalDiscount =
                        comboCount
                                * (itemCurrentPrice - (itemCurrentPrice
                                        * ((orderItemPrices.indexOf(0) + orderItemPrices.get(1)) - comboPromotionPrice) / (orderItemPrices
                                        .indexOf(0) + orderItemPrices.get(1))));
                orderItem.setItemTotalPostDiscount(proportionateTotalDiscount);
            }
        }
    }

    private int calulateCombosCount(
            List<OrderItemBean> orderItems, List<Integer> orderSKUQty, final int itemQtyLeftForPromo,
            PromotionsDataBean promotionsDataBean) {
        int comboCount = 0;

        for (OrderItemBean orderItem : orderItems) {
            if (orderItem.getItemOfferQuantity() == 0) {
                if (orderSKUQty.get(0) > orderSKUQty.get(1)) {
                    comboCount = orderSKUQty.get(1);
                } else {
                    comboCount = orderSKUQty.get(0);
                }
            } else {
                if (itemQtyLeftForPromo == 0) {
                    comboCount = 0;
                } else {
                    if (orderSKUQty.get(0) > orderSKUQty.get(1) && itemQtyLeftForPromo < orderSKUQty.get(1)) {
                        comboCount = itemQtyLeftForPromo;
                    } else if (orderSKUQty.get(0) > orderSKUQty.get(1) && itemQtyLeftForPromo > orderSKUQty.get(1)) {
                        comboCount = orderSKUQty.get(1);
                    } else {
                        comboCount = orderSKUQty.get(1);
                    }
                }
            }
        }

        return comboCount;
    }

    private double sortAndBestOrderTotal(
            Map<String, OrderDataBean> orderDataMap) {
        Set<Entry<String, OrderDataBean>> set = orderDataMap.entrySet();
        List<Entry<String, OrderDataBean>> list = new ArrayList<Entry<String, OrderDataBean>>(set);
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(
                    Map.Entry<String, OrderDataBean> orderDataCurrent, Map.Entry<String, OrderDataBean> orderDataNext) {
                return (orderDataNext.getValue().getOrderTotalWithPromotion()).compareTo(orderDataCurrent.getValue().getOrderTotalWithPromotion());
            }
        });
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " ==== " + entry.getValue());
        }
        return 0.0;
    }
}
