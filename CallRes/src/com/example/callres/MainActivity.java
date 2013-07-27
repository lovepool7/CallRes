package com.example.callres;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		Context context = getApplicationContext();
		//Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
		Context context = getApplicationContext();
		//Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_SHORT).show();
	    EndCallListener callListener = new EndCallListener();
	    callListener.onReceive(context, new Intent());
	}
	
	
	@Override
	public void onPause() {
		super.onPause();
		//Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_SHORT).show();

	}
	
	@Override
	public void onStop() {
		super.onPause();
		//Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_SHORT).show();
		finish();
	}
	
	@Override
	public void onDestroy() {
		super.onPause();
		//Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_SHORT).show();
		finish();
	}
	

	public static int LONG_PRESS_TIME = 1000; 

	final Handler _handler = new Handler(); 
	Runnable _longPressed = new Runnable() { 
	    public void run() {
	        System.exit(0);
	    }   
	};

	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    switch(event.getAction()){
	    case MotionEvent.ACTION_DOWN:
	        _handler.postDelayed(_longPressed, LONG_PRESS_TIME);
	        break;
	    case MotionEvent.ACTION_MOVE:
	        _handler.removeCallbacks(_longPressed);
	        break;
	    case MotionEvent.ACTION_UP:
	        _handler.removeCallbacks(_longPressed);
	        break;
	    }
	    return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if (keyCode == KeyEvent.KEYCODE_BACK) {
	     //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
	    	 return true;
	     }
	     if (keyCode == KeyEvent.KEYCODE_MENU){
	         return true;
	     } 
	     if (keyCode == KeyEvent.KEYCODE_HOME){
	    	 return true;
	     }
	     return super.onKeyDown(keyCode, event);    
	}

	 public void callAA(View view) {
		  Intent callIntent = new Intent(Intent.ACTION_CALL);
		  callIntent.setData(Uri.parse("tel: 8327663361"));
		  startActivity(callIntent);
		  //Toast.makeText(getApplicationContext(), "CallAA", Toast.LENGTH_SHORT).show();
		  
	 }
	 
	 public void callUS(View view) {
		Intent callIntent = new Intent(Intent.ACTION_CALL);
	    callIntent.setData(Uri.parse("tel: 4694637148"));
	    startActivity(callIntent);
	    //Toast.makeText(getApplicationContext(), "CallUS", Toast.LENGTH_SHORT).show();
		
	 }
	 
	 
 
    

	/* private void initControls()
    {
        try
        {
            volumeSeekbar = (SeekBar)findViewById(R.id.seekBar1);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));   


            volumeSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) 
                {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) 
                {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) 
                {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }*/
}

