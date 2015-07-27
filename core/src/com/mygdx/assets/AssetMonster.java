package com.mygdx.assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.mygdx.ObjectInfo.MonsterInfo;



public class AssetMonster extends AbstractAssetInfo{
	
	public Animation walk_UP;
	public Animation walk_DOWN;
	public Animation walk_RIGHT;
	public Animation walk_LEFT;
	public Animation attack_DOWN;
	public Animation attack_Right;
	public Animation attack_LEFT;
	public Animation attack_UP;
	public Animation death;
	
	
	public AssetMonster(MonsterInfo monsterInfo,TextureAtlas atlas) {
	
		super(atlas);
		walk_UP = new Animation(0.2f, getKeyFrames(monsterInfo.framesAnimationPairWalkUp,monsterInfo.path),PlayMode.LOOP);
	    walk_DOWN = new Animation(0.2f,getKeyFrames(monsterInfo.framesAnimationPairWalkDown,monsterInfo.path),PlayMode.LOOP);
	    walk_RIGHT = new Animation(0.2f,getKeyFrames(monsterInfo.framesAnimationPairWalkRight,monsterInfo.path),PlayMode.LOOP);
	    walk_LEFT = new Animation(0.2f, getKeyFrames(monsterInfo.framesAnimationPairWalkLeft,monsterInfo.path),PlayMode.LOOP);
	    
	   /* attack_LEFT = new Animation(0.2f, getKeyFrames(AnimationList.attack_AnimationLeft),PlayMode.LOOP);
	    attack_Right = new Animation(0.2f, getKeyFrames(AnimationList.attack_AnimationRight),PlayMode.LOOP);
	    attack_UP = new Animation(0.2f, getKeyFrames(AnimationList.attack_AnimationUp),PlayMode.LOOP);
	    attack_DOWN = new Animation(0.2f, getKeyFrames(AnimationList.attack_AnimationDown),PlayMode.LOOP);*/
	    
	    
		
		
	}
	
}