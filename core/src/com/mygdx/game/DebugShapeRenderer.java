package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.Global.Assets;
import com.mygdx.Global.Constants;
import com.mygdx.PathFinder.Cantor;
import com.mygdx.PathFinder.MyNode;

public class DebugShapeRenderer {
	MapLayer tilesLayer,objectLayer,spawnTilesLayer;
	MapObjects objects ;
	PolygonMapObject  poly;
	PolylineMapObject polyLine;
	Polygon mathPoly;
	EllipseMapObject ellipseMapObject;
	Ellipse mathEllipseSpawn1,mathEllipseSpawn2,mathEllipseSpawn3,mathEllipseSpawn4;
	private ShapeRenderer shapeRenderer;
	public float[] polyVertizes;
	private MapLayer objectLayer2;
	private MapLayer tilesLayer2;
	public WorldController worldController;
	public DebugShapeRenderer(WorldController worldController){
		//test Tilelayer
		this.worldController = worldController;
		shapeRenderer = new ShapeRenderer();
		/*objectLayer2 =  Assets.instance.map.getLayers().get("extendetSpawnBounds");
		tilesLayer2 = Assets.instance.map.getLayers().get("extendetSpawnLayer");
		tilesLayer = Assets.instance.map.getLayers().get("mapLayer");
		objectLayer =  Assets.instance.map.getLayers().get("spawn");
		spawnTilesLayer = Assets.instance.map.getLayers().get("spawnLayer");
	    objects = objectLayer.getObjects();
	    //texture
	    
	    //Ellipse
	    ellipseMapObject = (EllipseMapObject) objects.get("spawn1");
	    mathEllipseSpawn1 = new Ellipse(ellipseMapObject.getEllipse().x * Constants.UNITSCALE,ellipseMapObject.getEllipse().y * Constants.UNITSCALE,ellipseMapObject.getEllipse().width * Constants.UNITSCALE,ellipseMapObject.getEllipse().height * Constants.UNITSCALE);
	    ellipseMapObject = (EllipseMapObject) objects.get("spawn2");
	    mathEllipseSpawn2 = new Ellipse(ellipseMapObject.getEllipse().x * Constants.UNITSCALE,ellipseMapObject.getEllipse().y * Constants.UNITSCALE,ellipseMapObject.getEllipse().width * Constants.UNITSCALE,ellipseMapObject.getEllipse().height * Constants.UNITSCALE);
	    ellipseMapObject = (EllipseMapObject) objects.get("spawn3");
	    mathEllipseSpawn3 = new Ellipse(ellipseMapObject.getEllipse().x * Constants.UNITSCALE,ellipseMapObject.getEllipse().y * Constants.UNITSCALE,ellipseMapObject.getEllipse().width * Constants.UNITSCALE,ellipseMapObject.getEllipse().height * Constants.UNITSCALE);
	    ellipseMapObject = (EllipseMapObject) objects.get("spawn4");
	    mathEllipseSpawn4 = new Ellipse(ellipseMapObject.getEllipse().x * Constants.UNITSCALE,ellipseMapObject.getEllipse().y * Constants.UNITSCALE,ellipseMapObject.getEllipse().width * Constants.UNITSCALE,ellipseMapObject.getEllipse().height * Constants.UNITSCALE);
	    //Polygon
	    polyLine =  (PolylineMapObject) objects.get("polygon");
	    polyVertizes = polyLine.getPolyline().getVertices();
	    
	    for (int i = 0; i < polyVertizes.length; i++) {
	    	if(i % 2 == 0 ){
	    		polyVertizes[i] = (polyLine.getPolyline().getX() + polyVertizes[i] )* Constants.UNITSCALE ;
	    	}else{
	    		polyVertizes[i] =(polyLine.getPolyline().getY() + polyVertizes[i]) * Constants.UNITSCALE ;
	    	}
			
		}
	    mathPoly = new Polygon(polyVertizes);*/
	}
	public void render(OrthographicCamera camera){
          shapeRenderer.setProjectionMatrix(camera.combined);
		 
		 shapeRenderer.begin(ShapeType.Filled);
		 shapeRenderer.setColor(1, 1, 0, 1);
		 shapeRenderer.setColor(Color.ORANGE);
		for (int x=0;x<worldController.pathFinder.tilemapInfo.tilemapWidth;x++) {
			for (int y=0;y<worldController.pathFinder.tilemapInfo.tilemapHeight;y++) {
				if(!worldController.pathFinder.blockedTiles[x][y])
				 shapeRenderer.circle(x, y, 0.2f);
				 
				//Gdx.app.debug(TAG, "node x: "+ nodes[x][y].tX +" node y : "+ nodes[x][y].tY +"node blocked: "+ nodes[x][y].blocked);
			}
		}
		for (int i = 0; i < worldController.pathFinder.openList.size; i++) {
			shapeRenderer.circle(Cantor.computeX(worldController.pathFinder.openList.get(i)),Cantor.computeY(Cantor.computeX(worldController.pathFinder.openList.get(i))), 0.2f);
		}
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(MathUtils.floor(worldController.gameObject.position.x), MathUtils.floor(worldController.gameObject.position.y) ,0.2f, 10);
		shapeRenderer.end();
		
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.BLACK);
		
		/*for (int x=0;x<worldController.pathFinder.parentNode.length;x++) {
			for (int y=0;y<worldController.pathFinder.parentNode.length;y++) {
				if(worldController.pathFinder.parentNode[x][y] != 0 )
				 shapeRenderer.line(Cantor.computeX(worldController.pathFinder.parentNode[x][y]), Cantor.computeX(worldController.pathFinder.parentNode[x][y]), x, y);
				 
				//Gdx.app.debug(TAG, "node x: "+ nodes[x][y].tX +" node y : "+ nodes[x][y].tY +"node blocked: "+ nodes[x][y].blocked);
			}
		}
		*/
		
		for (int i = 0; i < worldController.level.monsterArray.size; i++) {
			if(worldController.level.monsterArray.get(i).pathArray != null){
				for (int j = 0; j < worldController.level.monsterArray.get(i).pathArray.size-1; j++) {
				
					shapeRenderer.line(worldController.level.monsterArray.get(i).pathArray.get(j).tX, worldController.level.monsterArray.get(i).pathArray.get(j).tY, worldController.level.monsterArray.get(i).pathArray.get(j+1).tX, worldController.level.monsterArray.get(i).pathArray.get(j+1).tY);
				}
			}
		}
		
		shapeRenderer.setColor(Color.ORANGE);
			if(worldController.globalTestPathArray != null){
				for (int j = 0; j < worldController.globalTestPathArray.size-3; j++) {
					if(j % 2 == 0){
					
					shapeRenderer.line(worldController.globalTestPathArray.get(j), worldController.globalTestPathArray.get(j+1),worldController.globalTestPathArray.get(j+2),worldController.globalTestPathArray.get(j+3));
					}
				}
			}
		
		
		 shapeRenderer.rect(worldController.gameObject.position.x, worldController.gameObject.position.y, worldController.gameObject.bounds.width, worldController.gameObject.bounds.width);
		 for (int i = 0; i < SkillController.skillArray.size; i++){
			 shapeRenderer.rect(SkillController.skillArray.get(i).position.x, SkillController.skillArray.get(i).position.y, SkillController.skillArray.get(i).bounds.width, SkillController.skillArray.get(i).bounds.height);
			
		}
		 for (int i = 0; i < worldController.level.monsterArray.size; i++){
			 shapeRenderer.rect(worldController.level.monsterArray.get(i).position.x, worldController.level.monsterArray.get(i).position.y, worldController.level.monsterArray.get(i).bounds.width, worldController.level.monsterArray.get(i).bounds.height);
			
		}
		 
		/* for (int i = 0; i < polyVertizes.length-3; i +=2) {
			 shapeRenderer.line(polyVertizes[i],polyVertizes[i+1], polyVertizes[i+2], polyVertizes[i+3]);
		}
		// shapeRenderer .rect(.testCharacter.position.x, worldController.testCharacter.position.y, worldController.testCharacter.bounds.width,worldController.testCharacter.bounds.height);
		 shapeRenderer.ellipse(mathEllipseSpawn1.x, mathEllipseSpawn1.y,mathEllipseSpawn1.width,mathEllipseSpawn1.height);
		 shapeRenderer.ellipse(mathEllipseSpawn2.x, mathEllipseSpawn2.y,mathEllipseSpawn2.width,mathEllipseSpawn2.height);
		 shapeRenderer.ellipse(mathEllipseSpawn3.x,mathEllipseSpawn3.y,mathEllipseSpawn3.width,mathEllipseSpawn3.height);
		 shapeRenderer.ellipse(mathEllipseSpawn4.x, mathEllipseSpawn4.y,mathEllipseSpawn4.width,mathEllipseSpawn4.height);
		*/
		 shapeRenderer.end();
	}
	public void update(){
		/*if(mathPoly.contains(testCharacter.position.x, testCharacter.position.y)) System.out.println("Intersects polygon");
		if(mathEllipseSpawn1.contains(testCharacter.position))System.out.println("Intersects spawn1");
		if(mathEllipseSpawn2.contains(testCharacter.position))System.out.println("Intersects spawn2");
		if(mathEllipseSpawn3.contains(testCharacter.position))System.out.println("Intersects spawn3");
		if(mathEllipseSpawn4.contains(testCharacter.position))System.out.println("Intersects spawn4");*/
	}
}
