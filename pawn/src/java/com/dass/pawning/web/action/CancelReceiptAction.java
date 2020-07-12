package com.dass.pawning.web.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import org.json.JSONObject;

import com.dass.core.exception.PawnException;
import com.dass.core.util.MasterAction;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.SessionUtil;
import com.dass.core.util.StrutsFormValidateUtil;
import com.dass.core.util.QueryCriteria.Oparator;
import com.dass.pawning.domain.Receipt;
import com.dass.pawning.dto.ReceiptDTO;
import com.dass.pawning.service.ReceiptService;
import com.dass.pawning.service.TicketService;
import com.dass.pawning.util.ReceiptStatusEnum;


public class CancelReceiptAction extends MasterAction {
	private static final Logger logger = Logger.getLogger(CancelReceiptAction.class);	
	private static DecimalFormat decimalFormat = new DecimalFormat();
	private static final SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	static{
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setMinimumFractionDigits(2);
	}

	private ReceiptService receiptService;

	public void setReceiptService(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}
	
	public ActionForward cancelReceipt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to cancelReceipt method ****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{        	        	
        	try {
        		receiptService.cancelReceipt(SessionUtil.getUserSession(request), Integer.parseInt(request.getParameter("recordId")));
        		response.getWriter().write(StrutsFormValidateUtil.getMessageCancelSuccess(messageResources).toString());
			} catch (PawnException ex) {
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
			}        	
        }		
		
		logger.debug("**** Leaving from createReceipt method ****");
		return null;
	}

	public ActionForward getAjaxData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to getAjaxData method ****");
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));
		
		ReceiptDTO receiptDTO 	= receiptService.getReceiptTicketData(SessionUtil.getUserSession(request), ticketId);
		List<QueryCriteria> criteriaList = new ArrayList<QueryCriteria>();
		criteriaList.add(new QueryCriteria("status", Oparator.EQUAL, ReceiptStatusEnum.ACTIVE.getCode()));
		criteriaList.add(new QueryCriteria("ticketId", Oparator.EQUAL, ticketId));
		List<Receipt> receiptList= receiptService.getAllReceipt(SessionUtil.getUserSession(request), criteriaList).getDataList();
		
		JSONObject object = new JSONObject();
		object.put("clientId", receiptDTO.getClientId());
		object.put("clientCode", receiptDTO.getClientCode());
		object.put("clientName", receiptDTO.getClientName());
		object.put("address", receiptDTO.getAddress());
		
		JSONArray mainArray = new JSONArray();
		for(Receipt receipt : receiptList){
			JSONArray array = new JSONArray();
			array.put(receipt.getReceiptNo());
			array.put(decimalFormat.format(receipt.getReceiptAmt()));
			array.put(sdfDate.format(receipt.getReceiptDate()));
			array.put(receipt.getRecordId());
			array.put(receipt.getVersion());
			mainArray.put(array);
		}
		
		object.put("receipts", mainArray);
		
		response.getWriter().write(object.toString());
		logger.debug("**** Leaving from getAjaxData method ****");
		return null;
	}

	@Override
	protected ActionForward getAuthorizeData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
}
