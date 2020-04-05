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
import com.dungeoncrawler.view.View;
import com.dungeoncrawler.model.Dungeon;
import com.dungeoncrawler.model.entities.Player;
import com.dungeoncrawler.model.entities.Archer;
import com.badlogic.gdx.utils.Timer;

public class Controller extends ApplicationAdapter implements InputProcessor{
    SpriteBatch batch;
    Dungeon d;
    View v;
    Player p; 
    Archer a;
    float movementX = 0f;
    float movementY = 0f;
    Timer t;
    
    @Override
    public void create(){
        batch = new SpriteBatch();
        v = new View();
        p = new Player(0,0,1);
        d = new Dungeon(p);
        a = new Archer(500, 200, 1);
        Gdx.input.setInputProcessor(this);
        t = new Timer();
        t.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        a.rdmMove();
                     }
                },0,0.1f);
    }
    
    @Override
    public void render(){
        v.render(batch, movementX ,movementY, a.getxPos(), a.getyPos());
    }
    
    @Override
	public void dispose () {
            batch.dispose();
	}
        
    @Override
    public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.LEFT){
                    movementX = -3f;
                    v.render(batch, movementX, movementY, a.getxPos(), a.getyPos());
                }
                
                if(keycode == Input.Keys.RIGHT){
                    movementX = 3f;
                    v.render(batch, movementX, movementY, a.getxPos(), a.getyPos());
                }
                
                if(keycode == Input.Keys.UP){
                    movementY = 2f;
                    v.render(batch, movementX, movementY, a.getxPos(), a.getyPos());
                }
                
                if(keycode == Input.Keys.DOWN){
                    movementY = -2f;
                    v.render(batch, movementX, movementY, a.getxPos(), a.getyPos());
                } 
                if(keycode == Input.Keys.W){
                    
                }
                return true;
    }

    @Override
    public boolean keyUp(int keycode) {
                if(keycode == Input.Keys.LEFT){
                    movementX = 0f;
                    v.tlinksstop();
                }
                
                if(keycode == Input.Keys.RIGHT){
                    movementX = 0f;
                    v.trechtsstop();
                }
                
                if(keycode == Input.Keys.DOWN){
                    movementY = 0f;
                    v.tuntenstop();
                } 
                
                if(keycode == Input.Keys.UP){
                    movementY = 0f;
                    v.tobenstop();
                }
                return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
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
