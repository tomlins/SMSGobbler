package net.tomlins.smsgobbler.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "SMS_RECEIVER";
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context _context, Intent _intent) {

        SharedPreferences settings = _context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        Boolean filterOn = settings.getBoolean("filterOn", false);

        Log.i(TAG, "SMS received");

        if(_intent.getAction().equals(SMS_RECEIVED) && filterOn) {
            Bundle bundle = _intent.getExtras();

            boolean deleted = false;
            if(bundle!=null) {

                Object[] pdus = (Object[])bundle.get("pdus");
                //StringBuffer smsMessageText = new StringBuffer();
                String address = "";

                for(int index=0; index<pdus.length; index++) {
                    SmsMessage SMS = SmsMessage.createFromPdu((byte[])pdus[index]);
                    address = SMS.getOriginatingAddress();
                    if(address.contains("55555")) {
                        //deleteSMS(_context, address);
                        filterSMS(address);
                        deleted=true;
                        continue;
                    }
                    //smsMessageText.append(SMS.getMessageBody());
                }

                if(deleted) {
                    this.abortBroadcast();
                    Toast.makeText(_context.getApplicationContext(),
                            "Spam SMS deleted from " + address, Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    private void filterSMS(String address) {
        // TODO: implement filter code here
    }

    public void deleteSMS(Context context, String number) {
        Log.i(TAG, "Attempting to delete SMS from " + number);

        try {
            Uri uriSms = Uri.parse("content://sms/inbox");
            Cursor c = context.getContentResolver().query(uriSms, null, null, null, null);

            Log.i(TAG, "Scanning SMS database...");
            while(c.moveToNext()) {

                int id = c.getInt(0);
                int thread_id = c.getInt(1);
                String address = c.getString(2);

                Log.i(TAG, "Found SMS ->Sender:" + number + ", _id:" + id + ", thread_id:" + thread_id);

                if(address.equals(number)) {
                    Log.i(TAG, "Match found, deleting...");
                    context.getContentResolver().delete(
                            Uri.parse("content://sms/" + thread_id), "address=?",
                            new String[]{String.valueOf(address)});
                }
            }
        }
        catch (Exception e) {
            Log.e("log>>>", e.toString());
        }
    }
}
