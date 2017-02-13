
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="org.opencps.statisticsmgt.bean.FieldDatasShema"%>
<%@page import="org.opencps.statisticsmgt.util.StatisticsUtil"%>
<%@page import="java.util.List"%>
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


<%@ include file="../../init.jsp"%>

<%
	String chartTitle =
		GetterUtil.getString(portletPreferences.getValue(
			"chartTitle", StringPool.BLANK));

	String govCode =
		GetterUtil.getString(portletPreferences.getValue(
			"govCode", StringPool.BLANK));

	String domainCode =
		GetterUtil.getString(portletPreferences.getValue(
			"domainCode", StringPool.BLANK));

	int startMonth =
		GetterUtil.getInteger(portletPreferences.getValue(
			"startMonth", String.valueOf(1)));

	int startYear =
		GetterUtil.getInteger(portletPreferences.getValue(
			"startYear", String.valueOf(1)));

	int period =
		GetterUtil.getInteger(portletPreferences.getValue(
			"period", String.valueOf(1)));

	boolean notNullGov =
		GetterUtil.getBoolean(portletPreferences.getValue(
			"notNullGov", String.valueOf(true)));

	boolean notNullDomain =
		GetterUtil.getBoolean(portletPreferences.getValue(
			"notNullDomain", String.valueOf(false)));

	int level =
		GetterUtil.getInteger(portletPreferences.getValue(
			"level", String.valueOf(0)));

	int domainDeepLevel =
		GetterUtil.getInteger(portletPreferences.getValue(
			"domainDeepLevel", String.valueOf(0)));

	String filterKey =
		GetterUtil.getString(portletPreferences.getValue(
			"filterKey", "gov"));

	String chartType =
		GetterUtil.getString(portletPreferences.getValue(
			"chartType", "pie"));

	String xaxisUnit =
		GetterUtil.getString(portletPreferences.getValue(
			"xaxisUnit", StringPool.BLANK));

	String yaxisUnit =
		GetterUtil.getString(portletPreferences.getValue(
			"yaxisUnit", StringPool.BLANK));

	String[] fields =
		StringUtil.split(preferences.getValue(
			"fields", "received-number"));

	String[] fieldLabels =
		preferences.getValues("fieldLabels", new String[] {
			LanguageUtil.get(locale, "received-number")
		});
	String[] fieldKeys =
		preferences.getValues("fieldKeys", new String[] {
			"rec"
		});
	String[] fieldFormulas =
		preferences.getValues("fieldKeys", new String[] {
			"$receivedNumber"
		});;

	String fieldTotalFormula =
		GetterUtil.getString(portletPreferences.getValue(
			"fieldTotalFormula", StringPool.BLANK));

	String fieldTotalLabel =
		GetterUtil.getString(portletPreferences.getValue(
			"fieldTotalLabel", StringPool.BLANK));

	String fieldTotalKey =
		GetterUtil.getString(portletPreferences.getValue(
			"fieldTotalKey", StringPool.BLANK));

	List<FieldDatasShema> fieldDatasShemas =
		StatisticsUtil.getFieldDatasShemas(
			fieldLabels, fieldKeys, fieldFormulas);
%>

