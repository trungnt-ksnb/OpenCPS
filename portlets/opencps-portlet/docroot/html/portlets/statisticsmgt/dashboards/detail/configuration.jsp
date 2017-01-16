
<%@page import="com.liferay.portal.kernel.util.ArrayUtil"%>
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */
%>

<%@page import="org.opencps.util.PortletPropsValues"%>
<%@page import="org.opencps.statisticsmgt.model.DossiersStatistics"%>
<%@page import="com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.template.TemplateHandler"%>

<%@ include file="init.jsp" %>


<liferay-portlet:actionURL var="configurationActionURL" portletConfiguration="true"/>

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	
	<liferay-ui:panel-container 
		extended="<%= true %>" 
		id="chartConfigPanelContainer" 
		persistState="<%= true %>"
	>
		<liferay-ui:panel 
			collapsible="<%= true %>" 
			extended="<%= true %>" 
			id="chartConfigPanel" 
			persistState="<%= true %>" 
			title="chart-configuration"
		>
			<aui:fieldset>
				<aui:input name="chartTitle" type="text" value="<%=chartTitle %>"/>
			</aui:fieldset>
			
			<aui:fieldset>
				<aui:input name="xaxisUnit" type="text" value="<%=xaxisUnit %>"/>
			</aui:fieldset>
			
			<aui:fieldset>
				<aui:input name="yaxisUnit" type="text" value="<%=yaxisUnit %>"/>
			</aui:fieldset>
			
			<aui:fieldset>
				<aui:select name="chartType">
					<aui:option value="pie" selected='<%=chartType.equals("pie") %>'><liferay-ui:message key="pie"/></aui:option>
					<aui:option value="bar" selected='<%=chartType.equals("bar") %>'><liferay-ui:message key="bar"/></aui:option>
					<aui:option value="table" selected='<%=chartType.equals("table") %>'><liferay-ui:message key="table"/></aui:option>
				</aui:select>
			</aui:fieldset>
		</liferay-ui:panel>
		
		<liferay-ui:panel 
			collapsible="<%= true %>" 
			extended="<%= true %>" 
			id="chartFilterPanel" 
			persistState="<%= true %>" 
			title="chart-filter"
		>
			<aui:fieldset>
				<aui:select name="" multiple="<%=true %>">
					<aui:option value="remaining-number" selected="<%=ArrayUtil.contains(fields, \"remaining-number\")%>">
						<liferay-ui:message key="remaining-number"/>
					</aui:option>
					<aui:option value="received-number" selected="<%=ArrayUtil.contains(fields, \"received-number\")%>">
						<liferay-ui:message key="received-number"/>
					</aui:option>
					<aui:option value="ontime-number" selected="<%=ArrayUtil.contains(fields, \"ontime-number\")%>">
						<liferay-ui:message key="ontime-number"/>
					</aui:option>
					<aui:option value="overtime-number" selected="<%=ArrayUtil.contains(fields, \"overtime-number\")%>">
						<liferay-ui:message key="overtime-number"/>
					</aui:option>
					<aui:option value="processing-number" selected="<%=ArrayUtil.contains(fields, \"processing-number\")%>">
						<liferay-ui:message key="processing-number"/>
					</aui:option>
					<aui:option value="delaying-number" selected="<%=ArrayUtil.contains(fields, \"delaying-number\")%>">
						<liferay-ui:message key="delaying-number"/>
					</aui:option>
				</aui:select>
			</aui:fieldset>
			<aui:fieldset>
				<aui:col width="30">
					<aui:select name="startMonth">
						<%
							for(int m = 1; m <=12; m++){
								%>
									<aui:option value="<%=m %>" selected="<%=m == startMonth %>"><liferay-ui:message key="month"/> <%=m %></aui:option>
								<%
							}
						%>
					</aui:select>
				</aui:col>
				
				<aui:col width="30">
					<aui:select name="startYear">
						<%
							for(int y = 2016; y <=2030; y++){
								%>
									<aui:option value="<%=y %>" selected="<%=y == startYear %>"><liferay-ui:message key="year"/> <%=y %></aui:option>
								<%
							}
						%>
					</aui:select>
				</aui:col>
				
				<aui:col width="30">
					<aui:input name="period" value="<%=period %>">
						 <aui:validator name="digits"/>
					</aui:input>
				</aui:col>
			</aui:fieldset>
			
			<aui:fieldset>
				<aui:input name="notNullGov" type="checkbox" value="<%=notNullGov %>"/>
			</aui:fieldset>
			
			<aui:fieldset>
				<aui:input name="notNullDomain" type="checkbox" value="<%=notNullDomain %>"/>
			</aui:fieldset>
			
			<aui:fieldset>
				<aui:col width="50">
					<datamgt:ddr
						cssClass="input100"
						depthLevel="1" 
						dictCollectionCode="<%=PortletPropsValues.DATAMGT_MASTERDATA_GOVERNMENT_AGENCY %>"
						itemNames="govCode"
						itemsEmptyOption="true"
						selectedItems="<%=govCode%>"
						optionValueType="code"
					/>
				</aui:col>
				
				<aui:col width="50">
					<datamgt:ddr
						cssClass="input100"
						depthLevel="1" 
						dictCollectionCode="<%=PortletPropsValues.DATAMGT_MASTERDATA_SERVICE_DOMAIN %>"
						itemNames="domainCode"
						itemsEmptyOption="true"
						selectedItems="<%=domainCode%>"
						optionValueType="code"
					/>
				</aui:col>
			</aui:fieldset>
			<aui:fieldset>
				<aui:select name="level">
					<aui:option value="0" selected="<%=level == 0 %>">0</aui:option>
					<aui:option value="-1" selected="<%=level== -1 %>">-1</aui:option>
				</aui:select>
			</aui:fieldset>
		</liferay-ui:panel>
		
		<liferay-ui:panel 
			collapsible="<%= true %>" 
			extended="<%= true %>" 
			id="dataFilterPanel" 
			persistState="<%= true %>" 
			title="data-filter"
		>
			<aui:fieldset>
				<aui:select name="filterKey">
					<aui:option value="gov" selected='<%=filterKey.equals("gov") %>'><liferay-ui:message key="govagency-code"/></aui:option>
					<aui:option value="domain" selected='<%=filterKey.equals("domain") %>'><liferay-ui:message key="domain-code"/></aui:option>
				</aui:select>
			</aui:fieldset>
		</liferay-ui:panel>
	</liferay-ui:panel-container>
	
	<aui:fieldset>
		<div class="display-template">
		<%
			TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(DossiersStatistics.class.getName() + "#DETAIL");
		%>
		<liferay-ui:ddm-template-selector
			classNameId="<%= PortalUtil.getClassNameId(templateHandler.getClassName()) %>"
			displayStyle="<%= displayStyle %>"
			displayStyleGroupId="<%= displayStyleGroupId %>"
			refreshURL="<%= PortalUtil.getCurrentURL(request) %>"
			showEmptyOption="<%= true %>"
		/>
		</div>
	</aui:fieldset>
	<aui:button type="submit" name="submit"></aui:button>
</aui:form>