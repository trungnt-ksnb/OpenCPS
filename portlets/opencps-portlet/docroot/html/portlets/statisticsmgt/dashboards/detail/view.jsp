
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

<%@page import="org.opencps.util.PortletPropsValues"%>
<%@page import="org.opencps.datamgt.service.DictCollectionLocalServiceUtil"%>
<%@page import="org.opencps.datamgt.model.DictCollection"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.opencps.statisticsmgt.service.DossiersStatisticsLocalServiceUtil"%>
<%@page import="org.opencps.statisticsmgt.model.DossiersStatistics"%>
<%@page import="org.opencps.statisticsmgt.service.DossiersStatisticsServiceUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>

<%@ include file="init.jsp" %>

<%
	
	List<DossiersStatistics> dossiersStatistics =
		DossiersStatisticsLocalServiceUtil.getStatsByGovAndDomain(scopeGroupId, startMonth, startYear, period, 
			govCodes, domainCodes, level);
	
	JSONArray jsonArray = StatisticsUtil.renderData(scopeGroupId, dossiersStatistics, fieldDatasShemas,
		filterKey, currentMonth, currentYear, locale);
	
%>
<c:choose>
	<c:when test="<%=portletDisplayDDMTemplateId > 0 %>">
		<%
			Map<String, Object> contextObjects = new HashMap<String, Object>();
	
			contextObjects.put("scopeGroupId", new Long(scopeGroupId));
		%>
		<%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, portletDisplayDDMTemplateId, dossiersStatistics, contextObjects) %>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>

