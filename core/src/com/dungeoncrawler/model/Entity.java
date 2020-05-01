package com.dungeoncrawler.model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Rectangle;

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
    protected int direction;
    protected Inventory inv;
    
    

    public Entity(float xPos, float yPos, int lvl){
        this.xPos = xPos;
        this.yPos = yPos;
        this.lvl = lvl;
        this.movementX = 0;
        this.movementY = 0;
        this.direction = 2;
    }
    
    public void attack(){
        
    }
    public void update(){
        xPos += movementX;
        yPos += movementY;
    }
    public void updateX(){
        xPos += movementX;
    }
    public void updateY(){
        yPos += movementY;
    }
    public void downgrade(){
        xPos -= movementX;
        yPos -= movementY;
    }
    public void die(){
    
    }
    public void move(){
            xPos = xPos + movementX;
            yPos = yPos + movementY;
            movementX = 0;
            movementY = 0;
    }
    
    public void updateDirection(){
        if(movementX > 1){
            direction = 1;
        }
        else if(movementX < -1){
            direction = 0;
        }
    }
    
    abstract public Entity move(int xPosPlayer, int yPosPlayer);
    
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
    
    public int getDirection(){
        return direction;
    }
    
    public void setDirection(int direction){
        this.direction = direction;
    }
    
}