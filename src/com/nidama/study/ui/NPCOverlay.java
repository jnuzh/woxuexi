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
 
    // 这是弹出窗口的内容部分
	private PopupView popupView=null;
    //private MapView mapView = null;
    
    //private OnTapListener onTapListener = null;
  
    public NPCOverlay(MapView mapView, Context context){  
    	//default marker
        super(context.getResources().getDrawable(R.drawable.ic_launcher),mapView);
        npcManager=new NPCManager();
        
     
        popupView=new PopupMessageView(context, mapView,R.layout.view_popup_message);

        this.context=context;
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
        // 点击Marker时，该Marker滑动到地图中央偏下的位置，并显示Popup窗口
        NPCOverlayItem item = (NPCOverlayItem)getItem(i);
        if(item.getStatus()==1){
            Toast.makeText(this.context, this.getItem(i).getSnippet(), Toast.LENGTH_SHORT).show();  
        }
        //popupView.Show(item.getPoint(),item.getSnippet());

        return false;
    } 
    @Override
    public boolean onTap(GeoPoint geoPoint, MapView mMapView) {
        // hide the view when mouse click outside view
    	popupView.Hide(geoPoint);
        return false;
    }    
}
