package com.mygdx.PathFinder;

public class TestClosedList implements ClosedList {
	public boolean[][] isInClosedListArray;
	public TileMapAStarInfo tileInfo;
	
	public TestClosedList(TileMapAStarInfo tileInfo){
		this.tileInfo = tileInfo;
		isInClosedListArray = new boolean[tileInfo.tilemapWidth][tileInfo.tilemapHeight];
	}
	@Override
	public void putNode(MyNode node) {
		isInClosedListArray[node.tX][node.tY] = true;
		
	}

	@Override
	public void clear() {
		for (int i = 0; i < tileInfo.tilemapWidth; i++) {
			for (int j = 0; j < tileInfo.tilemapWidth; j++) {
				isInClosedListArray[i][j] = false;
			}
		}
		
	}

	@Override
	public boolean containsNode(MyNode node) {
		
		return isInClosedListArray[node.tX][node.tY];
	}

}
