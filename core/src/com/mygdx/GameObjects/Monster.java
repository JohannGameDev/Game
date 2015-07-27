package com.mygdx.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Global.Assets;
import com.mygdx.Global.Constants;
import com.mygdx.ObjectInfo.MonsterStatsInfo;
import com.mygdx.ObjectInfo.ObjectSkillsInformation;
import com.mygdx.PathFinder.MyNode;
import com.mygdx.assets.AssetMonster;


public class Monster extends AttackAbleGameObject{

   
	private static final String TAG = Monster.class.getName();
	AssetMonster assetMonster;
    TextureRegion idle;
    float stateTime;
    float oldStepX,oldStepY;
    int step;
    boolean isFinished  = true;
    Vector2 tmp = new Vector2();
    public Array<MyNode> pathArray = new Array<MyNode>();
    public Array<Integer> pathArray2 = new Array<Integer>(); 
    public Array<Integer> temppathArray2 = new Array<Integer>(); 
    public Array<MyNode> tempPath = new Array<MyNode>();
	public Monster(MonsterStatsInfo monsterStatsInfo,AssetMonster assetMonster,ObjectSkillsInformation skillsInformation){
		super(monsterStatsInfo,skillsInformation,assetMonster);
		this.assetMonster = assetMonster;
		init();
		
		
	}
	private void init() {
		idle = assetMonster.walk_DOWN.getKeyFrame(0);
		bounds.width = idle.getRegionWidth() * Constants.UNITSCALE;
		bounds.height = idle.getRegionHeight() * Constants.UNITSCALE;
		position.x = 0;
		position.y = 0;
		scale.x= 1f;
		scale.y =1f;
		origin.x = bounds.width/2;
		origin.y = bounds.height/2;
		assetMonster.walk_DOWN.setPlayMode(PlayMode.LOOP);
		terminalVelocity.set(2.0f, 2.0f);
		friction.set(6.0f, 6.0f);
		acceleration.set(0.0f, 0.0f);
	}
	@Override
	public void render(Batch batch) {
		batch.begin();
		batch.draw(idle,position.x,position.y,
				origin.x,origin.y,
				bounds.width,bounds.height,scale.x,scale.y,rotation);
		batch.end();
		
	}
	public void update(float deltaTime){
	
		if( isFinished  ){
			if(pathArray != null && pathArray.size != 0){
			isFinished = false;
			temppathArray2 = pathArray2;
			step = 0;}
		}else{
			//Gdx.app.debug(TAG, "position X : " + position.x +"position y : " + position.y + " target x: " + tempPath.get(step).tX + " target y: " + tempPath.get(step).tY);
			if(position.x > tempPath.get(step).tX-1 && position.x < tempPath.get(step).tX+1 && position.y > tempPath.get(step).tY-1 && position.y < tempPath.get(step).tY+1 ){
				step++;
			}else{
				position.lerp(new Vector2(tempPath.get(step).tX, tempPath.get(step).tY),1f * deltaTime);
			}
			if(step >= tempPath.size ){
				isFinished = true;
			}
				
		}
		super.update(deltaTime);
		stateTime += deltaTime;
		idle = assetMonster.walk_DOWN.getKeyFrame(stateTime);
	
		
	}
	public void setPathArray(Array<MyNode> pathArray){
		this.pathArray = pathArray;
	}
	
	public void setPathArrayNew(Array<Integer> findPath) {
		this.pathArray = pathArray;
		
	}

}
