$(function() {

	$(".selector").dblclick(function() {

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
			savePlatToComanda(rawPlat[1]);
			$(this).css("visiblity", "hidden");
			$(this).css("display", "none");
		});

	});

	function savePlatToComanda(idPlat) {

		var data = "idPlat=" + idPlat + "&idComanda=" + $("#numComanda").text();
		$.ajax({
					type : "POST",
					url : '/'+context+'/comanda/ajaxLoadPlat.action',
					dataType : 'json',
					data : data,
					success : function(json) {
						if (json == null || json.error != null) {
							errorOnline.error(json.error);
						} else {
							if (json.alerta != null) {
								alertOnline.alertes(json.alerta);								
							} else {
								// Posem id en el local storage
								window.localStorage.setItem("comanda",
										json.numComanda);
								window.localStorage.setItem("comanda.preu",
										json.preu);
								window.localStorage.setItem("comanda.numplats",
										json.numPlats);
								saveLocalStorageNplatsOfsinglePlat(idPlat);

								$("#numComanda").text(json.numComanda);
								$("#numplats").text(json.numPlats);
								$("#preu").text(json.preu);
							}
						}
					},
					error : function(e) {						
						errorOnline.error("Error in AJAX");
					}
				});
	}

	function saveLocalStorageNplatsOfsinglePlat(idPlat) {
		var nPl = window.localStorage.getItem("comanda.plat_" + idPlat);

		if (nPl != 'undefined' && nPl != null) {
			window.localStorage.setItem("comanda.plat_" + idPlat,
					parseInt(nPl) + 1);
		} else {
			window.localStorage.setItem("comanda.plat_" + idPlat, 1);
		}
	}

	$(".selector").draggable({
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
			var rawPlat = item_id.split("_");
			var data = window.localStorage.setItem("comanda.data");			
			savePlatToComanda(rawPlat[1]);

		}
	});
});

function goToComandaPas1() {
	var data = window.localStorage.getItem("comanda.data");
	window.location.href = "/"+context+"/comanda/goToPas1Action.action?idComanda="+$("#numComanda").text()+"&data="+data;
}

$(document).ready(function() {
	$('#coin-slider').coinslider();
});

function goToInfoPlat(id){
	
	$("#infoPlat_dialog").load("../foro/foro.action?idPlat="+id);	
	$("#infoPlat_dialog").dialog({ 
		   autoOpen: false,
		   height: 500,
		   width: 750,
		   modal: true,
		   close: function(event, ui) { 			   
			   $("#infoPlat_dialog").dialog("close"); 			  
			}
	});
    $("#infoPlat_dialog").dialog("open");    
}

$("#infoPlat_dialog").dialog({ 
	   autoOpen: false,
	   height: 700,
	   width: 750,
	   modal: true,
	   close: function(event, ui) { 			   
		   $("#infoPlat_dialog").dialog("close"); 			  
		}
	});


var comanda = window.localStorage.getItem("comanda");

if (comanda != 'undefined' && comanda != null) {

	$("#numComanda").text(comanda);

	var preu = window.localStorage.getItem("comanda.preu");
	if (preu != 'undefined' && preu != null) {
		var preuBegudes = window.localStorage.setItem("comanda.beguda.preu");
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
