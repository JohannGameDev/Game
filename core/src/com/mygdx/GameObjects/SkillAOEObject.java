package com.mygdx.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.Global.Constants;
import com.mygdx.ObjectInfo.Skill;
import com.mygdx.assets.AssetSkill;


public class SkillAOEObject extends AbstractGameObject{
	public static final String TAG = SkillAOEObject.class.getName();
	Animation aoeAnimation;
	boolean isOver;
	Skill skill;
	AssetSkill assetSkill;
	float stateTime;
	TextureRegion region = new TextureRegion();
	long startetSkillTime;
	public boolean isSkillOver;
	
	public SkillAOEObject(Skill skill,Vector2 position,AssetSkill assetSkill){
    super(assetSkill);
	this.skill = skill;
	this.position = position;
	this.assetSkill = assetSkill;
	init();
	Gdx.app.debug(TAG,"position x:" + this.position.x +"position y: " + this.position.y );
	
	
}

	private void init() {
		
				startetSkillTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
				bounds.width = skill.skillInfo.skillDamgeInfo.skillAreaWidth;
				bounds.height = skill.skillInfo.skillDamgeInfo.skillAreaHeight;
				scale.x= 6f;
				scale.y = 6f;
				origin.x = bounds.width/2;
				origin.y = bounds.height/2;
				rotation = 0;
		
	}

	@Override
	public void render(Batch batch) {
		batch.begin();
		batch.draw(region, this.position.x, this.position.y,origin.x, origin.y,bounds.width,bounds.height ,scale.x, scale.y, 0);
		batch.end();
		
	}
	public void update(float delta){
		if (TimeUtils.nanosToMillis(TimeUtils.nanoTime()) - startetSkillTime >= skill.skillInfo.skillDamgeInfo.duration ) {
			
			isSkillOver = true;
		}
		stateTime +=delta;
	    region =  assetSkill.skillAnimation.getKeyFrame(stateTime);
	}

}
