package com.dass.pawning.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.dass.core.exception.CommonDataAccessException;
import com.dass.core.exception.PawnException;
import com.dass.core.util.MasterAction;
import com.dass.core.util.PasswordService;
import com.dass.core.util.SessionUtil;
import com.dass.core.util.StrutsFormValidateUtil;
import com.dass.pawning.domain.Officer;
import com.dass.pawning.service.OfficerService;

public class ChangePasswordAction extends MasterAction {
	private static final Logger logger = Logger.getLogger(ChangePasswordAction.class);
	private OfficerService officerService;
	
	public void setOfficerService(OfficerService officerService) {
		this.officerService = officerService;
	}
	
	public ActionForward changePassword(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to changePassword method ****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{
        	try{
        		Officer officer = officerService.getOfficerById(SessionUtil.getUserSession(request), SessionUtil.getUserSession(request).getUserId());
        		if(!officer.getPassword().equals(PasswordService.getInstance().encrypt(request.getParameter("oldPassword"))))
        			throw new PawnException("errors.oldpasswordnotmatch");
        		
        		officer.setPassword(PasswordService.getInstance().encrypt(request.getParameter("newPassword")));
        		officerService.updateOfficer(SessionUtil.getUserSession(request), officer);
        		response.getWriter().write(StrutsFormValidateUtil.getMessageUpdateSuccess(messageResources).toString());
        	}catch(PawnException ex){
        		response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
        	}
        }
		logger.debug("**** Leaving from changePassword method ****");
		return null;
	}
	
	@Override
	protected ActionForward getAuthorizeData(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {		
		return null;
	}

}
