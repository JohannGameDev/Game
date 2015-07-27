package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.Global.Assets;
import com.mygdx.screens.MainMenuScreen;

public class GameUml extends Game{
	
   
	@Override
	public void create() {
		//set log
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		//Load assets
		Assets.instance.init(new AssetManager());
		this.setScreen(new MainMenuScreen(this));
		
	}
	

}
