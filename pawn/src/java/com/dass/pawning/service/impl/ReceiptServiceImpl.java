package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.ReceiptDAO;
import com.dass.pawning.domain.Receipt;
import com.dass.pawning.dto.ReceiptDTO;
import com.dass.pawning.service.ReceiptService;

public class ReceiptServiceImpl implements ReceiptService {
	private ReceiptDAO receiptDAO;
	
	public void setReceiptDAO(ReceiptDAO receiptDAO) {
		this.receiptDAO = receiptDAO;
	}

	public ReceiptDTO getReceiptTicketData(UserConfig userConfig, int ticketId) throws PawnException {		
		return receiptDAO.getReceiptTicketData(userConfig, ticketId);
	}

	public String createReceipt(UserConfig userConfig, Receipt receipt) throws PawnException {		
		return receiptDAO.createReceipt(userConfig, receipt);
	}

	public List<Receipt> getAllReceiptByTicketId(UserConfig userConfig, int ticketId) throws PawnException {		
		return receiptDAO.getAllReceiptByTicketId(userConfig, ticketId);
	}
	public DataBag getAllReceipt(UserConfig userConfig, List<QueryCriteria> criteriaList) throws PawnException {
		return receiptDAO.getAllReceipt(userConfig, criteriaList);
	}
	public void updateReceiptPrint(UserConfig userConfig, String receiptNo ){
		 receiptDAO.updateReceiptPrint(userConfig, receiptNo);
	}

	public void cancelReceipt(UserConfig userConfig, int receiptId)	throws PawnException {
		receiptDAO.cancelReceipt(userConfig, receiptId);
	}
}
