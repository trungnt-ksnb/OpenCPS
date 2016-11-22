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

package org.opencps.dossiermgt.search;

import java.util.Date;

import javax.portlet.PortletRequest;

import org.opencps.util.DateTimeUtil;
import org.opencps.util.PortletConstants;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author trungnt
 */
public class DossierSearchTerms extends DossierDisplayTerms {

	public DossierSearchTerms(PortletRequest portletRequest) {
		super(
			portletRequest);

		createDate = ParamUtil
			.getDate(portletRequest, CREATE_DATE, DateTimeUtil
				.getDateTimeFormat(DateTimeUtil._VN_DATE_TIME_FORMAT));

		receiveDatetime = ParamUtil
			.getDate(portletRequest, RECEIVE_DATETIME, DateTimeUtil
				.getDateTimeFormat(DateTimeUtil._VN_DATE_TIME_FORMAT));
		dossierStatus = ParamUtil
			.getString(portletRequest, DOSSIER_STATUS,
				PortletConstants.DOSSIER_STATUS_NEW);

		serviceName = DAOParamUtil
			.getString(portletRequest, SERVICE_NAME);

		modifiedDate = ParamUtil
			.getDate(portletRequest, MODIFIED_DATE, DateTimeUtil
				.getDateTimeFormat(DateTimeUtil._VN_DATE_TIME_FORMAT));

		receptionNo = DAOParamUtil
			.getString(portletRequest, RECEPTION_NO);

		domainCode = DAOParamUtil
			.getString(portletRequest, SERVICE_DOMAIN_CODE);

		govAgencyName = DAOParamUtil
			.getString(portletRequest, GOVAGENCY_NAME);

	}

	public String getDossierStatus() {

		return dossierStatus;
	}

	public void setDossierStatus(String dossierStatus) {

		this.dossierStatus = dossierStatus;
	}

	public String getServiceName() {

		return serviceName;
	}

	public void setServiceName(String serviceName) {

		this.serviceName = serviceName;
	}

	public String getGovAgencyName() {

		return govAgencyName;
	}

	public void setGovAgencyName(String govAgencyName) {

		this.govAgencyName = govAgencyName;
	}

	public String getReceptionNo() {

		return receptionNo;
	}

	public void setReceptionNo(String receptionNo) {

		this.receptionNo = receptionNo;
	}

	public Date getCreateDate() {

		return createDate;
	}

	public void setCreateDate(Date createDate) {

		this.createDate = createDate;
	}

	public Date getReceiveDatetime() {

		return receiveDatetime;
	}

	public void setReceiveDatetime(Date receiveDatetime) {

		this.receiveDatetime = receiveDatetime;
	}

	protected String dossierStatus;
	protected String serviceName;
	protected String domainCode;

	public String getDomainCode() {

		return domainCode;
	}

	public void setDomainCode(String domainCode) {

		this.domainCode = domainCode;
	}

	protected String govAgencyName;
	protected String receptionNo;
	protected Date createDate;
	protected Date receiveDatetime;
}
