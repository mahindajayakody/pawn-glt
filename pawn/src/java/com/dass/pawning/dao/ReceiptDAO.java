package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Receipt;
import com.dass.pawning.dto.ReceiptDTO;

public interface ReceiptDAO {
	ReceiptDTO getReceiptTicketData(UserConfig userConfig,int ticketId );
	String createReceipt(UserConfig userConfig,Receipt receipt);
	List<Receipt> getAllReceiptByTicketId(UserConfig userConfig,int ticketId);
	DataBag getAllReceipt(UserConfig userConfig,List<QueryCriteria> criteriaList);
	void updateReceiptPrint(UserConfig userConfig, String receiptNo );
	void cancelReceipt(UserConfig userConfig, int receiptId);	
}
