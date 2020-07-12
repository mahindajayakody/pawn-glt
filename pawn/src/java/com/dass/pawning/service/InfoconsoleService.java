package com.dass.pawning.service;

import com.dass.core.exception.PawnException;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dto.InfoConsoleDTO;

public interface InfoconsoleService {
	public InfoConsoleDTO getInfoConsoleData(UserConfig userConfig,int ticketId)throws PawnException;
}
