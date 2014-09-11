package com.finndigital.zol1401a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
//import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.util.DisplayMetrics;
//import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.finndigital.zol1401a.R;

public class ResultsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Show the Up button in the action bar.
				setupActionBar();
		setContentView(R.layout.activity_results);
		
		
		Bundle extras = getIntent().getExtras();
		//choiceListView = (ListView) findViewById(R.id.choiceListView);vgetResources().getString(R.string.terms_of_use_body);
		TextView title = (TextView) findViewById(R.id.txtTitle);
		TextView body = (TextView) findViewById(R.id.txtResultBody);
		
		int score = extras.getInt("score");
		String factors = extras.getString("riskFactors");
		String strTitle;
		String strBody;
		if (score < 3) {
			strTitle = getResources().getString(R.string.results_low_title);
			strBody = getResources().getString(R.string.results_low_text);
		}else if (score < 6) {
			strTitle = getResources().getString(R.string.results_elevated_title);
			strBody = getResources().getString(R.string.results_elevated_text);
		}else{
			strTitle = getResources().getString(R.string.results_high_title);
			strBody = getResources().getString(R.string.results_high_text);
		}
		
		title.setText(strTitle);
		strBody += "<br/>" + factors;
		body.setText(Html.fromHtml(strBody));
	}

	
	public void onClickBackToStart(View v) {
		Intent intent = new Intent(this, StartActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	

	public void onClickReferences(View v) {
		Point p = new Point();
		p.x = 20;
		p.y = 160;
		
		//updated for deprecated code
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int pWidth = displaymetrics.widthPixels;
		int pHeight = displaymetrics.heightPixels;
		
//this is the deprecated code - getWidth and getHeight are deprecated, and replace as immediately above
//		Display display = getWindowManager().getDefaultDisplay();
//		int pWidth = display.getWidth() - 50;
//		int pHeight = display.getHeight() / 2 + 160;
		
		LinearLayout viewGroup = (LinearLayout) this.findViewById(R.id.ref_popup);
		LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = layoutInflater.inflate(R.layout.layout_references, viewGroup);
		
		// Creating the PopupWindow
		   final PopupWindow popup = new PopupWindow(ResultsActivity.this);
		   popup.setContentView(layout);
		   popup.setWidth(pWidth);
		   popup.setHeight(pHeight);
		   popup.setFocusable(true);


		   // Clear the default translucent background
		   //--replace deprecated BitmapDrawable below with ColorDrawable();
		   //popup.setBackgroundDrawable(new BitmapDrawable());
		   popup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		   TextView refTitle = (TextView) layout.findViewById(R.id.txtReferencesTitle);
		   refTitle.setText(getResources().getString(R.string.references_title));
		   
		   TextView refBody = (TextView) layout.findViewById(R.id.txtReferencesBody);
		   String str = getResources().getString(R.string.references_body);
		   refBody.setText(Html.fromHtml(str));
		   
		   // Displaying the popup at the specified location, + offsets.
		   popup.showAtLocation(v, Gravity.NO_GRAVITY, p.x, p.y);

		   // Getting a reference to Close button, and close the popup when clicked.
		   Button close = (Button) layout.findViewById(R.id.btnCloseReferences);
		   close.setOnClickListener(new OnClickListener() {

		     @Override
		     public void onClick(View pv) {
		       popup.dismiss();
		     }
		   });
	}
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
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
		getMenuInflater().inflate(R.menu.results, menu);
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

}
