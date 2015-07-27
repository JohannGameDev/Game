package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.OwnInputProcessor.SlotScreenStage;
import com.mygdx.game.GameUml;

public class SlotScreen extends AbstractGameScreen {
    private SlotScreenStage slotScreenStage;
    private Stage stage;
    
	public SlotScreen(GameUml game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(100, 200, 33, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		
	
		Table.drawDebug(stage);
		
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width,height,true);
		
	}

	@Override
	public void show() {
		
		slotScreenStage = new SlotScreenStage(game);
		stage = slotScreenStage.getSlotScreenStage();
		Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
