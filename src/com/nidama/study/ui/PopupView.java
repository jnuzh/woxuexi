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

public class PopupView {

	View popupView=null;

	Context context=null;
	private MapView mapView = null;
    private LinearLayout popupLinear = null;
	
	
	public PopupView(Context context,MapView mapView)
	{
		this.context=context;
		this.mapView=mapView;
        this.popupLinear = new LinearLayout(context);
        this.setLayoutId(R.layout.view_multiple_choices);
        popupLinear.setOrientation(LinearLayout.VERTICAL);
        LayoutParams layoutParams=new LayoutParams();
        layoutParams.height=LayoutParams.WRAP_CONTENT;
        layoutParams.width=LayoutParams.FILL_PARENT;
        popupLinear.setLayoutParams(layoutParams);
        popupLinear.setVisibility(View.GONE);
	}
	
	
	private int layoutId = 0;
	public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

	public void Hide(GeoPoint geoPoint){
        if (popupLinear != null && popupLinear.getVisibility() == View.VISIBLE) {
            Point tapP = new Point();
            mapView.getProjection().toPixels(geoPoint, tapP);
            if (tapP.x < popupLinear.getLeft() || tapP.y < popupLinear.getTop() || tapP.x > popupLinear.getRight()
                    || tapP.y > popupLinear.getBottom())
                popupLinear.setVisibility(View.GONE);
        }
	}
	public boolean Show(GeoPoint clickPoint,Object content)
	{
		if(!this.createPopupView())
			return false;
        //if (onTapListener == null)
        //    return true;
        popupLinear.setVisibility(View.VISIBLE);
        //onTapListener.onTap(i, popupView);

        popupLinear.measure(0, 0);
        int viewWidth = popupLinear.getMeasuredWidth();
        int viewHeight = popupLinear.getMeasuredHeight();
        
        //TextView shopName = (TextView) popupView.findViewById(R.id.multiple_choices_title);
        //shopName.setText("Hello Casper");

        //LayoutParams layoutParams = new LayoutParams(viewWidth, viewHeight,
        //        item.getPoint(), 0, -60, LayoutParams.WRAP_CONTENT);
        //layoutParams.mode = LayoutParams.MODE_MAP;

        //popupLinear.setLayoutParams(layoutParams);
        Point p = new Point();
        Projection projection = mapView.getProjection();

        projection.toPixels(clickPoint, p);
        p.y = p.y - viewHeight / 2;
        GeoPoint point = projection.fromPixels(p.x, p.y);

        mapView.getController().animateTo(point);
		return true;
	}
	protected boolean createPopupView() {
        if (layoutId == 0)
            return false;
        if(popupView!=null)return true;
        popupView = LayoutInflater.from(context).inflate(layoutId, null);
        /*popupView.setBackgroundResource(R.drawable.);
        ImageView dialogStyle = new ImageView(context);
        dialogStyle.setImageDrawable(context.getResources().getDrawable(
                R.drawable.iw_tail));
                */
        popupLinear.addView(popupView);
        android.widget.LinearLayout.LayoutParams lp = new android.widget.LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.topMargin = -2;
        lp.leftMargin = 60;
        //popupLinear.addView(dialogStyle, lp);
        mapView.addView(popupLinear);
        return true;
	}
}
