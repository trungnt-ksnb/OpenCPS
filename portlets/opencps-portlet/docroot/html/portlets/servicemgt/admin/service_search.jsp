<%@page import="org.opencps.servicemgt.search.ServiceSearchTerms"%>
<%@page import="org.opencps.servicemgt.search.ServiceSearch"%>
<%@page import="org.opencps.servicemgt.search.TemplateSearchTerms"%>
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

<%@ include file="../init.jsp" %>

<%
	ServiceSearch searchContainerService = (ServiceSearch)request.getAttribute("liferay-ui:search:searchContainer");

	ServiceSearchTerms searchTerms = (ServiceSearchTerms) searchContainerService.getSearchTerms();
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= searchTerms %>"
	id="toggle_id_service_search"
	autoFocus="<%= true %>"
>

	<aui:fieldset>
		<aui:row>
			
			<aui:col width="30">
					<aui:input name="<%= searchTerms.getKeywords() %>" size="20" value="<%= searchTerms.getKeywords() %>" />
			</aui:col>
			
			<aui:col width="30">
					<aui:input name="<%= searchTerms.getKeywords() %>" size="20" value="<%= searchTerms.getKeywords() %>" />
			</aui:col>
			
		</aui:row>
	</aui:fieldset>

</liferay-ui:search-toggle>
