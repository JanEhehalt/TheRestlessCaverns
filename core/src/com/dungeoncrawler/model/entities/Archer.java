package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.model.Entity;
import com.badlogic.gdx.utils.Timer;

public class Archer extends Entity{
    
    Timer tup;
    Timer tright;
    Timer tdown;
    Timer tleft;
    int timerRuns;
    boolean isRunning;
    
    public Archer(float xPos, float yPos, int lvl) {
        super(xPos, yPos, lvl);
        
        this.maxhp = 5*lvl;
        this.hp = this.maxhp;
        this.facing = 2;
        this.dmg = 3*lvl;
        this.id = 0;
        // TODO: Sinnvolle Werte finden
        
        tup = new Timer();
        tright = new Timer();
        tdown = new Timer();
        tleft = new Timer();
        isRunning = false;
        timerRuns = 0;
        facing = 2;
        
        tup.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setFacing(0);
                        setyPos(getyPos() + 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tup.stop();
                        }
                    }
        },0,0.015f);
        tup.stop();
        tright.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setFacing(1);
                        setxPos(getxPos() + 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tright.stop();
                        }
                    }
        },0,0.015f);
        tright.stop();
        tdown.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setFacing(2);
                        setyPos(getyPos() - 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tdown.stop();
                        }
                    }
        },0,0.015f);
        tdown.stop();
        tleft.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setFacing(3);
                        setxPos(getxPos() - 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tleft.stop();
                        }
                    }
        },0,0.015f);
        tleft.stop();
        
    }
    
    @Override
    public void randomMove(int x, int y) {
            double i = Math.random();
            if(i <= 0.2){
                if(isRunning == false){
                    if(getyPos() >= (x-1) * 48f){
                    }
                    else{
                        setIsRunning(true);
                        tup.start();
                    }
                }
            }
            else if(i > 0.2 && i <= 0.4){
                if(isRunning == false){
                    if(getxPos() >= (y-1) * 48f){
                    }
                    else{
                        setIsRunning(true);
                        tright.start();
                    }
                }
            }
            else if(i > 0.4 && i <= 0.6){
                if(isRunning == false){
                    if(getyPos() <= 48f){
                    }
                    else{
                        setIsRunning(true);
                        tdown.start();
                    }
                }
            }
            else if(i > 0.6 && i <= 0.8){
                if(isRunning == false){
                    if(getxPos() <= 48f){
                    }
                    else{
                        setIsRunning(true);
                        tleft.start();
                    }
                }
            }
            else{
            }
    }
        
    
    private void setIsRunning(boolean n){
        isRunning = n;
    }
    private boolean getIsRunning(){
        return isRunning;
    }
    
    public int getTimerRuns(){
        return timerRuns;
    }
    
    public void setTimerRuns(int n){
        timerRuns = n;
    }
    
    
    
}
