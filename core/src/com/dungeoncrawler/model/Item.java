/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model;

/**
 *
 * @author jonathan
 */
public abstract class Item {
    protected int dmg;
    protected int heal;
    protected int lvl;
    protected int id;
    
    public Item(int lvl){
        this.lvl = lvl;
    }

    
    public int getId(){
        return this.id;
    }
    public int getDmg(){
        return dmg;
    }
    public int getHeal(){
        return heal;
    }
    public int getLvl(){
        return lvl;
    }
    /**
     * @return the amount
     */
    
    
    
}

