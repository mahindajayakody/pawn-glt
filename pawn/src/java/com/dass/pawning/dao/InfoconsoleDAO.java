package com.dass.pawning.dao;

import com.dass.core.util.UserConfig;
import com.dass.pawning.dto.InfoConsoleDTO;

public interface InfoconsoleDAO {
	public InfoConsoleDTO getInfoConsoleData(UserConfig userConfig,int ticketId);
}
