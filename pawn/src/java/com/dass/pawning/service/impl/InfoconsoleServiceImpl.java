package com.dass.pawning.service.impl;

import com.dass.core.exception.PawnException;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.InfoconsoleDAO;
import com.dass.pawning.dto.InfoConsoleDTO;
import com.dass.pawning.service.InfoconsoleService;

public class InfoconsoleServiceImpl implements InfoconsoleService {
	private InfoconsoleDAO infoconsoleDAO;

	public void setInfoconsoleDAO(InfoconsoleDAO infoconsoleDAO) {
		this.infoconsoleDAO = infoconsoleDAO;
	}

	public InfoConsoleDTO getInfoConsoleData(UserConfig userConfig, int ticketId) throws PawnException {
		return infoconsoleDAO.getInfoConsoleData(userConfig, ticketId);
	}
}
