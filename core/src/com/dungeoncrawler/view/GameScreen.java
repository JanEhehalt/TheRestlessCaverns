package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.dungeoncrawler.model.Dungeon;
import com.dungeoncrawler.model.Entity;
import com.dungeoncrawler.model.entities.*;

public class GameScreen {
        //CONTROLS
        Texture ctr;
        Sprite controls;
    
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
        Texture healthBar;
        
        Texture[] arrowTextures;
        Sprite[] arrowSprites;
        
        //MAP
        private Map m;
        TiledMapRenderer tmr;
        TiledMap tm;
        OrthographicCamera camera;
        
        // Sound
        public Music music;
        
	public GameScreen(Dungeon d, float volume) {
                //CONTROLS
                ctr = new Texture("controls.png");
                controls = new Sprite(ctr);
                controls.setX(-400f);
                controls.setY(0);
            
            
                //PLAYER
                
                p = new Texture("player2.png");
                regions = TextureRegion.split(p, 32, 32);
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
                
                MapGenerator mg = new MapGenerator(new Texture(Gdx.files.internal("tiles.png")));
                
                TiledMap[][][] maps = mg.generateMap(d);
                m.setMaps(maps);
                mg.ichWillSpielen(m.getMaps());
                
                tm = new TiledMap();
                tmr = new OrthogonalTiledMapRenderer(tm);
                
                music = Gdx.audio.newMusic(Gdx.files.internal("gamemusic.mp3"));
                music.setVolume(volume);
                music.play();

	}

	public void render (SpriteBatch batch, Player p, Entity[] e, Entity[] arrows, int tileX, int tileY, int level, int roomPosX, int roomPosY) {
            
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                //setzt player Sprite auf richtige Position
                player.setX(p.getxPos());
                player.setY(p.getyPos());
                
                tm = getM().getMaps()[level][roomPosX][roomPosY];
                
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

                for(int i = 0; i < e.length; i++){
                    if(e[i] != null){
                        if(e[i].getId() == 0){ //nimmt entity ID -> 0 = Archer || 1 = Swordsman || 2 = Arrow
                            entityTextures[i] = new Texture("archer.png");
                            archerRegions = TextureRegion.split(entityTextures[i], 48, 48);
                            entitySprites[i] = new Sprite(archerRegions[0][2]);
                            entitySprites[i].setX(e[i].getxPos());
                            entitySprites[i].setY(e[i].getyPos());
                        }
                        if(e[i].getId() == 1){
                            entityTextures[i] = new Texture("swordsman.png");
                            swordsmanRegions = TextureRegion.split(entityTextures[i], 48, 48);
                            entitySprites[i] = new Sprite(swordsmanRegions[0][2]);
                            entitySprites[i].setX(e[i].getxPos());
                            entitySprites[i].setY(e[i].getyPos());
                        }
                    }
                }  
                

            //BATCH
            batch.begin();
                player.draw(batch);
                controls.draw(batch);
                    //DRAW'T JEDES ENTITY - prueft vorher ob vorhanden
                for(int i = 0; i < e.length; i++){
                    if(e[i] != null){
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
                        entitySprites[i].setX(e[i].getxPos());
                        entitySprites[i].setY(e[i].getyPos());
                        if(e[i].getHp() < e[i].getMaxhp() && e[i].getHp() > 0){
                            healthBar = new Texture("halfHealthEntity.png");
                            Sprite healthBarSprite = new Sprite(healthBar);
                            healthBarSprite.setPosition(e[i].getxPos(), e[i].getyPos());
                            healthBarSprite.draw(batch);
                        }
                        else if(e[i].getHp() == e[i].getMaxhp()){
                            healthBar = new Texture("fullHealthEntity.png");
                            Sprite healthBarSprite = new Sprite(healthBar);
                            healthBarSprite.setPosition(e[i].getxPos(), e[i].getyPos());
                            healthBarSprite.draw(batch);
                        }
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
        
        
        
        
        public Entity[] playerAttack(Entity e[], Player p){
            if(p.direction() == 0){
                Texture verticalAttack = new Texture("AttackVert.png");
                Sprite verticalAttackSprite = new Sprite(verticalAttack);
                verticalAttackSprite.setX(p.getxPos()-2f);
                verticalAttackSprite.setY(p.getyPos()-2f);
                for(int i = 0; i< e.length ; i++){
                    if(e[i] != null){
                        if(Intersector.overlaps(entitySprites[i].getBoundingRectangle(), verticalAttackSprite.getBoundingRectangle())){
                            if(e[i] != null){
                                if(e[i].getHp() - p.getDmg() <= 0){
                                    e[i] = null;
                                }
                                else{
                                    e[i].setHp(e[i].getHp() - p.getDmg());
                                }
                            }
                        }
                    }
                }
            }
            else if(p.direction() == 1){
                Texture horizontalAttack = new Texture("AttackHori.png");
                Sprite horizontalAttackSprite = new Sprite(horizontalAttack);
                horizontalAttackSprite.setX(p.getxPos()-2f);
                horizontalAttackSprite.setY(p.getyPos()-2f);
                for(int i = 0; i< e.length ; i++){
                    if(entitySprites[i] != null){
                        if(Intersector.overlaps(entitySprites[i].getBoundingRectangle(), horizontalAttackSprite.getBoundingRectangle())){
                            if(e[i] != null){
                                if(e[i].getHp() - p.getDmg() <= 0){
                                    e[i] = null;
                                }
                                else{
                                    e[i].setHp(e[i].getHp() - p.getDmg());
                                }
                            }
                        }
                    }
                }
            }
            else if(p.direction() == 2){
                Texture verticalAttack = new Texture("AttackVert.png");
                Sprite verticalAttackSprite = new Sprite(verticalAttack);
                verticalAttackSprite.setX(p.getxPos()-2f);
                verticalAttackSprite.setY(p.getyPos() - 24f-2f);
                for(int i = 0; i<e.length ; i++){
                    if(entitySprites[i] != null){
                        if(Intersector.overlaps(entitySprites[i].getBoundingRectangle(), verticalAttackSprite.getBoundingRectangle())){
                            if(e[i] != null){
                                if(e[i].getHp() - p.getDmg() <= 0){
                                    e[i] = null;
                                }
                                else{
                                    e[i].setHp(e[i].getHp() - p.getDmg());
                                }
                            }
                        }
                    }
                }
            }
            else if(p.direction() == 3){
                Texture horizontalAttack = new Texture("AttackHori.png");
                Sprite horizontalAttackSprite = new Sprite(horizontalAttack);
                horizontalAttackSprite.setX(p.getxPos() - 24f-2f);
                horizontalAttackSprite.setY(p.getyPos() -2f);
                for(int i = 0; i < e.length ; i++){
                    if(entitySprites[i] != null){
                        if(Intersector.overlaps(entitySprites[i].getBoundingRectangle(), horizontalAttackSprite.getBoundingRectangle())){
                            if(e[i] != null){
                                if(e[i].getHp() - p.getDmg() <= 0){
                                    e[i] = null;
                                }
                                else{
                                    e[i].setHp(e[i].getHp() - p.getDmg());
                                }
                            }
                        }
                    }
                }
            }
            return e;
        }
        
        public void cleanUp(){
            music.dispose();
        }
        
        
        //GETTER
        public float getPlayerSpriteX(){
            return player.getX();
        }
        public float getPlayerSpriteY(){
            return player.getY();
        }
        public Sprite getPlayer(){
            return player;
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

        /**
         * @return the m
         */
        public Map getM() {
            return m;
        }
       
}
