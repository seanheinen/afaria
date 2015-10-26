package gov.capetown.getsettings;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class GetSettings extends CordovaPlugin {
	
	public static final String ACTION_GET_SETTINGS = "getSettings";
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	 
		try 
		{
			
			if (ACTION_PUSH_GET_SETTINGS.equals(action)) {
				
				JSONObject arg_object = args.getJSONObject(0);
	            
				callbackContext.success();
				return true;
		    }
			callbackContext.error("Invalid action");
			return false;		    
			
		} catch(Exception e) {
		    
		    return false;
		} 
	}

}
