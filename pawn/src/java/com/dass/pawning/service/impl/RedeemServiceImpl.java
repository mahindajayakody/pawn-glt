package com.dass.pawning.service.impl;

import com.dass.core.exception.PawnException;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.RedeemDAO;
import com.dass.pawning.service.RedeemService;

public class RedeemServiceImpl implements RedeemService {
	private RedeemDAO redeemDAO;
	
	public void setRedeemDAO(RedeemDAO redeemDAO) {
		this.redeemDAO = redeemDAO;
	}
	
	public void redeem(UserConfig userConfig, int ticketId)throws PawnException {
		redeemDAO.redeem(userConfig, ticketId);
	}
}
