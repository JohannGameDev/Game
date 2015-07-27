package com.mygdx.factory;

import level.AmountMonster;
import level.LevelInfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Json;
import com.mygdx.jsonHelper.IntMapSerializer;


public class LevelInfoFactory {
	private static IntMap<LevelInfo> levelInfoMap = new  IntMap<LevelInfo>() ;
	public LevelInfoFactory(){}
	
	@SuppressWarnings({ "unchecked" })
	
	public static void  instantiate(){
		Json json = new Json();
	
		json.addClassTag("levelInfo", LevelInfo.class);
	    json.setElementType(LevelInfo.class, "monsterArray", AmountMonster.class);//Array Einträge von monsterArray sind vom Typ AmountMonster  
		json.setSerializer(IntMap.class, new IntMapSerializer());
		levelInfoMap = json.fromJson(IntMap.class,Gdx.files.internal("json/LevelInformation.json"));
		
	}
	public static LevelInfo getLevelInfo(int level){
		
		return levelInfoMap.get(level);
		
	}
}
