<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchEntry"%>
<%@page import="org.opencps.usermgt.service.JobPosLocalServiceUtil"%>
<%@page import="org.opencps.usermgt.search.JobPosSearchTerms"%>
<%@page import="org.opencps.usermgt.search.JobPosSearch"%>
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
<%@page import="java.util.ArrayList"%>
<%@page import="org.opencps.usermgt.model.JobPos"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletURL"%>
<%

	PortletURL iteratorURL = renderResponse.createRenderURL();
	iteratorURL.setParameter("mvcPath", templatePath + "jobpos.jsp");
	
	List<JobPos> jobPos = new ArrayList<JobPos>();
	int totalCount = 0;
%>

<liferay-ui:search-container searchContainer="<%= 
	new JobPosSearch(renderRequest ,SearchContainer.DEFAULT_DELTA, iteratorURL) %>">
	
	<liferay-ui:search-container-results>
		<%@include file="/html/portlets/usermgt/admin/result_search_jobpos.jspf"%>
	</liferay-ui:search-container-results>
	<liferay-ui:search-container-row 
		className="org.opencps.usermgt.model.JobPos" 
		modelVar="jobPosSearch" 
		keyProperty="jobposId"
	>
		<%
			row.addText(jobPosSearch.getTitle());
			row.addText(String.valueOf(jobPosSearch.getLeader()));
			row.addJSP("center", SearchEntry.DEFAULT_VALIGN,  templatePath +
				"jobpos-action.jsp", config.getServletContext(),
				request, response);
		%>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator/>
</liferay-ui:search-container>
