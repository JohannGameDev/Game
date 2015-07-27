package com.mygdx.OwnInputProcessor;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.WorldController;

public class GameScreenInputProcessor implements InputProcessor{
private WorldController worldController;
private static final String TAG = GameScreenInputProcessor.class.getName();
public GameScreenInputProcessor(WorldController worldController){
	this.worldController = worldController;
	
}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.A){
			worldController.gameObject.velocity.set(-worldController.gameObject.terminalVelocity.x,0);
		    
		
		}
		if(keycode == Input.Keys.W){
			worldController.gameObject.velocity.set(0,worldController.gameObject.terminalVelocity.y);
			
		}
		if(keycode == Input.Keys.D){
			worldController.gameObject.velocity.set(worldController.gameObject.terminalVelocity.x,0);
			
		}
		if(keycode == Input.Keys.S){
			worldController.gameObject.velocity.set(0,-worldController.gameObject.terminalVelocity.y);
			
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.A){
			worldController.gameObject.velocity.set(-worldController.gameObject.terminalVelocity.x,0);
		    
		
		}
		if(keycode == Input.Keys.W){
			worldController.gameObject.velocity.set(0,worldController.gameObject.terminalVelocity.y);
			
		}
		if(keycode == Input.Keys.D){
			worldController.gameObject.velocity.set(worldController.gameObject.terminalVelocity.x,0);
			
		}
		if(keycode == Input.Keys.S){
			worldController.gameObject.velocity.set(0,-worldController.gameObject.terminalVelocity.y);
			
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if(character == 'a'){
			worldController.gameObject.velocity.add(-worldController.gameObject.terminalVelocity.x,0);
		    worldController.level.monsterArray.first().velocity.add(-worldController.level.monsterArray.first().terminalVelocity.x,0);
		
		}
		if(character == 'w'){
			worldController.gameObject.velocity.add(0,worldController.gameObject.terminalVelocity.y);
			
		}
		if(character == 'd'){
			worldController.gameObject.velocity.add(worldController.gameObject.terminalVelocity.x,0);
			
		}
		if(character == 's'){
			worldController.gameObject.velocity.add(0,-worldController.gameObject.terminalVelocity.y);
			
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 clicked = new Vector3(screenX,screenY,0);
	   
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		worldController.cameraHelper.addZoom(((float)amount/2));
		Gdx.app.debug(TAG,"Scroll: "+worldController.cameraHelper.getZoom());
		return false;
	}

}
