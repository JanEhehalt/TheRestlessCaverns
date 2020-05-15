/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Jan
 */
public class EndScreen {
    
        Sprite backButton;
    
    
    public EndScreen(){
        backButton = new Sprite(new Texture("sprites/backButton.png"));
        backButton.setPosition(100, 100);
    }
        
    public void render (SpriteBatch batch, float volume) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                batch.begin();
                backButton.draw(batch);
                batch.end();
	}
    
    public int click(int x, int y){     // prueft ob cursor mit button (START) ueberlappt
            
            Rectangle r = new Rectangle(x,y,1,1);
            if(Intersector.overlaps(r, backButton.getBoundingRectangle())){
                return 11;   //NEUSTART
            }
            return 11;
            //return -1;
            
    }
}
