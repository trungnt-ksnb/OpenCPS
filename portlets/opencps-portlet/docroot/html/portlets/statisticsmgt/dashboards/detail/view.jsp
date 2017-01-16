
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
			govCode, domainCode, level, notNullGov, notNullDomain);

	System.out.println("###########################################################" +
		govCode);
	
	System.out.println("###########################################################" +
			level);

	long collectionId = 0;
	
	try{
		DictCollection dictCollection = null;
		if(filterKey.equals("gov")){
			dictCollection = DictCollectionLocalServiceUtil.getDictCollection(scopeGroupId, PortletPropsValues.DATAMGT_MASTERDATA_GOVERNMENT_AGENCY);
		}else{
			dictCollection = DictCollectionLocalServiceUtil.getDictCollection(scopeGroupId, PortletPropsValues.DATAMGT_MASTERDATA_SERVICE_DOMAIN);
		}
		
		collectionId = dictCollection.getDictCollectionId();
		
	}catch(Exception e){
		
	}
	
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
		<%
			JSONArray jsonArray =
			DossiersStatisticsServiceUtil.statisticsDossierByCode(collectionId,
				dossiersStatistics, null, filterKey, currentMonth, currentYear,
				locale);
	
			String strJSON = jsonArray.toString();
		
			System.out.println("###########################################################" +
				jsonArray);
	
		%>
		<div class="widget-wrapper">
			<div id="<portlet:namespace/>statistics" class="dashboard-statistics"></div>
		</div>
		<c:choose>
			<c:when test='<%=chartType.equals("table") %>'>
				<script type="text/javascript">
					var strJSON<%=portletName %> = '<%=strJSON%>';
					var codeType<%=portletName %> = '<%=filterKey%>'
					var objects<%=portletName %> = JSON.parse(strJSON<%=portletName %>);
					
					var data<%=portletName %> = [];
					
					for(var i = 0; i < objects<%=portletName %>.length; i++){
						var json = objects<%=portletName %>[i];
						var labels = json.labels;
						var values = json.values;
						var columnNames = [];
						if(codeType<%=portletName %> === 'gov'){
							columnNames.push(Liferay.Language.get("gov-name"))
						}else{
							columnNames.push(Liferay.Language.get("domain-name"));
						}
						columnNames = columnNames.concat(labels);
						var columns = [];
						var rowCss = "custom-cell even";
						if(i % 2 != 0){
							rowCss = "custom-cell odd"
						}
						for(var c = 0; c < columnNames.length; c ++){
							var cellContainer = '';
							if(c == 0){
								cellContainer = "<span class='custom-cell' onclick='viewDetail(&quot;" + json.code +"&quot;)'>{value}</span>";
							}
							
							cellContainer = "<span class='" + rowCss + "' onclick='viewDetail(&quot;" + json.code +"&quot;)'>{value}</span>";
							
							var cell = {
								key : columnNames[c],
								formatter: "<span class='" + rowCss + "' onclick='viewDetail(&quot;" + json.code +"&quot;)'>{value}</span>",
								allowHTML : true,
								sortable: true
							}
							
							columns.push(cell);
						}
						
						var rowData = {};
						rowData[columnNames[0]] = json.name;
						for(var c = 0; c < values.length; c ++){
							rowData[labels[c]] = values[c];
						}
						
						data<%=portletName %>.push(rowData);
					}
					
					AUI().use(
					  'aui-datatable',
					  function(A) {
					   
					    new A.DataTable(
					      {
					        columnset: columns,
					        recordset: data<%=portletName %>,
					       /*  plugins: [
			                  {
			                    cfg: {
			                      highlightRange: false
			                    },
			                    fn: A.Plugin.DataTableHighlight
			                  }
			                ] */
					      }
					    ).render('#<portlet:namespace/>statistics');
					  }
					);
					
					function viewDetail(code){
						
					}
				</script>
			</c:when>
			<c:when test='<%=chartType.equals("bar") %>'>
				<script type="text/javascript">
					var strJSON<%=portletName %> = '<%=strJSON%>';
					var codeType<%=portletName %> = '<%=filterKey%>'
					var objects<%=portletName %> = JSON.parse(strJSON<%=portletName %>);
					
					var data<%=portletName %> = [];
					var xValues = [];
					var yValues = [];
					var names = [];
					for(var i = 0; i < objects<%=portletName %>.length; i++){
						var json = objects<%=portletName %>[i];
						var labels = json.labels;
						var values = json.values;
						var code = json.code;
						var name = json.name;
						xValues.push(code);
						yValues.push(values[0]);
						names.push(name);
					}
					
					for(var i = 0; i < xValues.length; i++){
					
						var trace = {
							x: xValues[i],
						  	y: yValues[i],
						  	name: names[i],
						  	type: 'bar'	
						};
						data<%=portletName %>.push(trace);
					}
					
					console.log(data<%=portletName %>);

					var layout<%=portletName %> = {
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

					Plotly.newPlot('<portlet:namespace/>statistics', data<%=portletName %>, layout<%=portletName %>);

				
				</script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
					var strJSON<%=portletName %> = '<%=strJSON%>';
					
					var objects<%=portletName %> = JSON.parse(strJSON<%=portletName %>);
					
					var data<%=portletName %> = [];
					
					var ultimateColors = [['rgb(244, 98, 66)', 'rgb(8, 142, 62)', 'rgb(3, 51, 122)', 'rgb(247, 4, 4)', 'rgb(239, 247, 4)', 'rgb(247, 146, 4)']];
					
					var delta  = 1/objects<%=portletName %>.length;
					
					for(var i = 0; i < objects<%=portletName %>.length; i++){
						
						var json = objects<%=portletName %>[i];
						var lOffsetX = (i)*delta;
						var uOffsetX = lOffsetX + delta * 0.9;
						
						var item = {
							  title: json.code,
							  values: json.values,
							  labels: json.labels,
							  type: 'pie',
							  name: json.name,
							  marker: {
							    colors: ultimateColors[0]
							  },
							  domain: {
							    x: [lOffsetX, uOffsetX],
							    y: [0, 1]
							  },
							  hoverinfo: 'label+percent+name'
						}
						data<%=portletName %>.push(item);
					}
					
					var layout<%=portletName %> = {
						title: '<%=chartTitle%>',
						height: 400
					};
					
					Plotly.newPlot('<portlet:namespace/>statistics', data<%=portletName %>, layout<%=portletName %>);
				</script>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

