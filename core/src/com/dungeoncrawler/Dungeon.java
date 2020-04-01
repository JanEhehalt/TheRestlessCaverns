/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.com.dungeoncrawler;

import com.dungeoncrawler.entities.Player;

/**
 *
 * @author jonathan
 */
public class Dungeon {
    private Level level[];
    private Player player;
    private int playerRoom;
    private int playerLevel;
    
    public Dungeon(Player player){
        this.level = new Level[7];
        this.player = player;
        this.playerRoom = 0;
        this.playerLevel = 0;
        
        createLvl();
    }
    
    public void update(){
        // TODO: Implementieren
    }
    
    private void createLvl(){
        // TODO. Implementieren
    }

    /**
     * @return the level
     */
    public Level[] getLevel() {
        return this.level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Level[] level) {
        this.level = level;
    }
    
    /**
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * @return the playerRoom
     */
    public int getPlayerRoom() {
        return playerRoom;
    }

    /**
     * @param playerRoom the playerRoom to set
     */
    public void setPlayerRoom(int playerRoom) {
        this.playerRoom = playerRoom;
    }

    /**
     * @return the playerLevel
     */
    public int getPlayerLevel() {
        return playerLevel;
    }

    /**
     * @param playerLevel the playerLevel to set
     */
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    
    
}
