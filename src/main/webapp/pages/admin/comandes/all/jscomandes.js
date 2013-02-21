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

function ismaxlength(obj,mlength){
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength);
}

function reloadTableComandes(){
	oTableAllComandes.fnDeleteRow( 0 );
}

var  oTableAllComandes=null;




$(document).ready(function() {

	
			
		
	oTableAllComandes =$("#tbl_all_comandes").dataTable( {
					"iDisplayLength": 12,
					 "aoColumns" : [
					                  { "mDataProp":"nom", sWidth: '150px' },
					                  { "mDataProp":"telefon",  sWidth: '350px' },
					                  { "mDataProp":"address", "bSortable": false, sWidth: '40px' },
					                  { "mDataProp":"hora",  sWidth: '40px' },
					                  { "mDataProp":"preu",  sWidth: '40px' },
					                  { "mDataProp":"restaurant",  sWidth: '40px' },
					                  { "mDataProp":"metodePagament",  sWidth: '40px' }					                  
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
		    		"sAjaxSource": '/'+context+'/admin/ajaxTableAllComandesAction.action',
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
		$("#inforestaurant").hide();
		$("#errorsajax").hide();

} );














