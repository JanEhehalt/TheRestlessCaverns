/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.model;

import com.dungeoncrawler.model.entities.*;
import com.dungeoncrawler.model.items.Amulet;
import java.util.ArrayList;

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
            tempDungeon.setLevel(generateLevel(sizeX, sizeY, tileSize, i+1), i);
        }
        
        return tempDungeon;
        
    }
    
    private Level generateLevel(int sizeX, int sizeY, int tileSize, int lvl){
        int roomAmount = (int) (Math.random() * 6) + 5;
        Level tempLevel = new Level(roomAmount);
        
        System.out.println(roomAmount);
        
        // TODO: Zufällig Türen setzen
        
        int xPos = roomAmount / 2;
        int yPos = roomAmount / 2;

        tempLevel.setRoom(generateRoom(sizeX, sizeY, tileSize, lvl), xPos, yPos);
        
        // Schleife läuft so lange, bis die entsprechende Anzahl an Räumen generiert wurde
        for(int i = 1; i < roomAmount;){
            
            // Zufallszahl für die Richtung wird generiert, Oben: 0, Rechts: 1, Unten: 2, Links: 3
            int direction = (int) (Math.random() * 4);
            
            switch (direction) {
            // Oben
                case 0:
                    if(yPos != roomAmount - 1){
                        yPos += 1;
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
                    if(yPos != 0){
                        yPos -= 1;
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
                tempLevel.setRoom(generateRoom(sizeX, sizeY, tileSize, lvl), xPos, yPos);
                
                // i darf nur erhöht werden, wenn auch ein Raum generiert wurde
                i++;
            }
        }
            
        // Exit wird generiert
        if(lvl >= 7){
            tempLevel.setExit(-1, 0);
            tempLevel.setExit(-1, 1);
        }
        else{
            boolean istFertig = false;
            do{
                // Zufallszahl für die Richtung wird generiert, Oben: 0, Rechts: 1, Unten: 2, Links: 3
                int direction = (int) (Math.random() * 4);

                switch (direction) {
                // Oben
                    case 0:
                        if(yPos != roomAmount - 1){
                            yPos += 1;
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
                        if(yPos != 0){
                            yPos -= 1;
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
                    tempLevel.setRoom(generateRoom(sizeX, sizeY, tileSize, lvl), xPos, yPos);

                    tempLevel.setExit(xPos, 0);
                    tempLevel.setExit(yPos, 1);

                    istFertig = true;
                }
            } while(!istFertig);
        }
        
        return tempLevel;
    }
    
    private Room generateRoom(int sizeX, int sizeY, int tileSize, int lvl){
        int itemAmount = (int) (Math.random() * 2);
        int enemyAmount = (int) (Math.random() * 6);
        
        Room tempRoom = new Room(new ArrayList<ItemContainer>(itemAmount), new Entity[15]);
        
        // Items werden generiert
        int[][] belegt = new int[itemAmount][2];
        for(int j = 0; j < belegt.length; j++){
            belegt[j][0] = -1;
            belegt[j][1] = -1;
        }
        
        for(int i = 0; i < itemAmount; i++){
            
            int xTile;
            int yTile;
            
            boolean istFertig = false;
            do {
                System.out.println("läuft");
                
                // Tiles des Entities werden generiert

                xTile = generateTile(sizeX);
                yTile = generateTile(sizeY);

                
                // Test, ob Tiles bereits belegt
                boolean hatGeklappt = true;
                for(int j = 0; j < belegt.length; j++){
                    if(j != i){
                        if(xTile == belegt[j][0] && yTile == belegt[j][1]){
                            hatGeklappt = false;
                            break;
                        }
                    }
                }
                
                if(hatGeklappt == true){
                    // Tiles zum Array hinzufügen
                    for(int j = 0; j < belegt.length; j++){
                        if(belegt[j][0] == -1){
                            belegt[j][0] = xTile;
                            belegt[j][1] = yTile;
                        }
                    }
                    
                    istFertig = true;
                }
                
            } while(!istFertig);
            
            
            // Berechnung der Positionen
            
            int xPos = xTile * tileSize;
            int yPos = yTile * tileSize;
            
            // Typ des Entities wird generiert
            
            Item tempItem;
            
            int id = (int) (Math.random() * 2);
            switch(id){
                case 0:
                    tempItem = new Amulet(lvl);
                    break;
                    
                /*case 1:
                    temp = new Swordsman(xPos, yPos, lvl);
                    break;
                */    
                default:
                    tempItem = null;
            }
            
            if(tempItem == null){
                System.out.println("Es gibt Probleme, schau mal beim Raumgenerator nach. Es sind sogar sehr problematische Probleme mit den Items");
            }
            ItemContainer tempContainer;
            if(tempItem != null){
                tempContainer = new ItemContainer(xPos, yPos, tempItem);
            }
            else{
                tempContainer = null;
            }
            
            tempRoom.getItems().add(tempContainer);
        }
        
        // Entities werden generiert
        belegt = new int[enemyAmount][2];
        for(int j = 0; j < belegt.length; j++){
            belegt[j][0] = -1;
            belegt[j][1] = -1;
        }
        
        for(int i = 0; i < enemyAmount; i++){
            
            int xTile;
            int yTile;
            
            boolean istFertig = false;
            do {
                System.out.println("läuft");
                
                // Tiles des Entities werden generiert

                xTile = generateTile(sizeX);
                yTile = generateTile(sizeY);

                
                // Test, ob Tiles bereits belegt
                boolean hatGeklappt = true;
                for(int j = 0; j < belegt.length; j++){
                    if(j != i){
                        if(xTile == belegt[j][0] && yTile == belegt[j][1]){
                            hatGeklappt = false;
                            break;
                        }
                    }
                }
                
                if(hatGeklappt == true){
                    // Tiles zum Array hinzufügen
                    for(int j = 0; j < belegt.length; j++){
                        if(belegt[j][0] == -1){
                            belegt[j][0] = xTile;
                            belegt[j][1] = yTile;
                        }
                    }
                    
                    istFertig = true;
                }
                
            } while(!istFertig);
            
            
            // Berechnung der Positionen
            
            int xPos = xTile * tileSize;
            int yPos = yTile * tileSize;
            
            // Typ des Entities wird generiert
            
            Entity temp;
            
            int id = (int) (Math.random() * 2);
            switch(id){
                case 0:
                    temp = new Archer(xPos, yPos, lvl);
                    break;
                    
                case 1:
                    temp = new Swordsman(xPos, yPos, lvl);
                    break;
                    
                default:
                    temp = null;
            }
            
            if(temp == null){
                System.out.println("Es gibt Probleme, schau mal beim Raumgenerator nach. Es sind sogar sehr problematische Probleme");
            }
            
            tempRoom.setEnemies(temp, i);
        }
        
        return tempRoom;
    }
    
    private int generateTile(int size){
        int tile = ((int) (Math.random() * size) + 1);
        return tile;
    }
    
    public void ichWillSpielen(Dungeon d){
        
        for(int i=0;i<d.getLevel().length;i++){
            Level temp = d.getLevel()[i];
            System.out.println("Level " + i);
            
            for(int j = 0; j < temp.getRooms().length; j++){
                
                for(int k = temp.getRooms().length - 1; k >= 0; k--){
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
