/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

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
                    int tempX = 7;
                    int tempY = 5;
                    
                    TiledMap tempRoom = generateRoom(room, tempX, tempY);
                    
                    // Wenn es Fehler gibt, dann wohl hier: Viel Spaß beim Suchen!        Danke!
                    TiledMapTileLayer temp = (TiledMapTileLayer) tempRoom.getLayers().get(2);
                    
                    // Ausgang oben
                    if(y < l.getRooms()[0].length - 1 && l.getRooms()[x][y + 1] != null){
                        temp.getCell((tempX / 2) + 1, tempY + 1).setTile(new StaticTiledMapTile(splitTiles[0][3])); //oben
                    }
                    
                    // Ausgang rechts
                        if(x > 0 && l.getRooms()[x - 1][y] != null){
                        temp.getCell(tempX + 1, (tempY / 2) + 1).setTile(new StaticTiledMapTile(splitTiles[0][3])); //rechts
                    }
                    
                    // Ausgang unten
                    if(y > 0 && l.getRooms()[x][y - 1] != null){
                        temp.getCell((tempX / 2) + 1, 0).setTile(new StaticTiledMapTile(splitTiles[0][3])); //unten
                    }
                    
                    // Ausgang links
                    if(x < l.getRooms().length - 1 && l.getRooms()[x + 1][y] != null){
                        temp.getCell(0, (tempY / 2) + 1).setTile(new StaticTiledMapTile(splitTiles[0][3])); //links
                    }
                    
                    tempLevel[x][y] = tempRoom;
                }
                
            }
        }
        
        return tempLevel;
    }
    
    private TiledMap generateRoom(Room r, int roomDimensionX, int roomDimensionY){
        TiledMap tempRoom = new TiledMap();
        
        int mapDimensionX = roomDimensionX + 2;
        int mapDimensionY = roomDimensionY + 2;
        
        MapLayers layers = tempRoom.getLayers();
        TiledMapTileLayer collisionLayer = new TiledMapTileLayer(mapDimensionX, mapDimensionY, 48, 48);
        TiledMapTileLayer dynamicLayer = new TiledMapTileLayer(mapDimensionX, mapDimensionY, 48, 48);
        TiledMapTileLayer staticLayer = new TiledMapTileLayer(mapDimensionX, mapDimensionY, 48, 48);
        
        for(int x = 0; x < mapDimensionX + 1; x++){
            for(int y = 0; y < mapDimensionY + 1; y++){
                
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                
                if(x == 0 || x == mapDimensionX - 1 || y == 0 || y == mapDimensionY - 1){
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][5]));
                }
                else{
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][0]));
                }
                
                staticLayer.setCell(x, y, cell);
            }
        }
        
        layers.add(collisionLayer);
        layers.add(dynamicLayer);
        layers.add(staticLayer);
        
        return tempRoom;
    }
}
