package com.finndigital.zol1401a;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.text.Html;

//import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.content.Intent;
import com.finndigital.zol1401a.R;

public class MainActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();

        setContentView(R.layout.activity_main);
    //    getWindow().requestFeature(Window.FEATURE_NO_TITLE);
     //   getActionBar().hide();
        TextView tv = (TextView)findViewById(R.id.textView2);
     //   tv.setMovementMethod(new ScrollingMovementMethod());
        String str = getResources().getString(R.string.terms_of_use_body);
      
        tv.setText(Html.fromHtml(str));
        
        
        
		
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().hide();
		}else{
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void clickAccept(View v) {
    	Intent intent = new Intent(this, StartActivity.class);
    	startActivity(intent);
    }
    

    
}
