package com.dass.pawning.dao.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

import com.dass.core.exception.CommonDataAccessException;
import com.dass.core.util.DataBag;
import com.dass.core.util.MasterDAOSupport;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.PawnerDAO;
import com.dass.pawning.domain.Article;
import com.dass.pawning.domain.MapPawner;
import com.dass.pawning.domain.Pawner;
import com.dass.pawning.domain.PawnerType;
import com.dass.pawning.util.TicketStatusEnum;

public class PawnerDAOImpl extends MasterDAOSupport implements PawnerDAO{
	private static final Logger logger = Logger.getLogger(PawnerDAOImpl.class);

	public void createPawner(UserConfig userConfig, Pawner pawner) {
		logger.debug("**** Enter in to createPawner method ****");
		Criteria criteria = getSession().createCriteria(Pawner.class);
		criteria.add(Restrictions.eq("idOrBrNo", pawner.getIdOrBrNo()));
		criteria.setProjection(Projections.rowCount());
		
		int count = ((Integer) criteria.uniqueResult()).intValue();
        if (count !=0)
            throw new CommonDataAccessException("errors.recordexist");
		
        initializeDomainObject(userConfig, pawner);
        getHibernateTemplate().save(pawner);
        
        DecimalFormat format = new DecimalFormat("00000000");
        pawner.setPawnerCode(format.format(pawner.getPawnerId()));
        
        for(MapPawner mapPawner:pawner.getMapPawner()){
        	initializeDomainObject(userConfig, mapPawner);
        	mapPawner.setPawner(pawner);
        	mapPawner.setPawnerId(pawner.getPawnerId());
        	mapPawner.setPawnerType((PawnerType)getSession().get(PawnerType.class,mapPawner.getPawnerTypeId()));
        	getHibernateTemplate().save(mapPawner);
        }
		logger.debug("**** Leaving from createPawner method ****");
	}

	public void updatePawner(UserConfig userConfig, Pawner pawner) {
		logger.debug("**** Enter in to updatePawner method ****");
		initializeDomainObject(userConfig, pawner);
		getHibernateTemplate().update(pawner);
		
		String hql = "DELETE FROM MapPawner map WHERE map.pawnerId=" + pawner.getPawnerId();
		getSession().createQuery(hql).executeUpdate();
		
		for(MapPawner mapPawner:pawner.getMapPawner()){
        	initializeDomainObject(userConfig, mapPawner);
        	mapPawner.setPawner(pawner);  
        	mapPawner.setPawnerId(pawner.getPawnerId());
        	mapPawner.setPawnerType((PawnerType)getSession().get(PawnerType.class,mapPawner.getPawnerTypeId()));
        	getHibernateTemplate().save(mapPawner);
        }
		logger.debug("**** Leaving from updatePawner method ****");		
	}
	
	public DataBag getAllPawner(UserConfig userConfig, List<QueryCriteria> criteriaList) {
		logger.debug("**** Enter in to getAllPawner method ****");
		DataBag pawnerBag = null;		
		Criteria criteria = getSession().createCriteria(Pawner.class);
		criteria.setFetchMode("mapPawner", FetchMode.JOIN);
		criteria.setFetchMode("mapPawner.pawnerType", FetchMode.JOIN);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		pawnerBag = getDataList(getHibernateTemplate(), criteriaList,criteria );
		logger.debug("**** Leaving from getAllPawner method ****");
		return pawnerBag;
	}

	public Pawner getPawnerByCode(UserConfig userConfig, String code) {
		logger.debug("**** Enter in to getPawnerByCode method ****");
		Pawner pawner = null;
		Criteria criteria = getSession().createCriteria(Pawner.class);
		criteria.add(Restrictions.eq("pawnerCode", code));
		criteria.setFetchMode("mapPawner",FetchMode.JOIN);
		criteria.setFetchMode("mapPawner.pawnerType",FetchMode.JOIN);
		pawner = (Pawner)criteria.uniqueResult();
		
		if(pawner == null)
			throw new CommonDataAccessException("errors.recordnotfound");
		
		logger.debug("**** Leaving from getPawnerByCode method ****");
		return pawner;
	}

	public Pawner getPawnerById(UserConfig userConfig, int recordId) {
		logger.debug("**** Enter in to getPawnerById method ****");
		Pawner pawner = null;
		Criteria criteria = getSession().createCriteria(Pawner.class);
		criteria.add(Restrictions.eq("pawnerId", recordId));
		criteria.setFetchMode("mapPawner",FetchMode.JOIN);
		criteria.setFetchMode("mapPawner.pawnerType",FetchMode.JOIN);
		pawner = (Pawner)criteria.uniqueResult();		
		
		if(pawner == null)
			throw new CommonDataAccessException("errors.recordnotfound");
		
		logger.debug("**** Leaving from getPawnerById method ****");
		return pawner;
	}
	
	public Pawner getPawnerByCodeAndType(UserConfig userConfig, String code,String type) {	
		logger.debug("**** Enter in to getPawnerByCodeAndType method ****");
		MapPawner mapPawner = null;
		Criteria criteria = getSession().createCriteria(MapPawner.class);
		criteria.add(Restrictions.eq("pawner.pawnerCode", code));
		criteria.add(Restrictions.eq("pawnerType.code", type));
		criteria.setFetchMode("pawner", FetchMode.JOIN);
		criteria.setFetchMode("pawnerType", FetchMode.JOIN);
		mapPawner = (MapPawner)criteria.uniqueResult();
		
		if(mapPawner == null)
			throw new CommonDataAccessException("errors.recordnotfound");
		
		logger.debug("**** Leaving from getPawnerByCodeAndType method ****");
		return mapPawner.getPawner();
	}
	
	public Pawner getPawnerByIdOrBr(UserConfig userConfig, String idOrBr) {
		logger.debug("**** Enter in to getPawnerByIdOrBr method ****");
		Pawner pawner = null;
		Criteria criteria = getSession().createCriteria(Pawner.class);
		criteria.add(Restrictions.eq("idOrBrNo", idOrBr));
		criteria.setFetchMode("mapPawner",FetchMode.JOIN);
		criteria.setFetchMode("mapPawner.pawnerType",FetchMode.JOIN);
		pawner = (Pawner)criteria.uniqueResult();
		
		if(pawner == null)
			throw new CommonDataAccessException("errors.recordnotfound");
		
		logger.debug("**** Leaving from getPawnerByIdOrBr method ****");
		return pawner;
	}
	
	public List getPawnerName(UserConfig userConfig, int recordId){
		String hql = "SELECT pawnerCode,title,initials,surName FROM Pawner WHERE pawnerId=" + recordId;
		Query query = getSession().createQuery(hql);
		List list = query.list();
		
		if(list == null)
			throw new CommonDataAccessException("errors.recordnotfound");
		return list;		
	}
	
	public List<Pawner> getAllPawnerByType(UserConfig userConfig, String type) {
		logger.debug("**** Enter in to getAllPawnerByType method ****");
		List<Pawner> list = null;
		String hql = "SELECT DISTINCT map.pawner FROM MapPawner map INNER JOIN map.pawner WHERE map.pawnerType.code=:code " +
					 " AND map.companyId=:companyId";
		
		Query query = getSession().createQuery(hql);
		query.setReadOnly(true);
		query.setString("code", type);
		query.setInteger("companyId", userConfig.getCompanyId());
		list = query.list();
		
		logger.debug("**** Leaving from getAllPawnerByType method ****");
		return list;
	}
	
	public List<Pawner> getAllPawnerBySQL(UserConfig userConfig, String sql) {
		logger.debug("**** Enter in to getAllPawnerByType method ****");
		List<Pawner> list = null;
		String hql = "SELECT DISTINCT map.pawner FROM MapPawner map INNER JOIN map.pawner INNER JOIN map.pawnerType WHERE " +
					 " map.companyId=:companyId " + sql;
		
		Query query = getSession().createQuery(hql);
		query.setReadOnly(true);
		query.setInteger("companyId", userConfig.getCompanyId());
		
		list = query.list();
		
		logger.debug("**** Leaving from getAllPawnerByType method ****");
		return list;
	}
}
