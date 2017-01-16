package org.opencps.statisticsmgt.dashboard.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

/**
 * @author trungnt
 */
public class DetailDashBoardConfigurationImpl extends
		DefaultConfigurationAction {

	@Override
	public void processAction(PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {

		// TODO Auto-generated method stub
		String portletResource = ParamUtil.getString(actionRequest,
				"portletResource");

		PortletPreferences preferences = PortletPreferencesFactoryUtil
				.getPortletSetup(actionRequest, portletResource);
		String displayStyle = ParamUtil
				.getString(actionRequest, "displayStyle");

		String chartTitle = ParamUtil.getString(actionRequest, "chartTitle");

		String govCode = ParamUtil.getString(actionRequest, "govCode");

		String domainCode = ParamUtil.getString(actionRequest, "domainCode");

		String filterKey = ParamUtil.getString(actionRequest, "filterKey");

		String chartType = ParamUtil.getString(actionRequest, "chartType");

		String xaxisUnit = ParamUtil.getString(actionRequest, "xaxisUnit");

		String yaxisUnit = ParamUtil.getString(actionRequest, "yaxisUnit");

		int startMonth = ParamUtil.getInteger(actionRequest, "startMonth");

		int startYear = ParamUtil.getInteger(actionRequest, "startYear");

		int period = ParamUtil.getInteger(actionRequest, "period");

		int level = ParamUtil.getInteger(actionRequest, "level");

		boolean notNullGov = ParamUtil.getBoolean(actionRequest, "notNullGov");

		boolean notNullDomain = ParamUtil.getBoolean(actionRequest,
				"notNullDomain");

		String[] fields = ParamUtil.getParameterValues(actionRequest, "fields",
				new String[] { "received-number" });

		preferences.setValue("chartTitle", chartTitle);
		preferences.setValue("xaxisUnit", xaxisUnit);
		preferences.setValue("yaxisUnit", yaxisUnit);
		preferences.setValue("notNullGov", String.valueOf(notNullGov));
		preferences.setValue("notNullDomain", String.valueOf(notNullDomain));
		preferences.setValue("govCode", govCode);
		preferences.setValue("domainCode", domainCode);
		preferences.setValue("startMonth", String.valueOf(startMonth));
		preferences.setValue("startYear", String.valueOf(startYear));
		preferences.setValue("period", String.valueOf(period));
		preferences.setValue("displayStyle", displayStyle);
		preferences.setValue("filterKey", filterKey);
		preferences.setValue("level", String.valueOf(level));
		preferences.setValue("fields", StringUtil.merge(fields));
		preferences.setValue("chartType", String.valueOf(chartType));
		preferences.store();

		super.processAction(portletConfig, actionRequest, actionResponse);
	}
}
