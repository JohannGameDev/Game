package com.mygdx.assets;



import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.factory.AssetImpCharacterInfoFactory;


public abstract class AssetImpCharacter extends AbstractAssetInfo{
TextureAtlas atlas;
String path,folder;

public Animation walk_Vanilla_UP;
public Animation walk_Vanilla_DOWN;
public Animation walk_Vanilla_RIGHT;
public Animation walk_Vanilla_LEFT;
public AssetImpCharacter(String skinName,TextureAtlas atlas){
	super(atlas);
	this.atlas = atlas;
	
	folder = "walkVanilla";
	path = "imp/"+skinName+"/"+folder+"/"+skinName;

	AssetImpCharacterInfo assetImpCharacterInfo = AssetImpCharacterInfoFactory.getAssetImpCharacterInfo(skinName);
	
    walk_Vanilla_UP = new Animation(0.2f, getKeyFrames(assetImpCharacterInfo.getFramesAnimationPair("walk_VanillaUp"),path),PlayMode.NORMAL);
    walk_Vanilla_DOWN = new Animation(0.2f, getKeyFrames(assetImpCharacterInfo.getFramesAnimationPair("walk_VanillaDown"),path),PlayMode.NORMAL);
    walk_Vanilla_RIGHT = new Animation(0.2f, getKeyFrames(assetImpCharacterInfo.getFramesAnimationPair("walk_VanillaRight"),path),PlayMode.NORMAL);
    walk_Vanilla_LEFT = new Animation(0.2f, getKeyFrames(assetImpCharacterInfo.getFramesAnimationPair("walk_VanillaLeft"),path),PlayMode.NORMAL);
    
  /*  attack_LEFT = new Animation(0.2f, getKeyFrames(AnimationList.attack_AnimationLeft));
    attack_Right = new Animation(0.2f, getKeyFrames(AnimationList.attack_AnimationRight));
    attack_UP = new Animation(0.2f, getKeyFrames(AnimationList.attack_AnimationUp));
    attack_DOWN = new Animation(0.2f, getKeyFrames(AnimationList.attack_AnimationDown));*
    
    */
	
	
}

}
