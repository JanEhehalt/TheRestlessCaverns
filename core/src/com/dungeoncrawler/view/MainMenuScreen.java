package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;

public class MainMenuScreen{
        
        
    
        //MENU-SCREEN
	Texture startButtonTexture;
        Texture quitButtonTexture;
        Texture backgroundTexture;
        Texture settingsButtonTexture;
        
        Sprite startButtonSprite;
        Sprite quitButtonSprite;
        Sprite backgroundSprite;
        Sprite settingsButtonSprite;
        
        boolean hidden;
        
       
        //CAMERA
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        OrthographicCamera camera;
        
        
        // Sound
        public Music music;
        
	public MainMenuScreen(float volume) {
            
                //MENU-SCREEN
                w = Gdx.graphics.getWidth();
                h = Gdx.graphics.getHeight();
                float wc = w/2;
                
                hidden = false;
                
                backgroundTexture = new Texture("sprites/MAINSCREEN.png");
                startButtonTexture = new Texture("sprites/startButton.png");
                quitButtonTexture = new Texture("sprites/quitButton.png");
                settingsButtonTexture = new Texture("sprites/settingsButton.png");
                
                backgroundSprite = new Sprite(backgroundTexture);
                startButtonSprite = new Sprite(startButtonTexture);
                quitButtonSprite = new Sprite(quitButtonTexture);
                settingsButtonSprite = new Sprite(settingsButtonTexture);
                
                backgroundSprite.setX(0);
                backgroundSprite.setY(0);
                startButtonSprite.setX(backgroundSprite.getX() + 100);
                startButtonSprite.setY(backgroundSprite.getY() + 350);
                quitButtonSprite.setX(backgroundSprite.getX() + 50);
                quitButtonSprite.setY(backgroundSprite.getY() + 50);
                
                settingsButtonSprite.setX(backgroundSprite.getX() + 100);
                settingsButtonSprite.setY(backgroundSprite.getY() + 250);
                
                
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
                if(hidden == false){
                    backgroundSprite.draw(batch);
                    startButtonSprite.draw(batch);
                    quitButtonSprite.draw(batch);
                    settingsButtonSprite.draw(batch);
                }
                batch.end();
	}

        
        public int click(int x, int y){     // prueft ob cursor mit button (START) ueberlappt
            if(hidden == false){
                
                Rectangle r = new Rectangle();
                r.set(x, h-y, 1, 1);
                if(Intersector.overlaps(r, startButtonSprite.getBoundingRectangle())){
                    return 0;   //go ingame
                }
                if(Intersector.overlaps(r, quitButtonSprite.getBoundingRectangle())){
                    return 1;   //Exit Game
                }
                if(Intersector.overlaps(r, settingsButtonSprite.getBoundingRectangle())){
                    return 2;   //Settings
                }
            }
            return -1;
            
        }
        
        public void cleanUp(){
            music.dispose();
        }
        
        public void hide(){
            hidden = true;
        }
        
        public void appear(){
            hidden = false;
        }
        
        public boolean getHidden(){
            return hidden;
        }
        
        
       
        }
