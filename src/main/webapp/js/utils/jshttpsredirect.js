//Browser var
var _browser = {};
var browserController = {
			_init: function(){
				this._self=this;
				_browser.firefox=false;
				_browser.chrome=false;
				_browser.safari=false;
				_browser.opera=false;
				_browser.msie=false;
				_browser.version=null;
			},
			detectBrowser: function(){
				var uagent = navigator.userAgent.toLowerCase();
	
			    _browser.firefox = /mozilla/.test(uagent) && /firefox/.test(uagent);
			    _browser.chrome = /webkit/.test(uagent) && /chrome/.test(uagent);
			    _browser.safari = /applewebkit/.test(uagent) && /safari/.test(uagent) 
			                                                    && !/chrome/.test(uagent);
			    _browser.opera = /opera/.test(uagent);
			    _browser.msie = /msie/.test(uagent);
			    _browser.version = '';
	
			    for (x in _browser) {
			        if (_browser[x]) {            
			            _browser.version = uagent.match(new RegExp("(" + x +
			                                                           ")( |/)([0-9]+)"))[3];
			            break;
			        }
			    }
			},
			redirect: function(){
				if(_browser.firefox ){

					if(_browser.version<20){
						window.location.href="https://www.portamu.com/";
						return;
					}
				}
				if(_browser.safari){
					
					if(_browser.version<534){		
						window.location.href="https://www.portamu.com/";
						return;
					}
				}
				if(_browser.opera){

					if(_browser.version<9){
						window.location.href="https://www.portamu.com/";
						return;
					}
				}
				if(_browser.chrome){						
						return;
				}
				if(_browser.msie){
					if(_browser.version<9){
						window.location.href="https://www.portamu.com/";
						return;
					}
					
				}
			}
};


browserController._init();
browserController.detectBrowser();
browserController.redirect();

if (window.location.protocol != "https:" && entorn!="local"){
		window.location.href="https://www.portamu.com/elteurestaurantacasa/Welcome.action";
}