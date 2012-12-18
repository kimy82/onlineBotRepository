///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtborrat){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txtborrat=txtborrat;		
}

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

function reloadTableComandes(){
	oTablecomandes.fnDeleteRow( 0 );
}

function fillAddress(){
	
	var addressCarrer = $("#carrer").val();
	var addressNum = $("#numcarrer").val();
	var addressCodi = $("#codi").val();
	var addressPoble = $("#poble").val();
	
	$("#address").val(addressCarrer+" "+addressNum+", "+addressCodi+" "+addressPoble);
	
}

function repeatComanda(id){
	
	window.location.href="/onlineBot/user/repeatComanda.action?idComanda="+id;
}

function openCloseDiv(id){
	 	
	 if($("#"+id).is(":hidden")){
		 $("#"+id).show('slow');
	 }else{
		 $("#"+id).hide('slow');
	 }
}

function checkPassword(){
	var password1 = document.getElementById("password").value;
	var password2 = document.getElementById("passwordRetyped").value;
	if(password1 != password2){
		$('#passwordRetyped').css('border', 'solid 1px red');
		alert("Passwords no son iguals");
	}
}

var  oTablecomandes=null;


$(document).ready(function() {
				
		//taula de les comandes d'un user
	oTablecomandes =$("#tbl_comandes_user").dataTable( {
					"iDisplayLength": 12,
					 "aoColumns" : [
					                  { "mDataProp":"dia","bSortable": false, sWidth: '150px' },
					                  { "mDataProp":"plats", "bSortable": false, sWidth: '350px' },
					                  { "mDataProp":"preu", "bSortable": false, sWidth: '40px' },
					                  { "mDataProp":"observacions", "bSortable": false, sWidth: '40px' },
					                  { "mDataProp":"accio", "bSortable": false, sWidth: '40px' }
					            ],
					"sPaginationType": "full_numbers",
					"oLanguage": {
						  "sProcessing": "<img src='/onlineBot/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
					      "oPaginate": {
					        "sFirst": "<img src='/onlineBot/images/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
					        "sLast": initTableParams.txtlast+"&nbsp;<img src='/onlineBot/images/icono-paginador-fin.gif' style='vertical-align:middle'>",
					        "sNext": initTableParams.txtnext+"&nbsp;<img src='/onlineBot/images/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
					        "sPrevious": "<img src='/onlineBot/images/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious
					      }
					    },
					"sScrollY": "300",		    
					"sScrollX": "752",	
				    "bScrollCollapse": true,
		    		"bProcessing": false,
		    		"bServerSide": true,
		    		"sAjaxSource": '/onlineBot/user/ajaxTableComandesUser.action',
		    		"fnServerData": function( sUrl, aoData, fnCallback) {      			    			    			
		     		$.ajax( {
		            	"url": sUrl,
		            	"data": aoData,              
		            	"dataType": "json",
		            	"cache": false,
		           		"success":function(json){  
		           			if(json.error!=null){
		           				$("#errorsajaxlabel").text(json.error);
		                		$("#errorsajax").show();
		           			}else{
			            		fnCallback(json);			            		
		           			}            	
		            	},
		            	"error":function(e){ 
		            		$("#errorsajaxlabel").text("Error in ajax call");
		            		$("#errorsajax").show();            	
		            	}
		        	} );
		    	}
			} );
		
				
		//ocultem divs
		$("#errorsajax").hide();
		$("#password_div").hide();		
		$("#infoUser").hide();
		
} );















