/**
 * 	PhoneBlockPlugin.java
 * 	PhoneBlockPlugin PhoneGap plugin (Android)
 *
 * 	Created by Stephen Northcott of Ripper Design and Multimedia on 14/10/2014.
 * 	Copyright 2011 Ripper Design and Multimedia. All rights reserved.
 * 	MIT Licensed
 *
 */
 
package com.ripperdesignandmultimedia.phoneblockplugin;

import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.internal.telephony.ITelephony;

public class PhoneBlockerPlugin extends CordovaPlugin{
	
	public static final String TYPE_NONE = "NONE";
	private static final String LOG_TAG = "PhoneBlocker";
	
	private String phoneBlockerCallbackId;

	BroadcastReceiver receiver;
	
	/**
	 * Constructor.
	 */
	public PhoneBlockerPlugin()	{
		this.receiver = null;
	}
	
	/**
	 * Sets the context of the Command. This can then be used to do things like
	 * get file paths associated with the Activity.
	 * 
	 * @param ctx The context of the main Activity.
	 */
	public void setContext(CordovaInterface ctx) {
		super.setContext(ctx);
		this.phoneBlockerCallbackId = null;
		
		// We need to listen to connectivity events to update navigator.connection
		IntentFilter intentFilter = new IntentFilter() ;
		intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
		if (this.receiver == null) {
			this.receiver = new BroadcastReceiver() {
				@Override
				public void onReceive(Context context, Intent intent) {	
					if(intent != null && intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
			            // State has changed
			            String phoneState = intent.hasExtra(TelephonyManager.EXTRA_STATE) ? intent.getStringExtra(TelephonyManager.EXTRA_STATE) : null;
			            String state;
			    		// See if the new state is 'ringing', 'off hook' or 'idle'
			            if(phoneState != null && phoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
			            	// phone is ringing, awaiting either answering or canceling
			            	state = "RINGING";
			            	Log.i(LOG_TAG,state);
			            } else if (phoneState != null && phoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
			            	// actually talking on the phone... either making a call or having answered one
			            	state = "OFFHOOK";
			            	Log.i(LOG_TAG,state);
			            } else if (phoneState != null && phoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
			            	// idle means back to no calls in or out. default state.
			            	state = "IDLE";
			            	Log.i(LOG_TAG,state);
			            } else { 
			            	state = TYPE_NONE;
			            	Log.i(LOG_TAG,state);
			            }
			            updatePhoneState(state,true);
			        }
				}
			};
			// register the receiver... this is so it doesn't have to be added to AndroidManifest.xml
			cordova.getActivity().registerReceiver(this.receiver, intentFilter);
		}
	}
		
	/**
	 * Create a new plugin result and send it back to JavaScript
	 * 
	 * @param phone state sent back to the designated success callback
	 */
	private void updatePhoneState(String phoneState, boolean keepCallback) {
		if (this.phoneBlockerCallbackId != null) {
			PluginResult result = new PluginResult(PluginResult.Status.OK, phoneState);
			result.setKeepCallback(keepCallback);
			this.success(result, this.phoneBlockerCallbackId);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.phonegap.api.Plugin#execute(java.lang.String, org.json.JSONArray, java.lang.String)
	 */
	@Override
	public PluginResult execute(String action, JSONArray args, String callbackId) {
		PluginResult.Status status = PluginResult.Status.INVALID_ACTION;
		String result = "Unsupported Operation: " + action;	
		// either start or stop the listener...
		if (action.equals("startMonitoringPhoneState")) {
			if (this.phoneBlockerCallbackId != null) {
        		return new PluginResult(PluginResult.Status.ERROR, "Phone listener already running.");
        	}
			this.phoneBlockerCallbackId = callbackId;
			PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
			pluginResult.setKeepCallback(true);
			return pluginResult;
		}
		else if (action.equals("stopMonitoringPhoneState")) {
			removePhoneBlocker();
            		this.updatePhoneState("", false); // release status callback
            		this.phoneBlockerCallbackId = null;
        		return new PluginResult(PluginResult.Status.NO_RESULT);
		}
		else if (action.equals("stopPhoneCall")) {
			onReceive();
		}
		
		return new PluginResult(status, result); // no valid action called
	}
	
	/**
     	* Stop the phone listener receiver and set it to null.
     	*/
    	private void removePhoneBlocker() {
        	if (this.receiver != null) {
            		try {
                		this.ctx.getContext().unregisterReceiver(this.receiver);
                		this.receiver = null;
            		} catch (Exception e) {
                		Log.e(LOG_TAG, "Error unregistering phone listener receiver: " + e.getMessage(), e);
            		}
        	}
    	}
	
	/**
	 * Stop phone listener receiver.
	 */
	public void onDestroy() {
		removePhoneBlocker();
	}
	
	public void onReceive() {
		try {
			TelephonyManager localTelephonyManager;
          		Method localMethod = Class.forName(localTelephonyManager.getClass().getName()).getDeclaredMethod("getITelephony", new Class[0]);
          		localMethod.setAccessible(true);
          		this.telephonyService = ((ITelephony)localMethod.invoke(localTelephonyManager, new Object[0]));
          		this.telephonyService.endCall();
          		return;
		} catch (Exception e) {
			Log.e(LOG_TAG, "Couldn't Block Call: " + e.getMessage(), e);	
		}	
	}
}
