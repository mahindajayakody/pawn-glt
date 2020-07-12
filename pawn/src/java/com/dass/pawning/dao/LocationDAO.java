package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Location;

public interface LocationDAO extends AuthorizableDAO{
	public void createLocation(UserConfig userConfig,Location ticketStatus);
	public void updateLocation(UserConfig userConfig,Location ticketStatus);
	public void removeLocation(UserConfig userConfig,Location ticketStatus);

	public Location getLocationById(UserConfig userConfig,int recordId);
	public Location getLocationByCode(UserConfig userConfig,String code);
	public DataBag getAllLocation(UserConfig userConfig,List<QueryCriteria> criteriaList);

}
