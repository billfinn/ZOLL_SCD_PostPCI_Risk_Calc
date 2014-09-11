package com.finndigital.zol1401a.vo;

import java.util.ArrayList;

public class QuestionObject {

	private String question;
	private ArrayList<String> choices;
	private ArrayList<Integer> choiceValues;
	private ArrayList<String> choiceResultText;
	private int selectedChoice = -1;
	
	public QuestionObject() {
		
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String q) {
		this.question = q;
	}
	
	public ArrayList<String> getChoices() {
		return choices;
	}
	
	public void setChoices(ArrayList<String> c) {
		this.choices = c;
	}

	
	public void initChoiceData() {
		choices = new ArrayList<String>();
		choiceValues = new ArrayList<Integer>();
		choiceResultText = new ArrayList<String>();
	}
	
	public void addChoice(String c) {
		choices.add(c);
	}
	
	public ArrayList<Integer> getChoiceValues() {
		return this.choiceValues;
	}
	
	public void addChoiceValue(int value) {
		choiceValues.add(value);
	}
	
	public void addChoiceFeedback(String value) {
		choiceResultText.add(value);
	}
	
	public int getSelectedChoice() {
		return selectedChoice;
	}
	public void setSelectedChoice(int value) {
		selectedChoice = value;
	}
	
	public int getSelectionValue() {
		int value = 0;
		if (selectedChoice >= 0) {
			value = choiceValues.get(selectedChoice);
		}
		return value;
	}
	
	public String getSelectionRiskText() {
		String value = "";
		if (selectedChoice >= 0) {
			value = "- " + choiceResultText.get(selectedChoice);
		}
		return value;
	}

	@Override
	public String toString() {
		return "Qustion = " + question;
	}
}
