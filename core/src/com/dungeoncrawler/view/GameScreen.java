package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.dungeoncrawler.model.Dungeon;
import com.dungeoncrawler.model.Entity;
import com.dungeoncrawler.model.entities.*;
import com.badlogic.gdx.utils.Timer;

public class GameScreen {
        //PLAYER
        Texture p;
        Sprite player;
        TextureRegion[][] regions;
        
        //ENTITIES
        Texture[] entityTextures;
        Sprite[] entitySprites;
        TextureRegion[][] archerRegions;
        Texture archerTexture;
        TextureRegion[][] swordsmanRegions;
        Texture swordsmanTexture;
        
        Texture[] arrowTextures;
        Sprite[] arrowSprites;
        
        //MAP
        Map m;
        TiledMapRenderer tmr;
        TiledMap tm;
        OrthographicCamera camera;
        
	public GameScreen(Dungeon d) {
            
                //PLAYER
                p = new Texture("player.png");
                regions = TextureRegion.split(p, 48, 48);
                player = new Sprite(regions[0][2]);
                player.setX(200);
                player.setY(200);
                
                //ENTITIES
                entityTextures = new Texture[5];
                entitySprites = new Sprite[5];
                
                arrowTextures = new Texture[10];
                arrowSprites = new Sprite[10];
                    
                //MAP
                float w = Gdx.graphics.getWidth();
                float h = Gdx.graphics.getHeight();
                
                m = new Map();
                camera = new OrthographicCamera(1, h/w);
                
                MapGenerator mg = new MapGenerator(new Texture(Gdx.files.internal("tiles.gif")));
                
                TiledMap[][][] maps = mg.generateMap(d);
                m.setMaps(maps);
                mg.ichWillSpielen(m.getMaps());
                
                tm = new TiledMap();
                tmr = new OrthogonalTiledMapRenderer(tm);

	}

	public void render (SpriteBatch batch, Player p, Entity[] e, Entity[] arrows, int[] tile, int level, int[] posRoom) {
            
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                //setzt player Sprite auf richtige Position
                player.setX(p.getxPos());
                player.setY(p.getyPos());
                
                int xPosRoom = posRoom[0];
                int yPosRoom = posRoom[1];
                
                tm = m.getMaps()[level][xPosRoom][yPosRoom];
                
                if(tm == null){
                    System.out.println("Dein scheiß geht net");
                }
                else{
                    tmr = new OrthogonalTiledMapRenderer(tm);
                }
                
                // dreht SpielerSprite je nach Bewegungsrichtung 
                if(p.getMovementX() == 3){ //RECHTS
                    player.setRegion(regions[0][1]);
                }
                if(p.getMovementX() == -3){ //LINKS
                    player.setRegion(regions[0][3]);
                }
                if(p.getMovementY() == 3){ //OBEN
                    player.setRegion(regions[0][0]);
                }
                if(p.getMovementY() == -3){ //UNTEN
                    player.setRegion(regions[0][2]);
                }
                
                //MAP
                tmr.setView(camera);
                tmr.render();

                camera.zoom = 1500f;
                camera.update();
                batch.setProjectionMatrix(camera.combined);

                
                

            //BATCH
            batch.begin();
                player.draw(batch);
                    //DRAW'T JEDES ENTITY - prueft vorher ob vorhanden
                for(int i = 0; i < e.length; i++){
                    if(entitySprites[i] != null){
                        entitySprites[i].setX(e[i].getxPos());
                        entitySprites[i].setY(e[i].getyPos());
                        switch(e[i].getFacing()){
                            case -1:
                                break;
                            case 0:
                                if(e[i].getId() == 0){
                                    entitySprites[i].setRegion(archerRegions[0][0]);
                                }
                                else if(e[i].getId() == 1){
                                    entitySprites[i].setRegion(swordsmanRegions[0][0]);
                                }
                                break;
                            case 1:
                                if(e[i].getId() == 0){
                                    entitySprites[i].setRegion(archerRegions[0][1]);
                                }
                                else if(e[i].getId() == 1){
                                    entitySprites[i].setRegion(swordsmanRegions[0][1]);
                                }
                                break;
                            case 2:
                                if(e[i].getId() == 0){
                                    entitySprites[i].setRegion(archerRegions[0][2]);
                                }
                                else if(e[i].getId() == 1){
                                    entitySprites[i].setRegion(swordsmanRegions[0][2]);
                                }
                                break;
                            case 3:
                                if(e[i].getId() == 0){
                                    entitySprites[i].setRegion(archerRegions[0][3]);
                                }
                                else if(e[i].getId() == 1){
                                    entitySprites[i].setRegion(swordsmanRegions[0][3]);
                                }
                                break;
                    
                        }
                        entitySprites[i].draw(batch);
                    }
                }
                for(int i = 0; i < arrowSprites.length; i++){
                    if(arrowSprites[i] != null){
                        arrowSprites[i].setX(arrows[i].getxPos());
                        arrowSprites[i].setY(arrows[i].getyPos());
                        arrowSprites[i].draw(batch);
                    }
                }
            batch.end();
	}
        
        
        public void newEntity(int i,Entity e, float x, float y){
                    if(e.getId() == 0){ //nimmt entity ID -> 0 = Archer || 1 = Swordsman || 2 = Arrow
                        entityTextures[i] = new Texture("archer.png");
                        archerRegions = TextureRegion.split(entityTextures[i], 48, 48);
                        entitySprites[i] = new Sprite(archerRegions[0][2]);
                        entitySprites[i].setX(x);
                        entitySprites[i].setY(y);
                    }
                    if(e.getId() == 1){
                        entityTextures[i] = new Texture("swordsman.png");
                        swordsmanRegions = TextureRegion.split(entityTextures[i], 48, 48);
                        entitySprites[i] = new Sprite(swordsmanRegions[0][2]);
                        entitySprites[i].setX(x);
                        entitySprites[i].setY(y);
                    }
                    if(e.getId() == 2){
                        arrowTextures[i] = new Texture("key.png");
                        arrowSprites[i] = new Sprite(arrowTextures[i]);
                        arrowSprites[i].setX(x);
                        arrowSprites[i].setY(y);
                    }
        }
        
        
        //GETTER
        public float getPlayerSpriteX(){
            return player.getX();
        }
        public float getPlayerSpriteY(){
            return player.getY();
        }
        
        public float getEntitySpriteX(int i){
            return entitySprites[i].getX();
        }
        public float getEntitySpriteY(int i){
            return entitySprites[i].getY();
        }
        
        //SETTER
        public void setPlayerSpriteX(float x){
            player.setX(x);
        }
        public void setPlayerSpriteY(float y){
            player.setY(y);
        }
        
        public void setEntitySpriteX(int i,float x){
            entitySprites[i].setX(x);
        }
        public void setEntitySpriteY(int i,float y){
            entitySprites[i].setY(y);
        }
       
}
