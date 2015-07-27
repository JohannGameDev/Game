package com.mygdx.PathFinder;

public interface AStarHeuristic {
public float getCost(MyNode from,MyNode to);

public float getCost(int neighbourX, int neighbourY, int currentX, int currentY);



}
