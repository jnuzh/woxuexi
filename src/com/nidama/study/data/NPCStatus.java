package com.nidama.study.data;

import java.util.ArrayList;

public class NPCStatus{
	int status;
	int nextStatus;
	ArrayList<String> listWords=new ArrayList<String> ();
	public NPCStatus(int status,int nextStatus)
	{
		this.status=status;
		this.nextStatus=nextStatus;
	}
	public String getWords()
	{
		return null;
	}
}
