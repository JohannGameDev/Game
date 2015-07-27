package com.mygdx.PathFinder;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


public class TileMapAStarInfo {

	private static final String TAG = TileMapAStarInfo.class.getName();
	public TiledMap map;
	
	public int tilemapHeight,tilemapWidth,tileHeight,tileWidth;
    public TiledMapTileLayer tileMaplayer ;
    
	public TileMapAStarInfo(TiledMap map){
		this.map = map;
		for (Iterator<MapLayer> iterator = map.getLayers().iterator(); iterator.hasNext();) {
			TiledMapTileLayer type = (TiledMapTileLayer) iterator.next();
			Gdx.app.debug(TAG, ""+type.getName());
		}
		
	
		
		tileMaplayer = (TiledMapTileLayer) map.getLayers().get("mapLayer");
		//direkt anfragen
		tilemapHeight = tileMaplayer.getHeight();
		tilemapWidth = tileMaplayer.getWidth();
		tileHeight = (int) tileMaplayer.getTileHeight();
		tileWidth = (int) tileMaplayer.getTileWidth();
	}
	public boolean isBlocked(int tx,int ty){
		boolean blocked = false;
		if(tileMaplayer.getCell(tx, ty).getTile().getProperties().containsKey("Blocked"))
			blocked = true;
		return blocked;
		
	}
	
}
