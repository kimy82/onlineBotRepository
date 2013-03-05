///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtborrat,txtconfirmborrabeguda,txterrordouble,txterrornumber){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txtborrat=txtborrat;	
		this.txtconfirmborrabeguga= txtconfirmborrabeguda;
		this.txterrordouble=txterrordouble;
		this.txterrornumber=txterrornumber;
}


function opendivNewBeguda(){
	resetForm();
	$("#infobegudanew").show('slow');
}

function resetForm(){
		$("#nomBeguda").val("");
		$("#nomBegudaES").val("");
		$("#tipusBeguda option:first").attr('selected','selected');		
		$("#importBeguda").val("");
		$("#descripcioBeguda").val("");
		$("#descripcioBegudaES").val("");
		
		$("#id").val("");		
}



function reloadTableBegudes(){
	oTablebegudes.fnDeleteRow( 0 );
}

function showDivBeguda(id){
	
	data ="idBeguda="+id;
	$.ajax({
		  type: "POST",
		  url: '/'+context+'/admin/ajaxLoadBegudaAction.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json==null || json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else{
					 	
					 		$("#id").val(json.id);
					 		$("#nomBeguda").val(json.nom);
					 		$("#nomBegudaES").val(json.nomES);
					 		$("#tipusBeguda").val(json.tipus);					 		
					 		$("#importBeguda").val(json.preu);
					 		$("#descripcioBeguda").val(json.descripcio);
					 		$("#descripcioBegudaES").val(json.descripcioES);
					 		$("#infobegudanew").show('slow');					 							 			 					 										 					 						 					 						  
     			}				
		  },
		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
  								$("#errorsajax").show();  		
		  					}
		});	
	
}

function deleteBeguda(id){
	 var where_to= confirm(initTableParams.txtconfirmborrabeguda);
	  if (where_to== false)
	  {
		    return;
	  }
	  else {
		
			data ="idBeguda="+id;
			$.ajax({
				  type: "POST",
				  url: '/'+context+'/admin/ajaxDeleteBeguda.action',
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
						  "sProcessing": "<img src='/"+context+"/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
					      "oPaginate": {
					        "sFirst": "<img src='/"+context+"/images/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
					        "sLast": initTableParams.txtlast+"&nbsp;<img src='/"+context+"/images/icono-paginador-fin.gif' style='vertical-align:middle'>",
					        "sNext": initTableParams.txtnext+"&nbsp;<img src='/"+context+"/images/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
					        "sPrevious": "<img src='/"+context+"/images/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious
					      }
					    },
					"sScrollY": "300",		    
					"sScrollX": "752",	
				    "bScrollCollapse": true,
		    		"bProcessing": false,
		    		"bServerSide": true,
		    		"sAjaxSource": '/'+context+'/admin/ajaxTableBegudes.action',
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
