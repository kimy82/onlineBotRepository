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
window.promoBeguda=null;
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
			 
			 var nPlatsAdded = parseInt(value)-parseInt(numPlatsAnt);
			 
			 window.localStorage.setItem("comanda.plat_"+id,value);
			 
			 var preuToAdd = parseFloat(preu)*nPlatsAdded;
			 var preuActual = $("#preu").text();
			 var newPreu = preuToAdd+parseFloat(preuActual);
			 $("#preu").text(newPreu);
			 
			 window.localStorage.setItem("comanda.preu",newPreu);
			 
			 var nPlats = $("#numplats").text();
			 
			 $("#numplats").text(parseInt(nPlats)+nPlatsAdded);
			 window.localStorage.setItem("comanda.numplats",parseInt(nPlats)+nPlatsAdded);
			 //Guardem els plats afegits
			 savePlatToComanda(id,nPlatsAdded);
		 }else{
			 
			 //Si entrem aki hi ha hagut un error
			alert("Please try again!! or remove the plat. There aren't more");
			 
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
	
	
	
	function saveBegudaToComanda(idBeguda,promo){
		
		var data ="idBeguda="+idBeguda+"&idComanda="+$("#idcomanda").val()+"&promo="+promo;
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
	    	var  tipus = ui.draggable.attr("title");
	    	if(window.promoBeguda != 'undefined' && window.promoBeguda != null && window.promoBeguda.promo!="true" ){	    		    
		        saveBegudaToComanda(item_id,false);
	    	}else{
	    		if(window.promoBeguda.numBegudesAdded < window.promoBeguda.numBegudes){
	    			if(tipus!= window.promoBeguda.tipusBeguda){
	    				alert("La beb no és del tipus de la promoció!");
	    			}
	    			saveBegudaToComanda(item_id,true);
	    		}else{
	    			alert("No more drinks to the promo cart");
	    		}
	    		
	    	}
	    	
	        
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
	       					//Hi ha prom del tipus numero de comanda
	       					fillPromos(json.promosNumComanda);
	       			
	       				}
	       				if(json.promosAPartirDe !=null ){
	       					//Hi ha prom del tipus a partir de
	       					fillPromos(json.promosAPartirDe);
	       					
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

function fillPromos(json){
	$.each(json, function(index,item){
		if(item.numBegudes!=null && json.numBegudes!= "0"){
			//Promocio de begudes
			var liToAppend = "<li><a href='#' onclick='addPromoBeguda('"+json.numBegudes+"','"+json.tipusBeguda+"') >'Te un descompte per escollir "+json.numBegudes +" begudes de tipus "+json.tipusBeguda+"</a>";
			$("#dialog_promo ul").append(liToAppend);
			continue;
		}
		if(item.descompteImport!=null && item.descompteImport!="0"){			
			//promocio descompte de pasta
			var liToAppend = "<li><a href='#' onclick='addPromoImport('"+json.descompteImport+"','"+json.tipuDescompte+"') >'Te un descompte de  "+json.descompteImport +" en "+json.tipuDescompte+"</a>";
			$("#dialog_promo ul").append(liToAppend);
			continue;
		}
	});		
	
}

function addPromoBeguda(nbegudes, tipusBeguda){
	//La idea es obrir un div on es pugui arrastrar una beguda
	window.promoBeguda= {promo : "true"};
	window.promoBeguda.numBegudesAdded=0;
	window.promoBeguda.numBegudes=parseInt(nbegudes);
	window.promoBeguda.tipusBeguda=tipusBeguda;
	
	alert("Afegeix les begudes al box");
	
}

function addPromoImport(importDescompte, tipusDescompte){
	//La idea és afegir el descompte al div d'info de la comanda
}

function openDialogPromos(){
	 $("#dialog_promos").dialog("open"); 
}

$("#dialog_promo").dialog( { autoOpen: false,
	  modal: true,
	  position: 'center',
	  draggable: true,
	  height: 390,
	  width: 900,		
	  open: function(event, ui) { 
		 //carrega la taula del dialog
		checkPromocionsDisponibles(); 
		
		$('#dialog_promo').css('overflow', 'hidden');
		

	 }
});
