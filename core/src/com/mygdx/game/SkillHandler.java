package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.GameObjects.AbstractGameObject;
import com.mygdx.GameObjects.SkillAOEObject;
import com.mygdx.Global.Assets;
import com.mygdx.ObjectInfo.Skill;


public class SkillHandler {
	public final static String TAG = SkillHandler.class.getName();
public static void handleSkill(Skill skill,AbstractGameObject from,Vector2 touchDown){
	
	switch(skill.skillInfo.skillDamgeInfo.skillTyp){
	case "AOE":Gdx.app.debug(TAG, "AOE");
	    SkillController.skillArray.add(new SkillAOEObject(skill, new Vector2(from.position.x-from.origin.x,from.position.y-from.origin.y), Assets.instance.assetSkillMap.get(skill.skillName)));
	   
		break;
	
	default:break;
	}
	
}
}
