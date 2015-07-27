package SaveHelper;

import com.badlogic.gdx.utils.Array;
import com.mygdx.factory.SlotFactory;

public class SlotHelper {
	
public static Array<Slot> slotArray;

private static Slot currentSlot;

public static void init(){
	SlotFactory.instantiate();
	slotArray = SlotFactory.getSlots();
	
}
public static void setCurrentSlot(Slot newSlot){
	currentSlot = newSlot;
}
public static void setDebugCurrentSlot(int newSlot){
	currentSlot = slotArray.get(newSlot);
}
public static Slot getCurrentSlot(){
	
	return currentSlot;
	
}

public static void saveCurrentSlot(Slot latestSlot){
	
	currentSlot = latestSlot;
}


}
