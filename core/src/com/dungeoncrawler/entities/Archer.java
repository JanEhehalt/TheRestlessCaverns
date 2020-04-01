package com.dungeoncrawler.entities;
import com.dungeoncrawler.Entity;

public class Archer extends Entity{
    
    public Archer(int xPos, int yPos, int lvl) {
        super(xPos, yPos, lvl);
        
        this.maxhp = 5*lvl;
        this.hp = this.maxhp;
        
        this.dmg = 3*lvl;
        
        // TODO: Sinnvolle Werte finden
    }
    
}
