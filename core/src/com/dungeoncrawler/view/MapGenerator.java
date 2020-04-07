/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.dungeoncrawler.model.Dungeon;
import com.dungeoncrawler.model.Level;
import com.dungeoncrawler.model.Room;

/**
 *
 * @author jonathan
 */
public class MapGenerator {
    Texture tiles;
    TextureRegion[][] splitTiles;
    
    public MapGenerator(Texture tiles){
        this.tiles = tiles;
        splitTiles = TextureRegion.split(this.tiles, 48, 48);
    }
    
    public TiledMap[][][] generateMap(int levelAmount, Dungeon d){
        TiledMap[][][] tempMap = new TiledMap[levelAmount][][];
        
        for(int i = 0; i < levelAmount; i++){
            TiledMap[][] tempLevel = generateLevel(i, d.getLevel()[i]);
            tempMap[i] = tempLevel;
        }
        
        return tempMap;
    }
    
    private TiledMap[][] generateLevel(int i, Level l){
        int sizeX = l.getRooms().length;
        int sizeY = l.getRooms()[0].length;
        
        TiledMap[][] tempLevel = new TiledMap[sizeX][sizeY];
        
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                Room room = l.getRooms()[x][y];
                
                if(room != null){
                    TiledMap tempRoom = generateRoom(room, sizeX, sizeY);
                    tempLevel[x][y] = tempRoom;
                }
                else{
                    tempLevel[x][y] = null;
                }
            }
        }
        
        return tempLevel;
    }
    
    private TiledMap generateRoom(Room r, int sizeX, int sizeY){
        TiledMap tempRoom = new TiledMap();
        
        MapLayers layers = tempRoom.getLayers();
        TiledMapTileLayer collisionLayer = new TiledMapTileLayer(7, 5, 48, 48);
        TiledMapTileLayer dynamicLayer = new TiledMapTileLayer(7, 5, 48, 48);
        TiledMapTileLayer staticLayer = new TiledMapTileLayer(7, 5, 48, 48);
        
        for(int x = 0; x < 7; x++){
            for(int y = 0; y < 5; y++){
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(new StaticTiledMapTile(splitTiles[0][0]));
                staticLayer.setCell(x, y, cell);
            }
        }
        
        layers.add(collisionLayer);
        layers.add(dynamicLayer);
        layers.add(staticLayer);
        
        return tempRoom;
    }
}
