<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="sub_dialog_error" class="filtres filtres-oberts" title="<s:text name='txt.error.title' />">
	 <table>
	 	<tr>
	 		<td><h1><label id="sub_errorOn" > </label></h1></td>
	 	</tr>
	 </table>		
</div>  

<script>

$("#sub_dialog_error").dialog( { autoOpen: false,
	  modal: true,
	  position: 'center',
	  draggable: true,
	  height: 200,
	  width: 400,		
	  open: function(event, ui) { 		
		$('#sub_dialog_error').css('overflow', 'hidden');		
	 }
});

	if(errorOnline==undefined || errorOnline=='undefined'){
		var errorOnline={};
	}
	
	errorOnline.suberror= function(txt){
								 $("#sub_errorOn").text(txt);
								 $("#sub_dialog_error").dialog("open");		
							 };
</script>