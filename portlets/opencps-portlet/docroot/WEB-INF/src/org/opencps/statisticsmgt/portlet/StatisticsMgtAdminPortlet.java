package org.opencps.statisticsmgt.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.opencps.statisticsmgt.model.GovagencyLevel;
import org.opencps.statisticsmgt.service.DossiersStatisticsLocalServiceUtil;
import org.opencps.statisticsmgt.service.GovagencyLevelLocalServiceUtil;
import org.opencps.statisticsmgt.util.StatisticsUtil;
import org.opencps.statisticsmgt.util.StatisticsUtil.StatisticsFieldNumber;
import org.opencps.util.PortletConstants;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class StatisticsPortlet
 */
public class StatisticsMgtAdminPortlet extends MVCPortlet {

	public void doStatistics(ActionRequest actionRequest,
			ActionResponse actionResponse) {
		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		int month = ParamUtil.getInteger(actionRequest, "month");
		int year = ParamUtil.getInteger(actionRequest, "year");

		int firstMonth = month;
		int lastMonth = month;
		if (month == 0) {
			firstMonth = 1;
			lastMonth = 12;
		}

		_log.info("firstMonth " + firstMonth + "|" + "lastMonth " + lastMonth
				+ "|");

		List<Integer> months = DossiersStatisticsLocalServiceUtil.getMonths(
				groupId, year);

		_log.info("########################## " + months.size());

		_log.info("########################## " + StringUtil.merge(months));

		List total = new ArrayList<Object>();

		try {
			for (int m = firstMonth; m <= lastMonth; m++) {

				if (months.contains(m) && m < lastMonth) {
					continue;
				}

				List receiveds1 = DossiersStatisticsLocalServiceUtil
						.generalStatistics(
								groupId,
								m,
								year,
								StatisticsFieldNumber.ReceivedNumber.toString(),
								-1);
				List ontimes1 = DossiersStatisticsLocalServiceUtil
						.generalStatistics(groupId, m, year,
								StatisticsFieldNumber.OntimeNumber.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_ONTIME);
				List overtimes1 = DossiersStatisticsLocalServiceUtil
						.generalStatistics(
								groupId,
								m,
								year,
								StatisticsFieldNumber.OvertimeNumber.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_LATE);
				List processings1 = DossiersStatisticsLocalServiceUtil
						.generalStatistics(groupId, m, year,
								StatisticsFieldNumber.ProcessingNumber
										.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_UNEXPIRED);
				List delayings1 = DossiersStatisticsLocalServiceUtil
						.generalStatistics(
								groupId,
								m,
								year,
								StatisticsFieldNumber.DelayingNumber.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_EXPIRED);

				if (receiveds1 != null) {
					total.addAll(receiveds1);
				}

				if (ontimes1 != null) {
					total.addAll(ontimes1);
				}

				if (overtimes1 != null) {
					total.addAll(overtimes1);
				}

				if (processings1 != null) {
					total.addAll(processings1);
				}

				if (delayings1 != null) {
					total.addAll(delayings1);
				}

				List receiveds2 = DossiersStatisticsLocalServiceUtil
						.statisticsByDomain(
								groupId,
								m,
								year,
								StatisticsFieldNumber.ReceivedNumber.toString(),
								-1);
				List ontimes2 = DossiersStatisticsLocalServiceUtil
						.statisticsByDomain(groupId, m, year,
								StatisticsFieldNumber.OntimeNumber.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_ONTIME);
				List overtimes2 = DossiersStatisticsLocalServiceUtil
						.statisticsByDomain(
								groupId,
								m,
								year,
								StatisticsFieldNumber.OvertimeNumber.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_LATE);
				List processings2 = DossiersStatisticsLocalServiceUtil
						.statisticsByDomain(groupId, m, year,
								StatisticsFieldNumber.ProcessingNumber
										.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_UNEXPIRED);
				List delayings2 = DossiersStatisticsLocalServiceUtil
						.statisticsByDomain(
								groupId,
								m,
								year,
								StatisticsFieldNumber.DelayingNumber.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_EXPIRED);

				if (receiveds2 != null) {
					total.addAll(receiveds2);
				}

				if (ontimes2 != null) {
					total.addAll(ontimes2);
				}

				if (overtimes2 != null) {
					total.addAll(overtimes2);
				}

				if (processings2 != null) {
					total.addAll(processings2);
				}

				if (delayings2 != null) {
					total.addAll(delayings2);
				}

				List receiveds3 = DossiersStatisticsLocalServiceUtil
						.statisticsByGovAgency(
								groupId,
								m,
								year,
								StatisticsFieldNumber.ReceivedNumber.toString(),
								-1);
				List ontimes3 = DossiersStatisticsLocalServiceUtil
						.statisticsByGovAgency(groupId, m, year,
								StatisticsFieldNumber.OntimeNumber.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_ONTIME);
				List overtimes3 = DossiersStatisticsLocalServiceUtil
						.statisticsByGovAgency(
								groupId,
								m,
								year,
								StatisticsFieldNumber.OvertimeNumber.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_LATE);
				List processings3 = DossiersStatisticsLocalServiceUtil
						.statisticsByGovAgency(groupId, m, year,
								StatisticsFieldNumber.ProcessingNumber
										.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_UNEXPIRED);
				List delayings3 = DossiersStatisticsLocalServiceUtil
						.statisticsByGovAgency(
								groupId,
								m,
								year,
								StatisticsFieldNumber.DelayingNumber.toString(),
								PortletConstants.DOSSIER_DELAY_STATUS_EXPIRED);

				if (receiveds3 != null) {
					total.addAll(receiveds3);
				}

				if (ontimes3 != null) {
					total.addAll(ontimes3);
				}

				if (overtimes3 != null) {
					total.addAll(overtimes3);
				}

				if (processings3 != null) {
					total.addAll(processings3);
				}

				if (delayings3 != null) {
					total.addAll(delayings3);
				}

			}
		} catch (Exception e) {
			_log.error(e);
		}

		if (total != null && !total.isEmpty()) {
			StatisticsUtil.getDossiersStatistics(total);
			// List fakeData = StatisticsUtil.fakeData();
			// StatisticsUtil.getDossiersStatistics(fakeData);
		}
	}

	/**
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 */
	public void updateAdministrationLevel(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException {
		String redirectURL = ParamUtil.getString(actionRequest, "redirectURL");
		String[] govCodes = ParamUtil.getParameterValues(actionRequest,
				"govCode");

		String[] levels = ParamUtil.getParameterValues(actionRequest, "level");
		if (govCodes != null && levels != null
				&& levels.length == govCodes.length && govCodes.length > 0) {
			try {
				ServiceContext serviceContext = ServiceContextFactory
						.getInstance(actionRequest);
				for (int i = 0; i < govCodes.length; i++) {
					GovagencyLevelLocalServiceUtil.updateGovagencyLevel(
							serviceContext.getCompanyId(),
							serviceContext.getScopeGroupId(),
							serviceContext.getUserId(), govCodes[i],
							GetterUtil.getInteger(levels[i]));
				}
			} catch (Exception e) {
				_log.error(e);
			} finally {
				if (Validator.isNotNull(redirectURL)) {
					actionResponse.sendRedirect(redirectURL);
				}
			}

		}
	}

	private void validateStatistic(int month, int year, String statisticsBy) {
		// TODO
	}

	private Log _log = LogFactoryUtil.getLog(StatisticsMgtAdminPortlet.class
			.getName());
}
