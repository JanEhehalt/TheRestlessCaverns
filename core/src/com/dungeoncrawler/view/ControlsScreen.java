/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    BitmapFont font;
    
    Texture backButtonTexture;
    
    
    Sprite backButtonSprite;
    
    public ControlsScreen(){
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        float wc = w/2;
        font = new BitmapFont();
    
        backButtonSprite = new Sprite(new Texture("sprites/backButton.png"));
        backButtonSprite.setPosition(0,0);
        
    }
    
    public void render (SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                batch.begin();
                font.draw(batch,"UP:", 500, 500);
                font.draw(batch,"W", 700, 500);
                font.draw(batch,"LEFT:", 500, 480);
                font.draw(batch,"A", 700, 480);
                font.draw(batch,"DOWN:", 500, 460);
                font.draw(batch,"S", 700, 460);
                font.draw(batch,"RIGHT:", 500, 440);
                font.draw(batch,"D", 700, 440);
                
                font.draw(batch,"SHOOT UP:", 500, 400);
                font.draw(batch,"ARROW-KEY UP", 700, 400);
                font.draw(batch,"SHOOT LEFT:", 500, 380);
                font.draw(batch,"ARROW-KEY LEFT", 700, 380);
                font.draw(batch,"SHOOT DOWN:", 500, 360);
                font.draw(batch,"ARROW-KEY DOWN", 700, 360);
                font.draw(batch,"SHOOT RIGHT:", 500, 340);
                font.draw(batch,"ARROW-KEY RIGHT", 700, 340);
                
                font.draw(batch,"MELEE ATTACK:", 500, 320);
                font.draw(batch,"SPACE", 700, 320);
                
                font.draw(batch,"EQUIP ITEM:", 500, 280);
                font.draw(batch,"R", 700, 280);
                font.draw(batch,"DROP ITEM:", 500, 260);
                font.draw(batch,"Q", 700, 260);
                font.draw(batch,"USE ITEM:", 500, 240);
                font.draw(batch,"E", 700, 240);
                font.draw(batch,"CHANCE SELECTED ITEM:", 500, 220);
                font.draw(batch,"SCROLL WHEEL", 700, 220);
                
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
