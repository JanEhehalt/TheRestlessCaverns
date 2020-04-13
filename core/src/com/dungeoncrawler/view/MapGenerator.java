/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
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
    
    public TiledMap[][][] generateMap(Dungeon d){
        int levelAmount = d.getLevel().length;
     
        TiledMap[][][] tempMap = new TiledMap[levelAmount][][];
        
        // Jedes Level wird generiert
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
                    
                    // Raum wird generiertf
                    TiledMap tempRoom = generateRoom(room, tempX, tempY);
                    
                    // Wenn es Fehler gibt, dann wohl hier: Viel Spaß beim Suchen!        Danke!
                    TiledMapTileLayer temp = (TiledMapTileLayer) tempRoom.getLayers().get(2);
                    
                    // Ausgang oben
                    if(y < l.getRooms()[0].length - 1 && l.getRooms()[x][y + 1] != null){
                        
                        // X: Exakte Mitte der Gesamtlänge, Y: Gesamtlänge
                        temp.getCell((tempX / 2) + 1, tempY + 1).setTile(new StaticTiledMapTile(splitTiles[0][3])); //oben
                    }
                    
                    // Ausgang rechts    
                    if(x < l.getRooms().length - 1 && l.getRooms()[x + 1][y] != null){
                        
                        // X: Gesamtlänge, Y: Exakte Mitte der Gesamtlänge
                        temp.getCell(tempX + 1, (tempY / 2) + 1).setTile(new StaticTiledMapTile(splitTiles[0][3])); //rechts
                    }
                    
                    // Ausgang unten
                    if(y > 0 && l.getRooms()[x][y - 1] != null){
                        
                        // X: Exakte Mitte der Gesamtlänge, Y: 0
                        temp.getCell((tempX / 2) + 1, 0).setTile(new StaticTiledMapTile(splitTiles[0][3])); //unten
                    }
                    
                    // Ausgang links
                    if(x > 0 && l.getRooms()[x - 1][y] != null){
                        
                        // X: 0, Y: Exakte Mitte der Gesamtlänge
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
        
        // roomDimension bezieht sich auf die Größe des Raumes, da aber noch die Wände fehlen,
        // muss auf die Größe jeweils 2 addiert werden.
        int mapDimensionX = roomDimensionX + 2;
        int mapDimensionY = roomDimensionY + 2;
        
        // Drei layer für die Map werden erstellt, die jeweils unterschiedliche Sachen speichern sollen:
        // collisionLayer: Hier werden alle unsichtbaren Sprites gespeichert, anhand derer die Kollisionen berechnet werden
        // dynamicLayer: Beinhaltet alle Sprites, die ihre Postition ändern können (Entities, Items)
        // staticLayer: Beinhaltet alle Tiles und alles statische, was sich im Raum nicht ändern kann
        MapLayers layers = tempRoom.getLayers();
        
        MapLayer collisionLayer = new MapLayer();
        TiledMapTileLayer dynamicLayer = new TiledMapTileLayer(mapDimensionX, mapDimensionY, 48, 48);
        TiledMapTileLayer staticLayer = new TiledMapTileLayer(mapDimensionX, mapDimensionY, 48, 48);
        
        // Schleife läuft über jedes Teil des Raumes und generiert ein Tile aus dem tileset
        for(int x = 0; x < mapDimensionX + 1; x++){
            for(int y = 0; y < mapDimensionY + 1; y++){
                
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                
                // Test, ob Tile eine Wand sein muss
                if(x == 0 || x == mapDimensionX - 1 || y == 0 || y == mapDimensionY - 1){
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][5]));
                }
                else{
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][0]));
                }
                
                staticLayer.setCell(x, y, cell);
            }
        }
        
        RectangleMapObject collision = new RectangleMapObject(48, 48, roomDimensionX*48, roomDimensionY*48);
        collisionLayer.getObjects().add(collision);
        
        collisionLayer.setVisible(false);
        
        layers.add(collisionLayer);
        layers.add(dynamicLayer);
        layers.add(staticLayer);
        
        return tempRoom;
    }
    
    public void ichWillSpielen(TiledMap[][][] map){

        for(int i=0;i<map.length;i++){
            TiledMap[][] temp = map[i];

            System.out.println("MapLevel " + i);

            for(int j = 0; j < temp.length; j++){

                for(int k = temp[j].length - 1; k >= 0; k--){
                    if(temp[j][k] == null){
                        System.out.print("0 ");
                    }
                    else{
                        System.out.print("1 ");
                    }
                }
                System.out.println();
            }
        }
    }
}
