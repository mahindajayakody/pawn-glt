package com.dass.pawning.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.json.JSONArray;

import com.dass.core.exception.PawnException;
import com.dass.core.util.MasterAction;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.RecordStatusEnum;
import com.dass.core.util.SessionUtil;
import com.dass.core.util.StrutsFormValidateUtil;
import com.dass.core.util.QueryCriteria.Oparator;
import com.dass.pawning.domain.TicketStatus;
import com.dass.pawning.service.TicketStatusService;

public class TicketStatusAction extends MasterAction {
	private static final Logger logger = Logger.getLogger(TicketStatusAction.class);
	private TicketStatusService ticketStatusService;

	public void setTicketStatusService(TicketStatusService ticketStatusService) {
		this.ticketStatusService = ticketStatusService;
	}

	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to create method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{
        	TicketStatus ticketStatus = new TicketStatus();
        	ticketStatus.setCode(request.getParameter("code"));
        	ticketStatus.setDescription(request.getParameter("description"));

        	try {
        		ticketStatusService.createTicketStatus(SessionUtil.getUserSession(request), ticketStatus);
        		response.getWriter().write(StrutsFormValidateUtil.getMessageCreateSuccess(messageResources).toString());
			} catch (PawnException ex) {
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
			}
        }

		logger.debug("**** Leaving from create method *****");
		return null;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to update method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{
        	TicketStatus ticketStatus = new TicketStatus();
        	ticketStatus.setCode(request.getParameter("code"));
        	ticketStatus.setDescription(request.getParameter("description"));
        	ticketStatus.setRecordId(Integer.parseInt(request.getParameter("recordId")));
        	ticketStatus.setVersion(Integer.parseInt(request.getParameter("version")));

        	try {
        		ticketStatusService.updateTicketStatus(SessionUtil.getUserSession(request), ticketStatus);
        		response.getWriter().write(StrutsFormValidateUtil.getMessageUpdateSuccess(messageResources).toString());
			} catch (PawnException ex) {
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
			}
        }

		logger.debug("**** Leaving from update method *****");
		return null;
	}

	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to remove method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{
        	TicketStatus ticketStatus = new TicketStatus();
        	ticketStatus.setRecordId(Integer.parseInt(request.getParameter("recordId")));
        	ticketStatus.setVersion(Integer.parseInt(request.getParameter("version")));

        	try {
        		ticketStatusService.removeTicketStatus(SessionUtil.getUserSession(request), ticketStatus);
        		response.getWriter().write(StrutsFormValidateUtil.getMessageDeleteSuccess(messageResources).toString());
			} catch (PawnException ex) {
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
			}
        }

		logger.debug("**** Leaving from remove method *****");
		return null;
	}

	public ActionForward authorize(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.debug("**** Enter in to remove method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		String recordId = request.getParameter("recordId");
        String version  = request.getParameter("version");
        String authorizeType   = request.getParameter("authorizeMode");
        boolean authorize      = Boolean.parseBoolean(request.getParameter("authorizeValue"));

        if((recordId==null)||(version==null)||(recordId.equals(""))||(version.equals(""))){
        	response.getWriter().write(StrutsFormValidateUtil.getMessageNotFound(messageResources).toString());
        	return null;
        }

        TicketStatus ticketStatus = new TicketStatus();
    	ticketStatus.setRecordId(Integer.parseInt(request.getParameter("recordId")));
    	ticketStatus.setVersion(Integer.parseInt(request.getParameter("version")));

        try{
        	if(authorizeType.equals("Create") ){
        		ticketStatusService.authorizeCreation(SessionUtil.getUserSession(request), ticketStatus, authorize);
        	}else if( authorizeType.equals("Update") ){
        		ticketStatusService.authorizeUpdation(SessionUtil.getUserSession(request), ticketStatus, authorize);
        	}else if( authorizeType.equals("Delete") ){
        		ticketStatusService.authorizeDeletion(SessionUtil.getUserSession(request), ticketStatus, authorize);
	    	}
        }catch(PawnException ex){
	    	logger.error("exception in authorization" + ex.getExceptionCode());
	    	response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, getLocale(request)).toString());
	    	return null;
        }

        if(authorize == true){
        	response.getWriter().write(StrutsFormValidateUtil.getMessageAuthorizeSuccess(messageResources).toString());
        }else{
        	response.getWriter().write(StrutsFormValidateUtil.getMessageRejectSuccess(messageResources).toString());
        }

		return null;
	}

	public ActionForward getAjaxData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to getGridData method *****");
		String recordId = request.getParameter("recordId");

		JSONArray mainArray = new JSONArray();

		if(recordId!=null && recordId!=""){
			TicketStatus ticketStatus = ticketStatusService.geTicketStatusById(SessionUtil.getUserSession(request), Integer.parseInt(recordId));
			mainArray.put(ticketStatus.getCode());
			mainArray.put(ticketStatus.getDescription());
			mainArray.put(ticketStatus.getRecordId());
			mainArray.put(ticketStatus.getVersion());
		}else{
			List<TicketStatus> list = (List<TicketStatus>)ticketStatusService.getAllTicketStatus(SessionUtil.getUserSession(request), getQueryCriteriaList(request)).getDataList();
			for(TicketStatus status:list){
				JSONArray subArray = new JSONArray();
				subArray.put(status.getCode());
				subArray.put(status.getDescription());
				subArray.put(status.getRecordId());
				subArray.put(status.getVersion());

				mainArray.put(subArray);
			}
		}

		response.getWriter().write(mainArray.toString());
		logger.debug("**** Leaving from getGridData method *****");
		return null;
	}

	public ActionForward getAuthorizeData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONArray mainArray = new JSONArray();

		List<QueryCriteria> criteriaList = getAuthorizeQueryCriteriaList(request);
		criteriaList.add(new QueryCriteria("recordStatus",Oparator.GRATERTHAN,RecordStatusEnum.ACTIVE.getCode()));
		List<TicketStatus> list = (List<TicketStatus>)ticketStatusService.getAllTicketStatus(SessionUtil.getUserSession(request), criteriaList).getDataList();

		for(TicketStatus status:list){
			JSONArray subArray = new JSONArray();
			subArray.put(status.getCode());
			subArray.put(status.getDescription());
			subArray.put(getRecordStatusString(status.getRecordStatus()));
			subArray.put(status.getRecordId());
			subArray.put(status.getVersion());

			mainArray.put(subArray);
		}

		response.getWriter().write(mainArray.toString());
		return null;
	}
}
