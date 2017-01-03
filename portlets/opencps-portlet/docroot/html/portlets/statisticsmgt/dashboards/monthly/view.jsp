<%@page import="org.opencps.statisticsmgt.util.StatisticsUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="org.opencps.statisticsmgt.service.DossiersStatisticsLocalServiceUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.opencps.statisticsmgt.model.DossiersStatistics"%>
<%@page import="java.util.List"%>

<%@ include file="../../init.jsp" %>

<%
	List<DossiersStatistics> dossiersStatistics = new ArrayList<DossiersStatistics>();

	try {
		dossiersStatistics = DossiersStatisticsLocalServiceUtil
				.getDossiersStatisticsByG_GC_DC_Y_L(themeDisplay.getScopeGroupId(),
						StringPool.BLANK, StringPool.BLANK,
						currentYear, 0);
	} catch (Exception e) {

	}

	JSONArray statistics = StatisticsUtil.statisticsDossierMonthly(
			dossiersStatistics, locale);

	String strJSON = statistics.toString();
%>

<div id="<portlet:namespace/>statistics"></div>

<script type="text/javascript">
	var strJSON = '<%=strJSON%>';
	var objects = JSON.parse(strJSON);
	var data = [];
	console.log(json);
	for(var i = 0; i < objects.length; i++){
		console.log(objects[i]);
		var json = objects[i];
		console.log(json.name);
		console.log(json.months);
		console.log(json.values);
		var trace = {
			x: json.months,
		  	y: json.values,
		  	name: json.name,
		  	type: 'bar'	
		};
		
		data.push(trace);
	}
	

	var layout = {barmode: 'group'};

	Plotly.newPlot('<portlet:namespace/>statistics', data, layout);

</script>
