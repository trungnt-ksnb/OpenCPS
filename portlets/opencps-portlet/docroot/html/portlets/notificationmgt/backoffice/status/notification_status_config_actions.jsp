<%@page import="org.opencps.notificationmgt.search.NotificationStatusConfigDisplayTerms"%>
<%@page import="org.opencps.notificationmgt.permisson.NotificationStatusConfigPermission"%>
<%@page import="org.opencps.notificationmgt.model.NotificationStatusConfig"%>
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
	 * along with this program. If not, see <http://www.gnu.org/licenses/>.
	 */
%>
<%@page import="org.opencps.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="../../init.jsp"%>


<%
	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	NotificationStatusConfig notificationStatusConfig = (NotificationStatusConfig) row.getObject();
	
%>

<c:if
	test="<%=NotificationStatusConfigPermission.contains(permissionChecker,
						scopeGroupId, ActionKeys.EDIT_NOTIFICATION_CONFIG)%>">
	<portlet:renderURL var="editNotificationConfigURL">
		<portlet:param name="mvcPath"
			value='<%=templatePath +"backoffice/status/notification_status_config_edit.jsp"%>'/>
		<portlet:param name="<%=NotificationStatusConfigDisplayTerms.NOTICE_CONFIG_ID%>"
			value="<%=String.valueOf(notificationStatusConfig.getNotiStatusConfigId())%>" />
		<portlet:param name="backURL" value="<%=currentURL%>" />
	</portlet:renderURL>	
	<aui:icon cssClass="icon-edit" label="edit"
		url="<%=editNotificationConfigURL.toString()%>" />
</c:if>


<c:if test="<%=notificationStatusConfig.getIsSendNotification() == true%>">
	<portlet:actionURL var="activeNotificationConfigURL" name="changeNotificationStatusConfig">
	
		<portlet:param name="<%=NotificationStatusConfigDisplayTerms.NOTICE_CONFIG_ID%>"
			value="<%=String.valueOf(notificationStatusConfig.getNotiStatusConfigId())%>" />
			
		<portlet:param name="<%=NotificationStatusConfigDisplayTerms.IS_SEND_NOTIFICATION%>"
			value="false" />
	</portlet:actionURL>

	<aui:icon cssClass="icon-eye-close"
		label="inactive" url="<%=activeNotificationConfigURL.toString()%>"/>

</c:if>

<c:if test="<%=notificationStatusConfig.getIsSendNotification() == false%>">
	
	<portlet:actionURL var="deactiveNotificationConfigURL" name="changeNotificationStatusConfig">
	
		<portlet:param name="<%=NotificationStatusConfigDisplayTerms.NOTICE_CONFIG_ID%>"
			value="<%=String.valueOf(notificationStatusConfig.getNotiStatusConfigId())%>" />
		<portlet:param name="<%=NotificationStatusConfigDisplayTerms.IS_SEND_NOTIFICATION%>"
			value="true" />
			
	</portlet:actionURL>

	<aui:icon cssClass="icon-eye-open"
		label="active" url="<%=deactiveNotificationConfigURL.toString()%>" />
</c:if>

