<%@page import="org.opencps.util.WebKeys"%>
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

<%
	HttpServletRequest r = PortalUtil.getHttpServletRequest(renderRequest);
	String responseCode = PortalUtil.getOriginalServletRequest(r).getParameter("response_code");
	String trans_id = PortalUtil.getOriginalServletRequest(r).getParameter("trans_id");
	String secure_hash = PortalUtil.getOriginalServletRequest(r).getParameter("secure_hash");
	Map<String, String[]> params = PortalUtil.getOriginalServletRequest(r).getParameterMap();
    Map<String, String> fields = new HashMap<String, String>();
	
	for (Map.Entry<String, String[]> entry : params.entrySet())
	{
		fields.put(entry.getKey(), entry.getValue()[0]);
	}
	
	String command = PortalUtil.getOriginalServletRequest(r).getParameter("command");
	String merchant_trans_id = PortalUtil.getOriginalServletRequest(r).getParameter("merchant_trans_id");
	String merchant_code = PortalUtil.getOriginalServletRequest(r).getParameter("merchant_code");
	String response_code = PortalUtil.getOriginalServletRequest(r).getParameter("response_code");
	String good_code = PortalUtil.getOriginalServletRequest(r).getParameter("good_code");
	String net_cost = PortalUtil.getOriginalServletRequest(r).getParameter("net_cost");
	String ship_fee = PortalUtil.getOriginalServletRequest(r).getParameter("ship_fee");
	String tax = PortalUtil.getOriginalServletRequest(r).getParameter("tax");
	String service_code = PortalUtil.getOriginalServletRequest(r).getParameter("service_code");
	String currency_code = PortalUtil.getOriginalServletRequest(r).getParameter("currency_code");
	String bank_code = PortalUtil.getOriginalServletRequest(r).getParameter("bank_code");
	String desc_1 = PortalUtil.getOriginalServletRequest(r).getParameter("desc_1");
	String desc_2 = PortalUtil.getOriginalServletRequest(r).getParameter("desc_2");
	String desc_3 = PortalUtil.getOriginalServletRequest(r).getParameter("desc_3");
	String desc_4 = PortalUtil.getOriginalServletRequest(r).getParameter("desc_4");
	String desc_5 = PortalUtil.getOriginalServletRequest(r).getParameter("desc_5");
	String p_p_id = PortalUtil.getOriginalServletRequest(r).getParameter("p_p_id");
	String p_p_lifecycle = PortalUtil.getOriginalServletRequest(r).getParameter("p_p_id");
	KeyPay keyPay = new KeyPay(PortalUtil.getOriginalServletRequest(r));
	
	long dossierId = GetterUtil.getLong(merchant_trans_id);

%>

<portlet:renderURL var="backURL">
	<portlet:param name="mvcPath"
		value="/html/portlets/paymentmgt/frontoffice/frontofficepaymentlist.jsp" />	
</portlet:renderURL>

<liferay-ui:header
	backURL="<%= backURL.toString() %>"
	title="payment-list"
/>

<c:choose>
	<c:when test="<%= responseCode != null && responseCode.equals(\"00\") %>">
		<div class="alert alert-success">
			<liferay-ui:message key="keypay-success"></liferay-ui:message>
		</div>
		<%
			String receptionNo = good_code;
			Dossier dossier = null;
			try {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			}
			catch (SystemException e) {
				
			}
			ServiceInfo serviceInfo = null;
			try {
				if (dossier != null)
					serviceInfo = ServiceInfoLocalServiceUtil.getServiceInfo(dossier.getServiceInfoId());
			}
			catch (NoSuchServiceInfoException e) {
				
			}
			PaymentFile paymentFile = null;
			try {
				paymentFile = PaymentFileLocalServiceUtil.getPaymentFileByMerchantResponse(Long.parseLong(merchant_trans_id), good_code, Double.parseDouble(net_cost));
				if (paymentFile != null) {
					PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.getPaymentConfigByGovAgency(scopeGroupId, paymentFile.getGovAgencyOrganizationId());
					if (paymentConfig != null) {
						if (keyPay.checkSecureHash(secure_hash)) {
							paymentFile.setPaymentStatus(PaymentMgtUtil.PAYMENT_STATUS_APPROVED);
							paymentFile.setPaymentMethod(WebKeys.PAYMENT_METHOD_KEYPAY);
							PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
						}
					}
				}
			}
			catch (SystemException e) {
				
			}
		%>
		<c:if test="<%= dossier != null && paymentFile != null %>">
		<div class="lookup-result">
			<table>
				<tr style="background: #fae5d3;">
					<td class="col-left"><liferay-ui:message key="reception-no"></liferay-ui:message></td>
					<td class="col-right"><%= dossier != null ? dossier.getReceptionNo() : "" %></td>
				</tr>
				<tr>
					<td class="col-left"><liferay-ui:message key="service-name"></liferay-ui:message></td>
					<td class="col-right"><%= serviceInfo != null ? serviceInfo.getServiceName() : "" %></td>
				</tr>
				<tr>
					<td class="col-left"><liferay-ui:message key="administration-name"></liferay-ui:message></td>
					<td class="col-right">
						<c:if test="<%= dossier != null %>">
							<%= dossier.getGovAgencyName() %>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="col-left"><liferay-ui:message key="payment-name"></liferay-ui:message></td>
					<td class="col-right"><%= paymentFile != null ? paymentFile.getPaymentName() : "" %></td>
				</tr>
				<tr>
					<td class="col-left"><liferay-ui:message key="amount"></liferay-ui:message></td>
					<td class="col-right"><%= NumberFormat.getInstance(new Locale("vi", "VN")).format(paymentFile.getAmount()) %></td>
				</tr>
				<tr>
					<td class="col-left"><liferay-ui:message key="trans_id"></liferay-ui:message></td>
					<td class="col-right"><%= trans_id %></td>
				</tr>
			</table>	
		</div>	
		</c:if>
	</c:when>
	<c:when test="<%= responseCode != null && responseCode.equals(\"10\") %>">
		<div class="alert alert-error">
			<liferay-ui:message key="good-code-not-valid"></liferay-ui:message>
		</div>
	</c:when>
	<c:otherwise>
		<%
			String receptionNo = good_code;

			PaymentFile paymentFile = null;
			try {
				paymentFile = PaymentFileLocalServiceUtil.getPaymentFileByGoodCode(scopeGroupId, good_code);
				paymentFile.setPaymentStatus(PaymentMgtUtil.PAYMENT_STATUS_REJECTED);
				paymentFile.setPaymentMethod(WebKeys.PAYMENT_METHOD_KEYPAY);
				PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
			}
			catch (SystemException e) {
				
			}
			
		%>
	</c:otherwise>
</c:choose>