/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model.entities;

import com.dungeoncrawler.StaticMath;
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
        
        this.maxhp = 100 * lvl;
        this.hp = this.maxhp;
        this.standartMaxHp = 100 * lvl;
        
        this.dmg = 60*lvl;
        this.standartDmg = dmg;
        id = -1;
        type = -1;
        inv = new Inventory(3,2);
        // TODO: Sinnvolle Werte finden
        this.targetsPlayer = false;
        
    }
    
    public void updateStats(int lvl){
        this.lvl = lvl;
        this.standartMaxHp = 100 * lvl;
        this.standartDmg = 100 * lvl;
        updateItems();
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
    
    public void useItem(int x){
        if(inv.getItem(x) != null){
            switch(inv.getItem(x).getId()){
                case 0:
                    // nix lol weil key
                    break;
                case 1:
                    if(hp + inv.getItem(x).getHeal() >= maxhp){
                        hp = maxhp;
                    }
                    else{
                        this.hp = hp + inv.getItem(x).getHeal();
                    }
                    inv.setItem(x, null);
                    break;
                case 2:
                    // nix lol weil amulet
                    break;
            }
            
        }
    }

    public boolean move(int x, int y){
        return false;
    }
    
    @Override
    public Entity shoot(int xPosPlayer, int yPosPlayer){
        Projectile a = null;
        
        if(!isToDelete()){
            double alpha = StaticMath.calculateAngle((int) this.xPos, (int) this.yPos, xPosPlayer, yPosPlayer);
            
            a = new Projectile(this.xPos + 32, this.yPos + 32, this.lvl,(int) this.dmg,  5, false);
            int tempMovementX = (int) (8 * Math.cos(alpha));
            int tempMovementY = (int) (8 * Math.sin(alpha));

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
    
    public boolean checkKey(){
        return inv.checkKey();
    }
    
    public void deleteKey(){
        inv.deleteKey();
    }
    
}
