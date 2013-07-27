package com.example.callres;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


public class EndCallListener extends BroadcastReceiver {

	private boolean wasRinging = false;
	private boolean isCallEnded = false;
	private Context context;
	
	@Override
	public void onReceive(Context myContext, Intent myIntent) 
	{
		context = myContext;
	    System.out.println(":::called onReceiver:::");
	    TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	    telephony.listen(phoneCallListener, PhoneStateListener.LISTEN_CALL_STATE);
	}
	private final PhoneStateListener phoneCallListener = new PhoneStateListener() 
	{
	        @Override
	        public void onCallStateChanged(int state, String incomingNumber) 
	        {
	        	switch(state){
	            case TelephonyManager.CALL_STATE_RINGING:
	                 Log.i("MyListener", "RINGING");
	                 wasRinging = true;
	                 break;
	            case TelephonyManager.CALL_STATE_OFFHOOK:
	                 Log.i("MyListener", "OFFHOOK");
	                 wasRinging = true;
	                 break;
	            case TelephonyManager.CALL_STATE_IDLE:
	            	Log.i("MyListener", "IDLE");
	            	if(wasRinging == true){
	            		Log.i("MyListener", "hang up");
	            		isCallEnded = true;
	            	}
	              
	                 wasRinging = true;
	                 break;
	        }
	            if(isCallEnded)
	            {
	                isCallEnded=false;
	                Intent callIntent = new Intent(context.getApplicationContext(),MainActivity.class);
	                callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	                context.startActivity(callIntent);
	            }
	            super.onCallStateChanged(state, incomingNumber);
	        }
	    };
}