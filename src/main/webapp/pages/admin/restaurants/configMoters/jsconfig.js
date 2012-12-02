///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtdadesCargades){		
	this.txtdadesCargades=txtdadesCargades;
	this.txtlast=txtlast;
	this.txtnext=txtnext;
	this.txtprevious=txtprevious;
	this.txtfirst=txtfirst;
	this.txtloading=txtloading;
	this.txtborrat=txtborrat;
}
//Variable per taula moters
var  oTableMoters=null;
var dia=null;
//Inicialitza el calendari
$(document).ready(function() {
	now=new Date();
	selectedDate=new Date();
    document.getElementById('div_calendar').innerHTML='';
	loadCalendar("ca");		
	var txtDia =now.getDate();  
	dia=txtDia;
	clickDay(txtDia);
	
	//taula dels moters
	oTableMoters =$("#tbl_moters").dataTable( {
				"iDisplayLength": 22,
				 "aoColumns" : [
				                  { "mDataProp":"dia","bSortable": false, sWidth: '50px' },
				                  { "mDataProp":"h8000", "bSortable": false, sWidth: '50px' },
				                  { "mDataProp":"h8030", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h9000", "bSortable": false, sWidth: '20px' },
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
				                  { "mDataProp":"h1930", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h2000", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h2030", "bSortable": false, sWidth: '20px' },				                  
				                  { "mDataProp":"h2100", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h2130", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h2200", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h2230", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h2300", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h2330", "bSortable": false, sWidth: '20px' },
				                  { "mDataProp":"h2400", "bSortable": false, sWidth: '20px' }
				            ],
				"sPaginationType": "full_numbers",
				"oLanguage": {
					  "sProcessing": "<img src='/onlineBot/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
				      "oPaginate": {
				        "sFirst": "<img src='/onlineBot/images/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
				        "sLast": initTableParams.txtlast+"&nbsp;<img src='/onlineBot/images/icono-paginador-fin.gif' style='vertical-align:middle'>",
				        "sNext": initTableParams.txtnext+"&nbsp;<img src='/onlineBot/images/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
				        "sPrevious": "<img src='/onlineBot/images/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious
				      }
				    },
				"sScrollY": "100",		    
				"sScrollX": "152",	
			    "bScrollCollapse": true,
	    		"bProcessing": false,
	    		"bServerSide": true,
	    		"sAjaxSource": '/onlineBot/admin/ajaxTableMotersAction.action',
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
		            		("#tbl_restaurants_paginate").hide();
	           			}            	
	            	},
	            	"error":function(e){ 
	            		$("#errorsajaxlabel").text("Error in ajax call");
	            		$("#errorsajax").show();            	
	            	}
	        	} );
	    	}
		} );
	
	
});

//Funcio que s'executa quan es selecciona un restaurant del select multiple
function loadMotersAndConfig(idRestaurant){
	
	 var dia = $("#selectedDia").val();
	 data ="id="+idRestaurant+"&dia="+dia;
		$.ajax({
			  type: "POST",
			  url: '/onlineBot/admin/loadMotersAndConfig.action',
			  dataType: 'json',
			  data: data,
			  success: function(json){	
				  if(json!=null && json.error!=null){
	   				$("#errorsajaxlabel").text(json.error);
	   				$("#errorsajax").show();
	   			}else{
	   				if(json.numeroMoters==null){
	   					$("#numMoters").val("");
	   				}
	   				$("#numMoters").val(json.numeroMoters);
					if(json.obert==true){
						$("#obert").prop('checked', true);
					}else{
						$("#obert").prop('checked', false);
					}
					alert(initTableParams.txtdadesCargades);
	   			}				
			  },
			  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
									$("#errorsajax").show();  		
			  					}
			});
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
            	 	
                     $(".datepickerDays tr td").removeClass("border_selected");
                     $(".datepickerDays tr td").removeClass("green");                     
                     if(! $(this).hasClass("datepickerNotInMonth")){
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
           $("#config_rest").show('slow');          							
                                               
		}
		
		function fnClickAddRow() {
			$('#example').dataTable().fnAddData( [
				giCount+".1",
				giCount+".2",
				giCount+".3",
				giCount+".4" ] );
			
			giCount++;
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
