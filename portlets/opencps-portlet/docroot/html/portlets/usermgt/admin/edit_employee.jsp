
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

<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="org.opencps.usermgt.search.EmployeeDisplayTerm"%>
<%@page import="com.liferay.portal.UserLockoutException"%>
<%@page import="com.liferay.portal.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.PasswordPolicyLocalServiceUtil"%>
<%@page import="com.liferay.portal.model.PasswordPolicy"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.model.User"%>
<%@page import="org.opencps.usermgt.util.UserMgtUtil"%>
<%@page import="java.util.Date"%>
<%@page import="org.opencps.util.PortletUtil"%>
<%@page import="org.opencps.util.WebKeys"%>
<%@page import="org.opencps.usermgt.model.Employee"%>
<%@ include file="../init.jsp"%>


<%
	Employee employee = (Employee)request.getAttribute(WebKeys.EMPLOYEE_ENTRY);

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

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (employee == null) ? "add-employee" : "update-employee" %>'
/>

<portlet:actionURL var="updateEmployeeURL" name="updateEmployee"/>

<portlet:renderURL var="renderWorkingUnitJobPosURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>">
	<portlet:param name="mvcPath" value='<%=templatePath + "ajax/render_workingunit_jobpos.jsp" %>'/>
</portlet:renderURL>

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

<aui:form name="fm" action="<%=updateEmployeeURL %>" method="post">

	<aui:model-context bean="<%=employee %>" model="<%=Employee.class %>" />
	
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

	var workingUnitInput = AUI().one('#<portlet:namespace/><%= EmployeeDisplayTerm.WORKING_UNIT_ID%>');
	
	var boundingBox = AUI().one('#<portlet:namespace/>boundingBox .row-fields');

	var autoFieldRows = AUI().all('#<portlet:namespace/>boundingBox .lfr-form-row-inline');
	
	AUI().ready(function(A){
		
		<portlet:namespace/>renderWorkingUnitJobPos();
		
		workingUnitInput.on('change', function(){
			<portlet:namespace/>renderWorkingUnitJobPos();
		});
		
	});
	
	new Liferay.AutoFields(
       {
           contentBox: '#<portlet:namespace />opencps-usermgt-employee-jobpos > fieldset',
           fieldIndexes: '<portlet:namespace />jobPosIndexes'
       }
	).render();
 
	Liferay.provide(window, '<portlet:namespace/>renderWorkingUnitJobPos', function() {
		
		var A = AUI();

		autoFieldRows = A.all('#<portlet:namespace/>boundingBox .lfr-form-row-inline');

		if(autoFieldRows){
			autoFieldRows.each(function(node, index){
				console.log(autoFieldRows.size());
				console.log(index);
				if(index != 0){
					node.remove();	
				}		
			});
		}
		
		if(workingUnitInput){
			var value = workingUnitInput.val();
			 A.io.request(
				'<%= renderWorkingUnitJobPosURL.toString()%>',
				{
				    dataType : 'json',
				    data:{    	
				    	workingUnitId : value,
				    },   
				    on: {
				        success: function(event, id, obj) {
							var instance = this;
							var res = instance.get('responseData');
							
							if(boundingBox){
								boundingBox.empty();
								boundingBox.html(res);
							}
								
						},
				    	error: function(){}
					}
				}
			);
		}
	});
	
	Liferay.provide(window, '<portlet:namespace/>getJobPosByWorkingUnitId', function(e) {
			
		var A = AUI();
		
		var instance = A.one(e);
		
		var value = instance.val();
		
		var name = instance.attr('name');
		
		var index = name.substring(name.length -1, name.length);

		console.log(index);
		
		var jobPosBoundingBox = A.one('#<portlet:namespace/><%= EmployeeDisplayTerm.JOBPOS_ID%>' + index);
		
		console.log(jobPosBoundingBox);
		
		A.io.request(
			'<%= renderWorkingUnitJobPosURL.toString()%>',
			{
			    dataType : 'json',
			    data:{    	
			    	workingUnitId : value,
			    },   
			    on: {
			        success: function(event, id, obj) {
						var instance = this;
						var res = instance.get('responseData');
						
						if(jobPosBoundingBox){
							jobPosBoundingBox.empty();
							jobPosBoundingBox.html('<option>XXX</option>');
						}
							
					},
			    	error: function(){}
				}
			}
		);
	});
</aui:script>