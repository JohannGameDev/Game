package com.mygdx.game;

import SaveHelper.SlotHelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.GameObjects.AttackAbleGameObject;
import com.mygdx.GameObjects.OwnCharacter;
import com.mygdx.Global.Assets;
import com.mygdx.PathFinder.BadHeuristic;
import com.mygdx.PathFinder.FastPathFinder;
import com.mygdx.PathFinder.MyNode;
import com.mygdx.PathFinder.PathFinder;
import com.mygdx.PathFinder.TileMapAStarInfo;
import com.mygdx.factory.LevelInfoFactory;
import com.mygdx.factory.SkillFactory;

public class WorldController {
	public static final String TAG = WorldController.class.getName();

	
	GameUml gameUml;
	public CameraHelper cameraHelper;
	public OwnCharacter gameObject;

	public FastPathFinder pathFinder;
	
	TextureMapObject devilMapObject;
	TextureRegion deviltexture;

	public Level level;


	public Array<Integer> globalTestPathArray = new Array<Integer>();

	public WorldController(GameUml gameUml) {
		this.gameUml = gameUml;
		init();
	}

	private void init() {
		SkillFactory.instantiate();
		cameraHelper = new CameraHelper();
		
		
		LevelInfoFactory.instantiate();
		level = new Level(LevelInfoFactory.getLevelInfo(SlotHelper
				.getCurrentSlot().level));
		Gdx.app.debug(TAG,SlotHelper.getCurrentSlot().characterInfo.characterSkillInformation.objectSkills.first().skillName);
		
		gameObject = new OwnCharacter(SlotHelper.getCurrentSlot().characterInfo.statsInformation,
                SlotHelper.getCurrentSlot().characterInfo.characterSkillInformation,
                Assets.instance.assetCharacterGreenImp);
		gameObject.position.x = 10;
		gameObject.position.y = 10;
		Assets.instance.initSkills(SlotHelper.getCurrentSlot().characterInfo.characterSkillInformation);
		cameraHelper.setTarget(gameObject);
		
		//pathfinder
		//pathFinder = new PathFinder(new TileMapAStarInfo(Assets.instance.map),new BadHeuristic());
		pathFinder = new FastPathFinder(new TileMapAStarInfo(Assets.instance.map),new BadHeuristic());
		//pathFinder.findPath(5, 5, 18, 18);

	}

	public void update(float deltaTime) {
		cameraHelper.update(deltaTime);
		gameObject.update(deltaTime);
		//updatePath();
		
		//level.update(deltaTime);
		//SkillController.update(deltaTime);
		
		

	}

	/*private void updatePath() {
		for (int i = 0; i < level.monsterArray.size; i++) {
			level.monsterArray.get(i).setPathArray(pathFinder.findPath(MathUtils.floor(level.monsterArray.get(i).position.x), MathUtils.floor(level.monsterArray.get(i).position.y), MathUtils.floor(gameObject.position.x), MathUtils.floor(gameObject.position.y)));
		}
		
	}*/

}
