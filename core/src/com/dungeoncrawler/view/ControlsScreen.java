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
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author bfz
 */
public class ControlsScreen {
    float w;
    float h;
    
    Texture backButtonTexture;
    
    
    Sprite backButtonSprite;
    
    public ControlsScreen(){
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        float wc = w/2;
    
        backButtonTexture = new Texture("sprites/backButton.png");
        
        
        backButtonSprite = new Sprite(backButtonTexture);
        
    }
    
    public void render (SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                batch.begin();
                //batch.setProjectionMatrix(camera.combined);
                backButtonSprite.draw(batch);
                batch.end();
	}
    
    public int click(int x, int y){     // prueft ob cursor mit button (START) ueberlappt
            
            Rectangle r = new Rectangle();
            r.set(x, h-y, 1, 1);
            if(Intersector.overlaps(r, backButtonSprite.getBoundingRectangle())){
                return 2;   //go settingScreen
            }
            
            return -1;
            
        }
    
    
    
}
