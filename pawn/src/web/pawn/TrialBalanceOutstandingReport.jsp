<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<html:html>
	<head>
		<title>'TrialBalanceOutstandingReport.jsp'</title>
		<link rel="StyleSheet" type="text/css" href="<html:rewrite forward="commonCSS"/>"></link>
		<link rel="StyleSheet" type="text/css" href="<html:rewrite forward="awCSS"/>"></link>
		<link rel="StyleSheet" type="text/css" href="<html:rewrite forward="calendarCSS"/>"></link>
		<script type="text/javascript" src="<html:rewrite forward="commonJS"/>"></script>
		<script type="text/javascript" src="<html:rewrite forward="ajaxJS"/>"></script>
		<script type="text/javascript" src="<html:rewrite forward="awJS"/>"></script>
		<script type="text/javascript" src="<html:rewrite forward="calendarJS"/>"></script>
		

		<script type="text/javascript">
			window.parent.document.getElementById('footer').style.display="none";			
			window.parent.document.getElementById("mainbody").height="600px";
			window.parent.document.getElementById("footer").height="0px";
			
			function printDocument(){
				var branchId = $('branchId').value;
				if(branchId>0){	
				
				url = "trialbaloutstandingService.do?dispatch=print&rand=" + Math.random()*9999999 + "&branchId=" + branchId +
			    		  "&beginDate=" + $('beginDate').value + "&endDate=" + $('endDate').value+
			    		  "&tiketstatus=" + $('tiketstatus').value ;
						   		
			    		  
					theHeight=500;
					theWidth=800;
					var theTop=(screen.height/2)-(theHeight/2);
					var theLeft=(screen.width/2)-(theWidth/2);
					var features='height='+theHeight+',width='+theWidth+',top='+theTop+',left='+theLeft+",status=yes,scrollbars=no,resizable=yes";
					window.open(url,"PrintPreview",features);
				}else{
					alert('Please select a Branch to print the report.');
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
				//obj.onSelectedRowsChanged= function(row){};				
				return obj;
			}
			
			function setGridData(Data){
				grid.setRowCount(Data.length);
				grid.setCellText(Data);
				grid.setSelectedRows([]);
				grid.refresh();
			}
			
			function getSerchData(){
				data = "dispatch=getAjaxData";	
				var mySearchRequest = new ajaxObject("branchService.do");
				mySearchRequest.callback = function(responseText, responseStatus, responseXML) {
					if (responseStatus==200) {						
						var message =  eval('(' + responseText + ')');						
						setGridData(message);						
					}else {
			    	    alert(responseStatus + ' -- Error Processing Request'); 
				    }
				}
				mySearchRequest.update(data,'POST');
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
			
			#firstGrid {height: 310px;width:720px;}
			#firstGrid .aw-row-selector {text-align: center}
			#firstGrid .aw-column-0 {width: 120px;}
			#firstGrid .aw-column-1 {width: 555px;}	
			#firstGrid .aw-grid-cell {border-right: 1px solid threedlightshadow;}
			#firstGrid .aw-grid-row {border-bottom: 1px solid threedlightshadow;}
		</style>
	</head>

	<body>
		<html:form action="trialbaloutstandingService.do">
			<input type="hidden" name="branchId" id="branchId">

			<table border="0">	
										                          								
				<tr>
					<td colspan="2">							
						<table class="InputTable">    
							<tr height="2px"></tr>
							<tr>									
								<td colspan="2" align="center">
									<script>
										var myColumns = ["<bean:message bundle="lable" key="screen.code"/>",
							  						     "<bean:message bundle="lable" key="screen.branchname"/>"];
			            				var str = new AW.Formats.String; 
			            				var cellFormat = [str,str];
		                    			var grid = createBrowser(myColumns,'firstGrid',cellFormat);			                    						                    						                    			
		                                document.write(grid);		                                
		                                grid.onSelectedRowsChanged = function(row){	
		                                	if(row!='') { 													
												$('branchId').value     = this.getCellText(18,row);													
											}												
										}
										getSerchData();
		                            </script>
		                        </td>
		                    </tr>
		                </table>
		            </td>
		        </tr>         
	         <tr height="10px"></tr>	
				<tr>
					<td colspan="2">							
						<table class="InputTable">    
							<tr height="5px"></tr>
							<tr>
								<td width="20%" align="right">
									<bean:message bundle="lable" key="screen.begindate"/>&nbsp;
								</td>
								<td>
									<table cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td width="23%">
												<html:text property="beginDate" size="15" maxlength="12" styleId="beginDate" onkeypress="DateFormat(this,event);" onfocus="clearDivision('divBeginDate')" onkeyup="DateFormat(this,event);" /><img src="images/none.gif" width="5px" ><input type="button" value="..." onclick="return showCalendar('beginDate');" />
												<font color="red">*</font><br/>
												<div id="divBeginDate" class="validate"/>
											</td>
											<td>
												<table cellpadding="0" cellspacing="0">
													<tr>
														<td style="width: 140px" align="right">
															<bean:message bundle="lable" key="screen.enddate"/>&nbsp;
														</td>
														<td>
															<html:text property="endDate" size="15" maxlength="12" styleId="endDate" onkeypress="DateFormat(this,event);" onfocus="clearDivision('divEndDate')" onkeyup="DateFormat(this,event);" /><img src="images/none.gif" width="5px"><input type="button" value="..." onclick="return showCalendar('endDate');" />
															<font color="red">*</font><br/>
															<div id="divEndDate" class="validate"/>
														</td>
													</tr>
												</table>
											</td>
											
											
											<td  align="left" >
													<bean:message bundle="lable" key="screen.ticketstatus"/>&nbsp;
															<select id="tiketstatus" name="tiketstatus" style="width: 70px;font-size: 12px">
																<option value="0">Both</option>
																<option value="1">Active</option>
																<option value="2">Laps</option>
															</select>
											</td>
										</tr>
									<tr height="2px"></tr>
									
									</table>										
								</td>
								
							</tr>
							<tr height="5px"></tr>
						</table>
					</td>			
				</tr>        
		                 
		
			</table>
					
			<table class="toolTable" id="toolbar">
				<tr>
					<td align="center">
						<input type='button' value="<bean:message bundle="button" key="button.print"/> " class="buttonNormal" id="Print" onclick="printDocument()"/> 					
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>
