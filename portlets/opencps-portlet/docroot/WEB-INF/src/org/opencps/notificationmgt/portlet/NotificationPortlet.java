/**
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */

package org.opencps.notificationmgt.portlet;

import java.io.IOException;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.notificationmgt.model.NotificationConfig;
import org.opencps.notificationmgt.search.NotificationConfigDisplayTerms;
import org.opencps.notificationmgt.service.NotificationConfigLocalServiceUtil;
import org.opencps.util.WebKeys;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;
/**
 * @author nhanhoang
 */
public class NotificationPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(NotificationPortlet.class);
	
	public void updateNotificationConfig(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException {

		long notificationConfigId = ParamUtil.getLong(actionRequest,
				NotificationConfigDisplayTerms.NOTICE_CONFIG_ID);
		String dossierCurrentStatus = ParamUtil.getString(actionRequest,
				NotificationConfigDisplayTerms.DOSSIER_CURRENT_STATUS);
		String dossierNextStatus = ParamUtil.getString(actionRequest,
				NotificationConfigDisplayTerms.DOSSIER_NEXT_STATUS);
		boolean isSendNotification = ParamUtil.getBoolean(actionRequest,
				NotificationConfigDisplayTerms.IS_SEND_NOTIFICATION);

		String returnURL = ParamUtil.getString(actionRequest,
				WebKeys.RETURN_URL);
		String currentURL = ParamUtil.getString(actionRequest,
				WebKeys.CURRENT_URL);
		String backURL = ParamUtil.getString(actionRequest, WebKeys.BACK_URL);

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		NotificationConfig notificationConfig = null;

		try {

			try {
				notificationConfig = NotificationConfigLocalServiceUtil
						.fetchNotificationConfig(notificationConfigId);
			} catch (SystemException e) {

			}

			if (Validator.isNull(notificationConfig)) {

				notificationConfig = NotificationConfigLocalServiceUtil
						.createNotificationConfig(CounterLocalServiceUtil
								.increment(NotificationConfig.class.getName()));

				notificationConfig.setCompanyId(themeDisplay.getCompanyId());
				notificationConfig.setGroupId(themeDisplay.getScopeGroupId());
				notificationConfig.setUserId(themeDisplay.getUserId());
				notificationConfig.setCreateDate(new Date());
				notificationConfig.setModifiedDate(new Date());

				notificationConfig
						.setDossierCurrentStatus(dossierCurrentStatus);
				notificationConfig.setDossierNextStatus(dossierNextStatus);
				notificationConfig.setIsSendNotification(isSendNotification);

				NotificationConfigLocalServiceUtil
						.addNotificationConfig(notificationConfig);

			} else {

				notificationConfig.setModifiedDate(new Date());

				notificationConfig
						.setDossierCurrentStatus(dossierCurrentStatus);
				notificationConfig.setDossierNextStatus(dossierNextStatus);
				notificationConfig.setIsSendNotification(isSendNotification);

				NotificationConfigLocalServiceUtil
						.updateNotificationConfig(notificationConfig);

			}
			
			SessionMessages.add(actionRequest, "update-notification-config-success");
		} catch (Exception e) {
			_log.error(e);
			SessionMessages.add(actionRequest, "update-notification-config-error");
		}

	}

}
