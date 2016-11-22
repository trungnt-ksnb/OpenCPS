<%@page import="org.opencps.dossiermgt.util.DossierMgtUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
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
<%@ taglib prefix="opencps-ui" uri="/WEB-INF/tld/opencps-ui.tld"%>
<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@ include file="../init.jsp"%>

<%
	String tabs1 = ParamUtil.getString(request, "tabs1", DossierMgtUtil.TOP_TABS_DOSSIER_MONITORING_SEARCH);

	PortletURL viewMonitoringSearchURL = renderResponse.createRenderURL();
	viewMonitoringSearchURL.setParameter("mvcPath", "/html/portlets/dossiermgt/monitoring/dossiermonitoringsearch.jsp");
	viewMonitoringSearchURL.setParameter("tabs1", DossierMgtUtil.TOP_TABS_DOSSIER_MONITORING_SEARCH);
	
	PortletURL dossierFileSearchUrl = renderResponse.createRenderURL();
	dossierFileSearchUrl.setParameter("mvcPath", "/html/portlets/dossiermgt/monitoring/dossiermonitoringdossierfilelist.jsp");
	dossierFileSearchUrl.setParameter("tabs1", DossierMgtUtil.TOP_TABS_DOSSIER_MONITORING_DOSSIER_FILE_LIST);
	
	PortletURL viewMonitoringServiceURL = renderResponse.createRenderURL();
	viewMonitoringServiceURL.setParameter("mvcPath", "/html/portlets/dossiermgt/monitoring/dossiermonitoringservice.jsp");
	viewMonitoringServiceURL.setParameter("tabs1", DossierMgtUtil.TOP_TABS_DOSSIER_MONITORING_SERVICE);
%>

<portlet:actionURL name="searchAction" var="searchUrl"></portlet:actionURL>

<portlet:actionURL name="searchServiceAction" var="serviceUrl"></portlet:actionURL>

<%
	String keySelect = DossierMgtUtil.TOP_TABS_DOSSIER_MONITORING_SEARCH /*+ "," + DossierMgtUtil.TOP_TABS_DOSSIER_MONITORING_DOSSIER_FILE_LIST */  + "," + DossierMgtUtil.TOP_TABS_DOSSIER_MONITORING_SERVICE;
	String urlSelect = viewMonitoringSearchURL /*+ "," + dossierFileSearchUrl */ + "," + viewMonitoringServiceURL;
%>
<aui:nav-bar cssClass="opencps-toolbar custom-toolbar">
	<aui:nav id="toolbarContainer" cssClass="nav-display-style-buttons pull-left" >
		
	</aui:nav>
	<c:choose>
		<c:when test="<%= tabs1.equals(DossierMgtUtil.TOP_TABS_DOSSIER_MONITORING_SEARCH) %>">
			<aui:nav-bar-search cssClass="pull-left" style="width: 98%">
				<div class="form-search">
					<aui:form action="<%= searchUrl %>" method="post" name="fm">
						<div class="toolbar_search_input" style="width: 98%">
							<aui:row>
								<aui:col>
									<label>
										<liferay-ui:message key="keywords"/>
									</label>
									<opencps-ui:input-search 
										id="keywords"
										name="keywords"
										useNamespace="<%=true %>"
										cssClass="input-append home-bb-search"
										title='<%= LanguageUtil.get(portletConfig, locale, "keywords") %>'
										placeholder="<%= LanguageUtil.get(pageContext, \"dossier-search-keywords-home\") %>" 
										keySelect="<%=keySelect %>"
										urlSelect="<%=urlSelect %>"
										currentTab="<%=tabs1 %>"
									/>
								</aui:col>
							</aui:row>
						</div>
					</aui:form>
				</div>			
			</aui:nav-bar-search>
		</c:when>
		<c:when test="<%= tabs1.equals(DossierMgtUtil.TOP_TABS_DOSSIER_MONITORING_DOSSIER_FILE_LIST) %>">
			<aui:nav-bar-search cssClass="pull-left" style="width: 98%">
				<div class="form-search">
					<aui:form action="<%= dossierFileSearchUrl %>" method="post" name="fm">
						<div class="toolbar_search_input" style="width: 98%">
							<aui:row>
								<aui:col width="65">
									<label>
										<liferay-ui:message key="keywords"/>
									</label>
									<opencps-ui:input-search 
										id="keywords"
										name="keywords"
										useNamespace="<%=true %>"
										cssClass="input-append home-bb-search"
										title='<%= LanguageUtil.get(portletConfig, locale, "keywords") %>'
										placeholder="<%= LanguageUtil.get(pageContext, \"dossier-search-keywords\") %>" 
										keySelect="<%=keySelect %>"
										urlSelect="<%=urlSelect %>"
										currentTab="<%=tabs1 %>"
									/>
								</aui:col>
							</aui:row>
						</div>
					</aui:form>
				</div>			
			</aui:nav-bar-search>
		</c:when>
		<c:when test="<%= tabs1.equals(DossierMgtUtil.TOP_TABS_DOSSIER_MONITORING_SERVICE) %>">
			<aui:nav-bar-search cssClass="pull-left" style="width: 98%">
				<div class="form-search">
					<aui:form action="<%= serviceUrl %>" method="post" name="fm">
						<div class="toolbar_search_input" style="width: 98%">
							<aui:row>
								<aui:col width="65">
									<label>
										<liferay-ui:message key="keywords"/>
									</label>
									<opencps-ui:input-search 
										id="keywords"
										name="keywords"
										useNamespace="<%=true %>"
										cssClass="input-append home-bb-search"
										title='<%= LanguageUtil.get(portletConfig, locale, "keywords") %>'
										placeholder="<%= LanguageUtil.get(pageContext, \"dossier-search-keywords\") %>" 
										keySelect="<%=keySelect %>"
										urlSelect="<%=urlSelect %>"
										currentTab="<%=tabs1 %>"
									/>
								</aui:col>
							</aui:row>
						</div>
					</aui:form>
				</div>			
			</aui:nav-bar-search>
		</c:when>
	</c:choose>
</aui:nav-bar>
<%!
	private Log _log = LogFactoryUtil.getLog("html.portlets.dossiermgt.monitoring.toolbar.jsp");
%>