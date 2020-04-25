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
    Timer tEntities;
    GameScreen m;
    
    int tileX;
    int tileY;
    
    int roomPosX;
    int roomPosY;
    
    int roomX;
    int roomY;
    
    int level;

    int roomAmount;
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
        roomX = 10;
        roomY = 6;
        
        batch = new SpriteBatch();
        v = new MainMenu(volume);
        dg = new DungeonGenerator();
        
        
        d = dg.generateDungeon(roomX - 1, roomY - 1, 48, new Player());
        dg.ichWillSpielen(d);
        
        tileX = roomX / 2;
        tileY = roomY / 2;
        
        roomAmount = d.getLevel()[0].getRooms().length;
        
        int startRoom = (int) (Math.random() * roomAmount);
            
        level = 0;

        int k = 0;
        for(int i = 0; i < roomAmount; i++){
            for(int j = 0; j < roomAmount; j++){
                if(d.getLevel()[level].getRooms()[i][j] != null){

                    if(k == startRoom){
                        // Startraum wurde ausgewählt
                        roomPosX = i;
                        roomPosY = j;

                    }

                    k++;
                }

            }
        }
        
        d.setCurrentLevel(d.getLevel()[level]);
        d.setCurrentRoom(d.getCurrentLevel().getRooms()[roomPosX][roomPosY]);
        d.setCurrentEntities(d.getCurrentRoom().getEnemies());

        Gdx.input.setInputProcessor(this);
        
        entityMovement = new Timer();
        
        entityMovement.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        for(int i = 0; i < d.getCurrentEntities().length; i++){
                            if(d.getCurrentEntities()[i] != null){
                                d.getCurrentEntities()[i].randomMove(roomX, roomY);
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
                // Position des Players, etc. werden aktualisiert
                updateObjects(level, roomPosX, roomPosY);
                
                
                // tile[] beinhaltet die x und y Nummer des tiles, in dem sich der Player befindet
                tileX = (int) d.getPlayer().getxPos() / 48;
                tileY = (int) d.getPlayer().getyPos() / 48;
                
                if(tileX == 0 || tileX == roomX || tileY == 0 || tileY == roomY){
                    updateRoom();
                }

                
                // Render methode zum rendern der einzelnen Sprites wird aufgerufen
                m.render(batch, d.getPlayer(), d.getCurrentEntities(), arrows,  tileX, tileY, level, roomPosX, roomPosY);
            }
        }
    }
    
    
    @Override
    public void dispose () {
        batch.dispose();
    }
        
    public void updateObjects(int level, int roomPosX, int roomPosY){
        
        MapLayers layers = m.getM().getMaps()[level][roomPosX][roomPosY].getLayers();
        MapObjects objects = layers.get(0).getObjects();
        //System.out.println(objects.getCount());
        
        updatePlayer(objects);
    }
    
    public void updatePlayer(MapObjects objects){
        
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
        
        //System.out.println(roomX + " " + roomY);
        //System.out.println("pos Player tiles: " + tileX + " " + tileY);
        
        // Temp variablen werden wieder auf ihre Plätze geschrieben
        
        // Entities
        d.getCurrentRoom().setEnemies(d.getCurrentEntities());
        
        // Room
        d.getCurrentLevel().setRoom(d.getCurrentRoom(), roomPosX, roomPosY);
        
        // Level
        d.setLevel(d.getCurrentLevel(), level);
        
        // oben
        if(tileX == (roomX / 2) && tileY == roomY){
            System.out.println("oben");

            roomPosY += 1;
            d.getPlayer().setxPos((roomX / 2)* 48);
            d.getPlayer().setyPos(48);
        }

        // rechts
        if(tileX == roomX && tileY == (roomY / 2)){
            System.out.println("rechts");

            roomPosX += 1;
            d.getPlayer().setxPos(48);
            d.getPlayer().setyPos((roomY / 2)*48);
        }

        // unten
        if(tileX == (roomX / 2) && tileY == 0){
            System.out.println("unten");

            roomPosY -= 1;
            d.getPlayer().setxPos((roomX / 2)*48);
            d.getPlayer().setyPos(roomY*48 - 48);
        }

        // links
        if(tileX == 0 && tileY == (roomY / 2)){
            System.out.println("links");

            roomPosX -= 1;
            d.getPlayer().setxPos((roomX*48) - 48);
            d.getPlayer().setyPos((roomY / 2)*48);
        }
        
        if(roomPosX == d.getCurrentLevel().getExit()[0] && roomPosY == d.getCurrentLevel().getExit()[1]){
            if(level < 6){
                System.out.println("Nächstes Level, here we go");
                level++;
                
                tileX = roomX / 2;
                tileY = roomY / 2;
                
                int roomAmount = d.getLevel()[level].getRooms().length;
                roomPosX = roomAmount / 2;
                roomPosY = roomAmount / 2;
            }
        }
        
        d.setCurrentLevel(d.getLevel()[level]);
        d.setCurrentRoom(d.getCurrentLevel().getRooms()[roomPosX][roomPosY]);
        d.setCurrentEntities(d.getCurrentRoom().getEnemies());
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
                        d.setCurrentEntities(m.playerAttack(d.getCurrentEntities(), d.getPlayer()));
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
                  v.cleanUp();
                  v = null;
                  m = new GameScreen(d, volume);
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
    
}
