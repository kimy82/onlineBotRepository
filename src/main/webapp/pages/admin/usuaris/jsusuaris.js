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


//Borra  Restaurant
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
				  url: '/'+context+'/admin/ajaxDeleteUserAction.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
					  if(json!=null && json.error!=null){
		   				$("#errorsajaxlabel").text(json.error);
		   				$("#errorsajax").show();
		   			}else{
							 alert(initTableParams.txtborrat);
							 reloadTableUsers();							
		   			}				
				  },
				  error: function(e){   $("#errorsajaxlabel").text(txterrorAjax);
										$("#errorsajax").show();  		
				  					}
				});
	  }
}
function linkUserToPromo(){
	var username = $("#useremail").text();
	var promoId = $("#promosAssociades").val();
	var data ="username="+username+"&idPromocio="+promoId;
	$.ajax({
		  type: "POST",
		  url: '/'+context+'/admin/linkUserToPromo.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  	if(json!=null && json.error!=null){	       				
			  		   $("#errorsajaxlabel").text(json.error);
					   $("#errorsajax").show();
     			}else{     				
     						       			
     			}	  			  		  			  		  			  	
		  },
		  error: function(e){  $("#errorsajaxlabel").text(txterrorAjax);
		   					   $("#errorsajax").show();
		  					}
		});
}


$("#user_dialog").dialog({ 
	   autoOpen: false,
	   height: 400,
	   width: 400,
	   modal: true,
	   close: function(event, ui) { 			   
		   $("#user_dialog").dialog("close"); 			  
		}
});

function infoUser(id){
	data ="id="+id;
	$.ajax({
		  type: "POST",
		  url: '/'+context+'/admin/ajaxInfoUserAction.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json!=null && json.error!=null){
   				$("#errorsajaxlabel").text(json.error);
   				$("#errorsajax").show();
	   			}else{
	
	   				if(json.nom!=null){
	   					$("#usernom").text(json.nom);
	   				}
	   				if(json.username!=null){
	   					$("#useremail").text(json.username);
	   				}
	   				
	   				if(json.address!=null){
	   					$("#useraddress").text(json.address);
	   				}
	   				
	   				if(json.telNumber!=null){
	   					$("#usertel").text(json.telNumber);
	   				}
	   				
	   				if(json.indicacions!=null){
	   					$("#userind").text(json.indicacions);
	   				}
	   				
	   				if(json.numComandesRealitzades!=null){
	   					$("#nComandesRealitzades").text(json.numComandesRealitzades);
	   				}
	   				if(json.numComandesAmbTargeta!=null){
	   					$("#nComandesAmbTargeta").text(json.numComandesAmbTargeta);
	   				}
	   				
	   				if(json.numComandesSenseTargeta!=null){
	   					$("#nComandesSenseTargeta").text(json.numComandesSenseTargeta);
	   				}
	   				
	   				$("#promosAssociades").empty();
	   				$("#promosAssociades").append("<option value=''>" "</option>");
	   				$.each(json.promos,function(index, value){
	   					
       					$("#promosAssociades").append("<option value='"+value.id+"'>"+value.descripcio+"</option>");
       				});
	   				
	   				$("#user_dialog").dialog("open"); 
	   			}				
		  },
		  error: function(e){   $("#errorsajaxlabel").text(txterrorAjax);
								$("#errorsajax").show();  		
		  					}
		});
}

function reloadTableUsers(){
	oTableUsuaris.fnDeleteRow( 0 );
}

var  oTableUsuaris=null;

//ocultem divs
$("#errorsajax").hide();

$(document).ready(function() {

	
			
		//taula dels usuaris
	oTableUsuaris =$("#tbl_usuaris").dataTable( {
					"iDisplayLength": 20,
					 "aoColumns" : [
					                  { "mDataProp":"username","bSortable": false, sWidth: '130px' },
					                  { "mDataProp":"enabled", "bSortable": false, sWidth: '120px' },
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
			                            var oSettings = oTableUsuaris.fnSettings();
			                            oSettings._iDisplayLength=100;
			                            oTableUsuaris.fnDraw();
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
		    		"sAjaxSource": '/'+context+'/admin/ajaxTableUsuarisAction.action',
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
				


} );
