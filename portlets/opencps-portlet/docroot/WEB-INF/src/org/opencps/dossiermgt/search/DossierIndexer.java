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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.portlet.PortletURL;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.permissions.DossierPermission;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.DossierActionableDynamicQuery;
import org.opencps.dossiermgt.util.PortletKeys;
import org.opencps.util.ActionKeys;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author trungdk
 */
public class DossierIndexer extends BaseIndexer {
	public static final String[] CLASS_NAMES = { Dossier.class.getName() };

	public static final String PORTLET_ID = PortletKeys.PORTLET_19;

	@Override
	public String[] getClassNames() {
		// TODO Auto-generated method stub
		return CLASS_NAMES;
	}

	@Override
	public String getPortletId() {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker,
			String dossierClassName, long dossierClassPK,
			String actionId) throws Exception {

		return DossierPermission.contains(permissionChecker, dossierClassPK,
				ActionKeys.VIEW);
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Dossier dossier = (Dossier) obj;

		deleteDocument(dossier.getCompanyId(), dossier.getDossierId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Dossier dossier = (Dossier) obj;

		Document document = getBaseModelDocument(PORTLET_ID, dossier);
		
		if (dossier.getReceptionNo() != null && !Validator.isBlank(dossier.getReceptionNo())) {
			Field field = new Field(DossierDisplayTerms.RECEPTION_NO, dossier.getReceptionNo());
			field.setBoost(5);
			document.add(field);			
		}
		if (dossier.getModifiedDate() != null) {
			document.addDate(Field.MODIFIED_DATE, dossier.getModifiedDate());			
		}
		if (dossier.getCityName() != null && !Validator.isBlank(dossier.getCityName())) {
			document.addText(DossierDisplayTerms.CITY_NAME, dossier.getCityName().toLowerCase().split("\\s+"));			
		}
		if (dossier.getExternalRefNo() != null && !Validator.isBlank(dossier.getExternalRefNo())) {
			document.addText(DossierDisplayTerms.EXTERNALREF_NO, dossier.getExternalRefNo());			
		}
		if (dossier.getExternalRefUrl() != null && !Validator.isBlank(dossier.getExternalRefUrl())) {
			document.addText(DossierDisplayTerms.EXTERNALREF_URL, dossier.getExternalRefUrl());					
		}
		if (dossier.getGovAgencyName() != null && !Validator.isBlank(dossier.getGovAgencyName())) {
			document.addText(DossierDisplayTerms.GOVAGENCY_NAME, dossier.getGovAgencyName().toLowerCase().split("\\s+"));			
		}
		if (dossier.getSubjectName() != null && !Validator.isBlank(dossier.getSubjectName())) {
			document.addText(DossierDisplayTerms.SUBJECT_NAME, dossier.getSubjectName().toLowerCase().split("\\s+"));			
		}
		if (dossier.getAddress() != null && !Validator.isBlank(dossier.getAddress())) {
			document.addText(DossierDisplayTerms.ADDRESS, dossier.getAddress().toLowerCase().split("\\s+"));			
		}
		if (dossier.getCityCode() != null && !Validator.isBlank(dossier.getCityCode())) {
			document.addText(DossierDisplayTerms.CITY_CODE, dossier.getCityCode());			
		}
		if (dossier.getDistrictCode() != null && !Validator.isBlank(dossier.getDistrictCode())) {
			document.addText(DossierDisplayTerms.DISTRICT_CODE, dossier.getDistrictCode());			
		}
		if (dossier.getDistrictName() != null && !Validator.isBlank(dossier.getDistrictName())) {
			document.addText(DossierDisplayTerms.DISTRICT_NAME, dossier.getDistrictName().toLowerCase().split("\\s+"));			
		}
		if (dossier.getWardCode() != null && !Validator.isBlank(dossier.getWardCode())) {
			document.addText(DossierDisplayTerms.WARD_CODE, dossier.getWardCode());			
		}
		if (dossier.getWardName() != null && !Validator.isBlank(dossier.getWardName())) {
			document.addText(DossierDisplayTerms.WARD_NAME, dossier.getWardName().toLowerCase().split("\\s+"));			
		}
		if (dossier.getContactName() != null && !Validator.isBlank(dossier.getContactName())) {
			document.addText(DossierDisplayTerms.CONTACT_NAME, dossier.getContactName().toLowerCase().split("\\s+"));			
		}
		if (dossier.getContactTelNo() != null && !Validator.isBlank(dossier.getContactTelNo())) {
			document.addText(DossierDisplayTerms.CONTACT_TEL_NO, dossier.getContactTelNo());			
		}
		if (dossier.getContactEmail() != null && !Validator.isBlank(dossier.getContactEmail())) {
			document.addText(DossierDisplayTerms.CONTACT_EMAIL, dossier.getContactEmail());			
		}
		if (dossier.getNote() != null && !Validator.isBlank(dossier.getNote())) {
			document.addText(DossierDisplayTerms.NOTE, dossier.getNote().toLowerCase().split("\\s+"));			
		}
		document.addNumber(DossierDisplayTerms.DOSSIER_ID, dossier.getDossierId());
		
		document.addKeyword(Field.GROUP_ID,
				getSiteGroupId(dossier.getGroupId()));
		document.addKeyword(Field.SCOPE_GROUP_ID, dossier.getGroupId());
		
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale,
			String snippet, PortletURL portletURL) throws Exception {
		// TODO Auto-generated method stub
		Summary summary = createSummary(document);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Dossier dossier = (Dossier) obj;

		Document document = getDocument(dossier);

		SearchEngineUtil.updateDocument(getSearchEngineId(),
				dossier.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		long companyId = GetterUtil.getLong(ids[0]);

		reindexEntries(companyId);
	}

	protected void reindexEntries(long companyId) throws PortalException,
			SystemException {

		final Collection<Document> documents = new ArrayList<Document>();

		ActionableDynamicQuery actionableDynamicQuery = new DossierActionableDynamicQuery() {
			
			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
			}

			@Override
			protected void performAction(Object object) throws PortalException {
				Dossier dossier = (Dossier) object;

				Document document = getDocument(dossier);

				documents.add(document);
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);

		actionableDynamicQuery.performActions();

		SearchEngineUtil.updateDocuments(getSearchEngineId(), companyId,
				documents);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		// TODO Auto-generated method stub
        Dossier dossier = DossierLocalServiceUtil.getDossier(classPK);

        doReindex(dossier);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}

}
