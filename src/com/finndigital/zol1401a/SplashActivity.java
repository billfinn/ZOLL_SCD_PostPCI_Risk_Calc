package com.finndigital.zol1401a;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
//import android.os.Build;
import android.os.Bundle;
//import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.widget.VideoView;

public class SplashActivity extends Activity {
	VideoView videoHolder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        
		try{
	        videoHolder = new VideoView(this);
			setContentView(videoHolder);
			Uri video = Uri.parse("android.resource://" + getPackageName() + "/raw/splash");
			videoHolder.setVideoURI(video);
			videoHolder.setZOrderOnTop(true);
			
			videoHolder.setOnCompletionListener(new OnCompletionListener() {
	
				@Override
				public void onCompletion(MediaPlayer mp) {
					jump();
				}
				
			});
			videoHolder.start();
		} catch(Exception ex) {
			jump();
		}
    }
    
    
//  Uncomment this function if you want the user to be able to skip this screen
//	@Override
//    public boolean onTouchEvent(MotionEvent event) {
//	  try {
//    	videoHolder.stopPlayback();
//	  } catch(Exception ex) {}
//	  jump();
//    	return true;
//    }

	private void jump() {
		if(isFinishing())
			return;
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
}