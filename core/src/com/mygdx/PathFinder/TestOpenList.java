package com.mygdx.PathFinder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BinaryHeap;

public class TestOpenList implements OpenList {
private static final String TAG = TestOpenList.class.getName();
public boolean[][] isInOpenListArray;
public TileMapAStarInfo tileInfo;
public BinaryHeap<MyNode> heap = new BinaryHeap<MyNode>();

public TestOpenList(TileMapAStarInfo tileInfo){
	this.tileInfo = tileInfo;
	isInOpenListArray = new boolean[tileInfo.tilemapWidth][tileInfo.tilemapHeight];
}
	@Override
	public void putNode(MyNode node) {
		
		isInOpenListArray[node.tX][node.tY] = true;
		heap.add(node, node.getTotalCost());
	
		
	}

	@Override
	public MyNode getBestNode() {
		
		return heap.peek();
	}

	@Override
	public void deleteNode(MyNode node) {
		isInOpenListArray[node.tX][node.tY] = false;
		heap.remove(node);
		
	}

	@Override
	public boolean containsNode(MyNode node) {
		// TODO Auto-generated method stub
		return 	isInOpenListArray[node.tX][node.tY];
	}

	@Override
	public boolean isEmpty() {
		boolean isEmpty = false;
		if(heap.size == 0)
			isEmpty = true;
		return isEmpty;
	}

	@Override
	public void clear() {
		for (int i = 0; i < tileInfo.tilemapWidth; i++) {
			for (int j = 0; j < tileInfo.tilemapWidth; j++) {
				isInOpenListArray[i][j] = false;
			}
		}
		heap.clear();
		
	}
	
	public void updateValueNode(MyNode node){
		heap.setValue(node, node.getTotalCost());
	}

}
