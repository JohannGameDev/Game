package com.mygdx.game.desktop;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.mygdx.game.GameUml;


public class DesktopGameUmlLauncher {
	public static boolean rebuildAtlas = false;
	
	public static void main (String[] arg) {
		if(rebuildAtlas){
			Settings settings = new Settings();
			
			settings.maxWidth = 4096;
			settings.maxHeight = 4096;
			settings.combineSubdirectories=true;
			
			TexturePacker.process(settings, "assets-raw/sprites",
			"../android/assets/images", 
			"sprites.pack");
			
			TexturePacker.process(settings, "assets-raw/skills",
					"../android/assets/images", 
					"skills.pack");
			
			TexturePacker.process(settings, "assets-raw/ui",
					"../android/assets/images", 
					"ui.pack");
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GameUml(), config);
		
	}
}