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
import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.OrderItemBean;
import org.abhi.promotion.beans.PercentOffOnCategotryPromoBean;
import org.abhi.promotion.beans.PromotionsDataBean;
import org.abhi.promotion.beans.SKUCombinationOrderPromoBean;
import org.abhi.promotion.constants.OrderPromotionConstants;

public class OrderDataReader {

	// method will return the final order total (optimal) after applying the
	public OrderDataBean readOrderData(final String orderDataFilePath) {
		OrderDataBean orderDataBean = new OrderDataBean();

		FileInputStream fileInputStream = null;
		DataInputStream dataInputstream = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(orderDataFilePath);
			dataInputstream = new DataInputStream(fileInputStream);
			bufferedReader = new BufferedReader(new InputStreamReader(
					dataInputstream));

			String lineInFile;
			int fileRows = 0;
			OrderItemBean orderItemBean;
			List<OrderItemBean> orderItems = new ArrayList<OrderItemBean>();

			while (null != (lineInFile = bufferedReader.readLine())) {
				fileRows++; // move to 2nd line as first line is header.
				if (fileRows > 1) {
					String tokens[] = lineInFile
							.split(OrderPromotionConstants.SPACE);
					orderItemBean = new OrderItemBean();
					ItemBean items = new ItemBean();
					items.setSkuId(tokens[0]);

					String itemCurrentPrice = tokens[2];
					if (null != itemCurrentPrice) {
						items.setItemCurrentPrice(Integer
								.parseInt(itemCurrentPrice));
					}

					orderItemBean.setItems(items);
					// Null check for item quantity.
					String itemQuantity = tokens[1];
					if (null != itemQuantity) {
						orderItemBean.setItemQunatity(Integer
								.parseInt(itemQuantity));
						orderItemBean.setOrderItemsTotal(Integer
								.parseInt(itemQuantity)
								* items.getItemCurrentPrice());
						orderItemBean.setSkuId(tokens[0]);
					}
					orderItems.add(orderItemBean);
				}
			}
			orderDataBean.setOrderItems(orderItems);
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
		return orderDataBean;
	}
}
