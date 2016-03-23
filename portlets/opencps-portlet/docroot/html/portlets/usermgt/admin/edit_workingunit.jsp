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
	String backURL = ParamUtil.getString(request, "backURL");
%>
<portlet:actionURL var="updateWorkingUnitURL" name="updateWorkingUnit"/>

<liferay-util:buffer var="htmlTop">
	
</liferay-util:buffer>

<liferay-util:buffer var="htmlBot">
	<aui:button type="submit" name="submit" value="submit"></aui:button>
	<aui:button type="reset" value="clear"/>
</liferay-util:buffer>

<aui:form name="fm" 
	method="post" 
	action="<%=updateWorkingUnitURL.toString() %>">
	<liferay-ui:form-navigator 
		backURL="<%= backURL %>"
		categoryNames=<%= UserMgtUtil. %>	>
		
	</liferay-ui:form-navigator>


</aui:form>