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
}

function resetForm(){
		$("#nompromo").val("");
		$("#nompromoES").val("");
		$("#tipuDescompte").val("");
		$("#descompteImport").val("");
		$("#importAPartirDe").val("");		
		$("#numBegudes").val("");
 		$("#id_promo").val("");
 		$("#hora").attr('checked',false);		
}

function openDivTipuPromo(id){
	
	$("#infopromonew").show('slow');
}

function reloadTablePromos(){
	oTablePromos.fnDeleteRow( 0 );
}
function goToPromocio(id){
	data ="idPromocio="+id;
	$.ajax({
		  type: "POST",
		  url: '/'+context+'/admin/ajaxLoadPromoAssAction.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json==null || json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else{
					 		$("#id_promo").val(json.id);
					 		$("#nompromo").val(json.nom);
					 		$("#nompromoES").val(json.nomES);
					 		$("#tipuDescompte").val(json.tipuDescompte);
					 		$("#numBegudes").val(json.numBegudes);					 		
					 		$("#tipusBeguda option[value='"+json.tipusBeguda+"']").attr("selected","selected");  					 		
					 		$("#descompteImport").val(json.descompteImport);
					 		$("#importAPartirDe").val(json.importAPartirDe);
					 		
					 		if(json.hora==true){
					 			$("#hora").attr('checked','true');
					 		}
					 		$("#infopromonew").show('slow');		 						 					 										 					 						 					 						  
     			}				
		  },
		  error: function(e){   $("#errorsajaxlabel").text(txterrorAjax);
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
				  url: '/'+context+'/admin/ajaxDeletePromocioAss.action',
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
				  error: function(e){   $("#errorsajaxlabel").text(txterrorAjax);
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
		    		"sAjaxSource": '/'+context+'/admin/ajaxTablePromosAss.action',
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
		            		$("#errorsajaxlabel").text(txterrorAjax);
		            		$("#errorsajax").show();            	
		            	}
		        	} );
		    	}
			} );		
				
		//ocultem divs
		$("#infopromonew").hide();
		$("#errorsajax").hide();

} );
