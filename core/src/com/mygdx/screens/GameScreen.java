package com.mygdx.screens;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.Global.Assets;
import com.mygdx.OwnInputProcessor.GameScreenInputProcessor;
import com.mygdx.OwnInputProcessor.GameScreenStage;
import com.mygdx.game.GameUml;
import com.mygdx.game.WorldController;
import com.mygdx.game.WorldRenderer;

public class GameScreen extends AbstractGameScreen {
	 private WorldController worldController;
	 private WorldRenderer worldRenderer;
	 private Stage stage;
	 private GameScreenStage gameScreenStage;
	 private GameScreenInputProcessor gameScreenInputProcessor;
	 private InputMultiplexer multiplexer = new InputMultiplexer();
	 
	 public GameScreen(GameUml game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void render(float deltaTime) {
		// Sets the clear screen color to: Cornflower Blue
		Gdx.gl.glClearColor(0x64 / 255.0f,0x95 / 255.0f,0xed / 255.0f, 0xff / 255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldController.update(deltaTime);
		worldRenderer.render();
		stage.act();
		stage.draw();
		Table.drawDebug(stage);
	    gameScreenStage.handleTouchpad();
		
		
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize();
		stage.getViewport().update(width,height,true);
		
	}

	@Override
	//Called when GameScreen is set to actual Screen
	public void show() {
		
		worldController = new WorldController(game);
		worldRenderer = new WorldRenderer(worldController);
		gameScreenInputProcessor = new GameScreenInputProcessor(worldController);
		gameScreenStage = new GameScreenStage(game,worldController);
		stage = gameScreenStage.getStage();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(gameScreenInputProcessor);
		Gdx.input.setInputProcessor(multiplexer);
		gameScreenStage.setGameObject(worldController.gameObject);
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
