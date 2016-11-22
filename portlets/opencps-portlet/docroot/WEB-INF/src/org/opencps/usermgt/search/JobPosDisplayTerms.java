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

package org.opencps.usermgt.search;

import java.util.Date;

import javax.portlet.PortletRequest;

import org.opencps.util.DateTimeUtil;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

public class JobPosDisplayTerms extends DisplayTerms {

	public static final String ID_JOBPOS = "jobPosId";
	public static final String GROUPID_JOBPOS = "groupId";
	public static final String COMPANYID_JOBPOS = "companyId";
	public static final String USERID_JOBPOS = "userId";
	public static final String WORKINGUNITID_JOBPOS = "workingUnitId";
	public static final String DIRECTWORKINGUNITID_JOBPOS =
		"directWorkingUnitId";
	public static final String MAPPINGROLEID_JOBPOS = "mappingRoleId";
	public static final String LEADER_JOBPOS = "leader";
	public static final String TITLE_JOBPOS = "title";
	public static final String DESCRIPTION_JOBPOS = "description";
	public static final String CREATEDATE_JOBPOS = "createDate";
	public static final String MODIFIEDDATE_JOBPOS = "modifiedDate";

	public JobPosDisplayTerms(PortletRequest request) {

		super(request);

		jobPosId = ParamUtil.getLong(request, ID_JOBPOS);
		companyId = ParamUtil.getLong(request, COMPANYID_JOBPOS);
		userId = ParamUtil.getLong(request, USERID_JOBPOS);
		workingUnitId = ParamUtil.getLong(request, WORKINGUNITID_JOBPOS);
		directWorkingUnitId =
			ParamUtil.getLong(request, DIRECTWORKINGUNITID_JOBPOS);
		mappingRoleId = ParamUtil.getLong(request, MAPPINGROLEID_JOBPOS);
		leader = ParamUtil.getInteger(request, LEADER_JOBPOS);
		title = ParamUtil.getString(request, TITLE_JOBPOS);
		description = ParamUtil.getString(request, DESCRIPTION_JOBPOS);
		createDate =
			ParamUtil.getDate(
				request,
				CREATEDATE_JOBPOS,
				DateTimeUtil.getDateTimeFormat(DateTimeUtil._VN_DATE_TIME_FORMAT));
		modifiedDate =
						ParamUtil.getDate(
							request,
							MODIFIEDDATE_JOBPOS,
							DateTimeUtil.getDateTimeFormat(DateTimeUtil._VN_DATE_TIME_FORMAT));
		groupId = setGroupId(request);

	}
	
	public long setGroupId(PortletRequest portletRequest) {

		groupId = ParamUtil.getLong(portletRequest, GROUPID_JOBPOS);

		if (groupId != 0) {
			return groupId;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		return themeDisplay.getScopeGroupId();
	}

	public long getJobPosId() {

		return jobPosId;
	}

	public void setJobPosId(long jobPosId) {

		this.jobPosId = jobPosId;
	}

	public long getCompanyId() {

		return companyId;
	}

	public void setCompanyId(long companyId) {

		this.companyId = companyId;
	}

	public long getUserId() {

		return userId;
	}

	public void setUserId(long userId) {

		this.userId = userId;
	}

	public long getWorkingUnitId() {

		return workingUnitId;
	}

	public void setWorkingUnitId(long workingUnitId) {

		this.workingUnitId = workingUnitId;
	}

	public long getDirectWorkingUnitId() {

		return directWorkingUnitId;
	}

	public void setDirectWorkingUnitId(long directWorkingUnitId) {

		this.directWorkingUnitId = directWorkingUnitId;
	}

	public long getMappingRoleId() {

		return mappingRoleId;
	}

	public void setMappingRoleId(long mappingRoleId) {

		this.mappingRoleId = mappingRoleId;
	}

	public int getLeader() {

		return leader;
	}

	public void setLeader(int leader) {

		this.leader = leader;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public Date getCreateDate() {

		return createDate;
	}

	public void setCreateDate(Date createDate) {

		this.createDate = createDate;
	}

	public Date getModifiedDate() {

		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {

		this.modifiedDate = modifiedDate;
	}

	protected long jobPosId;
	protected long groupId;
	protected long companyId;
	protected long userId;
	protected long workingUnitId;
	protected long directWorkingUnitId;
	protected long mappingRoleId;
	protected int leader;
	protected String title;
	protected String description;
	protected Date createDate;
	protected Date modifiedDate;

}
