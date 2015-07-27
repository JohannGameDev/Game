package com.mygdx.GameObjects;





import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Global.Assets;
import com.mygdx.Global.Constants;
import com.mygdx.ObjectInfo.CharacterStatsInfo;
import com.mygdx.ObjectInfo.ObjectSkillsInformation;
import com.mygdx.StateMachineHelper.OwnCharacterState;
import com.mygdx.assets.AbstractAssetInfo;
import com.mygdx.assets.AssetImpCharacter;
import com.mygdx.game.SkillHandler;


public class OwnCharacter extends AttackAbleGameObject{
	public static final String Tag = OwnCharacter.class.getName();
	public TextureRegion texReg = Assets.instance.assetCharacterGreenImp.walk_Vanilla_DOWN.getKeyFrame(0);

public AssetImpCharacter assetImpCharacter;
public StateMachine<OwnCharacter> stateMachine;
public boolean isAnimationFinish;
public float stateTime;

	public OwnCharacter(CharacterStatsInfo characterStatsInfo,ObjectSkillsInformation skillInformation,AbstractAssetInfo asset){
	
		super(characterStatsInfo,skillInformation,asset);
        stateMachine = new DefaultStateMachine<OwnCharacter>(this, OwnCharacterState.IDLE);
		assetImpCharacter = (AssetImpCharacter) asset;
		//initSkilltestbegin
		skillInformation.init();
		//initSkilltestend
		
		Gdx.app.debug(Tag, skillInformation.getSkill("BoltSizzle").skillInfo.skillName);
		Gdx.app.debug(Tag, "baöböaböab");
		//test end
		
		bounds.width = texReg.getRegionWidth() * Constants.UNITSCALE;
		bounds.height = texReg.getRegionHeight() * Constants.UNITSCALE;
		position.x = 0;
		position.y = 0;
		scale.x= 1f;
		scale.y =1f;
		origin.x = bounds.width/2;
		origin.y = bounds.height/2;
		rotation = 0;
		
		terminalVelocity.set(6.0f, 6.0f);
		friction.set(18.0f, 18.0f);
		acceleration.set(0.0f, 0.0f);
	}
	
	@Override
	public void render(Batch batch) {
		batch.begin();
		batch.draw(texReg,position.x,position.y,
				origin.x,origin.y,
				bounds.width,bounds.height,scale.x,scale.y,rotation);
		
		batch.end();
		
		
	}
	
	public void update(float deltaTime){
		
		super.update(deltaTime);
		stateTime += deltaTime;
		stateMachine.update();
		
		}
	public void handleSkill(String skillName){
		
		SkillHandler.handleSkill(skillInformation.getSkill(skillName),this, new Vector2());
	}
	
	

}
