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
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.dungeoncrawler.model.Dungeon;
import com.dungeoncrawler.model.Item;
import com.dungeoncrawler.model.ItemContainer;
import com.dungeoncrawler.model.Level;
import com.dungeoncrawler.model.Room;

/**
 *
 * @author jonathan
 */
public class MapGenerator {
    TextureRegion[][][] allTiles;
    TextureRegion[][] splitTiles;
    Texture torchT;
    Texture sword;
    
    public MapGenerator(Texture[] tiles){
        allTiles = new TextureRegion[7][][];
        
        for(int i = 0; i < tiles.length; i++){
            allTiles[i] = TextureRegion.split(tiles[i], 48, 48);
        }
        torchT = new Texture("sprites/torch.png");
        sword = new Texture("sprites/sword.png");
    }
    
    public Map generateMap(Dungeon d){
        Map temp = new Map();
        
        int levelAmount = d.getLevel().length;
     
        MapContainer[][][] tempMap = new MapContainer[levelAmount][][];
        
        // Jedes Level wird generiert
        for(int i = 0; i < levelAmount; i++){
            MapContainer[][] tempLevel = generateLevel(i, d.getLevel()[i]);
            tempMap[i] = tempLevel;
        }
        
        temp.setMaps(tempMap);
        return temp;
    }
    
    private MapContainer[][] generateLevel(int i, Level l){
        
        splitTiles = allTiles[i];
        
        int sizeX = l.getRooms().length;
        int sizeY = l.getRooms()[0].length;
        
        MapContainer[][] tempLevel = new MapContainer[sizeX][sizeY];
        
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                Room room = l.getRooms()[x][y];
                
                if(room != null){
                    int tempX = 9;
                    int tempY = 5;
                    
                    int mapDimensionX = tempX + 2;
                    int mapDimensionY = tempY + 2;
                    
                    // Raum wird generiert
                    MapContainer temp = generateRoom(room, tempX, tempY, i);
                    TiledMap tempRoom = temp.getMap();
                    
                    // Wenn es Fehler gibt, dann wohl hier: Viel Spaß beim Suchen!        Danke!
                    MapLayer collisionLayer = tempRoom.getLayers().get(0);
                    TiledMapTileLayer staticLayer = (TiledMapTileLayer) tempRoom.getLayers().get(2);
                    
                    RectangleMapObject bottom = (RectangleMapObject) collisionLayer.getObjects().get(0);
                    RectangleMapObject left = (RectangleMapObject) collisionLayer.getObjects().get(1);
                    RectangleMapObject top = (RectangleMapObject) collisionLayer.getObjects().get(2);
                    RectangleMapObject right = (RectangleMapObject) collisionLayer.getObjects().get(3);
                    
                    // Ausgang oben
                    if(y < l.getRooms()[0].length - 1 && l.getRooms()[x][y + 1] != null){
                        
                        if(x == l.getExit()[0] && y+1 == l.getExit()[1]){
                            staticLayer.getCell((tempX / 2) + 1, tempY + 1).setTile(new StaticTiledMapTile(splitTiles[1][5])); //oben1
                            staticLayer.getCell((tempX / 2) + 1, tempY + 2).setTile(new StaticTiledMapTile(splitTiles[0][5])); //oben2
                            staticLayer.getCell((tempX / 2) + 1, tempY + 3).setTile(new StaticTiledMapTile(splitTiles[4][3])); //oben3
                            staticLayer.getCell((tempX / 2) + 0, tempY + 3).setTile(new StaticTiledMapTile(splitTiles[3][5])); //oben3-1
                            staticLayer.getCell((tempX / 2) + 2, tempY + 3).setTile(new StaticTiledMapTile(splitTiles[2][5])); //oben3+1
                        }
                        else{
                            // X: Exakte Mitte der Gesamtlänge, Y: Gesamtlänge
                            staticLayer.getCell((tempX / 2) + 1, tempY + 1).setTile(new StaticTiledMapTile(splitTiles[0][0])); //oben1
                            staticLayer.getCell((tempX / 2) + 1, tempY + 2).setTile(new StaticTiledMapTile(splitTiles[0][0])); //oben2
                            staticLayer.getCell((tempX / 2) + 1, tempY + 3).setTile(new StaticTiledMapTile(splitTiles[0][0])); //oben3
                            staticLayer.getCell((tempX / 2) + 0, tempY + 3).setTile(new StaticTiledMapTile(splitTiles[3][5])); //oben3-1
                            staticLayer.getCell((tempX / 2) + 2, tempY + 3).setTile(new StaticTiledMapTile(splitTiles[2][5])); //oben3+1
                        }
                        
                        collisionLayer.getObjects().remove(top);
             
                        RectangleMapObject tempLeft = new RectangleMapObject(0, mapDimensionY*48 - 48, (mapDimensionX / 2) * 48, 48);
                        RectangleMapObject tempRight = new RectangleMapObject(((mapDimensionX / 2) + 1) * 48, mapDimensionY*48 - 48, (mapDimensionX / 2) * 48, 48);
                        
                        collisionLayer.getObjects().add(tempLeft);
                        collisionLayer.getObjects().add(tempRight);
                    }
                    
                    // Ausgang rechts    
                    if(x < l.getRooms().length - 1 && l.getRooms()[x + 1][y] != null){
                        
                        if(x+1 == l.getExit()[0] && y == l.getExit()[1]){
                            staticLayer.getCell(tempX + 1, (tempY / 2) + 1).setTile(new StaticTiledMapTile(splitTiles[0][6])); //rechts
                        }
                        else{
                            // X: Gesamtlänge, Y: Exakte Mitte der Gesamtlänge
                            staticLayer.getCell(tempX + 1, (tempY / 2) + 0).setTile(new StaticTiledMapTile(splitTiles[4][4])); //rechts-1
                            staticLayer.getCell(tempX + 1, (tempY / 2) + 1).setTile(new StaticTiledMapTile(splitTiles[0][0])); //rechts1
                            staticLayer.getCell(tempX + 1, (tempY / 2) + 2).setTile(new StaticTiledMapTile(splitTiles[1][1])); //rechts2
                            staticLayer.getCell(tempX + 1, (tempY / 2) + 3).setTile(new StaticTiledMapTile(splitTiles[0][1])); //rechts3
                            staticLayer.getCell(tempX + 1, (tempY / 2) + 4).setTile(new StaticTiledMapTile(splitTiles[2][5])); //rechts4
                            
                            AnimatedObject tempObject = new AnimatedObject(torchT, 48, 96);
                            tempObject.getSprite().setPosition((tempX + 1) * 48, ((tempY / 2) + 2) * 48);
                            temp.getObjects().add(tempObject);
                        }
                        
                        collisionLayer.getObjects().remove(right);
                        
                        RectangleMapObject tempTop = new RectangleMapObject(mapDimensionX*48 - 48, ((mapDimensionY / 2) + 1) * 48, 48, (mapDimensionY / 2) * 48);
                        RectangleMapObject tempBottom = new RectangleMapObject(mapDimensionX*48 - 48, 0, 48, (mapDimensionY / 2) * 48);
                        
                        collisionLayer.getObjects().add(tempTop);
                        collisionLayer.getObjects().add(tempBottom);
                    }
                    
                    // Ausgang unten
                    if(y > 0 && l.getRooms()[x][y - 1] != null){
                        
                        if(x == l.getExit()[0] && y-1 == l.getExit()[1]){
                            staticLayer.getCell((tempX / 2) + 1, 0).setTile(new StaticTiledMapTile(splitTiles[1][6])); //unten
                        }
                        else{
                            // X: Exakte Mitte der Gesamtlänge, Y: 0
                            staticLayer.getCell((tempX / 2) + 0, 0).setTile(new StaticTiledMapTile(splitTiles[4][5])); //unten-1
                            staticLayer.getCell((tempX / 2) + 1, 0).setTile(new StaticTiledMapTile(splitTiles[0][0])); //unten1
                            staticLayer.getCell((tempX / 2) + 2, 0).setTile(new StaticTiledMapTile(splitTiles[4][4])); //unten2
                        }
                        
                        collisionLayer.getObjects().remove(bottom);
                        
                        RectangleMapObject tempLeft = new RectangleMapObject(0, 0, (mapDimensionX / 2) * 48, 48);
                        RectangleMapObject tempRight = new RectangleMapObject(((mapDimensionX / 2) + 1) * 48, 0, (mapDimensionX / 2) * 48, 48);
                        
                        collisionLayer.getObjects().add(tempLeft);
                        collisionLayer.getObjects().add(tempRight);
                    }
                    
                    // Ausgang links
                    if(x > 0 && l.getRooms()[x - 1][y] != null){
                        
                        if(x-1 == l.getExit()[0] && y == l.getExit()[1]){
                            staticLayer.getCell(0, (tempY / 2) + 1).setTile(new StaticTiledMapTile(splitTiles[2][6])); //links
                        }
                        else{
                            // X: 0, Y: Exakte Mitte der Gesamtlänge
                            staticLayer.getCell(0, (tempY / 2) + 0).setTile(new StaticTiledMapTile(splitTiles[4][5])); //links-1
                            staticLayer.getCell(0, (tempY / 2) + 1).setTile(new StaticTiledMapTile(splitTiles[0][0])); //links1
                            staticLayer.getCell(0, (tempY / 2) + 2).setTile(new StaticTiledMapTile(splitTiles[1][1])); //links2
                            staticLayer.getCell(0, (tempY / 2) + 3).setTile(new StaticTiledMapTile(splitTiles[0][1])); //links3
                            staticLayer.getCell(0, (tempY / 2) + 4).setTile(new StaticTiledMapTile(splitTiles[3][5])); //links4
                            
                            AnimatedObject tempObject = new AnimatedObject(torchT, 48, 96);
                            tempObject.getSprite().setPosition(0, ((tempY / 2) + 2) * 48);
                            temp.getObjects().add(tempObject);
                        }
                        
                        collisionLayer.getObjects().remove(left);
                        
                        RectangleMapObject tempTop = new RectangleMapObject(0, ((mapDimensionY / 2) + 1) * 48, 48, (mapDimensionY / 2) * 48);
                        RectangleMapObject tempBottom = new RectangleMapObject(0, 0, 48, (mapDimensionY / 2) * 48);
                        
                        collisionLayer.getObjects().add(tempTop);
                        collisionLayer.getObjects().add(tempBottom);
                    }
                    
                    temp.setMap(tempRoom);
                    tempLevel[x][y] = temp;
                }
                
            }
        }
        
        return tempLevel;
    }
    
    private MapContainer generateRoom(Room r, int roomDimensionX, int roomDimensionY, int lvl){
        TiledMap tempRoom = new TiledMap();
        MapContainer temp = new MapContainer(tempRoom);
        
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
        TiledMapTileLayer dynamicLayer = new TiledMapTileLayer(mapDimensionX, mapDimensionY + 2, 48, 48);
        TiledMapTileLayer staticLayer = new TiledMapTileLayer(mapDimensionX, mapDimensionY + 2, 48, 48);
        
        RectangleMapObject bottom = new RectangleMapObject(0, 0, mapDimensionX*48, 48);
        collisionLayer.getObjects().add(bottom);
        
        RectangleMapObject left = new RectangleMapObject(0, 0, 48, mapDimensionY*48);
        collisionLayer.getObjects().add(left);
        
        RectangleMapObject top = new RectangleMapObject(0, mapDimensionY*48 - 48, mapDimensionX*48, 48);
        collisionLayer.getObjects().add(top);
        
        RectangleMapObject right = new RectangleMapObject(mapDimensionX*48 - 48, 0, 48, mapDimensionY*48);
        collisionLayer.getObjects().add(right);
        
        collisionLayer.setVisible(false);
        
        // Schleife läuft über jedes Teil des Raumes und generiert ein Tile aus dem tileset
        for(int x = 0; x < mapDimensionX + 2; x++){
            for(int y = 0; y < mapDimensionY + 2; y++){
                
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                
                // links
                if(x == 0){
                    cell.setTile(new StaticTiledMapTile(splitTiles[4][2]));
                }
                // unten
                else if(y == 0){
                    cell.setTile(new StaticTiledMapTile(splitTiles[4][1]));
                }
                // rechts
                else if(x == mapDimensionX - 1){
                    cell.setTile(new StaticTiledMapTile(splitTiles[4][0]));
                }
                // oben1
                else if(y == mapDimensionY - 1){
                    if(x == roomDimensionX / 2 - 1 || x == roomDimensionX / 2 + 3){
                        
                        AnimatedObject tempObject = new AnimatedObject(torchT, 48, 96);
                        tempObject.getSprite().setPosition(x * 48, y * 48);
                        temp.getObjects().add(tempObject);
                    }
                    
                    cell.setTile(new StaticTiledMapTile(splitTiles[1][1]));
                }
                // oben2
                else if(y == mapDimensionY){
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][1]));
                }
                // oben3
                else if(y == mapDimensionY + 1){
                    cell.setTile(new StaticTiledMapTile(splitTiles[4][3]));
                }
                else{
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][0]));
                }
                
                // Ecken
                
                // unten links
                if(x == 0 && y == 0){
                    cell.setTile(new StaticTiledMapTile(splitTiles[2][2]));
                }
                // oben links
                else if(x == 0 && y == mapDimensionY + 1){
                    cell.setTile(new StaticTiledMapTile(splitTiles[3][2]));
                }
                // oben rechts
                else if(x == mapDimensionX - 1 && y == mapDimensionY + 1){
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][2]));
                }
                // unten rechts
                else if(x == mapDimensionX -1 && y == 0){
                    cell.setTile(new StaticTiledMapTile(splitTiles[1][2]));
                }
                
                staticLayer.setCell(x, y, cell);
            }
        }
        
        layers.add(collisionLayer);
        layers.add(dynamicLayer);
        layers.add(staticLayer);
        
        for(int i = 0; i < r.getItems().size(); i++){
            if(r.getItems().get(i) != null){
                ItemContainer container = r.getItems().get(i);
                Item item = container.getItem();
                
                if(item.getId() == 2){
                    AnimatedObject swordSprite = new AnimatedObject(sword, 48, 48);
                    swordSprite.getSprite().setPosition(container.getxPos(), container.getyPos());
                    temp.getMapItems().add(swordSprite);
                }
            }
        }
        
        return temp;
    }
    
    public void ichWillSpielen(MapContainer[][][] map){

        for(int i=0;i<map.length;i++){
            MapContainer[][] temp = map[i];

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
