package com.nidama.study.ui;

import android.content.Context;

import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.nidama.study.R;

public class ViewManager {
    // 这是弹出窗口的内容部分
	static private PopupMessageView popupMessageView=null;
	static private PopupView currentPopupView=null;
	
	public ViewManager(MapView mapView, Context context)
	{
		popupMessageView=new PopupMessageView(context, mapView,R.layout.view_popup_message);
	}
	
	public void showMessage(GeoPoint clickPoint,String content,String title,Object object)
	{
		if(currentPopupView!=null)
			currentPopupView.hide();
		currentPopupView=popupMessageView;
		popupMessageView.Show(clickPoint, content, title);
	}
	public boolean hide(GeoPoint clickPoint)
	{
		return currentPopupView!=null ?currentPopupView.hideOutside(clickPoint):true;
	}	


}
