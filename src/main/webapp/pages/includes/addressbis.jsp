<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


	<div class="page">
		<div id="main">
		   		<form id="MyForm" name="MyForm" action="form.html">
					<div id="map_canvasbis" style="float: right; height: 200px; width: 400px;"></div>				                
					                <table>
						                <tr>
						                	<td>						                	
	                        					<label for="carrer">
	                        							 <s:text name="adreca.carrer" /></label>		
	                        					<input type="text" id="carrerbis"  />  
	                        					
	                        					<label for="numcarrer">
	                        							  <s:text name="adreca.numcarrer" /></label>		
	                        					<input type="text" id="numcarrerbis"  />
	                        					
	                        					<label for="codi">
	                        							  <s:text name="adreca.codipostal" /></label>		
	                        					<input type="text" id="codibis"  />
	                        					
	                        					<label for="poble">
	                        							  <s:text name="adreca.poble" /></label>		
	                        					<input type="text" id="poblebis" disabled="disabled" value="Girona"  />                            						                    													    										                
						                    </td>
						                </tr>
						                <tr>
							                <td>				                
							                    <input id="checkAddbis" value="<s:text name="txt.button.address.ok" />" type="button" />
							                </td>
							                <td>
							                <label id="addressOKbis" ></label>
							                </td>    
						                </tr>
					                </table>
				</form>
		</div>
	</div>