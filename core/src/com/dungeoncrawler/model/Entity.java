package com.dungeoncrawler.model;


public abstract class Entity {
    
    protected int xPos;
    protected int yPos;
    protected int hp;
    protected int maxhp;
    protected int dmg;
    protected int lvl;
    protected int movementX;
    protected int movementY;

    public Entity(int xPos, int yPos, int lvl){
        this.xPos = xPos;
        this.yPos = yPos;
        this.lvl = lvl;
        this.movementX = 0;
        this.movementY = 0;
    }
    
    

    public void attack(){
        
    }
    public void update(){
        
    }
    public void die(){
    
    }
    public void move(int movementX, int movementY){
            xPos = xPos + movementX;
            yPos = yPos + movementY;
    }
    
    public void rdmMove(){
            
                 switch((int) (Math.random() * 5)){
                    case 0: //left
                        move(-3 , 0);
                        break;
                    case 1: //right
                        move(3 , 0);
                        break;
                    case 2: //up
                        move(0 , 3);
                        break;
                    case 3: //down
                        move(0 , -3);
                        break;
                 }
        }
    
            
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
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


}