///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtconfirm,txtproductes,txtproducte, txtconfirmcontinuar,txtavisrestauranttancat){		
	this.txtconfirm = txtconfirm;
	this.txtproductes = txtproductes;
	this.txtproducte = txtproducte;
	this.txtconfirmcontinuar= txtconfirmcontinuar;
	this.txtavisrestauranttancat = txtavisrestauranttancat;
}

//events 
$(function() {

	$(".selector_jq").dblclick(function() {

		var dragBeguda = $(this).clone();
		dragBeguda.appendTo("#droppable");

		dragBeguda.animate({
			width : "90%",
			opacity : 0.4,
			marginLeft : "0.6in",
			fontSize : "3em",
			borderWidth : "10px",
			left : "+=250px"
		}, 1800, function() {
			var item_id = $(this).attr("id");
			var rawPlat = item_id.split("_");
			platOBJ.savePlatToComanda(rawPlat[1]);
			$(this).css("visiblity", "hidden");
			$(this).css("display", "none");
		});

	});
	$(".selectorBeg").dblclick(function() {

		var dragBeguda = $(this).clone();
		dragBeguda.appendTo("#droppable");

		dragBeguda.animate({
			width : "90%",
			opacity : 0.4,
			marginLeft : "0.6in",
			fontSize : "3em",
			borderWidth : "10px",
			left : "+=250px"
		}, 1800, function() {
			var item_id = $(this).attr("id");
			var rawBeguda = item_id.split("_");
			begudaOBJ.saveBegudaToComanda(rawBeguda[1]);
			$(dragBeguda).css("visiblity", "hidden");
			$(dragBeguda).css("display", "none");
		});
	});
	$(".selector_jq").draggable({
		helper : 'clone',
		start : function(event, ui) {
			var id = $(this).attr("id");
		},
		stop : function(event, ui) {

		}
	});
	$(".selectorBeg").draggable({
		helper : 'clone',
		start : function(event, ui) {
			var id = $(this).attr("id");
		},
		stop : function(event, ui) {

		}
	});
	
	$("#droppable").droppable({
		drop : function(event, ui) {

			var item_id = ui.draggable.attr("id");
			var classes = ui.draggable.attr("class");
			
			if(classes.indexOf("selectorBeg") !== -1){
				var rawBeguda = item_id.split("_");
				begudaOBJ.saveBegudaToComanda(rawBeguda[1]);
			}else{
				var rawPlat = item_id.split("_");
				var data = window.localStorage.getItem("comanda.data");			
				platOBJ.savePlatToComanda(rawPlat[1]);
			}

		}
	});
});
	
	//OBJ for drinks management 
	var begudaOBJ ={
	}
	begudaOBJ._self=null;


	begudaOBJ ={
		
		_init: function (){
			this._self =this;
		},
		saveBegudaToComanda: function(idBeguda){
			if(isNaN(idBeguda)){console.log("idBeguda is not a number"); return;}
			var idComanda = $("#numComanda").text();
			if(isNaN(idComanda)){console.log("idComanda is not a number"); return;}
			var data ="idBeguda="+idBeguda+"&idComanda="+idComanda+"&promo=false";
		  	$.ajax({
		  		  type: "POST",
		  		  url: '/'+context+'/comanda/ajaxLoadBeguda.action',
		  		  dataType: 'json',
		  		  data: data,
		  		  success: function(json){
		  			  try{
			  			  if(json!=null && json.error!=null){           				
			       				console.log(json.error);	
			       			}else{
			       				if(json!=null && json.alerta!=null){
			       					alertOnline.alertes(json.alerta);
			       					
			       				}else{
			       					var numBegudes=0;
			       					var numBegudesPromo=0;           					
			       					var preuBegudes = 0.0;
			       					var html="";
			       					var begudes= json.begudes;	       					
			       					$("#disp_beguda").html("");
			       					var lis="";
			       					$.each(begudes, function(index, value) { 
			       					 	
			       					 	if(value.promo==true || value.promo=='true'){
			       					 		numBegudesPromo=numBegudesPromo+value.numBegudes;
			       					 		
			       					 	}else{
			       					 		numBegudes= numBegudes+value.numBegudes;
			       					 		preuBegudes=  parseFloat(preuBegudes) + (parseFloat(value.beguda.preu)*value.numBegudes);	       					 		
			       							var li= value.numBegudes+" <span class='plats' id='span_b_"+value.beguda.id+"'>x</span> "+changeHTML(value.beguda.nom)+"&nbsp;<a href='#' onclick='eliminaBeguda("+value.beguda.id+")' ><img class='dele' src='/"+context+"/images/delete2.png'></a><br><br>";
			       							lis = lis+li;
			    							$("#disp_beguda").append(li);
			       						}	       				
			       						
			       					});
			       				
			       					window.localStorage.setItem("comanda.begudes.lis",lis);
			       					window.localStorage.setItem("comanda.promo.nBegudes.added",numBegudesPromo);
			       					window.localStorage.setItem("comanda.numbegudes",numBegudes);
			       					window.localStorage.setItem("comanda.beguda.preu",preuBegudes.toFixed(2));	       					
			       					$("#numbegudes").text(numBegudes);	       				
			       					var preuComanda = window.localStorage.getItem("comanda.preu");
			       					if(preuComanda!= 'undefined' && preuComanda != null){
			       						var preuFinal = parseFloat(preuComanda) + parseFloat(preuBegudes);
			       						$("#preu").text(parseFloat(preuFinal).toFixed(2));
			       					}else{
			       						window.localStorage.setItem("comanda",json.numComanda);
			       						window.localStorage.setItem("comanda.preu","0");
			       						$("#preu").text(parseFloat(preuBegudes).toFixed(2));
			       					}	       						       				
			       					
			       					$("#numComanda").text(json.numComanda);
			       				
			       				}
			       			}
		  			  }catch(error){
		  				  console.log(error);
		  			  }
		  		  },
		  		  error: function(e){  errorOnline.error(txterrorAjax);	
		  		  					}
		  		});	
		},
		eliminaBeguda: function(id){
			var idcomanda= window.localStorage.getItem("comanda");
			if(isNaN(id) || isNaN(idcomanda)){console.log("idcomanda or idbeguda not a number"); return;}
			var data ="idBeguda="+id+"&idComanda="+idcomanda;
		  	$.ajax({
		  		  type: "POST",
		  		  url: '/'+context+'/comanda/deleteBegudaFromCarrito.action',
		  		  dataType: 'json',
		  		  data: data,
		  		  success: function(json){	
		  			  if(json!=null && json.error!=null){
		  				console.log(json.error);	
		       		  }else{
		       			var begudesAnterior = window.localStorage.getItem("comanda.numbegudes");
		       			var numBegudes= parseInt(begudesAnterior)-parseInt(json.numBegudes);
		       			window.localStorage.setItem("comanda.numbegudes",numBegudes);
		       			$("#numbegudes").text(numBegudes);
		       			var preuBegudaComanda = window.localStorage.getItem("comanda.beguda.preu");
		       			var preuComanda = window.localStorage.getItem("comanda.preu");
		       			var preuBegFinal = parseFloat(parseFloat(preuBegudaComanda) - parseFloat(json.preuToRest)).toFixed(2);
		       			window.localStorage.setItem("comanda.beguda.preu",preuBegFinal);
		       			var preuFinal = parseFloat(parseFloat(preuBegFinal)+ parseFloat(preuComanda)).toFixed(2);
		       			if(isNaN(preuFinal)){
		       				$("#preu").text("0.0");
		       			}else{
		       				$("#preu").text(parseFloat(preuFinal).toFixed(2));
		       			}
		       			
		       			var lis= window.localStorage.getItem("comanda.begudes.lis");
		       			var lista="";
		       			var begudes = lis.split("<br><br>");
		       				$.each(begudes, function(index, value) { 		       					 	
		       						 if(value.indexOf("span_b_"+id)==-1 && value!='')
		       							 lista=lista+value+"<br><br>";		       										       						
		       				});

		       				window.localStorage.setItem("comanda.begudes.lis",lista);
		       				$("#disp_beguda").html(lista);
		       		  }			
		  		  },
		  		  error: function(e){   console.log("error: "+e+" ajax: "+txterrorAjax);	
		  		  					}
		  		});	
		},
		goToInfoBeguda: function(id){
			if(isNaN(id)){console.log("idBeguda is not a number"); return;}
			$("#infoBeguda_dialog").load("../foro/foroBeguda.action?idBeguda="+id);	
			$("#infoBeguda_dialog").dialog({ 
				   autoOpen: false,
				   height: 500,
				   width: 750,
				   modal: true,
				   close: function(event, ui) { 			   
					   $("#infoBeguda_dialog").dialog("close"); 			  
					}
			});
		    $("#infoBeguda_dialog").dialog("open");   
		    $("#infoBeguda_dialog").siblings('div.ui-dialog-titlebar').remove();  
		    $("#infoBeguda_dialog").removeClass("ui-dialog-content");
		    $("#infoBeguda_dialog").removeClass("ui-widget-content");
		   	$("#infoBeguda_dialog").addClass("ui-helper-clearfix");
		},
		closeInfoBeguda: function(){
			$("#infoBeguda_dialog").dialog("close");
		}	


	}
	
	//OBJ for disches management 
	var platOBJ ={
	}
	platOBJ._self=null;


	platOBJ ={
		
		_init: function (){
			this._self =this;
		},
		savePlatToComanda: function(idPlat){
			if(isNaN(idPlat)){console.log("idPlat is not a number"); return;}
			
			var data = "idPlat=" + idPlat + "&idComanda=" + $("#numComanda").text();
			
			$.ajax({
						type : "POST",
						url : '/'+context+'/comanda/ajaxLoadPlat.action',
						dataType : 'json',
						data : data,
						success : function(json) {
							if (json == null || json.error != null) {
								console.log(json.error);
							} else {
								if (json.alerta != null) {
									alertOnline.alertes(json.alerta);								
								} else {
									try{
										// Posem id en el local storage
										window.localStorage.setItem("comanda",
												json.numComanda);
										window.localStorage.setItem("comanda.preu",
												json.preu);
										window.localStorage.setItem("comanda.numplats",
												json.numPlats);
										platOBJ.saveLocalStorageNplatsOfsinglePlat(idPlat);
	
										$("#numComanda").text(json.numComanda);
										$("#numplats").text(json.numPlats);
										var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
										
										if(preuBegudes!= 'undefined' && preuBegudes != null){
											var preuFinal = parseFloat(json.preu) + parseFloat(preuBegudes);
											$("#preu").text(parseFloat(preuFinal).toFixed(2));
										}else{
											var preuFinal = parseFloat(json.preu).toFixed(2);
											$("#preu").text(preuFinal);
										}
										
										var plats= json.platsNames;	       					
				       					$("#disp_plate").html("");
				       					var lis= "";
				       					$.each(plats, function(index, value) { 		 
				       						
				       							var li= value.numPlats+" <span class='plats' id='span_p_"+value.idPlat+"'>x</span> "+changeHTML(value.nomPlat)+"&nbsp;<a href='#' onclick='platOBJ.eliminaPlat("+value.idPlat+")' ><img class='dele' src='/"+context+"/images/delete2.png'></a><br><br>";
				       							 lis=lis+li;		       					
				    							$("#disp_plate").append(li);		       						
				       					});
	
				       					window.localStorage.setItem("comanda.plats.lis",lis);
									}catch(error){
										console.log(error);
									}
										
								}
							}
						},
						error : function(e) {						
							console.log(txterrorAjax);
						}
					});
		},
		saveLocalStorageNplatsOfsinglePlat: function(idPlat){
			if(isNaN(idPlat)){console.log("idPlat is not a number"); return;}
			
			var nPl = window.localStorage.getItem("comanda.plat_" + idPlat);

			if ( nPl != null) {
				window.localStorage.setItem("comanda.plat_" + idPlat,parseInt(nPl) + 1);
			} else {
				window.localStorage.setItem("comanda.plat_" + idPlat, 1);
			}
		},
		eliminaPlat: function(id){
			if(isNaN(id)){console.log("idPlat is not a number"); return;}
			var idcomanda= window.localStorage.getItem("comanda");
			if(isNaN(idcomanda)){console.log("idcomanda is not a number"); return;}
			var data ="idPlat="+id+"&idComanda="+idcomanda;
		  	$.ajax({
		  		  type: "POST",
		  		  url: '/'+context+'/comanda/deletePlatFromCarrito.action',
		  		  dataType: 'json',
		  		  data: data,
		  		  success: function(json){	
		  			  if(json!=null && json.error!=null){
		  				console.log(json.error);	
		       		  }else{
		       			try{
			       			var platsAnterior = window.localStorage.getItem("comanda.numplats");
			       			var numPlats = parseInt(platsAnterior)-parseInt(json.numPlats)
			       			window.localStorage.setItem("comanda.numplats",numPlats);
			       			$("#numplats").text(numPlats);	       			
			       			window.localStorage.removeItem("comanda.plat_"+id);
			       			var preuComanda = window.localStorage.getItem("comanda.preu");
			       			var preuBegudaComanda = window.localStorage.getItem("comanda.beguda.preu");
			       			var preuPlatFinal = parseFloat(parseFloat(preuComanda) - parseFloat(json.preuToRest)).toFixed(2);
			       			window.localStorage.setItem("comanda.preu",preuPlatFinal);
			       			var preuFinal = parseFloat(parseFloat(preuPlatFinal)+ parseFloat(preuBegudaComanda)).toFixed(2);
			       			if(isNaN(preuFinal)){
			       				$("#preu").text("0.0");
			       			}else{
			       				$("#preu").text(parseFloat(preuFinal).toFixed(2));
			       			}
			       			var lis= window.localStorage.getItem("comanda.plats.lis");
			       			var lista="";
			       			var plats = lis.split("<br><br>");
			       				$.each(plats, function(index, value) { 		       					 	
			       						 if(value.indexOf("span_p_"+id)==-1 && value!='')
			       							 lista=lista+value+"<br><br>";		       										       						
			       				});
	
			       				window.localStorage.setItem("comanda.plats.lis",lista);
			       				$("#disp_plate").html(lista);
		       			}catch(error){
							console.log(error);
						}
		       		  }			
		  		  },
		  		  error: function(e){   errorOnline.error(txterrorAjax);	
		  		  					}
		  		});	
			
		},
		showImagePlat: function(id){
			if(isNaN(id)){console.log("idPlat is not a number"); return;}
			$("#imgBigId").attr("src","/"+context+"/comanda/ImageAction.action?imageId="+id);
			$("#imageBig").dialog({ 
				   autoOpen: false,
				   height: 500,
				   width: 650,
				   modal: true});
			 $("#imageBig").dialog("open");   
			 $("#imageBig").siblings('div.ui-dialog-titlebar').remove();  
			 $("#imageBig").removeClass("ui-dialog-content");
			 $("#imageBig").removeClass("ui-widget-content");
			 $("#imageBig").addClass("ui-helper-clearfix");
			 $('#imageBig').css('visibility', '');
		},
		goToInfoPlat: function(id){
			if(isNaN(id)){console.log("idPlat is not a number"); return;}
			$("#infoPlat_dialog").load("../foro/foro.action?idPlat="+id);	
			$("#infoPlat_dialog").dialog({ 
				   autoOpen: false,
				   height: 629,
				   width: 806,
				   position: "center",
				   modal: true,
				   close: function(event, ui) { 			   
					   $("#infoPlat_dialog").dialog("close"); 			   
					}
			});
		    $("#infoPlat_dialog").dialog("open");
		    $("#infoPlat_dialog").siblings('div.ui-dialog-titlebar').remove();  
		    $("#infoPlat_dialog").removeClass("ui-dialog-content");
			$("#infoPlat_dialog").removeClass("ui-widget-content");
		   	$("#move").addClass("ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix");
			$(".ui-widget-content").css("background-color", "transparent");
		   	$(".ui-widget-content").css("border", "0px");
		    $(":ui-dialog").dialog('option', 'position', 'center');
		},
		filterPlats: function(filter,id){
			$("#plat_select_1").removeClass("selec");
			$("#plat_select_2").removeClass("selec");
			$("#plat_select_3").removeClass("selec");
			$("#"+id).addClass("selec");
			window.localStorage.setItem("plats.order",filter);
			var data = window.localStorage.getItem("comanda.data");
			window.location.href = "/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&data="+data+"&order="+filter;	
		},
		closeInfoPlat: function(){
			$("#infoPlat_dialog").dialog("close");
		}	
	}

	

//OBJ for managment of menu restaurant 
var menuRestaurantAction ={
}
menuRestaurantAction._self=null;


menuRestaurantAction ={
	
	_init: function (){
		this._self =this;
	},
	goToRestaurantMenu: function(id){
		if(isNaN(id)){window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	}
		var comanda = window.localStorage.getItem("comanda");
		var data = window.localStorage.getItem("comanda.data");
		window.localStorage.removeItem("plats.order");
		
		var comandaConfirm = window.localStorage.getItem("comanda.confirm");
		var timeoutdata = window.localStorage.getItem("comanda.timeout.data");
		
		if(comandaConfirm!=null){
			var currentDay = new Date();
			if((currentDay.getTime()-comandaConfirm)>60*confirmTime){
				window.localStorage.removeItem("comanda.confirm");
			}
		}
		
		if( timeoutdata!=null){
			var currentDay = new Date();
			if((currentDay.getTime()-timeoutdata)>60*24000){
				window.localStorage.clear();
			}
		}
		comandaConfirm = window.localStorage.getItem("comanda.confirm");
		window.localStorage.setItem("comanda.restaurant.new",id);
		if(comanda != null && comandaConfirm == null){
			var day = new Date();
			window.localStorage.setItem("comanda.confirm",day.getTime());
			window.localStorage.setItem("comanda.timeout.data",day.getTime());		
			menuRestaurantAction.acceptComandaDialog();
		}else{
			window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+id+"&data="+data;
		}
	},
	actionCloseConfirm: function(){
		var data = window.localStorage.getItem("comanda.data");
		var idRestaurant = window.localStorage.getItem("comanda.restaurant.new");
		if( isNaN(idRestaurant)) return;
		window.localStorage.clear();	
		window.localStorage.setItem("comanda.data",data);
		window.localStorage.setItem("comanda.restaurant",idRestaurant);
		window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&data="+data;
	},
	acceptComandaDialog: function(){
		confirmOnline.closeSetFunc(menuRestaurantAction.actionCloseConfirm);
		confirmOnline.confirm(initParams.txtconfirmcontinuar,menuRestaurantAction.confirmComanda);		
	},
	confirmComanda: function(){
		var comanda = window.localStorage.getItem("comanda");
		var idRestaurant = window.localStorage.getItem("comanda.restaurant.new");
		if( isNaN(idRestaurant)) return;
		if($("#list_rest_"+idRestaurant).hasClass("tancat")){
			alertOnline.alertes(initParams.txtavisrestauranttancat);	
		}
		if(!isNaN(comanda)){
			var data = window.localStorage.getItem("comanda.data");
			window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&idComanda="+comanda+"&data="+data;
		}
	},
	goToComandaPas1: function(){
		var plats = window.localStorage.getItem("comanda.numplats");
		if(isNaN(plats) || plats == null ||parseInt(plats) <= 0){
			alertOnline.alertes("No tens plats a la comanda");
			return;
		}
		var data = window.localStorage.getItem("comanda.data");
		window.location.href = "/"+context+"/comanda/goToPas1Action.action?idComanda="+$("#numComanda").text()+"&data="+data;
	}
}


var welcomeAction ={
}
welcomeAction._self=null;


welcomeAction ={
	
	_init: function (){
		this._self =this;
		$(document).ready(function() {
			var comanda = window.localStorage.getItem("comanda");
			var filtre = window.localStorage.getItem("plats.order");
			
			var numPlatsAdded= window.localStorage.getItem("comanda.numplats");
			if(lastIdRestaurant != 'undefined' && numPlatsAdded!='0' && lastIdRestaurant != null && numPlatsAdded!=null && lastIdRestaurant!=window.idRestaurant){
						alertOnline.alertes(txtavisdosrestaurants);	
			}
			
			if(!isNaN(comanda) && comanda != null){
				
				$("#numComanda").text(comanda);				
				
				welcomeAction._setPreu();

				var numplats= welcomeAction._setNumPlats();

				var numbegudes = welcomeAction._setNumBegudes();
			
				var nProductes = parseInt(numplats)+parseInt(numbegudes);
				welcomeAction._setNumProducts(nProductes);
				
			}else{
				$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
			}
						
			
			welcomeAction._setFilter();
			welcomeAction._getHora();
			
		});
	},
	addProduct: function(id){
		
		$("#"+id).dblclick();
		
	},
	_getHora: function(){
		if(isNaN(idRestaurant)){console.log("idRestaurant from server is not a number"); return;}
		var dataComanda = window.localStorage.getItem("comanda.data");
		var data ="data="+dataComanda+"&restaurantId="+idRestaurant;
		$.ajax({
			  type: "POST",
			  url: '/'+context+'/comanda/getHoraComanda.action',
			  dataType: 'json',
			  data: data,
			  success: function(json){	
				  if(json!=null && json.error!=null){           				
	   				console.log(json.error);	
	   			}else{
	   				if(json!=null){   					
	   					$("#hora_int").text(json.hora+ "H");   				
	   				}
	   			}				
			  },
			  error: function(e){ console.log(txterrorAjax);	
			  					}
			});	
	},
	_setFilter: function(){
		var filtre = window.localStorage.getItem("plats.order");
		$("#plat_select_1").removeClass("selec");
		$("#plat_select_2").removeClass("selec");
		$("#plat_select_3").removeClass("selec");
		

		if(filtre=='primer'){
			$("#plat_select_1").addClass("selec");
		}else if(filtre=='segon'){
			$("#plat_select_2").addClass("selec");
		}else if(filtre=='postre'){
			$("#plat_select_3").addClass("selec");
		}else{
			$("#plat_select_1").addClass("selec");
		}
	},
	_setPreu: function(){
		var preu = window.localStorage.getItem("comanda.preu");
		if (!isNaN(preu) && preu != null) {
			var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
			if (!isNaN(preuBegudes) && preuBegudes != null) {
				preu =  parseFloat(preu) + parseFloat(preuBegudes);
			}
			$("#preu").text(parseFloat(preu).toFixed(2));
		}else{
			$("#preu").text("0.0");
		}
	},
	_setNumProducts: function(nProducts){
		if(isNaN(nProducts)){console.log("numproducts not a number")}
		if(nProducts==1){
			$("#numProduct").text(initParams.txtconfirm+" "+nProducts+" "+initParams.txtproducte);
		}else if(nProducts>1){
			$("#numProduct").text(initParams.txtconfirm+" "+nProducts+" "+initParams.txtproductes);
		}		
		
		if(isNaN(nProducts)){
			window.localStorage.clear();
			$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
		}
	},
	_setNumBegudes: function(){
		var numbegudes = window.localStorage.getItem("comanda.numbegudes");
		if(isNaN(numbegudes) || numbegudes == null){
			numbegudes=0;
			window.localStorage.setItem("comanda.numbegudes","0");
		}else{
			var lis= window.localStorage.getItem("comanda.begudes.lis");
			if( lis!=null){
				$("#disp_beguda").append(lis);
			}
		}
	
		if (!isNaN(numbegudes) && numbegudes != null) {
			$("#numbegudes").text(numbegudes);
		}
		return numbegudes;
	},
	_setNumPlats: function(){
		var numplats = window.localStorage.getItem("comanda.numplats");
		if(isNaN(numplats) || numplats == null){
			numplats=0;
			window.localStorage.setItem("comanda.numplats","0");
		}else{
			
			var lis= window.localStorage.getItem("comanda.plats.lis");
			if( lis!=null){
				$("#disp_plate").append(lis);
			}
		}
		
		if (!isNaN(numplats) && numplats != null) {
			$("#numplats").text(numplats);
		}
		return numplats;
	}

}

function closeImage(){
	$("#imageBig").dialog("close");
}

$("#imageBig").dialog({ 
	   autoOpen: false,
	   height: 500,
	   width: 650,
	   modal: true});

begudaOBJ._init(); 
platOBJ._init(); 
menuRestaurantAction._init();
welcomeAction._init();