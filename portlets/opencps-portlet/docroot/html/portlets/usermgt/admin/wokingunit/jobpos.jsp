<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="org.opencps.usermgt.search.WorkingUnitDisplayTerms"%>
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

<%@ include file="../../init.jsp"%>
<%
	long workingUnitId = ParamUtil.getLong(request, WorkingUnitDisplayTerms.WORKINGUNIT_ID);
%>

<portlet:renderURL 	var="dialogURL"	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcPath" value='<%= templatePath + "edit_jobpos.jsp" %>' />
	<portlet:param name="workingunitRefId" value="<%=String.valueOf(workingUnitId)%>" />
</portlet:renderURL>

<c:if test="<%=workingUnitId !=0 %>">
	<%@include file="/html/portlets/usermgt/admin/link_popup.jspf" %>
</c:if>

