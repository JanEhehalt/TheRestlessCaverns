package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.StaticMath;
import com.dungeoncrawler.model.Entity;

public class Swordsman extends Entity {
    
    public Swordsman(float xPos, float yPos, int lvl) {
        super(xPos, yPos, lvl);
        
        this.maxhp = 100*lvl;
        this.hp = this.maxhp;
        this.direction = 1;
        this.dmg = 35*lvl;
        this.id = 1;
        this.type = 0;
        
        // TODO: Sinnvolle Werte finden
        direction = 2;
    }
    
    @Override
    public boolean move(int xPosPlayer, int yPosPlayer){
        if(!isToDelete()){
            double alpha = StaticMath.calculateAngle((int) this.xPos, (int) this.yPos, xPosPlayer, yPosPlayer);

            movementX = (int) (3 * Math.cos(alpha));
            movementY = (int) (3 * Math.sin(alpha));

            xPos += movementX;
            yPos += movementY;

            updateDirection();
        }
        return false;
    }
    
    
}
