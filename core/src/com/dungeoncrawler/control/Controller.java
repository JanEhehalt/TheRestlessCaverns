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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    Timer t;
    View m;
    Archer a;
    int[] tile;
    int[] posRoom;
    int level;
    
    @Override
    public void create(){
        a = new Archer(200,200,200);
        
        batch = new SpriteBatch();
        v = new MainMenu();
        dg = new DungeonGenerator();
        d = dg.generateDungeon(0, 0, 0, new Player());
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
        t = new Timer();
        t.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        for(int i = 0; i< e.length; i++){
                            if(e[i] == null){}
                            else{
                                e[i].rdmMove();
                            }
                        }
                     }
                },0,0.1f);
    }
    
    public void newEntity(Entity ent, int x, int y, int lvl){
        for(int i = 0; i < e.length ; i++){
                if(e[i] == null){
                    switch(ent.getId()){
                        case 0:
                        e[i] = new Archer(x,y,lvl);
                        m.newEntity(i,ent,x,y);
                        i = 10;
                        break;
                        case 1:    
                        e[i] = new Swordsman(x,y,lvl);
                        m.newEntity(i,ent,x,y);
                        i = 10;
                        break;
                    }
                    
                }
            }
    }
    
    @Override
    public void render(){
        if(v != null){
            v.render(batch, d.getPlayer() , e);
        }
        if(v == null){
            
            d.getPlayer().update();
            
            tile[0] = d.getPlayer().getxPos() / 48;
            tile[1] = d.getPlayer().getyPos() / 48;
            
            System.out.println("pos Player tiles: " + tile[0] + " " + tile[1]);
            
            int xPos = tile[0];
            int yPos = tile[1];
            
            // oben
            if(xPos == 4 && yPos == 6){
                System.out.println("oben");
                
                posRoom[1] += 1;
                d.getPlayer().setxPos(100);
                d.getPlayer().setyPos(100);
            }
            
            // rechts
            if(xPos == 8 && yPos == 3){
                System.out.println("rechts");
                
                posRoom[0] += 1;
                d.getPlayer().setxPos(100);
                d.getPlayer().setyPos(100);
            }
            
            // unten
            if(xPos == 4 && yPos == 0){
                System.out.println("unten");
                
                posRoom[1] -= 1;
                d.getPlayer().setxPos(100);
                d.getPlayer().setyPos(100);
            }
            
            // links
            if(xPos == 0 && yPos == 3){
                System.out.println("links");
                
                posRoom[0] -= 1;
                d.getPlayer().setxPos(100);
                d.getPlayer().setyPos(100);
            }
            
            // Render methode zum rendern der einzelnen Sprites wird aufgerufen
            m.render(batch, d.getPlayer(), a, tile, level, posRoom);
        }
    }
    
    @Override
	public void dispose () {
            batch.dispose();
	}
        
    @Override
    public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.LEFT){
                    if(v != null){
                        v.moveCursor(3);
                    }
                    if(m != null){
                        d.getPlayer().setMovementX(-3);
                    }
                }
                
                if(keycode == Input.Keys.RIGHT){
                    if(v != null){
                        v.moveCursor(1);
                    }
                    if(m != null){
                        d.getPlayer().setMovementX(3);
                    }
                }
                
                if(keycode == Input.Keys.DOWN){
                    if(v != null){
                    v.moveCursor(2);
                    }
                    if(m != null){
                    d.getPlayer().setMovementY(-3);
                    }
                } 
                
                if(keycode == Input.Keys.UP){
                    if(v != null){
                    v.moveCursor(0);
                    }
                    if(m != null){
                    d.getPlayer().setMovementY(3);
                    }
                } 
                
                if(keycode == Input.Keys.ENTER){
                    if(v != null){
                    if(v.click() == -1){}
                    else if(v.click() == 0){
                        v = null;
                        m = new View(d);
                        System.out.println("NICE");
                    }
                    }
                }
                return true;
    }

    @Override
    public boolean keyUp(int keycode) {
                if(keycode == Input.Keys.LEFT){
                    if(v != null){
                    v.stopCursor(3);
                    }
                    if(m != null){
                    d.getPlayer().setMovementX(0);
                    }
                }
                
                if(keycode == Input.Keys.RIGHT){
                    if(v != null){
                    v.stopCursor(1);
                    }
                    if(m != null){
                    d.getPlayer().setMovementX(0);
                    }
                }
                
                if(keycode == Input.Keys.DOWN){
                    if(v != null){
                    v.stopCursor(2);
                    }
                    if(m != null){
                    d.getPlayer().setMovementY(0);
                    }
                } 
                
                if(keycode == Input.Keys.UP){
                    if(v != null){
                    v.stopCursor(0);
                    }
                    if(m != null){
                    d.getPlayer().setMovementY(0);
                    }
                } 
                
                if(keycode == Input.Keys.ENTER){
                    if(v != null){
                    }
                    if(m != null){
                        
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
      
      return false;
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
