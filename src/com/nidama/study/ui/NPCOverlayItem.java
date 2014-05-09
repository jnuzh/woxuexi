/**
 * 
 */
package com.nidama.study.ui;

import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.nidama.study.data.NPCData;

/**
 * @author youran
 *
 */
public class NPCOverlayItem extends OverlayItem {
	NPCData npcData;

	public NPCOverlayItem(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
	}
	public NPCOverlayItem(NPCData npcData) {
		super(npcData.getGeoPoint(),npcData.getWords(),"");
		this.npcData=npcData;
	}
	public void onTap()
	{
		
	}

}
