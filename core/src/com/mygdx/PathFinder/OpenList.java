package com.mygdx.PathFinder;

public interface OpenList {
//put Node in the OpenList
public void putNode(MyNode node);
//get Node with lowest totalCost Value and delete it
public MyNode getBestNode();

public void deleteNode(MyNode node);
//return weather or not Node is in OpenList
public boolean containsNode(MyNode node);
// is OpenList empty?
public boolean isEmpty();
//clear the list
public void clear();
}
