///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtborrat, txtconfirmborrauser){		
	this.txtlast=txtlast;
	this.txtnext=txtnext;
	this.txtprevious=txtprevious;
	this.txtfirst=txtfirst;
	this.txtloading=txtloading;
	this.txtborrat=txtborrat;
	this.txtconfirmborrauser = txtconfirmborrauser;
}


function deleteUser(id){
	 var where_to= confirm(initTableParams.txtconfirmborrauser);
	  if (where_to== false)
	  {
		    return;
	  }
	  else {			  				
			
			data ="id="+id;
			$.ajax({
				  type: "POST",
				  url: '/'+context+'/admin/ajaxDeleteLetterUserAction.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
					  if(json!=null && json.error!=null){
		   				$("#errorsajaxlabel").text(json.error);
		   				$("#errorsajax").show();
		   			}else{
							 alert(initTableParams.txtborrat);
							 reloadTableLetterUsers();							
		   			}				
				  },
				  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
										$("#errorsajax").show();  		
				  					}
				});
	  }
}

function reloadTableLetterUsers(){
	oTableLetterUsuaris.fnDeleteRow( 0 );
}

var  oTableLetterUsuaris=null;

//ocultem divs
$("#errorsajax").hide();

$(document).ready(function() {
				
	//taula dels usuaris
	oTableLetterUsuaris =$("#tbl_letterusuaris").dataTable( {					
					"iDisplayLength": 3,
					 "aoColumns" : [
					                  { "mDataProp":"email","bSortable": false, sWidth: '130px' },					                 
					                  { "mDataProp":"accio", "bSortable": false, sWidth: '120px' }					                  
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
			                            var oSettings = oTableLetterUsuaris.fnSettings();
			                            oSettings._iDisplayLength=100;
			                            oTableLetterUsuaris.fnDraw();
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
		    		"sAjaxSource": '/'+context+'/admin/ajaxTableLetterUsuarisAction.action',
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
} );
