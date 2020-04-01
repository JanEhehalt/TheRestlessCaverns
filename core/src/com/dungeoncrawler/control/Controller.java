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

public class Controller extends ApplicationAdapter implements InputProcessor{
    SpriteBatch batch;
    Dungeon d;
    View v;
    Player p;
    float movementX = 0f;
    float movementY = 0f;
    
    @Override
    public void create(){
        batch = new SpriteBatch();
        v = new View();
        p = new Player(0,0,0);
        d = new Dungeon(p);
        Gdx.input.setInputProcessor(this);
    }
    
    @Override
    public void render(){
        v.render(batch);
    }
    
    @Override
	public void dispose () {
            batch.dispose();
	}
        
    @Override
    public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.LEFT){
                    movementX = -3f;
                    v.move(movementX, movementY);
                    System.out.println("EY");
                }
                
                if(keycode == Input.Keys.RIGHT){
                    movementX = 3f;
                    v.move(movementX, movementY);
                }
                
                if(keycode == Input.Keys.UP){
                    movementY = 3f;
                    v.move(movementX, movementY);
                }
                
                if(keycode == Input.Keys.DOWN){
                    movementY = -3f;
                    v.move(movementX, movementY);
                } 
                return true;
    }

    @Override
    public boolean keyUp(int i) {
        movementY = 0f;
        movementX = 0f;
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
