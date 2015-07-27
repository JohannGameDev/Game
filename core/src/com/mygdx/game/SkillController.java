package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.GameObjects.AbstractGameObject;
import com.mygdx.GameObjects.SkillAOEObject;

public class SkillController {

	public static Array<SkillAOEObject> skillArray  = new Array<SkillAOEObject>();
	public static void update(float delta){
		for (int i = 0; i < skillArray.size; i++) {
			skillArray.get(i).update(delta);
			
		}
	}
	public static void render(Batch batch){
		for (int i = 0; i < skillArray.size; i++) {
			if(!skillArray.get(i).isSkillOver)
			skillArray.get(i).render(batch);
			
		}
	}
}
