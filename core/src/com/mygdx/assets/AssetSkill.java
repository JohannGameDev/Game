package com.mygdx.assets;



import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.ObjectInfo.Skill;


public class AssetSkill extends AbstractAssetInfo {
public TextureAtlas atlas;
public Skill skill;
public Animation skillAnimation;
	public AssetSkill(Skill skill,TextureAtlas atlas){
		super(atlas);
		this.atlas =atlas;
		this.skill = skill;
		String temp = "";
		skillAnimation =  new Animation(0.05f,getKeyFrames(skill.skillInfo.skillFramesPerAnimation,temp),PlayMode.LOOP);
	}

}
