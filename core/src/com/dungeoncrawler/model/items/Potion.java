/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model.items;

import com.dungeoncrawler.model.Item;

/**
 *
 * @author jonathan
 */
public class Potion extends Item {
    
    public Potion(int lvl) {
        super(lvl);
        this.heal = lvl * 40;
        this.dmg = 0;
        this.id = 1;
    }
    
}
