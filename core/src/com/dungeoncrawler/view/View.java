package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class View {
	Texture b;
        Texture t;
        Texture p;
        Sprite button;
        Sprite title;
        Sprite player;
        int frame = 0;
        int zeile = 0;
        TextureRegion[][] regions;
        Timer tunten;
        Timer toben;
        Timer tlinks;
        Timer trechts;
        
	public View() {
                b = new Texture("Button.png");
                t = new Texture("Title.png");
                p = new Texture("animplay.png");
                button = new Sprite(b);
                title = new Sprite(t);
                
                float w = Gdx.graphics.getWidth();
                float h = Gdx.graphics.getHeight();
                float wc = w/2;
                title.setX(wc - (title.getWidth()/2));
                title.setY(h - 200);
                button.setX(wc - (button.getWidth()/2));
                button.setY(400);
                regions = TextureRegion.split(p, 32, 32);
                player = new Sprite(regions[0][2]);
                player.setX(200);
                player.setY(200);
                tunten = new Timer();
                toben = new Timer();
                tlinks = new Timer();
                trechts = new Timer();
                
        tunten.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        zeile = 2;
                        
                        if(frame == 9){
                            frame = 0;
                        }
                        else{
                            frame++;
                        }
                        
                        player.setRegion(regions[zeile][frame]);
                        
                     }
                },0,1/5f);
                tunten.stop();
                
                toben.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        zeile = 2;
                        
                        if(frame == 9){
                            frame = 0;
                        }
                        else{
                            frame++;
                        }
                 
                        player.setRegion(regions[zeile][frame]);
                        
                     }
                }, 0,1/5f);
                toben.stop();
                
                tlinks.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        zeile = 2;
                        if(player.isFlipY() == true){
                        
                        }
                        else{
                            player.flip(false, true);
                        }
                        if(frame == 9){
                            frame = 0;
                        }
                        else{
                            frame++;
                        }
                        
                        player.setRegion(regions[zeile][frame]);
                        
                     }
                }, 0,1/5f);
                tlinks.stop();
                
                trechts.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        zeile = 2;
                        if(player.isFlipY() == true){
                            player.flip(false, false);
                        }
                        if(frame == 9){
                            frame = 0;
                        }
                        else{
                            frame++;
                        }
                        
                 
                        player.setRegion(regions[zeile][frame]);
                        
                     }
                }, 0,1/5f);
                trechts.stop();
                
	}

        
	public void render (SpriteBatch batch, float x, float y) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                player.setX(player.getX()+x);
                player.setY(player.getY()+y);
                if(x == 3f){
                    trechts.start();
                }
                if(x == -3f){
                   tlinks.start();
                }
                if(y == 3f){
                    toben.start();
                }
                if(y == -3f){
                    tunten.start();
                }
                
                batch.begin();
                title.draw(batch);
                button.draw(batch);
                player.draw(batch);
                batch.end();
	}
        
        
        
        public void tuntenstop(){
            tunten.stop();
            frame = 0;
            player.setRegion(regions[zeile][frame]);}
        public void tobenstop(){    
            toben.stop();
            frame = 0;
            player.setRegion(regions[zeile][frame]);}
        public void tlinksstop(){    
            tlinks.stop();
            frame = 0;
            player.setRegion(regions[zeile][frame]);}
        public void trechtsstop(){    
            trechts.stop();
            frame = 0;
            player.setRegion(regions[zeile][frame]);}
            
        }
