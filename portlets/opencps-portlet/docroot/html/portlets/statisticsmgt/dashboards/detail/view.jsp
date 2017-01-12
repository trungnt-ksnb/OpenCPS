
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
			govCode, domainCode, level, notNullGov, notNullDomain);

	JSONArray jsonArray =
		DossiersStatisticsServiceUtil.statisticsDossierByCode(
			dossiersStatistics, null, filterKey, currentMonth, currentYear,
			locale);

	String strJSON = jsonArray.toString();

	System.out.println("###########################################################" +
		jsonArray);
%>
<div class="widget-wrapper">
	<div id="<portlet:namespace/>statistics"></div>
</div>
<script>
	var strJSON = '<%=strJSON%>';
	
	var objects = JSON.parse(strJSON);
	
	var data = [];
	
	var ultimateColors = [['rgb(244, 98, 66)', 'rgb(8, 142, 62)', 'rgb(3, 51, 122)', 'rgb(247, 4, 4)', 'rgb(239, 247, 4)', 'rgb(247, 146, 4)']];
	
	var delta  = 1/objects.length;
	
	for(var i = 0; i < objects.length; i++){
		
		var json = objects[i];
		var lOffsetX = (i)*delta;
		var uOffsetX = lOffsetX + delta * 0.9;
		
		var item = {
			  title: json.code,
			  values: json.values,
			  labels: json.labels,
			  type: 'pie',
			  name: json.code,
			  marker: {
			    colors: ultimateColors[0]
			  },
			  domain: {
			    x: [lOffsetX, uOffsetX],
			    y: [0, 1]
			  },
			  hoverinfo: 'label+percent+name'
		}
		data.push(item);
	}
	
	var layout = {
		title: '<%=chartTitle%>',
		height: 400
	};
	
	Plotly.newPlot('<portlet:namespace/>statistics', data, layout);
</script>