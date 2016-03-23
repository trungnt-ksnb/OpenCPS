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
<%@page import="org.opencps.usermgt.search.EmployeeDisplayTerm"%>
<%@page import="org.opencps.usermgt.service.WorkingUnitLocalServiceUtil"%>
<%@page import="org.opencps.usermgt.model.WorkingUnit"%>
<%@page import="java.util.List"%>
<%@ include file="../../init.jsp"%>
<%
	int[] jobPosIndexes = null;

	String jobPosIndexesParam = ParamUtil.getString(request, "jobPosIndexesParam");

	if (Validator.isNotNull(jobPosIndexesParam)) {
		
		jobPosIndexes = StringUtil.split(jobPosIndexesParam, 0);
	}
	else {
		if (jobPosIndexes == null) {
			jobPosIndexes = new int[] {0};
		}
	}
	
	List<WorkingUnit> workingUnits = WorkingUnitLocalServiceUtil.getWorkingUnit(scopeGroupId, true);
%>
<aui:row>
	<aui:col width="100">
		<aui:select name="<%= EmployeeDisplayTerm.WORKING_UNIT_ID %>" cssClass="input100">
			<%
				if(workingUnits != null){
					for(WorkingUnit workingUnit : workingUnits){
						%>
							<aui:option value="<%= workingUnit.getWorkingunitId()%>"><%=workingUnit.getName() %></aui:option>
						<%
					}
				}
			%>
		</aui:select>
	</aui:col>
</aui:row>

<aui:row>
	<aui:col width="100">
		<aui:input name="<%= EmployeeDisplayTerm.WORKING_STATUS  %>" 
			type="checkbox" inlineField="<%= true %>" inlineLabel="right"/>
	</aui:col>
</aui:row>

<label><liferay-ui:message key="main-jobpos"/></label>
<aui:row id="mainJobPosBoundingBox">
	<aui:col width="50">
		<aui:select 
			name='<%= EmployeeDisplayTerm.WORKING_UNIT_ID%>' 
			label="<%= EmployeeDisplayTerm.WORKING_UNIT_ID%>" 
			onChange='<%=renderResponse.getNamespace() + "getJobPosByWorkingUnitId(this)" %>'
			required="<%=true %>"
			showEmptyOption="<%= true %>"
		>
			
		</aui:select>
	</aui:col>
	<aui:col width="50">
		<aui:select name='<%=EmployeeDisplayTerm.MAIN_JOBPOS_ID %>' label="<%= EmployeeDisplayTerm.JOBPOS_ID%>">
			
		</aui:select>
	</aui:col>
</aui:row>

<label><liferay-ui:message key="other-jobpos"/></label>
<aui:row id="opencps-usermgt-employee-jobpos">
	<aui:fieldset id="boundingBox">
	<%
		for(int i = 0; i < jobPosIndexes.length; i++){
			int jobPosIndex = jobPosIndexes[i];
			%>
				<div class="lfr-form-row lfr-form-row-inline">
					<div class="row-fields">
						<aui:col width="50">
							<aui:select 
								name='<%= EmployeeDisplayTerm.WORKING_UNIT_ID + jobPosIndex %>' 
								label="<%= EmployeeDisplayTerm.WORKING_UNIT_ID%>" 
								onChange='<%=renderResponse.getNamespace() + "getJobPosByWorkingUnitId(this)" %>'
								required="<%=true %>"
								showEmptyOption="<%= true %>"
							>
								
							</aui:select>
						</aui:col>
						<aui:col width="50">
							<aui:select name='<%= "jobPosId" + jobPosIndex %>' label="<%= EmployeeDisplayTerm.JOBPOS_ID%>">
								
							</aui:select>
						</aui:col>
					</div>
				</div>
				
			<%
		}
	%>
	</aui:fieldset>
	
	<aui:input name="jobPosIndexes" type="hidden" value="<%= StringUtil.merge(jobPosIndexes)%>" />
</aui:row>


