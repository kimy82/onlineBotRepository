///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtdadesCargades,txtlast,txtnext,txtprevious,txtfirst,txtloading,txtborrat){
	
	this.txtdadesCargades=txtdadesCargades;
	this.txtlast=txtlast;
	this.txtnext=txtnext;
	this.txtprevious=txtprevious;
	this.txtfirst=txtfirst;
	this.txtloading=txtloading;
	this.txtborrat=txtborrat;
}

function saveMoters(id){
	
	numMot = $("#"+id).val();
	
	 data ="id="+id+"&num="+numMot;
		$.ajax({
			  type: "POST",
			  url: '/'+context+'/admin/saveMoters.action',
			  dataType: 'json',
			  data: data,
			  success: function(json){	
				  if(json!=null && json.error!=null){
	   				$("#errorsajaxlabel").text(json.error);
	   				$("#errorsajax").show();
	   			}else{	   					   			
					alert("Saved");
	   			}				
			  },
			  error: function(e){   $("#errorsajaxlabel").text("La sessió pot haver caducat!!");
									$("#errorsajax").show();  		
			  					}
			});
}
//Variable per taula moters
var  oTableMoters=null;
var dia=null;
var giCount = 2;
//Inicialitza el calendari
$(document).ready(function() {
	now=new Date();
	selectedDate=new Date();
    document.getElementById('div_calendar').innerHTML='';
	loadCalendar("ca");		
	var txtDia =now.getDate();  
	var txtyear = now.getFullYear();
    var txtmonth = now.getMonth()+1;                         
    var dia = txtDia+'-'+txtmonth+'-'+txtyear;
	
	
	//clickDay(txtDia);
	
	//taula dels moters
	oTableMoters =$("#tbl_moters").dataTable( {
				"iDisplayLength": 22,
				 "aoColumns" : [
				                  { "mDataProp":"dia","bSortable": false, sWidth: '50px' },
				                 /* { "mDataProp":"h0800", "bSortable": false, sWidth: '50px' },
				                  { "mDataProp":"h0830", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h0900", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h0930", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1000", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1030", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1100", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1130", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1200", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1230", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1300", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1330", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1400", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1430", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1500", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1530", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1600", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1630", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1700", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1730", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1800", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1830", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1900", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h1930", "bSortable": false, sWidth: '20px' },*/
				                  { "mDataProp":"h2000", "bSortable": false, sWidth: '40px' },
				                  { "mDataProp":"h2030", "bSortable": false, sWidth: '40px' },				                  
				                  { "mDataProp":"h2100", "bSortable": false, sWidth: '40px' },
				                  { "mDataProp":"h2130", "bSortable": false, sWidth: '40px' },
				                  { "mDataProp":"h2200", "bSortable": false, sWidth: '40px' },
				                  { "mDataProp":"h2230", "bSortable": false, sWidth: '40px' },
				                  { "mDataProp":"h2300", "bSortable": false, sWidth: '40px' },
				                  { "mDataProp":"h2330", "bSortable": false, sWidth: '40px' },
				                  { "mDataProp":"h2400", "bSortable": false, sWidth: '40px' }
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
				"sScrollX": "100%",	
			    "bScrollCollapse": true,
	    		"bProcessing": false,
	    		"bServerSide": false,
	    		"sAjaxSource": '/'+context+'/admin/ajaxTableMotersAction.action',
	    		"fnServerData": function( sUrl, aoData, fnCallback) {  
	    			if(dia=='')return;
	    			aoData.push({"name":"dia", "value": dia});
	     		$.ajax( {
	            	"url": sUrl,
	            	"data": aoData,              
	            	"dataType": "json",
	            	"cache": false,
	           		"success":function(json){  
	           			if(json.error!=null){
	           				$("#errorsajaxlabel").text(json.error);
	                		$("#errorsajax").show();
	           			}else{
		            		fnCallback(json);
		            		$("#tbl_moters_paginate").hide();
	           			}            	
	            	},
	            	"error":function(e){ 
	            		$("#errorsajaxlabel").text("La sessió pot haver caducat!!");
	            		$("#errorsajax").show();            	
	            	}
	        	} );
	    	}
		} );
	
	
	
});

//borra una fila de la taula
function deleteRow(txtDia){
	
	  var year = now.getFullYear();
      var month = now.getMonth()+1;                             
      var ddmmyyyy = txtDia+'-'+month+'-'+year;
      
      var idTodelete="";
      
      $.each(oTableMoters.fnGetData(),function(indice,tr) {
    	  var dia = tr.dia;    	  
    	  if(dia==ddmmyyyy){    		  
    		  idTodelete=indice;    		  
    	  }
    	});
      
      if(idTodelete!=""){
    	  oTableMoters.fnDeleteRow(idTodelete);
      }
}

//afegeix una fila a la taula de moters
function fnClickAddRow(ddmmyyyy) {
	
		data ="dia="+ddmmyyyy;
		var n ="";
		var json="";
		$.ajax({
			  type: "POST",
			  url: '/'+context+'/admin/ajaxTableMotersAction.action',
			  dataType: 'json',
			  data: data,
			  success: function(json){	
				  if(json!=null && json.error!=null){
	   				$("#errorsajaxlabel").text(json.error);
	   				$("#errorsajax").show();
	   			}else{	   					   			
	   				var ai =oTableMoters.fnAddData( [json.aaData[0]] );
	   				n = oTableMoters.fnSettings().aoData[ ai[0] ].nTr;
	   				
	   			}				
			  },
			  error: function(e){   $("#errorsajaxlabel").text("La sessió pot haver caducat!!");
									$("#errorsajax").show();  		
			  					}
			});
		
		//	var tbody = document.getElementById("tbl_moters").tBodies[0];
		//	tbody.appendChild(n);
}

function pad(str,ch,len) { 
    if (null==str) str="";
    var ps='';
    for(var i=0; i<Math.abs(len); i++) {
		  ps+=ch;
    }
    if (len>0) {
      var concat = (str+ps);
      var max = Math.max(str.length, len);
      return concat.substring(0,max);
    }else {
      var concat = (ps+str);
      var ini = concat.length+len;
      var fin = concat.length;
      return concat.substring( ini, fin);
    }
}

//Variable que conte el calendari
var datepicker=null;




		function loadCalendar(lang){
			var local={
					days: ["Diumenge", "Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte", "Diumenge"],
					daysShort: ["diu", "dll", "dim", "dix", "dij", "div", "dis", "diu"],
					daysMin: ["diu", "dll", "dim", "dix", "dij", "div", "dis", "diu"],
					months: ["Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"],
					monthsShort: ["Gen", "Feb", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Oct", "Nov", "Des"],
					weekMin: "Set"
				};
			if(lang=="es"){
				local= {
					days: ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"],
					daysShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sab", "dom"],
					daysMin: ["dom", "lun", "mar", "mié", "jue", "vie", "sab", "dom"],
					months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
					monthsShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
					weekMin: "Sem"
				};
			}

         var year = now.getFullYear();
         var month = now.getMonth()+1;

            var oo =[];// eval('hashFiest.p'+year);
       
            if (oo!=null) oo=oo.slice(0);
            else          oo== [];

            oo=[];
            var hoy = new Date();
            var hoyYear =hoy.getFullYear();
            var hoyMonth =hoy.getMonth()+1;
            var hoyDay =hoy.getDate();    
            var hoy = hoyYear+'-'+pad(hoyMonth,'0',-2)+'-'+pad(hoyDay,'0',-2);

           

			$('#div_calendar').DatePicker({
					flat: true,
					dateOk: [hoy],
					dateNook:eval([""]),
					dateEsp: oo,
					current: year+'-'+pad(month,'0',-2)+'-01',
					format: 'Y-m-d',
					calendars: 1,
					mode: 'multiple',
					extraHeight:1,
					extraWidth:1,
					starts: 1,				
					locale:local
				});
			
			
				
					
             $(".datepickerDays tr td").addClass("hand_cursor").click(function() {
            	 	
                     
                     $(".datepickerDays tr td").removeClass("green");                     
                     if(! $(this).hasClass("datepickerNotInMonth")){
                    	 if($(this).hasClass("border_selected")){                    
                    		 $(this).removeClass("border_selected");  
                    		 deleteRow($("span",this).html());
                    		 return;
                    	 }                    	                    	
                    	 $(this).addClass("border_selected");
                         clickDay($("span",this).html());	 
                     }else{                    	 
                    	 var dia = $("span",this).html();
                    	 if(parseInt(dia)<15){
                    		 nextYear(lang);
                    	 }else{                    		
                    		 previousYear(lang);
                    	 }
                     }                    
              });
		}
		
		function clickDay(txtDia) {
			
           var year = now.getFullYear();
           var month = now.getMonth()+1;
          
           selectedDate=new Date(year, month, txtDia, 0, 0, 0, 0);                             
           var ddmmyyyy = txtDia+'-'+month+'-'+year;
           
           
           
           $("#selectedDia").val(ddmmyyyy);
           $("#datePicked").text(ddmmyyyy);
           fnClickAddRow(ddmmyyyy);    							
                                               
		}
		
	

//JS BUTTONS
ButtonPanel = Ext.extend(Ext.Panel, {
	
	id: 'panelb',
  layout:'table',
  defaultType: 'button',
  baseCls: 'x-plain',
  cls: 'btn-panel',
  renderTo : 'plantillabut',
  menu: undefined,
  split: false,

  layoutConfig: {
      columns:3
  },
  constructor: function(desc, buttons){
      // apply test configs
      for(var i = 0, b; b = buttons[i]; i++){
          b.menu = this.menu;
          b.enableToggle = this.enableToggle;
          b.split = this.split;
          b.arrowAlign = this.arrowAlign;
      }
      var items = [{
          xtype: 'box',
          autoEl: {tag: 'h3', html: desc, style:"padding:15px 0 3px;"},
          colspan: 3
      }].concat(buttons);

      ButtonPanel.superclass.constructor.call(this, {
          items: items
      });
  }
});



function nextYear(lang) {
	document.getElementById('div_calendar').innerHTML='';
	now.setDate(1);
	now.setMonth(now.getMonth() + 1);
	loadCalendar(lang);
}

function previousYear(lang) {
	document.getElementById('div_calendar').innerHTML='';
	now.setDate(1);	
	now.setMonth(now.getMonth() - 1);
	loadCalendar(lang);
}
