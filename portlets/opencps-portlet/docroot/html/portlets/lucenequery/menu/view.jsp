<%@page import="com.liferay.portal.kernel.search.SearchEngineUtil"%>
<%@page import="com.liferay.portal.kernel.search.Hits"%>
<%@page import="com.liferay.portal.kernel.search.SearchContextFactory"%>
<%@page import="com.liferay.portal.kernel.search.SearchContext"%>
<%@page import="com.liferay.portal.kernel.search.BooleanQuery"%>
<%@ include file="../init.jsp"%>

<c:if test="<%=luceneMenuSchemas != null %>">
	<ul>
	<%
		SearchContext searchContext = SearchContextFactory.getInstance(request);
	
		for(LuceneMenuSchema luceneMenuSchema : luceneMenuSchemas){
			String pattern = luceneMenuSchema.getPattern();
			Hits hits = null;
			try{
				BooleanQuery booleanQuery = LuceneQueryUtil.buildQuerySearch(pattern, luceneMenuSchema.getParams(), searchContext);
				hits = SearchEngineUtil.search(searchContext, booleanQuery);
			}catch(Exception e){
				
			}
			
			%>
				<li class='<%="menu-item level-" + luceneMenuSchema.getLevel()%>'>
					<span class="item-name">
						<%=luceneMenuSchema.getName() %>
					</span>
					<span class="item-value">
						<%=hits != null ? hits.getLength() : "N/A" %>
					</span>
				</li>
			<%
		}
	%>
	</ul>
</c:if>