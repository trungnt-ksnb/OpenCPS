
package org.opencps.usermgt.portlet;

import com.liferay.util.bridges.mvc.MVCPortlet;

public class UserMgtPortlet extends MVCPortlet {

	/*@Override
	public void render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		long workingUnitId =
			ParamUtil.getLong(
				renderRequest, WorkingUnitDisplayTerms.WORKINGUNIT_ID);
		System.out.println("====wID  " + workingUnitId);

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
	}
	private Log _log = LogFactoryUtil.getLog(UserMgtPortlet.class.getName());*/
}
