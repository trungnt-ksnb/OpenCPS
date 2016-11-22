<%@page import="org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil"%>
<%@page import="org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil"%>
<%@page import="org.opencps.servicemgt.service.ServiceInfoLocalServiceUtil"%>
<%@page import="org.opencps.dossiermgt.NoSuchDossierException"%>
<%@page import="org.opencps.dossiermgt.service.DossierLocalServiceUtil"%>
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
<%@page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil"%>
<%@page import="com.liferay.portlet.PortletURLFactoryUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="javax.portlet.WindowState"%>
<%@page import="org.opencps.dossiermgt.EmptyDossierFileException"%>
<%@page import="org.opencps.dossiermgt.model.Dossier"%>
<%@page import="org.opencps.dossiermgt.model.DossierPart"%>
<%@page import="org.opencps.dossiermgt.model.DossierTemplate"%>
<%@page import="org.opencps.dossiermgt.model.impl.DossierPartImpl"%>
<%@page import="org.opencps.dossiermgt.model.ServiceConfig"%>
<%@page import="org.opencps.dossiermgt.search.DossierFileDisplayTerms"%>
<%@page import="org.opencps.dossiermgt.service.DossierPartLocalServiceUtil"%>
<%@page import="org.opencps.dossiermgt.util.DossierMgtUtil"%>
<%@page import="org.opencps.servicemgt.model.ServiceInfo"%>
<%@page import="org.opencps.util.PortletConstants"%>
<%@page import="org.opencps.dossiermgt.service.DossierFileLocalServiceUtil"%>
<%@page import="org.opencps.dossiermgt.model.DossierFile"%>
<%@page import="org.opencps.dossiermgt.service.FileGroupLocalServiceUtil"%>
<%@page import="org.opencps.dossiermgt.model.FileGroup"%>
<%@page import="org.opencps.util.WebKeys"%>
<%@page import="org.opencps.dossiermgt.search.DossierDisplayTerms"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>

<%@ include file="../../init.jsp"%>

<div class="ocps-dossier-content">
<%
	long dossierId = ParamUtil.getLong(request, "dossierId");
	
	Dossier dossier = null;
	
	try {
		dossier = DossierLocalServiceUtil.getDossier(dossierId);
	}
	catch (NoSuchDossierException ex) {
		
	}
	
	
	ServiceInfo serviceInfo = null;
	ServiceConfig serviceConfig = null;
	DossierTemplate dossierTemplate = null;
	
	try {
		serviceConfig = ServiceConfigLocalServiceUtil.getServiceConfig(dossier.getServiceConfigId());
	} catch (Exception e){}	
	
	try {
		serviceInfo = ServiceInfoLocalServiceUtil.getServiceInfo(dossier.getServiceInfoId());
	} catch (Exception e){}
	
	try {
		dossierTemplate = DossierTemplateLocalServiceUtil.getDossierTemplate(dossier.getDossierTemplateId());
	} catch (Exception e){}
	/* ServiceConfig serviceConfig = (ServiceConfig) request.getAttribute(WebKeys.SERVICE_CONFIG_ENTRY);
	
	ServiceInfo serviceInfo = (ServiceInfo) request.getAttribute(WebKeys.SERVICE_INFO_ENTRY);
	
	DossierTemplate dossierTemplate = (DossierTemplate) request.getAttribute(WebKeys.DOSSIER_TEMPLATE_ENTRY);
	 */
	String privateDossierGroup = StringPool.BLANK;
	
	List<DossierPart> dossierPartsLevel1 = new ArrayList<DossierPart>();
	
	boolean isEditDossier = false;
	
	if(dossierTemplate != null){		
		try{
			dossierPartsLevel1 = DossierPartLocalServiceUtil.getDossierPartsByT_P(dossierTemplate.getDossierTemplateId(), 0);
		}catch(Exception e){}
	}
	
	int index = 0; 
	
	if(dossierPartsLevel1 != null){
		for (DossierPart dossierPartLevel1 : dossierPartsLevel1){
			int partType = dossierPartLevel1.getPartType();
			
			List<DossierPart> dossierParts = DossierMgtUtil.getTreeDossierPart(dossierPartLevel1.getDossierpartId());
			
			if(dossierParts != null){
				%>
				<div class="opencps dossiermgt dossier-part-tree" id='<%= renderResponse.getNamespace() + "tree" + dossierParts.get(0).getDossierpartId()%>'>
					<c:choose>
						<c:when test="<%=partType == PortletConstants.DOSSIER_PART_TYPE_OPTION ||
							partType == PortletConstants.DOSSIER_PART_TYPE_SUBMIT || 
							partType == PortletConstants.DOSSIER_PART_TYPE_OTHER %>"
						>
							<%
							for(DossierPart dossierPart : dossierParts){
								
								boolean isDynamicForm = false;
								
								if(Validator.isNotNull(dossierPart.getFormReport()) && Validator.isNotNull(dossierPart.getFormScript())){
									isDynamicForm = true;
								}
								
								int level = 1;
								
								String treeIndex = dossierPart.getTreeIndex();
								
								if(Validator.isNotNull(treeIndex)){
									level = StringUtil.count(treeIndex, StringPool.PERIOD);
								}
								
								DossierFile dossierFile = null;
								
								int isOnlineData = 0;
								
								if(dossier != null){
									try{
										dossierFile = DossierFileLocalServiceUtil.getDossierFileInUse(dossier.getDossierId(), 
												dossierPart.getDossierpartId());
										if(dossierFile.getFormData().length() > 0){
											isOnlineData = 1;
										}else{
											isOnlineData = 0;
										}
									}catch(Exception e){
										
									}
								}
								
								%>
									<div class='<%="opencps dossiermgt dossier-part-row r-" + index%>'>
										<span class='<%="level-" + level + " opencps dossiermgt dossier-part"%>'>
											<span class="row-icon">
												<c:choose>
													<c:when test="<%=(partType == PortletConstants.DOSSIER_PART_TYPE_OPTION ||
														partType == PortletConstants.DOSSIER_PART_TYPE_OTHER) && level == 0%>"
													>
														<i class="fa fa-dot-circle-o" aria-hidden="true"></i>
													</c:when>
														<c:otherwise>
														<i 
															id='<%="rowcheck" + dossierPart.getDossierpartId() + StringPool.DASH + index %>' 
															class='<%=dossierFile != null &&  dossierFile.getFileEntryId() > 0 ? "fa fa-check-square-o" : "fa fa-square-o" %>' 
															aria-hidden="true"
														>
														</i>
													</c:otherwise>
												</c:choose>
												
											</span>
											<span class="opencps dossiermgt dossier-part-name">
												<%=dossierPart.getPartName() + (Validator.isNotNull(dossierFile) ?  " - " + dossierFile.getDossierFileNo():StringPool.BLANK) + DossierMgtUtil.getLoaiGiayToLabel((Validator.isNotNull(dossierFile) ? dossierFile.getDossierFileMark() : -1), locale) %>
											</span>
										</span>
									
										<span class="opencps dossiermgt dossier-part-control">
											<liferay-util:include 
												page="/html/common/portlet/dossier_actions.jsp" 
												servletContext="<%=application %>"
											>
												<portlet:param 
													name="<%=DossierDisplayTerms.DOSSIER_ID %>" 
													value="<%=String.valueOf(dossier != null ? dossier.getDossierId() : 0) %>"
												/>
												
												<portlet:param 
													name="isDynamicForm" 
													value="<%=String.valueOf(isDynamicForm) %>"
												/>
												
												<portlet:param 
													name="<%=DossierFileDisplayTerms.DOSSIER_PART_ID %>" 
													value="<%=String.valueOf(dossierPart.getDossierpartId()) %>"
												/>
												<portlet:param 
													name="<%=DossierFileDisplayTerms.FILE_ENTRY_ID %>" 
													value="<%=String.valueOf(dossierFile != null ? dossierFile.getFileEntryId() : 0) %>"
												/>
												<portlet:param 
													name="<%=DossierFileDisplayTerms.DOSSIER_FILE_ID %>" 
													value="<%=String.valueOf(dossierFile != null ? dossierFile.getDossierFileId() : 0) %>"
												/>
												<portlet:param 
													name="<%=DossierFileDisplayTerms.LEVEL %>" 
													value="<%=String.valueOf(level) %>"
												/>
												<portlet:param 
													name="<%=DossierFileDisplayTerms.GROUP_NAME %>" 
													value="<%=StringPool.BLANK%>"
												/>
												<portlet:param 
													name="<%=DossierFileDisplayTerms.PART_TYPE %>" 
													value="<%=String.valueOf(dossierPart.getPartType()) %>"
												/>
												<portlet:param 
													name="isEditDossier" 
													value="<%=String.valueOf(isEditDossier) %>"
												/>
												
												<portlet:param 
													name="isOnlineData" 
													value="<%=String.valueOf(isOnlineData) %>"
												/>
												
											</liferay-util:include>
										</span>
									</div>
									
								<%
								index++;
							}
							%>
							
							<c:if test="<%=partType == PortletConstants.DOSSIER_PART_TYPE_OTHER && dossier != null%>">
								<%
									List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.
										getDossierFileByD_DP(dossier.getDossierId(), dossierPartLevel1.getDossierpartId());
								
									if(dossierFiles != null){
										for(DossierFile dossierFileOther : dossierFiles){
											index ++;
											%>
											<div class='<%="opencps dossiermgt dossier-part-row r-" + index%>'>
												<span class='<%="level-1 opencps dossiermgt dossier-part"%>'>
													<span class="row-icon">
														<i 
															id='<%="rowcheck" + dossierFileOther.getDossierPartId() + StringPool.DASH + index %>' 
															class='<%=dossierFileOther.getFileEntryId() > 0 ? "fa fa-check-square-o" : "fa fa-square-o" %>' 
															aria-hidden="true"
														>
														</i>
													</span>
													<span class="opencps dossiermgt dossier-part-name">
														<%=dossierFileOther.getDisplayName()  + (dossierFileOther != null ? " - " +  dossierFileOther.getDossierFileNo():StringPool.BLANK) + DossierMgtUtil.getLoaiGiayToLabel(dossierFileOther.getDossierFileMark(), locale) %>
													</span>
												</span>
											
												<span class="opencps dossiermgt dossier-part-control">
													<liferay-util:include 
														page="/html/common/portlet/dossier_actions.jsp" 
														servletContext="<%=application %>"
													>
														<portlet:param 
															name="<%=DossierDisplayTerms.DOSSIER_ID %>" 
															value="<%=String.valueOf(dossier != null ? dossier.getDossierId() : 0) %>"
														/>
														<portlet:param 
															name="<%=DossierFileDisplayTerms.DOSSIER_PART_ID %>" 
															value="<%=String.valueOf(dossierFileOther.getDossierPartId()) %>"
														/>
														<portlet:param 
															name="<%=DossierFileDisplayTerms.FILE_ENTRY_ID %>" 
															value="<%=String.valueOf(dossierFileOther.getFileEntryId()) %>"
														/>
														<portlet:param 
															name="<%=DossierFileDisplayTerms.DOSSIER_FILE_ID %>" 
															value="<%=String.valueOf(dossierFileOther.getDossierFileId()) %>"
														/>
														<portlet:param 
															name="<%=DossierFileDisplayTerms.LEVEL %>" 
															value="<%=String.valueOf(1) %>"
														/>
														<portlet:param 
															name="<%=DossierFileDisplayTerms.GROUP_NAME %>" 
															value="<%=StringPool.BLANK%>"
														/>
														<portlet:param 
															name="<%=DossierFileDisplayTerms.PART_TYPE %>" 
															value="<%=String.valueOf(partType) %>"
														/>
														<portlet:param 
															name="isEditDossier" 
															value="<%=String.valueOf(isEditDossier) %>"
														/>
													</liferay-util:include>
												</span>
											</div>
											<%
										}
									}
								%>
							</c:if>
						</c:when>
						
						<c:when test="<%=partType == PortletConstants.DOSSIER_PART_TYPE_PRIVATE && dossier != null%>">
							<%
								List<FileGroup> fileGroups = new ArrayList<FileGroup>();
							
								try{
									fileGroups = FileGroupLocalServiceUtil.getFileGroupByD_DP(dossier.getDossierId(), dossierPartLevel1.getDossierpartId());
								}catch(Exception e){}
								
								
							%>
							<div class='<%="opencps dossiermgt dossier-part-row r-" + index%>'>
								<span class='<%="level-0" + " opencps dossiermgt dossier-part"%>'>
									<span class="row-icon">
										<i class="fa fa-dot-circle-o" aria-hidden="true"></i>
									</span>
									<span class="opencps dossiermgt dossier-part-name">
										<%=dossierPartLevel1.getPartName() %>
									</span>
								</span>
								<span class="opencps dossiermgt dossier-part-control">
									<c:if test="<%=isEditDossier %>">
										<aui:a 
											id="<%=String.valueOf(dossierPartLevel1.getDossierpartId()) %>"
											dossier="<%=String.valueOf(dossier.getDossierId()) %>"
											dossier-part="<%=String.valueOf(dossierPartLevel1.getDossierpartId()) %>"
											href="javascript:void(0);" 
											label="add-private-dossier" 
											cssClass="label opencps dossiermgt part-file-ctr add-individual-part-group"
										/>
									
									</c:if>
								</span>
								<%index++; %>
							</div>
							<c:choose>
								<c:when test="<%=fileGroups != null && ! fileGroups.isEmpty() %>">
									<%
										for(FileGroup fileGroup : fileGroups){
											%>
											<liferay-util:include 
												page="/html/common/portlet/dossier_individual_part.jsp" 
												servletContext="<%=pageContext.getServletContext() %>"
											>
												<portlet:param 
													name="<%=DossierDisplayTerms.DOSSIER_ID %>" 
													value="<%=String.valueOf(dossier != null ? dossier.getDossierId() : 0) %>"
												/>
												<portlet:param 
													name="<%=DossierFileDisplayTerms.DOSSIER_PART_ID %>" 
													value="<%=String.valueOf(fileGroup.getDossierPartId()) %>"
												/>
												
												<portlet:param 
													name="<%=DossierDisplayTerms.FILE_GROUP_ID %>" 
													value="<%=String.valueOf(fileGroup.getFileGroupId()) %>"
												/>
												
												<portlet:param 
													name="<%=DossierFileDisplayTerms.INDEX %>" 
													value="<%=String.valueOf(index) %>"
												/>
												
												<portlet:param 
													name="<%=DossierFileDisplayTerms.GROUP_NAME %>" 
													value="<%=fileGroup.getDisplayName() %>"
												/>
												
												<portlet:param 
													name="isEditDossier" 
													value="<%=String.valueOf(isEditDossier) %>"
												/>
											</liferay-util:include>
											<%
											index ++;
										}
									%>
								</c:when>
								
								<c:otherwise>
									<!--Nothing to show  -->
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<!--Nothing to show  -->
						</c:otherwise>
					</c:choose>
				</div>
				
			<%
			}
		}
	}
%>
</div>


<!-- <div id="myTreeView"></div>
<script type="text/javascript">
YUI().use(
		  'aui-tree-view',
		  function(Y) {
		    new Y.TreeViewDD(
		      {
		        boundingBox: '#myTreeView',
		        children: [
		          {
		            children: [
		              {label: 'Watermelon', leaf: true, type: 'check'},
		              {label: 'Apricot', leaf: true, type: 'check'},
		              {label: 'Pineapple', leaf: true, type: 'check'},
		              {label: 'Kiwi', leaf: true, type: 'check'},
		              {label: 'Orange', leaf: true, type: 'check'},
		              {label: 'Pomegranate', leaf: true, type: 'check'}
		            ],
		            expanded: true,
		            label: 'Checkboxes'
		          },
		          {
		            children: [
		              {label: 'Watermelon', leaf: true, type: 'radio'},
		              {label: 'Apricot', leaf: true, type: 'radio'},
		              {label: 'Pineapple', leaf: true, type: 'radio'},
		              {label: 'Kiwi', leaf: true, type: 'radio'},
		              {label: 'Orange', leaf: true, type: 'radio'},
		              {label: 'Pomegranate', leaf: true, type: 'radio'}
		            ],
		            expanded: true,
		            label: 'Radio'
		          },
		          {
		            children: [
		              {label: 'Watermelon', leaf: true, type: 'task'},
		              {label: 'Apricot', leaf: true, type: 'task'},
		              {label: 'Pineapple', leaf: true,  type: 'task'},
		              {label: 'Kiwi', leaf: true, type: 'task'},
		              {label: 'Orange', leaf: true, type: 'task'},
		              {label: 'Pomegranate', leaf: true,  type: 'task'}
		            ],
		            expanded: true,
		            label: 'Task',
		            type: 'task'
		          }
		        ]
		      }
		    ).render();
		  }
		);
</script> -->
