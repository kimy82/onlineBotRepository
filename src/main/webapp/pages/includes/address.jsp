<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


	<div class="page">
		<div id="main">
		   		<form id="MyForm" name="MyForm" action="form.html">
					<div id="map_canvas" style="float: right; height: 200px; width: 400px;"></div>				                
					                <table>
						                <tr>
						                	<td>						                	
	                        					<label for="carrer">
	                        							 <s:text name="adreca.carrer" /></label>		
	                        					<input type="text" id="carrer"  />  
	                        					
	                        					<label for="numcarrer">
	                        							  <s:text name="adreca.numcarrer" /></label>		
	                        					<input type="text" id="numcarrer"  />
	                        					
	                        					<label for="codi">
	                        							  <s:text name="adreca.codipostal" /></label>		
	                        					<input type="text" id="codi"  />
	                        					
	                        					<label for="poble">
	                        							  <s:text name="adreca.poble" /></label>		
	                        					<input type="text" id="poble" disabled="disabled" value="Girona"  />                            						                    													    										                
						                    </td>
						                </tr>
						                <tr>
							                <td>				                
							                    <input id="checkAdd" value="<s:text name="txt.button.address.ok" />" type="button" />
							                </td>
							                <td>
							                <label id="addressOK" ></label>
							                </td>    
						                </tr>
					                </table>
				</form>
		</div>
	</div>