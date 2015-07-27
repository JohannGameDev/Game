package com.mygdx.factory;



import SaveHelper.Slot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntMap.Values;
import com.badlogic.gdx.utils.Json;

import com.mygdx.jsonHelper.IntMapSerializer;


public class SlotFactory {
private static IntMap<Slot> slotMap;

@SuppressWarnings("unchecked")
public static void instantiate(){
	Json json = new Json();
	
	json.addClassTag("slot", Slot.class);
    json.setSerializer(IntMap.class, new IntMapSerializer());
	slotMap = json.fromJson(IntMap.class,Gdx.files.internal("json/SlotInfo.json"));
}
public static Array<Slot> getSlots(){
	Array<Slot> slotArray = new Array<Slot>();
	for (Values<Slot> iterator = slotMap.values(); iterator.hasNext();) {
		Slot slot = (Slot) iterator.next();
		slotArray.add(slot);
	}
	return slotArray;
	
}

}
