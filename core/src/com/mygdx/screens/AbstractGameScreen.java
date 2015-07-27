package com.mygdx.screens;


import com.badlogic.gdx.Screen;
import com.mygdx.game.GameUml;

public abstract class AbstractGameScreen implements Screen {
    protected GameUml game;
	public AbstractGameScreen(GameUml game){
		
		this.game =  game;
	}
	public abstract void render (float deltaTime);
	public abstract void resize (int width, int height);
	public abstract void show ();
	public abstract void hide ();
	public abstract void pause ();
	public void resume () {
	//Assets.instance.init(new AssetManager());
	}
	public void dispose () {
	//Assets.instance.dispose();
	}
}

