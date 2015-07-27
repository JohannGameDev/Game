package com.mygdx.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.assets.AssetImpCharacterInfo;
import com.mygdx.assets.FramesAnimationPair;



public class AssetImpCharacterInfoFactory {
private static ObjectMap<String, AssetImpCharacterInfo> assetImpCharacterInfoMap;

private AssetImpCharacterInfoFactory() {
}
	
@SuppressWarnings("unchecked")
public static void instantiate(){
	Json json = new Json();
	json.addClassTag("AssetImpCharacterInfo", AssetImpCharacterInfo.class);
	json.addClassTag("FramesAnimationPair", FramesAnimationPair.class);
    json.setElementType(AssetImpCharacterInfo.class, "animationInfoMap",ObjectMap.class);//Array Einträge von animationInfoArray sind vom Typ FramesAnimationpair  

	assetImpCharacterInfoMap = json.fromJson(ObjectMap.class,Gdx.files.internal("json/AssetCharacterImpInfo.json"));
	
}
public static AssetImpCharacterInfo getAssetImpCharacterInfo(String name){
	
	
	return assetImpCharacterInfoMap.get(name);
	
}
}
