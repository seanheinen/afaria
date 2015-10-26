var GetSettings = {
    getSettings: function(params, successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'getSettings', // mapped to our native Java class called "Calendar"
            'fetch', // with this action name
            [params]
        ); 
     }
}

module.exports = GetSettings;
