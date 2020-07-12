package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Receipt;
import com.dass.pawning.dto.ReceiptDTO;

public interface ReceiptService {
	ReceiptDTO getReceiptTicketData(UserConfig userConfig,int ticketId )throws PawnException;
	String createReceipt(UserConfig userConfig,Receipt receipt)throws PawnException;
	List<Receipt> getAllReceiptByTicketId(UserConfig userConfig,int ticketId)throws PawnException;
	DataBag getAllReceipt(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException;
	void updateReceiptPrint(UserConfig userConfig, String receiptNo )throws PawnException;
	void cancelReceipt(UserConfig userConfig, int receiptId)throws PawnException;
}
