package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.model.Entity;

public class Swordsman extends Entity {
    
    public Swordsman(float xPos, float yPos, int lvl) {
        super(xPos, yPos, lvl);
        
        this.maxhp = 5*lvl;
        this.hp = this.maxhp;
        this.direction = 2;
        this.dmg = 3*lvl;
        this.id = 1;
        
        // TODO: Sinnvolle Werte finden
        direction = 2;
    }
    
    @Override
    public boolean move(int xPosPlayer, int yPosPlayer){
        float deltaX = xPosPlayer - (int) xPos;
        float deltaY = yPosPlayer - (int) yPos;
        
        double alpha;
        if(deltaX == 0 && deltaY >= 0){
            alpha = Math.PI / 2;
        }
        else if(deltaX == 0 && deltaY < 0){
            alpha = Math.PI / -2;
        }
        else{
            alpha = Math.abs(Math.atan(deltaY / deltaX));
            
            if(deltaX < 0 && deltaY < 0){
                alpha = Math.PI + alpha;
            }
            else if(deltaX < 0 && deltaY > 0){
                alpha = Math.PI - alpha;
            }
            else if(deltaX > 0 && deltaY < 0){
                alpha = 2*Math.PI - alpha;
            }
        }
        
        movementX = (int) (3 * Math.cos(alpha));
        movementY = (int) (3 * Math.sin(alpha));
        
        xPos += movementX;
        yPos += movementY;
        
        return false;
    }
    
    
}
