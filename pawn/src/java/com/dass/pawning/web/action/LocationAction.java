package com.dass.pawning.web.action;

import java.util.ArrayList;
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

import com.dass.core.exception.PawnException;
import com.dass.core.util.MasterAction;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.RecordStatusEnum;
import com.dass.core.util.SessionUtil;
import com.dass.core.util.StrutsFormValidateUtil;
import com.dass.core.util.QueryCriteria.Oparator;
import com.dass.pawning.domain.Location;
import com.dass.pawning.service.LocationService;

public class LocationAction extends MasterAction {
	private static final Logger logger = Logger.getLogger(LocationAction.class);
	private LocationService locationService;

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to create method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{
        	Location location = new Location();
        	location.setCode(request.getParameter("code"));
        	location.setDescription(request.getParameter("description"));

        	try {
        		locationService.createLocation(SessionUtil.getUserSession(request), location);
        		response.getWriter().write(StrutsFormValidateUtil.getMessageCreateSuccess(messageResources).toString());
			} catch (PawnException ex) {
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
			}
        }

		logger.debug("**** Leaving from create method *****");
		return null;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to update method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{
        	Location location = new Location();
        	location.setCode(request.getParameter("code"));
        	location.setDescription(request.getParameter("description"));
        	location.setRecordId(Integer.parseInt(request.getParameter("recordId")));
        	location.setVersion(Integer.parseInt(request.getParameter("version")));

        	try {
        		locationService.updateLocation(SessionUtil.getUserSession(request), location);
        		response.getWriter().write(StrutsFormValidateUtil.getMessageUpdateSuccess(messageResources).toString());
			} catch (PawnException ex) {
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
			}
        }

		logger.debug("**** Leaving from update method *****");
		return null;
	}

	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to remove method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		if(!validateForm.isEmpty()){
        	response.getWriter().write(StrutsFormValidateUtil.getMessages(request, validateForm,messageResources,getLocale(request),null).toString());
        }else{
        	Location location = new Location();
        	location.setRecordId(Integer.parseInt(request.getParameter("recordId")));
        	location.setVersion(Integer.parseInt(request.getParameter("version")));

        	try {
        		locationService.removeLocation(SessionUtil.getUserSession(request), location);
        		response.getWriter().write(StrutsFormValidateUtil.getMessageDeleteSuccess(messageResources).toString());
			} catch (PawnException ex) {
				response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, request.getLocale()).toString());
			}
        }

		logger.debug("**** Leaving from remove method *****");
		return null;
	}

	public ActionForward authorize(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.debug("**** Enter in to remove method *****");
		ActionMessages validateForm = form.validate(mapping,request);
		MessageResources messageResources = getResources(request,"message");

		String recordId = request.getParameter("recordId");
        String version  = request.getParameter("version");
        String authorizeType   = request.getParameter("authorizeMode");
        boolean authorize      = Boolean.parseBoolean(request.getParameter("authorizeValue"));

        if((recordId==null)||(version==null)||(recordId.equals(""))||(version.equals(""))){
        	response.getWriter().write(StrutsFormValidateUtil.getMessageNotFound(messageResources).toString());
        	return null;
        }

        Location location = new Location();
    	location.setRecordId(Integer.parseInt(request.getParameter("recordId")));
    	location.setVersion(Integer.parseInt(request.getParameter("version")));

        try{
        	if(authorizeType.equals("Create") ){
        		locationService.authorizeCreation(SessionUtil.getUserSession(request), location, authorize);
        	}else if( authorizeType.equals("Update") ){
        		locationService.authorizeUpdation(SessionUtil.getUserSession(request), location, authorize);
        	}else if( authorizeType.equals("Delete") ){
        		locationService.authorizeDeletion(SessionUtil.getUserSession(request), location, authorize);
	    	}
        }catch(PawnException ex){
	    	logger.error("exception in authorization" + ex.getExceptionCode());
	    	response.getWriter().write(StrutsFormValidateUtil.getErrorMessage(ex, messageResources, getLocale(request)).toString());
	    	return null;
        }

        if(authorize==true){
        	response.getWriter().write(StrutsFormValidateUtil.getMessageAuthorizeSuccess(messageResources).toString());
        }else{
        	response.getWriter().write(StrutsFormValidateUtil.getMessageRejectSuccess(messageResources).toString());
        }

		return null;
	}

	public ActionForward getAjaxData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("**** Enter in to getGridData method *****");
		String recordId = request.getParameter("recordId");

		JSONArray mainArray = new JSONArray();

		if(recordId!=null && recordId!=""){
			Location location = locationService.getLocationById(SessionUtil.getUserSession(request), Integer.parseInt(recordId));
			mainArray.put(location.getCode());
			mainArray.put(location.getDescription());
			mainArray.put(location.getRecordId());
			mainArray.put(location.getVersion());
		}else{
			List<Location> list = (List<Location>)locationService.getAllLocation(SessionUtil.getUserSession(request), getQueryCriteriaList(request)).getDataList();
			for(Location status:list){
				JSONArray subArray = new JSONArray();
				subArray.put(status.getCode());
				subArray.put(status.getDescription());
				subArray.put(status.getRecordId());
				subArray.put(status.getVersion());

				mainArray.put(subArray);
			}
		}

		response.getWriter().write(mainArray.toString());
		logger.debug("**** Leaving from getGridData method *****");
		return null;
	}

	@Override
	public ActionForward getAuthorizeData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONArray mainArray = new JSONArray();

		List<QueryCriteria> criteriaList = getAuthorizeQueryCriteriaList(request);
		criteriaList.add(new QueryCriteria("recordStatus",Oparator.GRATERTHAN,RecordStatusEnum.ACTIVE.getCode()));
		List<Location> list = (List<Location>)locationService.getAllLocation(SessionUtil.getUserSession(request), criteriaList).getDataList();

		for(Location status:list){
			JSONArray subArray = new JSONArray();
			subArray.put(status.getCode());
			subArray.put(status.getDescription());
			subArray.put(getRecordStatusString(status.getRecordStatus()));
			subArray.put(status.getRecordId());
			subArray.put(status.getVersion());

			mainArray.put(subArray);
		}

		response.getWriter().write(mainArray.toString());
		return null;
	}
}
