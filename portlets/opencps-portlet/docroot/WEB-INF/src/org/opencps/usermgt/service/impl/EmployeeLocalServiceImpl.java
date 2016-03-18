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
import java.util.Locale;

import org.opencps.usermgt.NoSuchEmployeeException;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.base.EmployeeLocalServiceBaseImpl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupConstants;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the employee local service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link org.opencps.usermgt.service.EmployeeLocalService} interface. <p> This
 * is a local service. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM. </p>
 *
 * @author khoavd
 * @see org.opencps.usermgt.service.base.EmployeeLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.EmployeeLocalServiceUtil
 */
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link org.opencps.usermgt.service.EmployeeLocalServiceUtil} to access
	 * the employee local service.
	 */

	public Employee addEmployee(
		long userId, ServiceContext serviceContext, long workingUnitId,
		String employeeNo, String fullName, int gender, Date birthdate,
		String telNo, String mobile, String email, int workingStatus,
		long mainJobPosId)
		throws SystemException, PortalException {

		long mainEmployeeId =
			CounterLocalServiceUtil.increment(Employee.class.getName());
		Employee employee =
			EmployeeLocalServiceUtil.createEmployee(mainEmployeeId);

		Date currentDate = new Date();
		boolean booleanGender = false;
		if (gender == 1) {
			booleanGender = true;
		}

		JobPos jobPos = jobPosPersistence.findByPrimaryKey(mainJobPosId);

		Role role = RoleLocalServiceUtil.fetchRole(jobPos.getMappingRoleId());

		WorkingUnit workingUnit =
			workingUnitPersistence.findByPrimaryKey(workingUnitId);

		long[] collectionGroupId = {
			GroupConstants.DEFAULT_LIVE_GROUP_ID
		};
		long[] collectionOrganisationId = {
			workingUnit.getMappingOrganisationId()
		};
		long[] collectionRoleId = {
			role.getRoleId()
		};
		long[] collectionUserGroupId = {
			UserGroupConstants.DEFAULT_PARENT_USER_GROUP_ID
		};

		User user =
			UserLocalServiceUtil.addUser(
				userId, role.getCompanyId(), false, null, null, false,
				fullName, email, 0, null, Locale.US, fullName, fullName,
				fullName, 0, 0, booleanGender, 0, 0, 0, null,
				collectionGroupId, collectionOrganisationId, collectionRoleId,
				collectionUserGroupId, false, serviceContext);

		employee.setUserId(userId);
		employee.setGroupId(serviceContext.getScopeGroupId());
		employee.setCompanyId(serviceContext.getCompanyId());
		employee.setCreateDate(currentDate);
		employee.setModifiedDate(currentDate);
		employee.setWorkingUnitId(workingUnitId);
		employee.setEmployeeNo(employeeNo);
		employee.setFullName(fullName);
		employee.setGender(gender);
		employee.setBirthdate(birthdate);
		employee.setTelNo(telNo);
		employee.setMobile(mobile);
		employee.setEmail(email);
		employee.setWorkingStatus(workingStatus);
		employee.setMainJobPosId(mainJobPosId);
		employee.setMappingUserId(user.getUserId());

		return employeePersistence.update(employee);
	}

	public Employee updateEmployee(
		long employeeId, long userId, ServiceContext serviceContext,
		long workingUnitId, String employeeNo, String fullName, int gender,
		Date birthdate, String telNo, String mobile, String email,
		int workingStatus, long mainJobPosId)
		throws NoSuchEmployeeException, SystemException, NoSuchUserException {

		Employee employee = employeePersistence.findByPrimaryKey(employeeId);
		User user =
			userPersistence.findByPrimaryKey(employee.getMappingUserId());
		Date currentDate = new Date();

		employee.setUserId(userId);
		employee.setGroupId(serviceContext.getScopeGroupId());
		employee.setCompanyId(serviceContext.getCompanyId());
		employee.setCreateDate(currentDate);
		employee.setModifiedDate(currentDate);
		employee.setWorkingUnitId(workingUnitId);
		employee.setEmployeeNo(employeeNo);
		employee.setFullName(fullName);
		employee.setGender(gender);
		employee.setBirthdate(birthdate);
		employee.setTelNo(telNo);
		employee.setMobile(mobile);
		employee.setEmail(email);
		employee.setWorkingStatus(workingStatus);
		employee.setMainJobPosId(mainJobPosId);
		employee.setMappingUserId(user.getUserId());

		return employeePersistence.update(employee);

	}

	public void deleteEmployeeById(long employeeId)
		throws NoSuchEmployeeException, SystemException {

		employeePersistence.remove(employeeId);
	}

	public int countAll()
		throws SystemException {

		return employeePersistence.countAll();
	}

	public List<Employee> getAll(int start, int end, OrderByComparator odc)
		throws SystemException {

		return employeePersistence.findAll(start, end, odc);
	}

	public List<Employee> getEmployees(long groupId, long mainJobPosId)
		throws SystemException {

		return employeePersistence.findByG_W(groupId, mainJobPosId);
	}

	public List<Employee> getEmployees(
		long groupId, long workingUnitId, long mainJobPosId)
		throws SystemException {

		return employeePersistence.findByG_W_MJP(
			groupId, workingUnitId, mainJobPosId);
	}

	public void mapOneEmployeeToMutilpliJobPos(long employeeId, long[] jobPosIds)
		throws SystemException {

		employeePersistence.addJobPoses(employeeId, jobPosIds);
	}

	public void deleteMapOneEmployeeToMutilpliJobPos(
		long employeeId, long[] jobPosIds)
		throws SystemException {

		employeePersistence.removeJobPoses(employeeId, jobPosIds);
	}
}
