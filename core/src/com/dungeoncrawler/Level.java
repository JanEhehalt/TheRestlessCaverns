/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.com.dungeoncrawler;

/**
 *
 * @author jonathan
 */
public class Level {
    private Room[] rooms;
    
    public Level(int amount){
        this.rooms = new Room[amount];
    }

    /**
     * @return the rooms
     */
    public Room[] getRooms() {
        return rooms;
    }

    /**
     * @param rooms the rooms to set
     */
    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }
    
    
}
