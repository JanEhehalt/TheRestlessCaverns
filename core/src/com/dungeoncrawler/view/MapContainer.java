/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 *
 * @author jonathan
 */
public class MapContainer {
    private TiledMap map;
    private Sprite[] animatedObjects;
    private TextureRegion[][][] textures;
    
    public MapContainer(TiledMap map, int objectAmount){
        this.map = map;
        this.animatedObjects = new Sprite[objectAmount];
        this.textures = new TextureRegion[objectAmount][][];
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
    
    public void setAnimatedObjects(Sprite animatedObject, int i) {
        this.animatedObjects[i] = animatedObject;
    }

    /**
     * @return the textures
     */
    public TextureRegion[][][] getTextures() {
        return textures;
    }

    /**
     * @param textures the textures to set
     */
    public void setTextures(TextureRegion[][][] textures) {
        this.textures = textures;
    }
    
    public void setTextures(TextureRegion[][] textures, int i) {
        this.textures[i] = textures;
    }
    
    
}
