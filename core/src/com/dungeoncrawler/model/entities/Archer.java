package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.model.Entity;
import com.badlogic.gdx.utils.Timer;

public class Archer extends Entity{
    
    Timer t;
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
        
        t = new Timer();
        t.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        double i = Math.random();
        
                        if(i <= 0.2){
                            if(isRunning == false){
                                if(getyPos() == 240){
                                }
                                else{
                                    tup.start();
                                }
                            }
                        }
                        else if(i > 0.2 && i <= 0.4){
                            if(isRunning == false){
                                if(getxPos() == 336){
                                }
                                else{
                                    tright.start();
                                }
                            }
                        }
                        else if(i > 0.4 && i <= 0.6){
                            if(isRunning == false){
                                if(getyPos() == 48){
                                }
                                else{
                                    tdown.start();
                                }
                            }
                        }
                        else if(i > 0.6 && i <= 0.8){
                            if(isRunning == false){
                                if(getxPos() == 48){
                                }
                                else{
                                    tleft.start();
                                }
                            }
                        }
                        else{
                            facing = 2;
                        }
                    }
        },0,1f);
        t.stop();
        tup.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setFacing(0);
                        setIsRunning(true);
                        setyPos(getyPos() + 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tup.stop();
                        }
                    }
        },0,0.02f);
        tup.stop();
        tright.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setFacing(1);
                        setIsRunning(true);
                        setxPos(getxPos() + 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tright.stop();
                        }
                    }
        },0,0.02f);
        tright.stop();
        tdown.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setFacing(2);
                        setIsRunning(true);
                        setyPos(getyPos() - 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tdown.stop();
                        }
                    }
        },0,0.02f);
        tdown.stop();
        tleft.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setFacing(3);
                        setIsRunning(true);
                        setxPos(getxPos() - 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tleft.stop();
                        }
                    }
        },0,0.02f);
        tleft.stop();
        t.start();
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
