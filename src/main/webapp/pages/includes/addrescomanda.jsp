<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


	<div class="page">
		<div id="main">
		   		<form id="MyForm" name="MyForm" action="form.html">
									                
					    
					    <s:text name="adreca.carrer" />*:<br> <input class="inputs" type="text" id="carrer" onclick="focus()" ><br>
						<s:text name="adreca.codipostal" />*:<br> <input class="inputs" type="text" id="codi" onclick="focus()" ><br>
						
						<div class="inline">
							<s:text name="adreca.numcarrer" />*:<br><input class="inputs_short" type="text" id="numcarrer" onclick="focus()" >
						</div>
						<div class="inline">
							<s:text name="adreca.pis" />*:<br><input class="inputs_short" type="text" id="num" onclick="focus()" >
						</div>
						<div class="inline">
							<s:text name="adreca.porta" />*:<br><input class="inputs_short" type="text" id="porta" onclick="focus()" >
						</div>
						<div class="inline2">
						<s:text name="user.altres" />*:<br> <s:textfield cssClass="inputs" key="altres" id="altres" theme="simple"  onclick="focus()" /><br>
						</div>
						<input type="text" id="poble" disabled="disabled" value="Girona" class="inputs sepa" />    
						
						<input id="checkAdd" class="boton" value="<s:text name="txt.button.address.ok" />" type="button" />
						<label id="addressOK" ></label>						                	
				</form>
		</div>
	</div>
	
	