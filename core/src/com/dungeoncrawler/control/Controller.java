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
    Player p;
    Entity[] e;
    Timer t;
    Map m;
    
    @Override
    public void create(){
        batch = new SpriteBatch();
        v = new MainMenu();
        p = new Player();
        d = new Dungeon(p);
        dg = new DungeonGenerator();
        dg.ichWillSpielen();
        e = new Entity[5];
        //TEST
        Archer a = new Archer(0,0,0);
        newEntity(a, 200, 500, 200);
        Swordsman s = new Swordsman(0,0,0);
        newEntity(s, 500, 200, 500);
        //
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
                        v.newEntity(i,ent,x,y);
                        i = 10;
                        break;
                        case 1:    
                        e[i] = new Swordsman(x,y,lvl);
                        v.newEntity(i,ent,x,y);
                        i = 10;
                        break;
                    }
                    
                }
            }
    }
    
    @Override
    public void render(){
        if(v != null){
        v.render(batch, p , e);
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
                    p.setMovementX(-5);
                    }
                }
                
                if(keycode == Input.Keys.RIGHT){
                    if(v != null){
                    v.moveCursor(1);
                    }
                    if(m != null){
                    p.setMovementX(5);
                    }
                }
                
                if(keycode == Input.Keys.DOWN){
                    if(v != null){
                    v.moveCursor(2);
                    }
                    if(m != null){
                    p.setMovementY(-5);
                    }
                } 
                
                if(keycode == Input.Keys.UP){
                    if(v != null){
                    v.moveCursor(0);
                    }
                    if(m != null){
                    p.setMovementY(5);
                    }
                } 
                
                if(keycode == Input.Keys.ENTER){
                    if(v != null){
                    if(v.click() == -1){}
                    else if(v.click() == 0){
                        v = null;
                        m = new Map();
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
                    p.setMovementX(0);
                    }
                }
                
                if(keycode == Input.Keys.RIGHT){
                    if(v != null){
                    v.stopCursor(1);
                    }
                    if(m != null){
                    p.setMovementX(0);
                    }
                }
                
                if(keycode == Input.Keys.DOWN){
                    if(v != null){
                    v.stopCursor(2);
                    }
                    if(m != null){
                    p.setMovementY(0);
                    }
                } 
                
                if(keycode == Input.Keys.UP){
                    if(v != null){
                    v.stopCursor(0);
                    }
                    if(m != null){
                    p.setMovementY(0);
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
