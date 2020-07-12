package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Location;

public interface LocationService extends AuthorizableService{
	public void createLocation(UserConfig userConfig,Location ticketStatus)throws PawnException;
	public void updateLocation(UserConfig userConfig,Location ticketStatus)throws PawnException;
	public void removeLocation(UserConfig userConfig,Location ticketStatus)throws PawnException;

	public Location getLocationById(UserConfig userConfig,int recordId)throws PawnException;
	public Location getLocationByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllLocation(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;

}
