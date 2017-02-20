
package org.opencps.statisticsmgt.dashboard.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

/**
 * @author trungnt
 */
public class DetailDashBoardConfigurationImpl
	extends DefaultConfigurationAction {

	@Override
	public void processAction(
		PortletConfig portletConfig, ActionRequest actionRequest,
		ActionResponse actionResponse)
		throws Exception {

		// TODO Auto-generated method stub
		String portletResource =
			ParamUtil.getString(actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);
		String displayStyle =
			ParamUtil.getString(actionRequest, "displayStyle");

		String chartTitle = ParamUtil.getString(actionRequest, "chartTitle");

		String govCode = ParamUtil.getString(actionRequest, "govCode");

		String domainCode = ParamUtil.getString(actionRequest, "domainCode");
		
		String filterKey = ParamUtil.getString(actionRequest, "filterKey");

		int startMonth = ParamUtil.getInteger(actionRequest, "startMonth");

		int startYear = ParamUtil.getInteger(actionRequest, "startYear");

		int period = ParamUtil.getInteger(actionRequest, "period");

		boolean notNullGov = ParamUtil.getBoolean(actionRequest, "notNullGov");

		boolean notNullDomain =
			ParamUtil.getBoolean(actionRequest, "notNullDomain");

		preferences.setValue("chartTitle", chartTitle);

		preferences.setValue("notNullGov", String.valueOf(notNullGov));
		preferences.setValue("notNullDomain", String.valueOf(notNullDomain));
		preferences.setValue("govCode", govCode);
		preferences.setValue("domainCode", domainCode);
		preferences.setValue("startMonth", String.valueOf(startMonth));
		preferences.setValue("startYear", String.valueOf(startYear));
		preferences.setValue("period", String.valueOf(period));
		preferences.setValue("displayStyle", displayStyle);
		preferences.setValue("filterKey", filterKey);

		preferences.store();
		
		super.processAction(portletConfig, actionRequest, actionResponse);
	}
}
