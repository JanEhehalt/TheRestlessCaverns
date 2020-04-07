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
    
    public MapGenerator(String tiles){
        this.tiles = new Texture(Gdx.files.internal(tiles));
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
        int width = l.getRooms().length;
        int height = l.getRooms()[0].length;
        
        TiledMap[][] tempLevel = new TiledMap[width][height];
        
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Room room = l.getRooms()[x][y];
                
                TiledMap tempRoom = generateRoom(room);
                tempLevel[x][y] = tempRoom;
            }
        }
        
        return tempLevel;
    }
    
    private TiledMap generateRoom(Room r){
        TiledMap tempRoom = new TiledMap();
        
        return tempRoom;
    }
}
