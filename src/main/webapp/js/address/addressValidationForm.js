function Map(elementId, geolocation) {
    var myOptions = {
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(document.getElementById(elementId), myOptions);
    var marker = new google.maps.Marker({
        position: geolocation,
        title:"Here is your address!"
    });
	if(42.0013>geolocation.lat() && geolocation.lat()>41.9237 && 2.7290<geolocation.lng()&& geolocation.lng()<2.8965){
		$("#map_canvas").show();
		map.setCenter(geolocation);
		marker.setMap(map); 

		//POsem address al form
        $("#comandaddress").val(Address.addressValidation.prototype.addressToSave);
        $("#addressOK").text(Address.addressValidation.prototype._addressOK);		
	}else{
		$("#addressOK").text(Address.addressValidation.prototype._addressKO);
	}
    
}

var Address={
				
		 addressValidation : function(addressOK,addressKO){
			var self =this;
			self._init(addressOK,addressKO);
			
			var button = document.getElementById("checkAdd");
			button.onclick=self.checkAdd;
			
		}			
}

Address.addressValidation.prototype._carrer=null;
Address.addressValidation.prototype._numcarrer=null;
Address.addressValidation.prototype._codi=null;
Address.addressValidation.prototype._poble=null;
Address.addressValidation.prototype._mapdiv=null;
Address.addressValidation.prototype._button=null;
Address.addressValidation.prototype._infoLabel=null;
Address.addressValidation.prototype._addressOK=null;
Address.addressValidation.prototype._addressKO=null;
Address.addressValidation.prototype.addressToSave=null;

Address.addressValidation.prototype._init= function(addressOK,addressKO){
	this._carrer= document.getElementById("carrer");
	this._numcarrer = document.getElementById("numcarrer");
	this._codi= document.getElementById("codi");
	this._poble= document.getElementById("poble");
	this._mapdiv= $("#map_canvas");	
	this._infoLabel= $("#addressOK");
	Address.addressValidation.prototype._addressOK=addressOK;
	Address.addressValidation.prototype._addressKO=addressKO;
	Address.addressValidation.prototype.addressToSave="";
}

Address.addressValidation.prototype.checkAdd = function(){
	var address = $("#carrer").val()+","+$("#codi").val()+", Girona, Spain";
	var codiPostal= $("#codi").val();
	if(codiPostal!='17001' && codiPostal!='17002' && codiPostal!='17003' && codiPostal!='17004' && codiPostal!='17005' && codiPostal!='17006' && codiPostal!='17007'){
		 $("#addressOK").text(Address.addressValidation.prototype._addressKO);
		 return false;
	}
    var addressToSave = $("#carrer").val()+"-"+codiPostal;
	if($("#carrer").val() == '' || codiPostal == '' ){
		 $("#addressOK").text(Address.addressValidation.prototype._addressKO);
		 return false;
	}
	Address.addressValidation.prototype.addressToSave=addressToSave;
	address = address.replace(/\n/g, "");
	var geocoder = new google.maps.Geocoder();
    geocoder.geocode({ 'address': address }, function (results, status) {
		
    	 if (status == google.maps.GeocoderStatus.OK) {
    		// Get the formatted Google result
             var addressGoogle = results[0].formatted_address;
             numCommas = addressGoogle.match(/,/g).length;
             if (numCommas >= 2) {

                 // Replace the first comma found with a line-break
            	 addressGoogle = addressGoogle.replace(/, /, "\n");

                 // Remove USA from the address (remove this, if this is important to you)
            	 addressGoogle = addressGoogle.replace(/, USA$/, "");
                 
            	
                 Map("map_canvas", results[0].geometry.location);

                 
             }
    	 }else{
    		 $("#addressOK").text(Address.addressValidation.prototype._addressKO);
    	 }
             
    	
    });
}


      

   