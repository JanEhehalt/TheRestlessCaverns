package com.dungeoncrawler.model;


public class Inventory {
    
    Item items[][];
    Item equipped[];
    
    Inventory(int width, int height){
        items = new Item[width][height];
        equipped = new Item[2];
    }
    
    public void addItem(Item i)
    {
        
        for(int n = 0; n <= items.length; n++){
            for(int m = 0; m <= items[0].length; m++){
                if(items[n][m] == null){
                    items[n][m] = i;
                    n = items.length + 1;
                    break;
                } 
            }
        }
    }
    
    public void equipItem(int x, int y){
        if(items[x][y] == null || items[x][y].getId() == 0){}
        else{
            int slot = items[x][y].getId() - 1;
            Item temp = equipped[slot];
            equipped[slot] = items[x][y];
            items[x][y] = temp;
        }
    }
    
    
    public void dropItem(int x, int y){
        items[x][y] = null;
    }
    
    
    public Item getItem(int x, int y){
        return items[x][y];
    }
    
    
}
