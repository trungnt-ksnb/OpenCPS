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

package org.opencps.accountmgt.service.impl;

import java.util.Date;

import org.opencps.accountmgt.NoSuchBusinessException;
import org.opencps.accountmgt.model.Business;
import org.opencps.accountmgt.service.base.BusinessServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.util.StringPool;

/**
 * The implementation of the business remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.accountmgt.service.BusinessService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author khoavd
 * @see org.opencps.accountmgt.service.base.BusinessServiceBaseImpl
 * @see org.opencps.accountmgt.service.BusinessServiceUtil
 */
public class BusinessServiceImpl extends BusinessServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * org.opencps.accountmgt.service.BusinessServiceUtil} to access the
	 * business remote service.
	 */

	@JSONWebService(value = "updateBusiessProfile", method = "POST")
	public boolean updateBusiessProfile(String businessEmail, String fullName,
			String enName, String shortName, String businessType,
			String idNumber, String address, String cityCode,
			String districtCode, String wardCode, String cityName,
			String districtName, String wardName, String telNo,
			String representativeName, String representativeRole,
			Date dateOfIdNumber) {
		boolean result = false;

		Business business = null;
		try {
			business = businessPersistence.findByIdNumber(idNumber);
		} catch (NoSuchBusinessException | SystemException e) {
			e.printStackTrace();
			return result;
		}

		try {
			businessLocalService
					.updateBusiness(business.getBusinessId(), fullName, enName,
							shortName, businessType, idNumber, address,
							cityCode, districtCode, wardCode, cityName,
							districtName, wardName, telNo, representativeName,
							representativeRole, new String[] {}, false,
							StringPool.BLANK, StringPool.BLANK, 0, null,
							dateOfIdNumber);
		} catch (PortalException | SystemException e) {
			e.printStackTrace();
			return result;
		}

		result = true;
		return result;
	}
}