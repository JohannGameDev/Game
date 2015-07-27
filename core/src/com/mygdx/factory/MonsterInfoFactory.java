package com.mygdx.factory;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.mygdx.ObjectInfo.MonsterInfo;


public class MonsterInfoFactory {
	private static ObjectMap<String,MonsterInfo> monsterInfoMap = new  ObjectMap<String,MonsterInfo>() ;
	public MonsterInfoFactory(){}
	
	@SuppressWarnings({ "unchecked" })
	
	public static void  instantiate(){
		Json json = new Json();
	
		json.addClassTag("MonsterInfo", MonsterInfo.class);
		
		 monsterInfoMap = json.fromJson(ObjectMap.class,Gdx.files.internal("json/MonsterInfo.json"));
		
	}
	public static MonsterInfo getMonsterInfo(String monsterName){
		
		return monsterInfoMap.get(monsterName);
		
	}
public static Array<MonsterInfo> getMonsterInfoArray(){
		Array<MonsterInfo> monsterInfoArray = new Array<MonsterInfo>();
		
		for (Values<MonsterInfo> iterator = monsterInfoMap.values(); iterator.hasNext();) {
			MonsterInfo mI = (MonsterInfo) iterator.next();
			monsterInfoArray.add(mI);
		}
		return monsterInfoArray;
		
	}
}
