/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.model.Entity;
    

public class Arrow extends Entity{
    float xStart;
    float yStart;
    int direction;
    int lifetime;
    
    public Arrow(float xPos, float yPos, int lvl, int direction){
        super(xPos, yPos, lvl);
        xStart = xPos;
        yStart = yPos;
        this.direction = direction;
        this.dmg = 3*lvl;
        this.id = 2;
        this.lifetime = 0;
    }
    
    public float getxStart(){
        return xStart;
    }
    public float getyStart(){
        return yStart;
    }

    @Override
    public boolean move(int xPosPlayer, int yPosPlayer) {
        lifetime++;
        
        xPos += movementX;
        yPos += movementY;
        
        if(this.lifetime > 50){
            this.toDelete = true;
        }
        
        return false;
    }
}
