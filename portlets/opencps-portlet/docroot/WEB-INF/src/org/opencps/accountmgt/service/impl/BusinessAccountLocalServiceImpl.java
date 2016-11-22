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

import org.opencps.accountmgt.service.base.BusinessAccountLocalServiceBaseImpl;

/**
 * The implementation of the business account local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.accountmgt.service.BusinessAccountLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavd
 * @see org.opencps.accountmgt.service.base.BusinessAccountLocalServiceBaseImpl
 * @see org.opencps.accountmgt.service.BusinessAccountLocalServiceUtil
 */
public class BusinessAccountLocalServiceImpl
	extends BusinessAccountLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.opencps.accountmgt.service.BusinessAccountLocalServiceUtil} to access the business account local service.
	 */
}