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
public class Amulet extends Item {
    
    public Amulet(int lvl) {
        super(lvl);
        this.dmg = 25 * lvl;
        this.heal = 0 * lvl;
        this.id = 2;
    }
    
}
