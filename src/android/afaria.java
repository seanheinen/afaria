package gov.capetown.afaria;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class Afaria extends CordovaPlugin {
		
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	 		
		try 
		{	
			boolean result;
			
			if ("getSettings".equals(action)){
				result = this.getSettings(args, callbackContext);
			} else {
				//Log.e(LOG_TAG, "Invalid action");
				result = false;
			}
			
			
			// handle response
			if(result == true){
				callbackContext.success("Success");
			} else {
				callbackContext.success("Error");
			}
			return result;           

		} catch(Exception e) {
			callbackContext.error(e.toString());
		    return false;
		} 
	}
	
	private boolean getSettings(JSONArray args, CallbackContext callbackContext){
		boolean result;
		try {

			
			result = true;			
		} catch (Exception e){
			
			result = false;
		}
		return result;
	}

}
