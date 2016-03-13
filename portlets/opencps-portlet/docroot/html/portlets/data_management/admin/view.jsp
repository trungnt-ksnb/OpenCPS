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
	<liferay-ui:tabs names="DictColection,DictVersion,DictItem" refresh="false" tabsValues="DictColection,DictVersion,DictItem" type="pills">
    <liferay-ui:section>
       <liferay-ui:success message="Request Success" key="addCollSuccess"/>
       <liferay-ui:error message="Dict Collection Code Has Existed" key="dictCollCodeErr" />
       <liferay-util:include page="/html/portlets/data_management/admin/edit_dictcollection.jsp" servletContext="<%=application %>" />
	   <liferay-util:include page="/html/portlets/data_management/admin/dictcollection.jsp" servletContext="<%=application %>" />	
    </liferay-ui:section>
    <liferay-ui:section>
        Text for Tab 2.
    </liferay-ui:section>
    <liferay-ui:section>
        Text for Tab 3.
    </liferay-ui:section>
</liferay-ui:tabs>