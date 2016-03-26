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
<%@page import="org.opencps.usermgt.model.Employee"%>
<%@page import="com.liferay.portal.model.User"%>
<%@ include file="../init.jsp"%>

<c:choose>
	<c:when test="<%=themeDisplay.isSignedIn() %>">
		<%
			Employee employee = null;
		
			try{
				long mappingUserId = user.getUserId();
				
			}catch(Exception e){
				
			}
		%>

		<aui:form name="fm" action="" method="">
			<%
			
			%>
		</aui:form>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-warrning"><liferay-ui:message key="please-sign-in-to-view-profile"/></div>
	</c:otherwise>
</c:choose>


 