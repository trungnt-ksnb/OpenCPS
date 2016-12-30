/**
 * OpenCPS is the open source Core Public Services software Copyright (C)
 * 2016-present OpenCPS community This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software Foundation, either version 3
 * of the License, or any later version. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Affero General Public License for more details. You should have received a
 * copy of the GNU Affero General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>
 */

package org.opencps.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionServlet;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.search.DossierFileDisplayTerms;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.jasperreport.util.JRReportUtil;
import org.opencps.jasperreport.util.JRReportUtil.DocType;
import org.opencps.util.PortletPropsValues;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;

/**
 * @author trungnt
 */
public class ExportFileServlet extends ActionServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// fix encoding preview html
		//response.setCharacterEncoding("utf-8");
		//response.setContentType("text/html");

		//PrintWriter writer = response.getWriter();

		long dossierFileId = ParamUtil.getLong(request,
				DossierFileDisplayTerms.DOSSIER_FILE_ID);

		String docType = ParamUtil.getString(request, "docType");

		InputStream inputStream = null;
		OutputStream os = null;

		File file = null;

		// JSONObject responseJSON = JSONFactoryUtil.createJSONObject();

		String fileExportDir = StringPool.BLANK;

		try {

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(request);
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

			// Get dossier file
			DossierFile dossierFile = DossierFileLocalServiceUtil
					.getDossierFile(dossierFileId);

			// Get dossier part
			DossierPart dossierPart = DossierPartLocalServiceUtil
					.getDossierPart(dossierFile.getDossierPartId());

			String formData = dossierFile.getFormData();
			String jrxmlTemplate = dossierPart.getFormReport();

			// Validate json string

			JSONFactoryUtil.createJSONObject(formData);

			String outputDestination = PortletPropsValues.OPENCPS_FILE_SYSTEM_TEMP_DIR;
			String fileName = System.currentTimeMillis() + StringPool.DASH
					+ FriendlyURLNormalizerUtil.normalize(dossierPart.getPartName()) + docType;

			DocType type = DocType.getEnum(docType);

			fileExportDir = JRReportUtil.exportReportFile(jrxmlTemplate,
					formData, null, outputDestination, fileName, type);
			
			if (Validator.isNotNull(fileExportDir)) {

				file = new File(fileExportDir);
				inputStream = new FileInputStream(file);
				String mimeType = MimeTypesUtil.getContentType(file);

				response.setContentType(mimeType);
				response.setHeader("Content-Disposition",
			                     "attachment;filename=" + fileName);
				//ServletContext ctx = getServletContext();
				//InputStream is = ctx.getResourceAsStream("/testing.txt");

				int read=0;
				byte[] bytes = new byte[2048];
				os = response.getOutputStream();
				
				while((read = inputStream.read(bytes))!= -1){
					os.write(bytes, 0, read);
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if(inputStream != null){
				inputStream.close();
			}
			if(os != null){
				os.close();
			}
		}
	}

	@Override
	public void destroy() {

		// TODO Auto-generated method stub
		super.destroy();
	}
}
