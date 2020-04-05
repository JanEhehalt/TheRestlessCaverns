/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model;

import com.dungeoncrawler.model.entities.Player;

/**
 *
 * @author jonathan
 */
public class DungeonGenerator {
    
    public DungeonGenerator(){
        
    }
    
    public Dungeon generateDungeon(int sizeX, int sizeY, int tileSize, Player player){
        
        Dungeon tempDungeon = new Dungeon(player);
        int levelAmount = tempDungeon.getLevel().length;
        
        for(int i = 0; i < levelAmount; i++){
            tempDungeon.setLevel(generateLevel(), i);
        }
        
        return tempDungeon;
        
    }
    
    private Level generateLevel(){
        
        int roomAmount = (int) (Math.random() * 6) + 5;
        Level tempLevel = new Level(roomAmount);
        
        for(int i = 0; i < roomAmount; i++){
            Room tempRoom = generateRoom();
            tempLevel.setRoom(tempRoom, i);
        }
        
        // TODO: Zufällig Türen setzen
        
        return tempLevel;
    }
    
    private Room generateRoom(){
        
        return null;
    }
    
    private int[] generatePos(int sizeX, int sizeY, int tileSize){
        
        int[] position = new int[2];
        
        int xPos = (int) (Math.random() * sizeX);
        int yPos = (int) (Math.random() * sizeY);
        
        return position;
    }
}
