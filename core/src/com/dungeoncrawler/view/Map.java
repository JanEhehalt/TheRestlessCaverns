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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Timer;
/**
 *
 * @author jonathan
 */
public class Map {
    private TiledMap map;
    private Texture tiles;
    
    public Map(){
        map = new TiledMap();
        tiles = new Texture(Gdx.files.internal("tiles.gif"));
        
        generateMap();
    }
    
    private void generateMap(){
        TextureRegion[][] splitTiles = TextureRegion.split(getTiles(), 48, 48);
        MapLayers layers = getMap().getLayers();
        
        //for(int i=0;i<6;i++){
        int size = 10;
            TiledMapTileLayer layer = new TiledMapTileLayer(size, size, 48, 48);
            
            for(int x=0;x<size;x++){
                for(int y=0;y<size;y++){
                    
                    if(x == 0 || x == size -1 || y == 0 || y == size -1){
                        
                            Cell cell = new Cell();
                            cell.setTile(new StaticTiledMapTile(splitTiles[0][5]));
                            layer.setCell(x, y, cell);
                            continue;
                            
                    }
                    
                    //int ty = (int)(Math.random() * splitTiles.length);
                    //int tx = (int)(Math.random() * splitTiles[ty].length);
                    
                    Cell cell = new Cell();
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][0]));
                    layer.setCell(x, y, cell);
                    
                }
                layers.add(layer);
            }
        //}
    }

    /**
     * @return the map
     */
    public TiledMap getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(TiledMap map) {
        this.map = map;
    }

    /**
     * @return the tiles
     */
    public Texture getTiles() {
        return tiles;
    }

    /**
     * @param tiles the tiles to set
     */
    public void setTiles(Texture tiles) {
        this.tiles = tiles;
    }
    
    
    
}
