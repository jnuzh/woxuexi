package com.nidama.study.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.Toast;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.nidama.study.R;
import com.nidama.study.data.NPCData;
import com.nidama.study.engine.NPCManager;


public class NPCOverlay extends ItemizedOverlay<OverlayItem> {
    private Context context;
	NPCManager npcManager=null;
	
	private ViewManager viewManager=null;
 
    //private MapView mapView = null;
    
    //private OnTapListener onTapListener = null;
  
    public NPCOverlay(MapView mapView, Context context){  
    	//default marker
        super(context.getResources().getDrawable(R.drawable.ic_launcher),mapView);
        npcManager=new NPCManager();
        
     
        viewManager=new ViewManager(mapView, context);

        this.context=context;
        //this.mapView = mapView;
        
        for( NPCData npcData : npcManager.getNPCers())
        {
        	this.addItem(npcManager.NPCDataToNPCOverlayItem(npcData));
        }
    } 

    private NPCOverlayItem tappedItem=null;
    @Override  
    // 处理当点击事件  
    protected boolean onTap(int i){
    	super.onTap(i);
    	//TODO error with lostFocus
    	if(tappedItem!=null)tappedItem.lostFocus(viewManager,tappedItem.getPoint());
        // 点击Marker时，该Marker滑动到地图中央偏下的位置，并显示Popup窗口
    	tappedItem = (NPCOverlayItem)getItem(i);
    	tappedItem.onTap(viewManager);
        //if(item.getStatus()==1){
        //    Toast.makeText(this.context, this.getItem(i).getSnippet(), Toast.LENGTH_SHORT).show();  
        //}

        return false;
    } 
    @Override
    public boolean onTap(GeoPoint geoPoint, MapView mMapView) {
        // hide the view when mouse click outside view
    	if(tappedItem!=null){
    		if(tappedItem.lostFocus(viewManager,geoPoint))tappedItem=null;
    	}
        return false;
    }    
}
