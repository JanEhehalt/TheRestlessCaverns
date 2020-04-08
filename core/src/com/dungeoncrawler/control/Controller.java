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
    
    @Override
    public void create(){
        a = new Archer(200,200,200);
        
        batch = new SpriteBatch();
        v = new MainMenu();
        d = new Dungeon(new Player());
        dg = new DungeonGenerator();
        dg.ichWillSpielen();
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
            int xPosRoom = d.getPlayer().getxPos() / 48;
            int yPosRoom = d.getPlayer().getyPos() / 48;
            
            System.out.println(xPosRoom + " " + yPosRoom);
            
            d.getPlayer().setxPos(d.getPlayer().getxPos()+ d.getPlayer().getMovementX());
            d.getPlayer().setyPos(d.getPlayer().getyPos()+ d.getPlayer().getMovementY());
            
            // Render methode zum rendern der einzelnen Sprites wird aufgerufen
            m.render(batch, d.getPlayer(), a, xPosRoom, yPosRoom);
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
                        m = new View();
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
