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

function opendivNewPromo(){
	resetForm();
	$("#infopromonew").show('slow');
	$("#pnc_div").hide();
	$("#apd_div").hide();
}

function resetForm(){
		$("#nompromo_apd").val("");
		$("#nompromoES_apd").val("");
		$("#tipuDescompte_apd").val("");
		$("#descompteImport_apd").val("");
		$("#importAPartirDe").val("");
		$("#dia").val("");
		$("#numBegudes_apd").val("");
		$("#numBegudes_pnc").val("");
		$("#nompromo_pnc").val("");
		$("#nompromoES_pnc").val("");
 		$("#tipuDescompte_pnc").val("");
 		$("#descompteImport_pnc").val("");
 		$("#numComandes").val("");
 		$("#temps").val("");
 		$("#id_apd").val("");
 		$("#id_pnc").val("");
 		$("#numUses_apd").val("");
 		$("#numUses_pnc").val("");
 		$("#adp_hora").attr('checked',false);		
 		$("#pnc_hora").attr('checked',false);
 		
 		$("#pnc_dilluns").attr('checked',false);
 		$("#pnc_dimarts").attr('checked',false);
 		$("#pnc_dimecres").attr('checked',false);
 		$("#pnc_dijous").attr('checked',false);
 		$("#pnc_divendres").attr('checked',false);
 		$("#pnc_dissabte").attr('checked',false);
 		$("#pnc_diumenge").attr('checked',false);
 		
 		$("#adp_dilluns").attr('checked',false);
 		$("#adp_dimarts").attr('checked',false);
 		$("#adp_dimecres").attr('checked',false);
 		$("#adp_dijous").attr('checked',false);
 		$("#adp_divendres").attr('checked',false);
 		$("#adp_dissabte").attr('checked',false);
 		$("#adp_diumenge").attr('checked',false);
 		
 		$("#adp_visibility").attr('checked',true);
 		$("#pnc_visibility").attr('checked',true);
}

function openDivTipuPromo(id){
	
	$("#infopromonew").show('slow');
	$("#pnc_div").hide();
	$("#apd_div").hide();
	
	if(id=='pnc'){
		$("#pnc_div").show('slow');		
	}else{
		$("#apd_div").show('slow');		
	}
}

function reloadTablePromos(){
	oTablePromos.fnDeleteRow( 0 );
}
function goToPromocio(id){
	data ="idPromocio="+id;
	$.ajax({
		  type: "POST",
		  url: '/'+context+'/admin/ajaxLoadPromoAction.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json==null || json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else{
					 	if(json.tipus=='apd'){
					 		$("#id_apd").val(json.id);
					 		$("#nompromo_apd").html(json.nom);
					 		$("#nompromoES_apd").html(json.nomES);
					 		$("#tipuDescompte_apd").val(json.tipuDescompte);
					 		$("#numBegudes_apd").val(json.numBegudes);					 		
					 		$("#tipusBeguda_apd option[value='"+json.tipusBeguda+"']").attr("selected","selected");  					 		
					 		$("#descompteImport_apd").val(json.descompteImport);
					 		$("#importAPartirDe").val(json.importAPartirDe);
					 		$("#numUses_apd").val(json.numUses);
					 		if(json.hora==true){
					 			$("#apd_hora").attr('checked','true');
					 		}
					 		$("#dia").val(json.diaString);
					 		
					 		if(json.dilluns==true){
					 			$("#adp_dilluns").attr('checked','true');
					 		}
					 		if(json.dimarts==true){
					 			$("#adp_dimarts").attr('checked','true');
					 		}
					 		if(json.dimecres==true){
					 			$("#adp_dimecres").attr('checked','true');
					 		}
					 		if(json.dijous==true){
					 			$("#adp_dijous").attr('checked','true');
					 		}
					 		if(json.divendres==true){
					 			$("#adp_divendres").attr('checked','true');
					 		}
					 		if(json.dissabte==true){
					 			$("#adp_dissabte").attr('checked','true');
					 		}
					 		if(json.diumenge==true){
					 			$("#adp_diumenge").attr('checked','true');
					 		}
					 		if(json.visibility==true){
					 			$("#adp_visibility").attr('checked','true');
					 		}
					 		
					 		$("#infopromonew").show('slow');
					 		$("#apd").click();
					 	}
					 	if(json.tipus=='pnc'){
					 		$("#id_pnc").val("");
					 		$("#nompromo_pnc").html(json.nom);
					 		$("#nompromoES_pnc").html(json.nomES);
					 		$("#tipuDescompte_pnc").val(json.tipuDescompte);
					 		$("#descompteImport_pnc").val(json.descompteImport);
					 		$("#tipusBeguda_pnc option[value='"+json.tipusBeguda+"']").attr("selected","selected"); 
					 		$("#numBegudes_pnc").val(json.numBegudes);
					 		$("#numComandes").val(json.numComandes);
					 		$("#numUses_pnc").val(json.numUses);
					 		if(json.hora==true){
					 			$("#pnc_hora").attr('checked','true');
					 		}
					 		$("#temps").val(json.temps);
					 		
					 		if(json.dilluns==true){
					 			$("#pnc_dilluns").attr('checked','true');
					 		}
					 		if(json.dimarts==true){
					 			$("#pnc_dimarts").attr('checked','true');
					 		}
					 		if(json.dimecres==true){
					 			$("#pnc_dimecres").attr('checked','true');
					 		}
					 		if(json.dijous==true){
					 			$("#pnc_dijous").attr('checked','true');
					 		}
					 		if(json.divendres==true){
					 			$("#pnc_divendres").attr('checked','true');
					 		}
					 		if(json.dissabte==true){
					 			$("#pnc_dissabte").attr('checked','true');
					 		}
					 		if(json.diumenge==true){
					 			$("#pnc_diumenge").attr('checked','true');
					 		}
					 		if(json.visibility==true){
					 			$("#pnc_visibility").attr('checked','true');
					 		}
					 		
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
				  url: '/'+context+'/admin/ajaxDeletePromocio.action',
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
					                  { "mDataProp":"fentrada","bSortable": false, sWidth: '50px' },
					                  { "mDataProp":"tipuDescompte", "bSortable": false, sWidth: '350px' },
					                  { "mDataProp":"descompteImport", "bSortable": false, sWidth: '40px' },
					                  { "mDataProp":"numBegudes", "bSortable": false, sWidth: '40px' },
					                  { "mDataProp":"tipusBeguda", "bSortable": false, sWidth: '40px' },
					                  { "mDataProp":"code", "bSortable": false, sWidth: '40px' },
					                  { "mDataProp":"accio", "bSortable": false, sWidth: '40px' }
					            ],
					"sPaginationType": "full_numbers",
					"oLanguage": {
						  "sProcessing": "<img src='/"+context+"/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
					      "oPaginate": {
					        "sFirst": "<img src='/"+context+"/images/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
					        "sLast": initTableParams.txtlast+"&nbsp;<img src='/"+context+"/images/icono-paginador-fin.gif' style='vertical-align:middle'>",
					        "sNext": initTableParams.txtnext+"&nbsp;<img src='/"+context+"/images/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
					        "sPrevious": "<img src='/"+context+"/images/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious
					      }
					    },
					"sScrollY": "900",		    
					"sScrollX": "700",	
				    "bScrollCollapse": true,
		    		"bProcessing": false,
		    		"bServerSide": true,
		    		"sAjaxSource": '/'+context+'/admin/ajaxTablePromos.action',
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
