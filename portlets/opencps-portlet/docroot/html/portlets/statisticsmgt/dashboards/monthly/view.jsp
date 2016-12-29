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
				.getDossiersStatistics(themeDisplay.getScopeGroupId(),
						StringPool.BLANK, StringPool.BLANK,
						currentYear, 0);
	} catch (Exception e) {

	}

	JSONArray statistics = StatisticsUtil.statisticsDossierMonthly(
			dossiersStatistics, locale.getCountry());

	String strJSON = statistics.toString();
%>

<div id="<portlet:namespace/>statistics"></div>

<script type="text/javascript">
	var strJSON = '<%=strJSON%>';
	var json = JSON.parse(strJSON);
	console.log(json);
	var trace1 = {
		  x: ['giraffes', 'orangutans', 'monkeys'],
		  y: [20, 14, 23],
		  name: 'SF Zoo',
		  type: 'bar'
		};

		var trace2 = {
		  x: ['giraffes', 'orangutans', 'monkeys'],
		  y: [12, 18, 29],
		  name: 'LA Zoo',
		  type: 'bar'
		};

		var data = [trace1, trace2];

		var layout = {barmode: 'group'};

		Plotly.newPlot('<portlet:namespace/>statistics', data, layout);

</script>
