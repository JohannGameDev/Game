package com.mygdx.ObjectInfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.mygdx.factory.SkillFactory;

public class ObjectSkillsInformation {
public final static String TAG = ObjectSkillsInformation.class.getName();
public Array<Skill> objectSkills;
public Skill getSkill(String skillName){
	Skill tmp = null;
	
	for (int i = 0; i < objectSkills.size; i++) {
		if(objectSkills.get(i).skillName.compareTo(skillName)== 0){
			tmp = objectSkills.get(i);
		}
	}
	return tmp;
	
}
public void init(){
	
	for (int i = 0; i < objectSkills.size; i++) {
		Gdx.app.debug(TAG, SkillFactory.getSkillInfo("BoltSizzle").skillName);
		objectSkills.get(i).skillInfo = SkillFactory.getSkillInfo(objectSkills.get(i).skillName);
	}
}

}
