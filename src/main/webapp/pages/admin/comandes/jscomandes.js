///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtborrat,txtenviat){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txtborrat=txtborrat;
		this.txtenviat = txtenviat;	
}

function sendTo(id){
	
	var data ="idComanda="+id;
  	$.ajax({
  		  type: "POST",
  		  url: '/'+context+'/admin/ajaxSendComanda.action',
  		  dataType: 'json',
  		  data: data,
  		  success: function(json){	
  			  if(json!=null && json.error!=null){
  				errorOnline.error("Error in AJAX: "+json.error);	
       		  }else{       				       				  
       				alertOnline.alertes(initParams.txtenviat);	        			       			
       		  }				
  		  },
  		  error: function(e){   errorOnline.error("Error in AJAX");	
  		  					}
  		});	
	reloadTableComandes();
}

function deleteTo(id){
	
	
	var data ="idComanda="+id;
  	$.ajax({
  		  type: "POST",
  		  url: '/'+context+'/admin/ajaxDeleteComanda.action',
  		  dataType: 'json',
  		  data: data,
  		  success: function(json){	
  			  if(json!=null && json.error!=null){
  				errorOnline.error("Error in AJAX: "+json.error);	
       		  }else{       			    				  
       			alertOnline.alertes(initParams.txtborrat);	        			
       		  }				
  		  },
  		  error: function(e){   errorOnline.error("Error in AJAX");	
  		  					}
  		});	
	reloadTableComandes();
}

function reloadTableComandes(){
	oTableComandes.fnDeleteRow( 0 );
}

var  oTableComandes=null;

$(document).ready(function() {

	
			
		
	oTableComandes =$("#tbl_comandes").dataTable( {
					"iDisplayLength": 12,
					 "aoColumns" : [
					                  { "mDataProp":"nom","bSortable": false, sWidth: '150px' },
					                  { "mDataProp":"telefon", "bSortable": false, sWidth: '350px' },
					                  { "mDataProp":"address", "bSortable": false, sWidth: '40px' },
					                  { "mDataProp":"hora", "bSortable": false, sWidth: '40px' },
					                  { "mDataProp":"preu", "bSortable": false, sWidth: '40px' },
					                  { "mDataProp":"accioSend", "bSortable": false, sWidth: '40px' },
					                  { "mDataProp":"accioBorrar", "bSortable": false, sWidth: '40px' }
					            ],
					"sPaginationType": "full_numbers",
					"sDom": 'T<"clear">lfrtip',
			    	"oTableTools": {
			    			"sSwfPath": "/"+context+"/swf/copy_csv_xls_pdf.swf",
			    			"aButtons": [
			    				"copy",			    				
			    				{
			    					"sExtends":    "collection",
			    					"sButtonText": "Save",
			    					"aButtons":    [ "csv", "xls", "pdf" ]
			    				},{
			                        "sExtends":    "text",
			                        "sButtonText": "Show All",
			                        "fnClick": function ( nButton, oConfig, oFlash ) {			                            
			                            var oSettings = oTableComandes.fnSettings();
			                            oSettings._iDisplayLength=100;
			                            oTableComandes.fnDraw();
			                        }
			                    }
			    			]			        	
			        },
					"oLanguage": {
						  "sProcessing": "<img src='/"+context+"/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
					      "oPaginate": {
					        "sFirst": "<img src='/"+context+"/images/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
					        "sLast": initTableParams.txtlast+"&nbsp;<img src='/"+context+"/images/icono-paginador-fin.gif' style='vertical-align:middle'>",
					        "sNext": initTableParams.txtnext+"&nbsp;<img src='/"+context+"/images/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
					        "sPrevious": "<img src='/"+context+"/images/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious
					      }
					    },
					"sScrollY": "100%",		    
					"sScrollX": "700",	
				    "bScrollCollapse": true,
		    		"bProcessing": false,
		    		"bServerSide": true,
		    		"sAjaxSource": '/'+context+'/admin/ajaxTablePrimeresComandesAction.action',
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
		$("#inforestaurant").hide();
		$("#errorsajax").hide();

} );