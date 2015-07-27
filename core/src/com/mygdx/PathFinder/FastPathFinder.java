package com.mygdx.PathFinder;

import com.badlogic.gdx.utils.Array;

public class FastPathFinder {
	public int mapWidth;
	public int mapHeight;
	float fCost[][];
	float gCost[][];
	public int parentNode[][];
	public boolean blockedTiles[][];
	int whichList[][];
	int onOpenList = 0;
	int onClosedList = 1;
	float tileWidth;
	float tileHeight;
	public TileMapAStarInfo tilemapInfo;
	AStarHeuristic heuristic;
	public Array<Integer> openList = new Array<Integer>();
	
	public FastPathFinder(TileMapAStarInfo tilemapInfo,AStarHeuristic heuristic){
		this.tilemapInfo = tilemapInfo;
		this.heuristic = heuristic;
		
		mapWidth = tilemapInfo.tilemapWidth;
		mapHeight = tilemapInfo.tilemapHeight;
		tileHeight = tilemapInfo.tileHeight;
		tileWidth = tilemapInfo.tileWidth;
		
		blockedTiles = new boolean[mapWidth][mapHeight];
		parentNode = new int[mapWidth][mapHeight];
	
		
		fCost = new float[mapWidth][mapHeight];
		gCost = new float[mapWidth][mapHeight];
		whichList = new int[mapWidth][mapHeight];
		
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				blockedTiles[i][j] = tilemapInfo.isBlocked(i,j);
			}
		}
		System.out.println("mapWIDTH: " + mapWidth);
		System.out.println("mapWIDTH: " + mapHeight);
	}
	public Array<Integer> findPath(int tX,int tY,int gX,int gY){
		//wenn außerhalb der map
		if(gX > mapWidth || gX < 0 || gY > mapHeight || gY <0){
			return null;
		}
		if(blockedTiles[gX][gY])
			return null;
		int goalNodeId = Cantor.compute(gX, gY);
		
		
		float temp_G_Score = 0 ;
		int currentNodeId=0;
		int currentX = 0;
		int currentY =  0;
		int neighbourX = 0 ;
		int neighbourY = 0 ;
		int startId = Cantor.compute(tX, tY);
		gCost[tX][tY] = 0;
		fCost[tX][tY] = 1;
		
		openList.clear();
		onClosedList +=2;
		onOpenList = onClosedList-1;
		if(onOpenList > 3000){
			for (int i = 0; i < whichList.length; i++) {
				for (int j = 0; j < whichList.length; j++) {
					whichList[0][0] = 0 ;
					
				}
			}
		}
		
		openList.add(startId);
		whichList[tX][tY] = onOpenList;
		//solange knoten noch in der openList sind untersuche sie
		while(openList.size != 0){
			
			currentNodeId = getBestId();
			//System.out.println("compoutet currentNodeId:" +currentNodeId +" goald node id "+ goalNodeId);
			//shortest Path found
			if(currentNodeId == goalNodeId){
				System.out.println("found that bitch");
			
				return recontsructPath(startId,goalNodeId);
				
			}
			whichList[currentX][currentY] = onClosedList;
			currentX = Cantor.computeX(currentNodeId);
			currentY = Cantor.computeY(currentNodeId);
			
			
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				neighbourX = currentX + i;
				neighbourY = currentY + j;
				//falls außerhalb der karte
				if(( neighbourX == currentX && neighbourY == currentY)|| neighbourX >= mapWidth || neighbourX <= 0 || neighbourY >= mapHeight || neighbourY <= 0)
					continue;
				//falls kein Knoten
				if(blockedTiles[neighbourX][neighbourY])
					continue;
				if(whichList[neighbourX][neighbourY] == onClosedList )
					continue;
				
				
				
		
				
				if(onOpenList != whichList[neighbourX][neighbourY]){
					whichList[neighbourX][neighbourY] = onOpenList;
				    openList.add(Cantor.compute(neighbourX, neighbourY));
					parentNode[neighbourX][neighbourY] = currentNodeId;
					gCost[neighbourX][neighbourY] = gCost[currentX][currentY] + cost(neighbourX,neighbourY,currentX,currentY);
					fCost[neighbourX][neighbourY] = gCost[neighbourX][neighbourY] + heuristic.getCost(neighbourX,neighbourY,gX,gY);
				    
				}else{
					temp_G_Score = gCost[currentX][currentY] + cost(neighbourX,neighbourY,currentX,currentY);
					if(temp_G_Score < gCost[neighbourX][neighbourY]){
						gCost[neighbourX][neighbourY] =temp_G_Score;
						fCost[neighbourX][neighbourY] = gCost[neighbourX][neighbourY] + heuristic.getCost(neighbourX,neighbourY,gX,gY);
						
					}
						
				    	
				}
				
				
				}
				
			}
		}
			
		
		return null;
	}
	private float cost(int neighbourX, int neighbourY, int currentX,int currentY) {
		double one = Math.abs(neighbourX- currentX);
		double two = Math.abs(neighbourY - currentY);
		double powOf = Math.pow(one, 2)+ Math.pow(two, 2);
		float dist = (float) Math.sqrt(powOf);
		return dist ;
		
	}

	private int getBestId(){
		//System.out.println("before removing "+openList.toString());
		float tempValue = 3600;//max for map 60 * 60 
		int tempId = 0;
		int tempX = 0;
		int tempY = 0;
		int tempPosition = 0;
		float tempFCost = 0;
		for (int i = 0; i < openList.size; i++) {
			tempX = Cantor.computeX(openList.get(i));
			tempY = Cantor.computeY(openList.get(i));
			tempFCost = fCost[tempX][tempY];
			
			if(tempFCost <= tempValue){
				tempValue = tempFCost;
				tempId = openList.get(i);
				tempPosition = i;
			}
			
		}
		openList.removeIndex(tempPosition);
		//System.out.println("after removing "+openList.toString());
		
		return tempId;
	}
	private Array<Integer> recontsructPath(int startId,int goalId) {
		Array<Integer> tempPath = new Array<Integer>();
		int tempId = goalId;
		int pathX=0,pathY = 0;
		//System.out.println("inwhile");
		while(tempId !=startId){
		pathX = Cantor.computeX(tempId);
		pathY = Cantor.computeY(tempId);
		tempPath.add(pathY);
		tempPath.add(pathX);
		tempId = parentNode[pathX][pathY];
		//System.out.println("X:" + pathX + " Y : "+ pathY + "tempId "+ tempId);
		}
		//System.out.println("outwhile");
		tempPath.reverse();
		
		return tempPath;
	}
		
	}

