package com.nidama.study.engine;

import java.util.ArrayList;

import com.nidama.study.data.NPCData;
import com.nidama.study.ui.NPCOverlayItem;

public class NPCManager {
	static ArrayList<NPCData> NPCers=new ArrayList<NPCData>();
	static boolean isLoad=false;
	
	public void load()
	{
		if(!isLoad){
			NPCData npc=new NPCData();
			npc.setGeoPoint(22.254836, 113.538858);
			npc.setName("Mr Chen");
			npc.setWords("仗剑走天涯");
			NPCers.add(npc);
			isLoad=true;
		}
	}
	public ArrayList<NPCData> getNPCers()
	{
		return NPCers;
	}
	
	public NPCOverlayItem NPCDataToNPCOverlayItem(NPCData npcData)
	{
		NPCOverlayItem npcOverlayItem=new NPCOverlayItem(npcData);
		return npcOverlayItem;
	}

}
