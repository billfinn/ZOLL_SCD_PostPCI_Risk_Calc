package com.finndigital.zol1401a;


import android.os.Bundle;
//import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
//import android.widget.Button;
import android.widget.ImageButton;
import com.finndigital.zol1401a.R;


public class StartActivity extends Activity {

	ImageButton button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Show the Up button in the action bar.
		setupActionBar();

		setContentView(R.layout.activity_start);
		
//		 // Locate the button in activity_main.xml
        button = (ImageButton) findViewById(R.id.MyButton);
        
        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
 
                // Start NewActivity.class
                Intent myIntent = new Intent(StartActivity.this,
                        VideoViewActivity.class);
                startActivity(myIntent);
            }
        });

	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().hide();
		}else{
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    public void clickStart(View v) {
    	Intent intent = new Intent(this, QuestionsActivity.class);
    	startActivity(intent);
    }
    
//    public void clickVideo(View v) {
//    	Intent intent = new Intent(this, VideoViewActivity.class);
//    	startActivity(intent);
//    }
    
}
