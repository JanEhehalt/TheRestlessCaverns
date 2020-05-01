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
        this.dmg = 4 * (lvl + 1);
        this.heal = 4 * (lvl + 1);
        this.id = 2;
    }
    
}
