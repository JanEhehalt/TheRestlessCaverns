package com.dungeoncrawler.model.entities;
import com.badlogic.gdx.utils.Timer;
import com.dungeoncrawler.model.Entity;

public class Swordsman extends Entity {
    
    Timer tup;
    Timer tright;
    Timer tdown;
    Timer tleft;
    int timerRuns;
    boolean isRunning;
    
    public Swordsman(float xPos, float yPos, int lvl) {
        super(xPos, yPos, lvl);
        
        this.maxhp = 5*lvl;
        this.hp = this.maxhp;
        this.direction = 2;
        this.dmg = 3*lvl;
        this.id = 1;
        // TODO: Sinnvolle Werte finden
        tup = new Timer();
        tright = new Timer();
        tdown = new Timer();
        tleft = new Timer();
        isRunning = false;
        timerRuns = 0;
        direction = 2;
        
        
        tup.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setDirection(0);
                        setyPos(getyPos() + 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tup.stop();
                        }
                    }
        },0,0.025f);
        tup.stop();
        tright.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setDirection(1);
                        setxPos(getxPos() + 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tright.stop();
                        }
                    }
        },0,0.025f);
        tright.stop();
        tdown.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setDirection(2);
                        setyPos(getyPos() - 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tdown.stop();
                        }
                    }
        },0,0.025f);
        tdown.stop();
        tleft.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setDirection(3);
                        setxPos(getxPos() - 1f);
                        setTimerRuns(getTimerRuns() + 1);
                        
                        if(getTimerRuns() >= 48){
                            setTimerRuns(0);
                            setIsRunning(false);
                            tleft.stop();
                        }
                    }
        },0,0.025f);
        tleft.stop();
    }
    
    @Override
    public void randomMove(int x, int y) {
            double i = Math.random();
            if(i <= 0.25){
                if(isRunning == false){
                    if(yPos >= (x-1) * 48f){
                    }
                    else{
                        setIsRunning(true);
                        tup.start();
                    }
                }
            }
            else if(i > 0.25 && i <= 0.5){
                if(isRunning == false){
                    if(xPos >= (y-1) * 48f){
                    }
                    else{
                        setIsRunning(true);
                        tright.start();
                    }
                }
            }
            else if(i > 0.5 && i <= 0.75){
                if(isRunning == false){
                    if(yPos <= 48f){
                    }
                    else{
                        setIsRunning(true);
                        tdown.start();
                    }
                }
            }
            else if(i > 0.75 && i <= 1){
                if(isRunning == false){
                    if(xPos <= 48f){
                    }
                    else{
                        setIsRunning(true);
                        tleft.start();
                    }
                }
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
    
    @Override
    public void move(int xPosPlayer, int yPosPlayer){
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
        
        movementX = (int) (3 * Math.cos(alpha));
        movementY = (int) (3 * Math.sin(alpha));
        
        /*
        if(deltaX < 0 || deltaY < 0){
            movementX *= -1;
            movementY *= -1;
        }
        else if(deltaY < 0){
            movementX *= -1;
        }
        */
        
        xPos += movementX;
        yPos += movementY;
    }
    
    
}
