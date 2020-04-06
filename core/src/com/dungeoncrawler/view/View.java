package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dungeoncrawler.model.entities.*;

public class View {
	Texture b;
        Texture t;
        Texture p;
        Texture a;
        Sprite button;
        Sprite title;
        Sprite player;
        Sprite archer;
        int PlayerFrame = 0;
        int PlayerZeile = 0;
        TextureRegion[][] regions;
        Timer tunten;
        Timer toben;
        Timer tlinks;
        Timer trechts;
        Texture arrow;
        Sprite Arrow;
        Timer tArrowLeft = new Timer();
        Timer tArrowRight = new Timer();
        Timer tArrowUp = new Timer();
        Timer tArrowDown = new Timer();
        int ArrowTravel = 0;
        
	public View() {
                b = new Texture("Button.png");
                t = new Texture("Title.png");
                p = new Texture("animplay.png");
                a = new Texture("Archer-64.png");
                button = new Sprite(b);
                title = new Sprite(t);
                archer = new Sprite(a);
                arrow = new Texture("Archer.png");
                Arrow = new Sprite(arrow);
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
                        PlayerZeile = 2;
                        
                        if(PlayerFrame == 9){
                            PlayerFrame = 0;
                        }
                        else{
                            PlayerFrame++;
                        }
                        
                        player.setRegion(regions[PlayerZeile][PlayerFrame]);
                        
                     }
                },0,1/5f);
                tunten.stop();
                
                toben.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        PlayerZeile = 2;
                        
                        if(PlayerFrame == 9){
                            PlayerFrame = 0;
                        }
                        else{
                            PlayerFrame++;
                        }
                 
                        player.setRegion(regions[PlayerZeile][PlayerFrame]);
                        
                     }
                }, 0,1/5f);
                toben.stop();
                
                tlinks.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        PlayerZeile = 2;
                        if(player.isFlipX() == true){
                        
                        }
                        else{
                            player.flip(true, false);
                        }
                        if(PlayerFrame == 9){
                            PlayerFrame = 0;
                        }
                        else{
                            PlayerFrame++;
                        }
                        
                        player.setRegion(regions[PlayerZeile][PlayerFrame]);
                        
                     }
                }, 0,1/5f);
                tlinks.stop();
                
                trechts.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        PlayerZeile = 2;
                        if(player.isFlipX() == true){
                            player.flip(false, false);
                        }
                        if(PlayerFrame == 9){
                            PlayerFrame = 0;
                        }
                        else{
                            PlayerFrame++;
                        }
                        
                 
                        player.setRegion(regions[PlayerZeile][PlayerFrame]);
                        
                     }
                }, 0,1/5f);
                trechts.stop();
                tArrowLeft.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        Arrow.setX(Arrow.getX() - 3);
                        ArrowTravel++;
                        if(ArrowTravel >= 300){
                            ArrowTravel = 0;
                            tArrowLeft.stop();
                        }
                    }
                }, 0,1/50f);
                tArrowLeft.stop();
                tArrowRight.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        Arrow.setX(Arrow.getX() + 3);
                        ArrowTravel++;
                        if(ArrowTravel >= 300){
                            ArrowTravel = 0;
                            tArrowLeft.stop();
                        }
                    }
                }, 0,1/40f);
                tArrowRight.stop();
                tArrowUp.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        Arrow.setY(Arrow.getY() + 3);
                        ArrowTravel++;
                        if(ArrowTravel >= 300){
                            ArrowTravel = 0;
                            tArrowLeft.stop();
                        }
                    }
                }, 0,1/50f);
                tArrowUp.stop();
                tArrowDown.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        Arrow.setY(Arrow.getY() - 3);
                        ArrowTravel++;
                        if(ArrowTravel >= 300){
                            ArrowTravel = 0;
                            tArrowLeft.stop();
                        }
                    }
                }, 0,1/50f);
                tArrowDown.stop();
                
	}

        
	public void render (SpriteBatch batch, Player p, Archer a) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                player.setX(player.getX()+ (float) p.getMovementX());
                player.setY(player.getY()+ (float) p.getMovementY());
                archer.setX((float)a.getxPos());
                archer.setY((float)a.getyPos());
                
                if(p.getMovementX() == 3){
                    trechts.start();
                    if(player.isFlipX() == true){
                            player.flip(false, false);
                        }
                }
                if(p.getMovementX() == -3){
                    tlinks.start();
                    if(player.isFlipX() == true){
                        
                    }
                    else{
                        player.flip(true, false);
                    }
                }
                if(p.getMovementY() == 3){
                    toben.start();
                }
                if(p.getMovementY() == -3){
                    tunten.start();
                }
                
                batch.begin();
                title.draw(batch);
                button.draw(batch);
                player.draw(batch);
                archer.draw(batch);
                if(ArrowTravel > 0){
                    Arrow.draw(batch);
                }
                batch.end();
	}
        
        public void arrow(Archer a, int i){
            switch(i){
                case 0:
                        Arrow.setX((float) a.getxPos());
                        Arrow.setY((float) a.getyPos());
                        tArrowDown.start();
                    break;
                case 1:
                        Arrow.setX((float) a.getxPos());
                        Arrow.setY((float) a.getyPos());
                        tArrowUp.start();
                    break;
                case 2:
                    for(int n = 0; n < 50; n++){
                        Arrow.setX((float) a.getxPos());
                        Arrow.setY((float) a.getyPos());
                        tArrowLeft.start();
                    }
                    break;
                case 3:
                        Arrow.setX((float) a.getxPos());
                        Arrow.setY((float) a.getyPos());
                        tArrowRight.start();
                    break;
                } 
            
                
        }
        
        public void tuntenstop(){
            tunten.stop();
            PlayerFrame = 0;
            player.setRegion(regions[PlayerZeile][PlayerFrame]);}
        public void tobenstop(){    
            toben.stop();
            PlayerFrame = 0;
            player.setRegion(regions[PlayerZeile][PlayerFrame]);}
        public void tlinksstop(){    
            tlinks.stop();
            PlayerFrame = 0;
            player.setRegion(regions[PlayerZeile][PlayerFrame]);
            player.flip(true, false);}
        public void trechtsstop(){    
            trechts.stop();
            PlayerFrame = 0;
            player.setRegion(regions[PlayerZeile][PlayerFrame]);}
            
        }
