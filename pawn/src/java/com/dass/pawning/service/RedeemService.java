package com.dass.pawning.service;

import com.dass.core.exception.PawnException;
import com.dass.core.util.UserConfig;

public interface RedeemService {
	public void redeem(UserConfig userConfig,int ticketId)throws PawnException;
}
