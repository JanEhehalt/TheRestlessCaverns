/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.model.Entity;
import com.dungeoncrawler.model.Inventory;
import com.dungeoncrawler.model.Item;
/**
 *
 * @author Jan
 */
public class Player extends Entity {
    
    int standartDmg;
    int standartMaxHp;
    
    public Player() {
        super(200, 200, 1);
        
        this.maxhp = 5 * (lvl + 1);
        this.hp = this.maxhp;
        standartMaxHp = this.maxhp;
        
        this.dmg = 3*lvl;
        standartDmg = this.dmg;
        id = -1;
        inv = new Inventory(3,2);
        // TODO: Sinnvolle Werte finden
        
    }
    
    public void pickUp(Item item){
        inv.addItem(item);
    }
    public Inventory getInv(){
        return inv;
    }

    public void updateItems(){
        if(inv.getItem(1) != null){
            dmg = standartDmg + inv.getItem(1).getDmg();
            maxhp = standartMaxHp + inv.getItem(1).getHeal();
        }
        else{
            dmg = standartDmg;
            maxhp = standartMaxHp;
        }
    }

    public void move(int x, int y){
    
    }
    
    
}
