///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtconfirm,txtproductes,txtproducte){		
	this.txtconfirm = txtconfirm;
	this.txtproductes = txtproductes;
	this.txtproducte = txtproducte;
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
	
	var data ="idBeguda="+idBeguda+"&idComanda="+$("#idcomanda").val()+"&promo=false";
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
       							var li= value.numBegudes+" <span class='plats'>x</span> "+$("#p_desc_beg_"+value.beguda.id).text()+"<br><br>";
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



function goToInfoBeguda(id){
	
	$("#infoBeguda_dialog").load("../foro/foro.action?idPlat="+id);	
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
}

$("#infoBeguda_dialog").dialog({ 
	   autoOpen: false,
	   height: 700,
	   width: 750,
	   modal: true,
	   close: function(event, ui) { 			   
		   $("#infoBeguda_dialog").dialog("close"); 			  
		}
	});


var comanda = window.localStorage.getItem("comanda");

if (comanda != 'undefined' && comanda != null) {

	$("#numComanda").text(comanda);

	var preu = window.localStorage.getItem("comanda.preu");
	if (preu != 'undefined' && preu != null) {
		var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
		if (preuBegudes != 'undefined' && preuBegudes != null) {
			preu =  parseFloat(preu) + parseFloat(preuBegudes);
		}
		$("#preu").text(preu);
	}

	var numplats = window.localStorage.getItem("comanda.numplats");
	if (numplats != 'undefined' && numplats != null) {
		$("#numplats").text(numplats);
	}

	var numbegudes = window.localStorage.getItem("comanda.numbegudes");
	if (numbegudes != 'undefined' && numbegudes != null) {
		$("#numbegudes").text(numbegudes);
	}
}

$(document).ready(function() {
	
	var comanda = window.localStorage.getItem("comanda");
	
	if(comanda != 'undefined' && comanda != null){
		var numplats = window.localStorage.getItem("comanda.numplats");
		var numbegudes = window.localStorage.getItem("comanda.numbegudes");
		var nProductes = parseInt(numplats)+parseInt(numbegudes);
		if(nProductes==1){
			$("#numProduct").text(initParams.txtconfirm+" "+nProductes+" "+initParams.txtproducte);
		}else if(nProductes>1){
			$("#numProduct").text(initParams.txtconfirm+" "+nProductes+" "+initParams.txtproductes);
		}		
				
	}else{
		window.localStorage.clear();
		$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
	}
	
	if(isNaN(nProductes)){
		window.localStorage.clear();
		$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
	}		
});



