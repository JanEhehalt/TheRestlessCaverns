/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.dungeoncrawler.model.Item;
import java.util.ArrayList;

/**
 *
 * @author jonathan
 */
public class MapContainer {
    private TiledMap map;
    private ArrayList<AnimatedObject> objects;
    private ArrayList<AnimatedObject> mapItems;
    private EntitySprite[] enemies;
    
    public MapContainer(TiledMap map){
        this.map = map;
        this.objects = new ArrayList<>();
        this.mapItems = new ArrayList<>();
        this.enemies = new EntitySprite[15];
    }
    
    
    public void addItem(int width, int height, int xPos, int yPos, Item i){
        switch(i.getId()){
            case 0: // key
                AnimatedObject ao1 = new AnimatedObject(new Texture("sprites/key.png"), width, height);
                mapItems.add(ao1);
                ao1.setSpritePosition(xPos, yPos);
                break;
            case 1: // potion
                AnimatedObject ao2 = new AnimatedObject(new Texture("sprites/potion.png"), width, height);
                mapItems.add(ao2);
                ao2.setSpritePosition(xPos, yPos);
                break;
            case 2: // amulet
                AnimatedObject ao3 = new AnimatedObject(new Texture("sprites/amulet.png"), width, height);
                mapItems.add(ao3);
                ao3.setSpritePosition(xPos, yPos);
                break;
                
        }
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

    /**
     * @return the enemies
     */
    public EntitySprite[] getEnemies() {
        return enemies;
    }

    /**
     * @param enemies the enemies to set
     */
    public void setEnemies(EntitySprite[] enemies) {
        this.enemies = enemies;
    }
    
    public void setEnemies(EntitySprite enemy, int i) {
        this.enemies[i] = enemy;
    }
    
}
