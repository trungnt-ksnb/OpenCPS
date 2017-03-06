
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
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="org.opencps.dossiermgt.util.DossierMgtUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@page import="com.liferay.portal.RolePermissionsException"%>
<%@page import="com.liferay.portlet.documentlibrary.DuplicateFileException"%>
<%@page import="com.liferay.portlet.documentlibrary.FileExtensionException"%>
<%@page import="com.liferay.portlet.documentlibrary.FileSizeException"%>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFileEntry"%>
<%@page import="com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="org.opencps.accountmgt.NoSuchAccountException"%>
<%@page import="org.opencps.accountmgt.NoSuchAccountFolderException"%>
<%@page import="org.opencps.accountmgt.NoSuchAccountOwnOrgIdException"%>
<%@page import="org.opencps.accountmgt.NoSuchAccountOwnUserIdException"%>
<%@page import="org.opencps.accountmgt.NoSuchAccountTypeException"%>
<%@page import="org.opencps.dossiermgt.model.DossierFile"%>
<%@page import="org.opencps.dossiermgt.model.DossierPart"%>
<%@page import="org.opencps.dossiermgt.NoSuchDossierException"%>
<%@page import="org.opencps.dossiermgt.NoSuchDossierPartException"%>
<%@page import="org.opencps.dossiermgt.PermissionDossierException"%>
<%@page import="org.opencps.dossiermgt.search.DossierDisplayTerms"%>
<%@page import="org.opencps.dossiermgt.search.DossierFileDisplayTerms"%>
<%@page import="org.opencps.dossiermgt.service.DossierFileLocalServiceUtil"%>
<%@page import="org.opencps.dossiermgt.service.DossierPartLocalServiceUtil"%>
<%@page import="org.opencps.util.DateTimeUtil"%>
<%@page import="org.opencps.util.MessageKeys"%>
<%@page import="org.opencps.util.PortletConstants"%>
<%@page import="org.opencps.util.PortletConstants.FileSizeUnit"%>
<%@page import="org.opencps.util.PortletPropsValues"%>
<%@page import="org.opencps.util.PortletUtil"%>
<%@page import="org.opencps.util.WebKeys"%>

<%@ include file="/init.jsp"%>

<%
	String signatureType = ParamUtil.getString(request, "signatureType");
	String textPositionWithImageSign = ParamUtil.getString(request, "textPositionWithImageSign", "overlaps");
	double offsetX = ParamUtil.getDouble(request, "offsetX");
	double offsetY = ParamUtil.getDouble(request, "offsetY");
	String characterAttachs = ParamUtil.getString(request, "characterAttachs", "text");
	
	boolean success = false;
	
	try{
		success = !SessionMessages.isEmpty(renderRequest) && SessionErrors.isEmpty(renderRequest);
		
	}catch(Exception e){
		
	}
	
	long dossierId = ParamUtil.getLong(request, DossierDisplayTerms.DOSSIER_ID);
	
	long dossierPartId = ParamUtil.getLong(request, DossierFileDisplayTerms.DOSSIER_PART_ID);
	
	long dossierFileId = ParamUtil.getLong(request, DossierFileDisplayTerms.DOSSIER_FILE_ID);
	
	long fileGroupId = ParamUtil.getLong(request, DossierDisplayTerms.FILE_GROUP_ID);
	
	long groupDossierPartId = ParamUtil.getLong(request, "groupDossierPartId");
	
	long signImageId = 0;
	
	String groupName = ParamUtil.getString(request, DossierFileDisplayTerms.GROUP_NAME);
	
	String modalDialogId = ParamUtil.getString(request, "modalDialogId");
	
	String redirectURL = ParamUtil.getString(request, "redirectURL");
	
	if(accountType.equalsIgnoreCase("Business")) {
		signImageId = business.getSignImageId();
	} else if(accountType.equalsIgnoreCase("Citizen")) {
		signImageId = citizen.getSignImageId();
	}
	
	JSONObject signImageInfo = DossierMgtUtil.getSignImageAsBase64(signImageId);
	
	DossierFile dossierFile = null;
	boolean hasSign = false;
	if(dossierFileId > 0){
		try{
			
			dossierFile = DossierFileLocalServiceUtil.getDossierFile(dossierFileId);

		}catch(Exception e){}
		
	}
	
	String dossierPartName = StringPool.BLANK; 
	if(dossierPartId > 0){
		DossierPart dossierPart = DossierPartLocalServiceUtil.fetchDossierPart(dossierPartId);
		dossierPartName = Validator.isNotNull(dossierPart)?dossierPart.getPartName():StringPool.BLANK;
		hasSign = Validator.isNotNull(dossierPart) ? dossierPart.getHasSign() : false ;
		
	}
	
	Date defaultDossierFileDate = dossierFile != null && dossierFile.getDossierFileDate() != null ? 
		dossierFile.getDossierFileDate() : DateTimeUtil.convertStringToDate("01/01/1970");
		
	PortletUtil.SplitDate spd = new PortletUtil.SplitDate(defaultDossierFileDate);
	
	PortletPreferences preferences = renderRequest.getPreferences();

	if (Validator.isNotNull(portletResource)) {
		preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
	}
	
	String uploadFileTypes = preferences.getValue("uploadFileTypes", "pdf,doc,docx,xls,png");

	
	float maxTotalUploadFileSize = GetterUtil.getFloat(preferences.getValue("maxTotalUploadFileSize", String.valueOf(0)), 0);
	
	String maxTotalUploadFileSizeUnit = preferences.getValue("maxTotalUploadFileSizeUnit", PortletConstants.FileSizeUnit.MB.toString());
	
	float maxUploadFileSize = GetterUtil.getFloat(preferences.getValue("maxUploadFileSize", String.valueOf(0)), 0);
	
	String maxUploadFileSizeUnit = preferences.getValue("maxUploadFileSizeUnit", PortletConstants.FileSizeUnit.MB.toString());

	if (maxUploadFileSize == 0){
		maxUploadFileSize = PortletPropsValues.ACCOUNTMGT_FILE_SIZE/1024/1024;
		maxUploadFileSizeUnit = PortletConstants.FileSizeUnit.MB.getValue();
	}
	
	List<DossierFile> dossierFileList = new ArrayList<DossierFile>();
	if (dossierId > 0){
		try {
			dossierFileList = DossierFileLocalServiceUtil.getDossierFileByDossierId(dossierId);
		} catch (Exception e){}
	}
	
	float totalUploadFileSizeInByte = 0;
	
	if (!dossierFileList.isEmpty()){
		for (DossierFile tempDossierFile : dossierFileList){
			if (tempDossierFile.getRemoved() == 0){
				long fileEntryId = tempDossierFile.getFileEntryId();
				
				DLFileEntry fileEntry = null;
				try {
					fileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
				} catch (Exception e){}
				
				if (Validator.isNotNull(fileEntry)){
					totalUploadFileSizeInByte += fileEntry.getSize();
				}
			}
		}
	}
%>

<portlet:actionURL var="addAttachmentFileURL" name="addAttachmentFile"/>
	
<liferay-ui:error message="upload-error" key="upload-error"/>

<liferay-ui:error 
	exception="<%= NoSuchDossierException.class %>" 
	message="<%= NoSuchDossierException.class.getName() %>" 
/>
<liferay-ui:error 
	exception="<%= NoSuchDossierPartException.class %>" 
	message="<%= NoSuchDossierPartException.class.getName() %>" 
/>
<liferay-ui:error 
	exception="<%= NoSuchAccountException.class %>" 
	message="<%= NoSuchAccountException.class.getName() %>" 
/>
<liferay-ui:error 
	exception="<%= NoSuchAccountTypeException.class %>" 
	message="<%= NoSuchAccountTypeException.class.getName() %>" 
/>
<liferay-ui:error 
	exception="<%= NoSuchAccountFolderException.class %>" 
	message="<%= NoSuchAccountFolderException.class.getName() %>" 
/>
<liferay-ui:error 
	exception="<%= NoSuchAccountOwnUserIdException.class %>" 
	message="<%= NoSuchAccountOwnUserIdException.class.getName() %>" 
/>

<liferay-ui:error 
	exception="<%= NoSuchAccountOwnOrgIdException.class %>" 
	message="<%= NoSuchAccountOwnOrgIdException.class.getName() %>" 
/>
<liferay-ui:error 
	exception="<%= PermissionDossierException.class %>" 
	message="<%= PermissionDossierException.class.getName() %>" 
/>

<liferay-ui:error 
	exception="<%= FileSizeException.class %>" 
	message="<%= FileSizeException.class.getName() %>" 
/>

<liferay-ui:error 
	exception="<%= FileExtensionException.class %>" 
	message="<%= FileExtensionException.class.getName() %>" 
/>

<liferay-ui:error 
	exception="<%= DuplicateFileException.class %>" 
	message="<%= MessageKeys.DOSSIER_FILE_DUPLICATE_NAME %>"
/>

<liferay-ui:error 
    exception="<%= RolePermissionsException.class %>" 
    message="<%= RolePermissionsException.class.getName() %>"
/>

<aui:form 
	name="fm" 
	method="post" 
	action="<%=addAttachmentFileURL%>" 
	enctype="multipart/form-data"
>
	<aui:input name="redirectURL" type="hidden" value="<%=Validator.isNotNull(redirectURL)?redirectURL: currentURL %>"/>
	<aui:input name="<%=DossierDisplayTerms.DOSSIER_ID %>" type="hidden" value="<%=dossierId %>"/>
	<aui:input name="groupDossierPartId" type="hidden" value="<%=groupDossierPartId %>"/>
	<aui:input name="<%=DossierFileDisplayTerms.DOSSIER_FILE_ID %>" type="hidden" value="<%=dossierFileId %>"/>
	<aui:input name="<%=DossierDisplayTerms.FILE_GROUP_ID %>" type="hidden" value="<%=fileGroupId %>"/>
	<aui:input name="<%=DossierFileDisplayTerms.DOSSIER_PART_ID %>" type="hidden" value="<%=dossierPartId %>"/>
	<aui:input name="<%=DossierFileDisplayTerms.DOSSIER_FILE_ORIGINAL %>" type="hidden" value="<%=String.valueOf(PortletConstants.DOSSIER_FILE_ORIGINAL) %>"/>
	<aui:input name="<%=DossierFileDisplayTerms.DOSSIER_FILE_TYPE %>" type="hidden" value="<%=String.valueOf(renderResponse.getNamespace().equals(StringPool.UNDERLINE + WebKeys.DOSSIER_MGT_PORTLET + StringPool.UNDERLINE)  ? PortletConstants.DOSSIER_FILE_TYPE_INPUT : PortletConstants.DOSSIER_FILE_TYPE_OUTPUT) %>"/>
	<aui:input name="<%=DossierFileDisplayTerms.GROUP_NAME %>" type="hidden" value="<%=groupName %>"/>
	<aui:input name="<%=DossierFileDisplayTerms.FILE_TYPES %>" type="hidden" value="<%=uploadFileTypes %>"/>
	<aui:input name="<%=DossierFileDisplayTerms.MAX_UPLOAD_FILE_SIZE %>" type="hidden" value="<%=maxUploadFileSize %>"/>
	<aui:input name="<%=DossierFileDisplayTerms.MAX_UPLOAD_FILE_SIZE_UNIT %>" type="hidden" value="<%=maxUploadFileSizeUnit %>"/>
	<aui:input name="<%=DossierFileDisplayTerms.MAX_TOTAL_UPLOAD_FILE_SIZE %>" type="hidden" value="<%=maxTotalUploadFileSize %>"/>
	<aui:input name="<%=DossierFileDisplayTerms.MAX_TOTAL_UPLOAD_FILE_SIZE_UNIT %>" type="hidden" value="<%=maxTotalUploadFileSizeUnit %>"/>
	
	<aui:input name="dossierFileSigned" type="hidden" />
	<aui:input name="signatureFileName" type="hidden" />
	<aui:input name="functionCase" type="hidden" />
	
	<aui:row>
		<aui:col width="100">
			<aui:input name="<%= DossierFileDisplayTerms.DISPLAY_NAME %>" type="textarea" value="<%=dossierPartName %>" inlineLabel="true">

				<aui:validator name="required"/>
			</aui:input>
		</aui:col>
	</aui:row>

	<aui:row>
		<aui:col width="50">
			<aui:input name="<%= DossierFileDisplayTerms.DOSSIER_FILE_NO %>" type="text" inlineLabel="true"/>
		</aui:col>
		<aui:col width="50">
			
			<label class="control-label custom-lebel" for='<portlet:namespace/><%=DossierFileDisplayTerms.DOSSIER_FILE_DATE %>'>
				<liferay-ui:message key="dossier-file-date"/>
			</label>

			<liferay-ui:input-date
				dayParam="<%=DossierFileDisplayTerms.DOSSIER_FILE_DATE_DAY %>"
				dayValue="<%=spd.getDayOfMoth() %>"
				disabled="<%= false %>"
				monthParam="<%=DossierFileDisplayTerms.DOSSIER_FILE_DATE_MONTH %>"
				monthValue="<%=spd.getMonth() %>"
				name="<%=DossierFileDisplayTerms.DOSSIER_FILE_DATE%>"
				yearParam="<%=DossierFileDisplayTerms.DOSSIER_FILE_DATE_YEAR %>"
				yearValue="<%=spd.getYear() %>"
				formName="fm"
				autoFocus="<%=true %>"
				nullable="<%=dossierFile == null || dossierFile.getDossierFileDate() == null ? true : false %>"
			/>

		</aui:col>
	</aui:row>
	<aui:row>
		<%
			
			String exceptedFileType = StringPool.BLANK;
			if (uploadFileTypes == StringPool.BLANK){
				exceptedFileType = StringUtil.merge(PortletPropsValues.ACCOUNTMGT_FILE_TYPE, ", ");
			} else {
				String[] fileTypeArr = uploadFileTypes.split("\\W+");
				exceptedFileType= StringUtil.merge(fileTypeArr, ", ");
			}
		%>
		
		<aui:col width="65">
			<aui:input name="<%=DossierFileDisplayTerms.DOSSIER_FILE_UPLOAD %>" type="file">
				<aui:validator name="acceptFiles">
					'<%=exceptedFileType %>'
				</aui:validator>
			</aui:input>
			<div class="alert alert-info" role="alert">
				<liferay-ui:message key="dossier-file-type-excep"/>: <%= exceptedFileType %> --- <liferay-ui:message key="dossier-file-size-excep"/>: <%= String.valueOf(maxUploadFileSize) %> <%=maxUploadFileSizeUnit %>
			</div>
			<font class="requiredStyleCSS"><liferay-ui:message key="control-with-star-is-required"/></font>
		</aui:col>
		
		 <aui:col width="35">
			<aui:input
				name="hasSign"
				type="checkbox"	
				value="<%= hasSign %>"
			/>
		</aui:col>
	</aui:row>
	
	<aui:row>
		<aui:button name="agree" value="agree"/>
		<aui:button name="cancel" type="button" value="cancel"/>
	</aui:row>
</aui:form>

<aui:script use="aui-base,aui-io,aui-loading-mask-deprecated,aui-node">
	// load plugin signature
	/* function pluginload(loaded)
	{
		if(!loaded) {
			alert('Loading plugin is failed!');
		}
	} */
	
	AUI().ready(function(A){
		
		var cancelButton = A.one('#<portlet:namespace/>cancel');
		var agreeButton = A.one('#<portlet:namespace/>agree');
		var hasSign = A.one('#<portlet:namespace/>hasSign');
		var success = '<%=success%>';
		
		var file = null;
		
		if(cancelButton){
			cancelButton.on('click', function(){
				<portlet:namespace/>closeDialog();
			});
		}
		
		if(success == 'true'){
			Liferay.Util.getOpener().Liferay.fire('turnOffOverlaymask');
			<portlet:namespace/>closeDialog();
		}
		
		// Validate size and type file upload
		
		var maxUploadFileSizeInByte = '<%=PortletUtil.convertSizeUnitToByte(maxUploadFileSize, FileSizeUnit.getEnum(maxUploadFileSizeUnit))%>';
		maxUploadFileSizeInByte = parseFloat(maxUploadFileSizeInByte);
		
		var maxTotalUploadFileSizeInByte = '<%=PortletUtil.convertSizeUnitToByte(maxTotalUploadFileSize, FileSizeUnit.getEnum(maxTotalUploadFileSizeUnit))%>';
		maxTotalUploadFileSizeInByte = parseFloat(maxTotalUploadFileSizeInByte);

		
		var fileUploadSizeInByte = 0;
		var totalUploadFileSizeInByte = '<%=totalUploadFileSizeInByte%>';
		
		$('#<portlet:namespace />dossierFileUpload').on('change', function() {
			fileUploadSizeInByte = this.files[0].size;
			totalUploadFileSizeInByte += fileUploadSizeInByte;
			file = this.files[0];
		});
		
		if(agreeButton) {
			
			var author = '<%= Validator.isNotNull(user) ? user.getFullName() : StringPool.BLANK %>';
			var imgSrcName = '<%= Validator.isNotNull(user) ? user.getScreenName() : StringPool.BLANK %>';
			var condauImageSrc = imgSrcName + "_condau.png";
			agreeButton.on('click', function() {
				if (fileUploadSizeInByte == 0){
					alert('<%= LanguageUtil.get(themeDisplay.getLocale(), "please-upload-dossier-part-required-before-send") %>');
				} else
				
				if (fileUploadSizeInByte > maxUploadFileSizeInByte && maxUploadFileSizeInByte > 0) {
					alert('<%= LanguageUtil.get(themeDisplay.getLocale(), "please-upload-dossier-part-size-smaller-than") %>' + ' ' + '<%=maxUploadFileSize%>' + ' ' + '<%=maxUploadFileSizeUnit%>');
				}else 
				
				if (totalUploadFileSizeInByte > maxTotalUploadFileSizeInByte && maxTotalUploadFileSizeInByte > 0) {
					alert('<%= LanguageUtil.get(themeDisplay.getLocale(), "overload-total-file-upload-size") %>' + ' ' + '<%=maxTotalUploadFileSize%>' + ' ' + '<%=maxTotalUploadFileSizeUnit%>');
				}else {
					Liferay.Util.getOpener().Liferay.fire('turnOnOverlaymask');
					// is signature from configuration
					var hasSignVal = hasSign.val().toString();
					if(hasSignVal == 'true') {
						// convert file from input to base64 encode
						var fileBase64Encode = '';
						var reader = new FileReader();
						if(file) {
							reader.readAsDataURL(file);
							var fileName = file.name;
							// callback when convert success
							reader.onload = function() {
								fileBase64Encode = reader.result;
								if(fileBase64Encode != '') {
									
									fileBase64Encode = fileBase64Encode.substring(fileBase64Encode.lastIndexOf(',') + 1);
									// signal
									// <portlet:namespace/>requestDataSignalToServer(fileBase64Encode, imgSrcName, fileName);
									var signImageInfo = JSON.parse('<%= signImageInfo %>');
									<portlet:namespace/>signature(fileName, fileBase64Encode, 
											signImageInfo.signImageName, signImageInfo.signImageAsBase64);
								}
							}
						}
					} else {
						submitForm(document.<portlet:namespace />fm);
					}
					// 
				}
			});
		}
	});
	
	/* Liferay.provide(window, '<portlet:namespace/>requestDataSignalToServer', function(fileBase64Encode, imgSrcName, fileName) {
		var A = AUI();
		A.io.request(
				'<%= addAttachmentFileURL %>',
				{
					method: 'POST',
		            form: { 
		            		id: '<portlet:namespace />fm',
		            	},
		            data : {
		            	<portlet:namespace/>imgSrcName: imgSrcName,
		            	<portlet:namespace/>functionCase: '<%= PortletConstants.SIGNATURE_REQUEST_DATA %>'
		            },
		            on: {
		                 success : function(event, id, obj){
		                	 var response = this.get('responseData');
		                	  if(response) {
		                		   response = JSON.parse(response);
		   							// name and content of image file
		   							var imageName = imgSrcName + "_condau.png";
		   							var imageBase64Encode = response.imageBase64Encode;
		   							<portlet:namespace/>signature(fileName, fileBase64Encode, imageName, imageBase64Encode);
		                	  }
		                 },
		                 error: function(){
		    				console.log("error");
		                 }
		             }
				}
			);
	}); */
	
	Liferay.provide(window, '<portlet:namespace/>signature', function(fileName, fileBase64Encode, imageName, imageBase64Encode) {
		var A = AUI();
		var characterAttachs = '<%= characterAttachs %>';
					// create signature file from Base64 data
					if(fileName != '' && fileBase64Encode != '') {
						window.parent.PDFSigningHelper.writeBase64ToFile(fileName, fileBase64Encode, function(jsonDataSignedResult){
							
							//get certificate index
							window.parent.PDFSigningHelper.getCertIndex(function(certIndexJsonDataResutl){
								if(certIndexJsonDataResutl.data != '-1') {
									var signatureTypeVal = '<%= signatureType %>';
									var author = '<%= Validator.isNotNull(user) ? user.getFullName() : StringPool.BLANK %>';
									var characterAttachs = '<%= characterAttachs %>';
									var characterAttachArray = characterAttachs.split(',');
										// both image and text
										if (characterAttachArray.indexOf('image') != -1 && characterAttachArray.indexOf('text') != -1) {
											if(imageName != 'undefined' && imageBase64Encode != 'undefined') {
												<portlet:namespace/>overlapText();
												window.parent.PDFSigningHelper.writeBase64ToFile(imageName, imageBase64Encode , function(imageFileJsonDataResult) {
													<portlet:namespace/>chooseSignatureType(jsonDataSignedResult, imageFileJsonDataResult , author, certIndexJsonDataResutl, fileName, signatureTypeVal);
												});
											
											} else {
												alert('<%= LanguageUtil.get(themeDisplay.getLocale(), "no-sign-image-please-upload-it-in-your-profile") %>');
												Liferay.Util.getOpener().Liferay.fire('turnOffOverlaymask');
						            			<portlet:namespace/>closeDialog();
											}
											
											
										}
										// has image
										// if configuration contain image value
										else if(characterAttachArray.indexOf('image') != -1) {
											if(imageName != 'undefined' && imageBase64Encode != 'undefined') {
												// create image file from Base64 data
												window.parent.PDFSigningHelper.writeBase64ToFile(imageName, imageBase64Encode , function(imageFileJsonDataResult) {
													<portlet:namespace/>chooseSignatureType(jsonDataSignedResult, imageFileJsonDataResult , author, certIndexJsonDataResutl, fileName, signatureTypeVal);
												});
											} else {
												alert('<%= LanguageUtil.get(themeDisplay.getLocale(), "no-sign-image-please-upload-it-in-your-profile") %>');
												Liferay.Util.getOpener().Liferay.fire('turnOffOverlaymask');
						            			<portlet:namespace/>closeDialog();
											}
										} 
										// text
										// if configuration contain text value
										else if (characterAttachArray.indexOf('text') != -1) {
											<portlet:namespace/>overlapText();
											var noImage = {};
											noImage.data = '';
											<portlet:namespace/>chooseSignatureType(jsonDataSignedResult, noImage , author, certIndexJsonDataResutl, fileName, signatureTypeVal);
										}
										
								} else {
									Liferay.Util.getOpener().Liferay.fire('turnOffOverlaymask');
			            			<portlet:namespace/>closeDialog();
									// get certificate fail
								}
							});
						});
					}
	});
	
	Liferay.provide(window, '<portlet:namespace/>overlapText', function() {
		var A = AUI();
		var textPositionWithImageSign = '<%= textPositionWithImageSign %>';
		if(textPositionWithImageSign = 'overlaps') {
			window.parent.PDFSigningHelper.setSignatureInfo(1,1);
		} else if(textPositionWithImageSign == 'noOverlaps') {
			window.parent.PDFSigningHelper.setSignatureInfo(1,0);
		}
	});
	
	Liferay.provide(window, '<portlet:namespace/>chooseSignatureType', function(jsonDataSignedResult, imageFileJsonDataResult , author, certIndexJsonDataResutl, fileName, signatureTypeVal) {
		
		var A = AUI();
		
		// if signal with select point type
			if(signatureTypeVal == 'selectPoint') {
				window.parent.PDFSigningHelper.signPDFWithSelectedPoint(jsonDataSignedResult.data, imageFileJsonDataResult.data,
						author, "", certIndexJsonDataResutl.data, "", function(jsonDataSignedResult){
						<portlet:namespace/>updateDataAfterSign(jsonDataSignedResult, fileName);
				});
				
			} else if(signatureTypeVal == 'fixAtPoint'){
				// sign with coordinate
				var offsetX = '<%= offsetX %>';
				var offsetY = '<%= offsetY %>';
				window.parent.PDFSigningHelper.signPDFAtPoint(jsonDataSignedResult.data, imageFileJsonDataResult.data, author, 
						"", parseFloat(offsetX), parseFloat(offsetY), 0, certIndexJsonDataResutl.data, "", function(jsonDataSignedResult) {
					<portlet:namespace/>updateDataAfterSign(jsonDataSignedResult, fileName);
				});
			}
	});
	
	// update data
	Liferay.provide(window, '<portlet:namespace/>updateDataAfterSign', function(jsonDataSignedResult, fileName) {
		var A = AUI();
		if(jsonDataSignedResult.code == 0) {
			window.parent.PDFSigningHelper.readFileasBase64(jsonDataSignedResult.data.path , function(jsonDataSignedBase64Result) {
				// console.log(jsonDataSignedBase64Result);
				// console.log(jsonDataSignedBase64Result.data);
				
				var dossierFileSigned = A.one('#<portlet:namespace />dossierFileSigned');
				var functionCase = A.one('#<portlet:namespace />functionCase');
				var signatureFileName = A.one('#<portlet:namespace />signatureFileName');
				
				dossierFileSigned.val(jsonDataSignedBase64Result.data.toString());
				signatureFileName.val(fileName);
				functionCase.val('<%= PortletConstants.SIGNATURE_UPDATE_DATA_AFTER_SIGN %>');
				submitForm(document.<portlet:namespace />fm);
				
				/* Liferay.Util.getOpener().Liferay.fire('turnOffOverlaymask');
    			<portlet:namespace/>closeDialog(); */
				
				/*  A.io.request(
						'<%= addAttachmentFileURL %>',
						{
							contentType:"application/x-www-form-urlencoded",
							method: 'POST',
				            form: { 
				            		id: '<portlet:namespace />fm',
				            	},
				            data : {
				            	<portlet:namespace/>signatureFileName: fileName,
				            	<portlet:namespace/>functionCase: '<%= PortletConstants.SIGNATURE_UPDATE_DATA_AFTER_SIGN %>'
				            },
				            cache:false,
				            on : {
				            	success : function() {
				            		Liferay.Util.getOpener().Liferay.fire('turnOffOverlaymask');
			            			<portlet:namespace/>closeDialog();
				            		
			            			var responseDataJson = this.get('responseData');
				            		if(responseDataJson) {
				            			//responseDataJson = JSON.parse(responseDataJson);
				            			//close dialog
				            		}
				            	}
				            }
				         }
				); */
			});
		} else {
			alert(jsonDataSignedResult.errormsg);
			Liferay.Util.getOpener().Liferay.fire('turnOffOverlaymask');
			<portlet:namespace/>closeDialog();
		}
	});
	

	Liferay.provide(window, '<portlet:namespace/>closeDialog', function() {
		var data = {
			'conserveHash': true
		};
		
		Liferay.Util.getOpener().Liferay.Portlet.refresh('#p_p_id' + '<portlet:namespace/>', data);
		var dialog = Liferay.Util.getWindow('<portlet:namespace/>' + '<%=modalDialogId%>');
		dialog.destroy(); // You can try toggle/hide whate
	});

</aui:script>
	