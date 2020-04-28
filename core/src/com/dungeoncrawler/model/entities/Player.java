/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model.entities;


import com.dungeoncrawler.model.Entity;
import com.dungeoncrawler.model.Inventory;
import com.dungeoncrawler.model.Item;
import com.dungeoncrawler.model.ItemContainer;
/**
 *
 * @author Jan
 */
public class Player extends Entity {
    
    public Player() {
        super(200, 200, 1);
        
        this.maxhp = 5*lvl;
        this.hp = this.maxhp;
        
        this.dmg = 3*lvl;
        id = -1;
        inv = new Inventory(3,2);
        // TODO: Sinnvolle Werte finden
    }
    
    public void pickUp(Item item){
        inv.addItem(item);
    }
    
}
