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
        
        System.out.println(roomAmount);
        
        // TODO: Zufällig Türen setzen
        
        //int xPos = roomAmount / 2;
        //int yPos = roomAmount / 2;
        
        int xPos = (int) (Math.random() * roomAmount);
        int yPos = (int) (Math.random() * roomAmount);

        tempLevel.setRoom(generateRoom(), xPos, yPos);
        
        for(int i = 1; i < roomAmount;){
            // Zufallszahl für die Richtung wird generiert, Oben: 0, Rechts: 1, Unten: 2, Links: 3
            int direction = (int) (Math.random() * 4);
            
            switch (direction) {
            // Oben
                case 0:
                    if(yPos != 0){
                        yPos -= 1;
                    }
                    break;
            // Rechts
                case 1:
                    if(xPos != roomAmount - 1){
                        xPos += 1;
                    }
                    break;
            // Unten
                case 2:
                    if(yPos != roomAmount - 1){
                        yPos += 1;
                    }
                    break;
            // Links
                case 3:
                    if(xPos != 0){
                        xPos -= 1;
                    }
                    break;
            }
            
            // An der neuen Stelle vom Cursor gibt es noch keinen Raum
            if(tempLevel.getRooms()[xPos][yPos] == null){
                tempLevel.setRoom(generateRoom(), xPos, yPos);
                
                // i darf nur erhöht werden, wenn auche in Raum generiert wurde
                i++;
            }
        }
        
        return tempLevel;
    }
    
    private Room generateRoom(){
        int itemAmount = (int) (Math.random() * 2);
        int enemyAmount = (int) (Math.random() * 6);
        
        Room tempRoom = new Room(new ItemContainer[itemAmount], new Entity[enemyAmount]);
        
        
        return tempRoom;
    }
    
    private int[] generatePos(int sizeX, int sizeY, int tileSize){
        
        int[] position = new int[2];
        
        int xPos = (int) (Math.random() * sizeX);
        int yPos = (int) (Math.random() * sizeY);
        
        return position;
    }
    
    public void ichWillSpielen(){
        Dungeon d = this.generateDungeon(200, 200, 200, new Player());
        
        for(int i=0;i<d.getLevel().length;i++){
            Level temp = d.getLevel()[i];
            System.out.println("Level " + i);
            
            for(int j = 0; j < temp.getRooms().length; j++){
            //System.out.print(knoten[i].gebeName() + " ");
            for(int k = 0; k < temp.getRooms().length; k++){
                if(temp.getRooms()[j][k] == null){
                    System.out.print("0 ");
                }
                else{
                    System.out.print("1 ");
                }
            }
            System.out.println();
        }
        }
    }
}
