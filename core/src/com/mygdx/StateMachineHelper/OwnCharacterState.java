package com.mygdx.StateMachineHelper;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.mygdx.GameObjects.OwnCharacter;


public enum  OwnCharacterState implements State<OwnCharacter> {
	
	IDLE(){
		@Override
		public void update(OwnCharacter entity) {
			// TODO Auto-generated method stub
			
		}
	},
	
	WALKING_LEFT(){
		@Override
		public void update(OwnCharacter entity) {
			if(entity.isAnimationFinish){
			entity.stateTime = 0 ;
			}
			
			entity.texReg = entity.assetImpCharacter.walk_Vanilla_LEFT.getKeyFrame(entity.stateTime);
			
			if(entity.assetImpCharacter.walk_Vanilla_LEFT.isAnimationFinished(entity.stateTime)){
				entity.isAnimationFinish = true;
			}else
			{
				entity.isAnimationFinish = false;
			}
		}
	},
	
	WALKING_RIGHT(){
		@Override
		public void update(OwnCharacter entity) {
			if(entity.isAnimationFinish){
				entity.stateTime = 0 ;
				}
				
				entity.texReg = entity.assetImpCharacter.walk_Vanilla_RIGHT.getKeyFrame(entity.stateTime);
				
				if(entity.assetImpCharacter.walk_Vanilla_RIGHT.isAnimationFinished(entity.stateTime)){
					entity.isAnimationFinish = true;
				}else
				{
					entity.isAnimationFinish = false;
				}
			
		}
	},
	
	WALKING_UP(){
		@Override
		public void update(OwnCharacter entity) {
			if(entity.isAnimationFinish){
				entity.stateTime = 0 ;
				}
				
				entity.texReg = entity.assetImpCharacter.walk_Vanilla_UP.getKeyFrame(entity.stateTime);
				
				if(entity.assetImpCharacter.walk_Vanilla_UP.isAnimationFinished(entity.stateTime)){
					entity.isAnimationFinish = true;
				}else
				{
					entity.isAnimationFinish = false;
				}
			
		}
	},
	
	WALKING_DOWN(){
		@Override
		public void update(OwnCharacter entity) {
			if(entity.isAnimationFinish){
				entity.stateTime = 0 ;
				}
				
				entity.texReg = entity.assetImpCharacter.walk_Vanilla_DOWN.getKeyFrame(entity.stateTime);
				
				if(entity.assetImpCharacter.walk_Vanilla_DOWN.isAnimationFinished(entity.stateTime)){
					entity.isAnimationFinish = true;
				}else
				{
					entity.isAnimationFinish = false;
				}
			
		}
	},
	Magic(){
		@Override
		public void update(OwnCharacter entity) {
			if(entity.isAnimationFinish){
				entity.stateTime = 0 ;
				}
				
				entity.texReg = entity.assetImpCharacter.walk_Vanilla_UP.getKeyFrame(entity.stateTime);
				
				if(entity.assetImpCharacter.walk_Vanilla_UP.isAnimationFinished(entity.stateTime)){
					entity.isAnimationFinish = true;
				}else
				{
					entity.isAnimationFinish = false;
				}
			
		}
	},
	;

	@Override
	public void enter(OwnCharacter entity) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void exit(OwnCharacter entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMessage(Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}
	

	

}
