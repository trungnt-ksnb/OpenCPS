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

package org.opencps.notificationmgt.service.impl;

import java.util.List;

import org.opencps.notificationmgt.NoSuchNotificationConfigException;
import org.opencps.notificationmgt.model.NotificationConfig;
import org.opencps.notificationmgt.service.base.NotificationConfigLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the notification config local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.notificationmgt.service.NotificationConfigLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author nhanhoang
 * @see org.opencps.notificationmgt.service.base.NotificationConfigLocalServiceBaseImpl
 * @see org.opencps.notificationmgt.service.NotificationConfigLocalServiceUtil
 */
public class NotificationConfigLocalServiceImpl
	extends NotificationConfigLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.opencps.notificationmgt.service.NotificationConfigLocalServiceUtil} to access the notification config local service.
	 */
	
	public NotificationConfig findByCNS(String dossierCurrentStatus,
			String dossierNextStatus, boolean isSendNotification)
			throws SystemException, NoSuchNotificationConfigException {

		return notificationConfigPersistence.findByC_N_S(dossierCurrentStatus,
				dossierNextStatus, isSendNotification);

	}

	public List<NotificationConfig> findByDossierCurrentStatus(
			String dossierCurrentStatus, boolean isSendNotification)
			throws SystemException {

		return notificationConfigPersistence.findByC_S(dossierCurrentStatus,
				isSendNotification);

	}

	public List<NotificationConfig> findByDossierNextStatus(
			String dossierNextStatus, boolean isSendNotification)
			throws SystemException {

		return notificationConfigPersistence.findByC_S(dossierNextStatus,
				isSendNotification);

	}
	
	public NotificationConfig getByDossierNextStatus(String dossierNextStatus,
			boolean isSendNotification) throws SystemException {

		try {
			return notificationConfigPersistence.findByDossierNextStatus(
					dossierNextStatus, isSendNotification);
		} catch (NoSuchNotificationConfigException e) {
			
		}

		return null;

	}
}