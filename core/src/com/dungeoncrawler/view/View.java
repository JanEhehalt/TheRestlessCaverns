package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.dungeoncrawler.model.Dungeon;
import com.dungeoncrawler.model.DungeonGenerator;
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
        
        //MAP
        Map m;
        TiledMapRenderer tmr;
        TiledMap test;
        OrthographicCamera camera;
        Dungeon d;
        
	public View() {
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
                
                
                //MAP
                m = new Map();
                camera = new OrthographicCamera(1, h/w);
                d = new DungeonGenerator().generateDungeon(10, 10, 48, new Player());
                MapGenerator mg = new MapGenerator(new Texture(Gdx.files.internal("tiles.gif")));
                TiledMap[][][] maps = mg.generateMap(7, d);
                m.setMaps(maps);
                
                for(int i=0;i<m.getMaps()[0].length;i++){
                    for(int j=0;j<m.getMaps()[0][0].length;j++){
                        if(m.getMaps()[0][i][j] != null){
                            test = m.getMaps()[0][i][j];
                        }
                    }
                }
                tmr = new OrthogonalTiledMapRenderer(test);

	}

        
	public void render (SpriteBatch batch, Player p, Archer a, int xPosRoom, int yPosRoom) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                player.setX(p.getxPos());
                player.setY(p.getyPos());
                
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
                
                tmr.setView(camera);
                tmr.render();
                camera.zoom = 1500f;
                camera.update();
                batch.setProjectionMatrix(camera.combined);

                
                batch.begin();
                player.draw(batch);
                if(entitySprites[0] != null){
                    entitySprites[0].draw(batch);
                }
                if(entitySprites[1] != null){
                    entitySprites[1].draw(batch);
                }
                if(entitySprites[2] != null){
                    entitySprites[2].draw(batch);
                }
                if(entitySprites[3] != null){
                    entitySprites[3].draw(batch);
                }
                if(entitySprites[4] != null){
                    entitySprites[4].draw(batch);
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
}
