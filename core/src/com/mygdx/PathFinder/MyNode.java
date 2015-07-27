package com.mygdx.PathFinder;

import com.badlogic.gdx.utils.BinaryHeap.Node;

public class MyNode extends Node implements Comparable<Object>{
public int tX;//tile X
public int tY;//tile Y
private float cost;//cost to get to this Node
private float totalCost;//cost + heuristic
boolean blocked;//tile isbLockeD?
private MyNode parentNode;//from which is the immediate shortestpath to get to the Node

public MyNode(int tx,int ty,boolean blocked) {
	super(0);
	tX = tx;
	tY=ty;
	this.blocked = blocked;
	
}
public boolean isBlocked() {
	return blocked;
}
public void setBlocked(boolean blocked) {
	this.blocked = blocked;
}
public MyNode getParentNode() {
	return parentNode;
}

public void setParentNode(MyNode parentNode) {
	this.parentNode = parentNode;
}
public void setTotalCost(float heuristicValue){
	totalCost = cost + heuristicValue;
	
}
public float getTotalCost(){
	return totalCost;
}
public void setCost(float cost){
	this.cost = cost;
}
public float getCost(){
	return cost;
}
@Override
public int compareTo(Object other) {
	MyNode o = (MyNode) other;
	if(o.tX ==  tX && o.tY == tY){
		return 0;	
	}
	return 1;
}
}
