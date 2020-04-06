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
    
    public Player(float xPos, float yPos) {
        super(xPos, yPos, 0);
        this.xPos = xPos;
        this.yPos = yPos;
        this.maxhp = 5*lvl;
        this.hp = this.maxhp;
        
        this.dmg = 3*lvl;
        
        // TODO: Sinnvolle Werte finden
    }
    
    
    
}
