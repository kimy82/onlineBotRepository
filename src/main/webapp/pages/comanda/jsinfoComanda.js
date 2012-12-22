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
					
function checkComandaJS(){
	 var comanda = window.localStorage.getItem("comanda");
	if(comanda!= null && comanda != 'undefined'){
		$("#chargeBar").show();
		var data ="idComanda="+comanda;
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
	       						       			
	       			}
	  			  	alert("No problem to deliver comanda");
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
function submitLog(){
	
	$.ajax({
	    url: "<c:url value='/onlineBot/j_spring_security_check' />",
	    type: "POST",
	    data: $("#f").serialize(),
	    dataType: 'json',
	    beforeSend: function (xhr) {
	        xhr.setRequestHeader("X-Ajax-call", "true");
	    },
	    success: function(json) {
	        if (json.result == "ok") {
	        	$("#loged").text("OK, Validaci� correcte");
	             console.log("ssss");
	        } else if (json.result == "error") {
	        	console.log("error");
	        	$("#loged").text("KO, Validaci� incorrecte");
	        }
	    }
	});
	
}

//---------------------------------------------------------------------------------------------------------------------
    Calendar.setup({
        inputField    	:    "dia",      // id del campo de texto
        ifFormat       	:    "%d-%m-%Y",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llencadorData1",          // el id del bot�n que lanzar� el calendario
        locale 		   	:    "ca_ES"
    });
//---------------------------------------------------------------------------------------------------------------------
new Address.addressValidation();

$("#idcomanda").val(${idComanda});
$("#numComanda").text(${idComanda});

$("#numplats").text(${fn:length(comanda.plats)});
$("#preu").text(${comanda.preu});
$("#numbegudes").text(${fn:length(comanda.begudes)});
var sudoSlider = $("#slider").sudoSlider({
    autowidth:false,
    slideCount:3,
    continuous:true
});