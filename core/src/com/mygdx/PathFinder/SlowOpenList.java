package com.mygdx.PathFinder;

import com.badlogic.gdx.utils.Array;

public class SlowOpenList implements OpenList{
	Array<MyNode> array = new Array<MyNode>();
	@Override
	public void putNode(MyNode node) {
		array.add(node);
		
	}

	@Override
	public MyNode getBestNode() {
		
		float bestCost = 0;
		int index = 0 ;
		for (int i = 0; i < array.size; i++) {
			
			if(array.get(i).getTotalCost() < bestCost){
				index = i;
				bestCost = array.get(i).getTotalCost();
			}
		}
		
		return array.get(index);
	}

	@Override
	public boolean containsNode(MyNode node) {
		
		return array.contains(node, true);
	}

	@Override
	public boolean isEmpty() {
		if(array.size == 0 )
			return true;
		
		return false;
	}

	@Override
	public void clear() {
	array.clear();
	}

	@Override
	public void deleteNode(MyNode node) {
		array.removeValue(node, true);
		
	}

}
