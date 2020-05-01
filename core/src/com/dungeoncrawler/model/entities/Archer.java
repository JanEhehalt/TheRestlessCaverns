package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.model.Entity;

public class Archer extends Entity{
    
    int counter;
    
    public Archer(float xPos, float yPos, int lvl) {
        super(xPos, yPos, lvl);
        
        this.maxhp = 5*lvl;
        this.hp = this.maxhp;
        this.direction = 2;
        this.dmg = 3*lvl;
        this.id = 0;
        
        counter = 0;
        // TODO: Sinnvolle Werte finden
        
        direction = 2;
        
    }

    @Override
    public Entity move(int xPosPlayer, int yPosPlayer) {
        int deltaX = xPosPlayer - (int) xPos;
        int deltaY = yPosPlayer - (int) yPos;
        
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
        
        int distance = (int) Math.abs((deltaY / Math.sin(alpha)));
        
        Arrow a = null;
        if(distance >= 124 && distance <= 164 && counter % 10 == 0){
            a = new Arrow(this.xPos, this.yPos, this.lvl, 0);
            
            movementX = (int) (4 * Math.cos(alpha));
            movementY = (int) (4 * Math.sin(alpha));
            
            a.setMovementX(movementX);
            a.setMovementY(movementY);
        }
        else{
            movementX = (int) (3 * Math.cos(alpha));
            movementY = (int) (3 * Math.sin(alpha));

            System.out.println(distance);
            if(distance < 124){
                movementX *= -1;
                movementY *= -1;
            }
            else if(distance >= 124/* && distance <= 164*/){
                movementX = 0;
                movementY = 0;
            }
            else if(distance > 164){
                
            }
            
            
            xPos += movementX;
            yPos += movementY;
        }
        
        counter++;
        return a;
    }
    
    
    
}
