					window.setTimeout(function() {
						    var chargeBar = new Progress.bar({ id: "progress1", autoRemove: false, backgroundSpeed: 5, type: "charge" });
						    chargeBar.renderTo(document.getElementById('chargeBar'));
						
						    var percent = 0;
						    window.setInterval(function() {
						        percent = percent + Math.floor(Math.random()*10);
						        percent = percent >= 100 ? 0 : percent;
						        chargeBar.update(percent);
						    }, 500);						
						}, 500);

					$("#chargeBar").hide();

function payComanda(){
		var comanda = window.localStorage.getItem("comanda");
		if(comanda!= null && comanda != 'undefined'){
				$("#chargeBar").show();
				var data ="idComanda="+comanda;
				window.location.href="/onlineBot/payment/paymentEntry.action?"+data;
		}		
}	

function onlyEntero(value,id){
	  if(value =='' || /^[0-9]*$/.test(value)){
		$('#'+id).css('border', 'solid 1px rgb(135,155,179)');
		return true;
	}else{
		$('#'+id).css('border', 'solid 1px red');
		alert(initTableParams.txterrornumber);
		return false;
	}
}

function saveNewPLatAmount(id,value){
	if(onlyEntero(value,id)){
		 var preu = $("#platpreu_"+id).text();
		 var numPlatsAnt = window.localStorage.getItem("comanda.plat_"+id);
		 if(numPlatsAnt != 'undefined' && numPlatsAnt != null){
			 window.localStorage.setItem("comanda.plat_"+id,value);
			 var preuToAdd = parseFloat(preu)*value;
			 var preuActual = $("#preu").text();
			 var newPreu = preuToAdd+parseFloat(preuActual);
			 $("#preu").text(newPreu);
			 window.localStorage.setItem("comanda.preu",newPreu);
			 var nPlats = $("#numplats").text();
			 $("#numplats").text(parseInt(nPlats)+value);
			 window.localStorage.setItem("comanda.numplats",parseInt(nPlats)+value);
		 }else{
			 var numaddedPlats =parseInt(value);
			 var preuToAdd = parseFloat(preu)*numaddedPlats;
			 var preuActual = $("#preu").text();
			 var newPreu = preuToAdd+parseFloat(preuActual);
			 $("#preu").text(newPreu);
			 window.localStorage.setItem("comanda.preu",newPreu);
			 var nPlats = $("#numplats").text();			 
			 $("#numplats").text(parseInt(nPlats)+numaddedPlats);
			 window.localStorage.setItem("comanda.numplats",parseInt(nPlats)+numaddedPlats);
			 
		 }
	}
}

function savePlatToComanda(idPlat,nPlats){
	var idcomanda= window.localStorage.getItem("comanda");
	var data ="idPlat="+idPlat+"&idComanda="+idcomanda+"&nplats="+nPlats;
  	$.ajax({
  		  type: "POST",
  		  url: '/onlineBot/comanda/ajaxLoadNumPlat.action',
  		  dataType: 'json',
  		  data: data,
  		  success: function(json){	
  			  if(json==null || json.error!=null){
       				$("#errorsajaxlabel").text(json.error);
       				$("#errorsajax").show();
       			}				
  		  },
  		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
    								$("#errorsajax").show();  		
  		  					}
  		});	
}

function checkComandaJS(){
	var comanda = window.localStorage.getItem("comanda");
	if(comanda!= null && comanda != 'undefined'){
		var hora = $("#comandahora").val();
		var dia = $("#dia").val();
		var adomicili = $("#adomicili").val();
		var address = $("#comandaddress").val();
		address =  address.replace(/\n/g, "");
		$("#chargeBar").show();
		var data ="idComanda="+comanda+"&dia="+dia+"&hora="+hora+"&aDomicili="+adomicili+"&address="+address;
	  	$.ajax({
	  		  type: "POST",
	  		  url: '/onlineBot/comanda/checkComanda.action',
	  		  dataType: 'json',
	  		  data: data,
	  		  success: function(json){	
	  			  	if(json==null || json.error!=null){
	       				$("#errorsajaxlabel").text(json.error);
	       				$("#errorsajax").show();
	       			}else{
	       				if(json.alertLoged!=null){
	       					alert(json.alertLoged);
	       					$("#chargeBar").hide();
	       					return;
	       				}
	       				if(json.alerta!=null){
	       					alert(json.alerta);
	       					$("#chargeBar").hide();
	       					return;
	       				}
	       				
	       				if(json.comandaOK !=null ){
	       					alert(json.comandaOK);
	       					$("#chargeBar").hide();
	       					$("#paycomanda").show();
	       					$("#checkPromocionsDisponibles").show();
	       					return;
	       				}
	       						       			
	       			}	  			  		  			  		  			  	
	  			  $("#chargeBar").hide();
	  		  },
	  		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
	    								$("#errorsajax").show();  		
	  		  					}
	  		});
	}
}
$(function(){
	
	$( ".draggable" ).dblclick(function() {
		
		var dragBeguda =$(this).clone();
		dragBeguda.appendTo("#slider");
		
		dragBeguda.animate({
							    width: "90%",
							    opacity: 0.4, 
							    marginLeft: "0.6in",
							    fontSize: "3em",
							    borderWidth: "10px",
							    left: "+=250px"
	  						}, 1800,function() {
	  								var id = $(this).attr("id");
	  								saveBegudaToComanda(id);
	      							$(this).css("visiblity","hidden");
	      							$(this).css("display","none");
	    					});
	  	
	});
	
	
	function saveBegudaToComanda(idBeguda){
		
		var data ="idBeguda="+idBeguda+"&idComanda="+$("#idcomanda").val();
      	$.ajax({
      		  type: "POST",
      		  url: '/onlineBot/comanda/ajaxLoadBeguda.action',
      		  dataType: 'json',
      		  data: data,
      		  success: function(json){	
      			  if(json==null || json.error!=null){
           				$("#errorsajaxlabel").text(json.error);
           				$("#errorsajax").show();
           			}else{
           				if(json.alerta!=null){
           					alert(json.alerta);
           				}else{
           					var numBegudes=0;
           					var preu = $("#preu").text();
           					var preuF = parseFloat(preu);
           					var html="";
           					$.each(json, function(index, value) { 
           					 	numBegudes= numBegudes+value.numBegudes;
           						preuF= preuF + parseFloat(value.beguda.preu);
           						html=html+"<div class='selector'>"+value.beguda.nom+"<br>"+value.beguda.preu+"</div>";
           						
           					});
           					window.localStorage.setItem("comanda.numbegudes",numBegudes);
           					window.localStorage.setItem("comanda.preu",preuF);
           					$("#begudes").html(html);
           					$("#numbegudes").text(numBegudes);
           					$("#preu").text(preuF);
           				}
           			}				
      		  },
      		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
        								$("#errorsajax").show();  		
      		  					}
      		});	
	}
	
	$( ".draggable" ).draggable({
		 helper:'clone',		
		 start: function(event,ui){				
			 $("#slider").css("height","500px");	  	
	    }
	});
	$( "#droppable" ).droppable({
	    drop: function( event, ui ){	        
	        var item_id = ui.draggable.attr("id");     
	        saveBegudaToComanda(item_id);
	    }
	});
});

//---------------------------------------------------------------------------------------------------------------------
    Calendar.setup({
        inputField    	:    "dia",      // id del campo de texto
        ifFormat       	:    "%d-%m-%Y",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llencadorData1",          // el id del botón que lanzará el calendario
        locale 		   	:    "ca_ES"
    });
//---------------------------------------------------------------------------------------------------------------------
new Address.addressValidation();

$("#paycomanda").hide();
$("#checkPromocionsDisponibles").hide();


var sudoSlider = $("#slider").sudoSlider({
    autowidth:false,
    slideCount:3,
    continuous:true
});

function checkPromocionsDisponibles(){
	var comandaId = window.localStorage.getItem("comanda");
	if(comandaId!= null && comandaId != 'undefined'){

		var data ="idComanda="+comandaId;
		$("#chargeBar").show();
	  	$.ajax({
	  		  type: "POST",
	  		  url: '/onlineBot/comanda/checkComandaPromos.action',
	  		  dataType: 'json',
	  		  data: data,
	  		  success: function(json){	
	  			  	if(json==null || json.error!=null){
	       				$("#errorsajaxlabel").text(json.error);
	       				$("#errorsajax").show();
	       			}else{
	       				if(json.alertLoged!=null){
	       					alert(json.alertLoged);
	       					$("#chargeBar").hide();
	       					return;
	       				}
	       				
	       				if(json.promosNumComanda !=null ){
	       					alert("eiii");
	       				
	       					return;
	       				}
	       						       			
	       			}	  			  		  			  		  			  	
	  			  $("#chargeBar").hide();
	  		  },
	  		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
	    								$("#errorsajax").show();  		
	  		  					}
	  		});
	}	
	
}
