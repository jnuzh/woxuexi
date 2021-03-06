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
	int status=1;
	public int getStatus(){
		return status;
	}

	public NPCOverlayItem(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
	}
	public NPCOverlayItem(NPCData npcData) {
		super(npcData.getGeoPoint(),npcData.getName(),npcData.getWords());
		this.npcData=npcData;
	}
	public boolean lostFocus(ViewManager viewManager,GeoPoint clickPoint)
	{
		return viewManager.hide(clickPoint);
	}
	public String getSnippet(){
		return npcData.getWords();
	}
	public void onTap(ViewManager viewManager)
	{
		if(status==1)
		{
			viewManager.showMessage(this.getPoint(), npcData.getWords(), npcData.getName(), null);
		}
			
	}

}
