var PushUDP = {
    createEvent: function(url, port, location, successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'PushUDP', // mapped to our native Java class called "Calendar"
            'pushUDPPackets', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "title": url,                
                "title": port,                
                "Location": location                
            }]
        ); 
     }
}

module.exports = PushUDP;
