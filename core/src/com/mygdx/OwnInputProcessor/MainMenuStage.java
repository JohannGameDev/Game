package com.mygdx.OwnInputProcessor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.Global.Assets;
import com.mygdx.game.GameUml;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.SlotScreen;

public class MainMenuStage  {
	private Stage mainMenuStage;
	
	private GameUml game;
	private Skin skin;
	public Table table = new Table();
	
	
public MainMenuStage(GameUml game){
	this.game = game;
    init();	
}

private void init() {

	mainMenuStage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(),
			Gdx.graphics.getHeight()));
	
	table.setFillParent(true);
	table.debug();
	table.debugTable();
	
	
	skin = new Skin(Gdx.files.internal("userInterfaceSkins/myUiSkin.json"),Assets.instance.atlasUI);
	
	ImageButton startButton = new ImageButton(skin,"startStyle");
	ImageButton pauseButton = new ImageButton(skin,"pauseStyle");
	ImageButton selectButton = new ImageButton(skin,"selectStyle");
    
    
    
    startButton.addListener(new ClickListener(){
    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
    		
    		game.setScreen(new SlotScreen(game));
    		mainMenuStage.clear();
			return true;
    		
    	}
    });
    table.add(startButton).padBottom(10);
    table.row();
    table.add(selectButton).padBottom(10);
    table.row();
    table.add(pauseButton).padBottom(10);
    table.setBackground(skin.getDrawable("deadCover"));
    mainMenuStage.addActor(table);
    
	
}


public Stage getMainMenuStage(){
	return mainMenuStage;
	
}

}
