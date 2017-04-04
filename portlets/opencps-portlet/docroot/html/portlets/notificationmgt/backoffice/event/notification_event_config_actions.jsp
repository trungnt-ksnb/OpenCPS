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
<%@ include file="../../init.jsp"%>


<%
	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	NotificationEventConfig notificationEventConfig = (NotificationEventConfig) row.getObject();
	
%>

<c:if
	test="<%=true%>">
	<portlet:renderURL var="editNotificationConfigURL">
		<portlet:param name="mvcPath"
			value='<%=templatePath +"backoffice/event/notification_event_config_edit.jsp"%>'/>
		<portlet:param name="<%=NotificationEventConfigDisplayTerms.NOTICE_EVENT_CONFIG_ID%>"
			value="<%=String.valueOf(notificationEventConfig.getNotiStatusConfigId())%>" />
		<portlet:param name="backURL" value="<%=currentURL%>" />
	</portlet:renderURL>
	
	<aui:icon cssClass="icon-edit" label="edit"
		url="<%=editNotificationConfigURL.toString()%>" />
</c:if>

<c:if test="<%=notificationEventConfig.isActive() == true%>">
	<portlet:actionURL var="activeNotiEventConfigURL" name="changeNotiEventConfig">
	
		<portlet:param name="<%=NotificationEventConfigDisplayTerms.NOTICE_EVENT_CONFIG_ID%>"
			value="<%=String.valueOf(notificationEventConfig.getNotiStatusConfigId())%>" />
		<portlet:param name="<%=NotificationEventConfigDisplayTerms.ACTIVE%>"
			value="false" />
			
	</portlet:actionURL>

	<aui:icon cssClass="icon-eye-close"
		label="inactive" url="<%=activeNotiEventConfigURL.toString()%>"/>
</c:if>

<c:if test="<%=notificationEventConfig.isActive() == false%>">
	<portlet:actionURL var="deactiveNotiEventConfigURL" name="changeNotiEventConfig">
	
		<portlet:param name="<%=NotificationEventConfigDisplayTerms.NOTICE_EVENT_CONFIG_ID%>"
			value="<%=String.valueOf(notificationEventConfig.getNotiStatusConfigId())%>" />	
		<portlet:param name="<%=NotificationEventConfigDisplayTerms.ACTIVE%>"
			value="true" />
			
	</portlet:actionURL>
	<aui:icon cssClass="icon-eye-open"
		label="active" url="<%=deactiveNotiEventConfigURL.toString()%>" />
</c:if>

