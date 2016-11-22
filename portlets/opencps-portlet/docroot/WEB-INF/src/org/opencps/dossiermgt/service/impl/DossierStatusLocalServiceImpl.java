/*******************************************************************************
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/

package org.opencps.dossiermgt.service.impl;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.NoSuchDossierStatusException;
import org.opencps.dossiermgt.model.DossierStatus;
import org.opencps.dossiermgt.service.base.DossierStatusLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the dossier status local service. <p> All custom
 * service methods should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierStatusLocalService} interface.
 * <p> This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM. </p>
 *
 * @author trungnt
 * @see org.opencps.dossiermgt.service.base.DossierStatusLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierStatusLocalServiceUtil
 */
public class DossierStatusLocalServiceImpl
	extends DossierStatusLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link org.opencps.dossiermgt.service.DossierStatusLocalServiceUtil} to
	 * access the dossier status local service.
	 */

	/**
	 * @param dossierId
	 * @param fileGroupId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public DossierStatus getStatus(long dossierId, long fileGroupId)
		throws PortalException, SystemException {

		return dossierStatusPersistence
			.fetchByD_FG(dossierId, fileGroupId);
	}

	/**
	 * @param userId
	 * @param dossierId
	 * @param fileGroupId
	 * @param status
	 * @param actionInfo
	 * @param messageInfo
	 * @param updateDatetime
	 * @param syncStatus
	 * @param serviceContext
	 * @return
	 * @throws SystemException
	 */
	public DossierStatus addDossierStatus(
		long userId, long dossierId, long fileGroupId, String status,
		String actionInfo, String messageInfo, Date updateDatetime,
		int syncStatus, ServiceContext serviceContext)
		throws SystemException {

		long dossierStatusId = counterLocalService
			.increment(DossierStatus.class
				.getName());

		DossierStatus dossierStatus = dossierStatusPersistence
			.create(dossierStatusId);

		Date now = new Date();

		dossierStatus
			.setUserId(userId);
		dossierStatus
			.setGroupId(serviceContext
				.getScopeGroupId());
		dossierStatus
			.setCompanyId(serviceContext
				.getCompanyId());
		dossierStatus
			.setCreateDate(now);
		dossierStatus
			.setModifiedDate(now);

		dossierStatus
			.setDossierId(dossierId);
		dossierStatus
			.setFileGroupId(fileGroupId);
		dossierStatus
			.setDossierStatus(status);
		dossierStatus
			.setActionInfo(actionInfo);
		dossierStatus
			.setMessageInfo(messageInfo);
		dossierStatus
			.setUpdateDatetime(updateDatetime);
		dossierStatus
			.setSyncStatus(syncStatus);

		return dossierStatusPersistence
			.update(dossierStatus);
	}

	/**
	 * @param dossierId
	 * @return
	 * @throws NoSuchDossierStatusException
	 * @throws SystemException
	 */
	public DossierStatus getDossierStatus(long dossierId)
		throws NoSuchDossierStatusException, SystemException {

		return dossierStatusPersistence
			.findByDossierId(dossierId);
	}

	/**
	 * @param dossierStatusId
	 * @param userId
	 * @param dossierId
	 * @param fileGroupId
	 * @param status
	 * @param actionInfo
	 * @param messageInfo
	 * @param updateDatetime
	 * @param syncStatus
	 * @param serviceContext
	 * @return
	 * @throws SystemException
	 * @throws NoSuchDossierStatusException
	 */
	public DossierStatus updateDossierStatus(
		long dossierStatusId, long userId, long dossierId, long fileGroupId,
		String status, String actionInfo, String messageInfo,
		Date updateDatetime, int syncStatus, ServiceContext serviceContext)
		throws SystemException, NoSuchDossierStatusException {

		DossierStatus dossierStatus = dossierStatusPersistence
			.findByPrimaryKey(dossierStatusId);

		Date now = new Date();

		dossierStatus
			.setUserId(userId);
		dossierStatus
			.setModifiedDate(now);
		dossierStatus
			.setDossierStatus(status);
		dossierStatus
			.setActionInfo(actionInfo);
		dossierStatus
			.setMessageInfo(messageInfo);
		dossierStatus
			.setUpdateDatetime(updateDatetime);
		dossierStatus
			.setSyncStatus(syncStatus);

		return dossierStatusPersistence
			.update(dossierStatus);
	}
}
