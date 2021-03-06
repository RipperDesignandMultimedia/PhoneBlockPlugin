/**
 * 	PhoneBlockPlugin.js
 * 	PhoneBlockPlugin PhoneGap plugin (Android)
 *
 * 	Created by Stephen Northcott of Ripper Design and Multimedia on 14/10/2014.
 * 	Copyright 2014 Ripper Design and Multimedia. All rights reserved.
 * 	MIT Licensed
 *
 * 	Major chunks taken from DevGeeks PhoneListener
 */

var PhoneBlockerPlugin = { 
	start: function(successCallback, failureCallback) {
		return cordova.exec(    
			successCallback,
			failureCallback,
			'PhoneBlockerPlugin',
			'startMonitoringPhoneState',
			[]); // no arguments required
	},
	stop: function(successCallback, failureCallback) {
		return cordova.exec(    
			successCallback,
			failureCallback,
			'PhoneBlockerPlugin',
			'stopMonitoringPhoneState',
			[]); // no arguments required
	}
	stopPhoneCall: function(successCallback, failureCallback) {
		return cordova.exec(    
			successCallback,
			failureCallback,
			'PhoneBlockerPlugin',
			'stopPhoneCall',
			[]); // no arguments required
	}
};
