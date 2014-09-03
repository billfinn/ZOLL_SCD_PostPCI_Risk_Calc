package com.finndigital.zol1401a;

//import android.media.MediaPlayer;
//import android.media.MediaPlayer.OnPreparedListener;
//import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
//import android.os.Build;
import android.os.Bundle;
//import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import com.finndigital.zol1401a.R;
 
public class VideoViewActivity extends Activity {
 
    // Declare variables
    ProgressDialog pDialog;
    VideoView videoview;
    Button button_back;
 
    // Insert your Video URL
    //String VideoURL = "android.resource://" + getPackageName() + "R.raw.sd";
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        
        // Get the layout from video_main.xml
        setContentView(R.layout.videoview_main);
        // Find your VideoView in your video_main.xml layout
        videoview = (VideoView) findViewById(R.id.VideoView);
        // Execute StreamVideo AsyncTask
 
        // Locate the button in activity_main.xml
        button_back = (Button) findViewById(R.id.ButtonBack);
        
        // Capture button clicks
        button_back.setOnClickListener(new OnClickListener() {
            @Override
			public void onClick(View arg0) {
 
                // Start NewActivity.class
                Intent myIntent = new Intent(VideoViewActivity.this,
                        StartActivity.class);
                startActivity(myIntent);
            }
        });
        
//        // Create a progressbar
//        pDialog = new ProgressDialog(VideoViewActivity.this);
//        // Set progressbar title
//        pDialog.setTitle("Sudden Cardiac Death Post-PCI");
//        // Set progressbar message
//        pDialog.setMessage("Buffering...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);
//        // Show progressbar
//        pDialog.show();
 
        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    VideoViewActivity.this);
            mediacontroller.setAnchorView(videoview);
            // Get the URL from String VideoURL
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/raw/sd");
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);
 
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
 
        videoview.requestFocus();
//        videoview.setOnPreparedListener(new OnPreparedListener() {
//            // Close the progress bar and play the video
//            @Override
//			public void onPrepared(MediaPlayer mp) {
//                pDialog.dismiss();
                videoview.start();
//            }
//        });
 
    }
    

    
 
}
