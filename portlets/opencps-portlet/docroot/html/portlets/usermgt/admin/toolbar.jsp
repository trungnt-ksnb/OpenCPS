
<%@page import="org.opencps.usermgt.search.EmployeeDisplayTerm"%>
<%@page import="org.opencps.usermgt.util.UserMgtUtil"%>
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

<%@ include file="../init.jsp"%>

<%
	String tabs1 = ParamUtil.getString(request, "tabs1", UserMgtUtil.TOP_TABS_WORKINGUNIT);
%>

<c:choose>
	<c:when test="<%= tabs1.equals(UserMgtUtil.TOP_TABS_WORKINGUNIT)%>">
		<portlet:renderURL var="editWorkingUnitURL">
			<portlet:param name="mvcPath" value='<%= templatePath + "edit_workingunit.jsp" %>'/>
		</portlet:renderURL>
		<aui:button name="add-workingunit" value="add-workingunit" href="<%= editWorkingUnitURL%>"/>
	</c:when>
	
	<c:when test="<%= tabs1.equals(UserMgtUtil.TOP_TABS_EMPLOYEE)%>">
		<aui:col><aui:input name="keywords" type="text" label=""/></aui:col>
		<aui:col><aui:select name="<%=EmployeeDisplayTerm.WORKING_UNIT_ID %>" label=""></aui:select></aui:col>
		<aui:col><aui:button name="add-employee" value="add-employee" href="#"/></aui:col>
	</c:when>
	
	<c:otherwise>
	
	</c:otherwise>
</c:choose>


