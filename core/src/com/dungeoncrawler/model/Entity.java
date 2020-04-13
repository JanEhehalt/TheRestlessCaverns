package com.dungeoncrawler.model;

import com.badlogic.gdx.utils.Timer;


public abstract class Entity {
    
    protected float xPos;
    protected float yPos;
    protected int hp;
    protected int maxhp;
    protected int dmg;
    protected int lvl;
    protected float movementX;
    protected float movementY;
    protected int id;
    
    Timer tup;
    Timer tright;
    Timer tdown;
    Timer tleft;
    int timerRuns;
    boolean isRunning;
    int facing;

    public Entity(float xPos, float yPos, int lvl){
        this.xPos = xPos;
        this.yPos = yPos;
        this.lvl = lvl;
        this.movementX = 0;
        this.movementY = 0;
        
        
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
                        setIsRunning(true);
                        setyPos(getyPos() + 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tup.stop();
                        }
                    }
        },0,0.03f);
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
        },0,0.03f);
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
        },0,0.03f);
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
        },0,0.03f);
        tleft.stop();
    }
    
    

    public void attack(){
        
    }
    public void update(){
        xPos += movementX;
        yPos += movementY;
    }
    public void die(){
    
    }
    public void move(){
            xPos = xPos + movementX;
            yPos = yPos + movementY;
            movementX = 0;
            movementY = 0;
    }
    
    public void rdmMove(){
        double i = Math.random();
        
        if(i <= 0.2){
            if(isRunning == false){
                tup.start();
            }
        }
        else if(i > 0.2 && i <= 0.4){
            if(isRunning == false){
                tright.start();
            }
        }
        else if(i > 0.4 && i <= 0.6){
            if(isRunning == false){
                if(yPos == 0){
                }
                else{
                    tdown.start();
                }
            }
        }
        else if(i > 0.6 && i <= 0.8){
            if(isRunning == false){
                if(xPos == 0){
                }
                else{
                    tleft.start();
                }
            }
        }
        else{
            facing = 2;
        }
        
        
            move();
    }
    
    public int direction(){     // returns direction the entity is facing depending on its movement 
        if(movementX < 0f){     // TIS IS SHIT - NEED REWORK
            return 3;
        }
        else if(movementX < 3f){
            return 1;
        }
        else if(movementY > 3f){
            return 0;
        }
        else if(movementY < -3f){
            return 2;
        }
        return -1;
    }
    
    
    // GETTER + SETTER
    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxhp() {
        return maxhp;
    }

    public void setMaxhp(int maxhp) {
        this.maxhp = maxhp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    
    public float getMovementX(){
        return movementX;
    }
    
    public void setMovementX(float movementX){
        this.movementX = movementX;
    }

    public float getMovementY(){
        return movementY;
    }
    
    public void setMovementY(float movementY){
        this.movementY = movementY;
    }
    
    public int getId(){
        return this.id;
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
    
    public void setFacing(int i){
        facing = i;
    }
    
    public int getFacing(){
        return facing;
    }
    
}