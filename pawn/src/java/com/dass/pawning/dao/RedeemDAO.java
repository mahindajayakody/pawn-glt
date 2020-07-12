package com.dass.pawning.dao;

import com.dass.core.util.UserConfig;

public interface RedeemDAO {
	public void redeem(UserConfig userConfig,int ticketId);
}
