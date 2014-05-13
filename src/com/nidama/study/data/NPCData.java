package com.nidama.study.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.baidu.platform.comapi.basestruct.GeoPoint;

public class NPCData {
	GeoPoint geoPoint;
	int status=0;
	HashMap< Integer, NPCStatus> mapStatuses=new HashMap<Integer,NPCStatus>();
	public void addStatus(int key, NPCStatus status)
	{
		mapStatuses.put(Integer.valueOf(key),status);
	}
	public GeoPoint getGeoPoint(){
		return this.geoPoint;
	}
	public void setGeoPoint(GeoPoint geoPoint){
		this.geoPoint=geoPoint;
	}
	public void setGeoPoint(double latitude, double longitude)
	{
		this.geoPoint=new GeoPoint((int)(latitude*1e6),(int)(longitude*1e6));
	}
	public void setGeoPoint(int latitude, int longitude)
	{
		this.geoPoint=new GeoPoint(latitude,longitude);
	}
	
	String name="";
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}

	String words="";
	public String getWords()
	{
		if(mapStatuses.containsKey(Integer.valueOf(status)))
		{
			return mapStatuses.get(Integer.valueOf(status)).getWords();
		}
		return words;
	}
	public void setWords(String words)
	{
		this.words=words;
	}

}
