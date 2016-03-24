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

package org.opencps.usermgt.service.impl;

import java.util.Date;
import java.util.List;

import org.opencps.usermgt.NoSuchWorkingUnitException;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.base.WorkingUnitLocalServiceBaseImpl;
import org.opencps.usermgt.util.UserMgtUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the working unit local service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link org.opencps.usermgt.service.WorkingUnitLocalService} interface. <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM. </p>
 *
 * @author khoavd
 * @see org.opencps.usermgt.service.base.WorkingUnitLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.WorkingUnitLocalServiceUtil
 */
public class WorkingUnitLocalServiceImpl
	extends WorkingUnitLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link org.opencps.usermgt.service.WorkingUnitLocalServiceUtil} to access
	 * the working unit local service.
	 */

	public WorkingUnit addWorkingUnit(
		long userId, ServiceContext serviceContext, String name, String enName,
		String govAgencyCode, long parentWorkingUnitId, String address,
		String cityCode, String districtCode, String wardCode, String telNo,
		String faxNo, String email, String website, boolean isEmployer,
		long managerWorkingUnitId)
		throws SystemException, PortalException {

		int sibling = 0;

		Organization org = null;
		if (parentWorkingUnitId == 0) {
			org =
				OrganizationLocalServiceUtil.addOrganization(
					userId,
					OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, name,
					OrganizationConstants.TYPE_REGULAR_ORGANIZATION, 0, 0,
					ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, enName,
					true, serviceContext);

		}
		else {
			org =
				OrganizationLocalServiceUtil.addOrganization(
					userId, parentWorkingUnitId, name,
					OrganizationConstants.TYPE_REGULAR_ORGANIZATION, 0, 0,
					ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, enName,
					true, serviceContext);

		}

		long mappingOrganisationId = org.getOrganizationId();

		Date currentDate = new Date();
		if (getWorkingUnitsByGroupId(
			serviceContext.getScopeGroupId(),
			UserMgtUtil.getWorkingUnitOrderByComparator("sibling", "desc")) == null) {
			sibling = 1;
		}
		else {
			sibling =
				getWorkingUnitsByGroupId(
					serviceContext.getScopeGroupId(),
					UserMgtUtil.getWorkingUnitOrderByComparator(
						"sibling", "desc")).getSibling() + 1;
		}

		long workingUnitId =
			CounterLocalServiceUtil.increment(WorkingUnit.class.getName());

		WorkingUnit workingUnit = workingUnitPersistence.create(workingUnitId);

		String treeIndex =
			getTreeIndex(workingUnitId, parentWorkingUnitId, sibling);

		workingUnit.setCreateDate(currentDate);
		workingUnit.setModifiedDate(currentDate);
		workingUnit.setTreeIndex(treeIndex);
		workingUnit.setUserId(userId);
		workingUnit.setCompanyId(serviceContext.getCompanyId());
		workingUnit.setGroupId(serviceContext.getScopeGroupId());
		workingUnit.setName(name);
		workingUnit.setEnName(enName);
		workingUnit.setGovAgencyCode(govAgencyCode);
		workingUnit.setParentWorkingUnitId(parentWorkingUnitId);
		workingUnit.setAddress(address);
		workingUnit.setCityCode(cityCode);
		workingUnit.setSibling(sibling);
		workingUnit.setDistrictCode(districtCode);
		workingUnit.setWardCode(wardCode);
		workingUnit.setTelNo(telNo);
		workingUnit.setFaxNo(faxNo);
		workingUnit.setEmail(email);
		workingUnit.setWebsite(website);
		workingUnit.setIsEmployer(isEmployer);
		workingUnit.setMappingOrganisationId(mappingOrganisationId);
		workingUnit.setManagerWorkingUnitId(managerWorkingUnitId);
		return workingUnitPersistence.update(workingUnit);

	}

	public WorkingUnit updateWorkingUnit(
		long workingUnitId, long userId, ServiceContext serviceContext,
		String name, String enName, String govAgencyCode,
		long parentWorkingUnitId, String address, String cityCode,
		String districtCode, String wardCode, String telNo, String faxNo,
		String email, String website, boolean isEmployer,
		long managerWorkingUnitId)
		throws SystemException, PortalException {

		WorkingUnit workingUnit =
			workingUnitPersistence.findByPrimaryKey(workingUnitId);

		Organization org = null;
		long mappingOrganisationId = 0;
		if (parentWorkingUnitId == 0) {
			org =
				OrganizationLocalServiceUtil.addOrganization(
					userId,
					OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
					"CQNN", OrganizationConstants.TYPE_REGULAR_ORGANIZATION, 0,
					0, ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, "no",
					true, serviceContext);
			mappingOrganisationId = org.getOrganizationId();

		}
		else {
			mappingOrganisationId = workingUnit.getMappingOrganisationId();
		}

		Date currentDate = new Date();

		workingUnit.setCreateDate(currentDate);
		workingUnit.setModifiedDate(currentDate);
		workingUnit.setUserId(userId);
		workingUnit.setCompanyId(serviceContext.getCompanyId());
		workingUnit.setGroupId(serviceContext.getScopeGroupId());
		workingUnit.setName(name);
		workingUnit.setEnName(enName);
		workingUnit.setGovAgencyCode(govAgencyCode);
		workingUnit.setParentWorkingUnitId(parentWorkingUnitId);
		workingUnit.setAddress(address);
		workingUnit.setCityCode(cityCode);
		workingUnit.setDistrictCode(districtCode);
		workingUnit.setWardCode(wardCode);
		workingUnit.setTelNo(telNo);
		workingUnit.setFaxNo(faxNo);
		workingUnit.setEmail(email);
		workingUnit.setWebsite(website);
		workingUnit.setIsEmployer(isEmployer);
		workingUnit.setMappingOrganisationId(mappingOrganisationId);
		workingUnit.setManagerWorkingUnitId(managerWorkingUnitId);
		return workingUnitPersistence.update(workingUnit);
	}

	public void deleteWorkingUnitByWorkingUnitId(long workingUnitId)
		throws NoSuchWorkingUnitException, SystemException {

		List<Employee> employees =
			employeePersistence.findByWorkingUnitId(workingUnitId);
		List<JobPos> jobPos =
			jobPosPersistence.findByWorkingUnitId(workingUnitId);
		if (employees.isEmpty() && jobPos.isEmpty()) {
			WorkingUnit unit =
				workingUnitPersistence.findByPrimaryKey(workingUnitId);
			try {
				OrganizationLocalServiceUtil.deleteOrganization(unit.getMappingOrganisationId());
			}
			catch (Exception e) {

			}

			workingUnitPersistence.remove(workingUnitId);
		}
	}

	public int countAll()
		throws SystemException {

		return workingUnitPersistence.countAll();
	}

	public List<WorkingUnit> getWorkingUnit(
		int start, int end, OrderByComparator odc)
		throws SystemException {

		return workingUnitPersistence.findAll(start, end, odc);
	}

	protected String getTreeIndex(
		long workingunitId, long parentWorkingUnitId, int sibling)
		throws NoSuchWorkingUnitException, SystemException {

		if (parentWorkingUnitId == 0) {
			return String.valueOf(sibling);
		}
		else if (parentWorkingUnitId > 0) {
			WorkingUnit workingUnit =
				workingUnitPersistence.findByPrimaryKey(parentWorkingUnitId);
			return workingUnit.getTreeIndex() + StringPool.PERIOD +
				String.valueOf(workingunitId);
		}
		else {
			throw new NoSuchWorkingUnitException();
		}
	}

	public List<WorkingUnit> getWorkingUnit(
		long groupId, boolean isEmployee, long parentWorkingUnitId)
		throws SystemException {

		return workingUnitPersistence.findByG_E_P(
			groupId, isEmployee, parentWorkingUnitId);
	}

	public List<WorkingUnit> getWorkingUnit(long groupId, boolean isEmployee)
		throws SystemException {

		return workingUnitPersistence.findByG_E(groupId, isEmployee);
	}

	public List<WorkingUnit> getWorkingUnits(
		long groupId, long parentWorkingUnitId)
		throws SystemException {

		return workingUnitPersistence.findByG_P(groupId, parentWorkingUnitId);
	}

	public void mapMultipleJobPosWorkingUnitToOneWorkingUnit(
		long workingUnitId, long[] jobPosIds)
		throws SystemException {

		workingUnitPersistence.addJobPoses(workingUnitId, jobPosIds);
	}

	public WorkingUnit getWorkingUnitsByGroupId(
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchWorkingUnitException, SystemException {

		return workingUnitPersistence.findByGroupId_First(
			groupId, orderByComparator);
	}

	public List<WorkingUnit> getWorkingUnits(long groupId)
		throws NoSuchWorkingUnitException, SystemException {

		return workingUnitPersistence.findByGroupId(groupId);
	}

	public int getNextSibling(List<WorkingUnit> workingUnits) {

		int MAX = 0;
		for (WorkingUnit workingUnit : workingUnits) {
			if (MAX <= workingUnit.getSibling()) {
				MAX = workingUnit.getSibling();
			}
		}
		return MAX + 1;
	}
}
