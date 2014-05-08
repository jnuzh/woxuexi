package com.nidama.study.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.Projection;
import com.nidama.study.R;
import com.nidama.study.data.NPCData;
import com.nidama.study.engine.NPCManager;


public class NPCOverlay extends ItemizedOverlay<OverlayItem> {
    private Context context;
	NPCManager npcManager=null;
 
    private LinearLayout popupLinear = null;
    // 这是弹出窗口的内容部分
    private View popupView = null;
    private MapView mapView = null;
    private Projection projection = null;
    
    //private OnTapListener onTapListener = null;

    // 这是弹出窗口内容部分使用的layoutId，在Activity中设置
    private int layoutId = 0;
    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
  
    public NPCOverlay(MapView mapView, Context context){  
    	//default marker
        super(context.getResources().getDrawable(R.drawable.ic_launcher),mapView);

        this.context=context;
        this.popupLinear = new LinearLayout(context);
        this.mapView = mapView;
        
        for( NPCData npcData : npcManager.getNPCers())
        {
        	this.addItem(npcManager.NPCDataToNPCOverlayItem(npcData));
        }
        this.setLayoutId(R.layout.view_multiple_choices);

        popupLinear.setOrientation(LinearLayout.VERTICAL);
        popupLinear.setVisibility(View.GONE);
        projection = mapView.getProjection();
       
    } 

    private boolean createPopupView() {
        // TODO Auto-generated method stub
        if (layoutId == 0)
            return false;
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
  
    @Override  
    // 处理当点击事件  
    protected boolean onTap(int i){
    	super.onTap(i);
        //Toast.makeText(this.mContext, this.getItem(i).getSnippet(), Toast.LENGTH_SHORT).show();  
        // 点击Marker时，该Marker滑动到地图中央偏下的位置，并显示Popup窗口
        OverlayItem item = getItem(i);
        if (popupView == null) {
            // 如果popupView还没有创建，则构造popupLinear
            if (!createPopupView()){
                return true;
            }
        }
        //if (onTapListener == null)
        //    return true;
        popupLinear.setVisibility(View.VISIBLE);
        //onTapListener.onTap(i, popupView);

        popupLinear.measure(0, 0);
        int viewWidth = popupLinear.getMeasuredWidth();
        int viewHeight = popupLinear.getMeasuredHeight();

        //LayoutParams layoutParams = new LayoutParams(viewWidth, viewHeight,
        //        item.getPoint(), 0, -60, LayoutParams.WRAP_CONTENT);
        //layoutParams.mode = LayoutParams.MODE_MAP;

        //popupLinear.setLayoutParams(layoutParams);
        Point p = new Point();
        projection.toPixels(item.getPoint(), p);
        p.y = p.y - viewHeight / 2;
        GeoPoint point = projection.fromPixels(p.x, p.y);

        mapView.getController().animateTo(point);
        return true;
    } 
    @Override
    public boolean onTap(GeoPoint pt, MapView mMapView) {
        // hide the view when mouse click outside view
        if (popupLinear != null && popupLinear.getVisibility() == View.VISIBLE) {
            Point tapP = new Point();
            projection.toPixels(pt, tapP);
            if (tapP.x < popupLinear.getLeft() || tapP.y < popupLinear.getTop() || tapP.x > popupLinear.getRight()
                    || tapP.y > popupLinear.getBottom())
                popupLinear.setVisibility(View.GONE);
        }
        return false;
    }    
}
