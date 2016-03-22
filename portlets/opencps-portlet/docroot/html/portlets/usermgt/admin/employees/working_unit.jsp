<%@page import="org.opencps.usermgt.search.EmployeeDisplayTerm"%>
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
%>
<aui:row>
	<aui:col width="100">
		<aui:select name="<%= EmployeeDisplayTerm.WORKING_UNIT_ID %>" cssClass="input100"></aui:select>
	</aui:col>
</aui:row>

<aui:row>
	<aui:col width="100">
		<aui:input name="<%= EmployeeDisplayTerm.WORKING_STATUS  %>" 
			type="checkbox" inlineField="<%= true %>" inlineLabel="right"/>
	</aui:col>
</aui:row>

<aui:row id="opencps-usermgt-employee-jobpos">
	<aui:fieldset>
	<%
		for(int i = 0; i < jobPosIndexes.length; i++){
			int jobPosIndex = jobPosIndexes[i];
			%>
				<div class="lfr-form-row lfr-form-row-inline">
					<div class="row-fields">
						
						<aui:select inlineField="<%= true %>" label="type"  name='<%= "phoneTypeId" + jobPosIndex %>' />
		
						<aui:select inlineField="<%= true %>" label="type"  name='<%= "phoneTypeId2" + jobPosIndex %>' />
					</div>
				</div>
				
			<%
		}
	%>
	</aui:fieldset>
	<aui:input name="jobPosIndexes" type="hidden" value="" />
</aui:row>


