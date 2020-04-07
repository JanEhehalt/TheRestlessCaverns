package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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
        /*
        //PLAYER
        Texture p;
        Sprite player;
        TextureRegion[][] regions;
        */
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
                //ENTITIES
                entityTextures = new Texture[5];
                entitySprites = new Sprite[5];
                
                //CURSOR
                c = new Texture("cursor.png");
                cursor = new Sprite(c);
                cursor.setX((float) w/2);
                cursor.setY((float) h/2);
                CursorMoveX = 0f;
                CursorMoveY = 0f;
                
                //PLAYER
                /*
                p = new Texture("player.png");
                regions = TextureRegion.split(p, 64, 64);
                player = new Sprite(regions[0][0]);
                player.setX(200);
                player.setY(200);
                */
	}

        
	public void render (SpriteBatch batch, Player p, Entity[] a) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                cursor.setX(cursor.getX()+ CursorMoveX);
                cursor.setY(cursor.getY()+ CursorMoveY);
                                                            /*
                                                            player.setX(player.getX()+ (float) p.getMovementX());
                                                            player.setY(player.getY()+ (float) p.getMovementY());

                                                            if(p.getMovementX() == 5){
                                                                player.setRegion(regions[0][1]);
                                                            }
                                                            if(p.getMovementX() == -5){
                                                                player.setRegion(regions[0][3]);
                                                            }
                                                            if(p.getMovementY() == 5){
                                                                player.setRegion(regions[0][0]);
                                                            }
                                                            if(p.getMovementY() == -5){
                                                                player.setRegion(regions[0][2]);
                                                            }
                                                            */
                batch.begin();
                title.draw(batch);
                button.draw(batch);
                cursor.draw(batch);
                if(entitySprites[0] != null){
                    entitySprites[0].draw(batch);
                }
                if(entitySprites[1] != null){
                    entitySprites[1].draw(batch);
                }
                batch.end();
	}

        public void newEntity(int i,Entity e, int x, int y){
                    if(e.getId() == 0){
                        entityTextures[i] = new Texture("archer.png");
                        entitySprites[i] = new Sprite(entityTextures[i]);
                        entitySprites[i].setX(x);
                        entitySprites[i].setY(y);
                    }
                    else if(e.getId() == 1){
                        entityTextures[i] = new Texture("Swordsman.png");
                        entitySprites[i] = new Sprite(entityTextures[i]);
                        entitySprites[i].setX(x);
                        entitySprites[i].setY(y);
                    }
        }
            
        
        
        public void moveCursor(int direction){
            switch(direction){
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
        public void stopCursor(int direction){
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
        public int click(){
            Rectangle rectangleCursor = cursor.getBoundingRectangle();
            Rectangle rectangleButton = button.getBoundingRectangle();
            boolean overlapsPlay = rectangleCursor.overlaps(rectangleButton);
            if(overlapsPlay == true){
                return 0;
            }
            else{return -1;}
        }
        
        
       
        }
