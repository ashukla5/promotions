package org.abhi.promotion.dataload;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.abhi.promotion.beans.BuyQtyXInPriceOfQtyYPromoBean;
import org.abhi.promotion.beans.ItemBean;
import org.abhi.promotion.beans.OrderItemBean;
import org.abhi.promotion.beans.PercentOffOnCategotryPromoBean;
import org.abhi.promotion.beans.PromotionsDataBean;
import org.abhi.promotion.beans.SKUCombinationOrderPromoBean;
import org.abhi.promotion.constants.OrderPromotionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IO class to read promotion data from file.
 * 
 * @author ashu10
 *
 */
public class PromotionDataReader {

	private Logger logger = LoggerFactory.getLogger(PromotionDataReader.class);

	/**
	 * Method to read the promotion data file and preparing respective
	 * promotions beans.
	 * 
	 * @param promoFilePath
	 * @return
	 */
	public PromotionsDataBean readPromotionData(final String promoFilePath) {

		logger.info("Method start: readPromotionData");

		final PromotionsDataBean promoDataBean = new PromotionsDataBean();

		FileInputStream fileInputStream = null;
		DataInputStream dataInputstream = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(promoFilePath);
			dataInputstream = new DataInputStream(fileInputStream);
			bufferedReader = new BufferedReader(new InputStreamReader(
					dataInputstream));

			String lineInFile;
			int fileRows = 0;

			// Promotion data format is fixed hence reading data based on that.
			// If there is no data for some promotion then there will null value
			// against it.
			while (null != (lineInFile = bufferedReader.readLine())) {
				fileRows++;
				final String tokens[] = lineInFile
						.split(OrderPromotionConstants.SPACE);
				// read BuyQtyXInPriceOfQtyY Promotion data from file
				if (fileRows % 2 == 0
						&& tokens.length == 5
						&& null != tokens[4]
						&& OrderPromotionConstants.DELIMITER
								.equalsIgnoreCase(tokens[4])) {

					prepareBuyXInYPromo(promoDataBean, tokens);
				}
				// This condition is as per fixed data format of promotion data
				// hence expecting that for percentOff promotion length cannot
				// be more than 4.
				else if (fileRows % 2 == 0
						&& tokens.length == 4
						&& null != tokens[3]
						&& OrderPromotionConstants.DELIMITER
								.equalsIgnoreCase(tokens[3])) {

					preparePercentOffPromo(promoDataBean, tokens);
				} else if (fileRows % 2 == 0
						&& tokens.length == 7
						&& null != tokens[6]
						&& OrderPromotionConstants.DELIMITER
								.equalsIgnoreCase(tokens[6])) {
					prepareSKUComboPromo(promoDataBean, tokens);
				}

			}
		} catch (FileNotFoundException fileNotFound) {
			// TODO Auto-generated catch block
			logger.error(fileNotFound.getMessage());
		} catch (IOException ioExcep) {
			// TODO Auto-generated catch block
			logger.error(ioExcep.getMessage());
		} finally {
			try {
				closeStreams(fileInputStream, dataInputstream, bufferedReader);
			} catch (IOException ioExcep) {
				logger.error(ioExcep.getMessage());
			}
		}

		logger.info("Method end: readPromotionData");
		// System.out.println("getBuyXInYPromo Promotion data is"
		// + promoDataBean.getBuyXInYPromo().getSkuId());
		// System.out.println("getPercentOffPromo Promotion data is"
		// + promoDataBean.getPercentOffPromo().getSkuId());
		// System.out.println("getSkuComboPromo Promotion first sku is"
		// + promoDataBean.getSkuComboPromo().getOrderitems().get(0).getItems()
		// .getSkuId()
		// + "second sku is"
		// + promoDataBean.getSkuComboPromo().getOrderitems().get(1).getItems()
		// .getSkuId());
		return promoDataBean;
	}

	private void closeStreams(final FileInputStream fileInputStream,
			final DataInputStream dataInputstream,
			final BufferedReader bufferedReader) throws IOException {
		if (null != fileInputStream) {
			fileInputStream.close();
		}
		if (null != dataInputstream) {
			dataInputstream.close();
		}
		if (null != bufferedReader) {
			bufferedReader.close();
		}
	}

	/**
	 * Method to take data from file and put in the respective promotion object
	 * 
	 * @param promoDataBean
	 * @param tokens
	 */
	private void prepareBuyXInYPromo(final PromotionsDataBean promoDataBean,
			final String[] tokens) {
		final BuyQtyXInPriceOfQtyYPromoBean buyXInY = new BuyQtyXInPriceOfQtyYPromoBean();
		final String promoId = tokens[0];
		final String skuId = tokens[1];
		final String offerQuantity = tokens[2];
		final String currentQuantity = tokens[3];
		buyXInY.setPromotionId(promoId);
		buyXInY.setSkuId(skuId);
		buyXInY.setOfferQuantity(Integer.parseInt(offerQuantity));
		buyXInY.setCurrentQuantity(Integer.parseInt(currentQuantity));
		promoDataBean.setBuyXInYPromo(buyXInY);
	}

	/**
	 * Method to take data from file and put in the respective promotion object
	 * 
	 * @param promoDataBean
	 * @param tokens
	 */
	private void prepareSKUComboPromo(final PromotionsDataBean promoDataBean,
			final String[] tokens) {
		List<OrderItemBean> orderitems;
		orderitems = new ArrayList<OrderItemBean>();
		final SKUCombinationOrderPromoBean skuComboPromo = new SKUCombinationOrderPromoBean();
		final String promotionId = tokens[0];
		final String skuId = tokens[1];
		final String itemQuantity = tokens[2];

		skuComboPromo.setPromotionId(promotionId);

		// Prepare Order item for skucombo promotion
		OrderItemBean orderItem = new OrderItemBean();
		ItemBean itemBean = new ItemBean();
		itemBean.setSkuId(skuId);
		orderItem.setItemQunatity(Integer.parseInt(itemQuantity));
		orderItem.setItems(itemBean);
		orderitems.add(orderItem);

		orderItem = new OrderItemBean();
		itemBean = new ItemBean();
		final String nextSKUId = tokens[3];
		final String nextItemQty = tokens[4];

		itemBean.setSkuId(nextSKUId);
		orderItem.setItemQunatity(Integer.parseInt(nextItemQty));
		orderItem.setItems(itemBean);
		orderitems.add(orderItem);

		

		skuComboPromo.setOrderitems(orderitems);
		final String offerOrderAmt = tokens[5];
		skuComboPromo.setOfferOrderAmount(Double.parseDouble(offerOrderAmt));
		
		// System.out.println("order items before setting in sku promo"
		// + orderitems.get(0).getItems().getSkuId() + "second sku is "
		// + orderitems.get(1).getItems().getSkuId() + "offer total"+
		// skuComboPromo.getOfferOrderAmount());

		promoDataBean.setSkuComboPromo(skuComboPromo);
	}

	/**
	 * Method to take data from file and put in the respective promotion object
	 * 
	 * @param promoDataBean
	 * @param tokens
	 */
	private void preparePercentOffPromo(final PromotionsDataBean promoDataBean,
			final String[] tokens) {
		final PercentOffOnCategotryPromoBean percentOffOnCat = new PercentOffOnCategotryPromoBean();
		final String promoId = tokens[0];
		final String skuId = tokens[1];
		final String percentOff = tokens[2];
		percentOffOnCat.setPromotionId(promoId);
		percentOffOnCat.setSkuId(skuId);
		if (null != percentOff) {
			percentOffOnCat.setPercentOff(Double.parseDouble(percentOff));
		}
		promoDataBean.setPercentOffPromo(percentOffOnCat);
	}
}
