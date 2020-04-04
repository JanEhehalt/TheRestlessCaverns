/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model;

/**
 *
 * @author jonathan
 */
public class DungeonGenerator {
    
    public DungeonGenerator(){
        
    }
    
    public Dungeon generateDungeon(int sizeX, int sizeY, int tileSize){
        
        return null;
    }
    
    private int[] generatePos(int sizeX, int sizeY, int tileSize){
        
        int[] position = new int[2];
        
        int xPos = (int) (Math.random() * sizeX);
        int yPos = (int) (Math.random() * sizeY);
        
        if(xPos > sizeX / 2){
            xPos -= tileSize;
        }
        else if(xPos < sizeX / 2){
            
        }
        
        return position;
    }
}
