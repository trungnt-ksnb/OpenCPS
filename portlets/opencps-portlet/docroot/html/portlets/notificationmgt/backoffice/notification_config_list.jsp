
<%@page import="com.liferay.portal.kernel.dao.search.SearchEntry"%>
<%@page import="org.opencps.notificationmgt.search.NotificationConfigDisplayTerms"%>
<%@page import="org.opencps.notificationmgt.service.NotificationConfigLocalServiceUtil"%>
<%@page import="org.opencps.notificationmgt.model.impl.NotificationConfigImpl"%>
<%@page import="org.opencps.notificationmgt.model.NotificationConfig"%>
<%@page import="org.opencps.notificationmgt.permisson.NotificationConfigPermission"%>
<%@page import="org.opencps.notificationmgt.search.NotificationConfigSearch"%>
<%@page import="org.opencps.util.DateTimeUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%
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
%>

<%@ include file="../init.jsp"%>

<liferay-util:include page='<%=templatePath + "toolbar.jsp"%>'
	servletContext="<%=application%>" />

<%
	PortletURL iteratorURL = renderResponse.createRenderURL();
	iteratorURL.setParameter("mvcPath", templatePath
	+ "notification_config_list.jsp");

	boolean isPermission = NotificationConfigPermission.contains(
	themeDisplay.getPermissionChecker(),
	themeDisplay.getScopeGroupId(), ActionKeys.ADD_NOTICECONFIG);
	
%>
<div
	class="opencps-searchcontainer-wrapper-width-header default-box-shadow radius8">
	
	<liferay-ui:search-container
		searchContainer="<%=new NotificationConfigSearch(renderRequest,
						SearchContainer.DEFAULT_DELTA, iteratorURL)%>">

		<liferay-ui:search-container-results>
			<%
				List<NotificationConfig> notificationConfigs = NotificationConfigLocalServiceUtil
									.getNotificationConfigs(searchContainer.getStart(),
											searchContainer.getEnd());

							int totalSize = NotificationConfigLocalServiceUtil
									.getNotificationConfigs(QueryUtil.ALL_POS,
											QueryUtil.ALL_POS).size();

							pageContext.setAttribute("results", notificationConfigs);
							pageContext.setAttribute("total", totalSize);
			%>

		</liferay-ui:search-container-results>
		<liferay-ui:search-container-row
			className="org.opencps.notificationmgt.model.NotificationConfig"
			modelVar="notificationConfig" keyProperty="notificationConfigId">
			<%
				PortletURL editURL = renderResponse.createRenderURL();
						editURL.setParameter("mvcPath",
								templatePath+"backoffice/notification_config_edit.jsp");
						editURL.setParameter(NotificationConfigDisplayTerms.NOTICE_CONFIG_ID,
								String.valueOf(notificationConfig.getNotificationConfigId()));
						editURL.setParameter(WebKeys.BACK_URL, currentURL);

						row.setClassName("opencps-searchcontainer-row");

						row.addText(String.valueOf(notificationConfig.getNotificationConfigId()),
								editURL);
						row.addText(notificationConfig.getDossierNextStatus(), editURL);
						
						row.addText(DateTimeUtil.convertDateToString(
								notificationConfig.getCreateDate(),
								DateTimeUtil._VN_DATE_TIME_FORMAT), editURL);
						
						row.addText(DateTimeUtil.convertDateToString(
								notificationConfig.getModifiedDate(),
								DateTimeUtil._VN_DATE_TIME_FORMAT), editURL);
						
						row.addText(
								String.valueOf(notificationConfig.getIsSendNotification() == true ? LanguageUtil
										.get(pageContext, "active") : LanguageUtil
										.get(pageContext, "inactive")), editURL);

						if (isPermission) {
							row.addJSP(
									"center",
									SearchEntry.DEFAULT_VALIGN,
									templatePath+"backoffice/notification_config_actions.jsp",
									config.getServletContext(), request, response);
						}
			%>

		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator type="opencs_page_iterator" />
	</liferay-ui:search-container>
</div>

<%!private static Log _log = LogFactoryUtil
			.getLog("html.portlets.notificationmgt.backoffice.notification_config_list.jsp");%>