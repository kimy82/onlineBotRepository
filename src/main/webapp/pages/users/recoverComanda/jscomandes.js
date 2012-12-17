///////////////////////////////////
//variables per textos en locale


//per el formulari
function onlyDouble(value,id){
	 var n=value.split(".");
	  if(n.length==1){
		  value=value+".00";
	  }
	  if(value =='' || /^[0-9]*\.[0-9]*$/.test(value)){
		$('#'+id).css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#'+id).css('border', 'solid 1px red');
		alert(initTableParams.txterrordouble);
	}
}  

function onlyEntero(value,id){
	  if(value =='' || /^[0-9]*$/.test(value)){
		$('#'+id).css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#'+id).css('border', 'solid 1px red');
		alert(initTableParams.txterrornumber);
	}
}

function ismaxlength(obj,mlength){
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength);
}


function fillAddress(){
	
	var addressCarrer = $("#carrer").val();
	var addressNum = $("#numcarrer").val();
	var addressCodi = $("#codi").val();
	var addressPoble = $("#poble").val();
	
	$("#address").val(addressCarrer+" "+addressNum+", "+addressCodi+" "+addressPoble);
	
}


function openCloseDiv(id){
	 	
	 id($("#"+id).is(":hidden")){
		 $("#"+id).show('slow');
	 }else{
		 $("#"+id).hide('slow');
	 }
}





$(document).ready(function() {
				
		//ocultem divs
		$("#errorsajax").hide();
		$("#infoUser").hide();
		
} );















