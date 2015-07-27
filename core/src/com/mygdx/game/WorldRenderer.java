package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.Global.Assets;
import com.mygdx.Global.Constants;
import com.mygdx.factory.LevelInfoFactory;



public class WorldRenderer implements Disposable {
	public static final String TAG = WorldRenderer.class.getName();
	
	WorldController worldController;
	SpriteBatch batch;
	OrthographicCamera camera;
	DebugShapeRenderer shapeRenderer;

	Texture devielTexture;
	
	OrthogonalTiledMapRenderer renderer;
	private boolean accelerometerAvailable;
	

	public WorldRenderer(WorldController worldController) {
		this.worldController = worldController;
		init();

	}

	public void render() {
		System.out.println(Gdx.graphics.getFramesPerSecond());
     // handleAndroidInput();
	  worldController.cameraHelper.applyTo(camera);
      renderer.setView(camera);
      batch.setProjectionMatrix(camera.combined);
     // worldController.level.tiledMapHelper.renderTileMap(renderer,batch);
      renderer.render();
      worldController.gameObject.render(batch);
   //   worldController.level.render(batch);
     // SkillController.render(batch);
    //  shapeRenderer.render(camera);
      }

	private void init() {
	
	
	    shapeRenderer = new DebugShapeRenderer(worldController);
		renderer = new OrthogonalTiledMapRenderer(Assets.instance.map, Constants.UNITSCALE);
		batch = (SpriteBatch) renderer.getSpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16,16);
        camera.update();
        accelerometerAvailable = Gdx.input.isPeripheralAvailable(
        		Peripheral.Accelerometer);
        LevelInfoFactory.instantiate();
        
        
	}

	public void resize() {
		
		
	}

public void handleAndroidInput(){
		if (accelerometerAvailable) {
			// normalize accelerometer values from [-10, 10] to [-1, 1]
			// which translate to rotations of [-90, 90] degrees
			float amount = Gdx.input.getAccelerometerY() / 10.0f;
			amount *= 90.0f;
			// is angle of rotation inside dead zone?
			if (Math.abs(amount) < 5) {
			amount = 0;
			} else {
			// use the defined max angle of rotation instead of
			// the full 90 degrees for maximum velocity
			amount /= 20;
			}
			worldController.gameObject.velocity.x = worldController.gameObject.terminalVelocity.x * amount;
			
			float amount2 = Gdx.input.getAccelerometerX() / 10.0f;

			amount2 *= 90.0f;
			// is angle of rotation inside dead zone?
			if (Math.abs(amount2) < 5) {
			amount2 = 0;
			} else {
			// use the defined max angle of rotation instead of
			// the full 90 degrees for maximum velocity
			amount2 /= 20;
			}
			worldController.gameObject.velocity.y = worldController.gameObject.terminalVelocity.y * amount2;
			
			}
		
	}

	@Override
	public void dispose() {

		
	}

}
