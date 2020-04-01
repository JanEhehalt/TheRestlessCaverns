/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler;

import com.dungeoncrawler.entities.*;

/**
 *
 * @author jonathan
 */
public class Room {
    private ItemContainer item;
    private Entity[] enemies;
    
    public Room(ItemContainer item, Entity[] enemies){
        this.item = item;
        this.enemies = enemies;
    }
    
    public void spawnEnemies(int xPos, int yPos){
        // TODO: Zu Implementieren
    }
    
    public void spawnItem(int xPos, int yPos){
        // TODO: Zu Implementieren
    }

    /**
     * @return the item
     */
    public ItemContainer getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(ItemContainer item) {
        this.item = item;
    }

    /**
     * @return the enemies
     */
    public Entity[] getEnemies() {
        return enemies;
    }

    /**
     * @param enemies the enemies to set
     */
    public void setEnemies(Entity[] enemies) {
        this.enemies = enemies;
    }
    
    
}
