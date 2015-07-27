package com.mygdx.assets;


import com.badlogic.gdx.utils.ObjectMap;

public class AssetImpCharacterInfo {
	
	ObjectMap<String,FramesAnimationPair> animationInfoMap;
	
	

	public FramesAnimationPair getFramesAnimationPair(String animationName) {
		return animationInfoMap.get(animationName);
	
	}
    
}
