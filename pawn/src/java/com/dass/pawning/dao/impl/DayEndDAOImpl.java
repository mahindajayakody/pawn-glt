package com.dass.pawning.dao.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.dass.core.bean.Serial;
import com.dass.core.bean.SerialMaster;
import com.dass.core.exception.CommonDataAccessException;
import com.dass.core.util.RecordStatusEnum;
import com.dass.core.util.TransactionDAOSupport;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.DayEndDAO;
import com.dass.pawning.domain.Branch;
import com.dass.pawning.domain.DailyInterst;
import com.dass.pawning.domain.DueFrom;
import com.dass.pawning.domain.DueReceipt;
import com.dass.pawning.domain.DueType;
import com.dass.pawning.domain.InterestSlab;
import com.dass.pawning.domain.Ledger;
import com.dass.pawning.domain.ParameterValue;
import com.dass.pawning.domain.Product;
import com.dass.pawning.domain.Reminder;
import com.dass.pawning.domain.ReminderPara;
import com.dass.pawning.domain.Schemes;
import com.dass.pawning.domain.SystemDate;
import com.dass.pawning.domain.Ticket;
import com.dass.pawning.util.ParameterEnum;
import com.dass.pawning.util.ReminderCodeEnum;
import com.dass.pawning.util.SendMail;
import com.dass.pawning.util.TicketStatusEnum;

public class DayEndDAOImpl extends TransactionDAOSupport implements DayEndDAO {
	private static final Logger logger = Logger.getLogger(DayEndDAOImpl.class);
	private static final SimpleDateFormat simpdate = new SimpleDateFormat("dd-MM-yyyy");
	private static final SimpleDateFormat sqlpdate = new SimpleDateFormat("yyyy-MM-dd");
	//private SendMail sendMail =new SendMail();

	public void doDayEndProcess(UserConfig userConfig,Integer[] branchs){
		logger.debug("**** Enter in to doDayEndProcess method ****");
		List<Branch> branchList   = null;
		List<Ticket> ticketList   = null;
		List<Schemes> schemesList = null;
		List<DueType> dueTypeList = null;
		Map<String, DueType> dueTypeMap  = null;
		List<ParameterValue> paraValList = null;
		List<Ledger> lrdgerList = null;
		List<SerialMaster> seriaList = null;

		Criteria dueFromCriteria = null;
		Criteria ticketCriteria  = null;
		Criteria schemesCriteria = null;
		Criteria ReminderParaCriteria = null;
		Criteria reminderCriteriaList= null;


		Criteria serialCrt = getSession().createCriteria(SerialMaster.class);
        serialCrt.setLockMode(LockMode.READ);
        seriaList = serialCrt.list();

        File file = new File(System.getProperty("user.dir")+ "/output/");
        
		//Create Calander Obejct from System Date
		Calendar sysDate = Calendar.getInstance();
		sysDate.setTime(userConfig.getLoginDate());
		sysDate.add(Calendar.DATE, 1);

		//Load all DueTypes from DueType
		Criteria dueTypeCriteria = getSession().createCriteria(DueType.class);
		dueTypeCriteria.add(Restrictions.eq("recordStatus", RecordStatusEnum.ACTIVE.getCode()));
		dueTypeList = dueTypeCriteria.list();

		dueTypeMap = new HashMap<String, DueType>();
		for(DueType dueType:dueTypeList){
			dueTypeMap.put(dueType.getDueType(), dueType);
		}

		int capId = dueTypeMap.get("CAP").getDueTypeId();
		int intId = dueTypeMap.get("INT").getDueTypeId();
		int payId = dueTypeMap.get("PAY").getDueTypeId();
		int docId = dueTypeMap.get("DOC").getDueTypeId();

		DueType capType = dueTypeMap.get("CAP");
		DueType intType = dueTypeMap.get("INT");
		DueType payType = dueTypeMap.get("PAY");

		double totalInterest=0.00,interestRate = 0.00;
		boolean isCalculated=false;



		//Create Criteria for load Selected Brances
		Criteria branchCriteria = getSession().createCriteria(Branch.class);
		branchCriteria.add(Restrictions.eq("companyId",userConfig.getCompanyId()));
		branchCriteria.add(Restrictions.in("branchId",branchs));
		branchCriteria.setFetchMode("systemDate", FetchMode.JOIN);
		branchList = branchCriteria.list();

		//Get parameter value for MinIneterestDay
		Criteria paraCriteria = getSession().createCriteria(ParameterValue.class);
		paraCriteria.add(Restrictions.eq("parameterId", ParameterEnum.MININTERESTDAYS.getCode()));
		paraCriteria.add(Restrictions.le("effDate", userConfig.getLoginDate()));
		paraCriteria.addOrder(Order.desc("effDate"));
		paraValList = paraCriteria.list();
		int firstInterestDays = Integer.parseInt(paraValList.get(0).getParaValue());
		long ticketAge = 0;
		
		//Get parameter value for MinIneterestDay
		Criteria minIntCriteria = getSession().createCriteria(ParameterValue.class);
		minIntCriteria.add(Restrictions.eq("parameterId", ParameterEnum.SECONDINTEREST.getCode()));
		minIntCriteria.add(Restrictions.le("effDate", userConfig.getLoginDate()));
		minIntCriteria.addOrder(Order.desc("effDate"));
		List<ParameterValue> minIntValList = minIntCriteria.list();
		int secondInterestDays = Integer.parseInt(minIntValList.get(0).getParaValue());

		//Get parameter value for Reminder Cost
		Criteria reminderCostCriteria = getSession().createCriteria(ParameterValue.class);
		reminderCostCriteria.add(Restrictions.eq("parameterId", ParameterEnum.REMINDERCOST.getCode()));
		reminderCostCriteria.add(Restrictions.le("effDate", userConfig.getLoginDate()));
		reminderCostCriteria.addOrder(Order.desc("effDate"));
		List<ParameterValue> remCostValList = reminderCostCriteria.list();
	    double reminderCost = Double.parseDouble(remCostValList.get(0).getParaValue());

		for(Branch branch:branchList){
			//Criteria for load all Schemes defiend to the Branch
			schemesCriteria = getSession().createCriteria(Schemes.class);
			schemesCriteria.add(Restrictions.eq("companyId",userConfig.getCompanyId()));
//			schemesCriteria.add(Restrictions.eq("branchId",branch.getBranchId()));
			schemesCriteria.add(Restrictions.eq("recordStatus", RecordStatusEnum.ACTIVE.getCode()));
			//schemesCriteria.setFetchMode("interestSlads", FetchMode.JOIN);
			schemesList = schemesCriteria.list();

			Map<Integer, Schemes> schemeMap = new HashMap<Integer, Schemes>();
			for(Schemes schemes:schemesList)
				schemeMap.put(schemes.getSchemeId(), schemes);
			//Load all tickets
			ticketCriteria = getSession().createCriteria(Ticket.class);
			ticketCriteria.add(Restrictions.eq("branchId", branch.getBranchId()));
			ticketCriteria.add(Restrictions.eq("companyId", branch.getCompanyId()));
			ticketCriteria.add(Restrictions.ne("ticketStatusId", TicketStatusEnum.CLOSSED.getCode()));
			ticketCriteria.add(Restrictions.eq("isAuctioned", 0));
			ticketCriteria.add(Restrictions.isNotNull("ticketExpiryDate"));
			ticketCriteria.addOrder(Order.asc("ticketId"));
			ticketList = ticketCriteria.list();
			


			for(Ticket ticket:ticketList){
				dueFromCriteria = getSession().createCriteria(DueFrom.class);
				dueFromCriteria.add(Restrictions.eq("ticketId",ticket.getTicketId()));
				List<DueFrom> dueFormList = dueFromCriteria.list();

				Calendar ticketDate = Calendar.getInstance();
				ticketDate.setTime(ticket.getTicketDate());
				ticketDate.add(Calendar.DATE, ticket.getMinDays());

				//if(ticketDate.after(sysDate)){
				double interest = 0;

				//16-04-2011 Changed by Mahinda
				//Check whether the Ticket Lap or not
				if(simpdate.format(sysDate.getTime()).equals(simpdate.format(ticket.getTicketExpiryDate()))){

					//Ticket mark as Lap
					if (ticket.getTicketStatusId() == TicketStatusEnum.ACTIVE.getCode()){
						ticket.setTicketStatusId(TicketStatusEnum.LAPS.getCode());
						getHibernateTemplate().update(ticket);
					}				
				}

                if (ticket.getTicketStatusId() == TicketStatusEnum.ACTIVE.getCode() || 
                	ticket.getTicketStatusId() == TicketStatusEnum.LAPS.getCode())
                {
                	
					if(sysDate.compareTo(ticketDate)!=-1){
						
						List<InterestSlab> slabList = getSession().createCriteria(InterestSlab.class)
													  .add(Restrictions.eq("companyId" , userConfig.getCompanyId()))
													  //.add(Restrictions.eq("branchId" , branch.getBranchId()))
													  .add(Restrictions.eq("recordStatus", RecordStatusEnum.ACTIVE.getCode()))
													  .add(Restrictions.eq("interestSlabCode", schemeMap.get(ticket.getSchemeId()).getInterestId()))
													  .list();

						ticketDate.add(Calendar.DATE, -ticket.getMinDays());
						ticketAge = (sysDate.getTimeInMillis() - ticketDate.getTimeInMillis()) / 86400000;
						isCalculated=false;
						for(DueFrom dueFrom:dueFormList){
							if(dueFrom.getDueTypeId()==capId){
								for(InterestSlab slab:slabList){
									//Changed By Mahinda on 22-05-2011
									int days =slab.getNoOfDaysTo() - slab.getNoOfDaysFrom(); //,nextDays = 	slabList.get(slabList.indexOf(slab) + 1).getNoOfDaysTo() - slabList.get(slabList.indexOf(slab) + 1).getNoOfDaysFrom();								;
									interestRate = slab.getRate() / 36500 ;
									if (ticket.getTicketStatusId() == TicketStatusEnum.ACTIVE.getCode())
										days += 1;
									if(days <= ticketAge){
										
										if (ticketAge == days && slab.getSlabNo() == 1 ){
											if(slabList.get(slabList.indexOf(slab) + 1)!=null)
												interestRate = slabList.get(slabList.indexOf(slab) + 1).getRate() / 1200;
											else
												interestRate = slab.getRate() / 1200;
											isCalculated=true;
											interest = dueFrom.getBalanceAmount() * interestRate  ;
											break;
										}
										else if (ticketAge > ticket.getMinDays() && slab.getSlabNo() != 1){
											if (slabList.get(slabList.indexOf(slab) + 1)!=null)
												interestRate = slabList.get(slabList.indexOf(slab) + 1).getRate() / 36500;
											else
												interestRate = slab.getRate() / 36500;
											interest = dueFrom.getBalanceAmount() * interestRate ;
											totalInterest+=interest;
											break;
										}
									}else if (ticketAge > ticket.getMinDays() && days >= ticket.getMinDays() ){
										interest = dueFrom.getBalanceAmount() * slab.getRate()/36500 ;
										totalInterest+=interest;
										break;
									}
								}
							}
						}						

						for(DueFrom dueFrom:dueFormList){
							if(intId == dueFrom.getDueTypeId()&& interest>0){
								dueFrom.setDueAmount(dueFrom.getDueAmount() + round(interest ,2));
								dueFrom.setBalanceAmount(dueFrom.getBalanceAmount() + round(interest,2));
//								}
							}
						}
						if(ticketAge == firstInterestDays ){
							ticket.setMinDays(secondInterestDays);
							getHibernateTemplate().update(ticket);
						}
					}
                	
				}
				logger.info("Start Creating Daily Interest : - " + ticket.getTicketId() + "  Interest Amount= " + interest);
				DailyInterst dailyInterest=new DailyInterst();
				dailyInterest.setInterestRate(round(interestRate, 2));
				dailyInterest.setInterstAmount(round(interest, 2));
				dailyInterest.setIsBranchExplycit(true);
				dailyInterest.setBranchId(branch.getBranchId());
				dailyInterest.setTicketId(ticket.getTicketId());
				dailyInterest.setDate(sysDate.getTime());
				initializeDomainObject(userConfig, dailyInterest);
				getHibernateTemplate().save(dailyInterest);
				logger.info("End Creating Daily Interest : - " + ticket.getTicketId() + "  Interest Amount= " + interest);
				
				logger.info("**** Start Generating Reminders ****");
					Map<String,Reminder> reminderMap=ticket.getReminderMap();

					//Get Reminder Parameter List
					ReminderParaCriteria=getSession().createCriteria(ReminderPara.class);
					ReminderParaCriteria.add(Restrictions.eq("schemeId", ticket.getSchemeId()));
					ReminderParaCriteria.add(Restrictions.eq("productId", ticket.getProductId()));
					ReminderParaCriteria.add(Restrictions.eq("companyId" , userConfig.getCompanyId()));

					List<ReminderPara> reminderParaList=ReminderParaCriteria.list();
					Calendar reminderDate=Calendar.getInstance();
					Calendar ticketExpireDate=Calendar.getInstance();
					//reminderDate.setTime(userConfig.getLoginDate());
					double capitalOutsanding=0;
					double interestOutstanding=0;
					double otherOutstanding=0;
					boolean lDocAvailable =false;

					for (DueFrom dueFrom : dueFormList) {
						if(intId == dueFrom.getDueTypeId()){
							interestOutstanding += dueFrom.getBalanceAmount();
						}else if(capId == dueFrom.getDueTypeId()){
							capitalOutsanding += dueFrom.getBalanceAmount();
						}else{
							otherOutstanding += dueFrom.getBalanceAmount();
						}
					}
					if(ticket.getTicketId() == 1477)
						System.out.println(ticket.getTicketNumber());						
					for (ReminderPara reminderPara : reminderParaList) {
						if (ReminderCodeEnum.REM1 == ReminderCodeEnum.getEnumByCode(reminderPara.getCode())){
							Reminder remi = reminderMap.get(reminderPara.getCode());
							reminderDate.setTime(ticket.getTicketExpiryDate());
							reminderDate.add(Calendar.DAY_OF_MONTH, reminderPara.getNumberOfDays());
//							ticketExpireDate.setTime(ticket.getTicketExpiryDate());
							if (simpdate.format(reminderDate.getTime()).equals(simpdate.format(userConfig.getLoginDate().getTime()))){
								if (!reminderMap.containsKey(reminderPara.getCode())){
									//Creating First Reminder Record
									Reminder reminder = new Reminder();
									reminder.setBranchId(branch.getBranchId());
									reminder.setCapitalOutsanding(capitalOutsanding);
									reminder.setCompanyId(userConfig.getCompanyId());
									reminder.setDateGenerated(userConfig.getLoginDate());
									reminder.setInterestOutstanding(interestOutstanding);
									reminder.setIsPrinted(0);
									reminder.setOtherOutstanding(otherOutstanding + reminderCost);
									reminder.setProductId(ticket.getProductId());
									reminder.setReminderParaId(reminderPara.getReminderParaId());
									reminder.setSchemeId(ticket.getSchemeId());
									reminder.setTicketId(ticket.getTicketId());
									reminder.setReminderParaCode(reminderPara.getCode());
									reminder.setIsBranchExplycit(true);
									initializeDomainObject(userConfig, reminder);
									getHibernateTemplate().save(reminder);
									
									//Save DOC Record
//									DueFrom dueFromDOC = new DueFrom();
//									dueFromDOC.setProductId(ticket.getProductId());
//									dueFromDOC.setTicketId(ticket.getTicketId());
//									dueFromDOC.setDueTypeId(docId);
//									dueFromDOC.setDueAmount(round(reminderCost,2));
//									dueFromDOC.setPaidAmount(0);
//									dueFromDOC.setBalanceAmount(round(reminderCost,2));
//									initializeTransactionDomainObject(userConfig, dueFromDOC);
//									getHibernateTemplate().save(dueFromDOC);
								}
							}
						//Creating Second Reminder Record

						}else if (ReminderCodeEnum.REM2 == ReminderCodeEnum.getEnumByCode(reminderPara.getCode())){
							if (!reminderMap.containsKey(reminderPara.getCode())){
								if (reminderMap.containsKey(ReminderCodeEnum.REM1.getCode())){
									Reminder rem1 = reminderMap.get(ReminderCodeEnum.REM1.getCode());
									if (rem1.getIsPrinted() == 1 ){										
										reminderDate.setTime(rem1.getDatePrinted());
										reminderDate.add(Calendar.DAY_OF_MONTH, reminderPara.getNumberOfDays());
										if (simpdate.format(reminderDate.getTime()).equals(simpdate.format(sysDate.getTime()))){
											Reminder reminder2 = new Reminder();
											reminder2.setBranchId(branch.getBranchId());
											reminder2.setCapitalOutsanding(capitalOutsanding);
											reminder2.setCompanyId(userConfig.getCompanyId());
											reminder2.setDateGenerated(userConfig.getLoginDate());
											reminder2.setInterestOutstanding(interestOutstanding);
											reminder2.setIsPrinted(0);
											reminder2.setOtherOutstanding(otherOutstanding + reminderCost);
											reminder2.setProductId(ticket.getProductId());
											reminder2.setReminderParaId(reminderPara.getReminderParaId());
											reminder2.setSchemeId(ticket.getSchemeId());
											reminder2.setTicketId(ticket.getTicketId());
											reminder2.setReminderParaCode(reminderPara.getCode());
											reminder2.setIsBranchExplycit(true);
											initializeDomainObject(userConfig, reminder2);
											getHibernateTemplate().save(reminder2);
											
											for (DueFrom dueFrom : dueFormList) {
												if (dueFrom.getDueTypeId() == docId ){
													dueFrom.setDueAmount(dueFrom.getDueAmount()+round(reminderCost,2));
													dueFrom.setBalanceAmount(dueFrom.getBalanceAmount() + round(reminderCost,2));
													getHibernateTemplate().update(dueFrom);
													lDocAvailable = true;
												}
											}
											if(lDocAvailable){ 
								
												//Save DOC Record
												DueFrom dueFromDOC = new DueFrom();
												dueFromDOC.setProductId(ticket.getProductId());
												dueFromDOC.setTicketId(ticket.getTicketId());
												dueFromDOC.setDueTypeId(docId);
												dueFromDOC.setDueAmount(round(reminderCost,2));
												dueFromDOC.setPaidAmount(0);
												dueFromDOC.setBalanceAmount(round(reminderCost,2));
												initializeTransactionDomainObject(userConfig, dueFromDOC);
												getHibernateTemplate().update(dueFromDOC);
												lDocAvailable=false;													
											}											
										}
									}
								}
							}
						//Creating Third Reminder Record
						}else if (ReminderCodeEnum.REM3 == ReminderCodeEnum.getEnumByCode(reminderPara.getCode())){
							if (!reminderMap.containsKey(reminderPara.getCode())){
								if (reminderMap.containsKey(ReminderCodeEnum.REM2.getCode())){
									Reminder rem2=reminderMap.get(ReminderCodeEnum.REM2.getCode());
									if(rem2.getIsPrinted()==1){
										reminderDate.setTime(ticketExpireDate.getTime());
										reminderDate.add(Calendar.DATE, reminderPara.getNumberOfDays());
										if (simpdate.format(reminderDate.getTime()).equals(simpdate.format(sysDate.getTime()))){
											Reminder reminder3=new Reminder();
											reminder3.setCapitalOutsanding(capitalOutsanding);
											reminder3.setCompanyId(userConfig.getCompanyId());
											reminder3.setDateGenerated(userConfig.getLoginDate());
											reminder3.setInterestOutstanding(interestOutstanding);
											reminder3.setIsPrinted(0);
											reminder3.setOtherOutstanding(otherOutstanding);
											reminder3.setProductId(ticket.getProductId());
											reminder3.setReminderParaId(reminderPara.getReminderParaId());
											reminder3.setSchemeId(ticket.getSchemeId());
											reminder3.setTicketId(ticket.getTicketId());
											reminder3.setReminderParaCode(reminderPara.getCode());
											reminder3.setIsBranchExplycit(true);
											reminder3.setBranchId(branch.getBranchId());
											initializeDomainObject(userConfig, reminder3);
											getHibernateTemplate().save(reminder3);
										}
									}
								}
							}
						}
					logger.info("**** End Generating Reminders ****");
				}
			}

			logger.info("**** Begins Ledger Posting ****");

			Calendar ledgerDate=Calendar.getInstance();
			ledgerDate.setTime(userConfig.getLoginDate());
			
			List<Product>productList = getSession().createCriteria(Product.class)
			   						   .add(Restrictions.eq("companyId",userConfig.getCompanyId())).list();

			for (Product product : productList) {
				List<DueReceipt>dueReceiptList = getSession().createCriteria(DueReceipt.class)
												.add(Restrictions.eq("companyId",userConfig.getCompanyId()))
												.add(Restrictions.eq("branchId", branch.getBranchId()))
												.add(Restrictions.eq("settledDate", ledgerDate.getTime()))
												.add(Restrictions.eq("productId", product.getProductId())).list();

				if (totalInterest > 0){
					Ledger ledgerInt = new Ledger();
					ledgerInt.setDebitAmount(totalInterest);
					ledgerInt.setDueType(intType);
					ledgerInt.setProductId(product.getProductId());
					ledgerInt.setDate(userConfig.getLoginDate());
					ledgerInt.setIsBranchExplycit(true);
					ledgerInt.setBranchId(branch.getBranchId());
					initializeDomainObject(userConfig, ledgerInt);
					getHibernateTemplate().save(ledgerInt);
					totalInterest =0;
				}


				if (!dueReceiptList.isEmpty()){
					for(DueType dueType:dueTypeList){
						double selltedAmount=0.0;
						for (DueReceipt dueReceipt : dueReceiptList) {
							if (dueReceipt.getDueTypeId() == dueType.getDueTypeId()){
								selltedAmount += dueReceipt.getSettleAmount();
							}
						}
						if (selltedAmount > 0){
							Ledger ledger = new Ledger();
							ledger.setCreditAmount(selltedAmount);
							ledger.setDueType(dueType);
							ledger.setProductId(product.getProductId());
							ledger.setDate(userConfig.getLoginDate());
							ledger.setIsBranchExplycit(true);
							ledger.setBranchId(branch.getBranchId());
							initializeDomainObject(userConfig, ledger);
							getHibernateTemplate().save(ledger);
							selltedAmount=0;
						}
					}
				}

				double totalAdvance=0.0;
				ticketList = getSession().createCriteria(Ticket.class)
							.add(Restrictions.eq("companyId",userConfig.getCompanyId()))
						    .add(Restrictions.eq("branchId", branch.getBranchId()))
						    .add(Restrictions.eq("ticketDate", ledgerDate.getTime()))
						    .add(Restrictions.eq("productId", product.getProductId()))
						    .add(Restrictions.eq("ticketStatusId", TicketStatusEnum.ACTIVE.getCode())).list();

				for (Ticket ticket : ticketList) {
					totalAdvance += ticket.getPawnAdvance();
				}
				if (totalAdvance > 0){
					Ledger ledgerAdv = new Ledger();
					ledgerAdv.setDebitAmount(totalAdvance);
					ledgerAdv.setDueType(payType);
					ledgerAdv.setProductId(product.getProductId());
					ledgerAdv.setDate(userConfig.getLoginDate());
					ledgerAdv.setIsBranchExplycit(true);
					ledgerAdv.setBranchId(branch.getBranchId());
					initializeDomainObject(userConfig, ledgerAdv);
					getHibernateTemplate().save(ledgerAdv);
					totalAdvance=0;
				}
			}

			logger.info("**** End Ledger Posting ****");


			//Create record for SystemDate
	        Calendar calendar = Calendar.getInstance();
			calendar.setTime(userConfig.getLoginDate());
			calendar.add(Calendar.DATE, 1);

			SystemDate systemDate = branch.getSystemDate();
			systemDate.setPreviousDate(systemDate.getCurrentDate());
			systemDate.setCurrentDate(calendar.getTime());
			calendar.add(Calendar.DATE, 1);
			systemDate.setNextDate(calendar.getTime());
			getHibernateTemplate().update(systemDate);


			Calendar current = Calendar.getInstance();
			current.setTime(systemDate.getCurrentDate());

			int month = current.get(Calendar.MONTH);
			int date  = current.get(Calendar.DATE);

			if (month == Calendar.JANUARY && date == 1){

		        int financeYear = current.get(Calendar.YEAR);

		        current.set(financeYear, Calendar.JANUARY, 1);
		        Date financeYearBegin = current.getTime();

		        current.set(financeYear, Calendar.DECEMBER, 31);
		        Date financeYearEnd = current.getTime();

		        for (SerialMaster master : seriaList){
		        	if (master.getIsAnnually() == 1){
		        		Serial serial = new Serial();
		        		serial.setCompanyId(userConfig.getCompanyId());
		        		serial.setFinanceYear(financeYear);
		        		serial.setFinanceYearBegin(financeYearBegin);
		        		serial.setFinanceYearEnd(financeYearEnd);
		        		serial.setSerialCode(master.getSerialCode());
		        		serial.setSerialValue(0);
		        		serial.setBranchId(branch.getBranchId());
		        		serial.setProductId(1);

		        		getHibernateTemplate().save(serial);
		        	}
		        }
			}
		}
		logger.info("**** Start Ledger Entry Printing ****");
		
		//TODO: Very Very Bad Design  - need to globalize all the reusable  date objects
		Calendar ledgerCalander = Calendar.getInstance();
		Calendar ledgerCalanderStart = Calendar.getInstance();
		Calendar ledgerCalanderEnd = Calendar.getInstance();
		
		
		ledgerCalander.setTime(userConfig.getLoginDate());
		ledgerCalanderStart.set(ledgerCalander.get(Calendar.YEAR), ledgerCalander.get(Calendar.MONTH), ledgerCalander.getActualMinimum(Calendar.DATE));
		ledgerCalanderEnd.set(ledgerCalander.get(Calendar.YEAR), ledgerCalander.get(Calendar.MONTH), ledgerCalander.getActualMaximum(Calendar.DATE));
		
		ledgerCalander.add(Calendar.DATE, 1);
		
		if (ledgerCalander.get(Calendar.DAY_OF_MONTH) == 1) {
		
			String hql = "select b.code as branchId ,d.CODE as duetypeId, sum(l.crAmount) as crAmount, sum(l.drAmount) as drAmount from tblledger as l " +
			            "inner join tblbranch b on b.branchid = l.branchid " +
			            "inner join tblduetype d on d.duetypeid = l.duetypeid " +
			            "where l.date >= '" + sqlpdate.format(ledgerCalanderStart.getTime()) + "' " +
			            "and l.date < '" + sqlpdate.format(ledgerCalanderEnd.getTime()) + "' " +
			            "group by l.branchid,l.duetypeid"; 

			Query query = getSession().createSQLQuery(hql)
							.addScalar("branchId", Hibernate.STRING)
							.addScalar("duetypeId", Hibernate.STRING)
	                        .addScalar("crAmount", Hibernate.DOUBLE)
	                        .addScalar("drAmount", Hibernate.DOUBLE);
						
			List<Object[]> list = query.list();

			StringBuffer stringBuffer =new StringBuffer();
			for (Object object[] : list) {
				stringBuffer.append(object[0]);
				stringBuffer.append("|");
				stringBuffer.append(object[1]);
				stringBuffer.append("|");
				stringBuffer.append(object[2]);
				stringBuffer.append("|");
				stringBuffer.append(object[3]);
				stringBuffer.append("|");
				stringBuffer.append(simpdate.format(ledgerCalanderEnd.getTime()));
				stringBuffer.append("\n");
			}	
			
			try{
				StringBuilder buffer = new StringBuilder();
				buffer.append(file.getCanonicalPath() + "/gl/");
				buffer.append(simpdate.format(ledgerCalanderEnd.getTime()));
				buffer.append(".txt");
				logger.info("Try to write to File Name : "+ buffer.toString());
				
				FileWriter fileWriter   = new FileWriter(buffer.toString(),true);
				BufferedWriter glOutput = new BufferedWriter(fileWriter);
				glOutput.write(stringBuffer.toString());
				glOutput.close();
				fileWriter.close();
//				sendMail.sendMessage("pawngl@pmb.lk,mahinda@modular4.com" , "Monthly GL Download", "", "",buffer.toString());
				
			}catch (IOException ex){
				logger.error(ex.getMessage()+ file.getAbsolutePath());
				throw new CommonDataAccessException("error.cannotcreatefile");
			}
//			catch (MessagingException e) {
//				logger.error(e.getMessage());
//				throw new CommonDataAccessException("errors.sendmailfail");
//			}
			
		}

		logger.info("**** End Ledger Entry Printing ****");

		logger.debug("**** Leaving from doDayEndProcess method ****");
	}

	public List<Branch> getInitialData(UserConfig userConfig){
		logger.debug("**** Enter in to getInitialData ****");
		Branch branch = (Branch)getSession().get(Branch.class, userConfig.getBranchId());
		List<Branch> branchList = null;

		if(branch.getIsDefault()==1){
			getSession().evict(branch);
			Criteria criteria = getSession().createCriteria(Branch.class);
			criteria.add(Restrictions.eq("companyId", userConfig.getCompanyId()));
			criteria.add(Restrictions.eq("recordStatus", RecordStatusEnum.ACTIVE.getCode()));
			criteria.setFetchMode("systemDate",FetchMode.JOIN);
			branchList = criteria.list();
		}else{
			Criteria criteria = getSession().createCriteria(Branch.class);
			criteria.add(Restrictions.eq("branchId", branch.getBranchId()));
			criteria.setFetchMode("systemDate",FetchMode.JOIN);
			branchList = criteria.list();
		}
		logger.debug("**** Leaving from getInitialData ****");
		return branchList;
	}

	public static double round(double d, int decimalPlace){
	    // see the Javadoc about why we use a String in the constructor
	    // http://java.sun.com/j2se/1.5.0/docs/api/java/math/BigDecimal.html#BigDecimal(double)
	    BigDecimal bd = new BigDecimal(Double.toString(d));
	    bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
	    return bd.doubleValue();
	}
}
