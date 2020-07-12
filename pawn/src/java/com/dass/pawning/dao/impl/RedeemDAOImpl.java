package com.dass.pawning.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.dass.core.exception.CommonDataAccessException;
import com.dass.core.util.BaseDAOSupport;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.RedeemDAO;
import com.dass.pawning.domain.DueFrom;
import com.dass.pawning.domain.Ticket;
import com.dass.pawning.domain.TicketRedeem;
import com.dass.pawning.util.RedeemType;
import com.dass.pawning.util.TicketStatusEnum;

public class RedeemDAOImpl extends BaseDAOSupport implements RedeemDAO {
	private static final Logger logger = Logger.getLogger(RedeemDAOImpl.class);
	
	public void redeem(UserConfig userConfig, int ticketId) {
		logger.debug("**** Enter in to redeem method ****");
		
		double outstanding = (Double)getSession().createCriteria(DueFrom.class)
							 .add(Restrictions.eq("ticketId", ticketId))
							 .setProjection(Projections.sum("balanceAmount"))
							 .uniqueResult();
		
		if(outstanding!=0)
			throw new CommonDataAccessException("errors.nonzerooutstanding");
		
		Ticket ticket = (Ticket)getSession().load(Ticket.class, Integer.valueOf(ticketId));
		ticket.setTicketStatusId(TicketStatusEnum.CLOSSED.getCode());
		ticket.setTicketClosedRenewalDate(userConfig.getLoginDate());
		initializeDomainObject(userConfig, ticket);
		getHibernateTemplate().update(ticket);
		
		TicketRedeem redeem = new TicketRedeem();
		redeem.setTicketId(ticketId);
		redeem.setProductId(ticket.getProductId());		
		redeem.setRedeemType(RedeemType.REDEEM.getCode());
		redeem.setRedeemUserId(userConfig.getUserId());
		redeem.setRedeemDate(userConfig.getLoginDate());
		redeem.setUserId(userConfig.getUserId());
		redeem.setLastUpdateDate((userConfig).getLoginDate());
		redeem.setCompanyId(userConfig.getCompanyId());
		redeem.setBranchId(userConfig.getBranchId());
		redeem.setApproveDate(userConfig.getLoginDate());
		redeem.setApproveUserId(userConfig.getUserId());
		
		getHibernateTemplate().save(redeem);
		
		logger.debug("**** Leaving from redeem method ****");
	}

}
