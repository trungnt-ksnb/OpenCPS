<%@page import="org.opencps.notificationmgt.search.NotificationConfigDisplayTerms"%>
<%@page import="org.opencps.notificationmgt.permisson.NotificationConfigPermission"%>
<%@page import="org.opencps.notificationmgt.model.NotificationConfig"%>
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
<%@ include file="../init.jsp"%>


<%
	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	NotificationConfig notificationConfig = (NotificationConfig) row.getObject();
	
%>

<c:if
	test="<%=NotificationConfigPermission.contains(permissionChecker,
						scopeGroupId, ActionKeys.ADD_NOTICECONFIG)%>">
	<portlet:renderURL var="editNotificationConfigURL">
		<portlet:param name="mvcPath"
			value='<%=templatePath +"backoffice/notification_config_edit.jsp"%>'/>
		<portlet:param name="<%=NotificationConfigDisplayTerms.NOTICE_CONFIG_ID%>"
			value="<%=String.valueOf(notificationConfig.getNotificationConfigId())%>" />
		<portlet:param name="backURL" value="<%=currentURL%>" />
	</portlet:renderURL>
	<liferay-ui:icon image="edit"
		cssClass="search-container-action fa edit" message="edit"
		url="<%=editNotificationConfigURL.toString()%>" />
</c:if>

<c:if test="<%=notificationConfig.getIsSendNotification() == true%>">
	<portlet:actionURL var="deactiveNotificationConfigURL" name="updateNotificationConfig">
	
		<portlet:param name="<%=NotificationConfigDisplayTerms.NOTICE_CONFIG_ID%>"
			value="<%=String.valueOf(notificationConfig.getNotificationConfigId())%>" />
			
		<portlet:param name="redirectURL" value="<%=currentURL%>" />
		<portlet:param name="<%=NotificationConfigDisplayTerms.IS_SEND_NOTIFICATION%>"
			value="<%=String.valueOf(notificationConfig.getIsSendNotification())%>" />
			
	</portlet:actionURL>

	<liferay-ui:icon
		cssClass="search-container-action fa fa-eye-slash ocps-btn ocps-red"
		image="deactive"
	 	url="<%=deactiveNotificationConfigURL.toString()%>"></liferay-ui:icon>

</c:if>

<c:if test="<%=notificationConfig.getIsSendNotification() == false%>">
	<portlet:actionURL var="activeNotificationConfigURL" name="updateNotificationConfig">
	
		<portlet:param name="<%=NotificationConfigDisplayTerms.NOTICE_CONFIG_ID%>"
			value="<%=String.valueOf(notificationConfig.getNotificationConfigId())%>" />
			
		<portlet:param name="redirectURL" value="<%=currentURL%>" />
		<portlet:param name="<%=NotificationConfigDisplayTerms.IS_SEND_NOTIFICATION%>"
			value="<%=String.valueOf(notificationConfig.getIsSendNotification())%>" />
	</portlet:actionURL>
	<liferay-ui:icon
		image="active"
		cssClass="search-container-action fa fa-eye ocps-btn ocps-green"
		url="<%=activeNotificationConfigURL.toString()%>" ></liferay-ui:icon>
</c:if>

