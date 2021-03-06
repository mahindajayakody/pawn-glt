<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  	<head>
		<title>My JSP 'ChangePassword.jsp' starting page</title>
		<link rel="StyleSheet" type="text/css" href="<html:rewrite forward="commonCSS"/>"></link>
		<script type="text/javascript" src="<html:rewrite forward="commonJS"/>"></script>
		<script type="text/javascript" src="<html:rewrite forward="ajaxJS"/>"></script>
		<script type="text/javascript" src="<html:rewrite forward="commonJS"/>"></script>
		
		<script type="text/javascript">
			window.parent.$('footer').style.display="none";			
			window.parent.$("mainbody").height="600px";
			window.parent.$("footer").height="0px";
			
			var url = 'changePasswordService.do';
			
			function clearAll(){
				$('oldPassword').value = '';
				$('newPassword').value = '';
				$('conPassword').value = '';
				
				$('divOldPassword').innerHTML = '';
				$('divNewPassword').innerHTML = '';
				$('divConPassword').innerHTML = '';
			}
			
			function getCreateData(){
				var oldPassword = $('oldPassword').value;
				var newPassword	= $('newPassword').value
				var conPassword = $('conPassword').value;
				
				return "&oldPassword=" + oldPassword + "&newPassword=" + newPassword +
					   "&conPassword=" + conPassword;
			}
			
			function submitData(){				
				data = "dispatch=changePassword" + getCreateData();

				var mySearchRequest = new ajaxObject(url);
				mySearchRequest.callback = function(responseText, responseStatus, responseXML) {
					if (responseStatus==200) {												
						var message =  eval('(' + responseText + ')');
						if(message['error']){
			       			alert(message['error']);
			       		}else if(message['success']){			       			
			       			alert(message['success']);
			       			clearAll();				       						       				       			  						       									
			       		}else{
			       			showValidationErrors(message);
			       		}
			       		$('screenCont').className = 'enableAll';	
					}else {
			    	    alert(responseStatus + ' -- Error Processing Request'); 
			    	    $('screenCont').className = 'enableAll';
				    }
				}
				mySearchRequest.update(data,'POST');
			}			
	        	    
		    function confirms(){
		    	$('screenCont').className = 'disableAll';
		    	if(confirmCommonSubmit('',1)){
			    	submitData();
			    }
		    }
		
			function showValidationErrors(message){
	       		for( var i =0; i < message.length ; i++){
	                if( message[i]['oldPassword'] )
	                    $('divOldPassword').innerHTML = message[i]['oldPassword'];
	                else if( message[i]['newPassword'] )
	                    $('divNewPassword').innerHTML = message[i]['newPassword'];
	               	else if( message[i]['conPassword'] )
	                    $('divConPassword').innerHTML = message[i]['conPassword'];	               	
	            }
	        }
		</script>
		
		<style>
			table.toolTable { 
			    border: #91a7b4 1px solid;
			    width: 740px;
			    height:30px;
			    background-color:#FFFFFF;
			    bottom: 0px;
			    position: absolute;
			}
		</style>
	</head>

	<body>
		<div id="screenCont" class="enableAll" align="center" ></div>
		
		<logic:equal name="passwordchange" property="action" value="create">
			<html:form action="changePasswordService.do">
				<input type="hidden" id="recordId" name="recordId"/>
				<input type="hidden" id="version" name="version"/>

				<table border="0">
					<tr>
						<td>
							<table class="InputTable">
								<tr height="5px"></tr>
								
								<tr>
									<td width="30%" align="right">
										<bean:message bundle="lable" key="screen.oldpassword"/>&nbsp;
									</td>
									<td>
										<html:password property="oldPassword" styleId="oldPassword" size="20" maxlength="16"
											onfocus="clearDivision('divOldPassword')"/>										
										<font color="red">*</font><br/>
										<div id="divOldPassword" class="validate"/>
									</td>
								</tr>
								
								<tr height="5px"></tr>
								<tr>
									<td width="30%" align="right">
										<bean:message bundle="lable" key="screen.newpassword"/>&nbsp;
									</td>
									<td>
										<html:password property="newPassword" styleId="newPassword" size="20" maxlength="16"
											onfocus="clearDivision('divNewPassword')"/>										
										<font color="red">*</font><br/>
										<div id="divNewPassword" class="validate"/>
									</td>
								</tr>
								
								<tr height="5px"></tr>
								<tr>
									<td width="30%" align="right">
										<bean:message bundle="lable" key="screen.conpassword"/>&nbsp;
									</td>
									<td>
										<html:password property="conPassword" styleId="conPassword" size="20" maxlength="16"
											onfocus="clearDivision('divConPassword')"/>										
										<font color="red">*</font><br/>
										<div id="divConPassword" class="validate"/>
									</td>
								</tr>
								<tr height="5px"></tr>
							</table>
						</td>
					</tr>
				</table>
				
				<table class="toolTable" id="toolbar">
					<tr>
						<td align="right">
							<input type="button" value="<bean:message bundle="button" key="button.submit"/>" id="Check" class="buttonNormal" onclick='confirms();'/>
							<input type="button" value="<bean:message bundle="button" key="button.clear"/>" id="Clear" class="buttonNormal" onclick="clearAll();"/>
						</td>
					</tr>
				</table>
			</html:form>
		</logic:equal>						
	</body>
</html:html>
