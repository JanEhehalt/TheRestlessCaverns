package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.dungeoncrawler.model.entities.*;
import com.dungeoncrawler.model.Entity;


public class MainMenu{
        //MENU-SCREEN
	Texture b;
        Texture t;
        Sprite button;
        Sprite title;
        
        //ENTITIES
        Texture[] entityTextures;
        Sprite[] entitySprites;
        
        //CURSOR
        Texture c;
        Sprite cursor;
        float CursorMoveX;
        float CursorMoveY;
        
	public MainMenu() {
                //MENU-SCREEN
                float w = Gdx.graphics.getWidth();
                float h = Gdx.graphics.getHeight();
                float wc = w/2;
                b = new Texture("Button.png");
                t = new Texture("Title.png");
                button = new Sprite(b);
                title = new Sprite(t);
                title.setX(wc - (title.getWidth()/2));
                title.setY(h - 200);
                button.setX(wc - (button.getWidth()/2));
                button.setY(400);
                
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
                title.draw(batch);
                button.draw(batch);
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
            Rectangle rectangleButton = button.getBoundingRectangle();
            boolean overlapsPlay = rectangleCursor.overlaps(rectangleButton);
            if(overlapsPlay == true){
                return 0;           // ints weil fuer mehr buttons eine ID festgelegt werden kann. 0 = START - -1 = kein button
            }
            else{return -1;}
        }
        
        
        
       
        }
