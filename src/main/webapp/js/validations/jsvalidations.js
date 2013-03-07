//per el formulari
function onlyDouble(value,id){
	 var n=value.split(".");
	  if(n.length==1){
		  	if(value =='' || /^[0-9]*$/.test(value)){
				$('#'+id).css('border', 'solid 1px rgb(135,155,179)');
				return;
			}else{
				$('#'+id).css('border', 'solid 1px red');
				alert(initTableParams.txterrordouble);
			}
	  }
	  if(value =='' || /^[0-9]*\.[0-9]*$/.test(value)){
		$('#'+id).css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#'+id).css('border', 'solid 1px red');
		alert(initTableParams.txterrordouble);
	}
}  

function changePreu(id){
	var preu = $("#"+id).val();
	preu = preu.replace(".",",");
	$("#"+id).val(preu);
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