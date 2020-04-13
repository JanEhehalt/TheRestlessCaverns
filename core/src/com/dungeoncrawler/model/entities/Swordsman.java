package com.dungeoncrawler.model.entities;
import com.dungeoncrawler.model.Entity;

public class Swordsman extends Entity {
    
    public Swordsman(float xPos, float yPos, int lvl) {
        super(xPos, yPos, lvl);
        
        this.maxhp = 5*lvl;
        this.hp = this.maxhp;
        
        this.dmg = 3*lvl;
        this.id = 1;
        // TODO: Sinnvolle Werte finden
    }
    
}
