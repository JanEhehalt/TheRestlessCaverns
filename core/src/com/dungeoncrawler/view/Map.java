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
        TextureRegion[][] splitTiles = TextureRegion.split(tiles, 48, 48);
        MapLayers layers = map.getLayers();
    }
    
}
