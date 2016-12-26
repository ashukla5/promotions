package org.abhi.promotion.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.abhi.promotion.beans.BuyQtyXInPriceOfQtyYPromoBean;
import org.abhi.promotion.beans.ItemBean;
import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.OrderItemBean;
import org.abhi.promotion.beans.PercentOffOnCategotryPromoBean;
import org.abhi.promotion.beans.PromotionsDataBean;
import org.abhi.promotion.beans.SKUCombinationOrderPromoBean;
import org.abhi.promotion.constants.OrderPromotionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for calculating the combinations of promotions an order item value
 * based on that.
 * 
 * @author ashu10
 *
 */
public class PromotionCalculator {

	private Logger logger = LoggerFactory.getLogger(PromotionCalculator.class);

	public Map<String, Double> calculateOrderForPromoPriorityOrder(
			final String[] promoOrders, final OrderDataBean orderDataBean,
			final PromotionsDataBean promoDataBean) {

		final Map<String, Double> orderMapWithPromo = new ConcurrentHashMap<String, Double>();

		for (final String priorityOrder : promoOrders) {
			switch (priorityOrder) {
			case "123":
				final OrderDataBean orderDataCopy123 = copyOrderObject(orderDataBean);
				applyPromotionOnOrder(priorityOrder, orderDataCopy123,
						promoDataBean);
				orderMapWithPromo.put("123",
						orderDataCopy123.getOrderFinalTotal());
				orderDataCopy123.setOrderFinalTotal(0.0);
				break;
			case "132":
				final OrderDataBean orderDataCopy132 = copyOrderObject(orderDataBean);
				applyPromotionOnOrder(priorityOrder, orderDataCopy132,
						promoDataBean);
				orderMapWithPromo.put("132",
						orderDataCopy132.getOrderFinalTotal());
				orderDataCopy132.setOrderFinalTotal(0.0);
				break;
			case "213":
				final OrderDataBean orderDataCopy213 = copyOrderObject(orderDataBean);
				applyPromotionOnOrder(priorityOrder, orderDataCopy213,
						promoDataBean);
				orderMapWithPromo.put("213",
						orderDataCopy213.getOrderFinalTotal());
				orderDataCopy213.setOrderFinalTotal(0.0);
				break;
			case "231":
				final OrderDataBean orderDataCopy231 = copyOrderObject(orderDataBean);
				applyPromotionOnOrder(priorityOrder, orderDataCopy231,
						promoDataBean);
				orderMapWithPromo.put("231",
						orderDataCopy231.getOrderFinalTotal());
				orderDataCopy231.setOrderFinalTotal(0.0);
				break;
			case "321":
				final OrderDataBean orderDataCopy321 = copyOrderObject(orderDataBean);
				applyPromotionOnOrder(priorityOrder, orderDataCopy321,
						promoDataBean);
				orderMapWithPromo.put("321",
						orderDataCopy321.getOrderFinalTotal());
				orderDataCopy321.setOrderFinalTotal(0.0);
				break;
			case "312":
				final OrderDataBean orderDataCopy312 = copyOrderObject(orderDataBean);
				applyPromotionOnOrder(priorityOrder, orderDataCopy312,
						promoDataBean);
				orderMapWithPromo.put("312",
						orderDataCopy312.getOrderFinalTotal());
				orderDataCopy312.setOrderFinalTotal(0.0);
				break;
			}
		}
		return orderMapWithPromo;
	}

	private OrderDataBean copyOrderObject(final OrderDataBean orderDataBean) {
		OrderDataBean orderDataCopy = new OrderDataBean();
		// orderDataCopy.setOrderFinalTotal(orderDataBean.getOrderFinalTotal());
		orderDataCopy.setOrderId(orderDataBean.getOrderId());

		List<OrderItemBean> orderItems = new ArrayList<OrderItemBean>();

		for (OrderItemBean itemBean : orderDataBean.getOrderItems()) {

			ItemBean item = new ItemBean();
			item.setItemCurrentPrice(itemBean.getItems().getItemCurrentPrice());
			item.setItemOfferPrice(itemBean.getItems().getItemOfferPrice());
			item.setPercentOff(itemBean.getItems().getPercentOff());
			item.setSkuId(itemBean.getItems().getSkuId());

			OrderItemBean orderItemBean = new OrderItemBean();
			orderItemBean.setItems(item);
			// orderItemBean.setItemOfferQuantity(itemBean.getItemOfferQuantity());
			orderItemBean.setItemQunatity(itemBean.getItemQunatity());
			// orderItemBean.setOfferItemsTotal(itemBean.getOfferItemsTotal());
			// orderItemBean.setOrderItemsTotal(itemBean.getOrderItemsTotal());
			orderItemBean.setSkuId(itemBean.getSkuId());
			orderItems.add(orderItemBean);

		}

		orderDataCopy.setOrderItems(orderItems);
		return orderDataCopy;
	}

	/**
	 * method to apply promotion based on promotion priority.
	 * 
	 * @param promoPriorityOder
	 * @param orderDataBean
	 * @param promoBean
	 */
	private void applyPromotionOnOrder(final String promoPriorityOder,
			final OrderDataBean orderDataBean,
			final PromotionsDataBean promoBean) {
		final char[] promos = promoPriorityOder.toCharArray();
		logger.debug("promo order" + promos.toString() + "promo order length"
				+ promos.length);

		setRemainingItemsForPromo(orderDataBean, promoBean);

		BuyQtyXInPriceOfQtyPromoCalculator buyXInYCalc = null;
		PercentOffPromoCalculator percentOffCalc = null;
		SKUComboOrderPromoCalculator comboPromoCalc = null;
		for (int i = 0; i < promos.length; i++) {
			final int promotionPriority = i + 1;
			if (promos[i] == '1') {

				buyXInYCalc = new BuyQtyXInPriceOfQtyPromoCalculator();
				final BuyQtyXInPriceOfQtyYPromoBean buyXInYBean = promoBean
						.getBuyXInYPromo();
				buyXInYBean.setPromotionPriority(promotionPriority);
				promoBean.setBuyXInYPromo(buyXInYBean);
				buyXInYCalc.applyBuyXInPriceOfYPromo(orderDataBean, promoBean);
			}
			if (promos[i] == '2') {
				percentOffCalc = new PercentOffPromoCalculator();

				final PercentOffOnCategotryPromoBean percentOffBean = promoBean
						.getPercentOffPromo();
				percentOffBean.setPromotionPriority(promotionPriority);
				promoBean.setPercentOffPromo(percentOffBean);
				percentOffCalc.applyPercentageOffOnCategoryPromo(orderDataBean,
						promoBean);
			}
			if (promos[i] == '3') {
				comboPromoCalc = new SKUComboOrderPromoCalculator();
				final SKUCombinationOrderPromoBean skUComboBean = promoBean
						.getSkuComboPromo();
				skUComboBean.setPromotionPriority(promotionPriority);
				promoBean.setSkuComboPromo(skUComboBean);
				if (null != orderDataBean.getOrderItems()
						&& 1 < orderDataBean.getOrderItems().size()) {
					comboPromoCalc.applyCategoryComboPromotion(orderDataBean,
							promoBean);
				}
			}
		}
	}

	/**
	 * set Remaining orderitems same as orderItemsQuantity at before applying
	 * the promotions.
	 * 
	 * @param orderBean
	 * @param promoBean
	 */
	private void setRemainingItemsForPromo(OrderDataBean orderBean,
			PromotionsDataBean promoBean) {

		for (final OrderItemBean orderItem : orderBean.getOrderItems()) {
			orderItem.setRemainingItems(orderItem.getItemQunatity());
		}
	}

}
