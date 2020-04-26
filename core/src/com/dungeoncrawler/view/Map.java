/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dungeoncrawler.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 *
 * @author jonathan
 */
public class Map {
    private TiledMap[][][] maps;
    private Sprite[] animatedObjects;
    
    public Map(){
        
    }

    /**
     * @return the maps
     */
    public TiledMap[][][] getMaps() {
        return maps;
    }

    /**
     * @param maps the maps to set
     */
    public void setMaps(TiledMap[][][] maps) {
        this.maps = maps;
    }

    /**
     * @return the animatedObjects
     */
    public Sprite[] getAnimatedObjects() {
        return animatedObjects;
    }

    /**
     * @param animatedObjects the animatedObjects to set
     */
    public void setAnimatedObjects(Sprite[] animatedObjects) {
        this.animatedObjects = animatedObjects;
    }
    
    public void setAnimatedObjects(Sprite animatedObjects, int i) {
        this.animatedObjects[i] = animatedObjects;
    }
    
}
