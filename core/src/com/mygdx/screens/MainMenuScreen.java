package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.OwnInputProcessor.MainMenuStage;
import com.mygdx.game.GameUml;

public class MainMenuScreen extends AbstractGameScreen {
	
	private static final String TAG = MainMenuScreen.class.getName();
	
	private MainMenuStage mainMenuStage;
	private Stage stage;
	
	public MainMenuScreen(GameUml game) {
		
		super(game);
		
	}

	@Override
	public void render(float deltaTime) {
	
			Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			stage.draw();
			Table.drawDebug(stage);
		
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width,height,true);
		
	}

	@Override
	public void show() {
		
		mainMenuStage = new MainMenuStage(game);
		stage = mainMenuStage.getMainMenuStage();
		Gdx.input.setInputProcessor(mainMenuStage.getMainMenuStage());
		
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
