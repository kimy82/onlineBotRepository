///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtborrat,txtconfirmborrapromo,txterrordouble,txterrornumber){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txtborrat=txtborrat;	
		this.txtconfirmborrapromo= txtconfirmborrapromo;
		this.txterrordouble=txterrordouble;
		this.txterrornumber=txterrornumber;
}

//per el formulari
function onlyDouble(value,id){
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


function opendivNewBeguda(){
	resetForm();
	$("#infobegudanew").show('slow');
}

function resetForm(){
		$("#nomBeguda").val("");
		$("#tipusBeguda option:first").attr('selected','selected');		
		$("#importBeguda").val("");
		$("#descripcioBeguda").val("");
		$("#id").val("");		
}



function reloadTableBegudes(){
	oTablebegudes.fnDeleteRow( 0 );
}

function showDivBeguda(id){
	
	data ="idBeguda="+id;
	$.ajax({
		  type: "POST",
		  url: '/onlineBot/admin/ajaxLoadBegudaAction.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json==null || json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else{
					 	
					 		$("#id").val(json.id);
					 		$("#nomBeguda").val(json.nom);
					 		$("#tipusBeguda").val(json.tipus);					 		
					 		$("#importBeguda").val(json.preu);
					 		$("#descripcioBeguda").val(json.descripcio);
					 		
					 		$("#infobegudanew").show('slow');					 							 			 					 										 					 						 					 						  
     			}				
		  },
		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
  								$("#errorsajax").show();  		
		  					}
		});	
	
}

function deleteBeguda(id){
	 var where_to= confirm(initTableParams.txtconfirmborrapromo);
	  if (where_to== false)
	  {
		    return;
	  }
	  else {
		
			data ="idPromocio="+id;
			$.ajax({
				  type: "POST",
				  url: '/onlineBot/admin/ajaxDeleteBeguda.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
					  if(json!=null && json.error!=null){
		   				$("#errorsajaxlabel").text(json.error);
		   				$("#errorsajax").show();
		   			}else{
							 alert(initTableParams.txtborrat);
							 reloadTableBegudes();							
		   			}				
				  },
				  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
										$("#errorsajax").show();  		
				  					}
				});
	  }
}

var  oTablebegudes=null;


$(document).ready(function() {

	
			
		//taula de les begudes
	oTablebegudes =$("#tbl_begudes").dataTable( {
					"iDisplayLength": 12,
					 "aoColumns" : [
					                  { "mDataProp":"tipus","bSortable": false, sWidth: '150px' },
					                  { "mDataProp":"nom", "bSortable": false, sWidth: '350px' },
					                  { "mDataProp":"preu", "bSortable": false, sWidth: '40px' },
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
		    		"sAjaxSource": '/onlineBot/admin/ajaxTableBegudes.action',
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
		$("#infobegudanew").hide();
		$("#errorsajax").hide();

} );















