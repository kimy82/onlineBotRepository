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

    map.setCenter(geolocation);
    marker.setMap(map);  
    
}

var Address={
				
		 addressValidation : function(){
			var self =this;
			self._init();
			
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

Address.addressValidation.prototype._init= function(){
	this._carrer= document.getElementById("carrer");
	this._numcarrer = document.getElementById("numcarrer");
	this._codi= document.getElementById("codi");
	this._poble= document.getElementById("poble");
	this._mapdiv= $("#map_canvas");	
	this._infoLabel= $("#addressOK");
}

Address.addressValidation.prototype.checkAdd = function(){
	var address = $("#carrer").val()+","+$("#codi").val()+", Girona, Spain";
    
	if($("#carrer").val() == '' || $("#codi").val() == '' ){
		return false;
	}
	
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
                 
            	 $("#map_canvas").show();
                 Map("map_canvas", results[0].geometry.location);

                 //POsem address al form
                 $("#comandaddress").val(addressGoogle);
                 $("#addressOK").text("OK Address");
             }
    	 }else{
    		 $("#addressOK").text("KO Address");
    	 }
             
    	
    });
}


      

   