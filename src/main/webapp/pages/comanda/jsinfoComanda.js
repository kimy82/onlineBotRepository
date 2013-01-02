///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtBegudaNoPromocio,txtNoMoreDrinksToAddinPromo,txtAddDrinkstoBox, txtdescompteaplicat,txtpromodeleted){		
		this.txtBegudaNoPromocio=txtBegudaNoPromocio;
		this.txtNoMoreDrinksToAddinPromo=txtNoMoreDrinksToAddinPromo;
		this.txtAddDrinkstoBox = txtAddDrinkstoBox;
		this.txtdescompteaplicat = txtdescompteaplicat;
		this.txtpromodeleted = txtpromodeleted;
}
//progress var
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
				var subdata = checkPromoImport();
				var data ="idComanda="+comanda + subdata;
				window.location.href="/onlineBot/payment/paymentEntry.action?"+data;
		}		
}	

function checkPromoImport(){
	var promo = 	window.localStorage.getItem("comanda.promo.descompte");
	if(promo!=null && promo!='undefined'){
		var import = window.localStorage.getItem("comanda.promo.descompte.import");
		var tipus = window.localStorage.setItem("comanda.promo.descompte.tipus");
		
		return "&tipuDescomte="+tipus+"&importDescomte="+import;
	}
	return "";
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
			 $("#preu").text(parseFloat(newPreu).round(2));
			 
			 window.localStorage.setItem("comanda.preu",parseFloat(newPreu).round(2));
			 
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
           					var numBegudesPromo=0;
           					var preu = $("#preu").text();
           					var preuF = parseFloat(preu).round(2);
           					var html="";
           					$.each(json, function(index, value) { 
           					 	
           					 	if(value.promo==true || value.promo=='true'){
           					 		numBegudesPromo=numBegudesPromo+value.numBegudes;
           					 		
           					 	}else{
           					 		numBegudes= numBegudes+value.numBegudes;
           					 		preuF= preuF + parseFloat(value.beguda.preu).round(2);
           						}
           						html=html+"<div class='selector'>"+value.beguda.nom+"<br>"+value.beguda.preu+"</div>";
           						
           					});
           				
           					window.localStorage.setItem("comanda.promo.nBegudes.added",numBegudesPromo);
           					window.localStorage.setItem("comanda.numbegudes",numBegudes);
           					window.localStorage.setItem("comanda.preu",preuF);
           					$("#begudes").html(html);
           					$("#numbegudes").text(numBegudes);
           					$("#numbegudespromo").text(numBegudesPromo);
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
	    	if(window.promoBeguda == null && window.promoBeguda.promo!="true" ){	    		    
		        saveBegudaToComanda(item_id,false);
	    	}else{
	    		if(window.promoBeguda.numBegudesAdded < window.promoBeguda.numBegudes){
	    			if(tipus!= window.promoBeguda.tipusBeguda){
	    				alert(initParams.txtBegudaNoPromocio);
	    			}
	    			//Afegim beguda al contador
	    			var n = window.promoBeguda.numBegudesAdded;
	    			window.promoBeguda.numBegudesAdded = parseInt(n) +1;
	    			saveBegudaToComanda(item_id,true);
	    		}else{
	    			alert(initParams.txtNoMoreDrinksToAddinPromo);
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
	       					fillPromos(json.promosNumComanda[0]);
	       			
	       				}
	       				if(json.promosAPartirDe !=null ){
	       					//Hi ha prom del tipus a partir de
	       					fillPromos(json.promosAPartirDe[0]);
	       					
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
		if(item.numBegudes!=null && item.numBegudes!= 'undefined' && json.numBegudes!= "0"){
			//Promocio de begudes
			var liToAppend = "<li><a href='#' onclick=\"addPromoBeguda('"+item.numBegudes+"','"+item.tipusBeguda+"')\" >'Te un descompte per escollir "+item.numBegudes +" begudes de tipus "+item.tipusBeguda+"</a>";
			$("#dialog_promo ul").append(liToAppend);
			
		
		}
		if(item.descompteImport!=null && item.descompteImport!= 'undefined' && item.descompteImport!="0"){			
			//promocio descompte de pasta
			var liToAppend = "<li><a href='#' onclick=\"addPromoImport('"+item.descompteImport+"','"+item.tipuDescompte+"')\" >'Te un descompte de  "+item.descompteImport +" en "+item.tipuDescompte+"</a>";
			$("#dialog_promo ul").append(liToAppend);

		}
	});		
	
}

function addPromoBeguda(nbegudes, tipusBeguda){
	//La idea es obrir un div on es pugui arrastrar una beguda
	window.localStorage.setItem("comanda.promo.beguda","true");
	window.localStorage.setItem("comanda.promo.nBegudes.total",nbegudes);
	window.localStorage.setItem("comanda.promo.beguda.tipus",tipusBeguda);
	
	window.promoBeguda= {promo : "true"};
	window.promoBeguda.numBegudesAdded=0;
	window.promoBeguda.numBegudes=parseInt(nbegudes);
	window.promoBeguda.tipusBeguda=tipusBeguda;
	
	$("#checkPromocionsDisponibles").hide();
	
	alert(initParams.txtAddDrinkstoBox);
	
}

//Inicialitzem si tenia una promo de begudes
function initPromoBegudaFromStorage(){
	var isPromo =  window.localStorage.getItem("comanda.promo.beguda");
	if(isPromo=='undefined' || isPromo==null){
		window.promoBeguda=null;	
	}else{
		
		var nbegudes= window.localStorage.getItem("comanda.promo.nBegudes.total");	
		var tipoBeguda = window.localStorage.getItem("comanda.promo.beguda.tipus");
		if(nbegudes!='undefined' && nbegudes!=null && tipoBeguda!='undefined' && tipoBeguda != null){
			addPromoBeguda(nbegudes, tipoBeguda);
			var nbegudesAdded = window.localStorage.getItem("comanda.promo.nBegudes.added");
			window.promoBeguda.numBegudesAdded = nbegudesAdded;
		}			
	}
}

initPromoBegudaFromStorage();

//Inicialitzem si tenia una promo de descompte
function initPromoDescompteFromStorage(){
	var isPromo =  window.localStorage.getItem("comanda.promo.descompte");
	if(isPromo=='undefined' || isPromo==null){
		window.promoDescompte=null;	
	}else{
		
		var importe= window.localStorage.getItem("comanda.promo.descompte.import");	
		var tipoDescompte = window.localStorage.getItem("comanda.promo.descompte.tipus");
		if(importe!='undefined' && importe!=null && tipoDescompte!='undefined' && tipoDescompte != null){
			addPromoImport(importe, tipoDescompte);		
		}			
	}
}

initPromoDescompteFromStorage();




function addPromoImport(importDescompte, tipusDescompte){
	
	//La idea és afegir el descompte al div d'info de la comanda
	window.localStorage.setItem("comanda.promo.descompte","true");
	window.localStorage.setItem("comanda.promo.descompte.import",importDescompte);
	window.localStorage.setItem("comanda.promo.descompte.tipus",tipusDescompte);
	
	window.promoDescompte= {promo : "true"};
	window.promoDescompte.import= importDescompte;
	window.promoDescompte.tipus=tipusDescompte;
	
	//Actualitzem les dadesde la cart
	var preu = window.localStorage.getItem("comanda.preu");
	
	if(tipusDescompte=='1'){
		var preuF = parseFloat(preu)*((100-parseFloat(importDescompte))/100);
		$("#preu").text(preu+" (-"+importDescompte+" %) ="+parseFloat(preuF).round(2));
	}else{
		var preuF = parseFloat(preu)-parseFloat(importDescompte);
		$("#preu").text(preu+" (-"+importDescompte+" Euros) ="+parseFloat(preuF).round(2));
	}
	
	$("#checkPromocionsDisponibles").hide();
	
}

function deletePromoApplied(){
 
	var isPromoBeguda = window.localStorage.getItem("comanda.promo.beguda");
	var isPromoDescompte = window.localStorage.getItem("comanda.promo.descompte");
	
	if(isPromoDescompte!='undefined' && isPromoDescompte!=null){
		
		window.localStorage.removeItem("comanda.promo.descompte");
		window.localStorage.removeItem("comanda.promo.descompte.import");
		window.localStorage.removeItem("comanda.promo.descompte.tipus");
		
		window.promoDescompte=null;
		
		var preu = window.localStorage.getItem("comanda.preu");
		$("#preu").text(preu);
		
	}
	if(isPromoBeguda!='undefined' && isPromoBeguda!=null){
		
		window.localStorage.removeItem("comanda.promo.beguda");
		window.localStorage.removeItem("comanda.promo.nBegudes.total");
		window.localStorage.removeItem("comanda.promo.beguda.tipus");
		
		window.promoBeguda= null;
		
		$("#numbegudespromo").text("0");
		
		deleteAjaxBegudesPromo();
	}
	
	alert(initParams.txtpromodeleted);
 
}

function deleteAjaxBegudesPromo(){
	var comandaId = window.localStorage.getItem("comanda");
	if(comandaId!= null && comandaId != 'undefined'){

		var data ="idComanda="+comandaId;

	  	$.ajax({
	  		  type: "POST",
	  		  url: '/onlineBot/comanda/deleteBegudesPromo.action',
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
}

function openDialogPromos(){
	 $("#dialog_promo").dialog("open"); 
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
