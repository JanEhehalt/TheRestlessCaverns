package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.dungeoncrawler.model.entities.*;
import com.dungeoncrawler.model.Entity;


public class MainMenu{
        //MENU-SCREEN
	Texture startButtonTexture;
        Texture quitButtonTexture;
        Texture backgroundTexture;
        Sprite startButtonSprite;
        Sprite quitButtonSprite;
        Sprite backgroundSprite;
        
        //ENTITIES
        
        //CURSOR
        Texture c;
        Sprite cursor;
        float CursorMoveX;
        float CursorMoveY;
        
        //CAMERA
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        OrthographicCamera camera;
        
	public MainMenu() {
                //MENU-SCREEN
                float w = Gdx.graphics.getWidth();
                float h = Gdx.graphics.getHeight();
                float wc = w/2;
                
                
                
                startButtonTexture = new Texture("startButton.png");
                quitButtonTexture = new Texture("quitButton.png");
                backgroundTexture = new Texture("MAINSCREEN.png");
                
                startButtonSprite = new Sprite(startButtonTexture);
                quitButtonSprite = new Sprite(quitButtonTexture);
                backgroundSprite = new Sprite(backgroundTexture);
                
                startButtonSprite.setX(100f);
                startButtonSprite.setY(350f);
                quitButtonSprite.setX(50f);
                quitButtonSprite.setY(50f);
                backgroundSprite.setX(0f);
                backgroundSprite.setY(0f);
                
                camera = new OrthographicCamera(1, h/w);
                camera.zoom = 1200f;
                camera.translate(backgroundSprite.getWidth()/2, backgroundSprite.getHeight()/2);
                camera.update();
                
                Pixmap pm = new Pixmap(Gdx.files.internal("cursor.png"));
                Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
                pm.dispose();
                
                //ENTITIES
                
                //CURSOR
                c = new Texture("cursor.png");
                cursor = new Sprite(c);
                cursor.setX((float) w/2);
                cursor.setY((float) h/2);
                CursorMoveX = 0f;
                CursorMoveY = 0f;
                
                //PLAYER
	}

        
	public void render (SpriteBatch batch, Player p, Entity[] a) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                cursor.setX(cursor.getX()+ CursorMoveX);
                cursor.setY(cursor.getY()+ CursorMoveY);
                
                batch.begin();
                batch.setProjectionMatrix(camera.combined);
                backgroundSprite.draw(batch);
                startButtonSprite.draw(batch);
                quitButtonSprite.draw(batch);
                cursor.draw(batch);
                batch.end();
	}

        
            
        
        
        public void moveCursor(int direction){
            switch(direction){                  //starts the directional movement of the cursor in one direction depending on the pressed key
                case 0:
                CursorMoveY = 10f; break;
                case 1:
                CursorMoveX = 10f; break;
                case 2:
                CursorMoveY = -10f; break;
                case 3:
                CursorMoveX = -10f; break;
            }
        }
        public void stopCursor(int direction){  //stops the directional movement of the cursor in one direction depending on the released key
            switch(direction){
                case 0:
                CursorMoveY = 0f; break;
                case 1:
                CursorMoveX = 0f; break;
                case 2:
                CursorMoveY = 0f; break;
                case 3:
                CursorMoveX = 0f; break;
            }
        }
        public int click(){     // prueft ob cursor mit button (START) ueberlappt
            Rectangle rectangleCursor = cursor.getBoundingRectangle();
            boolean overlapsPlay = rectangleCursor.overlaps(startButtonSprite.getBoundingRectangle());
            boolean overlapsQuit = rectangleCursor.overlaps(quitButtonSprite.getBoundingRectangle());
            if(overlapsPlay == true){
                return 0;           // ints weil fuer mehr buttons eine ID festgelegt werden kann. 0 = START || 1 = QUIT ||| -1 = kein button
            }
            else if(overlapsQuit == true){
                return 1;
            }
            else{return -1;}
        }
        
        
        
       
        }
