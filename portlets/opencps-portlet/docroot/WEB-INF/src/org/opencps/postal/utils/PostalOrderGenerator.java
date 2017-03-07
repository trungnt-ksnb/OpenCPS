/**
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */

/**
 * @author nhanhlt
 * */
package org.opencps.postal.utils;

import org.opencps.postal.NoSuchPostalOrderException;
import org.opencps.postal.model.PostalOrder;
import org.opencps.postal.model.VnPostal;
import org.opencps.postal.service.PostalOrderLocalServiceUtil;
import org.opencps.postal.service.PostalOrderLogLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class PostalOrderGenerator {

	private static Log _log = LogFactoryUtil.getLog(PostalOrder.class);

	public static final String TARGET_DOMAIN = "https://hcconline.vnpost.vn/demo/";
	public static final String API_DIEUTIN = "serviceApi/v1/postDieuTin?";
	public static final String API_DELIVERY = "serviceApi/v1/getDelivery?";
	public static final String API_COLLECT = "serviceApi/v1/getAcceptance?";
	public static final String TOKEN = "token=c45b5eae-23a1-4da2-af66-db834db0e65b";

	public void sendDossierCollectMessage(long dossierId) {

		try {

			PostalOrder postalOrder = null;

			if (dossierId > 0) {

				try {

					postalOrder = PostalOrderLocalServiceUtil.getPostalOrderBy(
							dossierId, PostalKeys.DOSSIER_COLLECT);
				} catch (NoSuchPostalOrderException e) {

				}

				if (Validator.isNotNull(postalOrder)) {

					String transactionCode = StringPool.BLANK;
					VnPostal vnPostal = null;
					PostalUtils postalUtils = new PostalUtils();

					JSONObject jsonObject = JSONFactoryUtil
							.createJSONObject(postalOrder
									.getPostalOrderContent());

					vnPostal = postalUtils.convertJsonToVnPostal(jsonObject);

					// ///////////////////////////////////

					transactionCode = String.valueOf(postalUtils
							._genetatorTransactionCode());

					vnPostal.setSoDonHang(transactionCode);

					postalOrder.setTransactionCode(transactionCode);
					postalOrder.setPostalOrderContent(postalUtils
							.convertVnPostalToJSon(vnPostal).toString());

					PostalOrderLocalServiceUtil.updatePostalOrder(postalOrder);

					// ///////////////////////////////////
					APIRequestUtils apiRequest = new APIRequestUtils();
					jsonObject = apiRequest.callAPI(TARGET_DOMAIN, API_DIEUTIN,
							TOKEN, postalOrder.getPostalOrderContent());

					if (jsonObject.getBoolean("Status") == true) {

						postalOrder
								.setPostalOrderStatus(PostalKeys.DOSSIER_SENDED);

					} else {
						postalOrder.setPostalOrderStatus(PostalKeys.ERROR);
					}
					PostalOrderLocalServiceUtil.updatePostalOrder(postalOrder);

					PostalOrderLogLocalServiceUtil.addLog(
							postalOrder.getPostalOrderId(),
							postalOrder.getPostalOrderStatus(),
							jsonObject.toString());
				}

			}

		} catch (Exception e) {
			_log.error(e);
		}
	}

	public void sendDossierDeliveryMessage(long dossierId) {

		try {

			PostalOrder postalOrder = null;

			if (dossierId > 0) {

				try {

					postalOrder = PostalOrderLocalServiceUtil.getPostalOrderBy(
							dossierId, PostalKeys.DOSSIER_DELIVERY);
				} catch (NoSuchPostalOrderException e) {

				}

				if (Validator.isNotNull(postalOrder)) {

					String transactionCode = StringPool.BLANK;
					VnPostal vnPostal = null;
					PostalUtils postalUtils = new PostalUtils();

					JSONObject jsonObject = JSONFactoryUtil
							.createJSONObject(postalOrder
									.getPostalOrderContent());

					vnPostal = postalUtils.convertJsonToVnPostal(jsonObject);

					// ///////////////////////////////////

					transactionCode = String.valueOf(postalUtils
							._genetatorTransactionCode());

					vnPostal.setSoDonHang(transactionCode);

					postalOrder.setTransactionCode(transactionCode);
					postalOrder.setPostalOrderContent(postalUtils
							.convertVnPostalToJSon(vnPostal).toString());

					PostalOrderLocalServiceUtil.updatePostalOrder(postalOrder);

					// ///////////////////////////////////
					APIRequestUtils apiRequest = new APIRequestUtils();
					jsonObject = apiRequest.callAPI(TARGET_DOMAIN, API_DIEUTIN,
							TOKEN, postalOrder.getPostalOrderContent());

					if (jsonObject.getBoolean("Status") == true) {

						postalOrder
								.setPostalOrderStatus(PostalKeys.DOSSIER_SENDED);

					} else {
						postalOrder.setPostalOrderStatus(PostalKeys.ERROR);
					}
					PostalOrderLocalServiceUtil.updatePostalOrder(postalOrder);

					PostalOrderLogLocalServiceUtil.addLog(
							postalOrder.getPostalOrderId(),
							postalOrder.getPostalOrderStatus(),
							jsonObject.toString());
				}

			}

		} catch (Exception e) {
			_log.error(e);
		}
	}

	
	public static void sendCheckStatusMessage(long postalOrderId,String API_PATH,String postalOrderStatus) {

		try {

			PostalOrder postalOrder = null;

			if (postalOrderId > 0) {

				postalOrder = PostalOrderLocalServiceUtil
						.fetchPostalOrder(postalOrderId);

				if (Validator.isNotNull(postalOrder)) {

					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

					PostalOrderLocalServiceUtil.updatePostalOrder(postalOrder);

					// ///////////////////////////////////
					APIRequestUtils apiRequest = new APIRequestUtils();
					jsonObject = apiRequest.callAPI(TARGET_DOMAIN,
							API_PATH, TOKEN,
							postalOrder.getPostalOrderContent());

					if (jsonObject.toString().length() > 0) {

						postalOrder
								.setPostalOrderStatus(postalOrderStatus);

						PostalOrderLocalServiceUtil
								.updatePostalOrder(postalOrder);

						PostalOrderLogLocalServiceUtil.addLog(
								postalOrder.getPostalOrderId(),
								postalOrder.getPostalOrderStatus(),
								jsonObject.toString());

					}

				}

			}

		} catch (Exception e) {
			_log.error(e);
		}
	}

}