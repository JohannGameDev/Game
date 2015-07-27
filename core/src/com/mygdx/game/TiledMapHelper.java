package com.mygdx.game;

import level.TiledMapInfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Global.Assets;

public class TiledMapHelper {
	public static final String TAG = TiledMapHelper.class.getName();
	TiledMapInfo tiledMapInfo;
	int[] renderLayers;
	Array<TiledMapTileLayer> tileLayers = new Array<TiledMapTileLayer>(); //array for tileMapTileLayers to draw on actual level
	
public TiledMapHelper(TiledMapInfo tiledMapInfo){
	
	init(tiledMapInfo);
}
private void init(TiledMapInfo tiledMapInfo) {
	
	this.tiledMapInfo = tiledMapInfo;
	
	for (int i = 0; i < tiledMapInfo.tiledLayers.size; i++) {
		Gdx.app.debug(TAG, tiledMapInfo.tiledLayers.get(i));
		tileLayers.add((TiledMapTileLayer) Assets.instance.map.getLayers().get(tiledMapInfo.tiledLayers.get(i)));
	}
	
	
	
	/*
	 * renderLayers = new int[tiledMapInfo.tiledLayers.size];
	 * int indexLayer = tiledMapInfo.tiledLayers.size;
	 for (int i = 0; i < Assets.instance.map.getLayers().getCount(); i++) {
		 for (int j = 0; j < tiledMapInfo.tiledLayers.size; j++) {
			if(Assets.instance.map.getLayers().get(i).getProperties().get("level").toString().compareTo(tiledMapInfo.tiledLayers.get(j)) == 0){
				renderLayers[indexLayer] =(Integer) Assets.instance.map.getLayers().get(i).getProperties().get("index");
				indexLayer--;
		}
   	
   		
   	  }
   	  s
		
	}
	*/
	

}
public void renderTileMap(OrthogonalTiledMapRenderer mapRenderer, Batch batch){
	//Gdx.app.debug(TAG, "renderTileLayerSize: " + tileLayers.size);
	batch.begin();
	for (int i = tileLayers.size-1; i >= 0 ; i--) {
		//Gdx.app.debug(TAG, tileLayers.get(i).getName());
		mapRenderer.renderTileLayer(tileLayers.get(i));
		}
	batch.end();
	
}
}
