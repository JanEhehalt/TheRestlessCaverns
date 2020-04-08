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

public class View {
        //PLAYER
        Texture p;
        Sprite player;
        TextureRegion[][] regions;
        
        
        
        //ENTITIES
        Texture[] entityTextures;
        Sprite[] entitySprites;
        TextureRegion[][] archerRegions;
        Texture archerTexture;
        
        //MAP
        Map m;
        TiledMapRenderer tmr;
        TiledMap test;
        OrthographicCamera camera;
        
	public View(Dungeon d) {
                float w = Gdx.graphics.getWidth();
                float h = Gdx.graphics.getHeight();
                float wc = w/2;
            
                //PLAYER
                p = new Texture("Player.png");
                regions = TextureRegion.split(p, 64, 64);
                player = new Sprite(regions[0][2]);
                player.setX(200);
                player.setY(200);
                
                //ENTITIES
                entityTextures = new Texture[5];
                entitySprites = new Sprite[5];
                archerTexture = new Texture("archer.png");
                archerRegions = TextureRegion.split(archerTexture, 64, 64);
                
                
                
                //MAP
                m = new Map();
                camera = new OrthographicCamera(1, h/w);
                
                MapGenerator mg = new MapGenerator(new Texture(Gdx.files.internal("tiles.gif")));
                
                TiledMap[][][] maps = mg.generateMap(d);
                m.setMaps(maps);
                mg.ichWillSpielen(m.getMaps());
                
                test = new TiledMap();
                tmr = new OrthogonalTiledMapRenderer(test);

	}

	public void render (SpriteBatch batch, Player p, Entity[] e, int[] tile, int level, int[] posRoom) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                player.setX(p.getxPos());
                player.setY(p.getyPos());
                
                int xPosRoom = posRoom[0];
                int yPosRoom = posRoom[1];
                
                test = m.getMaps()[level][xPosRoom][yPosRoom];
                
                if(test == null){
                    System.out.println("Dein scheiß geht net");
                }
                else{
                    tmr = new OrthogonalTiledMapRenderer(test);
                }
                
                if(p.getMovementX() == 3){
                    player.setRegion(regions[0][1]);
                }
                if(p.getMovementX() == -3){
                    player.setRegion(regions[0][3]);
                }
                if(p.getMovementY() == 3){
                    player.setRegion(regions[0][0]);
                }
                if(p.getMovementY() == -3){
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
                for(int i = 0; i < e.length; i++){
                
                if(entitySprites[i] != null){
                    entitySprites[i].setX(e[i].getxPos());
                    entitySprites[i].setY(e[i].getyPos());
                    switch(e[i].direction()){
                        case -1:
                            break;
                        case 0:
                            entitySprites[i].setRegion(archerRegions[0][0]);
                            break;
                        case 1:
                            entitySprites[i].setRegion(archerRegions[0][1]);
                            break;
                        case 2:
                            entitySprites[i].setRegion(archerRegions[0][2]);
                            break;
                        case 3:
                            entitySprites[i].setRegion(archerRegions[0][3]);
                            break;
                    
                        }
                        entitySprites[i].draw(batch);
                    }
                }
                batch.end();
	}
        
        
        public void newEntity(int i,Entity e, int x, int y){
                    if(e.getId() == 0){
                        entityTextures[i] = new Texture("archer.png");
                        entitySprites[i] = new Sprite(archerRegions[0][2]);
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
        
        public float getPlayerSpriteX(){
            return player.getX();
        }
        public float getPlayerSpriteY(){
            return player.getY();
        }
        public void setPlayerSpriteX(float x){
            player.setX(x);
        }
        public void setPlayerSpriteY(float y){
            player.setY(y);
        }
        
        public float getEntitySpriteX(int i){
            return entitySprites[i].getX();
        }
        public float getEntitySpriteY(int i){
            return entitySprites[i].getY();
        }
        public void setEntitySpriteX(int i,float x){
            entitySprites[i].setX(x);
        }
        public void setEntitySpriteY(int i,float y){
            entitySprites[i].setY(y);
        }
       
}
