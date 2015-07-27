package com.mygdx.GameObjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.ObjectInfo.AbstractStatsInfo;
import com.mygdx.assets.AbstractAssetInfo;


public class DamageAbleGameObjects extends AbstractGameObject{
    AbstractStatsInfo statsInfo;
	public DamageAbleGameObjects(AbstractStatsInfo statsInfo,AbstractAssetInfo assetInfo){
		super(assetInfo);
		this.statsInfo = statsInfo;
	}
	@Override
	public void render(Batch batch) {
		// TODO Auto-generated method stub
		
	}
	public void handleDamage(){
		
	}

}
