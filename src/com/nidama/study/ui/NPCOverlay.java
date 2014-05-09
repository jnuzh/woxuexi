package com.nidama.study.ui;

import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.nidama.study.R;
import com.nidama.study.data.NPCData;
import com.nidama.study.engine.NPCManager;


public class NPCOverlay extends ItemizedOverlay<OverlayItem> {
    //private Context context;
	NPCManager npcManager=null;
 
    // 这是弹出窗口的内容部分
	private PopupView popupView=null;
    //private MapView mapView = null;
    
    //private OnTapListener onTapListener = null;
  
    public NPCOverlay(MapView mapView, Context context){  
    	//default marker
        super(context.getResources().getDrawable(R.drawable.ic_launcher),mapView);
        npcManager=new NPCManager();
        
        
        popupView=new PopupView(context, mapView);

        //this.context=context;
        //this.mapView = mapView;
        
        for( NPCData npcData : npcManager.getNPCers())
        {
        	this.addItem(npcManager.NPCDataToNPCOverlayItem(npcData));
        }
    } 

    @Override  
    // 处理当点击事件  
    protected boolean onTap(int i){
    	super.onTap(i);
        //Toast.makeText(this.mContext, this.getItem(i).getSnippet(), Toast.LENGTH_SHORT).show();  
        // 点击Marker时，该Marker滑动到地图中央偏下的位置，并显示Popup窗口
        OverlayItem item = getItem(i);
        popupView.Show(item.getPoint());

        return true;
    } 
    @Override
    public boolean onTap(GeoPoint geoPoint, MapView mMapView) {
        // hide the view when mouse click outside view
    	popupView.Hide(geoPoint);
        return false;
    }    
}
