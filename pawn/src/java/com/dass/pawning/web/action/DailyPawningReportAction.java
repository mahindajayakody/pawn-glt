package com.dass.pawning.web.action;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.hibernate.criterion.Restrictions;
import com.dass.pawning.util.TicketStatusEnum;
import com.dass.core.util.MasterAction;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.RecordStatusEnum;
import com.dass.core.util.SessionUtil;
import com.dass.core.util.StrutsFormValidateUtil;
import com.dass.core.util.UserConfig;
import com.dass.core.util.QueryCriteria.Oparator;
import com.dass.pawning.domain.Branch;
import com.dass.pawning.domain.Company;
import com.dass.pawning.domain.DueFrom;
import com.dass.pawning.domain.Pawner;
import com.dass.pawning.domain.Ticket;
import com.dass.pawning.service.BranchService;
import com.dass.pawning.service.CompanyService;
import com.dass.pawning.service.DueFromService;
import com.dass.pawning.service.PawnerService;
import com.dass.pawning.service.TicketService;
import com.dass.pawning.service.impl.CompanyServiceImpl;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class DailyPawningReportAction extends MasterAction {
	static DecimalFormat points2decimalFormat = new DecimalFormat();
	static {
		points2decimalFormat.setMinimumFractionDigits(2);
		points2decimalFormat.setMaximumFractionDigits(2);
		points2decimalFormat.setGroupingSize(3);
	}
	
	private BranchService branchService;
	private PawnerService pawnerService; 
	private TicketService ticketService;
	private CompanyService companyService;
	private DueFromService dueFromService;
	
	public void setDueFromService(DueFromService dueFromService) {
		this.dueFromService = dueFromService;
	}
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}
	public void setPawnerService(PawnerService pawnerService) {
		this.pawnerService = pawnerService;
	}
	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	private BaseFont helv;
	private PdfTemplate tpl;
	
	public ActionForward print(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		new PrintUtil().print(request, response);
		return null;
	}
	
	@Override
	public ActionForward createSetup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaActionForm frm = (DynaActionForm) form;
	    frm.initialize(mapping);
        frm.set("action",CREATE);
        frm.set("beginDate", StrutsFormValidateUtil.parseString(SessionUtil.getUserSession(request).getLoginDate()));
        frm.set("endDate", StrutsFormValidateUtil.parseString(SessionUtil.getUserSession(request).getLoginDate()));
        setOtherData(form, request);
        return mapping.getInputForward();
	}
	
	
	@Override
	protected ActionForward getAuthorizeData(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return null;
	}
    
    class PrintUtil extends PdfPageEventHelper{
    	public void print(HttpServletRequest request,HttpServletResponse response)throws Exception{
    		UserConfig config = SessionUtil.getUserSession(request);
    		
    		String beginDate = request.getParameter("beginDate");
    		String endDate   = request.getParameter("endDate");
    		String branchId  = request.getParameter("branchId");
    		
    		float cellWeidth[]  = new float[]{1,3,1,3,5,2,1,1,1};
    		String[] cellTitles = new String[]{ "Serial No",
    											"Ticket No/Status",
    											"Pawned Date",
    											"Client Name",
    											"Address",
    											"Pawn Advance (Rs.)",
    											"Interest Accrued",
    											"Interest Settled",
    											"Interest Outstanding"};
    		
    		Date beginDateDate = null;
    		Date endDateDate   = null;
    		
    		if(endDate==null)
    			endDateDate = config.getLoginDate();
    		else
    			endDateDate = StrutsFormValidateUtil.parseDate(endDate);
    		
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(endDateDate);
    		calendar.set(Calendar.HOUR, 23);		
    		
    		beginDateDate = StrutsFormValidateUtil.parseDate(beginDate);
    		
    		//Changed By Mahinda on 24th-May-2011
    		Branch branch = branchService.getBranchById(config , Integer.parseInt(request.getParameter("branchId")));
    		Pawner officer = pawnerService.getPawnerById(config , config.getPawnerId());
    		Company company = companyService.getCompanyById(config , config.getCompanyId());
    		
    		if (!branch.getCode().equals("***")){
    			
	    		List<QueryCriteria> queryList = new ArrayList<QueryCriteria>();
	    		queryList.add(new QueryCriteria("ticketDate",Oparator.GRATERTHAN_OR_EQUAL,beginDateDate));
	    		queryList.add(new QueryCriteria("ticketDate",Oparator.LESSTHAN_OR_EQUAL,calendar.getTime()));
	    		queryList.add(new QueryCriteria("ticketStatusId",Oparator.NOT_EQUAL,TicketStatusEnum.REJECTED.getCode()));
	    		//Changed By Mahinda on 24th-May-2011
	    		if (!branch.getCode().equals("***"))
	    			queryList.add(new QueryCriteria("branchId",Oparator.EQUAL,Integer.parseInt(branchId)));
	    		queryList.add(new QueryCriteria("companyId",Oparator.EQUAL,config.getCompanyId()));
	    	
	    		List<Ticket> ticketList = ticketService.getAllTicket(config,queryList ).getDataList();
	    		
	    		/** 
	    		 *  Start building the DailyPawningReport.pdf
	    		*/  
	    		Document document = new Document(PageSize._11X17.rotate());
	    		document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin()-20, 0);
	            PdfPCell cell    = null;
	            
	            Font lableFont   = FontFactory.getFont("Verdana",10, Font.NORMAL ,Color.black);        
	            
	            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());                      
	            response.setContentType("application/pdf");
	            response.setHeader("Content-disposition","inline; filename=DailyPawningReport.pdf" );
	    		
	    		response.setHeader("Expires", "0");
	    		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	    		response.setHeader("Pragma", "public");
	            document.open();
	            
	            PdfPTable headTable = new PdfPTable(1);        
	            headTable.setWidthPercentage(100);
	            
	            //Print header details
	            PdfPTable headerTable = new PdfPTable(1);
	            
	            
	            cell = new PdfPCell(new Paragraph(company.getCompanyName()+" - Pawning system",lableFont));
	            cell.setBorder(Rectangle.NO_BORDER);
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            headerTable.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph("Daily Pawning Statement : From  " + beginDate + "   To   " + endDate,lableFont));
	            cell.setBorder(Rectangle.NO_BORDER);
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            headerTable.addCell(cell);
	            
	            
	            cell = new PdfPCell(new Paragraph("Branch Code   : " + branch.getCode(),lableFont));
	            cell.setBorder(Rectangle.NO_BORDER);
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            headerTable.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph("Branch Name  : " + branch.getBarnchName() ,lableFont));
	            cell.setBorder(Rectangle.NO_BORDER);
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            headerTable.addCell(cell);
	            
	            
	            
	            cell = new PdfPCell(new Paragraph("Printed By       : " + officer.getPawnerName() ,lableFont));
	            cell.setBorder(Rectangle.NO_BORDER);
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            headerTable.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph("Printed Date    : " + StrutsFormValidateUtil.parseString(config.getLoginDate()) ,lableFont));
	            cell.setBorder(Rectangle.NO_BORDER);
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            headerTable.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(" "));
	            cell.setBorder(Rectangle.NO_BORDER);
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            headerTable.addCell(cell);
	            
	            cell = new PdfPCell(headerTable);
	            cell.setBorder(Rectangle.NO_BORDER);
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            headTable.addCell(cell);
	            document.add(headTable);
	            
	            //Creating data table headings
	    	    PdfPTable dataTable = new PdfPTable(9);
	            dataTable.setWidthPercentage(100);        
	            dataTable.setHeaderRows(1);
	            
	            for(String lable:cellTitles){
	            	cell = new PdfPCell(new Paragraph(lable,lableFont));
	                cell.setGrayFill(0.9f);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                dataTable.addCell(cell);
	                dataTable.setWidths(cellWeidth);
	            }
	            int noItems=1;
	            double TotPowanadvance=0,totDueInt=0,totBalInt=0,totPaidInt=0;
	            String strTicketStatus=""; 
	            for(Ticket ticket:ticketList){
	            	
	            	double dueInt=0,paidInt=0,balInt=0;	
	            	//Get Balance from duefrom
	            	List<DueFrom> dueFromList = dueFromService.getDueFromByFacility(getUserSession(request), ticket.getTicketId());
	            	for (DueFrom dueFrom : dueFromList) {
						dueInt+=dueFrom.getDueAmount();
						paidInt+=dueFrom.getPaidAmount();
						balInt+=dueFrom.getBalanceAmount();
					}
	            	//cell = new PdfPCell(new Paragraph(ticket.getTicketSerialNumber(),lableFont));   
	            	cell = new PdfPCell(new Paragraph(String.valueOf(noItems),lableFont)); 
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setBorder(Rectangle.NO_BORDER);
	                dataTable.addCell(cell);
	                
	                switch (ticket.getTicketStatusId()) {
					case 1:
						strTicketStatus=" - ACTIVE";
						break;
					case 2:
						strTicketStatus=" - PENDING";
						break;
					case 3:
						strTicketStatus=" - REJECTED";
						break;
					case 4:
						strTicketStatus=" - LAPS";
						break;
					case 5:
						strTicketStatus=" - CLOSSED";
						break;
					case 6:
						strTicketStatus=" - STOLEN";
						break;						
					case 7:
						strTicketStatus=" - DEAD";
						break;							
					default:
						strTicketStatus="";
						break;
					}
	                
	                
	                cell = new PdfPCell(new Paragraph(ticket.getTicketNumber()+strTicketStatus,lableFont));            
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                cell.setBorder(Rectangle.NO_BORDER);
	                dataTable.addCell(cell);
	                
	                cell = new PdfPCell(new Paragraph(StrutsFormValidateUtil.parseString(ticket.getTicketDate()),lableFont));            
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                cell.setBorder(Rectangle.NO_BORDER);
	                dataTable.addCell(cell);
	                
	                cell = new PdfPCell(new Paragraph(ticket.getPawner().getPawnerName(),lableFont));            
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                cell.setBorder(Rectangle.NO_BORDER);
	                dataTable.addCell(cell);
	                
	                cell = new PdfPCell(new Paragraph(new StringBuilder().append(ticket.getPawner().getAddressLine1())
	                					.append(" ").append(ticket.getPawner().getAddressLine2())
	                					.append(" ").append(ticket.getPawner().getAddressLine3())
	                					.append(" ").append(ticket.getPawner().getAddressLine4()).toString(),lableFont));            
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                cell.setBorder(Rectangle.NO_BORDER);
	                dataTable.addCell(cell);
	                
	                cell = new PdfPCell(new Paragraph(points2decimalFormat.format(ticket.getPawnAdvance()),lableFont));            
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setBorder(Rectangle.NO_BORDER);
	                dataTable.addCell(cell);
	                
	                cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(dueInt),lableFont));            
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setBorder(Rectangle.NO_BORDER);
	                dataTable.addCell(cell);
	                
	                cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(paidInt),lableFont));            
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setBorder(Rectangle.NO_BORDER);
	                dataTable.addCell(cell);
	                
	                cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(balInt),lableFont));            
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setBorder(Rectangle.NO_BORDER);
	                dataTable.addCell(cell);
	                
	                noItems+=1;
	                TotPowanadvance+=ticket.getPawnAdvance();
	                totBalInt+=balInt;
	                totDueInt+=dueInt;
	                totPaidInt+=paidInt;
	                
	                balInt =0; dueInt=0; paidInt=0;
	            }
	            
	            document.add(dataTable);    
	           
	            
	           
	            
	            //Creating data table GranTotal
	    	    PdfPTable SpaceTable = new PdfPTable(1);
	    	    SpaceTable.setWidthPercentage(100);  
	            
	    	    cell = new PdfPCell(new Paragraph(""));
	            cell.setBorder(Rectangle.NO_BORDER);
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            SpaceTable.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(""));
	            cell.setBorder(Rectangle.NO_BORDER);
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            SpaceTable.addCell(cell);
	            
	            //SpaceTable.setWidths(totcellWeidth);
	            document.add(SpaceTable);
	    	    
	    	    
	    	    
	            //Creating data table GranTotal
	            
	            float totcellWeidth[]  = new float[]{1,3,1,3,5,2,1,1,1};
	            
	    	    PdfPTable GranddataTable = new PdfPTable(9);
	    	    GranddataTable.setWidthPercentage(100);  
	    	    
	         
	            cell = new PdfPCell(new Paragraph(""));
	            cell.setBorder(Rectangle.TOP);
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            GranddataTable.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(""));
	            cell.setBorder(Rectangle.TOP);
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            GranddataTable.addCell(cell);
	        
	            cell = new PdfPCell(new Paragraph(""));
	            cell.setBorder(Rectangle.TOP);
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            GranddataTable.addCell(cell);
	            
	            
	            cell = new PdfPCell(new Paragraph("Grand Totals    : "));
	            cell.setBorder(Rectangle.TOP);
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            GranddataTable.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(""));
	            cell.setBorder(Rectangle.TOP);
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            GranddataTable.addCell(cell);
	            
	            
                cell = new PdfPCell(new Paragraph("" + points2decimalFormat.format(TotPowanadvance),lableFont));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                GranddataTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("" + points2decimalFormat.format(totDueInt),lableFont));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                GranddataTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("" + points2decimalFormat.format(totPaidInt),lableFont));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                GranddataTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("" + points2decimalFormat.format(totBalInt),lableFont));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                GranddataTable.addCell(cell);
	            
	        
	            GranddataTable.setWidths(totcellWeidth);
	            document.add(GranddataTable);
	            
	            document.close();
    		}else{
    			/** 
        		 *  Start building the DailyPawningReport.pdf
        		*/  
        		Document document = new Document(PageSize._11X17.rotate());
        		document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin()-20, 0);
                PdfPCell cell    = null;
                
                Font lableFont   = FontFactory.getFont("Verdana",10, Font.NORMAL ,Color.black);        
                
                PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());                      
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition","inline; filename=DailyPawningReport.pdf" );
        		
        		response.setHeader("Expires", "0");
        		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        		response.setHeader("Pragma", "public");
                document.open();
                
                PdfPTable headTable = new PdfPTable(1);        
                headTable.setWidthPercentage(100);
                
                //Print header details
                PdfPTable headerTable = new PdfPTable(1);
                
                
                cell = new PdfPCell(new Paragraph(company.getCompanyName()+" - Pawning system",lableFont));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                headerTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Daily Pawning Statement : From  " + beginDate + "   To   " + endDate,lableFont));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                headerTable.addCell(cell);
                
                
                cell = new PdfPCell(new Paragraph("Branch Code   : " + branch.getCode(),lableFont));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                headerTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Branch Name  : " + branch.getBarnchName() ,lableFont));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                headerTable.addCell(cell);
                
                
                
                cell = new PdfPCell(new Paragraph("Printed By       : " + officer.getPawnerName() ,lableFont));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                headerTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Printed Date    : " + StrutsFormValidateUtil.parseString(config.getLoginDate()) ,lableFont));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                headerTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(" "));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                headerTable.addCell(cell);
                
                cell = new PdfPCell(headerTable);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                headTable.addCell(cell);
                document.add(headTable);
                
                //Creating data table headings
        	    PdfPTable dataTable = new PdfPTable(9);
                dataTable.setWidthPercentage(100);        
                dataTable.setHeaderRows(1);
                
                for(String lable:cellTitles){
                	cell = new PdfPCell(new Paragraph(lable,lableFont));
                    cell.setGrayFill(0.9f);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(cell);
                    dataTable.setWidths(cellWeidth);
                }
                int noItems=1;
                
                String strTicketStatus=""; 
                double TotPowanadvance=0,totDueInt=0,totBalInt=0,totPaidInt=0,openAdvance = 0,openDueInt = 0,openPaidInt = 0,openBalInt = 0,grandTotAdvance=0,grandTotBalInt=0,grandTotDueInt=0,grandTotPaidInt=0;
                
                List<Ticket> ticketList =null;
                
                List<QueryCriteria> criteriaList = new ArrayList<QueryCriteria>();
                criteriaList.add(new QueryCriteria("companyId",Oparator.EQUAL, config.getCompanyId()));
                criteriaList.add(new QueryCriteria("code",Oparator.NOT_EQUAL, "***"));
                
                List<Branch> branchList = branchService.getAllBranch(config, criteriaList).getDataList();
                for (Branch allBranch : branchList) {
                	
                	List<QueryCriteria> queryList = new ArrayList<QueryCriteria>();
            		queryList.add(new QueryCriteria("ticketDate",Oparator.LESSTHAN,calendar.getTime()));
            		queryList.add(new QueryCriteria("ticketStatusId",Oparator.NOT_EQUAL,TicketStatusEnum.REJECTED.getCode()));
            		queryList.add(new QueryCriteria("companyId",Oparator.EQUAL,config.getCompanyId()));
            		queryList.add(new QueryCriteria("branchId",Oparator.EQUAL,allBranch.getBranchId()));
            		
            		ticketList = ticketService.getAllTicket(config,queryList ).getDataList();
                
            		for(Ticket ticket:ticketList){
            			openAdvance+=ticket.getPawnAdvance();
    	            	//Get Balance from duefrom
    	            	List<DueFrom> dueFromList = dueFromService.getDueFromByFacility(getUserSession(request), ticket.getTicketId());
    	            	for (DueFrom dueFrom : dueFromList) {
    	            		openDueInt+=dueFrom.getDueAmount();
    	            		openPaidInt+=dueFrom.getPaidAmount();
    	            		openBalInt+=dueFrom.getBalanceAmount();
    					}
            		}
                    //Opening Balance
                    cell = new PdfPCell(new Paragraph("Opening Balance For " + allBranch.getBarnchName()));            
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(5);
                    dataTable.addCell(cell);
                    
                    cell = new PdfPCell(new Paragraph(points2decimalFormat.format(openAdvance),lableFont));            
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    dataTable.addCell(cell);
                    
                    cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(openDueInt),lableFont));            
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    dataTable.addCell(cell);
                    
                    cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(openPaidInt),lableFont));            
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    dataTable.addCell(cell);
                    
                    cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(openBalInt),lableFont));            
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    dataTable.addCell(cell);
                    //
                    //End Opening Balance       		
            		queryList=new ArrayList<QueryCriteria>();
            		ticketList=null;
    				
            		queryList.add(new QueryCriteria("ticketDate",Oparator.GRATERTHAN_OR_EQUAL,StrutsFormValidateUtil.parseDate(endDate)));
            		queryList.add(new QueryCriteria("ticketDate",Oparator.LESSTHAN_OR_EQUAL,StrutsFormValidateUtil.parseDate(endDate)));
            		queryList.add(new QueryCriteria("ticketStatusId",Oparator.NOT_EQUAL,TicketStatusEnum.REJECTED.getCode()));
            		if (!allBranch.getCode().equals("***"))
            			queryList.add(new QueryCriteria("branchId",Oparator.EQUAL,allBranch.getBranchId()));
            		queryList.add(new QueryCriteria("companyId",Oparator.EQUAL,config.getCompanyId()));
            		
            		ticketList = ticketService.getAllTicket(config,queryList ).getDataList();
            		
    	            for(Ticket ticket:ticketList){
    	            	double dueInt=0,paidInt=0,balInt=0;	
    	            	//Get Balance from duefrom
    	            	List<DueFrom> dueFromList = dueFromService.getDueFromByFacility(getUserSession(request), ticket.getTicketId());
    	            	for (DueFrom dueFrom : dueFromList) {
    						dueInt+=dueFrom.getDueAmount();
    						paidInt+=dueFrom.getPaidAmount();
    						balInt+=dueFrom.getBalanceAmount();
    					}
    	            	//cell = new PdfPCell(new Paragraph(ticket.getTicketSerialNumber(),lableFont));   
    	            	
    	            	cell = new PdfPCell(new Paragraph(String.valueOf(noItems),lableFont)); 
    	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	                cell.setBorder(Rectangle.NO_BORDER);
    	                dataTable.addCell(cell);
    	                
    	                switch (ticket.getTicketStatusId()) {
    					case 1:
    						strTicketStatus=" - ACTIVE";
    						break;
    					case 2:
    						strTicketStatus=" - PENDING";
    						break;
    					case 3:
    						strTicketStatus=" - REJECTED";
    						break;
    					case 4:
    						strTicketStatus=" - LAPS";
    						break;
    					case 5:
    						strTicketStatus=" - CLOSSED";
    						break;
    	
    					default:
    						strTicketStatus="";
    						break;
    					}

    	                cell = new PdfPCell(new Paragraph(ticket.getTicketNumber()+strTicketStatus,lableFont));            
    	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	                cell.setBorder(Rectangle.NO_BORDER);
    	                dataTable.addCell(cell);
    	                
    	                cell = new PdfPCell(new Paragraph(StrutsFormValidateUtil.parseString(ticket.getTicketDate()),lableFont));            
    	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	                cell.setBorder(Rectangle.NO_BORDER);
    	                dataTable.addCell(cell);
    	                
    	                cell = new PdfPCell(new Paragraph(ticket.getPawner().getPawnerName(),lableFont));            
    	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	                cell.setBorder(Rectangle.NO_BORDER);
    	                dataTable.addCell(cell);
    	                
    	                cell = new PdfPCell(new Paragraph(new StringBuilder().append(ticket.getPawner().getAddressLine1())
    	                					.append(" ").append(ticket.getPawner().getAddressLine2())
    	                					.append(" ").append(ticket.getPawner().getAddressLine3())
    	                					.append(" ").append(ticket.getPawner().getAddressLine4()).toString(),lableFont));            
    	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	                cell.setBorder(Rectangle.NO_BORDER);
    	                dataTable.addCell(cell);
    	                
    	                cell = new PdfPCell(new Paragraph(points2decimalFormat.format(ticket.getPawnAdvance()),lableFont));            
    	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	                cell.setBorder(Rectangle.NO_BORDER);
    	                dataTable.addCell(cell);
    	                
    	                cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(dueInt),lableFont));            
    	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	                cell.setBorder(Rectangle.NO_BORDER);
    	                dataTable.addCell(cell);
    	                
    	                cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(paidInt),lableFont));            
    	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	                cell.setBorder(Rectangle.NO_BORDER);
    	                dataTable.addCell(cell);
    	                
    	                cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(balInt),lableFont));            
    	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	                cell.setBorder(Rectangle.NO_BORDER);
    	                dataTable.addCell(cell);
    	                
    	                noItems+=1;
    	                TotPowanadvance+=ticket.getPawnAdvance();
    	                totBalInt+=balInt;
    	                totDueInt+=dueInt;
    	                totPaidInt+=paidInt;
    	                
    	                balInt =0; dueInt=0; paidInt=0;
    	            }
    	            
    	            TotPowanadvance+=openAdvance;
    	            totBalInt+=openBalInt;
	                totDueInt+=openDueInt;
	                totPaidInt+=openPaidInt;
	                openAdvance =0; openBalInt=0; openDueInt=0;
	                grandTotAdvance+=TotPowanadvance;
	                grandTotBalInt+=totBalInt;
	                grandTotDueInt+=totDueInt;
	                grandTotPaidInt+=totPaidInt;
	              //Closing Balance
                    cell = new PdfPCell(new Paragraph("Closing Balance For " + allBranch.getBarnchName()));            
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setColspan(5);
                    dataTable.addCell(cell);
                    
                    cell = new PdfPCell(new Paragraph(points2decimalFormat.format(TotPowanadvance),lableFont));            
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    dataTable.addCell(cell);
                    
                    cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(totDueInt),lableFont));            
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    dataTable.addCell(cell);
                    
                    cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(totPaidInt),lableFont));            
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    dataTable.addCell(cell);
                    
                    cell = new PdfPCell(new Paragraph(""+points2decimalFormat.format(totBalInt),lableFont));            
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    dataTable.addCell(cell);
                    //
                    //End Closing Balance
                    totBalInt=0; totDueInt=0;totPaidInt=0;TotPowanadvance=0;
                }
                document.add(dataTable);           
                
                //Creating data table GranTotal
        	    PdfPTable SpaceTable = new PdfPTable(1);
        	    SpaceTable.setWidthPercentage(100);  
                
        	    cell = new PdfPCell(new Paragraph(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                SpaceTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                SpaceTable.addCell(cell);
                
                //SpaceTable.setWidths(totcellWeidth);
                document.add(SpaceTable);
        	    
        	    
        	    
                //Creating data table GranTotal
                
                float totcellWeidth[]  = new float[]{1,3,1,3,5,2,1,1,1};
                
        	    PdfPTable GranddataTable = new PdfPTable(9);
        	    GranddataTable.setWidthPercentage(100);  
        	    
             
                cell = new PdfPCell(new Paragraph(""));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                GranddataTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(""));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                GranddataTable.addCell(cell);
            
                cell = new PdfPCell(new Paragraph(""));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                GranddataTable.addCell(cell);
                
                
                cell = new PdfPCell(new Paragraph("Grand Totals    : "));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                GranddataTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(""));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                GranddataTable.addCell(cell);
                
                
                cell = new PdfPCell(new Paragraph("" + points2decimalFormat.format(grandTotAdvance),lableFont));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                GranddataTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("" + points2decimalFormat.format(grandTotDueInt),lableFont));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                GranddataTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("" + points2decimalFormat.format(grandTotPaidInt),lableFont));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                GranddataTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("" + points2decimalFormat.format(grandTotBalInt),lableFont));
                cell.setBorder(Rectangle.TOP);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                GranddataTable.addCell(cell);
                
            
                GranddataTable.setWidths(totcellWeidth);
                document.add(GranddataTable);
                
                document.close();
    		}
            
            
    	}
    	
        public void onOpenDocument(PdfWriter writer, Document document) {
            try {
                // initialization of the template
                tpl = writer.getDirectContent().createTemplate(100, 100);
                helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
            }
            catch(Exception e) {
                throw new ExceptionConverter(e);
            }
        } 
    	
        public void onEndPage(PdfWriter writer, Document document){
            PdfContentByte cb = writer.getDirectContent();
            cb.saveState();
            String text = "Page " + writer.getPageNumber() + " of ";
            
            float adjust = helv.getWidthPoint("0", 8);
            float textBase = document.bottom() - 20;
            float textSize = helv.getWidthPoint(text, 8);
            
            cb.beginText();
            cb.setFontAndSize(helv, 8);
            cb.setTextMatrix(document.right() - textSize - adjust, textBase);
            cb.showText(text);
            cb.endText();
            
            cb.addTemplate(tpl, document.right() - adjust, textBase);        
            cb.saveState();
            cb.restoreState();
        }
        
        public void onCloseDocument(PdfWriter writer, Document document) {
           tpl.beginText();
           tpl.setFontAndSize(helv, 8);
           tpl.setTextMatrix(0, 0);
           tpl.showText("" + (writer.getPageNumber() - 1));
           tpl.endText();
        }
    }
}
