/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.statisticsmgt.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.opencps.statisticsmgt.model.DossiersStatistics;
import org.opencps.statisticsmgt.service.DossiersStatisticsLocalServiceUtil;
import org.opencps.statisticsmgt.service.base.DossiersStatisticsServiceBaseImpl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringPool;

/**
 * The implementation of the dossiers statistics remote service. <p> All custom
 * service methods should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.statisticsmgt.service.DossiersStatisticsService}
 * interface. <p> This is a remote service. Methods of this service are expected
 * to have security checks based on the propagated JAAS credentials because this
 * service can be accessed remotely. </p>
 *
 * @author trungnt
 * @see org.opencps.statisticsmgt.service.base.DossiersStatisticsServiceBaseImpl
 * @see org.opencps.statisticsmgt.service.DossiersStatisticsServiceUtil
 */
public class DossiersStatisticsServiceImpl
	extends DossiersStatisticsServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link org.opencps.statisticsmgt.service.DossiersStatisticsServiceUtil}
	 * to access the dossiers statistics remote service.
	 */

	/**
	 * @param govAgencyCode
	 * @param domainCode
	 * @param year
	 * @return
	 * @throws SystemException
	 */
	@JSONWebService(value = "get-dossierstatistic-by-gc-dc-y", method = "POST")
	public List<DossiersStatistics> getDossiersStatisticsByGC_DC_Y(
		long groupId, String govAgencyCode, String domainCode, int year)
		throws SystemException {

		return dossiersStatisticsLocalService.getDossiersStatisticsByG_GC_DC_Y(
			groupId, govAgencyCode, domainCode, year);
	}

	/**
	 * @param year
	 * @return
	 * @throws SystemException
	 */
	@JSONWebService(value = "statistics-dossier-of-year", method = "POST")
	public JSONArray statisticsDossierOfYear(
		long groupId, int year, String language)
		throws SystemException {

		JSONArray array = JSONFactoryUtil.createJSONArray();

		List<DossiersStatistics> dossiersStatistics =
			new ArrayList<DossiersStatistics>();

		Locale locale = new Locale(language);

		int remainingNumber = 0;
		int receivedNumber = 0;
		int ontimeNumber = 0;
		int overtimeNumber = 0;
		int processingNumber = 0;
		int delayingNumber = 0;

		for (int i = 1; i <= 12; i++) {
			try {
				DossiersStatistics statistics =
					DossiersStatisticsLocalServiceUtil.getDossiersStatisticsByG_GC_DC_M_Y_L(
						groupId, StringPool.BLANK, StringPool.BLANK, i, year, 0);
				dossiersStatistics.add(statistics);

			}
			catch (Exception e) {
				continue;
			}
		}
		if (dossiersStatistics != null && !dossiersStatistics.isEmpty()) {
			for (DossiersStatistics statistics : dossiersStatistics) {
				receivedNumber += statistics.getReceivedNumber();
				ontimeNumber += statistics.getOntimeNumber();
				overtimeNumber += statistics.getOvertimeNumber();
			}

			processingNumber +=
				dossiersStatistics.get(dossiersStatistics.size() - 1).getProcessingNumber();
			delayingNumber +=
				dossiersStatistics.get(dossiersStatistics.size() - 1).getDelayingNumber();
		}

		JSONObject obj1 = JSONFactoryUtil.createJSONObject();
		obj1.put("field", LanguageUtil.get(locale, "remaining-number"));
		obj1.put("value", remainingNumber);

		JSONObject obj2 = JSONFactoryUtil.createJSONObject();
		obj2.put("field", LanguageUtil.get(locale, "received-number"));
		obj2.put("value", receivedNumber);

		JSONObject obj3 = JSONFactoryUtil.createJSONObject();
		obj3.put("field", LanguageUtil.get(locale, "ontime-number"));
		obj3.put("value", ontimeNumber);

		JSONObject obj4 = JSONFactoryUtil.createJSONObject();
		obj4.put("field", LanguageUtil.get(locale, "overtime-number"));
		obj4.put("value", overtimeNumber);

		JSONObject obj5 = JSONFactoryUtil.createJSONObject();
		obj5.put("field", LanguageUtil.get(locale, "processing-number"));
		obj5.put("value", processingNumber);

		JSONObject obj6 = JSONFactoryUtil.createJSONObject();
		obj6.put("field", LanguageUtil.get(locale, "delaying-number"));
		obj6.put("value", delayingNumber);

		array.put(obj1);
		array.put(obj2);
		array.put(obj3);
		array.put(obj4);
		array.put(obj5);
		array.put(obj6);
		return array;
	}

	/**
	 * @param dossiersStatistics
	 * @param names
	 * @param locale
	 * @return
	 */
	@JSONWebService(value = "statistics-dossier-monthly", method = "POST")
	public JSONArray statisticsDossierMonthly(
		List<DossiersStatistics> dossiersStatistics, String[] names,
		Locale locale) {

		JSONArray datas = JSONFactoryUtil.createJSONArray();

		if (names == null || names.length == 0) {
			names =
				new String[] {
					"remaining-number", "received-number", "ontime-number",
					"overtime-number", "processing-number", "delaying-number"
				};
		}

		if (dossiersStatistics != null) {
			for (int n = 0; n < names.length; n++) {
				JSONArray months = JSONFactoryUtil.createJSONArray();
				JSONArray values = JSONFactoryUtil.createJSONArray();
				JSONObject data = JSONFactoryUtil.createJSONObject();
				for (DossiersStatistics statistics : dossiersStatistics) {

					months.put(String.valueOf(statistics.getMonth()));

					if (names[n].equals("remaining-number")) {
						values.put(String.valueOf(statistics.getRemainingNumber()));
					}
					else if (names[n].equals("received-number")) {
						values.put(String.valueOf(statistics.getReceivedNumber()));
					}
					else if (names[n].equals("ontime-number")) {
						values.put(String.valueOf(statistics.getOntimeNumber()));
					}
					else if (names[n].equals("overtime-number")) {
						values.put(String.valueOf(statistics.getOvertimeNumber()));
					}
					else if (names[n].equals("processing-number")) {
						values.put(String.valueOf(statistics.getProcessingNumber()));
					}
					else if (names[n].equals("delaying-number")) {
						values.put(String.valueOf(statistics.getDelayingNumber()));
					}

				}

				data.put("name", LanguageUtil.get(locale, names[n]));
				data.put("months", months);
				data.put("values", values);
				datas.put(data);
			}

		}

		return datas;
	}

	/**
	 * @param dossiersStatistics
	 * @param labels
	 * @param codeType
	 * @param currentMonth
	 * @param currentYear
	 * @param locale
	 * @return
	 */
	@JSONWebService(value = "statistics-dossier-by-code", method = "POST")
	public JSONArray statisticsDossierByCode(
		List<DossiersStatistics> dossiersStatistics, String[] labels,
		String codeType, int currentMonth, int currentYear, Locale locale) {

		JSONArray datas = JSONFactoryUtil.createJSONArray();

		LinkedHashMap<String, List<DossiersStatistics>> statisticLinkedHashMap =
			new LinkedHashMap<String, List<DossiersStatistics>>();

		if (labels == null || labels.length == 0) {
			labels =
				new String[] {
					"remaining-number", "received-number", "ontime-number",
					"overtime-number", "processing-number", "delaying-number"
				};
		}

		if (dossiersStatistics != null) {

			for (DossiersStatistics dossiersStatistic : dossiersStatistics) {
				String code = dossiersStatistic.getGovAgencyCode();
				if (codeType.equals("domain")) {
					code = dossiersStatistic.getDomainCode();
				}

				List<DossiersStatistics> dossiersStatisticsTemp =
					new ArrayList<DossiersStatistics>();
				if (statisticLinkedHashMap.containsKey(code)) {
					dossiersStatisticsTemp = statisticLinkedHashMap.get(code);
				}

				dossiersStatisticsTemp.add(dossiersStatistic);
				statisticLinkedHashMap.put(code, dossiersStatisticsTemp);

			}

			for (Map.Entry<String, List<DossiersStatistics>> entry : statisticLinkedHashMap.entrySet()) {

				JSONObject itemObject = JSONFactoryUtil.createJSONObject();

				JSONArray columnLabels = JSONFactoryUtil.createJSONArray();

				JSONArray values = JSONFactoryUtil.createJSONArray();

				String code = entry.getKey();

				String name = StringPool.BLANK;

				// TODO get dictItem by code

				List<DossiersStatistics> dossiersStatisticsTemp =
					entry.getValue();

				if (dossiersStatisticsTemp != null &&
					!dossiersStatisticsTemp.isEmpty()) {

					int month = dossiersStatisticsTemp.get(0).getMonth();

					itemObject.put("code", code);

					itemObject.put("codeType", codeType);

					itemObject.put("name", name);

					for (int l = 0; l < labels.length; l++) {

						columnLabels.put(LanguageUtil.get(
							locale, labels[l]));

						int value = 0;

						for (DossiersStatistics statistics : dossiersStatisticsTemp) {

							if (labels[l].equals("remaining-number")) {

								if (statistics.getMonth() <= month) {
									value = statistics.getRemainingNumber();
								}
							}
							else if (labels[l].equals("received-number")) {
								value += statistics.getReceivedNumber();
							}
							else if (labels[l].equals("ontime-number")) {
								value += statistics.getOntimeNumber();
							}
							else if (labels[l].equals("overtime-number")) {
								value += statistics.getOvertimeNumber();
							}
							else if (labels[l].equals("processing-number") &&
								statistics.getMonth() == currentMonth &&
								statistics.getYear() == currentYear) {
								value = statistics.getProcessingNumber();
							}
							else if (labels[l].equals("delaying-number") &&
								statistics.getMonth() == currentMonth &&
								statistics.getYear() == currentYear) {
								value = statistics.getDelayingNumber();
							}

						}

						values.put(String.valueOf(value));
					}

					itemObject.put("labels", columnLabels);
					itemObject.put("values", values);
					
					datas.put(itemObject);	
					
				}
			}
		}
		return datas;
	}

}
