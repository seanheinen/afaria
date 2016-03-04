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

			// get settings
			String result = "";
			if (action.equals("getSettings")) {						
				result = this.getSettings(args);
			} 
			else {
				callbackContext.error("Invalid method: " + action);
				return false;
			}
						
			// handle response
			//if(result.substring(0, 6).compareTo("ERROR")){
				callbackContext.success(result);
			//} else {
		//		callbackContext.error(result);
		//	}
			return true;			

		} catch(Exception e) {
			callbackContext.error("ERROR 1: " + e.toString());
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
	    	return "ERROR 2: " + ex.toString();
	    	//throw new RuntimeException(ex);		        
	    }
	  }
 		catch(Exception e)
    {
      //Fix this disgraceful crash      
      return "ERROR 3: " + e.toString();

      //Does this need to be here?
      //keyValues = new java.util.HashMap<String, String>();
	  }		
		return fileContents;
	}

}
