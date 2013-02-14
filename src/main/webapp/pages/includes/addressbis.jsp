<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

		   		<form id="MyForm" name="MyForm" action="form.html">
									                
					    
					    <s:text name="adreca.carrer" />*:<br> <input class="inputs" type="text" id="carrerbis" onclick="focus()" ><br>
						<s:text name="adreca.codipostal" />*:<br> <input class="inputs" type="text" id="codibis" onclick="focus()" ><br>
						
						<div class="inline">
							<s:text name="adreca.numcarrer" />*:<br><input class="inputs_short" type="text" id="numcarrerbis" onclick="focus()" >
						</div>
						<div class="inline">
							<s:text name="adreca.pis" />*:<br><input class="inputs_short" type="text" id="numpis" onclick="focus()" >
						</div>
						<div class="inline">
							<s:text name="adreca.porta" />*:<br><input class="inputs_short" type="text" id="porta" onclick="focus()" >
						</div>
						<input type="hidden" id="poblebis" disabled="disabled" value="Girona"  />  
						<input id="checkAddbis" class="boton" value="<s:text name="txt.button.address.ok" />" type="button" />
						<label id="addressOKbis" ></label>						                	
				</form>