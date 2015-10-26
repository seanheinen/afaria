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
	 		
		try 
		{	
			String result = "";
			
			if ("getSettings".equals(action)){
				result = this.getSettings(args, callbackContext);
			} 
			callbackContext.success(result);
			
			/*else {
				//Log.e(LOG_TAG, "Invalid action");
				result = false;
			}
			
			
			// handle response
			if(result.substring(0, 6).compareTo("failed")){
				callbackContext.success("Success");
			} else {
				callbackContext.success("Error");
			}
			return result;
			*/           
			return true;
		} catch(Exception e) {
			callbackContext.error(e.toString());
		    return false;
		} 
	}
	
	private String getSettings(JSONArray args, CallbackContext callbackContext){
		
		String filePath;
		String fileContents = "";		
		
		try {
			
			Context context = this.cordova.getActivity().getApplicationContext();
			
			SeedDataAPI.initialize(context);
		    SeedDataCredentials sdc = new SeedDataCredentials("rpatel", "Jibberj@bber");
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
		    	return "failed - " + ex.toString();
		    	//throw new RuntimeException(ex);		        
		    }

		} catch (Exception e){			
			return "failed - " + e.toString();
		}
		return fileContents;
	}

}
