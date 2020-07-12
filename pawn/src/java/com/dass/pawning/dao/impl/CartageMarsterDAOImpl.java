package com.dass.pawning.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.dass.core.exception.CommonDataAccessException;
import com.dass.core.util.DataBag;
import com.dass.core.util.MasterDAOSupport;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.CartageMarsterDAO;
import com.dass.pawning.domain.CartageMarster;
import com.dass.pawning.domain.Location;

public class CartageMarsterDAOImpl extends MasterDAOSupport implements CartageMarsterDAO {
	private static final Logger logger = Logger.getLogger(CartageMarsterDAOImpl.class);

	public void createCartageMarster(UserConfig userConfig,CartageMarster cartageMarster) {
		logger.debug("**** Enter in to createCartageMarster method ****");
		Criteria criteria = getSession().createCriteria(CartageMarster.class);
		criteria.add(Restrictions.eq("code", cartageMarster.getCode()));
		criteria.setProjection(Projections.rowCount());

		int count = ((Integer) criteria.uniqueResult()).intValue();
        if (count !=0)
            throw new CommonDataAccessException("errors.recordexist");

//        initializeDomainObject(userConfig, cartageMarster);
//        getHibernateTemplate().save(cartageMarster);
        create(userConfig, cartageMarster);
		logger.debug("**** Leaving from createCartageMarster method ****");
	}

	public void removeCartageMarster(UserConfig userConfig,CartageMarster cartageMarster) {
		logger.debug("**** Enter in to removeTicketStatus method ****");
//		CartageMarster status = (CartageMarster)getHibernateTemplate().get(CartageMarster.class, Integer.valueOf(cartageMarster.getRecordId()));
//		status.setVersion(cartageMarster.getVersion());
//		getHibernateTemplate().delete(status);
		remove(userConfig, cartageMarster);
		logger.debug("**** Leaving from removeTicketStatus method ****");
	}

	public void updateCartageMarster(UserConfig userConfig,CartageMarster cartageMarster) {
		logger.debug("**** Enter in to updateTicketStatus method ****");
//		initializeDomainObject(userConfig, cartageMarster);
//		getHibernateTemplate().update(cartageMarster);
		update(userConfig, cartageMarster);
		logger.debug("**** Leaving from updateTicketStatus method ****");
	}

	public DataBag getAllCartageMarster(UserConfig userConfig,List<QueryCriteria> criteriaList) {
		logger.debug("**** Enter in to getAllCartageMarster method ****");
		DataBag cartageMarsterBag = null;
		Criteria criteria = getSession().createCriteria(CartageMarster.class);
		cartageMarsterBag = getDataList(getHibernateTemplate(), criteriaList,criteria );
		logger.debug("**** Leaving from getAllCartageMarster method ****");
		return cartageMarsterBag;
	}

	public CartageMarster getCartageMarsterByCode(UserConfig userConfig,String code) {
		logger.debug("**** Enter in to getCartageMarsterByCode method ****");
		CartageMarster cartageMarster = null;
		Criteria criteria = getSession().createCriteria(CartageMarster.class);
		criteria.add(Restrictions.eq("code", code));
		cartageMarster = (CartageMarster)criteria.uniqueResult();

		if(cartageMarster == null)
			throw new CommonDataAccessException("errors.recordnotfound");

		logger.debug("**** Leaving from getCartageMarsterByCode method ****");
		return cartageMarster;
	}

	public CartageMarster getCartageMarsterById(UserConfig userConfig,int recordId) {
		logger.debug("**** Enter in to getCartageMarsterById method ****");
		CartageMarster cartageMarster = null;
		cartageMarster = (CartageMarster)getHibernateTemplate().get(CartageMarster.class, Integer.valueOf(recordId));

		if(cartageMarster == null)
			throw new CommonDataAccessException("errors.recordnotfound");

		logger.debug("**** Leaving from getCartageMarsterById method ****");
		return cartageMarster;
	}
}
