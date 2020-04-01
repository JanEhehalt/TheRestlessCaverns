package src.com.dungeoncrawler;


public class Inventory {
    
    Item items[][];
    
    
    Inventory(int width, int height){
        items = new Item[width][height];
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
    
    public void dropItem(int x, int y){
        items[x][y] = null;
    }
    
    
    public Item getItem(int x, int y){
        return items[x][y];
    }
    
    
}
