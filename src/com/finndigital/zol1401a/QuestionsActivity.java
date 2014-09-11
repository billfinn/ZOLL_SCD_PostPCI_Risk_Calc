package com.finndigital.zol1401a;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
//import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.content.res.XmlResourceParser;
//import android.graphics.Point;

import java.io.IOException;
import java.util.ArrayList;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.finndigital.zol1401a.vo.QuestionObject;
import com.finndigital.zol1401a.R;

public class QuestionsActivity extends Activity {

	String[] choiceArray = { "JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY",
			 "AUG", "SEPT", "OCT", "NOV", "DEC" };
	private ListView choiceListView;
	private TextView questionTextView;
	
	private ArrayAdapter<String> arrayAdapter;
	
	private String elemName;
	private String elemValue;
	
	private ArrayList<QuestionObject> questions;
	private int currentQuestion = 0;
	private final Handler handler = new Handler();;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Show the Up button in the action bar.
		setupActionBar();
		setContentView(R.layout.activity_questions);
		
		initQuestionData();
		currentQuestion = 0;
		
		choiceListView = (ListView) findViewById(R.id.choiceListView);
		choiceListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	
		choiceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
                
            	QuestionObject curQuestion = questions.get(currentQuestion);
                curQuestion.setSelectedChoice(position);
                choiceListView.setEnabled(false);
                
                handler.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                    //Do something after 100ms
                	 autoNext();
                  }
                }, 425);
               
            }
        });
		
		questionTextView = (TextView) findViewById(R.id.txtQuestion);
		setCurrentQuestion();
		
	}
	


	private void setCurrentQuestion() {
		QuestionObject curQuestion = questions.get(currentQuestion);
		questionTextView.setText(curQuestion.getQuestion());
		String t = getResources().getString(R.string.question_tracker_label, currentQuestion+1, questions.size());
		setTitle(t);
		ArrayList<String> currentChoices = curQuestion.getChoices();
		//arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, currentChoices);
		int density = getResources().getDisplayMetrics().densityDpi;
		switch (density) {
		case DisplayMetrics.DENSITY_LOW:
			arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, currentChoices);
		    break;
		case DisplayMetrics.DENSITY_MEDIUM:
			arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, currentChoices);
		    break;
		case DisplayMetrics.DENSITY_HIGH:
			arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, currentChoices);
		    break;
		case DisplayMetrics.DENSITY_XHIGH:
			arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, currentChoices);
		    break;
		}
		String l = (density + " <----------------------");
		Log.e("ERROR",l);
		
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, currentChoices);
		choiceListView.setAdapter(arrayAdapter);
		if (curQuestion.getSelectedChoice() >= 0) {
			choiceListView.setItemChecked(curQuestion.getSelectedChoice(), true);
		}
		Button bPrev = (Button) findViewById(R.id.btnPrev);
		//Button bNext = (Button) findViewById(R.id.btnNext);
		if (currentQuestion <=0) {
			bPrev.setEnabled(false);
		}else{
			bPrev.setEnabled(true);
		}
	}
	
	public void clickPrev(View v) {
		if (currentQuestion > 0) {
			currentQuestion--;
			setCurrentQuestion();
		}
	}
	
	public void clickNext(View v) {
		if (currentQuestion+1 < questions.size()) {
			currentQuestion++;
			setCurrentQuestion();
		}else{
			doCalculate(null);
		}
	}
	
	private void autoNext() {
		if (currentQuestion+1 < questions.size()) {
			currentQuestion++;
			setCurrentQuestion();
			choiceListView.setEnabled(true);
		}else{
			doCalculate(null);
		}
	}
	
	public void doCalculate(View v) {
		int score = 0;
		int choiceValue = 0;
		String factorText = "";
	
		for (QuestionObject q : questions) {
			choiceValue =  q.getSelectionValue();
			score += choiceValue;
			if (choiceValue > 0) {
				factorText += "<br/>";
				factorText += q.getSelectionRiskText();
			}
		}
		if (factorText.equals("")) {
			factorText = "<br/>" + getResources().getString(R.string.no_risks_selected);
		}
		
		Intent intent = new Intent(this, ResultsActivity.class);
		intent.putExtra("score", score);
		intent.putExtra("riskFactors", factorText);
		startActivity(intent);
	}
	
	private void initQuestionData() {
		QuestionObject thisQuestion = null;
		questions = new ArrayList<QuestionObject>();
		elemName = "";
		elemValue = "";
		try {
			XmlResourceParser xp = this.getResources().getXml(R.xml.question_data);
			int eventType = xp.getEventType();
			
			while(eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					elemName = xp.getName();
					if (elemName.equals("questions")) {
					}
				
				}else if (eventType == XmlPullParser.TEXT) {
					this.elemValue = xp.getText();
					if (elemName.equals("question_text")) {
						if (thisQuestion != null) {
							questions.add(thisQuestion);
							
						}
						thisQuestion = new QuestionObject();
						thisQuestion.initChoiceData();
						thisQuestion.setQuestion(elemValue);
					}
					if (elemName.equals("choicetext")) {
						thisQuestion.addChoice(elemValue);
					}
					if (elemName.equals("selection_value")) {
						thisQuestion.addChoiceValue(Integer.parseInt(elemValue));
					}
					if (elemName.equals("feedback_value")) {
						thisQuestion.addChoiceFeedback(elemValue);
					}
					
				}
				
				eventType = xp.next();
			}
		}catch (XmlPullParserException e) {
			  e.printStackTrace();
		}catch (IOException e) {
			  e.printStackTrace();
		}
		questions.add(thisQuestion); // Add last one
	}
	//**************************//
	//*************************//
	
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questions, menu);
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
