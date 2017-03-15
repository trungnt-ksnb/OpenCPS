
<%@page import="org.opencps.postal.utils.PostalKeys"%>
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

<%@page import="org.opencps.util.ActionKeys"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.service.permission.PortletPermissionUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp"%>

<%
	
	String[] names = new String[]{PostalKeys.TOP_TABS_POSTALCONFIG, PostalKeys.TOP_TABS_POSTOFFICEMAPPING};

	String value = ParamUtil.getString(request, "tabs1", PostalKeys.TOP_TABS_CODE);
	
	List<String> urls = new ArrayList<String>();
	
	if (PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.VIEW) ) {
		PortletURL viewCitizensURL = renderResponse.createRenderURL();
		viewCitizensURL.setParameter("mvcPath", "/html/portlets/postalmgt/postalconfig/edit_postalconfig.jsp");
		viewCitizensURL.setParameter("tabs1", PostalKeys.TOP_TABS_POSTALCONFIG);
		urls.add(viewCitizensURL.toString());
	}
	
	if (PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.VIEW)) {
		PortletURL viewBusinessesURL = renderResponse.createRenderURL();
		viewBusinessesURL.setParameter("mvcPath", "/html/portlets/postalmgt/postofficemapping/postofficemapping_list.jsp");
		viewBusinessesURL.setParameter("tabs1", PostalKeys.TOP_TABS_POSTOFFICEMAPPING);
		urls.add(viewBusinessesURL.toString());
	}
	
%>
<liferay-ui:tabs
	names="<%= StringUtil.merge(names) %>"
	param="tabs1"
	url0="<%=urls != null && urls.size() > 0 ? urls.get(0): StringPool.BLANK %>"
	url1="<%=urls != null && urls.size() > 1 ? urls.get(1): StringPool.BLANK %>"
/>
