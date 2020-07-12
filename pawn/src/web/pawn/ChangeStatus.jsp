<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% try { %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  	<head>
		<link rel="StyleSheet" type="text/css" href="<html:rewrite forward="commonCSS"/>"></link>
		<link rel="StyleSheet" type="text/css" href="<html:rewrite forward="awCSS"/>"></link>
		<link rel="StyleSheet" type="text/css" href="css/com-all.css"></link>
		<script type="text/javascript" src="<html:rewrite forward="commonJS"/>"></script>
		<script type="text/javascript" src="<html:rewrite forward="ajaxJS"/>"></script>
		<script type="text/javascript" src="<html:rewrite forward="awJS"/>"></script>	
		<script type="text/javascript" src="js/com-all.js"></script>
		
		
		<script type="text/javascript">								
			var action = 1;
			
			var url = 'changeStatusService.do';
			function loadToolBar(){	
			    if(CurrentPage(window.parent.frames['footer'].location.pathname)!='toolbar.jsp'){
			   		open('toolbar.jsp?evn=<c:out value="${param.evn}" />','footer');			   		
			    }			    
			}
			
			function createBrowser(myColumns,gridName,dataFormat){
				var obj = new AW.UI.Grid;
				obj.setId(gridName);
				obj.setCellText([]);
				obj.setCellFormat(dataFormat);
				obj.setHeaderText(myColumns);
				obj.setSelectorVisible(true);
				obj.setRowCount(0);
				obj.setColumnCount(myColumns.length);
				obj.setSelectorText(function(i){return this.getRowPosition(i)+ 1});
				obj.setSelectorWidth(20);
				obj.setHeaderHeight(20);
				obj.setSelectionMode("single-row");
				obj.onSelectedRowsChanged= function(row){};				
				return obj;
			}
			
			function setGridData(gridObj,Data){
				gridObj.setRowCount(Data.length);
				gridObj.setCellText(Data);
				gridObj.setSelectedRows([]);
				gridObj.refresh();
			}
			
			function getRecordData(id){
				data = "dispatch=getAjaxData&recordId=" + id ;
				var mySearchRequest = new ajaxObject(url);
				mySearchRequest.callback = function(responseText, responseStatus, responseXML) {
					if (responseStatus==200) {						
						var message =  eval('(' + responseText + ')');
						if(message['error']){
							alert(message['error']);
						}else{
							$('code').value        = message[0];
							$('description').value = message[1];
							$('recordId').value    = message[2];
							$('version').value     = message[3];	
						}	
					}else {
			    	    alert(responseStatus + ' -- Error Processing Request'); 
				    }
				}
				mySearchRequest.update(data,'POST');
			}
			
			function getGridData(){			
				data = "dispatch=getTicketById&ticketId=" + $('ticketId').value;
				var mySearchRequest = new ajaxObject(url);
				mySearchRequest.callback = function(responseText, responseStatus, responseXML) {
					if (responseStatus==200) {						
						var message =  eval('(' + responseText + ')');
						if(message['error']){
							alert(message['error']);
						}else{	
							setGridData(grid,message);					
						}	
					}else {
			    	    alert(responseStatus + ' -- Error Processing Request'); 
				    }
				}
				mySearchRequest.update(data,'POST');
			}
						
			function getSerchData(rurl,gridobj){
				data = "dispatch=getAjaxData&ticketStatus=9";	
				var mySearchRequest = new ajaxObject(rurl);
				mySearchRequest.callback = function(responseText, responseStatus, responseXML) {
					if (responseStatus==200) {						
						var message =  eval('(' + responseText + ')');						
						setGridData(gridobj,message);						
					}else {
			    	    alert(responseStatus + ' -- Error Processing Request'); 
				    }
				}
				mySearchRequest.update(data,'POST');
			}
			
			function clearAll(){
				//$('ticketNumber').value 			= '';
				//$('ticketId').value   			= '0';
				//$('auctionDescription').value 	= '';
				
				$('dueTypeId').value 			= '0';
				$('dueTypeCode').value   		= '';
				$('dueTypeDescription').value 	= '';
				$('amount').value 			    = '0';
				
				//$('divDescription').innerHTML 	= '';
				//$('divTicketNo').innerHTML 	= '';
				$('divDueTypeCode').innerHTML 	= '';	
				setGridData(grid,[]);
			}
			
			function clearFilled(){
				$('code').value		   = '';
				$('description').value = '';				
				$('recordId').value    = '';
				$('version').value     = '';
				$('dueTypeId').value   = '0';
				$('dueTypeCode').value = '';
				$('dueTypeDescription').value = '';
				$('amount').value 	= '0';
				
				$('divDescription').innerHTML = '';
				$('divTicketNo').innerHTML = '';
				$('divDueTypeCode').innerHTML = '';
				grid.setSelectedRows([]);
			}
			
			function clearOtherData(){
				$('code').value		   = '';
				$('description').value = '';
				$('recordId').value    = '';
				$('version').value     = '';
				
				$('divDescription').innerHTML = '';
				$('divTicketNo').innerHTML = '';
				$('divDueTypeCode').innerHTML = '';
				setGridData(grid,[]);
			}
			
			function getCreateData(){
				var ticketId = $('ticketId').value;
				var statusId = $('tiketstatus').value;
				return "&ticketId=" + ticketId + "&statusId=" + statusId;
			}
			
			function getChangedData(){
				var recordId = $('recordId').value;
				var version  = $('version').value;
				
				var str = getCreateData() + "&recordId=" + recordId + "&version=" + version;	
				return str;
			}
		
			function showValidationErrors(message){
	       		for( var i =0; i < message.length ; i++){	            	
					if( message[i]['ticketNumber'] )
	                    $('divTicketNo').innerHTML = message[i]['ticketNumber'];
	                else if( message[i]['dueTypeCode'] )
	                    $('divDueTypeCode').innerHTML = message[i]['dueTypeCode'];
	            }    
	        }
	        function getTicketData(){
	        	getGridData();
	        }
		</script>
		
		<style>
			#firstGrid {height: 250px;width:720px;}
			#firstGrid .aw-row-selector {text-align: center}
			#firstGrid .aw-column-0 {width: 150px;}
			#firstGrid .aw-column-1 {width: 300px;}	
			#firstGrid .aw-column-2 {width: 200px;}
			#firstGrid .aw-grid-cell {border-right: 1px solid threedlightshadow;}
			#firstGrid .aw-grid-row {border-bottom: 1px solid threedlightshadow;}
		</style>
  	</head>
  
  	<body onload="loadToolBar()">
    	<div id="screenCont" class="enableAll" align="center" ></div>
    	<input type="hidden" id="ticketStatus" name="ticketStatus" value="9" />
    	<jsp:include flush="true" page="TicketBrowser.jsp"></jsp:include>
    			
		<div id="hidDiv" class="hideSearchPopup">
		</div>
    	
    	
    	<!-- update record -->
    	<logic:equal name="changestatus" property="action" value="update">
			<html:form action="changeStatusService.do">
				<input type="hidden" id="recordId" name="recordId"/>
				<input type="hidden" id="version" name="version"/>
				
				<table border="0">
					<tr>
						<td>							
							<table class="InputTable">    
								<tr height="5px"></tr> 
								<tr>
									<td width="20%" align="right">
										<bean:message bundle="lable" key="screen.tiketno"/>&nbsp;
									</td>
									<td>
										<input type="hidden" id="ticketId" name="ticketId" value="0"/>
										<html:text property="ticketNumber" styleId="ticketNumber" size="18" maxlength="18" 
											onfocus="clearDivision('divTicketNo')"/>
										<input id="ButtonTicketSerch" type="button" value="..." />
										
										<font color="red">*</font><br/>
										<div id="divTicketNo" class="validate"></div>
									</td>
								</tr>
								<tr height="5px"></tr>
							</table>
						</td>
					</tr>
					<tr height="5px"></tr> 
					<tr>
						<td>							
							<table class="InputTable">																	
								<tr>									
									<td colspan="4" align="center">
										<script>
											var myColumns = ["<bean:message bundle="lable" key="screen.tiketno"/>",
							  								 "<bean:message bundle="lable" key="screen.clientname"/>",
							  								 "<bean:message bundle="lable" key="screen.status"/>"];
				            				var str = new AW.Formats.String; 
				            				var cellFormat = [str,str,str];
			                    			var grid = createBrowser(myColumns,'firstGrid',cellFormat);			                    						                    						                    			
			                                document.write(grid);
			                                grid.onSelectedRowsChanged = function(row){	
			                                	if(row!='') { 			                                		
			                                		$('ticketId').value = grid.getCellText(3,row);
			                                		$('version').value  = grid.getCellText(4,row);
			                                	}																					
			                                };
			                            </script>
			                        </td>
			                    </tr>            
			                </table>
						</td>
					</tr>
			    	<tr height="5px"></tr>
		          	<tr>
						<td>							
							<table class="InputTable">    
								<tr height="5px"></tr> 								 
								<tr>
									<td width="20%" align="right">
										<bean:message bundle="lable" key="screen.status"/>&nbsp;
									</td>
									<td  align="left" >
											<select id="tiketstatus" name="tiketstatus" style="width: 70px;font-size: 12px">
												<option value="6">Stolen</option>
												<option value="7">Dead</option>
											</select>
									</td>
								</tr>
								<tr height="2px"></tr>								
							</table>
						</td>
					</tr>
					
					<tr height="5px"></tr>
					
				</table>
			</html:form>
    	</logic:equal>

    	<!-- View record -->
    	<logic:equal name="changestatus" property="action" value="view">
    		<br/>
			<html:form action="changeStatusService.do">
				<input type="hidden" id="recordId" name="recordId"/>
				<input type="hidden" id="version" name="version"/>
				
				</html:form>
    	</logic:equal>
    	
    	<script>
			var winTicket;
			Ext.onReady(function(){
			    
			    var button = Ext.get('ButtonTicketSerch');
			    button.on('click', function(){
			        if(!winTicket){
			            winTicket = new Ext.Window({
			                el:'ticket-serchDiv',
			                layout:'fit',
			                width:600,
			                height:300,
			                closable:false, 
			                closeAction:'hide',
			                plain: true,
			
			                items: new Ext.TabPanel({
			                    el: 'serch-tab',
			                    autoTabs:true,
			                    activeTab:0,
			                    deferredRender:false,
			                    border:false
			                }),
			
			                buttons: [{
			                    text: 'Ok',
			                    handler: function(){				                   		
			                   		//getTableData();
			                   		//$('hidDiv').className='hideSearchPopup';
			                        winTicket.hide();
			                        getGridData();
			                   	}
			                }]
			            });
			        }
			        winTicket.show(this);
			        getSerchData("ticketService.do",gridSerchPrd);			        
			    });
			});
		</script>
  	</body>
</html:html>
<% } catch(Exception e) { 
	System.out.print(e.getMessage()); 
} %>