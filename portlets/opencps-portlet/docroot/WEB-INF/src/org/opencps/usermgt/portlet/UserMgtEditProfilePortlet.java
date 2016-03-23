
package org.opencps.usermgt.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.opencps.usermgt.NoSuchWorkingUnitException;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.search.JobPosDisplaySearchTerms;
import org.opencps.usermgt.search.WorkingUnitDisplayTerms;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
import org.opencps.util.MessageKeys;
import org.opencps.util.WebKeys;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class UserMgtEditProfilePortlet extends MVCPortlet {

	@Override
	public void render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		long workingUnitId =
			ParamUtil.getLong(
				renderRequest, WorkingUnitDisplayTerms.WORKINGUNIT_ID);
		System.out.println("add-workingunit " + workingUnitId);
		try {
			if (workingUnitId > 0) {
				WorkingUnit workingUnit =
					WorkingUnitLocalServiceUtil.getWorkingUnit(workingUnitId);
				renderRequest.setAttribute(
					WebKeys.WORKING_UNIT_ENTRY, workingUnit);
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
		super.render(renderRequest, renderResponse);
	}

	public void workingUnitAdd(ActionRequest request, ActionResponse response)
		throws PortalException, SystemException {

		long managerWorkingUnitId =
			ParamUtil.getLong(
				request,
				WorkingUnitDisplayTerms.WORKINGUNIT_MANAGERWORKINGUNITID);
		long workingUnitId =
			ParamUtil.getLong(request, WorkingUnitDisplayTerms.WORKINGUNIT_ID);
		long parentWorkingUnitId =
			ParamUtil.getLong(
				request,
				WorkingUnitDisplayTerms.WORKINGUNIT_PARENTWORKINGUNITID);
		String name =
			ParamUtil.getString(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_NAME);
		String enName =
			ParamUtil.getString(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_ENNAME);
		String address =
			ParamUtil.getString(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_ADDRESS);
		String telNo =
			ParamUtil.getString(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_TELNO);
		String faxNo =
			ParamUtil.getString(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_FAXNO);
		String email =
			ParamUtil.getString(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_EMAIL);
		String website =
			ParamUtil.getString(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_WEBSITE);
		boolean isEmployer =
			ParamUtil.getBoolean(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_ISEMPLOYER);
		String govAgencyCode =
			ParamUtil.getString(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_GOVAGENCYCODE);
		String cityCode =
			ParamUtil.getString(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_CITYCODE);
		String districtCode =
			ParamUtil.getString(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_DISTRICTCODE);
		String wardCode =
			ParamUtil.getString(
				request, WorkingUnitDisplayTerms.WORKINGUNIT_WARDCODE);
		ServiceContext serviceContext =
			ServiceContextFactory.getInstance(request);

		if (managerWorkingUnitId == -1) {
			managerWorkingUnitId = 0;
		}
		System.out.println("====workingUnitId  " + workingUnitId +
			" parentWorkingUnitId " + parentWorkingUnitId +
			" managerWorkingUnitId " + managerWorkingUnitId);

		if (workingUnitId == 0) {
			WorkingUnitLocalServiceUtil.addWorkingUnit(
				serviceContext.getUserId(), serviceContext, name, enName,
				govAgencyCode, parentWorkingUnitId, address, cityCode,
				districtCode, wardCode, telNo, faxNo, email, website,
				isEmployer, managerWorkingUnitId);
			SessionMessages.add(request, MessageKeys.WORKINGUNIT_UPDATE_SUCESS);
		}
		else {
			WorkingUnitLocalServiceUtil.updateWorkingUnit(
				workingUnitId, serviceContext.getUserId(), serviceContext,
				name, enName, govAgencyCode, parentWorkingUnitId, address,
				cityCode, districtCode, wardCode, telNo, faxNo, email, website,
				isEmployer, managerWorkingUnitId);
			SessionMessages.add(request, MessageKeys.WORKINGUNIT_UPDATE_SUCESS);
		}

	}

	public void deleteWorkingUnit(ActionRequest request, ActionResponse response)
		throws NoSuchWorkingUnitException, SystemException {

		long workingUnitId =
			ParamUtil.getLong(request, WorkingUnitDisplayTerms.WORKINGUNIT_ID);
		WorkingUnitLocalServiceUtil.deleteWorkingUnitByWorkingUnitId(workingUnitId);
	}

	public void jobPosAdd(ActionRequest request, ActionResponse response) {

		String rowIndexes = request.getParameter("rowIndexes");
		System.out.println("===rowIndexes " + rowIndexes);
		String[] indexOfRows = rowIndexes.split(",");

		for (int index = 0; index < indexOfRows.length; index++) {
			String chucvu =
				request.getParameter(JobPosDisplaySearchTerms.TITLE_JOBPOS +
					indexOfRows[index].trim());
			String vitri =
				request.getParameter(JobPosDisplaySearchTerms.LEADER_JOBPOS +
					indexOfRows[index].trim());

			System.out.println("====chucvu " + chucvu + " vitri " + vitri +
				" indexOfRows " + indexOfRows + " index " + index);
		}
	}
	private Log _log =
		LogFactoryUtil.getLog(UserMgtEditProfilePortlet.class.getName());
}
