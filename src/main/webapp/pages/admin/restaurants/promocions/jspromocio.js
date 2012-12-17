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

function opendivNewPromo(){
	resetForm();
	$("#infopromonew").show('slow');
	$("#pnc_div").hide();
	$("#apd_div").hide();
}

function resetForm(){
		$("#nompromo_apd").val("");
		$("#tipuDescompte_apd").val("");
		$("#descompteImport_apd").val("");
		$("#importAPartirDe").val("");
		$("#dia").val("");
		$("#nompromo_pnc").val("")
 		$("#tipuDescompte_pnc").val("");
 		$("#descompteImport_pnc").val("");
 		$("#numComandes").val("");
 		$("#temps").val("");
 		$("#id_apd").val("");
 		$("#id_pnc").val("");
}

function openDivTipuPromo(id){
	
	if(id=='pnc'){
		$("#pnc_div").show('slow');
		$("#apd_div").hide();
	}else{
		$("#apd_div").show('slow');
		$("#pnc_div").hide();
	}
}

function reloadTablePromos(){
	oTablePromos.fnDeleteRow( 0 );
}
function goToPromocio(id){
	data ="idPromocio="+id;
	$.ajax({
		  type: "POST",
		  url: '/onlineBot/admin/ajaxLoadPromoAction.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json==null || json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else{
					 	if(json.tipus=='apd'){
					 		$("#id_apd").val(json.id);
					 		$("#nompromo_apd").val(json.nom);
					 		$("#tipuDescompte_apd").val(json.tipuDescompte);
					 		$("#descompteImport_apd").val(json.descompteImport);
					 		$("#importAPartirDe").val(json.importAPartirDe);
					 		$("#dia").val(json.diaString);
					 		$("#infopromonew").show('slow');
					 		$("#apd").click();
					 	}
					 	if(json.tipus=='pnc'){
					 		$("#id_pnc").val("");
					 		$("#nompromo_pnc").val(json.nom)
					 		$("#tipuDescompte_pnc").val(json.tipuDescompte);
					 		$("#descompteImport_pnc").val(json.descompteImport);
					 		$("#numComandes").val(json.numComandes);
					 		$("#temps").val(json.temps);
					 		$("#infopromonew").show('slow');
					 		$("#pnc").click();
					 	}					 					 										 					 						 					 						  
     			}				
		  },
		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
  								$("#errorsajax").show();  		
		  					}
		});	
}
function deletePromocio(id){
	 var where_to= confirm(initTableParams.txtconfirmborrapromo);
	  if (where_to== false)
	  {
		    return;
	  }
	  else {
		
			data ="idPromocio="+id;
			$.ajax({
				  type: "POST",
				  url: '/onlineBot/admin/ajaxDeletePromocio.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
					  if(json!=null && json.error!=null){
		   				$("#errorsajaxlabel").text(json.error);
		   				$("#errorsajax").show();
		   			}else{
							 alert(initTableParams.txtborrat);
							 reloadTablePromos();							
		   			}				
				  },
				  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
										$("#errorsajax").show();  		
				  					}
				});
	  }
}

var  oTablePromos=null;


$(document).ready(function() {

	
			
		//taula de les promocions
	oTablePromos =$("#tbl_promos").dataTable( {
					"iDisplayLength": 12,
					 "aoColumns" : [
					                  { "mDataProp":"nom","bSortable": false, sWidth: '150px' },
					                  { "mDataProp":"tipuDescompte", "bSortable": false, sWidth: '350px' },
					                  { "mDataProp":"descompteImport", "bSortable": false, sWidth: '40px' },
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
					"sScrollY": "100",		    
					"sScrollX": "152",	
				    "bScrollCollapse": true,
		    		"bProcessing": false,
		    		"bServerSide": true,
		    		"sAjaxSource": '/onlineBot/admin/ajaxTablePromos.action',
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
			            		//("#tbl_restaurants_paginate").hide();
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
		$("#infopromonew").hide();
		$("#errorsajax").hide();

} );















