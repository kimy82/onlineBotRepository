
(function() {
	
	
    Ext.apply(Ext.util.Format,{
        eurMoney : function(v) {
            v = (Math.round((v-0)*100))/100;
            v = (v == Math.floor(v)) ? v + ".00" : ((v*10 == Math.floor(v*10)) ? v + "0" : v);
            v = String(v);
            var ps = v.split('.'),
                whole = ps[0],
                sub = ps[1] ? '.'+ ps[1] : '.00',
                r = /(\d+)(\d{3})/;
            while (r.test(whole)) {
                whole = whole.replace(r, '$1' + ',' + '$2');
            }
            v = whole + sub;
            if (v.charAt(0) == '-') {
                return '-' + v.substr(1);
            }
            
            var s = v.split('.');
            s[0] = s[0].replace(',','.');
            
            v=s[0]+","+s[1];                       
            
            return   v;
        }
    });
})();

Ext.chart.Chart.CHART_URL = '/'+context+'/swf/charts.swf';

Ext.onReady(function(){
	
    var store = new Ext.data.JsonStore({
        fields: ['nom', 'numUses', 'numUsed']
    });
    store.loadData(myData);
    new Ext.Panel({
        width: 900,
        height: 200,
        renderTo: 'container',
        title: '',
        items: {
            xtype: 'barchart',
            store: store,
            yField: 'nom',
            xAxis: new Ext.chart.NumericAxis({
                stackingEnabled: true,                
                labelRenderer: Ext.util.Format.eurMoney
            }),
            series: [{         
                xField: 'numUses',
                displayName: initGraficParams.txtnumUses
            },{
                xField: 'numUsed',
                displayName: initGraficParams.txtnumUsed
            }]
        }
    });
});

function showDates(id){
	var data= "idPromocio="+id;
	$.ajax({
		  type: "POST",
		  url: '/'+context+'/admin/showDates.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json!=null ){
				  var storeDates = new Ext.data.JsonStore({
				        fields: ['date', 'numUsed']
				    });
				  	storeDates.loadData(json);
				    new Ext.Panel({
				        width: 900,
				        height: 200,
				        renderTo: 'containerDates',
				        title: '',
				        items: {
				        	 xtype: 'linechart',
				             store: storeDates,
				             yField: 'numUsed',
				             xField: 'date'
				        }
				    });
			  }	
		  },
		  error: function(e){   errorOnline.error(txterrorAjax);	
		  					}
		});	
}

///////////////////////////////////
//variables per textos en locale
var initGraficParams=null ;
function InitGraficParams(txtnumUses,txtnumUsed){		
		this.txtnumUses=txtnumUses;		
		this.txtnumUsed=txtnumUsed;
}