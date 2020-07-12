package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.OverPayment;

public interface OverPaymentDAO extends AuthorizableDAO{
	public void createOverPayment(UserConfig userConfig,OverPayment overPayment);

	public OverPayment getOverPaymentById(UserConfig userConfig,int recordId);
	public DataBag getAllOverPayment(UserConfig userConfig,List<QueryCriteria> criteriaList);
	public DataBag getAllOverPaymentByTicket(UserConfig userConfig,int ticketId);
	public DataBag getAllOverPaymentByReceipt(UserConfig userConfig,int receiptId);
}
