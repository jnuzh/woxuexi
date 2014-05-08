package com.nidama.study.data;

import com.baidu.platform.comapi.basestruct.GeoPoint;

public class NPCData {
	GeoPoint geoPoint;
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
	
	String words;
	public String getWords()
	{
		return words;
	}
	public void setWords(String words)
	{
		this.words=words;
	}

}
