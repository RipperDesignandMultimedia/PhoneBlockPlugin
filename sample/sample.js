/**
 * 	PhoneBlockPlugin.js
 * 	PhoneBlockPlugin PhoneGap plugin (Android)
 *
 * 	Created by Stephen Northcott of Ripper Design and Multimedia on 14/10/2014.
 * 	Copyright 2011 Ripper Design and Multimedia. All rights reserved.
 * 	MIT Licensed
 *
 *  This file contains the sample javascript calls for our app.
 */
 
function onPhoneBlockStart()
{
  PhoneBlockerPlugin.start(onPhoneStateChanged,onErrorStart);
}

function onPhoneBlockStop()
{
  PhoneBlockerPlugin.stop(onSuccessStop,onError);
}

function onPhoneStateChanged(phoneState) 
{
    switch (phoneState) {
        case "RINGING":
            console.log('Phone is ringing.');
            PhoneBlockerPlugin.stopPhoneCall(onSuccessBlock,onErrorBlock);
            break;
        case "OFFHOOK":
            console.log('Phone is off the hook.');
            PhoneBlockerPlugin.stopPhoneCall(onSuccessBlock,onErrorBlock);            
            break;
        case "IDLE":
            console.log('Phone has returned to the idle state.');
            break;
        default:
            // no default...
    }
}

function onErrorStart(error) {
    console.log('PhoneBlockerPlugin failed to start.');
}

function onSuccessStop() {
    console.log('PhoneBlockerPlugin has stopped.');
}

function onErrorStop(error) {
    console.log('PhoneBlockerPlugin failed to stop.');
}

function onSuccessBlock() {
    console.log('PhoneBlockerPlugin has ended a phone call.');
}

function onErrorBlock(error) {
    console.log('PhoneBlockerPlugin has failed to end a phone call.');
}
