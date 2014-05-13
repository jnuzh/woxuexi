package com.nidama.study.engine;

import java.util.ArrayList;

import com.nidama.study.data.NPCData;
import com.nidama.study.data.NPCStatus;
import com.nidama.study.data.NPCStatusSpeaking;
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
			
			NPCStatusSpeaking npcStatusSpeaking=new NPCStatusSpeaking(0,0);
			npcStatusSpeaking.addWords("忠信笃敬，一统江湖!");
			npcStatusSpeaking.addWords("葵花宝典，武林绝学!");
			npcStatusSpeaking.addWords("无敌袁大侠等着你!");
			npcStatusSpeaking.addWords("长在江湖飘哪有不挨刀!");
			npc.addStatus(0, npcStatusSpeaking);
			

			npc=new NPCData();
			npc.setGeoPoint(22.253000, 113.538858);
			npc.setName("Mr Zhang");
			npc.setWords("解放台湾");
			npcStatusSpeaking=new NPCStatusSpeaking(0,0);
			npcStatusSpeaking.setRandomSpeacking();
			npcStatusSpeaking.addWords("1");
			npcStatusSpeaking.addWords("2");
			npcStatusSpeaking.addWords("3");
			npcStatusSpeaking.addWords("4");
			npc.addStatus(0, npcStatusSpeaking);
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
