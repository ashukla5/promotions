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

public class PromotionDataReader {

	public PromotionsDataBean readPromotionData(
			final String promotionDataFilePath) {
		PromotionsDataBean promotionsDataBean = new PromotionsDataBean();

		FileInputStream fileInputStream = null;
		DataInputStream dataInputstream = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(promotionDataFilePath);
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
				String tokens[] = lineInFile
						.split(OrderPromotionConstants.SPACE);
				BuyQtyXInPriceOfQtyYPromoBean buyXInY = null;
				PercentOffOnCategotryPromoBean percentOffOnCat = null;
				SKUCombinationOrderPromoBean skuComboOrderPromo = null;
				List<OrderItemBean> orderitems = null;

				// read BuyQtyXInPriceOfQtyY Promotion data from file
				if (fileRows % 2 == 0
						&& tokens.length == 5
						&& (null != tokens[4])
						&& (OrderPromotionConstants.PROMOTION_DELIMITER
								.equalsIgnoreCase(tokens[4]))) {

					buyXInY = new BuyQtyXInPriceOfQtyYPromoBean();
					buyXInY.setPromotionId(tokens[0]);
					buyXInY.setSkuId(tokens[1]);
					buyXInY.setOfferQuantity(Integer.parseInt(tokens[2]));
					buyXInY.setQualifiyingQuantity(Integer.parseInt(tokens[3]));
					promotionsDataBean.setBuyXInYPromo(buyXInY);
				}
				// This condition is as per fixed data format of promotion data
				// hence expecting that for percentOff promotion length cannot
				// be more than 4.
				else if (fileRows % 2 == 0
						&& tokens.length == 4
						&& (null != tokens[3])
						&& (OrderPromotionConstants.PROMOTION_DELIMITER
								.equalsIgnoreCase(tokens[3]))) {
					percentOffOnCat = new PercentOffOnCategotryPromoBean();
					percentOffOnCat.setPromotionId(tokens[0]);
					percentOffOnCat.setSkuId(tokens[1]);
					if (null != tokens[2]) {
						percentOffOnCat.setPercentOff(Double
								.parseDouble(tokens[2]));
					}
					promotionsDataBean.setPercentOffPromo(percentOffOnCat);
				} else if (fileRows % 2 == 0
						&& tokens.length == 7
						&& (null != tokens[6])
						&& (OrderPromotionConstants.PROMOTION_DELIMITER
								.equalsIgnoreCase(tokens[6]))) {
					orderitems = new ArrayList<OrderItemBean>();
					skuComboOrderPromo = new SKUCombinationOrderPromoBean();
					skuComboOrderPromo.setPromotionId(tokens[0]);

					// Prepare Order item for skucombo promotion
					OrderItemBean orderItem = new OrderItemBean();
					ItemBean itemBean = new ItemBean();
					itemBean.setSkuId(tokens[1]);
					orderItem.setItemQunatity(Integer.parseInt(tokens[2]));
					orderItem.setItems(itemBean);
					orderitems.add(orderItem);

					orderItem = new OrderItemBean();
					itemBean = new ItemBean();
					itemBean.setSkuId(tokens[3]);
					orderItem.setItemQunatity(Integer.parseInt(tokens[4]));
					orderItem.setItems(itemBean);
					orderitems.add(orderItem);

					skuComboOrderPromo.setOrderitems(orderitems);
					skuComboOrderPromo.setQualifyingOrderAmount(Double
							.parseDouble(tokens[5]));

					promotionsDataBean
							.setSkuCombinationPromo(skuComboOrderPromo);
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != fileInputStream) {
					fileInputStream.close();
				}
				if (null != dataInputstream) {
					dataInputstream.close();
				}
				if (null != bufferedReader) {
					bufferedReader.close();
				}
			} catch (IOException expception) {
				expception.printStackTrace();
			}
		}

		System.out.println(" promotionDataBean object"+promotionsDataBean);
		return promotionsDataBean;
	}
}
