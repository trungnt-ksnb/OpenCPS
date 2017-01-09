<%@page import="org.opencps.statisticsmgt.util.StatisticsUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="org.opencps.statisticsmgt.service.DossiersStatisticsLocalServiceUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.opencps.statisticsmgt.model.DossiersStatistics"%>
<%@page import="java.util.List"%>

<%@ include file="init.jsp" %>

<%
	List<DossiersStatistics> dossiersStatistics = new ArrayList<DossiersStatistics>();

	try {
		dossiersStatistics = DossiersStatisticsLocalServiceUtil
				.getStatatistic(scopeGroupId, startMonth, startYear, period, govCode, domainCode, 0);
	} catch (Exception e) {

	}

	JSONArray statistics = StatisticsUtil.statisticsDossierMonthly(
			dossiersStatistics, locale);

	String strJSON = statistics.toString();
%>
<div class="widget-wrapper">
	<div id="<portlet:namespace/>statistics"></div>
</div>

<script type="text/javascript">
	var strJSON = '<%=strJSON%>';
	var objects = JSON.parse(strJSON);
	var data = [];
	for(var i = 0; i < objects.length; i++){
		var json = objects[i];
		var trace = {
			x: json.months,
		  	y: json.values,
		  	name: json.name,
		  	type: 'bar'	
		};
		data.push(trace);
	}
	

	var layout = {
		barmode: 'group',
		bargap: 0.15,
		bargroupgap: 0.1,
		
		title: '<%=chartTitle%>',
		yaxis: {
			title: '<%=xaxisUnit%>',
			tickfont: {
		      size: 14,
		      color: 'rgb(107, 107, 107)'
		    }
		},
		xaxis: {
		    title: '<%=yaxisUnit%>',
		    titlefont: {
		      size: 16,
		      color: 'rgb(107, 107, 107)'
		    },
		    tickfont: {
		      size: 14,
		      color: 'rgb(107, 107, 107)'
		    }
		}
	};

	Plotly.newPlot('<portlet:namespace/>statistics', data, layout);

</script>
