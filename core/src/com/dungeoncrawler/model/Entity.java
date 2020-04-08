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
    protected int id;

    public Entity(int xPos, float yPos, int lvl){
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
    public void move(){
            xPos = xPos + movementX;
            yPos = yPos + movementY;
            movementX = 0;
            movementY = 0;
    }
    
    public void rdmMove(float xPlayer, float yPlayer){
            if(xPlayer == xPos){
                if(yPlayer == yPos){}
                else if(yPlayer > yPos){movementY = -1f;}
                else if(yPlayer < yPos){movementY = 1f;}
            }
            else if(yPlayer == yPos){
                if(xPlayer == xPos){}
                else if(xPlayer > xPos){movementX = 1f;}
                else if(xPlayer < xPos){movementX = -1f;}
            }
            else if(xPlayer > xPos){
                if(yPlayer > yPos){     //archer ist im Quadrant III
                    if((yPos - yPlayer) > (xPos - xPlayer)){ 
                        movementY = 1f;
                    }
                    else{
                        movementX = 1f;
                    }
                }
                else if(yPlayer < yPos){ //archer ist im Quadrant IV
                    if((yPos - yPlayer) > (xPlayer - xPos)){
                        movementX = 1f;
                    }
                    else{
                        movementY = -1f;
                    }
                }
            }
            else if(xPlayer < xPos){
                if(yPlayer < yPos){     //archer ist im Quadrant II
                    if((yPlayer - yPos) > (xPlayer - xPos)){
                        movementX = -1f;
                    }
                    else{
                        movementY = 1f;
                    }
                }
                else if(yPlayer > yPos){ //archer ist im Quadrant I
                    if((yPlayer - yPos) > (xPos - xPlayer)){
                        movementY = -1;
                    }
                    else{
                        movementX = -1;
                    }
                }
            }
        
        move();
        
        
        /*
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
        */
        }
    
    public int direction(){
        if(movementX < 0f){
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
}