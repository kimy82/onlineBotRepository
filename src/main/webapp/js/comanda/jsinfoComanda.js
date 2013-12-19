//variables per textos en locale
var initParams=null ;

function InitParams(txtBegudaNoPromocio,txtNoMoreDrinksToAddinPromo,txtAddDrinkstoBox, 
					txtdescompteaplicat,txtpromodeleted, txtfaltahora, txtcheckaddress, 
					checkok, checkko,txtconfirm,txtproductes,txtproducte,txtnomesdomocili,
					txtbottreurepromo){		
		this.txtBegudaNoPromocio=txtBegudaNoPromocio;
		this.txtNoMoreDrinksToAddinPromo=txtNoMoreDrinksToAddinPromo;
		this.txtAddDrinkstoBox = txtAddDrinkstoBox;
		this.txtdescompteaplicat = txtdescompteaplicat;
		this.txtpromodeleted = txtpromodeleted;
		this.txtfaltahora = txtfaltahora;
		this.txtcheckaddress = txtcheckaddress;
		this.checkok = checkok;
		this.checkko = checkko;
		this.txtconfirm = txtconfirm;
		this.txtproductes = txtproductes;
		this.txtproducte = txtproducte;
		this.txtnomesdomocili = txtnomesdomocili;
		this.txtbottreurepromo = txtbottreurepromo;
}

var initTxtPromos=null ;

function InitTxtPromos(txtnoplats,txtteundescompte,txtteundescomptebis,txtbegudesdetipus,txtregalde,txten){		
		this.txtnoplats=txtnoplats;
		this.txtteundescompte=txtteundescompte;
		this.txtteundescomptebis=txtteundescomptebis;
		this.txtbegudesdetipus=txtbegudesdetipus;
		this.txtregalde=txtregalde;
		this.txten=txten;
}

//amagar divs
$("#arecollir_div").hide();
$("#adomicili_div").hide();
$("#pagarDom").hide();
$("#pagarRec").hide();
$("#checkPromocionsDisponibles").hide();
$("#deletePromoApplied").hide();

//OBJ per l'adreca
var addressManagerObj ={
};
addressManagerObj._self=null;

addressManagerObj ={
	
	_init: function (){
		this._self =this;
	},	
	emptyAddress: function(){
		$("#comandaddress").val("");
	},	
	initAddress: function(addressToLoad){
		if(addressToLoad!=''){
			try{
				var arrayAddress = addressToLoad.split("-");
				$("#carrer").val(arrayAddress[0]);
				
				var codi =arrayAddress[arrayAddress.length-1];
				$("#codi").val(arrayAddress[arrayAddress.length-1]);
				
				if(codi=='17190'){
					$('#poble option[value="Salt"]').attr("selected", "selected");
				}else{
					$('#poble option[value="Girona"]').attr("selected", "selected");
				}
				
				if(arrayAddress.length==5){
					$("#porta").val(arrayAddress[arrayAddress.length-2]);
					$("#num").val(arrayAddress[arrayAddress.length-3]);
					$("#numcarrer").val(arrayAddress[arrayAddress.length-4]);
				}
				
				$("#comandaddress").val(addressToLoad);					
				addressManagerObj.validate();			
			}catch(error){
				addressManagerObj.emptyAddress();
			}
		}	
    },
	validate: function(){
		$("#checkAdd").click();
	}
	
};

//OBJ for managment of promos
var promosManagerObj ={
};
promosManagerObj._self=null;

promosManagerObj ={
	
	_init: function (){
		this._self =this;
	},	
	checkPromoImport: function(){
		var promo = window.localStorage.getItem("comanda.promo.descompte");
		if(promo!=null){
			var importe = window.localStorage.getItem("comanda.promo.descompte.import");
			var tipus = window.localStorage.getItem("comanda.promo.descompte.tipus");
			if(importe==null || tipus==null ){
				return "";
			}else{
				return "&tipuDescomte="+tipus+"&importDescomte="+importe;
			}			
		}
		return "";
	},
	checkPromocionsDisponibles: function(){
		var comandaId = window.localStorage.getItem("comanda");
		if(comandaId!= null){
			var data ="idComanda="+comandaId;
			$.ajax({
				  type: "POST",
				  url: '/'+context+'/comanda/checkComandaPromos.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
						if(json!=null && json.error!=null){	       				
						   console.log(json.error);	
						}else{
							if(json.alertLoged!=null){
								alertOnline.alertes(json.alertLoged);	       					
								return;
							}
							$("#dialog_promo ul#prm").html("");
							if(json.promosNumComanda !=null ){
								//Hi ha prom del tipus numero de comanda
								promosManagerObj.fillPromos(json.promosNumComanda[0]);
						
							}
							if(json.promosAPartirDe !=null ){
								//Hi ha prom del tipus a partir de
								promosManagerObj.fillPromos(json.promosAPartirDe[0]);	       					
							}
													
						}	  			  		  			  		  			  					
				  },
				  error: function(e){  window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	
									}
			});
		}	
	},
	checkPromoEspecial: function(){
		var data="";
		$.ajax({
			  type: "POST",
			  url: '/'+context+'/comanda/checkPromosEspecial.action',
			  dataType: 'json',
			  data: data,
			  success: function(json){	
					if(json!=null && json.error!=null){	       				
						errorOnline.error("HO SENTIM, HI HA HAGUT UN ERROR:"+json.error);	
					}else{
						
						$("#dialog_promo ul#esp").html("");
						if(json !=null ){
							//Hi ha prom del tipus numero de comanda
							promosManagerObj.fillPromosEsp(json);					
						}												
					}	  			  		  			  		  			  	
			  },
			  error: function(e){ window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	
								}
		});
	},
	checkPromoVibility: function(value){
		if(value=='')return;
		var data="code="+value;
		$.ajax({
			  type: "POST",
			  url: '/'+context+'/comanda/checkPromosVisibility.action',
			  dataType: 'json',
			  data: data,
			  success: function(json){	
					if(json!=null && json.error!=null){	       				
					 
					}else{     	
						if(json!=null && json.alert!=null ){
							$("#dialog_promo ul#visp").html(json.alert);
							return;
						}
						$("#dialog_promo ul#visp").html("");
						if(json !=null ){
							//Hi ha prom del tipus numero de comanda
							promosManagerObj.fillPromosVisp(json);					
						}											
					}	  			  		  			  		  			  	
			  },
			  error: function(e){ window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	
								}
		});
	},
	fillPromosVisp: function(json){
		try{
			$.each(json, function(index,item){
				if(item.numBegudes!=null && json.numBegudes!= "0"){
					//Promocio de begudes
					var liToAppend = "<li><a href='#' onclick=\"promosManagerObj.addPromoBeguda('"+item.numBegudes+"','"+item.tipusBeguda+"','"+item.id+"','gen')\" >"+initTxtPromos.txtregalde+" "+item.numBegudes +" "+item.tipusBeguda+"</a>";
					$("#dialog_promo ul#visp").append(liToAppend);									
				}
				if(item.descompteImport!=null && item.descompteImport!="0"){			
					//promocio descompte de pasta
					var tipus=promosManagerObj.getTipoDesCompte(item.tipuDescompte);
					if(item.tipuDescompte=='C1'){
						var liToAppend = "<li><a href='#' onclick=\"promosManagerObj.addPromoImport('"+item.descompteImport+"','"+item.tipuDescompte+"','"+item.id+"','gen')\" >"+initTxtPromos.txtteundescompte+"  "+item.descompteImport +" "+tipus+"</a>";
						$("#dialog_promo ul#visp").append(liToAppend);
					}else{
						var liToAppend = "<li><a href='#' onclick=\"promosManagerObj.addPromoImport('"+item.descompteImport+"','"+item.tipuDescompte+"','"+item.id+"','gen')\" >"+initTxtPromos.txtteundescomptebis+"  "+item.descompteImport +" "+tipus+"</a>";
						$("#dialog_promo ul#visp").append(liToAppend);
					}
				

				}
			});	
		}catch(error){
			console.log(error);
		}
	},
	fillPromosEsp: function(json){
		try{
			$.each(json, function(index,item){
				if(item.numBegudes!=null && json.numBegudes!= "0"){
					//Promocio de begudes
					var liToAppend = "<li><a href='#' onclick=\"promosManagerObj.addPromoBeguda('"+item.numBegudes+"','"+item.tipusBeguda+"','"+item.id+"','esp')\" >"+initTxtPromos.txtregalde+" "+item.numBegudes +" "+item.tipusBeguda+"</a>";
					$("#dialog_promo ul#esp").append(liToAppend);									
				}
				if(item.descompteImport!=null && item.descompteImport!= 'undefined' && item.descompteImport!="0"){			
					//promocio descompte de pasta
					var tipus=promosManagerObj.getTipoDesCompte(item.tipuDescompte);
					if(item.tipuDescompte=='C1'){
						var liToAppend = "<li><a href='#' onclick=\"promosManagerObj.addPromoImport('"+item.descompteImport+"','"+item.tipuDescompte+"','"+item.id+"','esp')\" >"+initTxtPromos.txtteundescompte+"  "+item.descompteImport +" "+tipus+"</a>";
						$("#dialog_promo ul#esp").append(liToAppend);
					}else{
						var liToAppend = "<li><a href='#' onclick=\"promosManagerObj.addPromoImport('"+item.descompteImport+"','"+item.tipuDescompte+"','"+item.id+"','esp')\" >"+initTxtPromos.txtteundescomptebis+"  "+item.descompteImport +" "+tipus+"</a>";
						$("#dialog_promo ul#esp").append(liToAppend);
					}
					
				}
			});	
		}catch(error){
			console.log(error);
		}	
	},
	fillPromos: function(json){
		try{
			$.each(json, function(index,item){
				if(item.numBegudes!=null && json.numBegudes!= "0"){
					//Promocio de begudes
					var liToAppend = "<li><a href='#' onclick=\"promosManagerObj.addPromoBeguda('"+item.numBegudes+"','"+item.tipusBeguda+"','"+item.id+"','gen')\" >"+initTxtPromos.txtregalde+" "+item.numBegudes +"  "+item.tipusBeguda+"</a>";
					$("#dialog_promo ul#prm").append(liToAppend);									
				}
				if(item.descompteImport!=null && item.descompteImport!="0"){			
					//promocio descompte de pasta
					var tipus=promosManagerObj.getTipoDesCompte(item.tipuDescompte);
					if(item.tipuDescompte=='C1'){
						var liToAppend = "<li><a href='#' onclick=\"promosManagerObj.addPromoImport('"+item.descompteImport+"','"+item.tipuDescompte+"','"+item.id+"','gen')\" >"+initTxtPromos.txtteundescompte+"  "+item.descompteImport +" "+tipus+"</a>";
						$("#dialog_promo ul#prm").append(liToAppend);
					}else{
						var liToAppend = "<li><a href='#' onclick=\"promosManagerObj.addPromoImport('"+item.descompteImport+"','"+item.tipuDescompte+"','"+item.id+"','gen')\" >"+initTxtPromos.txtteundescomptebis+"  "+item.descompteImport +" "+tipus+"</a>";
						$("#dialog_promo ul#prm").append(liToAppend);
					}
			
				}
			});	
		}catch(error){
			console.log(error);
		}			
	},
	checkBegudaToAddPromo: function(promo){
		var idcomanda = $("#idcomanda").val();
		if(isNaN(promo)){console.log("No promo");}
		if(isNaN(idcomanda)){
			window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	
		}
		var data ="idComanda="+idcomanda+"&promo="+promo;
			$.ajax({
				  type: "POST",
				  url: '/'+context+'/comanda/checkBegudaToAddPromo.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
					try{  	
						if(json!=null && json.error!=null){           				
							console.log(json.error);	
						}else{
							if(json.alerta!=null){
								alertOnline.alertes(json.alerta);       					
							}else{
								try{
									var numBegudes=0;
									var numBegudesPromo=0;           					
									var preuBegudes = 0.0;
									var html="";
									var begudes = json.begudes;       					          				       					
			 
									var table=document.getElementById("order");
									var lis="";
									$.each(begudes, function(index, value){ 
																		
										numBegudesPromo=numBegudesPromo+value.numBegudesPromo;

										numBegudes= numBegudes+value.numBegudes;

										preuBegudes= validationsOBJ.getFloatParsed2(preuBegudes)+validationsOBJ.getFloatParsed2(value.beguda.preu)*value.numBegudes;           					
																	
										$('#beguda_p_'+value.beguda.id).remove();
									
										$('#beguda_'+value.beguda.id).remove();   					   					 		
											
											if(value.numBegudes!=0){
												var row=table.insertRow(1);
												
												$(row).addClass("selector_pl");
												row.id="beguda_"+value.beguda.id;
												
												var cell1=row.insertCell(0);
												var cell2=row.insertCell(1);
												var cell3=row.insertCell(2);
												var cell4=row.insertCell(3);
												var cell5=row.insertCell(4);
												var cell6=row.insertCell(5);
												
												
												$(cell1).addClass("img_order");
												cell1.innerHTML="<img width='114px' src='"+context+"/comanda/ImageAction.action?imageId="+value.beguda.foto.id+"' />";
												
												
												$(cell2).addClass("descri");
												cell2.innerHTML="<span class='tit'>"+value.beguda.nom+"</span><br>"+changeHTML(value.beguda.descripcio)+"</td>";
												
												
												$(cell3).addClass("preusun");
												cell3.innerHTML="<label id='begudapreu_"+value.beguda.id+"' >"+value.beguda.preu+"</label>&euro;";
												
												
												$(cell4).addClass("canti");
												cell4.innerHTML="<input class='mores' type='submit' onclick='begudaOBJ.saveBegudaToComanda(this,"+value.beguda.id+",false,-1);' value='-'><label id='labelnum_b_"+value.beguda.id+"'>"+value.numBegudes+"</label><input class='mores' type='submit' onclick='begudaOBJ.saveBegudaToComanda(this,"+value.beguda.id+",false,1);' value='+'>";
												
													
												$(cell5).addClass("total");
												cell5.innerHTML="<label id='labelpreutotal_b_"+value.beguda.id+"'>"+validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(value.beguda.preu)*validationsOBJ.getFloatParsed2(value.numBegudes))+"</label> &euro;";
												
												
												$(cell6).addClass("elimi");
												cell6.innerHTML="<input class='elimin' type='submit' onclick='begudaOBJ.eliminaBeguda(this,"+value.beguda.id+")'  value='ELIMINAR'>";
												
												var li= value.numBegudes+" <span class='plats' id='span_b_"+value.beguda.id+"'>x</span> "+changeHTML(value.beguda.nom)+"<br><br>";
												lis = lis+li;
											}
											if(value.numBegudesPromo!=0){
												var row=table.insertRow(1);
												
												
												$(row).addClass("selector_pl");
												row.id="beguda_p_"+value.beguda.id;
												var list = window.localStorage.getItem("comanda.promo.begudes.list");
												if(list == 'undefined' || list == null){
													window.localStorage.setItem("comanda.promo.begudes.list",value.beguda.id);
												}else{
													window.localStorage.setItem("comanda.promo.begudes.list",list+","+value.beguda.id);
												}
												
												var cell1=row.insertCell(0);
												var cell2=row.insertCell(1);
												var cell3=row.insertCell(2);
												var cell4=row.insertCell(3);
												var cell5=row.insertCell(4);
												var cell6=row.insertCell(5);
												
												
												$(cell1).addClass("img_order");
												cell1.innerHTML="<img width='114px' src='"+context+"/comanda/ImageAction.action?imageId="+value.beguda.foto.id+"' />";
												
												
												$(cell2).addClass("descri");
												cell2.innerHTML="<span class='tit'>"+value.beguda.nom+"</span><br>"+changeHTML(value.beguda.descripcio)+"</td>";
												
												
												$(cell3).addClass("preusun");
												cell3.innerHTML="<label id='begudapreu_p_"+value.beguda.id+"' >"+value.beguda.preu+"</label>&euro;";
												
												
												$(cell4).addClass("canti");
												cell4.innerHTML="<label id='labelnum_b_p_"+value.beguda.id+"'>"+value.numBegudesPromo+"</label>";
												
													
												$(cell5).addClass("total");
												cell5.innerHTML="<label id='labelpreutotal_b_p_"+value.beguda.id+"'>0</label> &euro;";
												
												
												$(cell6).addClass("elimi");
												cell6.innerHTML="<input class='elimin' type='submit' onclick='promosManagerObj.deletePromoApplied();'  value='"+initParams.txtbottreurepromo+"'>";
												
												var li= value.numBegudesPromo+" <span class='plats' id='span_b_"+value.beguda.id+"'>x</span> "+changeHTML(value.beguda.nom)+"(PROMO)<br><br>";
												lis = lis+li;
											}       				    					 	   					 		      					 		           					       						 		       					 
									});
									window.localStorage.setItem("comanda.begudes.lis",lis);
									window.localStorage.setItem("comanda.promo.nBegudes.added",numBegudesPromo);
									window.localStorage.setItem("comanda.numbegudes",numBegudes);
									window.localStorage.setItem("comanda.beguda.preu",preuBegudes);           					           				
									var preuComanda = window.localStorage.getItem("comanda.preu");
									
									var transportOnTheFly=generalManagerObj.getTransPortOnTheFly();	
									var preuFinal = validationsOBJ.getFloatParsed2(preuComanda) + validationsOBJ.getFloatParsed2(preuBegudes)+ validationsOBJ.getFloatParsed2(transportOnTheFly);
									$("#preu").text(preuFinal);
									$("#labelpreutotalPromo").text(preuFinal);       					
								}catch(error){
									console.log(error);
								}
						}   
				  }
				}catch(error){
					console.log(error);
				}	
			},
				  error: function(e){  window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	
									}
			});	
	
	},
	addPromoBeguda: function(nbegudes, tipusBeguda,id,tipus){
		if(isNaN(id) || isNaN(nbegudes) || nbegudes==0) return;
		var numBegudes = window.localStorage.getItem("comanda.numbegudes");
	
		window.localStorage.setItem("comanda.promo.id",id);
		window.localStorage.setItem("comanda.promo.beguda","true");
		window.localStorage.setItem("comanda.promo.nBegudes.total",nbegudes);
		window.localStorage.setItem("comanda.promo.beguda.tipus",tipusBeguda);
		window.localStorage.setItem("comanda.promo.tipus",tipus);
		
		window.promoBeguda= {promo : "true"};
		window.localStorage.setItem("comanda.promo.nBegudes.added",0);
		window.promoBeguda.numBegudes=parseInt(nbegudes);
		window.promoBeguda.tipusBeguda=tipusBeguda;
		
		$("#checkPromocionsDisponibles").hide();
		$("#deletePromoApplied").show();
		promosManagerObj.closeDialogPromos();
		
		if(numBegudes!=null){		  
			promosManagerObj.checkBegudaToAddPromo(id);				
		}
		
		alertOnline.alertes(initParams.txtAddDrinkstoBox);
	}, 
	initPromoBegudaFromStorage: function(){
		var isPromo =  window.localStorage.getItem("comanda.promo.beguda");
		if(isPromo==null){
			window.promoBeguda=null;	
		}else{
			
			var nbegudes= window.localStorage.getItem("comanda.promo.nBegudes.total");	
			var tipoBeguda = window.localStorage.getItem("comanda.promo.beguda.tipus");
			var id = window.localStorage.getItem("comanda.promo.id");
			var tipus = window.localStorage.getItem("comanda.promo.tipus");
			if(nbegudes!=null &&  tipoBeguda != null  && id != null && tipus != null){
				promosManagerObj.addPromoBeguda(nbegudes, tipoBeguda,id,tipus);
				var nbegudesAdded = window.localStorage.getItem("comanda.promo.nBegudes.added");
				window.promoBeguda.numBegudesAdded = nbegudesAdded;
				$("#numbegudespromo").text(nbegudesAdded);
			}			
		}
	}, 
	initPromoDescompteFromStorage: function(){
		var isPromo =  window.localStorage.getItem("comanda.promo.descompte");
	
		if(isPromo==null){
			window.promoDescompte=null;	
		}else{
			
			var importe= window.localStorage.getItem("comanda.promo.descompte.import");	
			var tipoDescompte = window.localStorage.getItem("comanda.promo.descompte.tipus");
			var id = window.localStorage.getItem("comanda.promo.id");
			var tipus = window.localStorage.getItem("comanda.promo.tipus");
			if( importe!=null && tipoDescompte != null){
				promosManagerObj.addPromoImport(importe, tipoDescompte,id, tipus);		
			}			
		}
	}, 
	addPromoImport: function (importDescompte, tipusDescompte,id,tipus){
		window.localStorage.setItem("comanda.promo.id",id);
		window.localStorage.setItem("comanda.promo.descompte","true");
		window.localStorage.setItem("comanda.promo.descompte.import",importDescompte);
		window.localStorage.setItem("comanda.promo.descompte.tipus",tipusDescompte);
		window.localStorage.setItem("comanda.promo.tipus",tipus);
		
		window.promoDescompte= {promo : "true"};
		window.promoDescompte.importe= importDescompte;
		window.promoDescompte.tipus=tipusDescompte;
		
		//Actualitzem les dadesde la cart
		var preuPlats = window.localStorage.getItem("comanda.preu");
		
		var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
		if(preuBegudes=='undefined' || preuBegudes==null){
			preuBegudes=0.0;
			window.localStorage.setItem("comanda.beguda.preu","0.0");
		}
		var transportOnTheFly=generalManagerObj.getTransPortOnTheFly();		
		var preu =  validationsOBJ.getFloatParsed2(preuPlats)+ validationsOBJ.getFloatParsed2(preuBegudes);
		
		if(tipusDescompte=='1' || tipusDescompte=='C1' ){
			var preuF = validationsOBJ.getFloatParsed2(preu)*((100-validationsOBJ.getFloatParsed2(importDescompte))/100);
			$("#promoImp").text("-"+importDescompte+" %");
			$("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(preuF)+validationsOBJ.getFloatParsed2(transportOnTheFly));
		}else{
			var preuF = validationsOBJ.getFloatParsed2(preu)-validationsOBJ.getFloatParsed2(importDescompte);
			$("#promoImp").text("-"+importDescompte+" Euros");
			$("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(preuF)+ validationsOBJ.getFloatParsed2(transportOnTheFly));
		}
		
		$("#checkPromocionsDisponibles").hide();
		$("#deletePromoApplied").show();
		promosManagerObj.closeDialogPromos();
	}, 
	deletePromoApplied: function(){
		try{
			var isPromoBeguda = window.localStorage.getItem("comanda.promo.beguda");
			var isPromoDescompte = window.localStorage.getItem("comanda.promo.descompte");
			window.localStorage.removeItem("comanda.promo.tipus");
			if( isPromoDescompte!=null){
				
				window.localStorage.removeItem("comanda.promo.descompte");
				window.localStorage.removeItem("comanda.promo.descompte.import");
				window.localStorage.removeItem("comanda.promo.descompte.tipus");
				window.localStorage.removeItem("comanda.promo.id");
				
				window.promoDescompte=null;
								
				var preuPlats = window.localStorage.getItem("comanda.preu");
				var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
				var transportOnTheFly=generalManagerObj.getTransPortOnTheFly();
				
				var preu =  validationsOBJ.getFloatParsed2(preuPlats)+ validationsOBJ.getFloatParsed2(preuBegudes)+ validationsOBJ.getFloatParsed2(transportOnTheFly);
				
				$("#promoImp").text("");
				$("#labelpreutotalPromo").text(preu);
				
			}
			if(isPromoBeguda!='undefined' && isPromoBeguda!=null){
				
				window.localStorage.removeItem("comanda.promo.beguda");
				window.localStorage.removeItem("comanda.promo.nBegudes.total");
				window.localStorage.removeItem("comanda.promo.beguda.tipus");
				window.localStorage.removeItem("comanda.promo.id");
				
				var list = window.localStorage.getItem("comanda.promo.begudes.list");
				if(list!=null){
					var array = list.split(",");
					$.each(array, function(index, value){
						$('#beguda_p_'+value).remove();			
					});
				}
				window.promoBeguda= null;
				
				$("#numbegudespromo").text("0");
				
				promosManagerObj.deleteAjaxBegudesPromo();
			}
			$("#deletePromoApplied").hide();
			$("#checkPromocionsDisponibles").show();
		}catch(error){
			console.log(error);
		}	
	}, 
	deleteAjaxBegudesPromo: function(){
		var comandaId = window.localStorage.getItem("comanda");
		if(comandaId!= null){

			var data ="idComanda="+comandaId;

			$.ajax({
				  type: "POST",
				  url: '/'+context+'/comanda/deleteBegudesPromo.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
						try{
							if(json!=null && json.error!=null){	       					       				
								console.log(json.error); 
							} 
						   var lis= window.localStorage.getItem("comanda.begudes.lis");
						   var lista="";
						   var begudes = lis.split("<br><br>");
							$.each(begudes, function(index, value) { 		       					 	
									 if(value.indexOf("(PROMO)")==-1)
										 lista=lista+value+"<br><br>";		       										       						
							});

							window.localStorage.setItem("comanda.begudes.lis",lista);
						}catch(error){
							console.log(error);
						}
					 
				  },
				  error: function(e){  window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action"; 		
									}
				});
		}	
	}, 
	openDialogPromos: function(){
		$("#dialog_promo").dialog("open"); 
	},
	closeDialogPromos: function(){
		$("#dialog_promo").dialog("close"); 
	},
	getTipoDesCompte: function(tipuDescompte){
		if(tipuDescompte=="C1"){
			return "%";
		}else{
			return "&euro;";
		}
	}
};

//OBJ for general managment
var generalManagerObj ={
};
generalManagerObj._self=null;


generalManagerObj ={
	
	_init: function (){
		this._self =this;
	},
	getTransPortOnTheFly: function(){
		try{
			var transportOnTheFly=0;
			if($("#adomicili").is(':checked')){
						if(morethanone=="true"){       						 
							transportOnTheFly=transportPreuDouble;
						}else{
							transportOnTheFly=transportPreu;
						}
			} 
			return transportOnTheFly;
		}catch(error){
			console.log(error);
		}
	},
	addDomicili: function(){
		try{
			 var preu = $("#preu").text();
			 var preuT = $("#labelpreutotalPromo").text();
			 $("#arecollir_div").hide('slow'); 
			if($("#adomicili").is(':checked')){		
				
				 $("#arecollir").attr('checked',false);		
				 if(morethanone=="true"){
					 $("#transport_lb").text(transportPreuDouble);
					 $("#preu").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preu)+validationsOBJ.getFloatParsed2(transportPreuDouble)));		 
					 $("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preuT)+validationsOBJ.getFloatParsed2(transportPreuDouble)));
					 
				 }else{
					 $("#transport_lb").text(transportPreu);
					 $("#preu").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preu)+validationsOBJ.getFloatParsed2(transportPreu)));		 
					 $("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preuT)+validationsOBJ.getFloatParsed2(transportPreu)));			 
				 }
				 promosManagerObj.initPromoDescompteFromStorage();
				 $("#adomicili_div").show('slow');
				 $("#pagarDom").show('slow');
				 $("#pagarRec").hide('slow');
				 
				 
			}else{
				 $("#adomicili").attr('checked',true);	
				
			}
			horesManagerObj.reloadHores();
		}catch(error){
			console.log(error);
		}
	},
	targeta: function(){
		try{
			$("#targetaContrarembols").val("targeta");
			$("#targeta").attr('checked',true);
			$("#contrarembols").attr('checked',false);
			$("#botopagarcomanda").removeAttr('disabled');
		}catch(error){
			console.log(error);
		}
	},
	contrarembols: function(){
		try{		
			$("#targetaContrarembols").val("contrarembols");
			$("#contrarembols").attr('checked',true);
			$("#targeta").attr('checked',false);
			$("#botopagarcomanda").removeAttr('disabled');
		}catch(error){
			console.log(error);
		}
	},
	addRecollir: function(){
		try{
			if($("#arecollir").is(':checked')){	
			var adomiciliChecked =$("#adomicili").is(':checked');
			$("#adomicili").attr('checked',false);		
			var idcomanda= window.localStorage.getItem("comanda");
			var data ="idComanda="+idcomanda;
			$.ajax({
				  type: "POST",
				  url: '/'+context+'/comanda/ajaxLoadInfoARecollir.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
					  if(json!=null && json.error!=null){
						console.log(json.error);	
					  }else{
						  if(json.moreThanOne=='true' || json.moreThanOne==true){	       				  
							alertOnline.alertes(initParams.txtnomesdomocili);		       			
							return;
						  }
						 if(adomiciliChecked){			       			 
							 $("#adomicili_div").hide('slow');	
							 $("#pagarDom").hide('slow');
							 $("#pagarRec").show('slow');
							 var preu = $("#preu").text();
							 var preuT = $("#labelpreutotalPromo").text();
							 $("#transport_lb").text("0");
							 if(morethanone=="true"){
								$("#preu").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preu)-validationsOBJ.getFloatParsed2(transportPreuDouble)));
								 $("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preuT)-validationsOBJ.getFloatParsed2(transportPreuDouble)));
							 }else{
								$("#preu").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preu)-validationsOBJ.getFloatParsed2(transportPreu)));
								 $("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preuT)-validationsOBJ.getFloatParsed2(transportPreu)));
							 }
							 
							 promosManagerObj.initPromoDescompteFromStorage();
						 }
						 $("#targetaContrarembols").val("targeta");
						 $("#address_restaurant").text(json.address);
						 window.localStorage.setItem("restaurant.address",json.address);
						 $("#arecollir_div").show('slow');
						 horesManagerObj.reloadHores();	
					  }				
				  },
				  error: function(e){ window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	
									}
				});							
			}else{
				$("#arecollir").attr('checked',true);
			}
		}catch(error){
			console.log(error);
		}
	},
	deleteAndGoToIni: function(){
		window.localStorage.clear();
	window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";
	}
};





//OBJ for managment of hours
var horesManagerObj ={
};
horesManagerObj._self=null;


horesManagerObj ={
	
	_init: function (){
		this._self =this;
	},
	reloadHores: function(){
		var dia = $("#dia").val();
		window.localStorage.setItem("comanda.data",dia);
		var comanda = window.localStorage.getItem("comanda");
		if(comanda==null) 
			window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";
			
		var data ="data="+dia+"&idComanda="+comanda+"&aDomicili="+comandaOBJ.getAdomicili();
		
		$.ajax({
			  type: "POST",
			  url: '/'+context+'/comanda/loadHores.action',
			  dataType: 'json',
			  data: data,
			  success: function(json){	
				  if(json!=null && json.error!=null){
					console.log(json.error);	
				  }else{
					$("#comandahora").val("");
					/*if(json._0800=='true'){_self.setHourCheck('0800');}else{_self.setHourNotCheck('0800'); }  
					if(json._0830=='true'){_self.setHourCheck('0830');}else{_self.setHourNotCheck('0830'); }  
					if(json._0900=='true'){_self.setHourCheck('0900');}else{_self.setHourNotCheck('0900'); }  				  
					if(json._0930=='true'){_self.setHourCheck('0930');}else{_self.setHourNotCheck('0930'); }  
					if(json._1000=='true'){_self.setHourCheck('1000');}else{_self.setHourNotCheck('1000'); }
					if(json._1030=='true'){_self.setHourCheck('1030');}else{_self.setHourNotCheck('1030'); }  				  
					if(json._1100=='true'){_self.setHourCheck('1100');}else{_self.setHourNotCheck('1100'); }  
					if(json._1130=='true'){_self.setHourCheck('1130');}else{_self.setHourNotCheck('1130'); }    				  
					if(json._1200=='true'){_self.setHourCheck('1200');}else{_self.setHourNotCheck('1200'); }
					if(json._1230=='true'){_self.setHourCheck('1230');}else{_self.setHourNotCheck('1230'); }  				 
					if(json._1300=='true'){_self.setHourCheck('1300');}else{_self.setHourNotCheck('1300'); }  				 
					if(json._1330=='true'){_self.setHourCheck('1330');}else{_self.setHourNotCheck('1330'); }  				 
					if(json._1400=='true'){_self.setHourCheck('1400');}else{_self.setHourNotCheck('1400'); }
					if(json._1430=='true'){_self.setHourCheck('1430');}else{_self.setHourNotCheck('1430'); }  				 
					if(json._1500=='true'){_self.setHourCheck('1500');}else{_self.setHourNotCheck('1500'); }
					if(json._1530=='true'){_self.setHourCheck('1530');}else{_self.setHourNotCheck('1530'); }
					if(json._1600=='true'){_self.setHourCheck('1600');}else{_self.setHourNotCheck('1600'); }
					if(json._1630=='true'){_self.setHourCheck('1630');}else{_self.setHourNotCheck('1630'); }
					if(json._1700=='true'){_self.setHourCheck('1700');}else{_self.setHourNotCheck('1700'); }
					if(json._1730=='true'){_self.setHourCheck('1730');}else{_self.setHourNotCheck('1730'); }
					if(json._1800=='true'){_self.setHourCheck('1800');}else{_self.setHourNotCheck('1800'); }
					if(json._1830=='true'){_self.setHourCheck('1830');}else{_self.setHourNotCheck('1830'); }
					if(json._1900=='true'){_self.setHourCheck('1900');}else{_self.setHourNotCheck('1900'); }
					if(json._1930=='true'){_self.setHourCheck('1930');}else{_self.setHourNotCheck('1930'); }*/
					if(json._2000=='true'){horesManagerObj._self.setHourCheck('2000');}else{horesManagerObj._self.setHourNotCheck('2000'); }
					if(json._2030=='true'){horesManagerObj._self.setHourCheck('2030');}else{horesManagerObj._self.setHourNotCheck('2030'); }
					if(json._2100=='true'){horesManagerObj._self.setHourCheck('2100');}else{horesManagerObj._self.setHourNotCheck('2100'); }
					if(json._2130=='true'){horesManagerObj._self.setHourCheck('2130');}else{horesManagerObj._self.setHourNotCheck('2130'); }
					if(json._2200=='true'){horesManagerObj._self.setHourCheck('2200');}else{horesManagerObj._self.setHourNotCheck('2200'); }
					if(json._2230=='true'){horesManagerObj._self.setHourCheck('2230');}else{horesManagerObj._self.setHourNotCheck('2230'); }
					if(json._2300=='true'){horesManagerObj._self.setHourCheck('2300');}else{horesManagerObj._self.setHourNotCheck('2300'); }
					if(json._2330=='true'){horesManagerObj._self.setHourCheck('2330');}else{horesManagerObj._self.setHourNotCheck('2330'); }
					if(json._2400=='true'){horesManagerObj._self.setHourCheck('2400');}else{horesManagerObj._self.setHourNotCheck('2400'); }
				  }				
			  },
			  error: function(e){  	window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";
								}
			});	
	},
	checKHour: function(id){
		if($("#"+id).hasClass("notcheck")){
			return;
		}
		var id_old = $("#comandahora").val();
		
		$("#"+id_old).removeClass("checked");
		$("#"+id_old).addClass("check");
		
		$("#"+id).removeClass("check");
		$("#"+id).addClass("checked");
		
		$("#comandahora").val(id);
	},
	setHourNotCheck: function(id){
		$("#"+id).removeClass("check");
		$("#"+id).removeClass("checked");
		$("#"+id).addClass("notcheck");
	},
	setHourCheck: function(id){
		$("#"+id).addClass("check");
		$("#"+id).removeClass("checked");
		$("#"+id).removeClass("notcheck");
	},
	setHourChecked: function(id){
		$("#"+id).removeClass("check");
		$("#"+id).addClass("checked");
		$("#"+id).removeClass("notcheck");	
	}
};


//OBJ for managment of menu restaurant 
var menuRestaurantAction ={
};
menuRestaurantAction._self=null;


menuRestaurantAction ={
	
	_init: function (){
		this._self =this;
	},
	goToRestaurantMenu: function(id){
		if(isNaN(id)){window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	}
		var comanda = window.localStorage.getItem("comanda");
		var data = window.localStorage.getItem("comanda.data");
		var comandaConfirm = menuRestaurantAction.getConfirm();
		
		if( comanda != null && comandaConfirm == null){
			var day = new Date();
			window.localStorage.setItem("comanda.confirm",day.getTime());
			menuRestaurantAction.acceptComandaDialog();
		}else{
			window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+id+"&data="+data;
		}
	},
	getConfirm: function(){
		var comandaConfirm = window.localStorage.getItem("comanda.confirm");
		
		if( comandaConfirm!=null){
			var currentDay = new Date();
			if((currentDay.getTime()-comandaConfirm)>60*confirmTime){
				window.localStorage.removeItem("comanda.confirm");
			}
		}
		return window.localStorage.getItem("comanda.confirm");
	},
	actionCloseConfirm: function(){
		var data = window.localStorage.getItem("comanda.data");
		var idRestaurant = window.localStorage.getItem("comanda.restaurant");
		if(idRestaurant==null) return;
		window.localStorage.clear();	
		window.localStorage.setItem("comanda.data",data);
		window.localStorage.setItem("comanda.restaurant",idRestaurant);
		window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&data="+data;
	},
	acceptComandaDialog: function(){
		menuRestaurantAction.confirmComanda();
	},
	confirmComanda: function(){
		var comanda = window.localStorage.getItem("comanda");
		var idRestaurant = window.localStorage.getItem("comanda.restaurant");
		if(idRestaurant==null) return;
		if($("#list_rest_"+idRestaurant).hasClass("tancat")){
			alertOnline.alertes(initParams.txtavisrestauranttancat);	
		}
		if(comanda != null){
			var data = window.localStorage.getItem("comanda.data");
			window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&idComanda="+comanda+"&data="+data;
		}
	}
};

//OBJ for dishes managment 
var platsOBJ ={
};
platsOBJ._self=null;



platsOBJ ={
	
	_init: function (){
		this._self =this;
	},
	savePlatToComandaMesMenys: function(self,idPlat,nPlats){
		var idcomanda= window.localStorage.getItem("comanda");
		if(idcomanda==null)return;
		if(isNaN(idPlat) ){ console.log("id not a number"); return;}
		if(isNaN(nPlats) ){ nPlats=1;}
		var data ="idPlat="+idPlat+"&idComanda="+idcomanda+"&nplats="+nPlats;
		$.ajax({
			  type: "POST",
			  url: '/'+context+'/comanda/ajaxLoadNumPlat.action',
			  dataType: 'json',
			  data: data,
			  success: function(json){	
				  if(json!=null && json.error!=null){
					console.log(json.error);	
				  }else{				
					  $(self).removeAttr("disabled");					
					  if(json!=null && (json.morethanone=='true'||json.morethanone=='false'  )){
						  if($("#adomicili").is(':checked') && morethanone!=json.morethanone){								  
						  						  							 																		 
								 if(morethanone=="true" && json.morethanone=='false'){
									 $("#transport_lb").text(transportPreu);
									 var preu =  $("#preu").text();
									 var preuT = $("#labelpreutotalPromo").text();
									 $("#preu").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preu)-validationsOBJ.getFloatParsed2(transportPreuDouble)+validationsOBJ.getFloatParsed2(transportPreu)));		 
									 $("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preuT)-validationsOBJ.getFloatParsed2(transportPreuDouble)+validationsOBJ.getFloatParsed2(transportPreu)));										 
								 }
								 morethanone= json.morethanone;																		 									 								
						  }
					  }	
					  	
				  }			
			  },
			  error: function(e){  window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	
								}
		});	
	},
	saveNewPLatAmount: function(self,id,value){
		try{
			if(isNaN(id) || isNaN(value)){ console.log("id o value not a number"); return;}
			 $(self).attr("disabled","disabled");
			 var preuPlat = $("#platpreu_"+id).text();
			 var preu = $("#preu").text();
			 var numPlatsAnt = window.localStorage.getItem("comanda.plat_"+id);
		 
			 if( numPlatsAnt != null){
				 
				 var nPlatsAdded = parseInt(value);
				 var platsTotalPlat = parseInt(numPlatsAnt)+parseInt(value);
				 if(platsTotalPlat==0){
					$("#plat_"+id).remove();
				 }		
				 $("#labelnum_"+id).text(parseInt(numPlatsAnt)+parseInt(value));
				 window.localStorage.setItem("comanda.plat_"+id,parseInt(numPlatsAnt)+parseInt(value));
				 
				 var preuToAdd = validationsOBJ.getFloatParsed2(preuPlat)*nPlatsAdded;
				 var preuActual = $("#labelpreutotal_"+id).text();
				 var newPreu = preuToAdd+validationsOBJ.getFloatParsed2(preuActual);
				 $("#labelpreutotal_"+id).text(validationsOBJ.getFloatParsed2(newPreu));
				 
				 var preuCom = validationsOBJ.getFloatParsed2(preu)+preuToAdd;
				 $("#preu").text(validationsOBJ.getFloatParsed2(preuCom));
				 $("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(preuCom));
				 
				 var preuPlats =  window.localStorage.getItem("comanda.preu");
				 window.localStorage.setItem("comanda.preu",validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preuPlats)+ validationsOBJ.getFloatParsed2(preuToAdd)));
				 
				 promosManagerObj.initPromoDescompteFromStorage();
				 
				 //Guardem els plats afegits
				 platsOBJ.savePlatToComandaMesMenys(self,id,nPlatsAdded);
				 var platsAnterior = window.localStorage.getItem("comanda.numplats");	
				 var platsAra= parseInt(platsAnterior)+parseInt(nPlatsAdded);
				 window.localStorage.setItem("comanda.numplats",platsAra);       			
					if(platsAra<=0){
						alertOnline.alertes(initTxtPromos.txtnoplats);
						generalManagerObj.deleteAndGoToIni();
					}
					var lis= window.localStorage.getItem("comanda.plats.lis");
					var lista="";
					var plats = lis.split("<br><br>");
						$.each(plats, function(index, value) { 		       					 	
								 if(value.indexOf("span_p_"+id)==-1){
									 if(value!=''){
										 lista=lista+value+"<br><br>";	 
									 }								 		       
									 }else{
										 if(platsAra!=0 && parseInt(platsTotalPlat)>0){
											 var liEnd= value.split("</span>");									 
											 var li= window.localStorage.getItem("comanda.plat_"+id)+" <span class='plats' id='span_p_"+id+"'>x</span> "+liEnd[1]+"<br><br>";
											 lista=lista+li;
										 }
									 }																								
						});
						
						window.localStorage.setItem("comanda.plats.lis",lista);
			
			}
		}catch(error){
			console.log(error);
		}
	},
	eliminaPlat: function(id){
		try{
			 window.localStorage.setItem("comanda.plat_"+id,"0");
			 var n = $("#labelnum_"+id).text();
			 $("#labelnum_"+id).text("0");	
			 var preuTotalPlat = $("#labelpreutotal_"+id).text();
			 $("#labelpreutotal_"+id).text("0");
			 var preu =  $("#preu").text();
			 var preuComanda = validationsOBJ.getFloatParsed2(preu)-validationsOBJ.getFloatParsed2(preuTotalPlat);
			 var preuInStorage = window.localStorage.getItem("comanda.preu");
			 if(preuInStorage!=null){
				window.localStorage.setItem("comanda.preu",validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preuInStorage)- validationsOBJ.getFloatParsed2(preuTotalPlat)));
			 }
			 $("#preu").text(validationsOBJ.getFloatParsed2(preuComanda));
			 $("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(preuComanda));
			 promosManagerObj.initPromoDescompteFromStorage();
			 platsOBJ.savePlatToComanda(id,-n); 
			var platsAnterior = window.localStorage.getItem("comanda.numplats");	
			var platsAra= parseInt(platsAnterior)-parseInt(n);
			window.localStorage.setItem("comanda.numplats",platsAra);       			
				if(platsAra<=0){
					alertOnline.alertes(initTxtPromos.txtnoplats);
					generalManagerObj.deleteAndGoToIni();
				}
			 $('#plat_'+id).remove();	 
				var lis= window.localStorage.getItem("comanda.plats.lis");
				var lista="";
				var plats = lis.split("<br><br>");
					$.each(plats, function(index, value) { 		       					 	
							 if(value.indexOf("span_p_"+id)==-1)
								 lista=lista+value+"<br><br>";		       										       						
					});

					window.localStorage.setItem("comanda.plats.lis",lista);
		}catch(error){
			console.log(error);
		}
	},
	savePlatToComanda: function(idPlat,nPlats){
			
			var idcomanda= window.localStorage.getItem("comanda");
			if(idcomanda==null || isNaN(idPlat))return;
			if(isNaN(nPlats))nPlats=1;
			var data ="idPlat="+idPlat+"&idComanda="+idcomanda+"&nplats="+nPlats;
			$.ajax({
				  type: "POST",
				  url: '/'+context+'/comanda/ajaxLoadNumPlat.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
					  if(json!=null && json.error!=null){
						console.log(json.error);	
					  }else{
						  if(json!=null && (json.morethanone=='true'||json.morethanone=='false'  )){
							  if($("#adomicili").is(':checked') && morethanone!=json.morethanone){								  
							  						  							 																		 
									 if(morethanone=="true" && json.morethanone=='false'){
										 $("#transport_lb").text(transportPreu);
										 var preu =  $("#preu").text();
										 var preuT = $("#labelpreutotalPromo").text();
										 $("#preu").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preu)-validationsOBJ.getFloatParsed2(transportPreuDouble)+validationsOBJ.getFloatParsed2(transportPreu)));		 
										 $("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(preuT)-validationsOBJ.getFloatParsed2(transportPreuDouble)+validationsOBJ.getFloatParsed2(transportPreu)));										 
									 }
									 morethanone= json.morethanone;																		 									 								
						  }
					  }		
					  } 
				  },
				  error: function(e){  window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	
									}
			});	
	}
};

//OBJ for comanda management 
var comandaOBJ ={
};
comandaOBJ._self=null;

comandaOBJ ={
	
	_init: function (){
		this._self =this;
		try{
			$(document).ready(function() {
				var comanda = window.localStorage.getItem("comanda");		
				if( comanda != null){
					$("#numComanda").text(comanda);								
					var preu = window.localStorage.getItem("comanda.preu");
					if (preu != null) {
						var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
						if (preuBegudes != 'undefined' && preuBegudes != null) {
							preu =  validationsOBJ.getFloatParsed2(preu) + validationsOBJ.getFloatParsed2(preuBegudes);
						}
						$("#preu").text(validationsOBJ.getFloatParsed2(preu));			
						$("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(preu));
					}
					var numplats = window.localStorage.getItem("comanda.numplats");
					if(numplats == null){
						numplats=0;
						window.localStorage.setItem("comanda.numplats","0");
					}				
					var numbegudes = window.localStorage.getItem("comanda.numbegudes");
					if( numbegudes == null){
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
					console.log("No comanda");
					window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";					
				}
				
				promosManagerObj.initPromoBegudaFromStorage();
				promosManagerObj.initPromoDescompteFromStorage();
				
				window.localStorage.removeItem("plats.order");
				
				$("#arecollir").attr('checked',false);
				$("#adomicili").attr('checked',true);
				generalManagerObj.addDomicili();
			});
			}catch(error){
				console.log(error);
			}
		
	},
	initNumBegudes: function(){
		var numbegudes = window.localStorage.getItem("comanda.numbegudes");
		if (numbegudes != null) {
			$("#numbegudes").text(numbegudes);
		}else{
			$("#numbegudes").text("0");
		}	
	},
	checkComandaJS: function(){
		try{
			$("#botopagarcomanda").attr('disabled','disabled');
			var comanda = window.localStorage.getItem("comanda");
			var plats = window.localStorage.getItem("comanda.numplats");			
			if(plats<=0){
				alertOnline.alertes(initTxtPromos.txtnoplats);
				$("#botopagarcomanda").removeAttr('disabled');
				generalManagerObj.deleteAndGoToIni();
				return;
			}									
			if(comanda!= null){
				var hora = $("#comandahora").val();				
				
				if(hora==''){
					alertOnline.alertes(initParams.txtfaltahora);
					$("#botopagarcomanda").removeAttr('disabled');
					return;
				}				
				window.localStorage.setItem("comanda.hora",hora);
				var adomicili =comandaOBJ.getAdomicili();
				var address = $("#comandaddress").val();				
				
				if(adomicili){										
					if(address==''){
						alertOnline.alertes(initParams.txtcheckaddress);
						$("#botopagarcomanda").removeAttr('disabled');
						return;
					}
					address =  address.replace(/\n/g, "");
					window.localStorage.setItem("comanda.address",address);
				}else{
					address=  window.localStorage.getItem("restaurant.address");
					window.localStorage.removeItem("comanda.address");
				}
				var dia = $("#dia").val();
				var targeta =comandaOBJ.getTargeta();
				var promoId = window.localStorage.getItem("comanda.promo.id");
				var tipusPromo = window.localStorage.getItem("comanda.promo.tipus");				
				
				address = comandaOBJ.encode(address,false);
				var indicacions =$("#altres").val();			
				indicacions = comandaOBJ.encode(indicacions,true);
				var data ="idComanda="+comanda+"&dia="+dia+"&hora="+hora+"&aDomicili="+adomicili+"&targeta="+targeta+"&address="+address+"&promoId="+promoId+"&tipusPromo="+tipusPromo+"&indicacions="+indicacions;
				$.ajax({
					  type: "POST",
					  url: '/'+context+'/comanda/checkComanda.action',
					  dataType: 'json',
					  data: data,
					  success: function(json){	
							if(json==null || json.error!=null){
								console.log(json.error);								
							}else{
								if(json.alertLoged!=null){
									alertOnline.alertes(json.alertLoged);		
									$("#botopagarcomanda").removeAttr('disabled');
									return;
								}
								if(json.alerta!=null){
									alertOnline.alertes(json.alerta);	
									$("#botopagarcomanda").removeAttr('disabled');									
									return;
								}	       				
								if(json.comandaOK !=null ){
									alertOnline.alertes(json.comandaOK);	       					
									$("#checkPromocionsDisponibles").show();
									comandaOBJ.payComanda();
									return;
								}	       						       		
							}	  			  		  			  		  			  	
					  },
					  error: function(e){   window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	
										}
					});
			}
		}catch(error){
			console.log(error);
		}	
	}, 
	encode: function(txt,especial){
			if(typeof txt === "undefined" || txt=='')return "";
			
			txt = txt.replace(/[\u00A0-\u00FF]/g, function(c) {
				return '#'+c.charCodeAt(0)+';';
			});
			if(especial==true){
				txt = txt.replace(/"/g, function(c) {
					return '#34;';
				});

				txt = txt.replace(/&/g, function(c) {
					return '#38;';
				});
			}
			return txt;
	},
	getTargeta: function(){
		var targetaContrarembols = $("#targetaContrarembols").val();
		if(targetaContrarembols=='contrarembols'){
					return false;
		}else if (targetaContrarembols=='targeta'){
					return true;
		}else{
					return true;
		}
	},
	getAdomicili: function(){
		if($("#adomicili").is(':checked')){					
			return true;			
		}else{
			return false;
		}
	},
	payComanda: function(){
		var comanda = window.localStorage.getItem("comanda");
		if(comanda!= null){
				var subdata = promosManagerObj.checkPromoImport();
				var data ="idComanda="+comanda + subdata;
				var addres = window.localStorage.getItem("comanda.address");
				var addresRestaurant = window.localStorage.getItem("restaurant.address");
				var hora = window.localStorage.getItem("comanda.hora");
				window.localStorage.clear();
				window.localStorage.setItem("comanda.address",addres);
				window.localStorage.setItem("comanda.hora",hora);
				window.localStorage.setItem("restaurant.address",addresRestaurant);
				window.location.href="/"+context+"/payment/paymentEntry.action?"+data;
		}		
	}
};	
	
//OBJ for drinks management 
var begudaOBJ ={
};
begudaOBJ._self=null;


begudaOBJ ={
	
	_init: function (){
		this._self =this;
	},
	eliminaBeguda: function(self,id){
		try{
			var n = $("#labelnum_b_"+id).text();
			$("#labelnum_b_"+id).text("0");	
			var preuTotalBeguda = $("#labelpreutotal_b_"+id).text();
			$("#labelpreutotal_b_"+id).text("0");
			var preu =  $("#preu").text();
			var preuComanda = validationsOBJ.getFloatParsed2(preu)-validationsOBJ.getFloatParsed2(preuTotalBeguda);
			$("#preu").text(validationsOBJ.getFloatParsed2(preuComanda));
			$("#labelpreutotalPromo").text(validationsOBJ.getFloatParsed2(preuComanda));
			begudaOBJ.saveBegudaToComanda(self,id,false,-n); 
			var lis= window.localStorage.getItem("comanda.begudes.lis");
			var lista="";
			var begudes = lis.split("<br><br>");
					$.each(begudes, function(index, value) { 		       					 	
							 if(value.indexOf("span_b_"+id)==-1)
								 lista=lista+value+"<br><br>";		       										       						
					});

			window.localStorage.setItem("comanda.begudes.lis",lista);
		}catch(error){
			console.log(error);
		}	
	},
	saveBegudaToComanda: function (self,idBeguda,promo,amount){
		try{
				if(typeof idBeguda === "undefined" || idBeguda=='' || idBeguda==null ) return;
				if(isNaN(amount)) amount=1;
				$(self).attr("disabled","disabled");		
				var data ="idBeguda="+idBeguda+"&idComanda="+$("#idcomanda").val()+"&amount="+amount+"&promo="+promo;
				$.ajax({
					  type: "POST",
					  url: '/'+context+'/comanda/ajaxLoadBeguda.action',
					  dataType: 'json',
					  data: data,
					  success: function(json){	
						  if(json!=null && json.error!=null){           				
								console.log(json.error);	
							}else{
								if(json.alerta!=null){
									alertOnline.alertes(json.alerta);       					
								}else{
									var numBegudes=0;
									var numBegudesPromo=0;           					
									var preuBegudes = 0.0;
									var html="";
									var begudes = json.begudes;
									
										$('#beguda_p_'+idBeguda).remove();
									
										$('#beguda_'+idBeguda).remove();
									
			 
									var table=document.getElementById("order");
										var lis="";
									
									$.each(begudes, function(index, value){ 
																		
										numBegudesPromo=numBegudesPromo+value.numBegudesPromo;

										numBegudes= numBegudes+value.numBegudes;

										preuBegudes=  validationsOBJ.getFloatParsed2(preuBegudes) + validationsOBJ.getFloatParsed2(value.beguda.preu)*value.numBegudes;           					
																	
										
											
											if(value.numBegudes!=0){
												if(value.beguda.id==idBeguda){
													var row=table.insertRow(1);
													
													$(row).addClass("selector_pl");
													row.id="beguda_"+value.beguda.id;
													
													var cell1=row.insertCell(0);
													var cell2=row.insertCell(1);
													var cell3=row.insertCell(2);
													var cell4=row.insertCell(3);
													var cell5=row.insertCell(4);
													var cell6=row.insertCell(5);
													
													
													$(cell1).addClass("img_order");
													cell1.innerHTML="<img width='114px' src='"+context+"/comanda/ImageAction.action?imageId="+value.beguda.foto.id+"' />";
													
													
													$(cell2).addClass("descri");
													cell2.innerHTML="<span class='tit'>"+value.beguda.nom+"</span><br>"+changeHTML(value.beguda.descripcio)+"</td>";
													
													
													$(cell3).addClass("preusun");
													cell3.innerHTML="<label id='begudapreu_"+value.beguda.id+"' >"+value.beguda.preu+"</label>&euro;";
													
													
													$(cell4).addClass("canti");
													cell4.innerHTML="<input class='mores' type='submit' onclick='begudaOBJ.saveBegudaToComanda(this,"+value.beguda.id+",false,-1);' value='-'><label id='labelnum_b_"+value.beguda.id+"'>"+value.numBegudes+"</label><input class='mores' type='submit' onclick='begudaOBJ.saveBegudaToComanda(this,"+value.beguda.id+",false,1);' value='+'>";
													
														
														
													
													$(cell5).addClass("total");
													cell5.innerHTML="<label id='labelpreutotal_b_"+value.beguda.id+"'>"+validationsOBJ.getFloatParsed2(validationsOBJ.getFloatParsed2(value.beguda.preu)*validationsOBJ.getFloatParsed2(value.numBegudes))+"</label> &euro;";
													
													
													$(cell6).addClass("elimi");
													cell6.innerHTML="<input class='elimin' type='submit' onclick='begudaOBJ.eliminaBeguda(this,"+value.beguda.id+")'  value='ELIMINAR'>";
												}
												var li= value.numBegudes+" <span class='plats' id='span_b_"+value.beguda.id+"'>x</span> "+changeHTML(value.beguda.nom)+"<br><br>";
												lis = lis+li;
											}
											if(value.numBegudesPromo!=0){
												if(value.beguda.id==idBeguda){
													var row=table.insertRow(1);																										
													$(row).addClass("selector_pl");
													row.id="beguda_p_"+value.beguda.id;
													var list = window.localStorage.getItem("comanda.promo.begudes.list");
													if(list == null){
														window.localStorage.setItem("comanda.promo.begudes.list",value.beguda.id);
													}else{
														window.localStorage.setItem("comanda.promo.begudes.list",list+","+value.beguda.id);
													}
													
													var cell1=row.insertCell(0);
													var cell2=row.insertCell(1);
													var cell3=row.insertCell(2);
													var cell4=row.insertCell(3);
													var cell5=row.insertCell(4);
													var cell6=row.insertCell(5);
													
													
													$(cell1).addClass("img_order");
													cell1.innerHTML="<img width='114px' src='"+context+"/comanda/ImageAction.action?imageId="+value.beguda.foto.id+"' />";
													
													
													$(cell2).addClass("descri");
													cell2.innerHTML="<span class='tit'>"+value.beguda.nom+"</span><br>"+changeHTML(value.beguda.descripcio)+"</td>";
													
													
													$(cell3).addClass("preusun");
													cell3.innerHTML="<label id='begudapreu_p_"+value.beguda.id+"' >"+value.beguda.preu+"</label>&euro;";
													
													
													$(cell4).addClass("canti");
													cell4.innerHTML="<label id='labelnum_b_p_"+value.beguda.id+"'>"+value.numBegudesPromo+"</label>";
													
														
													$(cell5).addClass("total");
													cell5.innerHTML="<label id='labelpreutotal_b_p_"+value.beguda.id+"'>0</label> &euro;";
													
													
													$(cell6).addClass("elimi");
													cell6.innerHTML="<input class='elimin' type='submit' onclick='promosManagerObj.deletePromoApplied();'  value='"+initParams.txtbottreurepromo+"'>";
												}
												var li= value.numBegudesPromo+" <span class='plats' id='span_b_"+value.beguda.id+"'>x</span> "+changeHTML(value.beguda.nom)+"(PROMO)<br><br>";
												lis = lis+li;
											
										}       					 	   					 		      					 		           					       						 		       					 	
									});
									window.localStorage.setItem("comanda.begudes.lis",lis);
									window.localStorage.setItem("comanda.promo.nBegudes.added",numBegudesPromo);
									window.localStorage.setItem("comanda.numbegudes",numBegudes);
									window.localStorage.setItem("comanda.beguda.preu",preuBegudes);           					           				
									var preuComanda = window.localStorage.getItem("comanda.preu");
									
									var transportOnTheFly=generalManagerObj.getTransPortOnTheFly();	
									
									var preuFinal = validationsOBJ.getFloatParsed2(preuComanda) + validationsOBJ.getFloatParsed2(preuBegudes)+ validationsOBJ.getFloatParsed2(transportOnTheFly);
									$("#preu").text( validationsOBJ.getFloatParsed2(preuFinal));
									$("#labelpreutotalPromo").text( validationsOBJ.getFloatParsed2(preuFinal));
									promosManagerObj.initPromoDescompteFromStorage();
									$(".mores").removeAttr("disabled");
							}   
					  }
					
			  },
			  error: function(e){ window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	
								}
			});
		}catch(error){
						console.log(error);
		}			
	},
};	
//OBJ for validations management 
var validationsOBJ ={
};
validationsOBJ._self=null;


validationsOBJ ={
	
	_init: function (){
		this._self =this;
	},
	getFloatParsed2: function(vartoparse){
		try{
			
			var vartoparseTransitorio = vartoparse.replace(",",".");
			var parsedFloat = parseFloat(vartoparseTransitorio).toFixed(2);
			return parseFloat(parsedFloat);
		}catch(error){
						if(!isNaN(vartoparse) && vartoparse!=""){return parseFloat(parseFloat(vartoparse).toFixed(2));}else{return 0.0;};
		}				
	},
};


var func = $(function(){
	
	$( ".draggable" ).click(function() {
		
		var dragBeguda =$(this).clone();
		dragBeguda.appendTo("#preus");
		
		dragBeguda.animate({
							    width: "90%",
							    opacity: 0.4, 
							    marginLeft: "0.6in",
							    fontSize: "3em",
							    borderWidth: "10px",
							    left: "+=250px"
	  						}, 1800,function() {
	  								var id = $(this).attr("id");
	  								var tipus = $(this).attr("title");
	  								addingBegudaMangaer(this,id,tipus);
	      							$(this).css("visiblity","hidden");
	      							$(this).css("display","none");
	    					});
	  	
	});
	
	function addingBegudaMangaer(self,id,tipus){
	
		var plats = window.localStorage.getItem("comanda.numplats");
		//if no dishes go to inici
		if(isNaN(plats)){
			window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";	
			return;
		}
		
			if(window.promoBeguda == null || window.promoBeguda.promo!="true" ){	
				begudaOBJ.saveBegudaToComanda(self,id,false,1);
			}else{
				var n = window.localStorage.getItem("comanda.promo.nBegudes.added");
				if(parseInt(n) < window.promoBeguda.numBegudes){
	    			if(tipus!= window.promoBeguda.tipusBeguda){
	    				alertOnline.alertes(initParams.txtBegudaNoPromocio);	
	    				return;
	    			}
	    			//Afegim beguda al contador
	    			window.localStorage.setItem("comanda.promo.nBegudes.added", parseInt(n) +1);
	    			begudaOBJ.saveBegudaToComanda(self,id,true,1);
	    		}else{
	    			begudaOBJ.saveBegudaToComanda(self,id,false,1);
	    		}
			}
	}
});
//---------------------------------------------------------------------------------------------------------------------
    Calendar.setup({
        inputField    	:    "dia",      // id del campo de texto
        ifFormat       	:    "%Y-%m-%d",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llencadorData1",          // el id del botón que lanzará el calendario
        locale 		   	:    "ca_ES"
    });
//---------------------------------------------------------------------------------------------------------------------

//inizialize objects
addressManagerObj._init();
promosManagerObj._init();
generalManagerObj._init();
horesManagerObj._init();
menuRestaurantAction._init();
platsOBJ._init();
comandaOBJ._init();
begudaOBJ._init();
validationsOBJ._init();
new Address.addressValidation();

//init slider
var sudoSlider = $("#slider").sudoSlider({
    autowidth:false,
    slideCount:3,
    continuous:true
});

//init dialog promocions
$("#dialog_promo").dialog( { autoOpen: false,
	  modal: true,
	  position: 'center',
	  draggable: true,
	  height: 390,
	  width: 600,		
	  open: function(event, ui) { 
		 //carrega la taula del dialog
		  promosManagerObj.checkPromocionsDisponibles(); 
		  promosManagerObj.checkPromoEspecial();
		$('#dialog_promo').css('visibility', '');
		$('#dialog_promo').css('overflow', 'hidden');
	 }
});
