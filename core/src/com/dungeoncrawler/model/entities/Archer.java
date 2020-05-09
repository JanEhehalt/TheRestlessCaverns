package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.model.Entity;

public class Archer extends Entity{
    
    int counter;
    
    public Archer(float xPos, float yPos, int lvl) {
        super(xPos, yPos, lvl);
        
        this.maxhp = 5*lvl;
        this.hp = this.maxhp;
        this.direction = 1;
        this.dmg = 3*lvl;
        this.id = 0;
        this.type = 1;
        counter = 0;
        // TODO: Sinnvolle Werte finden
        
        direction = 2;
        
    }

    @Override
    public boolean move(int xPosPlayer, int yPosPlayer) {
        if(!isToDelete()){
            float deltaX = xPosPlayer - (int) xPos;
            float deltaY = yPosPlayer - (int) yPos;

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

            int distance = (int) Math.abs((deltaY / Math.sin(alpha)));

            if(distance >= 104 && distance <= 184 && counter % 40 == 0){
                return true;
            }
            else{
                movementX = (int) (3 * Math.cos(alpha));
                movementY = (int) (3 * Math.sin(alpha));

                System.out.println(distance);
                if(distance < 124){
                    movementX *= -1;
                    movementY *= -1;
                }
                else if(distance >= 124 && distance <= 164){
                    movementX = 0;
                    movementY = 0;
                }


                xPos += movementX;
                yPos += movementY;
            }

            if(alpha >= Math.PI / -2 && alpha <= Math.PI / 2){
                setDirection(1);
            }
            else{
                setDirection(0);
            }

            counter++;
        }
        
        return false;
    }
    
    @Override
    public Entity shoot(int xPosPlayer, int yPosPlayer){
        Projectile a = null;
        
        if(!isToDelete()){
            float deltaX = xPosPlayer - (int) xPos;
            float deltaY = yPosPlayer - (int) yPos;

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
            a = new Projectile(this.xPos + 32, this.yPos + 32, this.lvl, 2, true);
            movementX = (int) (6 * Math.cos(alpha));
            movementY = (int) (6 * Math.sin(alpha));

            a.setMovementX(movementX);
            a.setMovementY(movementY);
            a.setAngle(alpha);

            if((alpha >= 0 && alpha <= Math.PI / 2) || (alpha <= 2 * Math.PI && alpha >= 2 * Math.PI - Math.PI / 2)){
                setDirection(1);
            }
            else{
                setDirection(0);
            }
        }
        
        return a;
    }
    
}
