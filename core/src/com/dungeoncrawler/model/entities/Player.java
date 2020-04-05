/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model.entities;


import com.dungeoncrawler.model.Entity;
/**
 *
 * @author Jan
 */
public class Player extends Entity {
    
    public Player() {
        super(0, 0, 1);
        
        this.maxhp = 5*lvl;
        this.hp = this.maxhp;
        
        this.dmg = 3*lvl;
        
        // TODO: Sinnvolle Werte finden
    }
    
    
    
}
