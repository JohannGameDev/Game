package com.mygdx.game;

import java.util.Iterator;








import level.AmountMonster;
import level.LevelInfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.GameObjects.Monster;
import com.mygdx.Global.Assets;
import com.mygdx.ObjectInfo.MonsterInfo;
import com.mygdx.factory.MonsterInfoFactory;



public class Level {
	public static final String TAG = Level.class.getName();

	public Array<Monster> monsterArray = new Array<Monster>();
	TiledMapHelper tiledMapHelper;

	public Level(LevelInfo levelInfo) {
		init(levelInfo);
	}

	private void init(LevelInfo levelInfo) {

		Gdx.app.debug("Level.class", levelInfo.time + "");
		initAssets();
		for (Iterator<AmountMonster> iterator = levelInfo.monsterArray
				.iterator(); iterator.hasNext();) {
			AmountMonster amountMonster = (AmountMonster) iterator.next();
			for (int i = 0; i < amountMonster.amount; i++) {
				monsterArray.add(new Monster(
						        MonsterInfoFactory.getMonsterInfo(amountMonster.name).monsterStatsInfo,
								Assets.instance.assetMonsterMap.get(amountMonster.name),
								MonsterInfoFactory.getMonsterInfo(amountMonster.name).monsterSkills));
				
				monsterArray.get(i).position.set(MathUtils.random(1, 20-1),MathUtils.random(1, 20-1));// test
			}
		}
		tiledMapHelper = new TiledMapHelper(levelInfo.tiledMapInfo);

	}

	private void initAssets() {
		Assets.instance.initAssetMonster(MonsterInfoFactory.getMonsterInfoArray());
		
	}

	public void render(Batch batch) {
		for (Iterator<Monster> iterator = monsterArray.iterator(); iterator
				.hasNext();) {
			Monster tempMonster = (Monster) iterator.next();
			tempMonster.render(batch);
		}

	}

	public void update(float deltaTime) {
		for (Iterator<Monster> iterator = monsterArray.iterator(); iterator
				.hasNext();) {
			Monster tempMonster = (Monster) iterator.next();
			tempMonster.update(deltaTime);

		}
	}

}
