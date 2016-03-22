
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
<%@page import="com.liferay.portal.UserLockoutException"%>
<%@page import="com.liferay.portal.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.PasswordPolicyLocalServiceUtil"%>
<%@page import="com.liferay.portal.model.PasswordPolicy"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.model.User"%>
<%@page import="org.opencps.usermgt.util.UserMgtUtil"%>
<%@page import="java.util.Date"%>
<%@page import="org.opencps.util.PortletUtil"%>

<%
	String backURL = ParamUtil.getString(request, "backURL");
	User mappingUser = (User)request.getAttribute("");
	
	PasswordPolicy passwordPolicy = null;

	if (mappingUser == null) {
		passwordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(company.getCompanyId());
	}
	else {
		passwordPolicy = mappingUser.getPasswordPolicy();
	}
	
	String[] employeeSections = new String[]{"general_info", "working_unit", "account_info"};
	
	String[][] categorySections = {employeeSections};
%>

<liferay-util:buffer var="htmlTop">
	<c:if test="<%= mappingUser != null %>">
		<div class="user-info">
			<div class="float-container">
				<img alt="<%= HtmlUtil.escape(mappingUser.getFullName()) %>" class="user-logo" src="<%= mappingUser.getPortraitURL(themeDisplay) %>" />

				<span class="user-name"><%= HtmlUtil.escape(mappingUser.getFullName()) %></span>
			</div>
		</div>
	</c:if>
</liferay-util:buffer>

<liferay-util:buffer var="htmlBottom">

	<%
	boolean lockedOut = false;

	if ((mappingUser != null) && (passwordPolicy != null)) {
		try {
			UserLocalServiceUtil.checkLockout(mappingUser);
		}
		catch (UserLockoutException ule) {
			lockedOut = true;
		}
	}
	%>

	<c:if test="<%= lockedOut %>">
		<aui:button-row>
			<div class="alert alert-block"><liferay-ui:message key="this-user-account-has-been-locked-due-to-excessive-failed-login-attempts" /></div>

			<%
			String taglibOnClick = renderResponse.getNamespace() + "saveUser('unlock');";
			%>

			<aui:button onClick="<%= taglibOnClick %>" value="unlock" />
		</aui:button-row>
	</c:if>
</liferay-util:buffer>

<aui:form name="fm">
	<liferay-ui:form-navigator
		backURL="<%= backURL %>"
		categoryNames="<%= UserMgtUtil._EMPLOYESS_CATEGORY_NAMES %>"
		categorySections="<%= categorySections %>"
		htmlBottom="<%= htmlBottom %>"
		htmlTop="<%= htmlTop %>"
		jspPath="/html/portlets/usermgt/admin/employees/"
	/>
</aui:form>

<aui:script use="liferay-auto-fields">
 new Liferay.AutoFields(
       {
           contentBox: '#<portlet:namespace />opencps-usermgt-employee-jobpos > fieldset',
           fieldIndexes: '<portlet:namespace />jobPosIndexes'
       }
   ).render();
</aui:script>

<%-- <aui:script>
	AUI().ready(function(){
		<portlet:namespace/>renderPicker();
	});
	
	Liferay.provide(window, '<portlet:namespace/>renderPicker', function() {
		var A = AUI();
		
		var picker = new Liferay.RenderDatePicker(
        {
        	trigger : '.datePicker',
			lang : 'vi',
			mask : '%d/%m/%Y',
			defaultValue : '',
			zIndex : 1
        }
 	).render();
	},['render-datepicker']);
</aui:script> --%>