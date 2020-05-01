/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model;

import java.util.ArrayList;

/**
 *
 * @author jonathan
 */
public class Room {
    private ArrayList<ItemContainer> items;
    private ArrayList<Entity> enemies;
    int lvl;
    
    public Room(ArrayList<ItemContainer> items, ArrayList<Entity> enemies){
        this.items = items;
        this.enemies = enemies;
        
    }
    
    
    public void spawnEnemies(int xPos, int yPos, Entity enemy){
        enemy.setxPos(xPos);
        enemy.setyPos(yPos);
    }
    
    public void spawnItem(int xPos, int yPos){
        // TODO: Zu Implementieren
    }

    /**
     * @return the enemies
     */
    public ArrayList<Entity> getEnemies() {
        return enemies;
    }

    /**
     * @param enemies the enemies to set
     */
    public void setEnemies(ArrayList<Entity> enemies) {
        this.enemies = enemies;
    }

    /**
     * @return the items
     */
    public ArrayList<ItemContainer> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<ItemContainer> items) {
        this.items = items;
    }
}
