package com.mygdx.PathFinder;

public interface ClosedList {
	//put Node in the ClosedList
	public void putNode(MyNode node);
	//clear the list
	public void clear();
	//return weather or not Node is in ClosedList
	public boolean containsNode(MyNode node);
}
