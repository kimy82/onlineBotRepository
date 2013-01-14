<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="sub_dialog_alert" class="filtres filtres-oberts" title="<s:text name='txt.alert.title' />">
	 <h1><label id="sub_alertOn" > </label></h1>		
</div>  

<script>

$("#sub_dialog_alert").dialog( { autoOpen: false,
	  modal: true,
	  position: 'center',
	  draggable: true,
	  height: 200,
	  width: 400,		
	  open: function(event, ui) { 		
		$('#sub_dialog_alert').css('overflow', 'hidden');		
	 }
});


	if(alertOnline == undefined || alertOnline =='undefined'){
		var alertOnline={};
	}
	
	alertOnline.subalertes= function(txt){
								 $("#sub_alertOn").text(txt);
								 $("#sub_dialog_alert").dialog("open");		
							 };


</script>