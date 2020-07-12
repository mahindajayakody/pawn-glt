package com.dass.pawning.web.action;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.json.JSONArray;

import com.dass.core.exception.PawnException;
import com.dass.core.util.MasterAction;
import com.dass.core.util.SessionUtil;
import com.dass.core.util.StrutsFormValidateUtil;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Branch;
import com.dass.pawning.domain.Company;
import com.dass.pawning.domain.DueFrom;
import com.dass.pawning.domain.Pawner;
import com.dass.pawning.domain.Reminder;
import com.dass.pawning.domain.Ticket;
import com.dass.pawning.domain.TicketArticle;
import com.dass.pawning.dto.InfoConsoleDTO;
import com.dass.pawning.service.BranchService;
import com.dass.pawning.service.CompanyService;
import com.dass.pawning.service.InfoconsoleService;
import com.dass.pawning.service.PawnerService;
import com.dass.pawning.service.ReminderService;
import com.dass.pawning.service.TicketService;
import com.dass.pawning.util.ReminderCodeEnum;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class ReminderPrintAction extends MasterAction {
	private static final Logger logger = Logger.getLogger(ReminderPrintAction.class);
	private static DecimalFormat decimalFormat = new DecimalFormat();
	private static final SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
	static{
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setMinimumFractionDigits(2);
	}
	private PawnerService  pawnerService;
	private ReminderService reminderService;
	private TicketService ticketService;
	private InfoconsoleService infoconsoleService;
	private CompanyService companyService;
	private BranchService branchService;
	
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public void setInfoconsoleService(InfoconsoleService infoconsoleService) {
		this.infoconsoleService = infoconsoleService;
	}

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	public void setReminderService(ReminderService reminderService) {
		this.reminderService = reminderService;
	}	
	public void setPawnerService(PawnerService pawnerService) {
		this.pawnerService = pawnerService;
	}
	
	/**
	 * @param branchService the branchService to set
	 */
	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}

	public ActionForward getAjaxData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to getAjaxData method ****");
		MessageResources messageResources = getResources(request,"message");
		String recordId = request.getParameter("recordId");
		
		JSONArray mainArray=new JSONArray();
		
		if (recordId != "" && recordId!= null && recordId =="0")
		{
			Reminder reminder = reminderService.getReminderById(SessionUtil.getUserSession(request),Integer.valueOf(recordId));
			Ticket ticket=ticketService.getTicketById(SessionUtil.getUserSession(request), reminder.getTicketId());
			Pawner pawner=pawnerService.getPawnerById(SessionUtil.getUserSession(request), ticket.getPawner().getPawnerId());
			
			mainArray.put("");
			mainArray.put(ticket.getTicketNumber());
			mainArray.put(pawner.getTitle() + " " + pawner.getInitials() + " " + pawner.getPawnerName());
			mainArray.put(reminder.getDateGenerated());
			mainArray.put(reminder.getCapitalOutsanding() + reminder.getInterestOutstanding() + reminder.getOtherOutstanding());
			mainArray.put(reminder.getReminderId());
		}else{
			List<Reminder>reminderList=(List<Reminder>) reminderService.getAllReminder(SessionUtil.getUserSession(request), getQueryCriteriaList(request)).getDataList();
			
			for (Reminder reminder : reminderList) {
				JSONArray subArray=new JSONArray();
				Ticket ticket=ticketService.getTicketById(SessionUtil.getUserSession(request), reminder.getTicketId());
				Pawner pawner=pawnerService.getPawnerById(SessionUtil.getUserSession(request), ticket.getPawner().getPawnerId());
				
				subArray.put("");
				subArray.put(ticket.getTicketNumber());
				subArray.put(pawner.getTitle() + " " + pawner.getInitials() + " " + pawner.getPawnerName());
				subArray.put(reminder.getDateGenerated());
				subArray.put(reminder.getCapitalOutsanding() + reminder.getInterestOutstanding() + reminder.getOtherOutstanding());
				subArray.put(reminder.getReminderId());
				mainArray.put(subArray);		
			}			
		}
		response.getWriter().write(mainArray.toString());
		logger.debug("**** Leaving from getAjaxData method ****");
		return null;
	}
	
	public ActionForward print(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		logger.debug("######### Enter In To Reminder Print Method  ####################");
		
		String reminders[] = request.getParameter("reminders").split("<@>");
		Integer[] intReminders = new Integer[reminders.length];
		String reminderDate = request.getParameter("reminderDate"); 

		for(int i=0;i<reminders.length;i++)
			intReminders[i] = Integer.parseInt(reminders[i]);


		UserConfig userConfig=SessionUtil.getUserSession(request);;
		MessageResources messageResources = getResources(request,"message");
		Date receiptDate=new Date();
		InfoConsoleDTO infoConsoleDTO=null;
		Company company=companyService.getCompanyById(userConfig, userConfig.getCompanyId());
		
	
		Document document = new Document(PageSize.LETTER);
		document.setMargins(document.leftMargin()-30, document.rightMargin(), document.topMargin()-20, 0);
        PdfPCell cell = null;
        PdfPCell subCell = null;  
        
        Font lableFont = FontFactory.getFont("Verdana",10, Font.NORMAL ,Color.black);
        Font boldFont = FontFactory.getFont("Verdana",10, Font.BOLD ,Color.black); 
        
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());                      
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition","inline; filename=Reminder.pdf" );
		
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		document.open();
       
        PdfPTable headerTable = null;
        
        for (int i = 0; i < intReminders.length; i++) {
        	headerTable = new PdfPTable(new float[]{1,1,1});
        	try{
        		
	        	Reminder reminder=reminderService.getReminderById(userConfig, intReminders[i]);
	        	infoConsoleDTO = infoconsoleService.getInfoConsoleData(userConfig, Integer.valueOf(reminder.getTicketId()));
	        	Ticket ticket=ticketService.getTicketById(userConfig, reminder.getTicketId());
	        	Branch branch=branchService.getBranchById(userConfig, ticket.getBranchId());
	        	Pawner pawner =pawnerService.getPawnerById(userConfig, ticket.getPawner().getPawnerId());
        	
		        //Print Pawner Name
		        String strName=pawner.getTitle() + " " + pawner.getInitials() + " " +  pawner.getPawnerName();
		        for (int j = 0; j < 30; j++) {
			        cell = new PdfPCell(new Paragraph(""));
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setColspan(3);
			        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			        headerTable.addCell(cell);
				}
		        
		        //Branch
		        cell=new PdfPCell(new Paragraph(branch.getBarnchName(),lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
//		        cell.setFixedHeight(10);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        headerTable.addCell(cell);      
		        
		        cell=new PdfPCell(new Paragraph(branch.getAddressline1(),lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        //cell.setFixedHeight(30);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        headerTable.addCell(cell);
		        
		        cell=new PdfPCell(new Paragraph(branch.getAddressline2(),lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        //cell.setFixedHeight(30);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        headerTable.addCell(cell);        
		        
		        cell=new PdfPCell(new Paragraph(branch.getAddressline3(),lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        //cell.setFixedHeight(30);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        headerTable.addCell(cell);        
		        
		        cell=new PdfPCell(new Paragraph(branch.getAddressline4(),lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        //cell.setFixedHeight(30);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        headerTable.addCell(cell);        
		        
		        cell = new PdfPCell(new Paragraph("",lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        cell.setFixedHeight(20);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph(strName,lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
		
		        //Print Pawner Address
		        cell = new PdfPCell(new Paragraph(pawner.getAddressLine1(),lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);

		        cell = new PdfPCell(new Paragraph(pawner.getAddressLine2(),lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);

		        cell = new PdfPCell(new Paragraph(pawner.getAddressLine3(),lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);

		        cell = new PdfPCell(new Paragraph(pawner.getAddressLine4(),lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);

		        cell = new PdfPCell(new Paragraph(""));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph("",lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
		            
		        cell = new PdfPCell(new Paragraph(""));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        cell.setFixedHeight(30);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
		          
		        cell = new PdfPCell(new Paragraph(""));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setFixedHeight(30);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
		        
		        //print Ticket No
		        cell=new PdfPCell(new Paragraph(ticket.getTicketNumber(),lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);     
		        
		        cell = new PdfPCell(new Paragraph(""));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setFixedHeight(30);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph(""));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setFixedHeight(30);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
		        
//		        //Print Amount Of Advance
		        cell = new PdfPCell(new Paragraph(" " + ticket.getPawnAdvance(),lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
		        
	
		        cell = new PdfPCell(new Paragraph(""));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        cell.setFixedHeight(60);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph(""));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
		        cell.setFixedHeight(25);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph(reminderDate,lableFont));
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setColspan(3);
//		        cell.setFixedHeight(60);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        headerTable.addCell(cell);
//		        
		        for (int j = 0; j < 30; j++) {
		        	cell = new PdfPCell(new Paragraph("" ,lableFont));
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setColspan(3);
			        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			        headerTable.addCell(cell);	
				}
		        
		        List<TicketArticle>ticketArticleList=infoConsoleDTO.getTicketArticleList();

		        for (TicketArticle ticketArticle : ticketArticleList) {
		        	
			        cell = new PdfPCell(new Paragraph("" + ticketArticle.getNoOfItem() + "   " + ticketArticle.getArticleDescription() ,lableFont));
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			        headerTable.addCell(cell);
			        
			        cell = new PdfPCell(new Paragraph("" + ticketArticle.getNetWeight() + "                        " + ticketArticle.getAssessedValue(),lableFont));
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			        headerTable.addCell(cell);
			        
			        cell = new PdfPCell(new Paragraph("",lableFont));
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			        headerTable.addCell(cell);			        
	
				}
		        
		        reminder.setDatePrinted(userConfig.getLoginDate());
		        reminder.setIsPrinted(1);
		        reminderService.updateReminder(userConfig, reminder);
		        
		        
        	}catch(PawnException ex){
        		response.getWriter().write(StrutsFormValidateUtil.getAJAXErrorMessage(ex, messageResources, request.getLocale()));
    			return null;
        	}
            cell = new PdfPCell(headerTable);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            headerTable.addCell(cell);
            document.add(headerTable);
            document.newPage();
        }
        document.close();               
        logger.debug("######### End Of The Reminder Print Method  ####################");
		
		return null;
	}

	@Override
	protected ActionForward getAuthorizeData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
