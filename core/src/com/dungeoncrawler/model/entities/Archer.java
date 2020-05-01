package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.model.Entity;

public class Archer extends Entity{
    
    public Archer(float xPos, float yPos, int lvl) {
        super(xPos, yPos, lvl);
        
        this.maxhp = 5*lvl;
        this.hp = this.maxhp;
        this.direction = 2;
        this.dmg = 3*lvl;
        this.id = 0;
        // TODO: Sinnvolle Werte finden
        
        direction = 2;
        
    }

    @Override
    public void move(int xPosPlayer, int yPosPlayer) {
        // Nothing
    }
    
    
    
}
