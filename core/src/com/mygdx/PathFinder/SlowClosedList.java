package com.mygdx.PathFinder;

import com.badlogic.gdx.utils.Array;

public class SlowClosedList implements ClosedList {
 Array<MyNode> array = new Array<MyNode>();
	@Override
	public void putNode(MyNode node) {
		array.add(node);
		
	}

	@Override
	public boolean containsNode(MyNode node) {
		
			
		return array.contains(node, true);
	}

	@Override
	public void clear() {
		array.clear();
		
	}

}
