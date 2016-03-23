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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.base.EmployeeLocalServiceBaseImpl;
import org.opencps.util.PortletConstants;
import org.opencps.util.PortletUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.model.Website;
import com.liferay.portal.service.ContactLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.announcements.model.AnnouncementsDelivery;

/**
 * The implementation of the employee local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.usermgt.service.EmployeeLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author khoavd
 * @author trungnt
 * @see org.opencps.usermgt.service.base.EmployeeLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.EmployeeLocalServiceUtil
 */
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link org.opencps.usermgt.service.EmployeeLocalServiceUtil} to access
	 * the employee local service.
	 */

	public Employee addEmployee(long userId, long workingUnitId,
			String employeeNo, String fullName, int gender, String telNo,
			String mobile, String email, String screenName, int workingStatus,
			long mainJobPosId, long[] jobPosIds, int birthDateDay,
			int birthDateMonth, int birthDateYear, String password,
			String reTypePassword, long[] groupIds, long[] userGroupIds,
			ServiceContext serviceContext)
			throws SystemException, PortalException {

		long employeeId = CounterLocalServiceUtil
				.increment(Employee.class.getName());

		Employee employee = employeePersistence.create(employeeId);

		// Get main JobPos
		JobPos jobPos = jobPosPersistence.findByPrimaryKey(mainJobPosId);

		// Get Working Unit
		WorkingUnit workingUnit = workingUnitPersistence
				.findByPrimaryKey(workingUnitId);

		// Get OrganizationId
		long[] organizationIds = null;

		if (workingUnit != null) {
			organizationIds = new long[]{
					workingUnit.getMappingOrganisationId()};
		}

		long[] roleIds = null;

		if (jobPosIds != null && jobPosIds.length > 0) {

			roleIds = new long[jobPosIds.length];

			for (int job = 0; job < jobPosIds.length; job++) {
				JobPos jobPosTemp = jobPosPersistence
						.findByPrimaryKey(jobPosIds[job]);
				if (jobPosTemp != null) {
					roleIds[job] = jobPosTemp.getMappingRoleId();
				}
			}
		}

		Date now = new Date();

		PortletUtil.SplitName spn = PortletUtil.splitName(fullName);

		Date birthDate = PortletUtil.getDate(birthDateDay, birthDateMonth,
				birthDateYear);

		User user = userService.addUserWithWorkflow(
				serviceContext.getCompanyId(), false, password, reTypePassword,
				false, screenName, email, 0L, StringPool.BLANK,
				LocaleUtil.getDefault(), spn.getFirstName(), spn.getMidName(),
				spn.getLastName(), 0, 0, (gender == 1), birthDateDay,
				birthDateMonth, birthDateYear, jobPos.getTitle(), groupIds,
				organizationIds, roleIds, userGroupIds,
				new ArrayList<Address>(), new ArrayList<EmailAddress>(),
				new ArrayList<Phone>(), new ArrayList<Website>(),
				new ArrayList<AnnouncementsDelivery>(), false, serviceContext);

		if (user != null) {

			employee.setUserId(userId);
			employee.setGroupId(serviceContext.getScopeGroupId());
			employee.setCompanyId(serviceContext.getCompanyId());
			employee.setCreateDate(now);
			employee.setModifiedDate(now);
			employee.setWorkingUnitId(workingUnitId);
			employee.setEmployeeNo(employeeNo);
			employee.setFullName(fullName);
			employee.setGender(gender);
			employee.setBirthdate(birthDate);
			employee.setTelNo(telNo);
			employee.setMobile(mobile);
			employee.setEmail(email);
			employee.setWorkingStatus(workingStatus);
			employee.setMainJobPosId(mainJobPosId);
			employee.setMappingUserId(user.getUserId());
			employeePersistence.setJobPoses(employeeId, jobPosIds);
		}

		return employeePersistence.update(employee);
	}

	public Employee updateEmployee(long employeeId, long userId,
			long workingUnitId, String employeeNo, String fullName, int gender,
			String telNo, String mobile, String email, String screenName,
			int workingStatus, long mainJobPosId, long mappingUserId,
			long[] jobPosIds, boolean isChangePassWord, int birthDateDay,
			int birthDateMonth, int birthDateYear, String oldPassWord,
			String password, String reTypePassword, long[] groupIds,
			long[] organizationIds, long[] roleIds, long[] userGroupIds,
			ServiceContext serviceContext)
			throws SystemException, PortalException {

		Employee employee = employeePersistence.findByPrimaryKey(employeeId);

		User user = userPersistence
				.findByPrimaryKey(employee.getMappingUserId());

		Date now = new Date();

		User mappingUser = userLocalService
				.getUserById(employee.getMappingUserId());

		// Change password
		if (isChangePassWord) {
			long userIdTemp = userLocalService.authenticateForBasic(
					serviceContext.getCompanyId(), email,
					user.getEmailAddress(), oldPassWord);

			if (userIdTemp > 0 && userIdTemp == employee.getMappingUserId()) {
				mappingUser = userLocalService.updatePassword(
						serviceContext.getUserId(), password, reTypePassword,
						false);
			}
		}

		// Change user name
		if (!fullName.equals(employee.getFullName())) {

			PortletUtil.SplitName spn = PortletUtil.splitName(fullName);

			mappingUser.setFirstName(spn.getFirstName());
			mappingUser.setLastName(spn.getLastName());
			mappingUser.setMiddleName(spn.getMidName());
		}

		// update job title
		JobPos jobPos = jobPosPersistence.findByPrimaryKey(mainJobPosId);

		if (!jobPos.getTitle().equals(mappingUser.getJobTitle())) {
			mappingUser.setJobTitle(jobPos.getTitle());
		}

		mappingUser.setScreenName(screenName);

		mappingUser = userLocalService.updateUser(mappingUser);

		// update birth date
		Date birthDate = PortletUtil.getDate(birthDateDay, birthDateMonth,
				birthDateYear);

		Contact contact = ContactLocalServiceUtil
				.getContact(mappingUser.getContactId());

		if (contact != null) {
			contact.setBirthday(birthDate);
			contact = ContactLocalServiceUtil.updateContact(contact);
		}

		// update employee
		employee.setUserId(userId);
		employee.setGroupId(serviceContext.getScopeGroupId());
		employee.setCompanyId(serviceContext.getCompanyId());
		employee.setModifiedDate(now);
		employee.setEmployeeNo(employeeNo);
		employee.setFullName(fullName);
		employee.setGender(gender);
		employee.setBirthdate(birthDate);
		employee.setTelNo(telNo);
		employee.setMobile(mobile);
		employee.setMainJobPosId(mainJobPosId);

		return employeePersistence.update(employee);

	}

	public void updateEmployee(long employeeId, int workingStatus,
			ServiceContext serviceContext)
			throws SystemException, PortalException {

		Employee employee = employeePersistence.findByPrimaryKey(employeeId);

		User mappingUse = userLocalService.getUser(employee.getMappingUserId());

		Date now = new Date();

		if (workingStatus == PortletConstants.WORKING_STATUS_ACTIVATE
				|| workingStatus == PortletConstants.WORKING_STATUS_DEACTIVATE) {

			int status = WorkflowConstants.STATUS_APPROVED;

			if (workingStatus == PortletConstants.WORKING_STATUS_DEACTIVATE) {
				status = WorkflowConstants.STATUS_INACTIVE;
			}

			mappingUse.setStatus(status);

			userLocalService.updateUser(mappingUse);

			employee.setWorkingStatus(workingStatus);

			employee.setUserId(serviceContext.getUserId());
			employee.setGroupId(serviceContext.getScopeGroupId());
			employee.setCompanyId(serviceContext.getCompanyId());
			employee.setModifiedDate(now);

			employeePersistence.update(employee);
		}

	}

	public List<Employee> getEmployees(long groupId, long mainJobPosId)
			throws SystemException {

		return employeePersistence.findByG_W(groupId, mainJobPosId);
	}

	public List<Employee> getEmployees(long groupId, long workingUnitId,
			long mainJobPosId) throws SystemException {

		return employeePersistence.findByG_W_MJP(groupId, workingUnitId,
				mainJobPosId);
	}

}
