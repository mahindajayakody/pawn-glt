package com.dass.pawning.web.action;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dass.core.exception.PawnException;
import com.dass.core.util.MasterAction;
import com.dass.core.util.SessionUtil;
import com.dass.core.util.StrutsFormValidateUtil;
import com.dass.pawning.domain.DueFrom;
import com.dass.pawning.domain.DueType;
import com.dass.pawning.domain.TicketArticle;
import com.dass.pawning.dto.InfoConsoleDTO;
import com.dass.pawning.service.DueTypeService;
import com.dass.pawning.service.InfoconsoleService;
import com.dass.pawning.service.RedeemService;

public class RenewAction extends MasterAction {
	private final static Logger logger = Logger.getLogger(RenewAction.class);	
	private static DecimalFormat decimalFormat = new DecimalFormat();
	static{
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setMinimumFractionDigits(2);
	}
	
	private RedeemService redeemService;
	private InfoconsoleService infoconsoleService;
	private DueTypeService dueTypeService;
	
	public void setRedeemService(RedeemService redeemService) {
		this.redeemService = redeemService;
	}
	public void setInfoconsoleService(InfoconsoleService infoconsoleService) {
		this.infoconsoleService = infoconsoleService;
	}
	public void setDueTypeService(DueTypeService dueTypeService) {
		this.dueTypeService = dueTypeService;
	}
	
	public ActionForward renewTicket(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to redeemTicket method ****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");
		String ticketId = request.getParameter("ticketId");

		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{
        	try {
        		redeemService.redeem(SessionUtil.getUserSession(request), Integer.parseInt(ticketId));
        		JSONObject messageObject = new JSONObject();
            	try{
            		messageObject.put("success",messageResources.getMessage("msg.redeemsuccess"));
            	}catch(JSONException jex){}
        		response.getWriter().write(messageObject.toString());
			} catch (PawnException ex) {
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
			}
        }

		logger.debug("**** Leaving from redeemTicket method ****");
		return null;
	}
	
	public ActionForward getInfoData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageResources messageResources = getResources(request,"message");
		InfoConsoleDTO consoleDTO = null;
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));
		
		try {
			consoleDTO = infoconsoleService.getInfoConsoleData(SessionUtil.getUserSession(request), ticketId);			
		} catch (PawnException e) {
			response.getWriter().write(StrutsFormValidateUtil.getAJAXErrorMessage(e, messageResources, request.getLocale()));
			return null;
		}
		
		JSONObject object = new JSONObject();
		object.put("pawnerCode", consoleDTO.getPawnerCode());
		object.put("pawnerName", consoleDTO.getPawnerName());
		object.put("address", consoleDTO.getAddress());
		object.put("pawnAdvance", decimalFormat.format(consoleDTO.getPawnAdvance()));
		object.put("marketValue", decimalFormat.format(consoleDTO.getMarketValue()));
		object.put("actualDisValue", decimalFormat.format(consoleDTO.getActualDisValue()));
		object.put("totalNetWeight", consoleDTO.getTotalNetWeight());
		object.put("ticketDate", StrutsFormValidateUtil.parseString(consoleDTO.getTicketDate()));
		//object.put("authorizeDate", StrutsFormValidateUtil.parseString(consoleDTO.getTicketDate()));
		object.put("expiraryDate", StrutsFormValidateUtil.parseString(consoleDTO.getExpiraryDate()));
		//object.put("printedDate", StrutsFormValidateUtil.parseString(consoleDTO.getTicketDate()));
		object.put("schemeCode", consoleDTO.getSchemeCode());
		object.put("interestCode", consoleDTO.getInterestCode());
		object.put("schemeDesc", consoleDTO.getSchemeDescription());
		object.put("interestId", consoleDTO.getInterestId());
		object.put("totalreceipts", decimalFormat.format(consoleDTO.getTotalReceiptAmount()));
		
		JSONArray mainArray = new JSONArray();
		for(TicketArticle article:consoleDTO.getTicketArticleList()){
			JSONArray array = new JSONArray();
			array.put("");
			array.put(article.getArticleDescription());
			array.put(article.getNetWeight());
			array.put(decimalFormat.format(article.getAssessedValue()));
			array.put(article.getNoOfItem());
			array.put(article.getNoOfItem());
			mainArray.put(array);
		}
		
		object.put("ticketArticleList", mainArray.toString());
		
		List<DueType> dueTypeList = dueTypeService.getAllDueType(SessionUtil.getUserSession(request), null).getDataList();
		Map<Integer, DueType> dueMap = new HashMap<Integer, DueType>();
		
		for(DueType dueType:dueTypeList)
			dueMap.put(dueType.getDueTypeId(), dueType);
		
		mainArray = new JSONArray();
		for(DueFrom dueFrom:consoleDTO.getDueFromList()){
			JSONArray array = new JSONArray();
			//array.put(dueTypeService.getDueTypeById(SessionUtil.getUserSession(request), dueFrom.getDueTypeId()).getDescription());
			DueType dueType = dueMap.get(dueFrom.getDueTypeId());
			array.put(dueType.getDescription());
			array.put(decimalFormat.format(dueFrom.getDueAmount()));
			array.put(decimalFormat.format(dueFrom.getPaidAmount()));
			array.put(decimalFormat.format(dueFrom.getBalanceAmount()));
			array.put(dueType.getDueType());
			mainArray.put(array);
		}
		object.put("dueFromList", mainArray.toString());
		
		response.getWriter().write(object.toString());
		return null;
	}
	
	protected ActionForward getAuthorizeData(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return null;
	}
}
