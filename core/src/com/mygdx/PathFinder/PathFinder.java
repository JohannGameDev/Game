package com.mygdx.PathFinder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class PathFinder {

	private static final String TAG = PathFinder.class.getName();
	public TileMapAStarInfo tileMapAStarInfo;
	private AStarHeuristic aStarHeuristic;
	public MyNode[][] nodes;
	private ClosedList closedList ;
	private TestOpenList openList ;
	
	public PathFinder(TileMapAStarInfo tileMapAStarInfo ,AStarHeuristic aStarHeuristic){
		
		this.tileMapAStarInfo = tileMapAStarInfo;
		this.aStarHeuristic = aStarHeuristic;
		openList = new TestOpenList(tileMapAStarInfo);
		closedList = new TestClosedList(tileMapAStarInfo);
		nodes = new MyNode[this.tileMapAStarInfo.tilemapWidth][this.tileMapAStarInfo.tilemapHeight];
		for (int x=0;x<this.tileMapAStarInfo.tilemapWidth;x++) {
			for (int y=0;y<this.tileMapAStarInfo.tilemapHeight;y++) {
				nodes[x][y] = new MyNode(x,y,this.tileMapAStarInfo.isBlocked(x, y));
				
			}
		}
		
	}
	public Array<MyNode> findPath(int sx,int sy,int tx,int ty){
	if(nodes[tx][ty].isBlocked())
		return null;
	
		long startTime = TimeUtils.nanoTime();
	
		long endTime = 0;
		float temp_g_score = 0 ;
		MyNode current = null;
		MyNode goalNode = new MyNode(tx, ty, false);
		
		nodes[sx][sy].setParentNode(null);
		nodes[sx][sy].setCost(0);
		openList.clear();
		closedList.clear();
		
		openList.putNode(nodes[sx][sy]);
		
		while(!openList.isEmpty()){
			current = openList.getBestNode();
			
			if(current.compareTo(goalNode)==0){
				endTime = (TimeUtils.nanoTime() - startTime);
				//Gdx.app.debug(TAG,"seconds to get Path: " + endTime);
				return pathArray(current);
			}
			
			openList.deleteNode(current);
			closedList.putNode(current);
			
			for (int x=-1;x<2;x++) {
				for (int y=-1;y<2;y++) {
					// not a neighbour, its the current tile
					
					if ((x == 0) && (y == 0)) 
						continue;
					
					int xp = x + current.tX;
					int yp = y + current.tY;
					
					if(xp<=0 || yp <=0 || yp >=tileMapAStarInfo.tilemapHeight-2 || xp >=tileMapAStarInfo.tilemapWidth -2) continue;
					
					if(closedList.containsNode(nodes[xp][yp]) || nodes[xp][yp].isBlocked())
						continue;
					
				temp_g_score  = current.getCost() + distBetweent(current,nodes[xp][yp]);
				if(openList.containsNode(nodes[xp][yp]) && nodes[xp][yp].getCost() > temp_g_score){
					nodes[xp][yp].setParentNode(current);
					nodes[xp][yp].setCost(temp_g_score);
					nodes[xp][yp].setTotalCost(temp_g_score+ aStarHeuristic.getCost(nodes[xp][yp], goalNode));
					openList.updateValueNode(nodes[xp][yp]);
					
				}
				if(!openList.containsNode(nodes[xp][yp])){
					nodes[xp][yp].setParentNode(current);
					nodes[xp][yp].setCost(temp_g_score);
					nodes[xp][yp].setTotalCost(temp_g_score+ aStarHeuristic.getCost(nodes[xp][yp], goalNode));
					openList.putNode(nodes[xp][yp]);
				}
			}
			
			
			
		}
		
		
		
		
	}
		return null;
}
	private Array<MyNode> pathArray(MyNode goalNode) {
		MyNode tmpNode = goalNode;

		Array<MyNode> path = new Array<MyNode>();
		path.add(goalNode);
		while(tmpNode.getParentNode() != null){
			
			path.add(tmpNode.getParentNode());
			tmpNode = tmpNode.getParentNode();
		}
		 path.reverse();
		 return path;
	}
	private float distBetweent(MyNode current, MyNode myNode) {
		double one = Math.abs(myNode.tX - current.tX);
		double two = Math.abs(myNode.tY - current.tY);
		double powOf = Math.pow(one, 2)+ Math.pow(two, 2);
		float dist = (float) Math.sqrt(powOf);
		return dist ;
	}
}
