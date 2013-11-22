package net.tomlins.smsgobbler.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "SMS_RECEIVER";
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context _context, Intent _intent) {

        Log.i(TAG, "SMS received");

        if(_intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = _intent.getExtras();

            if(bundle!=null) {
                Object[] pdus = (Object[])bundle.get("pdus");
                StringBuffer smsMessageText = new StringBuffer();
                String sender = "";

                for(int index=0; index<pdus.length; index++) {
                    SmsMessage message = SmsMessage.createFromPdu((byte[])pdus[index]);
                    sender = message.getOriginatingAddress();
                    smsMessageText.append(message.getMessageBody());
                }

                if(smsMessageText.length()>0) {
                    this.abortBroadcast();
                    Toast.makeText(_context.getApplicationContext(),
                            "SMS Received from " + sender + " says " +
                                    smsMessageText.toString(), Toast.LENGTH_LONG).show();
                }
            }

        }
    }

}
