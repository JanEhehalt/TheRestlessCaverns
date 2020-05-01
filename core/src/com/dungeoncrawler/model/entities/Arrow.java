/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.model.Entity;
import com.badlogic.gdx.utils.Timer;
    

public class Arrow extends Entity{
    Timer toben;
    Timer tunten;
    Timer trechts;
    Timer tlinks;
    float xStart;
    float yStart;
    int direction;
    
    public Arrow(float xPos, float yPos, int lvl, int direction){
        super(xPos, yPos, lvl);
        xStart = xPos;
        yStart = yPos;
        Timer toben = new Timer();
        Timer tunten = new Timer();
        Timer trechts = new Timer();
        Timer tlinks = new Timer();
        this.direction = direction;
        this.dmg = 3*lvl;
        this.id = 2;
        
        toben.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setyPos(getyPos() + 3f);
                    }
        },0,0.1f);
        toben.stop();
        tunten.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setyPos(getyPos() - 3f);
                    }
        },0,0.1f);
        tunten.stop();
        trechts.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setxPos(getxPos() + 3f);
                    }
        },0,0.1f);
        trechts.stop();
        tlinks.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setyPos(getxPos() - 3f);
                    }
        },0,0.1f);
        tlinks.stop();
        switch(direction){
            case 0:
                toben.start();
                break;
            case 1:
                trechts.start();
                break;
            case 2:
                tunten.start();
                break;
            case 3:
                tlinks.start();
                break;
        
        }
        

   }
    
    
    public float getxStart(){
        return xStart;
    }
    public float getyStart(){
        return yStart;
    }

    @Override
    public void move(int xPosPlayer, int yPosPlayer) {
        // Nothing
    }
}
