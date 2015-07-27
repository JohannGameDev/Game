package com.mygdx.OwnInputProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.GameObjects.AbstractGameObject;
import com.mygdx.GameObjects.AttackAbleGameObject;
import com.mygdx.GameObjects.OwnCharacter;
import com.mygdx.Global.Assets;
import com.mygdx.StateMachineHelper.OwnCharacterState;
import com.mygdx.game.GameUml;
import com.mygdx.game.WorldController;

public class GameScreenStage {
private static final String TAG = GameScreenStage.class.getName();
public Stage gameScreenStage;
public GameUml game;
public Table content;
public Touchpad touchpad;
public ImageButton attack1,attack2,attack3;
public Skin skin;
WorldController worldController;
private OwnCharacter gameObject;
private Vector2 tmpTouchpadPosition = new Vector2();
float touchPadAngle;
public GameScreenStage(GameUml game,WorldController worldController){
	this.worldController = worldController;
	this.game = game;
	init();
}

private void init() {
	gameScreenStage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(),
			Gdx.graphics.getHeight()));
	content = new Table();
	content.debug();
	content.setFillParent(true);
	skin = new Skin(Gdx.files.internal("userInterfaceSkins/myUiSkin.json"),Assets.instance.atlasUI);
	touchpad = new Touchpad(10, skin, "ownTouchpadStyle");

	content.bottom().left();
	content.add(touchpad).bottom().left().expandX().pad(0,0, 5,5);
	
	attack1 = new ImageButton(skin, "attackButtonStyle");
	
	attack1.addListener(new ClickListener(){
    	
    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
			
    		return false;
    		
    	}
    });
	content.add(attack1).right().pad(0, 0, 5, 5);
	
	attack2 = new ImageButton(skin, "attackButtonStyle");
	content.add(attack2).right().pad(0, 0, 5, 5);
	
	attack3 = new ImageButton(skin, "attackButtonStyle");
    content.add(attack3).right().pad(0, 0, 5, 5);
    attack3.addListener(new ClickListener(){
    	
    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
			
    		gameObject.handleSkill("BoltSizzle");
    		return false;
    		
    	}
    });
	
    content.addListener(new ActorGestureListener(){
    	public void zoom(InputEvent event,float initialDistance,float distance){
    		worldController.cameraHelper.addZoom(distance - initialDistance);
    	}
    });
	gameScreenStage.addActor(content);
	
	
	
}
public void handleTouchpad(){
	
	tmpTouchpadPosition.set(touchpad.getKnobPercentX(),touchpad.getKnobPercentY());
	if(tmpTouchpadPosition.angle() != touchPadAngle){
		//worldController.globalTestPathArray = worldController.pathFinder.findPath(1, 1, MathUtils.floor(worldController.gameObject.position.x),MathUtils.floor(worldController.gameObject.position.y));
		touchPadAngle = tmpTouchpadPosition.angle();
	}
	
	//Gdx.app.debug(TAG, "Angle: "+touchPadAngle);
	
	//Idle
	if(touchpad.getKnobPercentX() == 0 && touchpad.getKnobPercentY() == 0 ){
		gameObject.stateMachine.changeState(OwnCharacterState.IDLE);
		
		
		}
	//Up
	if(touchPadAngle < 135 && touchPadAngle > 45){
		gameObject.stateMachine.changeState(OwnCharacterState.WALKING_UP);
		gameObject.velocity.y = gameObject.terminalVelocity.y * 2;
		gameObject.rotation = touchPadAngle-90;//-90 grad nochmal ausrechenen
	}
	//right
	if(touchPadAngle > 360-45 &&touchPadAngle >= 360-45  ||  touchPadAngle <= 45 &&  touchPadAngle > 0){
		gameObject.stateMachine.changeState(OwnCharacterState.WALKING_RIGHT);
		gameObject.velocity.x = gameObject.terminalVelocity.x * 2;
		gameObject.rotation = touchPadAngle;//-90 grad nochmal ausrechenen
	}
	//left
	if(touchPadAngle > 135 && touchPadAngle < 135+90){
		gameObject.stateMachine.changeState(OwnCharacterState.WALKING_LEFT);
		gameObject.velocity.x = gameObject.terminalVelocity.x * -2;
		gameObject.rotation = touchPadAngle+180;//-90 grad nochmal ausrechenen
	}
	//Down
	if(touchPadAngle > 135+90 && touchPadAngle < 360-45){
		gameObject.stateMachine.changeState(OwnCharacterState.WALKING_DOWN);
		gameObject.velocity.y = gameObject.terminalVelocity.y * -2;
		gameObject.rotation = touchPadAngle+90;//-90 grad nochmal ausrechenen
	}
	
	
	/*Without angle
	 * 
	 * //walking right
	
	if(touchpad.getKnobPercentX() > 0 && touchpad.getKnobPercentY() < 0.5 && touchpad.getKnobPercentY() > -0.5){
		
		gameObject.stateMachine.changeState(OwnCharacterState.WALKING_RIGHT);
		gameObject.velocity.x = gameObject.terminalVelocity.x * 2;
	}
	
	if(touchpad.getKnobPercentX() < 0 && touchpad.getKnobPercentY() < 0.5 && touchpad.getKnobPercentY() > -0.5 ){
		gameObject.stateMachine.changeState(OwnCharacterState.WALKING_LEFT);
		gameObject.velocity.x = gameObject.terminalVelocity.x * -2;
	}
	if(touchpad.getKnobPercentY() > 0 && touchpad.getKnobPercentX() < 0.5 && touchpad.getKnobPercentX() > -0.5){
		gameObject.stateMachine.changeState(OwnCharacterState.WALKING_UP);
		gameObject.velocity.y = gameObject.terminalVelocity.y * 2;
	}
	if(touchpad.getKnobPercentY() < 0 && touchpad.getKnobPercentX() < 0.5 && touchpad.getKnobPercentX() > -0.5){
		gameObject.stateMachine.changeState(OwnCharacterState.WALKING_DOWN);
		gameObject.velocity.y = gameObject.terminalVelocity.y * -2;
}*/
}

public void setGameObject(OwnCharacter gameObject2){
	this.gameObject = gameObject2;
}
public AbstractGameObject getGameObject(){
	return gameObject;
}


public Stage getStage() {
	// TODO Auto-generated method stub
	return gameScreenStage;
}
}
