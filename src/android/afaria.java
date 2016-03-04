package gov.capetown.afaria;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import com.sybase.afaria.*;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.provider.Settings.Secure;
import android.util.Log;

import java.util.*;

import java.io.*;

public class Afaria extends CordovaPlugin {
		
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		try {	
			boolean result;

			// get settings
			String settings = "";
			if ("getSettings".equals(action)) {

				settings = this.getSettings(args);

				if(settings.contains("ERROR")){
					callbackContext.error(settings);	
					result = true;
				} else {
					callbackContext.success(settings);	
					result = false;
				}
			} 
			else {
				callbackContext.error("Invalid method: " + action);
				result = false;				
			}
									
			return result;

		} catch(Exception ex) {
			callbackContext.error("ERROR 1: " + ex.toString());
		  return false;
		} 
	}
	
	private String getSettings(JSONArray args){
		
		String filePath;
		String fileContents = "";		
		
		try {
			
			Context context = cordova.getActivity().getApplicationContext();					
			//Context context = this.cordova.getApplicationContext();								
			SeedDataAPI.initialize(context);

	    SeedDataCredentials sdc = new SeedDataCredentials("sa-global-eqtest", "#nrolling16");	    
	    filePath = SeedDataAPI.retrieveSeedData(sdc);

	    BufferedReader reader = null;
	    Map<String, String> keyValues = null;
	    try
	    {
	    	reader = new BufferedReader(new FileReader(filePath));
	    	
        String line = null;
        keyValues = new java.util.HashMap<String, String>();		        

        while ((line = reader.readLine()) != null)
        {		            
        	fileContents = fileContents.concat(line + "\r\n");
        }
	      reader.close();

		  }
	    catch(Exception ex) {
	    	reader.close();
	    	fileContents = "ERROR 2: " + ex.toString();	    	
	    }
	  }
 		catch(Exception ex)
    {
      //Fix this disgraceful crash                  
      fileContents = "ERROR 3: " + ex.toString();	    	
	  }		
		return fileContents;
	}

}
