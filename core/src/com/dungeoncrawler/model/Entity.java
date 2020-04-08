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
    protected int id;

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
        xPos += movementX;
        yPos += movementY;
    }
    public void die(){
    
    }
    public void move(){
            xPos = xPos + movementX;
            yPos = yPos + movementY;
    }
    
    public void rdmMove(){
            
                 switch((int) (Math.random() * 5)){
                    case 0: //left
                        setMovementX(-3);
                        move();
                        break;
                    case 1: //right
                        setMovementX(3);
                        move();
                        break;
                    case 2: //up
                        setMovementY(3);
                        move();
                        break;
                    case 3: //down
                        setMovementY(-3);
                        move();
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
    
    public int getMovementX(){
        return movementX;
    }
    
    public void setMovementX(int movementX){
        this.movementX = movementX;
    }

    public int getMovementY(){
        return movementY;
    }
    
    public void setMovementY(int movementY){
        this.movementY = movementY;
    }
    
    public int getId(){
        return this.id;
    }
}