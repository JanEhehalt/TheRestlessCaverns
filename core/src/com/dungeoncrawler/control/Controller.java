/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.control;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.dungeoncrawler.view.*;
import com.dungeoncrawler.model.Dungeon;
import com.dungeoncrawler.model.DungeonGenerator;
import com.dungeoncrawler.model.entities.*;
import com.dungeoncrawler.model.Entity;
import com.badlogic.gdx.utils.Timer;

public class Controller extends ApplicationAdapter implements InputProcessor{
    SpriteBatch batch;
    Dungeon d;
    DungeonGenerator dg;
    MainMenu v;
    Entity[] e;
    Timer tEntities;
    GameScreen m;
    int[] tile;
    int[] posRoom;
    int level;
    int roomX;
    int roomY;
    float volume;
    
    Entity[] arrows;
    
    Texture verticalAttack;
    Texture horizontalAttack;
    Sprite verticalAttackSprite;
    Sprite horizontalAttackSprite;
    
    Timer entityMovement;
    
    @Override
    public void create(){
        
        volume = 0.01f;
        
        arrows = new Entity[10];
        roomX = 8;
        roomY = 6;
        
        batch = new SpriteBatch();
        v = new MainMenu(volume);
        dg = new DungeonGenerator();
        
        
        d = dg.generateDungeon(roomX - 1, roomY - 1, 48, new Player());
        dg.ichWillSpielen(d);
        
        tile = new int[2];
        posRoom = new int[2];
        
        int roomLengthX = d.getLevel()[0].getRooms().length;
        int roomLengthY = d.getLevel()[0].getRooms()[0].length;
        int roomAmount = d.getLevel()[0].getRooms().length;
        
        int startRoom = (int) (Math.random() * roomAmount);
            
        level = 0;

        int k = 0;
        for(int i = 0; i < roomLengthX; i++){
            for(int j = 0; j < roomLengthY; j++){
                if(d.getLevel()[level].getRooms()[i][j] != null){

                    if(k == startRoom){
                        // Startraum wurde ausgewählt
                        posRoom[0] = i;
                        posRoom[1] = j;

                    }

                    k++;
                }

            }
        }
        
        e = new Entity[5];

        Gdx.input.setInputProcessor(this);
        
        entityMovement = new Timer();
        
        entityMovement.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        for(int i = 0; i < e.length; i++){
                            if(e[i] != null){
                                e[i].randomMove(roomX, roomY);
                            }
                        }
                    }
        },0,1f);
    }
    
    
    @Override
    public void render(){
        
        //PASSIERT IN MAINMENU
        if(v != null){
            v.render(batch);
        }
        
        //PASSIERT IN GAMESCREEN
        if(m != null){
            
            if(v == null){
                e = d.getLevel()[level].getRooms()[posRoom[0]][posRoom[1]].getEnemies();
                // Position des Players, etc. werden aktualisiert
                updateObjects(level, posRoom);
                
                // Raum, in dem sich der Player jetzt befindet, wird aktualisiert
                updateRoom();

                
                // Render methode zum rendern der einzelnen Sprites wird aufgerufen
                m.render(batch, d.getPlayer(), e, arrows,  tile, level, posRoom);
            }
        }
    }
    
    
    @Override
    public void dispose () {
        batch.dispose();
    }
        
    public void updateObjects(int level, int[] posRoom){
        
        MapLayers layers = m.getM().getMaps()[level][posRoom[0]][posRoom[1]].getLayers();
        MapObjects objects = layers.get(0).getObjects();
        System.out.println(objects.getCount());
        
        float x = d.getPlayer().getxPos();
        d.getPlayer().updateX();
        
        m.setPlayerSpriteX(d.getPlayer().getxPos());
        
        for(RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)){
            Rectangle rectangle = rectangleObject.getRectangle();
            
            if(Intersector.overlaps(rectangle, m.getPlayer().getBoundingRectangle())){
                
                d.getPlayer().setxPos(x);

                System.out.println("Es laedt, es laedt, ich will nicht, dass es laedt, wenn es laedt, muss man immer so lange warten!!!!!");
            }
        }
        
        float y = d.getPlayer().getyPos();
        d.getPlayer().updateY();
        m.setPlayerSpriteX(d.getPlayer().getxPos());
        m.setPlayerSpriteY(d.getPlayer().getyPos());
        
        for(RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)){
            Rectangle rectangle = rectangleObject.getRectangle();
            
            if(Intersector.overlaps(rectangle, m.getPlayer().getBoundingRectangle())){
                
                d.getPlayer().setyPos(y);

                System.out.println("Es laedt, es laedt, ich will nicht, dass es laedt, wenn es laedt, muss man immer so lange warten!!!!!");
            }
        }
    }
    
    public void updateRoom(){
        // tile[] beinhaltet die x und y Nummer des tiles, in dem sich der Player befindet
        tile[0] = (int) d.getPlayer().getxPos() / 48;
        tile[1] = (int) d.getPlayer().getyPos() / 48;
        
        System.out.println(roomX + " " + roomY);

        System.out.println("pos Player tiles: " + tile[0] + " " + tile[1]);

        int xPos = tile[0];
        int yPos = tile[1];

        // oben
        if(xPos == (roomX / 2) && yPos == roomY){
            System.out.println("oben");

            posRoom[1] += 1;
            d.getPlayer().setxPos((roomX / 2)* 48);
            d.getPlayer().setyPos(48);
        }

        // rechts
        if(xPos == roomX && yPos == (roomY / 2)){
            System.out.println("rechts");

            posRoom[0] += 1;
            d.getPlayer().setxPos(48);
            d.getPlayer().setyPos((roomY / 2)*48);
        }

        // unten
        if(xPos == (roomX / 2) && yPos == 0){
            System.out.println("unten");

            posRoom[1] -= 1;
            d.getPlayer().setxPos((roomX / 2)*48);
            d.getPlayer().setyPos(roomY*48 - 48);
        }

        // links
        if(xPos == 0 && yPos == (roomY / 2)){
            System.out.println("links");

            posRoom[0] -= 1;
            d.getPlayer().setxPos((roomX*48) - 48);
            d.getPlayer().setyPos((roomY / 2)*48);
        }
        
        if(posRoom[0] == d.getLevel()[level].getExit()[0] && posRoom[1] == d.getLevel()[level].getExit()[1]){
            if(level < 6){
                System.out.println("Nächstes Level, here we go");
                level++;
                
                tile[0] = roomX / 2;
                tile[1] = roomY / 2;
                
                int roomAmount = d.getLevel()[level].getRooms().length;
                posRoom[0] = roomAmount / 2;
                posRoom[1] = roomAmount / 2;
            }
        }
    }
        
    public void newEntity(Entity ent, float x, float y, int lvl){
        for(int i = 0; i < e.length ; i++){
            if(e[i] == null){
                switch(ent.getId()){
                    case 0:
                        e[i] = new Archer(x,y,lvl);
                        i = 11;
                        break;
                    case 1:
                        e[i] = new Swordsman(x,y,lvl);
                        i = 11;
                        break;
                }
            }
        }
    }

    
    @Override
    public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.A){
                    if(v != null){
                    }
                    if(m != null){
                            d.getPlayer().setMovementX(-3f);
                    }
                }
                
                if(keycode == Input.Keys.D){
                    if(v != null){
                    }
                    if(m != null){
                            d.getPlayer().setMovementX(+3f);
                    }
                }
                
                if(keycode == Input.Keys.S){
                    if(v != null){
                    }
                    if(m != null){
                            d.getPlayer().setMovementY(-3f);
                    }
                } 
                
                if(keycode == Input.Keys.W){
                    if(v != null){
                    }
                    if(m != null){
                            d.getPlayer().setMovementY(3f);
                    }
                } 
                
                if(keycode == Input.Keys.E){
                    if(v != null){}
                    if(m != null){
                        d.getLevel()[level].getRooms()[posRoom[0]][posRoom[1]].setEnemies(m.playerAttack(e, d.getPlayer()));
                    }
                }
                
                if(keycode == Input.Keys.UP){
                    volume += 0.1f;
                    
                    if(v != null){
                        v.music.setVolume(volume);
                    }
                    if(m != null){
                        m.music.setVolume(volume);
                    }
                }
                
                if(keycode == Input.Keys.DOWN){
                    if(volume >= 0.1f){
                        volume -= 0.1f;
                    }
                    
                    if(v != null){
                        v.music.setVolume(volume);
                    }
                    if(m != null){
                        m.music.setVolume(volume);
                    }
                }
                return true;
    }

    @Override
    public boolean keyUp(int keycode) {
                if(keycode == Input.Keys.A){
                    if(v != null){
                    }
                    if(m != null){
                    d.getPlayer().setMovementX(0);
                    }
                }
                
                if(keycode == Input.Keys.D){
                    if(v != null){
                    }
                    if(m != null){
                    d.getPlayer().setMovementX(0);
                    }
                }
                
                if(keycode == Input.Keys.S){
                    if(v != null){
                    }
                    if(m != null){
                    d.getPlayer().setMovementY(0);
                    }
                } 
                
                if(keycode == Input.Keys.W){
                    if(v != null){
                    }
                    if(m != null){
                    d.getPlayer().setMovementY(0);
                    }
                } 
                
                return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button)
  {
    if(button == Input.Buttons.LEFT){
      if(v != null){
          switch(v.click(screenX, screenY)){
              case -1:
                  
                  return true;
              case 0:
                  v.cleanUp();
                  v = null;
                  m = new GameScreen(d, volume);
                  return true;
              case 1:
                  
                  return true;
          }
          
        return true;
      }
      if(m != null){
          
        return true;
      }
    }
      return true;
  }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
    
    public Entity[] getEntities(){
        return e;
    }
    
    
    
    
}
