package com.nidama.study.ui;

import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.Projection;
import com.nidama.study.R;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PopupMessageView extends PopupView {
	private String message=null;
	private String title=null;
	public void setMessage(String message)
	{
		this.message=message;
	}
	public void setTitle(String title)
	{
		this.title=title;
	}

	public PopupMessageView(Context context,MapView mapView,int layoutId)
	{
		super(context,mapView);
		this.setLayoutId(layoutId);
	}
	public boolean Show(GeoPoint clickPoint,String message,String title){
		this.setMessage(message);
		this.setTitle(title);
		return super.Show(clickPoint,null);
	}
	protected boolean createPopupView() {
		if(message==null||!super.createPopupView())return false;
    	TextView txtMessage = (TextView) popupView.findViewById(R.id.popup_message_content);
        txtMessage.setText(message);

        if(title!=null){
        	TextView txtTitle = (TextView) popupView.findViewById(R.id.popup_message_title);
        	txtTitle.setText(title);
        }
		
        return true;
	}
}
