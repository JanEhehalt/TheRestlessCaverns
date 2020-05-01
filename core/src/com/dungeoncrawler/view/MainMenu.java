package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;

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
        public Music music;
        
	public MainMenu(float volume) {
                //MENU-SCREEN
                w = Gdx.graphics.getWidth();
                h = Gdx.graphics.getHeight();
                float wc = w/2;
                
                
                
                startButtonTexture = new Texture("sprites/startButton.png");
                quitButtonTexture = new Texture("sprites/quitButton.png");
                backgroundTexture = new Texture("sprites/MAINSCREEN.png");
                
                startButtonSprite = new Sprite(startButtonTexture);
                quitButtonSprite = new Sprite(quitButtonTexture);
                backgroundSprite = new Sprite(backgroundTexture);
                
                startButtonSprite.setX(100);
                startButtonSprite.setY(350);
                quitButtonSprite.setX(50);
                quitButtonSprite.setY(50);
                backgroundSprite.setX(0);
                backgroundSprite.setY(0);
                
                
                //camera = new OrthographicCamera(1, h/w);
                //camera.translate(backgroundSprite.getWidth()/2, backgroundSprite.getHeight()/2);
                //camera.zoom = 1150f;
                //camera.update();
                
                Pixmap pm = new Pixmap(Gdx.files.internal("sprites/cursor.png"));
                Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
                pm.dispose();
                
                //ENTITIES
                
                //PLAYER
                
                // Sound
                music = Gdx.audio.newMusic(Gdx.files.internal("music/mainmenu.mp3"));
                music.setVolume(volume);
                music.play();
                
                System.out.println(startButtonSprite.getWidth());
                System.out.println(startButtonSprite.getHeight());
	}

        
	public void render (SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                batch.begin();
                //batch.setProjectionMatrix(camera.combined);
                backgroundSprite.draw(batch);
                startButtonSprite.draw(batch);
                quitButtonSprite.draw(batch);
                batch.end();
	}

        
        public int click(int x, int y){     // prueft ob cursor mit button (START) ueberlappt
            
            Rectangle r = new Rectangle();
            r.set(x, h-y, 1, 1);
            if(Intersector.overlaps(r, startButtonSprite.getBoundingRectangle())){
                return 0;
            }
            if(Intersector.overlaps(r, quitButtonSprite.getBoundingRectangle())){
                return 1;
            }
            return -1;
            
        }
        
        public void cleanUp(){
            music.dispose();
        }
        
        
        
       
        }
