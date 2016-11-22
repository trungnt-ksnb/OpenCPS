
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */
%>


<%@ include file="../init.jsp" %>

<%
	//HttpServletRequest originalServletRequest = PortalUtil.getOriginalServletRequest(request);
	/* String type = GetterUtil.getString(request.getParameter("type"), "citizen"); */
	String type = ParamUtil.getString(request, "type", "citizen");
%>

<c:choose>
	<c:when test='<%=type.equals("citizen") %>'>
		<liferay-util:include 
			page="/html/portlets/accountmgt/registration/citizenregistration-ux.jsp" 
			servletContext="<%=application %>" 
		/> 
	</c:when>
	
	<c:when test='<%=type.equals("business") %>'>
		<liferay-util:include 
			page="/html/portlets/accountmgt/registration/businessregistration-ux.jsp" 
			servletContext="<%=application %>" 
		/> 
	</c:when>
	<c:otherwise>
		<liferay-util:include 
			page="/html/portlets/accountmgt/registration/citizenregistration-ux.jsp" 
			servletContext="<%=application %>" 
		/> 
	</c:otherwise>
</c:choose>

