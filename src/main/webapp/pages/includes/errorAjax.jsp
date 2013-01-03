<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="dialog_error" class="filtres filtres-oberts" title="<s:text name='txt.error.title' />">
	 <table>
	 	<tr>
	 		<td><img  src="<c:url value='/images/error/errorAjax.jpg'/>" width="50px" > </td>
	 		<td><h1><label id="errorOn" > </label></h1></td>
	 	</tr>
	 </table>		
</div>  

<script>

$("#dialog_error").dialog( { autoOpen: false,
	  modal: true,
	  position: 'center',
	  draggable: true,
	  height: 90,
	  width: 200,		
	  open: function(event, ui) { 		
		$('#dialog_error').css('overflow', 'hidden');		
	 }
});

	if(errorOnline==undefined || errorOnline=='undefined'){
		var errorOnline={};
	}
	
	errorOnline.error= function(txt){
								 $("#errorOn").text(txt);
								 $("#dialog_error").dialog("open");		
							 };
</script>