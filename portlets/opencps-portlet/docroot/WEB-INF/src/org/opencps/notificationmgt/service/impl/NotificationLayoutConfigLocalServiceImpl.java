/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.notificationmgt.service.impl;

import org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException;
import org.opencps.notificationmgt.model.NotificationLayoutConfig;
import org.opencps.notificationmgt.service.base.NotificationLayoutConfigLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the notification layout config local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.notificationmgt.service.NotificationLayoutConfigLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author nhanhoang
 * @see org.opencps.notificationmgt.service.base.NotificationLayoutConfigLocalServiceBaseImpl
 * @see org.opencps.notificationmgt.service.NotificationLayoutConfigLocalServiceUtil
 */
public class NotificationLayoutConfigLocalServiceImpl
	extends NotificationLayoutConfigLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.opencps.notificationmgt.service.NotificationLayoutConfigLocalServiceUtil} to access the notification layout config local service.
	 */
	
	public NotificationLayoutConfig getConfigBy(long notiConfigId, boolean isEmployee,boolean isPayment) throws SystemException{
		
		try {
			return notificationLayoutConfigPersistence.findByNotificationConfigId(notiConfigId, isEmployee, isPayment);
		} catch (NoSuchNotificationLayoutConfigException e) {
			
		}
		return null;
	}
}