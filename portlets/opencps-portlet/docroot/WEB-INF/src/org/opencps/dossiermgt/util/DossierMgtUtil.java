/**
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */

package org.opencps.dossiermgt.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

import org.opencps.dossiermgt.comparator.DossierTemplateNameComparator;
import org.opencps.dossiermgt.comparator.DossierTemplateNoComparator;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.search.DossierTemplateDisplayTerms;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.paymentmgt.util.PaymentMgtUtil;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author trungnt
 */
public class DossierMgtUtil {

	public static final String TOP_TABS_DOSSIER_TEMPLATE =
		"top_tabs_dossier_template";
	public static final String TOP_TABS_DOSSIER_PART = "top_tabs_dossier_part";
	public static final String TOP_TABS_SERVICE_CONFIG =
		"top_tabs_service_config";
	public static final String DOSSIER_PART_TOOLBAR = "dossierPartToolBar";
	public static final String SERVICE_CONFIG_TOOLBAR = "serviceConfigToolBar";

	public static final String TOP_TABS_DOSSIER = "dossier";
	public static final String TOP_TABS_DOSSIER_FILE = "dossier-file";
	public static final String TOP_TABS_EXTERNAL_DOSSIER = "external-dossier";

	public static final int DOSSIERFILETYPE_INPUT = 1;
	public static final int DOSSIERFILETYPE_OUTPUT = 2;
	public static final int DOSSIERFILETYPE_ALL = 0;

	public static final int DOSSIERFILEMARK_BAN_CHINH = 1;
	public static final int DOSSIERFILEMARK_BAN_CONG_CHUNG = 2;
	public static final int DOSSIERFILEMARK_BAN_CHUP = 3;
	
	public static final String TOP_TABS_DOSSIER_MONITORING_SEARCH =
		"dossier-monitoring-search";
	public static final String TOP_TABS_DOSSIER_MONITORING_DOSSIER_FILE_LIST =
		"dossier-monitoring-dossier-file-list";
	public static final String TOP_TABS_DOSSIER_MONITORING_SERVICE =
		"dossier-monitoring-service";

	public static String[] _DOSSIER_CATEGORY_NAMES = {
		"update-dossier-info"
	};

	/**
	 * @param orderByCol
	 * @param orderByType
	 * @return
	 */
	public static OrderByComparator getDossierTemplateOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals(DossierTemplateDisplayTerms.DOSSIERTEMPLATE_TEMPLATENO)) {
			orderByComparator = new DossierTemplateNoComparator(orderByAsc);
		}
		else if (orderByCol.equals(DossierTemplateDisplayTerms.DOSSIERTEMPLATE_TEMPLATENAME)) {
			orderByComparator = new DossierTemplateNameComparator(orderByAsc);
		}

		return orderByComparator;
	}

	/**
	 * @param partType
	 * @param locale
	 * @return
	 */
	public static String getNameOfPartType(int partType, Locale locale) {

		String partTypeName = StringPool.BLANK;
		switch (partType) {
		case 1:
			partTypeName = LanguageUtil.get(locale, "paper-submited");
			break;
		case 2:
			partTypeName = LanguageUtil.get(locale, "other-papers-group");
			break;
		case 3:
			partTypeName = LanguageUtil.get(locale, "groups-optional");
			break;
		case 4:
			partTypeName = LanguageUtil.get(locale, "own-records");
			break;
		case 5:
			partTypeName = LanguageUtil.get(locale, "papers-results");
			break;
		case 6:
			partTypeName = LanguageUtil.get(locale, "multy-papers-results");
			break;
		default:
			partTypeName = LanguageUtil.get(locale, StringPool.BLANK);
			break;
		}

		return partTypeName;
	}

	public static String getSynchStatus(int synchStatus, Locale locale) {

		String synchStatusName = StringPool.BLANK;
		switch (synchStatus) {
		case 0:
			synchStatusName = LanguageUtil.get(locale, "no-need-to-synch");
			break;
		case 1:
			synchStatusName = LanguageUtil.get(locale, "need-to-synch");
			break;
		case 2:
			synchStatusName = LanguageUtil.get(locale, "synch-success");
			break;
		case 3:
			synchStatusName = LanguageUtil.get(locale, "synch-error");
			break;
		default:
			synchStatusName = LanguageUtil.get(locale, StringPool.BLANK);
			break;
		}

		return synchStatusName;
	}

	/**
	 * @param mode
	 * @param locale
	 * @return
	 */
	public static String getNameOfServiceConfigMode(int mode, Locale locale) {

		String modeName = StringPool.BLANK;
		switch (mode) {
		case 0:
			modeName = LanguageUtil.get(locale, "inactive");
			break;
		case 1:
			modeName = LanguageUtil.get(locale, "front-office");
			break;
		case 2:
			modeName = LanguageUtil.get(locale, "back-office");
			break;
		case 3:
			modeName = LanguageUtil.get(locale, "front-back-office");
			break;
		default:
			modeName = LanguageUtil.get(locale, StringPool.BLANK);
			break;
		}

		return modeName;
	}

	/**
	 * @param dossierpartId
	 * @return
	 */
	public static List<DossierPart> getTreeDossierPart(long dossierpartId) {

		List<DossierPart> dossierPartsResult = new ArrayList<DossierPart>();

		Stack<DossierPart> dossierPartsStack = new Stack<DossierPart>();

		try {
			DossierPart dossierPart =
				DossierPartLocalServiceUtil.getDossierPart(dossierpartId);

			dossierPartsStack.add(dossierPart);

			DossierPart dossierPartIndex = null;

			while (!dossierPartsStack.isEmpty()) {
				dossierPartIndex = dossierPartsStack.pop();

				List<DossierPart> dossierPartsChild =
					new ArrayList<DossierPart>();
				dossierPartsChild =
					DossierPartLocalServiceUtil.getDossierPartsByParentId(dossierPartIndex.getDossierpartId());

				if (!dossierPartsChild.isEmpty()) {

					for (int i = dossierPartsChild.size() - 1; i >= 0; i--) {
						dossierPartsStack.add(dossierPartsChild.get(i));
					}

				}

				dossierPartsResult.add(dossierPartIndex);
			}
			return dossierPartsResult;
		}
		catch (Exception e) {
			_log.error(e);
		}

		return new ArrayList<DossierPart>();
	}
	
	public static String getLoaiGiayToLabel(int value, Locale locale) {

		String statusLabel = StringPool.BLANK;

		switch (value) {
		case DOSSIERFILEMARK_BAN_CHINH:
			statusLabel = LanguageUtil.get(locale, "loai-giay-to-ban-chinh");
			break;
		case DOSSIERFILEMARK_BAN_CONG_CHUNG:
			statusLabel = LanguageUtil.get(locale, "loai-giay-to-ban-cong-chung");
			break;
		case DOSSIERFILEMARK_BAN_CHUP:
			statusLabel = LanguageUtil.get(locale, "loai-giay-to-ban-chup");
			break;
		default:
			statusLabel = StringPool.BLANK;
			break;
		}

		return statusLabel;
	}
	
	private static Log _log =
		LogFactoryUtil.getLog(DossierMgtUtil.class.getName());
}
