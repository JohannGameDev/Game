package com.mygdx.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.ObjectInfo.SkillDamageInfo;
import com.mygdx.ObjectInfo.SkillsInfo;
import com.mygdx.assets.FramesAnimationPair;


public class SkillFactory {
	public static final String TAG = SkillFactory.class.getName();
	private static ObjectMap<String,SkillsInfo> map = new  ObjectMap<String,SkillsInfo>() ;
	public SkillFactory(){}
	
	@SuppressWarnings({ "unchecked" })
	
	public static void  instantiate(){
		Json json = new Json();
		json.addClassTag("SkillInfo", SkillsInfo.class);
		
		json.addClassTag("SkillDamageInfo", SkillDamageInfo.class);
		json.addClassTag("FramesAnimationPair", FramesAnimationPair.class);
		map = json.fromJson(ObjectMap.class,Gdx.files.internal("json/Skills.json"));
		 if(map.containsKey("RainOfFire"))
			 Gdx.app.debug(TAG,"true");
		
	}
	public static SkillsInfo getSkillInfo(String skillName){
		
		return map.get(skillName);
		
	}
}
