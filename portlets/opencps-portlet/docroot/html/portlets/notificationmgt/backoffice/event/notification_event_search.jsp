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
<%@ include file="../../init.jsp" %>

<%
	SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");
	
	DisplayTerms displayTerms = searchContainer.getDisplayTerms();

	List<NotificationStatusConfig> notiStatusConfigs = new ArrayList<NotificationStatusConfig>();

	long notiStatusConfigId = ParamUtil.getLong(request,
			NotificationStatusConfigDisplayTerms.NOTICE_CONFIG_ID, 0);
	
	notiStatusConfigs = NotificationStatusConfigLocalServiceUtil
			.getNotificationStatusConfigs(QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

%>
<liferay-ui:search-toggle
	buttonLabel="Student Search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_noti_vent_search">
	
	<aui:select  name="<%=NotificationStatusConfigDisplayTerms.NOTICE_CONFIG_ID%>"
				label="noti-status-config-id">
				<%
					for (NotificationStatusConfig notiStatusConfig : notiStatusConfigs) {
							
						boolean statusSelect = false;
						
						if(notiStatusConfigId == notiStatusConfig.getNotiStatusConfigId()){
							statusSelect = true;
						}
						
							
				%>

					<aui:option selected="<%=statusSelect%>"
						value="<%=notiStatusConfig.getNotiStatusConfigId()%>"
					>
						<%=notiStatusConfig.getDossierNextStatus()%>
					</aui:option>

				<%
					}
				%>
	</aui:select>
</liferay-ui:search-toggle>