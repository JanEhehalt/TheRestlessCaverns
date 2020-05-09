/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.model.Entity;
import com.dungeoncrawler.model.Inventory;
import com.dungeoncrawler.model.Item;
/**
 *
 * @author Jan
 */
public class Player extends Entity {
    
    float standartDmg;
    float standartMaxHp;
    
    public Player() {
        super(200, 200, 1);
        
        this.maxhp = 20 * (lvl + 1);
        this.hp = this.maxhp;
        this.standartMaxHp = 5 * (lvl + 1);
        
        this.dmg = 3*lvl;
        this.standartDmg = dmg;
        id = -1;
        type = -1;
        inv = new Inventory(3,2);
        // TODO: Sinnvolle Werte finden
        this.targetsPlayer = false;
        
    }
    
    public void pickUp(Item item){
        inv.addItem(item);
    }
    public Inventory getInv(){
        return inv;
    }

    public void updateItems(){
        if(inv.getItem(1) != null){
            this.dmg = this.standartDmg + inv.getItem(1).getDmg();
            //this.maxhp = this.standartMaxHp + inv.getItem(1).getHeal();
        }
        else{
            this.dmg = this.standartDmg;
            //this.maxhp = this.standartMaxHp;
        }
    }

    public boolean move(int x, int y){
        return false;
    }
    
    @Override
    public Entity shoot(int xPosPlayer, int yPosPlayer){
        Projectile a = null;
        
        if(!isToDelete()){
            float deltaX = xPosPlayer - (int) xPos;
            float deltaY = yPosPlayer - (int) yPos;

            double alpha;
            if(deltaY == 0){
                if(deltaX < 0){
                    alpha = Math.PI;
                }
                else{
                    alpha = 0;
                }
            }
            else if(deltaX == 0 && deltaY >= 0){
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
            a = new Projectile(this.xPos + 32, this.yPos + 32, this.lvl, 2, false);
            int tempMovementX = (int) (6 * Math.cos(alpha));
            int tempMovementY = (int) (6 * Math.sin(alpha));

            a.setMovementX(tempMovementX);
            a.setMovementY(tempMovementY);
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
