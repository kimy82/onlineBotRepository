///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtconfirm,txtproductes,txtproducte, txtavisrestauranttancat, txtconfirmcontinuar){		
	this.txtconfirm = txtconfirm;
	this.txtproductes = txtproductes;
	this.txtproducte = txtproducte;
	this.txtavisrestauranttancat = txtavisrestauranttancat;
	this.txtconfirmcontinuar = txtconfirmcontinuar
}

$(function() {

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
			saveBegudaToComanda(rawBeguda[1]);
			$(dragBeguda).css("visiblity", "hidden");
			$(dragBeguda).css("display", "none");
		});

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
			var rawBeguda = item_id.split("_");		
			saveBegudaToComanda(rawBeguda[1]);

		}
	});
});

function addProduct(id){
	
	$("#"+id).dblclick();
	
}

function saveBegudaToComanda(idBeguda){
	var comanda = $("#numComanda").text(comanda);
	var data ="idBeguda="+idBeguda+"&idComanda="+comanda+"&promo=false";
  	$.ajax({
  		  type: "POST",
  		  url: '/'+context+'/comanda/ajaxLoadBeguda.action',
  		  dataType: 'json',
  		  data: data,
  		  success: function(json){	
  			  if(json!=null && json.error!=null){           				
       				errorOnline.error("Error in AJAX: "+json.error);	
       			}else{
       				if(json.alerta!=null){
       					alertOnline.alertes(json.alerta);
       					
       				}else{
       					var numBegudes=0;
       					var numBegudesPromo=0;           					
       					var preuBegudes = 0.0;
       					var html="";
       					var begudes= json.begudes;	       					
       					$("#disp_beguda").html("");
       					$.each(begudes, function(index, value) { 
       					 	
       					 	if(value.promo==true || value.promo=='true'){
       					 		numBegudesPromo=numBegudesPromo+value.numBegudes;
       					 		
       					 	}else{
       					 		numBegudes= numBegudes+value.numBegudes;
       					 		preuBegudes=  parseFloat(preuBegudes) + (parseFloat(value.beguda.preu)*value.numBegudes);
       							var li= value.numBegudes+" <span class='plats'>x</span> "+value.beguda.nom+"<br><br>";
    							$("#disp_beguda").append(li);
       						}	       				
       						
       					});
       				
       					window.localStorage.setItem("comanda.promo.nBegudes.added",numBegudesPromo);
       					window.localStorage.setItem("comanda.numbegudes",numBegudes);
       					window.localStorage.setItem("comanda.beguda.preu",preuBegudes.toFixed(2));	       					
       					$("#numbegudes").text(numBegudes);	       				
       					var preuComanda = window.localStorage.getItem("comanda.preu");
       					if(preuComanda!= 'undefined' && preuComanda != null){
       						var preuFinal = parseFloat(preuComanda) + parseFloat(preuBegudes);
       						$("#preu").text(preuFinal.toFixed(2));
       					}else{
       						window.localStorage.setItem("comanda",json.numComanda);
       						window.localStorage.setItem("comanda.preu","0");
       						$("#preu").text(preuBegudes.toFixed(2));
       					}	       						       				
       					
       					$("#numComanda").text(json.numComanda);
       					}
       			}				
  		  },
  		  error: function(e){  errorOnline.error("Error in AJAX");	
  		  					}
  		});	
}
function goToComandaPas1() {
	var data = window.localStorage.getItem("comanda.data");
	window.location.href = "/"+context+"/comanda/goToPas1Action.action?idComanda="+$("#numComanda").text()+"&data="+data;
}

function goToRestaurantMenu(id){
	var comanda = window.localStorage.getItem("comanda");
	var data = window.localStorage.getItem("comanda.data");
	window.localStorage.setItem("comanda.restaurant",id);
	
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
										window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&idComanda="+comanda+"&data="+data;
									}
								}

$(document).ready(function() {
	
	var comanda = window.localStorage.getItem("comanda");
	if(comanda != 'undefined' && comanda != null){
		
		$("#numComanda").text(comanda);
	
		var preu = window.localStorage.getItem("comanda.preu");
		
		if(preu == 'undefined' || preu == null){
			preu=0.0;
			window.localStorage.setItem("comanda.preu","0.0");
		}
		
		if (preu != 'undefined' && preu != null) {
			var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
			if (preuBegudes != 'undefined' && preuBegudes != null) {
				preu =  parseFloat(preu) + parseFloat(preuBegudes);
			}
			$("#preu").text(preu);
		}
	
		var numplats = window.localStorage.getItem("comanda.numplats");
		
		if(numplats == 'undefined' || numplats == null){
			numplats=0;
			window.localStorage.setItem("comanda.numplats","0");
		}else{
			
			var lis= window.localStorage.getItem("comanda.plats.lis");
			if(lis != 'undefined' && lis!=null){
				$("#disp_plate").append(lis);
			}
		}
		
		if (numplats != 'undefined' && numplats != null) {
			$("#numplats").text(numplats);
		}
	
		var numbegudes = window.localStorage.getItem("comanda.numbegudes");
		
		if(numbegudes == 'undefined' || numbegudes == null){
			numbegudes=0;
			window.localStorage.setItem("comanda.numbegudes","0");
		}else{
			var lis= window.localStorage.getItem("comanda.begudes.lis");
			if(lis != 'undefined' && lis!=null){
				$("#disp_beguda").append(lis);
			}
		}
		if (numbegudes != 'undefined' && numbegudes != null) {
			$("#numbegudes").text(numbegudes);
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
		window.localStorage.clear();
		$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
	}
	
		
});



