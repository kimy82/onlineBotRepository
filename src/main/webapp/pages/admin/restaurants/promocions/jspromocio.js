///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtborrat,txtconfirmborrapromo){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txtborrat=txtborrat;	
		this.txtconfirmborrapromo= txtconfirmborrapromo;
}

function reloadTablePromos(){
	oTablePromos.fnDeleteRow( 0 );
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
		
		//per agafar click en qualsevol part de la row
		$("#tbl_promos tbody").click(function(event) {
			$(oTableRest.fnSettings().aoData).each(function (){	
				$(this.nTr).addClass('select_ro');
			});
			$(event.target.parentNode).removeClass('odd');
			$(event.target.parentNode).removeClass('even');
			
			var id=event.target.parentNode.firstChild.id;
			showDivRestaurant(id);
		
		});
		
		
		//ocultem divs
		$("#infopromo").hide();
		$("#infopromonew").hide();
		$("#errorsajax").hide();

} );















