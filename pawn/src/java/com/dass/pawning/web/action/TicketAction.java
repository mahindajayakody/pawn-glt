package com.dass.pawning.web.action;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.json.JSONArray;
import org.json.JSONObject;

import com.dass.core.exception.PawnException;
import com.dass.core.util.MasterAction;
import com.dass.core.util.SessionUtil;
import com.dass.core.util.StrutsFormValidateUtil;
import com.dass.pawning.domain.Article;
import com.dass.pawning.domain.ArticleModel;
import com.dass.pawning.domain.Cartage;
import com.dass.pawning.domain.Pawner;
import com.dass.pawning.domain.Product;
import com.dass.pawning.domain.Schemes;
import com.dass.pawning.domain.Ticket;
import com.dass.pawning.domain.TicketArticle;
import com.dass.pawning.dto.AuthorizeTicketDTO;
import com.dass.pawning.service.ArticleModelService;
import com.dass.pawning.service.ArticleService;
import com.dass.pawning.service.BranchService;
import com.dass.pawning.service.CartageService;
import com.dass.pawning.service.ProductService;
import com.dass.pawning.service.SchemeService;
import com.dass.pawning.service.TicketService;
import com.dass.pawning.util.EnglishNumberToWords;
import com.dass.pawning.util.TicketStatusEnum;
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

public class TicketAction extends MasterAction {
	private static final Logger logger = Logger.getLogger(TicketAction.class);
	private static DecimalFormat decimalFormat = new DecimalFormat();

	static{
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setMinimumFractionDigits(2);
	}
	private TicketService ticketService;
	private SchemeService schemeService;
	private ArticleModelService articleModelService;
	private ArticleService articleService;
	private ProductService productService;
	private CartageService cartageService;
	private BranchService branchService;

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	public void setArticleModelService(ArticleModelService articleModelService) {
		this.articleModelService = articleModelService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	public void setSchemeService(SchemeService schemeService) {
		this.schemeService = schemeService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCartageService(CartageService cartageService) {
		this.cartageService = cartageService;
	}
	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}

	public ActionForward create(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.debug("**** Enter in to create method ****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{
        	Ticket ticket = new Ticket();
        	ticket.setProductId(Integer.parseInt(request.getParameter("productId")));
        	ticket.setSchemeId(Integer.parseInt(request.getParameter("schemeId")));
        	ticket.setInterestSlabId(Integer.parseInt(request.getParameter("interestRateId")));
        	ticket.setPeriod(Integer.parseInt(request.getParameter("period")));
        	//ticket.setLocationId(Integer.parseInt(request.getParameter("")));
        	ticket.setTotalNoOfItems(Integer.parseInt(request.getParameter("totNoOfItems")));
        	ticket.setTotalNetWeight(Double.parseDouble(request.getParameter("totWeight")));
        	ticket.setTotalGrossWeight(Double.parseDouble(request.getParameter("grossTotWeight")));
        	ticket.setTotalAssedValue(Double.parseDouble(request.getParameter("totActualDisb")));
        	ticket.setTotalMarketValue(Double.parseDouble(request.getParameter("totMrtValue")));
        	ticket.setPawnAdvance(Double.parseDouble(request.getParameter("totPawnAdvance")));
        	ticket.setSystemAssedValue(Double.parseDouble(request.getParameter("totGoldValue")));
        	ticket.setPawner(new Pawner(Integer.parseInt(request.getParameter("pawnerId"))));
        	ticket.setTotalCapitalOutstanding(Double.parseDouble(request.getParameter("totPawnAdvance")));
        	ticket.setRemark(request.getParameter("remark"));

        	//ticket.setTicketStatusId()

        	List<TicketArticle> list = new ArrayList<TicketArticle>();
        	String itemData = request.getParameter("itemData");

        	if(itemData!=null && !itemData.equals("")){
        		String[] rows = itemData.split("<row>");
        		for(String row:rows){
        			String[] columns = row.split("<@>");
        			TicketArticle ticketArticle = new TicketArticle();
        			ticketArticle.setArticleId(Integer.parseInt(columns[0]));
        			ticketArticle.setProductId(ticket.getProductId());
        			ticketArticle.setArticleModelId(Integer.parseInt(columns[1]));
        			ticketArticle.setMarcketValue(Double.parseDouble(columns[2]));
        			ticketArticle.setAssessedValue(Double.parseDouble(columns[3]));
        			ticketArticle.setNetWeight(Double.parseDouble(columns[4]));
        			ticketArticle.setGrossWeight(Double.parseDouble(columns[5]));
        			ticketArticle.setArticleDescription(columns[6]);
        			ticketArticle.setNoOfItem(Integer.parseInt(columns[7]));
        			ticketArticle.setCartage(new Cartage(Integer.parseInt(columns[8])));

        			list.add(ticketArticle);
        		}
        	}

        	try {
        		String number = ticketService.createTicket(SessionUtil.getUserSession(request), ticket , list);
        		JSONObject object = StrutsFormValidateUtil.getMessageCreateSuccess(messageResources);
        		object.put("ticketNumber",number );
        		response.getWriter().write(object.toString());
			} catch (PawnException ex) {
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
			}
        }


		logger.debug("**** Leaving from create method ****");
		return null;
	}

	public ActionForward approve(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.debug("**** Enter in to approve method ****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{
        	Ticket ticket = new Ticket();
        	ticket.setTicketId(Integer.parseInt(request.getParameter("ticketId")));
        	ticket.setVersion(Integer.parseInt(request.getParameter("version")));
        	boolean authorize = Boolean.parseBoolean(request.getParameter("booleanVal"));

        	try {
				ticketService.authorizeTicket(SessionUtil.getUserSession(request), ticket,authorize );
			} catch (PawnException ex) {
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
				return null;
			}

			if(authorize){
				response.getWriter().write(StrutsFormValidateUtil.getMessageAuthorizeSuccess(messageResources).toString());
			}else{
				response.getWriter().write(StrutsFormValidateUtil.getMessageRejectSuccess(messageResources).toString());
			}
        }

		logger.debug("**** Leaving from approve method ****");
		return null;
	}

	public ActionForward getArticleModel(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		MessageResources messageResources = getResources(request,"message");
    	ArticleModel articleModel = null;
 		String code   = request.getParameter("code");
 		int productId = Integer.parseInt(request.getParameter("productId"));

 		try {
 			articleModel = articleModelService.getArticaleModelByCodeAndProductId(SessionUtil.getUserSession(request),code,productId);
 		}catch(PawnException ex){
			response.getWriter().write(StrutsFormValidateUtil.getAJAXErrorMessage(ex, messageResources, request.getLocale()));
			return null;
 		}
 		response.getWriter().write(StrutsFormValidateUtil.getAJAXReferenceData(articleModel.getRecordId(),articleModel.getDescription()));
		return null;
	}

	public ActionForward getArticle(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		MessageResources messageResources = getResources(request,"message");
    	Article article = null;
 		String code   = request.getParameter("code");
 		int artModelId= Integer.parseInt(request.getParameter("articleModId"));

 		try {
 			article = (Article)articleService.getArticleByCodeAndArticleModel(SessionUtil.getUserSession(request),code,artModelId);
 		}catch(PawnException ex){
			response.getWriter().write(StrutsFormValidateUtil.getAJAXErrorMessage(ex, messageResources, request.getLocale()));
			return null;
 		}
 		response.getWriter().write(StrutsFormValidateUtil.getAJAXReferenceData(article.getRecordId(),article.getDescription()));
		return null;
	}

	public ActionForward getScheme(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		MessageResources messageResources = getResources(request,"message");
    	Schemes schemes = null;
 		String code   = request.getParameter("code");
 		int productId = Integer.parseInt(request.getParameter("productId"));

 		try {
 			schemes = schemeService.getSchemesByCodeAndProductId(SessionUtil.getUserSession(request), code,productId);
 		}catch(PawnException ex){
			response.getWriter().write(StrutsFormValidateUtil.getAJAXErrorMessage(ex, messageResources, request.getLocale()));
			return null;
 		}
 		response.getWriter().write(StrutsFormValidateUtil.getAJAXReferenceData(schemes.getRecordId(),schemes.getDescription()));
		return null;
	}

	public ActionForward getProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		MessageResources messageResources = getResources(request,"message");
    	Product product = null;
 		String code   = request.getParameter("code");

 		try {
 			product = productService.getProductByCode(SessionUtil.getUserSession(request), code);
 		}catch(PawnException ex){
			response.getWriter().write(StrutsFormValidateUtil.getAJAXErrorMessage(ex, messageResources, request.getLocale()));
			return null;
 		}
 		response.getWriter().write(StrutsFormValidateUtil.getAJAXReferenceData(product.getRecordId(),product.getDescription()));
		return null;
	}

	public ActionForward getCartage(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		MessageResources messageResources = getResources(request,"message");
    	Cartage cartage = null;
 		String code   = request.getParameter("code");
 		int cartageMasterId = Integer.parseInt(request.getParameter("cartageMasterId"));

 		try {
 			cartage = cartageService.getCartageByCodeAndMasterId(SessionUtil.getUserSession(request), code,cartageMasterId);
 		}catch(PawnException ex){
			response.getWriter().write(StrutsFormValidateUtil.getAJAXErrorMessage(ex, messageResources, request.getLocale()));
			return null;
 		}
 		response.getWriter().write(StrutsFormValidateUtil.getAJAXReferenceData(cartage.getRecordId(),cartage.getDescription()));
		return null;
	}

	public ActionForward getSchemeData(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		MessageResources messageResources = getResources(request,"message");
    	Schemes schemes = null;
 		int schemeId = Integer.parseInt(request.getParameter("schemeId"));

 		try {
 			schemes = schemeService.getSchemeById(SessionUtil.getUserSession(request), schemeId);
 			JSONObject object = new JSONObject();
 			object.put("period", schemes.getPeriod());
 			object.put("intCode", schemes.getInterestCode());
 			object.put("intId", schemes.getInterestId());
 			object.put("masterId", schemes.getCartageMarsterId());
 			response.getWriter().write(object.toString());
 		}catch(PawnException ex){
			response.getWriter().write(StrutsFormValidateUtil.getAJAXErrorMessage(ex, messageResources, request.getLocale()));
			return null;
 		}

		return null;
	}

	public ActionForward getCartageData(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		MessageResources messageResources = getResources(request,"message");
    	Cartage cartage = null;
 		int cartageId = Integer.parseInt(request.getParameter("cartageId"));

 		try {
 			cartage = cartageService.getCartageById(SessionUtil.getUserSession(request), cartageId);

 			JSONObject object = new JSONObject();
 			object.put("marcketValue", cartage.getMarcketValue());
 			object.put("disburseValue", cartage.getDisburseValue() );

 			response.getWriter().write(object.toString());
 		}catch(PawnException ex){
			response.getWriter().write(StrutsFormValidateUtil.getAJAXErrorMessage(ex, messageResources, request.getLocale()));
			return null;
 		}

		return null;
	}

	public ActionForward getAjaxData(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Object[]> list = null;

		String pawnerCode = request.getParameter("pawnerCode");
		String nicOrBrn   = request.getParameter("nicOrBrn");
		String surname    = request.getParameter("surname");
		String ticketStatus = request.getParameter("ticketStatus");

		String sql = "";

		if(pawnerCode!=null && pawnerCode!="")
			sql = " AND t.pawner.pawnerCode LIKE '" + pawnerCode + "%' ";
		if(nicOrBrn!=null && nicOrBrn!="")
			sql += " AND t.pawner.idOrBrNo LIKE '" + nicOrBrn + "%' ";
		if(surname!=null && surname!="")
			sql += " AND t.pawner.surName LIKE '" + surname + "%' ";
		if(ticketStatus!=null && ticketStatus!=""){
			//Modified By Mahinda on 21-Nov-2011
			if (ticketStatus.equals("9")) {
				sql += " AND t.ticketStatusId<>3 AND t.ticketStatusId<>5";
			}else{
				sql += " AND t.ticketStatusId=" + Integer.parseInt(ticketStatus);
			}
		}

		list = ticketService.getSerchTicketData(SessionUtil.getUserSession(request),sql);
		JSONArray mainArray = new JSONArray();
		for(Object object[]:list){
			JSONArray array = new JSONArray();
			array.put(object[1]);
			array.put(object[2] + " " + object[3] + " " + object[4]);
			array.put(object[5]);
			array.put(object[0]);
			array.put(object[6]);
			array.put(TicketStatusEnum.getEnumByCode(Integer.parseInt(object[6].toString())));
			mainArray.put(array);
		}
		response.getWriter().write(mainArray.toString());
		return null;
	}

	public ActionForward getAuthorizeData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageResources messageResources = getResources(request,"message");
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));
		AuthorizeTicketDTO ticketDTO = null;

		try {
			ticketDTO = ticketService.getAuthorizeTicketData(SessionUtil.getUserSession(request), ticketId);
		} catch (PawnException e) {
			response.getWriter().write(StrutsFormValidateUtil.getAJAXErrorMessage(e, messageResources, request.getLocale()));
			return null;
		}

		JSONObject object = new JSONObject();
		object.put("pawnerCode", ticketDTO.getPawnerCode());
		object.put("pawnerName", ticketDTO.getPawnerName());
		object.put("productCode", ticketDTO.getProductCode());
		object.put("productDescription", ticketDTO.getProductDescription());
		object.put("schemCode", ticketDTO.getSchemCode());
		object.put("schemDescription", ticketDTO.getSchemDescription());
		object.put("period", ticketDTO.getPeriod());
		object.put("interestId", ticketDTO.getInterestId());
		object.put("noOfArticle", ticketDTO.getNoOfArticle());
		object.put("goldValue",decimalFormat.format(ticketDTO.getGoldValue()));
		object.put("pawnAdvance", decimalFormat.format(ticketDTO.getPawnAdvance()));
		object.put("marketValue", decimalFormat.format(ticketDTO.getMarketValue()));
		object.put("actualDisValue", decimalFormat.format(ticketDTO.getActualDisValue()));
		object.put("totalNetWeight", ticketDTO.getTotalNetWeight());
		object.put("interestCode", ticketDTO.getInterestCode());
		object.put("remark", ticketDTO.getRemark());
		object.put("version", ticketDTO.getVersion());

		JSONArray mainArray = new JSONArray();
		for(TicketArticle article:ticketDTO.getTicketArticleList()){
			JSONArray array = new JSONArray();
			array.put(article.getArticleDescription());
			array.put(article.getNetWeight());
			array.put(decimalFormat.format(article.getAssessedValue()));
			array.put(article.getNoOfItem());
			mainArray.put(array);
		}

		object.put("ticketArticleList", mainArray.toString());

		response.getWriter().write(object.toString());
		return null;
	}

	public ActionForward print(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		int ticketId        = Integer.parseInt(request.getParameter("ticketId"));
		String ticketNumber = request.getParameter("ticketNumber");
		
		AuthorizeTicketDTO ticketDTO = ticketService.getAuthorizeTicketData(SessionUtil.getUserSession(request), ticketId);
		Ticket ticket                = ticketService.getTicketById(SessionUtil.getUserSession(request), ticketId);
		
		/** 
		 *  Start building the ticket.pdf
		 */  
		Document document = new Document(PageSize.LETTER);
		document.setMargins(document.leftMargin(), document.rightMargin()-20, document.topMargin()-40, 0);
        PdfPCell cell = null;
        PdfPCell subCell = null;  
        
        Font lableFont = FontFactory.getFont("Verdana",10, Font.NORMAL ,Color.black);        
        
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());                      
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition","inline; filename=Ticket.pdf" );
		
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
        document.open();
        
        PdfPTable headTable = new PdfPTable(1);        
        headTable.setWidthPercentage(100);
        
        //============= Print date =====================
        PdfPTable dateTable = new PdfPTable(new float[]{2,1});
        
        cell = new PdfPCell(new Paragraph(" "));
        cell.setBorder(Rectangle.BOX);
        cell.setFixedHeight(20);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateTable.addCell(cell);
        
        cell = new PdfPCell(new Paragraph("                           "+StrutsFormValidateUtil.parseString(ticket.getTicketDate()),lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setFixedHeight(20);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateTable.addCell(cell);
        
//        cell = new PdfPCell(dateTable);
//        cell.setBorder(Rectangle.BOX);        
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        headTable.addCell(cell);
        
        /*Change By Mahinda */
        cell = new PdfPCell(new Paragraph(" "));
        cell.setBorder(Rectangle.BOX);
        cell.setFixedHeight(20);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateTable.addCell(cell);
        
        cell = new PdfPCell(new Paragraph(" "));
        cell.setBorder(Rectangle.BOX);
        cell.setFixedHeight(20);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateTable.addCell(cell);
        
        cell = new PdfPCell(new Paragraph(" "));
        cell.setBorder(Rectangle.BOX);
        cell.setFixedHeight(20);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateTable.addCell(cell);
        
        cell = new PdfPCell(new Paragraph("                           "+ticket.getTicketNumber(),lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setFixedHeight(20);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateTable.addCell(cell);
        
        cell = new PdfPCell(dateTable);
        cell.setBorder(Rectangle.BOX);        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headTable.addCell(cell);
        /*End Of Change*/
        
        /////////////// Start Space ////////////////
        for(int i=0;i<1;i++){
        	cell = new PdfPCell(new Paragraph(" "));
            cell.setBorder(Rectangle.BOX);        
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(20);
            headTable.addCell(cell);
        }
        /////////////// End Space ////////////////
        
        
//        PdfPTable tempTable1 = new PdfPTable(new float[]{1,1,2});
//        cell = new PdfPCell(new Paragraph(branchService.getBranchById(SessionUtil.getUserSession(request), SessionUtil.getUserSession(request).getBranchId()).getBarnchName(),lableFont));
//        cell.setBorder(Rectangle.BOX);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        tempTable1.addCell(cell);
//        
//        cell = new PdfPCell(new Paragraph(ticketNumber,lableFont));
//        cell.setBorder(Rectangle.BOX);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        tempTable1.addCell(cell);
//        
//        cell = new PdfPCell(new Paragraph(" "));
//        cell.setBorder(Rectangle.BOX);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        tempTable1.addCell(cell);
//        
//        //changed by Mahinda
//        cell = new PdfPCell(new Paragraph(" "));
//        cell.setBorder(Rectangle.BOX);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        tempTable1.addCell(cell);
        
        //End of change
        
//        cell = new PdfPCell(tempTable1);
//        cell.setBorder(Rectangle.BOX);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        headTable.addCell(cell);
        
        //============= Start print Pawner details ================
        PdfPTable tempTable2 = new PdfPTable(new float[]{1,1});
        cell = new PdfPCell(new Paragraph("\n\n\n" + ticketDTO.getPawnerName()+ "\n\n" + ticketDTO.getAddress(),lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tempTable2.addCell(cell);        
        
        
        //============= Start print Item details ================
        PdfPTable artDescription = new PdfPTable(new float[]{2,1,1});
        int items = 0;
        for(TicketArticle article:ticketDTO.getTicketArticleList()){
        	cell = new PdfPCell(new Paragraph("        " +article.getArticleDescription(),lableFont));
            cell.setBorder(Rectangle.BOX);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            artDescription.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("   " +article.getNoOfItem(),lableFont));
            cell.setBorder(Rectangle.BOX);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            artDescription.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("   " +article.getNetWeight() + "g",lableFont));
            cell.setBorder(Rectangle.BOX);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            artDescription.addCell(cell);
            items ++;
        }
        
        cell = new PdfPCell(new Paragraph(" "));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(3);
        artDescription.addCell(cell);
        
        cell = new PdfPCell(new Paragraph("        Total Weights ",lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);        
        artDescription.addCell(cell);
        
        cell = new PdfPCell(new Paragraph("" + ticketDTO.getTotGrossWeight() + "g",lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);        
        artDescription.addCell(cell);
             
        cell = new PdfPCell(new Paragraph("" + ticketDTO.getTotalNetWeight() + "g",lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);        
        artDescription.addCell(cell);
        
        cell = new PdfPCell(new Paragraph(" "));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(3);
        artDescription.addCell(cell);
        
        cell = new PdfPCell(new Paragraph("    " + ticketDTO.getRemark(),lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(3);
        artDescription.addCell(cell);
        
        cell = new PdfPCell(artDescription);
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tempTable2.addCell(cell);
        
        cell = new PdfPCell(tempTable2);
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headTable.addCell(cell);
        
        /////////////// Start Space ////////////////
        for(int i=0;i<(3-items) ;i++){
        	cell = new PdfPCell(new Paragraph(" "));
            cell.setBorder(Rectangle.BOX);        
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headTable.addCell(cell);
        }
        /////////////// End Space ////////////////

        
        PdfPTable tempTable3 = new PdfPTable(new float[]{1,3});
        cell = new PdfPCell(new Paragraph("   "+ticket.getPawner().getIdOrBrNo(),lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tempTable3.addCell(cell);        
        
        cell = new PdfPCell(new Paragraph("          General Purpose ",lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tempTable3.addCell(cell);
        
        cell = new PdfPCell(tempTable3);
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headTable.addCell(cell);
        
        /////////////// Start Space ////////////////
        for(int i=0;i<1;i++){
        	cell = new PdfPCell(new Paragraph(" "));
            cell.setBorder(Rectangle.BOX);        
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headTable.addCell(cell);
        }
        /////////////// End Space ////////////////
        Calendar ticketExpireDate = Calendar.getInstance();
		ticketExpireDate.setTime(ticket.getTicketDate());
		Date expireDate =new Date();
        if (ticket.getTicketExpiryDate() == null) {
        	ticketExpireDate.add(Calendar.DATE, 365);
		}else {
			ticketExpireDate.setTime(ticket.getTicketExpiryDate());
		}
        expireDate = ticketExpireDate.getTime();
        
        //=============== Start print Assessed value + Interest + Period + Redemption Date
        PdfPTable tempTable4 = new PdfPTable(new float[]{2,2,1,1});
        cell = new PdfPCell(new Paragraph("        "+decimalFormat.format(ticket.getSystemAssedValue())+" ",lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tempTable4.addCell(cell);        
        //Remove the Monthly Interest Rate to Annual by Mahinda 19-09-2012
        cell = new PdfPCell(new Paragraph("                      " + Double.parseDouble(ticketDTO.getInterestSlab().get(0)) + " " ,lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tempTable4.addCell(cell);
        
        cell = new PdfPCell(new Paragraph( "     " +ticketDTO.getPeriod() + " " ,lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tempTable4.addCell(cell);
        
        cell = new PdfPCell(new Paragraph("          " +StrutsFormValidateUtil.parseString(expireDate),lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tempTable4.addCell(cell);
        
        cell = new PdfPCell(tempTable4);
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headTable.addCell(cell);
        
        /////////////// Start Space ////////////////
        for(int i=0;i<1;i++){
        	cell = new PdfPCell(new Paragraph(" "));
            cell.setBorder(Rectangle.BOX);        
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headTable.addCell(cell);
        }
        /////////////// End Space ////////////////
        
        // =============== Start print PawnAdvance ====================
        PdfPTable tempTable5 = new PdfPTable(new float[]{3,1});
        cell = new PdfPCell(new Paragraph("                            " + EnglishNumberToWords.convert(Math.round(ticketDTO.getPawnAdvance())) + " only.",lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tempTable5.addCell(cell);
        
        cell = new PdfPCell(new Paragraph("        ***" + decimalFormat.format(ticketDTO.getPawnAdvance()) + "   ",lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tempTable5.addCell(cell); 
        
        cell = new PdfPCell(tempTable5);
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headTable.addCell(cell);
        
        //===============================================================
        //================ Start print voucher data =====================
        //===============================================================
        
        /////////////// Start Space ////////////////
        for(int i=0;i<14;i++){
        	cell = new PdfPCell(new Paragraph(" "));
            cell.setBorder(Rectangle.BOX);        
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headTable.addCell(cell);
        }
        /////////////// End Space ////////////////
        
        //============= Print date =====================      
        cell = new PdfPCell(dateTable);
        cell.setBorder(Rectangle.BOX);        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headTable.addCell(cell);
        
        /////////////// Start Space ////////////////
        for(int i=0;i<1;i++){
        	cell = new PdfPCell(new Paragraph(" "));
            cell.setBorder(Rectangle.BOX);        
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(40);
            headTable.addCell(cell);
        }
        /////////////// End Space ////////////////
        
//        cell = new PdfPCell(tempTable1);
//        cell.setBorder(Rectangle.BOX);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        headTable.addCell(cell);
        
        /////////////// Start Space ////////////////
        for(int i=2;i<3;i++){
        	cell = new PdfPCell(new Paragraph(" "));
            cell.setBorder(Rectangle.BOX);        
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            headTable.addCell(cell);
        }
        /////////////// End Space ////////////////
        
        
        //============= Start print Pawner details ================
        PdfPTable tempTable7 = new PdfPTable(new float[]{1,1});
        
        PdfPTable leftTable = new PdfPTable(1);
        
        cell = new PdfPCell(new Paragraph( ticketDTO.getPawnerName()+ "\n" + ticketDTO.getAddress(),lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        leftTable.addCell(cell); 
        
        /////////////// Start Space ////////////////
        for(int i=0;i<5;i++){
        	cell = new PdfPCell(new Paragraph(" "));
            cell.setBorder(Rectangle.BOX);        
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            leftTable.addCell(cell);
        }
        /////////////// End Space ////////////////
        
        cell = new PdfPCell(new Paragraph("\n***" + decimalFormat.format(ticketDTO.getPawnAdvance()) + "    ",lableFont));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        leftTable.addCell(cell);
        
        cell = new PdfPCell(leftTable);
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tempTable7.addCell(cell); 
        
        cell = new PdfPCell(artDescription);
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tempTable7.addCell(cell);
        
        cell = new PdfPCell(tempTable7);
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headTable.addCell(cell);
        
        document.add(headTable);
        document.close();
		
		return null;
	}
	public ActionForward getClientExposure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageResources messageResources = getResources(request,"message");
 		int pawnerId = Integer.parseInt(request.getParameter("pawnerId"));

 		
 		List<List<Object>> rows = ticketService.getClientExposure(SessionUtil.getUserSession(request), pawnerId);

		JSONArray mainArray = new JSONArray();
		for (Iterator<List<Object>> iterator = rows.iterator(); iterator.hasNext();) {
			List<Object> row = iterator.next();
			JSONArray array = new JSONArray();
			array.put(row.get(0));
			array.put(row.get(1));
			array.put(row.get(2));
			array.put(row.get(3));
			array.put(row.get(4));
			array.put(row.get(5));
			mainArray.put(array);
		}
		response.getWriter().write(mainArray.toString());
		
		return null;
	}
	public ActionForward getTicketById(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{

		String ticketId = request.getParameter("ticketId");
		AuthorizeTicketDTO ticketDTO = ticketService.getAuthorizeTicketData(SessionUtil.getUserSession(request), Integer.parseInt(ticketId));
		JSONArray mainArray = new JSONArray();
		JSONArray array = new JSONArray();
		mainArray.put(ticketDTO.getTicketNo());
		mainArray.put(ticketDTO.getPawnerName());
		mainArray.put(TicketStatusEnum.getEnumByCode(ticketDTO.getTicketStatus()));
		mainArray.put(ticketDTO.getTicketId());
		mainArray.put(ticketDTO.getVersion());
		array.put(mainArray);
		response.getWriter().write(array.toString());
		return null;
	}
	public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		logger.debug("**** Enter in to create method ****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");
		int ticketId=Integer.parseInt(request.getParameter("ticketId"));
		int statusId=Integer.parseInt(request.getParameter("statusId"));
		
		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{
        	Ticket ticket = ticketService.getTicketById(SessionUtil.getUserSession(request), ticketId);
        	ticket.setTicketStatusId(statusId);
        	try {
				ticketService.update(SessionUtil.getUserSession(request),ticket);
				response.getWriter().write(StrutsFormValidateUtil.getMessageUpdateSuccess(messageResources).toString());
			} catch (PawnException ex) {
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
			}
        }
		return null;
	}
}