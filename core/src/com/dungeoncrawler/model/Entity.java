package com.dungeoncrawler.model;


public abstract class Entity {
    
    protected float xPos;
    protected float yPos;
    protected int hp;
    protected int maxhp;
    protected int dmg;
    protected int lvl;
    protected float movementX;
    protected float movementY;

    public Entity(float xPos, float yPos, int lvl){
        this.xPos = xPos;
        this.yPos = yPos;
        this.lvl = lvl;
        this.movementX = 0f;
        this.movementY = 0f;
    }
    
    

    public void attack(int i){
        
    }
    public void update(){
        
    }
    public void die(){
    
    }
    public void move(){
            xPos = xPos + movementX;
            yPos = yPos + movementY;
    }
    
    public void rdmMove(){
                 switch((int) (Math.random() * 5)){
                    case 0: //UP
                            setMovementY(32f);
                            move();
                        break;
                    case 1: //RIGHT
                            setMovementX(32f);
                            move();
                        break;
                    case 2: //DOWN
                            setMovementY(-32f);
                            move();
                        break;
                    case 3: //LEFT
                            setMovementX(-32f);
                            move();
                        break;
                 }
        }
    
            
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
}