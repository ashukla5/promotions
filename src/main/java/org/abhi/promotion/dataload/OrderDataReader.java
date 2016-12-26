package org.abhi.promotion.dataload;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.abhi.promotion.beans.ItemBean;
import org.abhi.promotion.beans.OrderDataBean;
import org.abhi.promotion.beans.OrderItemBean;
import org.abhi.promotion.constants.OrderPromotionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to read the order data file.
 * 
 * @author ashu10
 *
 */
public class OrderDataReader {

	private Logger logger = LoggerFactory.getLogger(OrderDataReader.class);

	/**
	 * Method to read the order data from file and prepare orderData bean.
	 * 
	 * @param orderDataBean
	 * @param orderDataFilePath
	 * @return orderDataBean
	 */
	public OrderDataBean readOrderData(final OrderDataBean orderDataBean,
			final String orderDataFilePath) {

		logger.info("Method start: readOrderData");

		FileInputStream fileInputStream = null;
		DataInputStream dataInputstream = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(orderDataFilePath);
			logger.debug(" order data file path " + orderDataFilePath);
			dataInputstream = new DataInputStream(fileInputStream);
			bufferedReader = new BufferedReader(new InputStreamReader(
					dataInputstream));

			String lineInFile;
			int fileRows = 0;
			OrderItemBean orderItemBean;
			final List<OrderItemBean> orderItems = new ArrayList<OrderItemBean>();

			while (null != (lineInFile = bufferedReader.readLine())) {
				fileRows++; // move to 2nd line as first line is header.
				if (fileRows > 1) {
					String tokens[] = lineInFile
							.split(OrderPromotionConstants.SPACE);
					orderItemBean = new OrderItemBean();
					ItemBean items = new ItemBean();

					final String skuId = tokens[0];
					items.setSkuId(skuId);

					final String itemCurrentPrice = tokens[2];
					if (null != itemCurrentPrice) {
						items.setItemCurrentPrice(Integer
								.parseInt(itemCurrentPrice));
					}

					orderItemBean.setItems(items);
					final String itemQuantity = tokens[1];

					if (null != itemQuantity) {
						orderItemBean.setItemQunatity(Integer
								.parseInt(itemQuantity));
						orderItemBean.setOrderItemsTotal(Integer
								.parseInt(itemQuantity)
								* items.getItemCurrentPrice());
						orderItemBean.setSkuId(skuId);
					}
					orderItems.add(orderItemBean);
				}
			}

			orderDataBean.setOrderItems(orderItems);
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
		logger.info("Method end: readOrderData");
		return orderDataBean;
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
}
