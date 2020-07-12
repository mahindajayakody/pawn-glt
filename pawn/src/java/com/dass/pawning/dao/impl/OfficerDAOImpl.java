package com.dass.pawning.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

import com.dass.core.bean.AccessBranch;
import com.dass.core.exception.CommonDataAccessException;
import com.dass.core.util.DataBag;
import com.dass.core.util.MasterDAOSupport;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.OfficerDAO;
import com.dass.pawning.domain.Branch;
import com.dass.pawning.domain.Officer;
import com.dass.pawning.domain.Pawner;
import com.dass.pawning.domain.UserGroup;

public class OfficerDAOImpl extends MasterDAOSupport implements OfficerDAO {
	private static final Logger logger = Logger.getLogger(OfficerDAOImpl.class);

	public void createOfficer(UserConfig userConfig, Officer officer) {
		logger.debug("**** Enter in to createOfficer method ****");
		Criteria criteria = getSession().createCriteria(Officer.class);
		criteria.add(Restrictions.eq("userName", officer.getUserName()));
		criteria.add(Restrictions.eq("companyId", userConfig.getCompanyId()));
		criteria.setProjection(Projections.rowCount());

		int count = ((Integer) criteria.uniqueResult()).intValue();
        if (count !=0)
            throw new CommonDataAccessException("errors.recordexist");


        officer.setBranch((Branch)getSession().get(Branch.class , officer.getBranch().getBranchId()));
        officer.setPawner((Pawner)getSession().get(Pawner.class , officer.getPawner().getPawnerId()));
        officer.setUserGroup((UserGroup)getSession().get(UserGroup.class , officer.getUserGroup().getUserGroupId()));
        officer.setCompanyId(userConfig.getCompanyId());

//        initializeDomainObject(userConfig, officer);
//        getHibernateTemplate().save(officer);
        create(userConfig, officer);
        
        for (AccessBranch accBranch:officer.getAccessBranchCollection()){
        	accBranch.setOfficerId(officer.getOfficerId());
        	getHibernateTemplate().save(accBranch);
        }
        
		logger.debug("**** Leaving from createOfficer method ****");
	}

	public void removeOfficer(UserConfig userConfig, Officer officer) {
		logger.debug("**** Enter in to removeOfficer method ****");
//		Officer status = (Officer)getHibernateTemplate().get(Officer.class, Integer.valueOf(officer.getRecordId()));
//		status.setVersion(officer.getVersion());
//		getHibernateTemplate().delete(status);
		remove(userConfig, officer);
		logger.debug("**** Leaving from removeOfficer method ****");
	}

	public void updateOfficer(UserConfig userConfig, Officer officer) {
		logger.debug("**** Enter in to updateOfficer method ****");
        officer.setBranch((Branch)getSession().get(Branch.class , officer.getBranch().getBranchId()));
        officer.setPawner((Pawner)getSession().get(Pawner.class , officer.getPawner().getPawnerId()));
        officer.setUserGroup((UserGroup)getSession().get(UserGroup.class , officer.getUserGroup().getUserGroupId()));
		officer.setCompanyId(userConfig.getCompanyId());
		
//		initializeDomainObject(userConfig, officer);
//		getHibernateTemplate().update(officer);
		update(userConfig, officer);
		
		Query query = getSession().createQuery("DELETE FROM AccessBranch WHERE officerId=:officerId");
		query.setInteger("officerId", officer.getOfficerId());
		query.executeUpdate();
		
		for (AccessBranch accBranch:officer.getAccessBranchCollection()){
        	accBranch.setOfficerId(officer.getOfficerId());
        	getHibernateTemplate().save(accBranch);
        }
		
		logger.debug("**** Leaving from updateOfficer method ****");
	}

	public Officer getOfficerById(UserConfig userConfig,int recordId){
		logger.debug("**** Enter in to getOfficerById method ****");
		Officer officer = null;
		Criteria criteria = getSession().createCriteria(Officer.class);
		criteria.add(Restrictions.eq("officerId", recordId));
		criteria.setFetchMode("pawner", FetchMode.JOIN);
		criteria.setFetchMode("branch", FetchMode.JOIN);
		criteria.setFetchMode("userGroup", FetchMode.JOIN);
		criteria.setFetchMode("accessBranchCollection", FetchMode.JOIN);
		officer = (Officer)criteria.uniqueResult();

		if(officer == null)
			throw new CommonDataAccessException("errors.recordnotfound");

		logger.debug("**** Leaving from getOfficerById method ****");
		return officer;
	}

	public Officer getOfficerByUserName(UserConfig userConfig,String code){
		logger.debug("**** Enter in to getOfficerByUserName method ****");
		Officer officer = null;
		Criteria criteria = getSession().createCriteria(Officer.class);
		criteria.add(Restrictions.eq("userName", code));
		criteria.add(Restrictions.eq("companyId", userConfig.getCompanyId()));
		criteria.setFetchMode("pawner", FetchMode.JOIN);
		criteria.setFetchMode("branch", FetchMode.JOIN);
		criteria.setFetchMode("userGroup", FetchMode.JOIN);
		criteria.setFetchMode("accessBranchCollection", FetchMode.JOIN);

		officer = (Officer)criteria.uniqueResult();

		if(officer == null)
			throw new CommonDataAccessException("errors.recordnotfound");

		logger.debug("**** Leaving from getOfficerByUserName method ****");
		return officer;
	}

	public DataBag getAllOfficer(UserConfig userConfig,List<QueryCriteria> criteriaList){
		logger.debug("**** Enter in to getAllOfficer method ****");
		DataBag officerBag = null;
		Criteria criteria = getSession().createCriteria(Officer.class);
		criteria.add(Restrictions.eq("companyId", userConfig.getCompanyId()));
		criteria.setFetchMode("pawner", FetchMode.JOIN);
		criteria.setFetchMode("branch", FetchMode.JOIN);
		criteria.setFetchMode("userGroup", FetchMode.JOIN);
		criteria.setFetchMode("accessBranchCollection", FetchMode.JOIN);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		officerBag = getDataList(getHibernateTemplate(), criteriaList,criteria );
		logger.debug("**** Leaving from getAllOfficer method ****");
		return officerBag;
	}
	public Officer getOfficerByPawnerId(UserConfig userConfig,int pawnerId){
		logger.debug("**** Enter in to getOfficerByPawnerId method ****");
		Officer officer = null;
		Criteria criteria = getSession().createCriteria(Officer.class);
		criteria.add(Restrictions.eq("pawner.pawnerId", pawnerId));
		criteria.setFetchMode("pawner", FetchMode.JOIN);
		officer = (Officer)criteria.uniqueResult();

		if(officer == null)
			throw new CommonDataAccessException("errors.recordnotfound");

		logger.debug("**** Leaving from getOfficerByPawnerId method ****");
		return officer;
	}


}
