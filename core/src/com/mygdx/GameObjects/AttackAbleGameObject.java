package com.mygdx.GameObjects;

import com.mygdx.ObjectInfo.AbstractStatsInfo;
import com.mygdx.ObjectInfo.ObjectSkillsInformation;
import com.mygdx.assets.AbstractAssetInfo;


public class AttackAbleGameObject extends DamageAbleGameObjects{
    ObjectSkillsInformation skillInformation;
	public AttackAbleGameObject(AbstractStatsInfo statsInfo,ObjectSkillsInformation skillInformation,AbstractAssetInfo assetInfo) {
		super(statsInfo,assetInfo);
		this.skillInformation = skillInformation;
		
	}

}
