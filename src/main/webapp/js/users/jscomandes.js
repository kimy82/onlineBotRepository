///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtborrat){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txtborrat=txtborrat;		
}

var initParams=null ;
function InitParams(txtusernameempty, txtpasswordempty,txtpasswordnotequal,txttelempty,txtaddressempty,txterrordouble,txterrornumber, txtconfirm, txtproductes,txtproducte,txtconfirmcontinuar,txtavisrestauranttancat){		

	this.txtusernameempty= txtusernameempty;
	this.txtpasswordempty= txtpasswordempty;
	this.txtpasswordnotequal=txtpasswordnotequal;
	this.txttelempty = txttelempty;
	this.txtaddressempty = txtaddressempty;
	this.txterrordouble=txterrordouble;
	this.txterrornumber=txterrornumber;
	this.txtconfirm = txtconfirm;
	this.txtproductes = txtproductes;
	this.txtproducte = txtproducte;
	this.txtconfirmcontinuar=txtconfirmcontinuar;
	this.txtavisrestauranttancat=txtavisrestauranttancat;
}

function changeClass(id){
	if($("#"+id).hasClass("hiddenIn")){
		$("#"+id).removeClass("hiddenIn");
	}else{
		$("#"+id).addClass("hiddenIn");
	}
}

function goToVotarPlat(idPlat){
	
	$("#votaPlats_dialog").load("../foro/foro.action?idPlat="+idPlat);	
	$("#votaPlats_dialog").dialog({ 
		   autoOpen: false,
		   height: 500,
		   width: 750,
		   modal: true,
		   close: function(event, ui) { 			   
			   $("#votaPlats_dialog").dialog("close"); 			  
			}
	});
    $("#votaPlats_dialog").dialog("open");  	
}

$("#votaPlats_dialog").dialog({ 
	   autoOpen: false,
	   height: 700,
	   width: 750,
	   modal: true,
	   close: function(event, ui) { 			   
		   $("#votaPlats_dialog").dialog("close"); 			  
		}
	});

$("#dialog_details").dialog( { autoOpen: false,
	  modal: true,
	  position: 'center',
	  draggable: true,
	  height: 530,
	  width: 700,		
	  open: function(event, ui) { 		 

	 }
});

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
		alertOnline.alertes(initParams.txterrordouble);

	}
}  

function onlyEntero(value,id){
	  if(value =='' || /^[0-9]*$/.test(value)){
		$('#'+id).css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#'+id).css('border', 'solid 1px red');
		alertOnline.alertes(initParams.txterrornumber);

	}
}

function ismaxlength(obj,mlength){
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength);
}

function reloadTableComandes(){
	oTablecomandes.fnDeleteRow( 0 );
}

function fillAddress(){
	
	var self = $("#saveUserDetails")[0];
	
	if(self.password.value==''|| self.passwordRetyped.value=='' ){
		self.password.value="";
		
	}else{	
		if(self.password.value!=self.passwordRetyped.value){
			$("#confirmPassword").css('border', 'solid 1px red');
			$("#password").css('border', 'solid 1px red');
			alertOnline.alertes(initParams.txtpasswordnotequal);	
			return false;
		}else{
			$("#confirmPassword").css('border', 'solid 1px rgb(135,155,179)');
			$("#password").css('border', 'solid 1px rgb(135,155,179)');			
		}
	}
	if(self.telNumber.value==''){
		
		$("#telefon").css('border', 'solid 1px red');
		alertOnline.alertes(initParams.txttelempty);
		return false;
		
	}else{
		$("#telefon").css('border', 'solid 1px rgb(135,155,179)');
	}
	
	if(self.comandaddress.value==''){
		
		$("#comandaddress").css('border', 'solid 1px red');
		alertOnline.alertes(initParams.txtaddressempty);		
		return false;
		
	}else{
		$("#comandaddress").css('border', 'solid 1px rgb(135,155,179)');
	}
	
	if($("#altres").val()!=''){
		$("#indicacions").val($("#altres").val());
	}
	
	self.submit();
	
	$("#dialog_details").dialog("close");
}

function repeatComanda(id){
	
	window.location.href="/"+context+"/user/repeatComanda.action?idComanda="+id;
}

function openDialog(id){

	if(id=='infoUser'){
		$("#dialog_details").dialog("open");	
	}	
}

function checkPassword(){
	var password1 = document.getElementById("password").value;
	var password2 = document.getElementById("passwordRetyped").value;
	if(password1 != password2){
		$('#passwordRetyped').css('border', 'solid 1px red');
		alertOnline.alertes(initParams.txtpasswordnotequal);	
		return;
	}
}

function goToRestaurantMenu(id){
	var comanda = window.localStorage.getItem("comanda");
	var data = window.localStorage.getItem("comanda.data");
	var comandaConfirm = window.localStorage.getItem("comanda.confirm");
	
	if(comandaConfirm !='undefined' && comandaConfirm!=null){
		var currentDay = new Date();
		if((currentDay.getTime()-comandaConfirm)>60*confirmTime){
			window.localStorage.removeItem("comanda.confirm");
		}
	}
	
	comandaConfirm = window.localStorage.getItem("comanda.confirm");
	
	if(comanda != 'undefined' && comanda != null && comandaConfirm == null){
		var day = new Date();
		window.localStorage.setItem("comanda.confirm",day.getTime());
		acceptComandaDialog();
	}else{
		window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+id+"&data="+data;
	}
}

var actionCloseConfirm = function(){
	var data = window.localStorage.getItem("comanda.data");
	var idRestaurant = window.localStorage.getItem("comanda.restaurant");
	if(idRestaurant=='undefined' || idRestaurant==null) return;
	window.localStorage.clear();	
	window.localStorage.setItem("comanda.data",data);
	window.localStorage.setItem("comanda.restaurant",idRestaurant);
	window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&data="+data;
}

function acceptComandaDialog(){
	confirmOnline.closeSetFunc(actionCloseConfirm);
	confirmOnline.confirm(initParams.txtconfirmcontinuar,confirmComanda);
}

var confirmComanda = function (){
									var comanda = window.localStorage.getItem("comanda");
									var idRestaurant = window.localStorage.getItem("comanda.restaurant");
									if(idRestaurant=='undefined' || idRestaurant==null) return;
									if($("#list_rest_"+idRestaurant).hasClass("tancat")){
										alertOnline.alertes(initParams.txtavisrestauranttancat);	
									}
									if(comanda != 'undefined' && comanda != null){
										var data = window.localStorage.getItem("comanda.data");
										window.location.href = "/"+context+"/comanda/goToPas1Action.action?idComanda="+comanda+"&data="+data;
										//window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&idComanda="+comanda+"&data="+data;
									}
								}

var  oTablecomandes=null;


$(document).ready(function() {
				
		//taula de les comandes d'un user
	oTablecomandes =$("#tbl_comandes_user").dataTable( {
					"iDisplayLength": 6,
					 "aoColumns" : [
					                  { "mDataProp":"dia","bSortable": false, sWidth: '90px' },
					                  { "mDataProp":"platsString", "bSortable": false, sWidth: '430px' },
					                  { "mDataProp":"links", "bSortable": false, sWidth: '108px' },
					                  { "mDataProp":"preu", "bSortable": false, sWidth: '61px' },
					          
					                  { "mDataProp":"accio", "bSortable": false, sWidth: '117px' }
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
					"sScrollY": "100%",		    
					"sScrollX": "957",	
				    "bScrollCollapse": true,
		    		"bProcessing": false,
		    		"bServerSide": true,
		    		"sAjaxSource": '/'+context+'/user/ajaxTableComandesUser.action',
		    		"fnServerData": function( sUrl, aoData, fnCallback) {      			    			    			
		     		$.ajax( {
		            	"url": sUrl,
		            	"data": aoData,              
		            	"dataType": "json",
		            	"cache": false,
		           		"success":function(json){  
		           			if(json.error!=null){
		           				errorOnline.error(json.error);	
		           			}else{
			            		fnCallback(json);			            		
		           			}            	
		            	},
		            	"error":function(e){ 
		            		 errorOnline.error("Error in AJAX");	         	
		            	}
		        	} );
		    	}
			} );
		
	
	var comanda = window.localStorage.getItem("comanda");
	
	if(comanda != 'undefined' && comanda != null){
		$("#numComanda").text(comanda);
		
		
		var numplats = window.localStorage.getItem("comanda.numplats");
		if(numplats == 'undefined' || numplats == null){
			numplats=0;
			window.localStorage.setItem("comanda.numplats","0");
		}
		
		if (numplats != 'undefined' && numplats != null) {
			$("#numplats").text(numplats);
		}

		var numbegudes = window.localStorage.getItem("comanda.numbegudes");
		if(numbegudes == 'undefined' || numbegudes == null){
			numbegudes=0;
			window.localStorage.setItem("comanda.numbegudes","0");
		}
	
		
		var nProductes = parseInt(numplats)+parseInt(numbegudes);
		if(nProductes==1){
			$("#numProduct").text(initParams.txtconfirm+" "+nProductes+" "+initParams.txtproducte);
		}else if(nProductes>1){
			$("#numProduct").text(initParams.txtconfirm+" "+nProductes+" "+initParams.txtproductes);
		}		
		
		if(isNaN(nProductes)){
			window.localStorage.clear();
			$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
		}
		
	}else{
		$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
	}
});
