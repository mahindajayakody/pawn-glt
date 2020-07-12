package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.LocationDAO;
import com.dass.pawning.domain.Location;
import com.dass.pawning.service.LocationService;

public class LocationServiceImpl extends AuthorizableServiceImpl implements LocationService {
	private LocationDAO locationDAO;

	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	public void createLocation(UserConfig userConfig, Location ticketStatus) throws PawnException {
		locationDAO.createLocation(userConfig, ticketStatus);
	}

	public void removeLocation(UserConfig userConfig, Location ticketStatus) throws PawnException {
		locationDAO.removeLocation(userConfig, ticketStatus);
	}

	public void updateLocation(UserConfig userConfig, Location ticketStatus) throws PawnException {
		locationDAO.updateLocation(userConfig, ticketStatus);
	}

	public Location getLocationById(UserConfig userConfig, int recordId) throws PawnException {
		return locationDAO.getLocationById(userConfig, recordId);
	}

	public Location getLocationByCode(UserConfig userConfig, String code) throws PawnException {
		return locationDAO.getLocationByCode(userConfig, code);
	}

	public DataBag getAllLocation(UserConfig userConfig, List<QueryCriteria> criteriaList) throws PawnException {
		return locationDAO.getAllLocation(userConfig, criteriaList);
	}
}
