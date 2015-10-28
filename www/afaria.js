cordova.define("gov.capetown.afaria.Afaria", function(require, exports, module) { 
	var Afaria = {
	    getSettings: function(params, successCallback, errorCallback) {
	        cordova.exec(
	            successCallback, // success callback function
	            errorCallback, // error callback function
	            'Afaria', // mapped to our native Java class called "getSettings"
	            'getSettings', // with this action name
	            [params]
	        ); 
	     }
	}
	module.exports = Afaria;
});
