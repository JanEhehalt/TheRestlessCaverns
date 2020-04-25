package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class MainMenu{
        //MENU-SCREEN
	Texture startButtonTexture;
        Texture quitButtonTexture;
        Texture backgroundTexture;
        Sprite startButtonSprite;
        Sprite quitButtonSprite;
        Sprite backgroundSprite;
        
       
        //CAMERA
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        OrthographicCamera camera;
        
        // Sound
        Music music;
        
	public MainMenu(float volume) {
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
                
                startButtonSprite.setBounds(100f, 350f, startButtonTexture.getWidth(), startButtonTexture.getHeight());
                quitButtonSprite.setBounds(50f, 50f, quitButtonTexture.getWidth(), quitButtonTexture.getHeight());
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
                
                //PLAYER
                
                // Sound
                music = Gdx.audio.newMusic(Gdx.files.internal("mainmenu.mp3"));
                music.setVolume(volume);
                music.play();
	}

        
	public void render (SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                batch.begin();
                batch.setProjectionMatrix(camera.combined);
                backgroundSprite.draw(batch);
                startButtonSprite.draw(batch);
                quitButtonSprite.draw(batch);
                batch.end();
	}

        
        public int click(int x, int y){     // prueft ob cursor mit button (START) ueberlappt
            if(x >= (int) startButtonSprite.getX() && x <= (int) startButtonSprite.getX()+ (int) startButtonSprite.getWidth() && y>= (int) startButtonSprite.getY() && y <= (int) startButtonSprite.getY() + (int) startButtonSprite.getHeight()){
                return 0;
            }
            if(x >= (int) quitButtonSprite.getX() && x <= (int) quitButtonSprite.getX()+ (int) quitButtonSprite.getWidth() && y>= (int) quitButtonSprite.getY() && y <= (int) quitButtonSprite.getY() + (int) quitButtonSprite.getHeight()){
                return 1;
            }
            
            return -1;
        }
        
        public void cleanUp(){
            music.dispose();
        }
        
        
        
       
        }
