/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

import com.badlogic.gdx.maps.tiled.TiledMap;
import java.util.ArrayList;

/**
 *
 * @author jonathan
 */
public class MapContainer {
    private TiledMap map;
    private ArrayList<AnimatedObject> objects;
    private ArrayList<AnimatedObject> mapItems;
    
    public MapContainer(TiledMap map){
        this.map = map;
        this.objects = new ArrayList<>();
        this.mapItems = new ArrayList<>();
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
     * @return the objects
     */
    public ArrayList<AnimatedObject> getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(ArrayList<AnimatedObject> objects) {
        this.objects = objects;
    }

    /**
     * @return the mapItems
     */
    public ArrayList<AnimatedObject> getMapItems() {
        return mapItems;
    }

    /**
     * @param mapItems the mapItems to set
     */
    public void setMapItems(ArrayList<AnimatedObject> mapItems) {
        this.mapItems = mapItems;
    }
    
}
