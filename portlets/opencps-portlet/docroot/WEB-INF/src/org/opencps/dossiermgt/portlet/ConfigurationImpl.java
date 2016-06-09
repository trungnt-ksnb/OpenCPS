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

package org.opencps.dossiermgt.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.opencps.util.WebKeys;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.PortletURLFactoryUtil;


/**
 * @author khoavd
 *
 */
public class ConfigurationImpl implements ConfigurationAction{

	/* (non-Javadoc)
     * @see com.liferay.portal.kernel.portlet.ConfigurationAction#processAction(javax.portlet.PortletConfig, javax.portlet.ActionRequest, javax.portlet.ActionResponse)
     */
	@Override
	public void processAction(
	    PortletConfig portletConfig, ActionRequest actionRequest,
	    ActionResponse actionResponse)
	    throws Exception {

		long plid = ParamUtil.getLong(actionRequest, "plid");

		PortletURL redirectURL =
		    PortletURLFactoryUtil.create(
		        PortalUtil.getHttpServletRequest(actionRequest),
		        WebKeys.PAYMENT_MGT_PORTLET, plid, PortletRequest.RENDER_PHASE);

		redirectURL.setParameter(
		    "mvcPath",
		    "/html/portlets/paymentmgt/frontoffice/frontofficeconfirmkeypay.jsp");

		String portletResource =
		    ParamUtil.getString(actionRequest, "portletResource");

		PortletPreferences preferences =
		    PortletPreferencesFactoryUtil.getPortletSetup(
		        actionRequest, portletResource);

		preferences.setValue("redirectPaymentURL", redirectURL.toString());
		preferences.store();

		SessionMessages.add(actionRequest, "potlet-config-saved");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.liferay.portal.kernel.portlet.ConfigurationAction#render(javax.portlet
	 * .PortletConfig, javax.portlet.RenderRequest,
	 * javax.portlet.RenderResponse)
	 */
	@Override
	public String render(
	    PortletConfig portletConfig, RenderRequest renderRequest,
	    RenderResponse renderResponse)
	    throws Exception {

		return "/html/portlets/dossiermgt/frontoffice/configuration.jsp";
	}

}
