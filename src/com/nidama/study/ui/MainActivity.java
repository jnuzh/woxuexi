package com.nidama.study.ui;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.nidama.study.R;
import com.nidama.study.R.drawable;
import com.nidama.study.R.id;
import com.nidama.study.R.layout;
import com.nidama.study.data.NPCData;
import com.nidama.study.engine.NPCManager;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MainActivity extends Activity {
	BMapManager baiduMapManager = null;
	MapView mapView = null;
	
	NPCManager npcManager=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//init baidu map view
		baiduMapManager = new BMapManager(getApplication());
		baiduMapManager.init(null);

		setContentView(R.layout.activity_main);
		
		mapView = (MapView) findViewById(R.id.bmapsView);
		//mapView.setBuiltInZoomControls(true);
		MapController mapController = mapView.getController();

		//http://api.map.baidu.com/lbsapi/getpoint/index.html
		// 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		GeoPoint point = new GeoPoint((int) (22256467),(int) (113540718));
		mapController.setCenter(point);// 设置地图中心点
		mapController.setZoom(17);// 设置地图zoom级别

		mapController.enableClick(true);
		
		npcManager.load();
        NPCOverLay npcOverLay=new NPCOverLay(mapView,MainActivity.this);
        for( NPCData npcData : npcManager.getNPCers())
        {
        	npcOverLay.addItem(npcManager.NPCDataToNPCOverlayItem(npcData));
        }
        npcOverLay.setLayoutId(R.layout.view_multiple_choices);
        mapView.getOverlays().add(npcOverLay); 
        mapView.refresh();   

	}

	@Override
	protected void onDestroy() {
		mapView.destroy();
		if (baiduMapManager != null) {
			baiduMapManager.destroy();
			baiduMapManager = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mapView.onPause();
		if (baiduMapManager != null) {
			baiduMapManager.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mapView.onResume();
		if (baiduMapManager != null) {
			baiduMapManager.start();
		}
		super.onResume();
	}
}
