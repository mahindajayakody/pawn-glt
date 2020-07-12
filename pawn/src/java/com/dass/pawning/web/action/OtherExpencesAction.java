package com.dass.pawning.web.action;


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
import com.dass.pawning.domain.AuctionExpences;
import com.dass.pawning.domain.DueFrom;
import com.dass.pawning.domain.DueType;
import com.dass.pawning.service.DueFromService;
import com.dass.pawning.service.DueTypeService;

public class OtherExpencesAction extends MasterAction {
	private static final Logger logger= Logger.getLogger(AuctionExpences.class);
	private DueTypeService dueTypeService;
	private DueFromService dueFromService;
	
	
	public void setDueFromService(DueFromService dueFromService) {
		this.dueFromService = dueFromService;
	}

	public void setDueTypeService(DueTypeService dueTypeService) {
		this.dueTypeService = dueTypeService;
	}

	public void populateDomaiObject(HttpServletRequest request,HttpServletResponse response,DueFrom dueFrom) throws Exception{
		
		dueFrom.setBranchId(getUserSession(request).getBranchId());
		dueFrom.setCompanyId(getUserSession(request).getCompanyId());
		dueFrom.setDueTypeId(Integer.parseInt(request.getParameter("dueTypeId")));
		dueFrom.setTicketId(Integer.parseInt(request.getParameter("ticketId")));
		dueFrom.setBalanceAmount(Double.parseDouble(request.getParameter("amount")));
		dueFrom.setDueAmount(Double.parseDouble(request.getParameter("amount")));
		dueFrom.setPaidAmount(0);
		if (request.getParameter("version")!=null) {
			dueFrom.setVersion(Integer.parseInt(request.getParameter("version")));
		}
		if (request.getParameter("recordId")!=null) {
			dueFrom.setRecordId(Integer.parseInt(request.getParameter("version")));
		}
		
	}
	
	public ActionForward create(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.debug("**** Enter in to create method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if (!validateForm.isEmpty())
			response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm, messageResources, getLocale(request), null).toString());
		else{
			DueFrom dueFrom = new DueFrom();
			populateDomaiObject(request, response, dueFrom);
		
			try{
				dueFromService.createDueFrom(getUserSession(request), dueFrom);
				response.getWriter().write(StrutsFormValidateUtil.getMessageCreateSuccess(messageResources).toString());
				
			}catch (PawnException ex){
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, getLocale(request)).toString());
			}
		}
		logger.debug("**** Leaving create method *****");
		return null;
	}
	
	public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.debug("**** Enter in to update method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if (!validateForm.isEmpty())
			response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm, messageResources, getLocale(request), null).toString());
		else{
			DueFrom dueFrom = new DueFrom();
			populateDomaiObject(request, response, dueFrom);
		
			try{
				dueFromService.updateDueFrom(getUserSession(request), dueFrom);
				response.getWriter().write(StrutsFormValidateUtil.getMessageUpdateSuccess(messageResources).toString());
				
			}catch (PawnException ex){
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, getLocale(request)).toString());
			}
		}
		logger.debug("**** Leaving update method *****");
		return null;
	}
	public ActionForward remove(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.debug("**** Enter in to remove method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if (!validateForm.isEmpty())
			response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm, messageResources, getLocale(request), null).toString());
		else{
			DueFrom dueFrom = new DueFrom();
			populateDomaiObject(request, response, dueFrom);
			try{
				dueFromService.removeDueFrom(getUserSession(request), dueFrom);
				response.getWriter().write(StrutsFormValidateUtil.getMessageDeleteSuccess(messageResources).toString());
				
			}catch (PawnException ex){
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, getLocale(request)).toString());
			}
		}
		logger.debug("**** Leaving remove method *****");
		return null;
	}
	
	public ActionForward authorize(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.debug("**** Enter in to authorize method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		String recordId = request.getParameter("recordId");
        String version  = request.getParameter("version");
        String authorizeType   = request.getParameter("authorizeMode");
        boolean authorize = Boolean.parseBoolean(request.getParameter("authorizeValue"));

        if((recordId==null)||(version==null)||(recordId.equals(""))||(version.equals(""))){
        	response.getWriter().write(StrutsFormValidateUtil.getMessageNotFound(messageResources).toString());
        	return null;
        }

		AuctionExpences auctionExpences=new AuctionExpences();
		auctionExpences.setVersion(Integer.parseInt(request.getParameter("version")));
		auctionExpences.setRecordId(Integer.parseInt(request.getParameter("recordId")));

        if(authorize==true){
        	response.getWriter().write(StrutsFormValidateUtil.getMessageAuthorizeSuccess(messageResources).toString());
        }else{
        	response.getWriter().write(StrutsFormValidateUtil.getMessageRejectSuccess(messageResources).toString());
        }

        logger.debug("**** Leaving from authorize method *****");
		return null;
	}

	public ActionForward getAjaxData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to getAjaxData method *****");
		String recordId = request.getParameter("recordId");
		String ticketId = request.getParameter("ticketId");
		JSONArray mainArray = new JSONArray();

		if(recordId!=null && recordId!="" && recordId!="0"){
			DueFrom dueFrom =dueFromService.getDueFromById(SessionUtil.getUserSession(request), Integer.parseInt(recordId));
			DueType dueType=dueTypeService.getDueTypeById(getUserSession(request),dueFrom.getDueTypeId());

			mainArray.put(dueType.getDueType());
			mainArray.put(dueType.getDescription());
			mainArray.put(dueFrom.getDueAmount());
			mainArray.put(dueFrom.getBalanceAmount());			
			mainArray.put(dueFrom.getPaidAmount());
			mainArray.put(dueType.getDueTypeId());
			mainArray.put(dueFrom.getBranchId());
			mainArray.put(dueFrom.getRecordId());
			mainArray.put(dueFrom.getVersion());
		}else{
			List<DueFrom> dueFromList = (List<DueFrom>)dueFromService.getAllDueFrom(getUserSession(request), Integer.parseInt(ticketId));
			for(DueFrom dueFrom:dueFromList){
				JSONArray subArray = new JSONArray();
				
				DueType dueType=dueTypeService.getDueTypeById(getUserSession(request),dueFrom.getDueTypeId());

				subArray.put(dueType.getDueType());
				subArray.put(dueType.getDescription());
				subArray.put(dueFrom.getDueAmount());
				subArray.put(dueFrom.getBalanceAmount());
				subArray.put(dueFrom.getPaidAmount());								
				subArray.put(dueType.getDueTypeId());
				subArray.put(dueFrom.getBranchId());
				subArray.put(dueFrom.getRecordId());
				subArray.put(dueFrom.getVersion());
				mainArray.put(subArray);
			}
		}

		response.getWriter().write(mainArray.toString());
		logger.debug("**** Leaving from getAjaxData method *****");
		return null;

	}

	public ActionForward getAuthorizeData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONArray mainArray = new JSONArray();

		List<QueryCriteria> criteriaList = getAuthorizeQueryCriteriaList(request);
		criteriaList.add(new QueryCriteria("recordStatus",Oparator.GRATERTHAN,RecordStatusEnum.ACTIVE.getCode()));
		List<DueFrom> dueFromList = (List<DueFrom>)dueFromService.getAllDueFrom(getUserSession(request), criteriaList).getDataList();
		for(DueFrom dueFrom:dueFromList){
			JSONArray subArray = new JSONArray();
			DueType dueType=dueTypeService.getDueTypeById(getUserSession(request),dueFrom.getDueTypeId());
			
			subArray.put(dueType.getDueType());
			subArray.put(dueType.getDescription());
			subArray.put(dueFrom.getBalanceAmount());
			subArray.put(dueFrom.getDueAmount());
			subArray.put(dueFrom.getPaidAmount());
			subArray.put(dueType.getDueTypeId());
			subArray.put(dueFrom.getBranchId());
			subArray.put(dueFrom.getRecordId());
			subArray.put(dueFrom.getVersion());
			mainArray.put(subArray);
		}
		response.getWriter().write(mainArray.toString());
		return null;
	}
	

}
