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
package org.opencps.processmgt.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.processmgt.model.ActionHistory;
import org.opencps.processmgt.model.ProcessOrder;
import org.opencps.processmgt.model.ProcessWorkflow;
import org.opencps.processmgt.model.StepAllowance;
import org.opencps.processmgt.service.ActionHistoryLocalServiceUtil;
import org.opencps.processmgt.service.ProcessOrderLocalServiceUtil;
import org.opencps.processmgt.service.ProcessWorkflowLocalServiceUtil;
import org.opencps.processmgt.service.StepAllowanceLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

public class ProcessMgtUtil {
	public static final String TOP_TABS_DOSSIERLIST = "top_tabs_dossierlist";
	public static final String TOP_TABS_DOSSIERFILELIST = "top_tabs_dossierfilelist";
	
	public static final String DELERR_EXIST_STEP = "step-exist";
	public static final String DELERR_EXIST_SERVICE_CONFIG = "service-config-exist";
	public static final String DELERR_EXIST_WORKFLOW = "workflow-exist";
	
	public static final String DEL_PROCESS_SUCC = "delete-process-success";
	
	public static final String[] _PROCESS_ORDER_CATEGORY_NAMES = {
		"process-order"
	};	
	
	
	/**
	 * @param processWorkflowId
	 * @return
	 */
	public static long getAssignUser(
	    long processWorkflowId, long processOrderId, long preProcessStepId) {

		long userId = 0;

		try {
			if (processWorkflowId > 0) {
				ProcessWorkflow processWorkflow =
				    ProcessWorkflowLocalServiceUtil.getProcessWorkflow(processWorkflowId);
				
				userId = processWorkflow.getActionUserId();

				if (userId == 0) {
					/*List<ActionHistory> actionList =
					    ActionHistoryLocalServiceUtil.getActionHistoryRecent(
					        processOrderId, preProcessStepId);

					
					if (actionList.size() != 0) {
						userId = actionList.get(0).getActionUserId();
					}
					
					if(userId != 0) {
						 ProcessOrder processOrder = ProcessOrderLocalServiceUtil.getProcessOrder(processOrderId);
						
						userId = processOrder.getAssignToUserId(); 
						
					}*/
					if(processWorkflow.getPostProcessStepId() > 0) {
						List<StepAllowance> allowances = StepAllowanceLocalServiceUtil.getByProcessStep(processWorkflow.getPostProcessStepId(), false);
						
						List<Long> roleWasActs = getRoleActions(allowances);
						
						List<ActionHistory> actionHistories = new ArrayList<ActionHistory>();
						
						if(roleWasActs.size() > 0) {
							List<Long> userActions = getUserWasActionings(roleWasActs);
							
							for(long userActionId : userActions) {
								List<ActionHistory> acHistories = ActionHistoryLocalServiceUtil
										.getActionHistoryByPOID_UAID(processOrderId, userActionId);
								
								for(ActionHistory actionHistory : acHistories) {
									if(!actionHistories.contains(actionHistory)) {
										actionHistories.add(actionHistory);
									}
								}
							}
						}
						
						
						if(actionHistories.size() > 0) {
							ActionHistory actionHistory = getLastestActionHistory(actionHistories);
							
							userId = actionHistory.getActionUserId();
						} /*else {
							actionHistories = ActionHistoryLocalServiceUtil
									.getActionHistoryByProcessOrderId(processOrderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, false);
							
							if(actionHistories.size() > 0) {
								ActionHistory actionHistory = getLastestActionHistory(actionHistories);
								
								userId = actionHistory.getActionUserId();
							}
						}*/
						
					}
				}
			}
			
		}
		catch (Exception e) {
			userId = 0;
		}
		
		return userId;
	}
	
	
	private static ActionHistory getLastestActionHistory(List<ActionHistory> actionHistories) {
		ActionHistory actionHistoryTemp = actionHistories.get(0);
		for(ActionHistory actionHistory : actionHistories) {
			if(actionHistoryTemp.getModifiedDate().compareTo(actionHistory.getModifiedDate()) < 0) {
				actionHistoryTemp = actionHistory;
			}
		}
		
		return actionHistoryTemp;
	}
	
	private static List<Long> getUserWasActionings(List<Long> roleWasActioning) {
		List<Long> userWasActioning = new ArrayList<Long>();
		try {
			for(long roleWasAct : roleWasActioning){
				List<User> users =  UserLocalServiceUtil.getRoleUsers(roleWasAct);
				
				for(User user : users) {
					if(!userWasActioning.contains(user.getUserId())){
						userWasActioning.add(user.getUserId());
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return userWasActioning;
	
	}
	
	private static List<Long> getRoleActions (List<StepAllowance> allowances) {
		List<Long> roleWasActioning = new ArrayList<Long>();
		
		for(StepAllowance allowance : allowances) {
			if(!roleWasActioning.contains(allowance.getRoleId())) {
				roleWasActioning.add(allowance.getRoleId());
			}
		}
		return roleWasActioning;
	}
	
	
	public static long getAssignUserWasActioning(long processOrderId) {
		long userId = 0;
		try {
			ProcessOrder processOrder = ProcessOrderLocalServiceUtil.getProcessOrder(processOrderId);
			List<ActionHistory> actionHistories = ActionHistoryLocalServiceUtil.getActionHistory(processOrderId, processOrder.getProcessWorkflowId());
			userId = actionHistories.get(0).getActionUserId();
		} catch (Exception e) {
		}
		
		return userId;
	}
	
	/**
	 * @param processWorkflowId
	 * @return
	 */
	public static ProcessWorkflow getProcessWorkflow (long processWorkflowId) {
		
		ProcessWorkflow workflow = null;
		try {
			
			if (processWorkflowId != 0) {
				workflow = ProcessWorkflowLocalServiceUtil.getProcessWorkflow(processWorkflowId);
			}
        }
        catch (Exception e) {
	       
        }
		
		return workflow;
	}
}
