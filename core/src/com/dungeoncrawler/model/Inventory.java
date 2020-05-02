package com.dungeoncrawler.model;


public class Inventory {
    
    Item items[];
    int selected;
    int width;
    int height;
    
    public Inventory(int width, int height){
        this.width = width;
        this.height = height;
        items = new Item[8];
        selected = 2;
    }
    
    public void addItem(Item i)
    {
        for(int n = 2; n < items.length; n++){
            if(i.getId() == 2){
                if(items[1] == null){
                    items[1] = i;
                    n = items.length + 1;
                    break;
                } 
            }
                if(items[n] == null){
                    items[n] = i;
                    n = items.length + 1;
                    break;
                } 
        }
    }
    
    public void equipItem(){
        if(selected == 0 || selected == 1){
            Item temp0 = items[selected];
            items[selected] = null;
                addItem(temp0);
        }
        else{
            if(items[selected] == null || items[selected].getId() == 0){}
            else{
                switch(items[selected].getId()){
                    case 0:
                        break;
                    case 1:
                        Item temp1 = items[0];
                        items[0] = items[selected];
                        items[selected] = temp1;
                        break;
                    case 2:
                        Item temp2 = items[1];
                        items[1] = items[selected];
                        items[selected] = temp2;
                        break;
                }
            }
        }
    }
    
    
    public void scroll(int i){
        selected += i;
    }
    
    public void dropItem(){
        items[selected] = null;
    }
    
    
    public Item getItem(int x){
        return items[x];
    }
    
    public Item[] getItem(){
        return items;
    }
    
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getSelected(){
        return selected;
    }
    
}
