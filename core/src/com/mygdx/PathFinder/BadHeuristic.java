package com.mygdx.PathFinder;

public class BadHeuristic implements AStarHeuristic {

	
	public float getCost(int neighbourX, int neighbourY, int currentX,
			int currentY) {
		double one = Math.abs(neighbourX - currentX);
		double two = Math.abs(neighbourY - currentY);
		double powOf = Math.pow(one, 2)+ Math.pow(two, 2);
		float dist = (float) Math.sqrt(powOf);
		return dist;
	}

	@Override
	public float getCost(MyNode from, MyNode to) {
		// TODO Auto-generated method stub
		return 0;
	}

}
