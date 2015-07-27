package com.mygdx.assets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class AbstractAssetInfo {
	public TextureAtlas atlas;
	public AbstractAssetInfo(TextureAtlas atlas){
		this.atlas = atlas;
	}
	
	public Array<TextureRegion> getKeyFrames(FramesAnimationPair framesAnimationPair,String tempPath){
		Array<TextureRegion> keyFrames = new Array<TextureRegion>();

		for (int index = 1; index <= framesAnimationPair.frames; index++) {
		
			keyFrames.add(new TextureRegion(atlas.findRegion(tempPath+framesAnimationPair.animationName,index)));
			
		}
		    
						
			
		
		return keyFrames;
		
	}
}
