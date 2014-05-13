package com.nidama.study.data;

import java.util.ArrayList;

public class NPCStatusSpeaking extends NPCStatus {
	boolean isRandom;
	int currentWordIndex;
	ArrayList<String> listWords=new ArrayList<String>();
	public NPCStatusSpeaking(int status,int nextStatus)
	{
		super(status,nextStatus);
		currentWordIndex=0;
	}
	public void addWords(String words){
		listWords.add(words);
	}
	public void setRandomSpeacking()
	{
		isRandom=true;
	}
	public String getWords()
	{
		if(listWords.size()==0)return null;
		if(isRandom)
		{
			java.util.Random random=new java.util.Random();
			currentWordIndex=random.nextInt(listWords.size());
		}
		else
			currentWordIndex=currentWordIndex%listWords.size();
		return listWords.get(currentWordIndex++);
	}
}
