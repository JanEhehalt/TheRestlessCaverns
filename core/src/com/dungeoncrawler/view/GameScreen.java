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
import com.badlogic.gdx.utils.Timer;
import com.dungeoncrawler.model.Dungeon;
import com.dungeoncrawler.model.Entity;
import com.dungeoncrawler.model.Item;
import com.dungeoncrawler.model.entities.*;
import java.util.ArrayList;

public class GameScreen {
    
        //PLAYER
        Texture p;
        PlayerSprite player;
        
        
        //ENTITIES
        Texture[] entityTextures;
        public Sprite[] entitySprites;
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
        public ArrayList<AnimatedObject> objects;
        public ArrayList<AnimatedObject> mapItems;
        
        Timer animations;
        Timer animatePlayer;
        
        Timer roomChangeTimer;
        int roomChangeAnimationState;
        boolean roomLoading;
        Texture roomChangeTexture;
        Sprite roomChangeSprite;
        TextureRegion[][] roomChangeTextureRegion;
        int roomChangeRow;
        
        boolean playerMoving;
        
        HudContainer hc;
        
        // Sound
        public Music music;
        
        //Inventory TEST
        
        float animationSpeed = 0.1f;
        
	public GameScreen(Dungeon d, float volume) {
                //CONTROLS
                /*
                ctr = new Texture("controls.png");
                controls = new Sprite(ctr);
                controls.setX(-400f);
                controls.setY(0);
                */
            
                //PLAYER
                Texture[] playerTexture = new Texture[4];
                playerTexture[0] = new Texture(Gdx.files.internal("sprites/player.png"));
                playerTexture[1] = new Texture(Gdx.files.internal("sprites/player.png"));
                playerTexture[2] = new Texture(Gdx.files.internal("sprites/player.png"));
                playerTexture[3] = new Texture(Gdx.files.internal("sprites/player.png"));
                
                player = new PlayerSprite(playerTexture);
                
                player.update(200, 200);
                
                //ENTITIES
                entityTextures = new Texture[5];
                entitySprites = new Sprite[15];
                
                arrowTextures = new Texture[10];
                arrowSprites = new Sprite[10];
                    
                //MAP
                float w = Gdx.graphics.getWidth();
                float h = Gdx.graphics.getHeight();
                
                m = new Map();
                camera = new OrthographicCamera(1, h/w);
                camera.translate(175f, 215f);
                
                
                Texture[] tempTextures = new Texture[d.getLevel().length];
                for(int i = 0; i < tempTextures.length; i++){
                    int j = i+1;
                    tempTextures[i] = new Texture(Gdx.files.internal("tilesets/tileset_floor_" + j + ".png"));
                }
                MapGenerator mg = new MapGenerator(tempTextures);
                
                m = mg.generateMap(d);
                mg.ichWillSpielen(m.getMaps());
                
                tm = new TiledMap();
                tmr = new OrthogonalTiledMapRenderer(tm);
                
                music = Gdx.audio.newMusic(Gdx.files.internal("music/gamemusic.mp3"));
                music.setVolume(volume);
                music.play();
                
                animations = new Timer();
                animations.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        if(objects != null){
                            for(AnimatedObject object : objects){
                                object.updateTexture();        
                            }
                        }
                    }
                },0, 0.1f);
                
                animatePlayer = new Timer();
                animatePlayer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        if(!playerMoving){
                            player.updateIdle();
                        }
                        else{
                            player.updateWalking();
                        }
                    }
                }, 0, animationSpeed);
                
                //Inventory TEST
                
                hc = new HudContainer();
                
                
                
                roomChangeTimer = new Timer();
                roomLoading = false;
                roomChangeTexture = new Texture("sprites/RoomChange.png");
                roomChangeSprite = new Sprite(roomChangeTexture);
                roomChangeTextureRegion = roomChangeSprite.split(roomChangeTexture, 528, 432);
                roomChangeRow = 0;
                roomChangeSprite.setPosition(0f, 0f);
                
                roomChangeTimer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                       if(roomChangeRow >= 9){
                           roomLoading = false;
                           roomChangeRow = 0;
                           roomChangeTimer.stop();
                       }
                       else{
                           roomChangeRow++;
                       }
                    }
                },0, 0.01f);
                
	}

	public void render (SpriteBatch batch, Player p, Entity[] e, Entity[] arrows, int tileX, int tileY, int level, int roomPosX, int roomPosY) {
            
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                playerMoving = (p.getMovementX() != 0 || p.getMovementY() != 0);
                
                //setzt player Sprite auf richtige Position
                player.update((int) p.getxPos(), (int) p.getyPos());
                if(p.getMovementX() > 1){
                    player.setDirection(1);
                    player.updateFlip();
                }
                else if(p.getMovementX() < -1){
                    player.setDirection(0);
                    player.updateFlip();
                }
                
                tm = getM().getMaps()[level][roomPosX][roomPosY].getMap();
                objects = getM().getMaps()[level][roomPosX][roomPosY].getObjects();
                mapItems = getM().getMaps()[level][roomPosX][roomPosY].getMapItems();
                
                if(tm == null){
                    System.out.println("Dein scheiß geht net");
                }
                else{
                    tmr = new OrthogonalTiledMapRenderer(tm);
                }
                
                
                // dreht SpielerSprite je nach Bewegungsrichtung 
                /*
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
                */
                
                //MAP
                tmr.setView(camera);
                tmr.render();

                camera.zoom = 700f; // Standart 700f
                
                camera.update();
                batch.setProjectionMatrix(camera.combined);

                
                
                updateEntitySprite(e);
                

            //BATCH
            batch.begin();
                
                //DRAWING LOADING SCREEN IF LOADING
                
                
                //DRAWING INVENTORY
                
                
                for(AnimatedObject object : objects){
                    object.getSprite().draw(batch);
                }
                
                for(AnimatedObject mapItem : mapItems){
                    mapItem.getSprite().draw(batch);
                }
                
                // Player wird gedrawt
                for(Sprite sprite : player.getSprites()){
                    sprite.draw(batch);
                }
                
                //controls.draw(batch);
                    //DRAW'T JEDES ENTITY - prueft vorher ob vorhanden
                for(int i = 0; i < e.length; i++){
                    if(e[i] != null){
                        switch(e[i].getDirection()){
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
                            healthBar = new Texture("sprites/halfHealthEntity.png");
                            Sprite healthBarSprite = new Sprite(healthBar);
                            healthBarSprite.setPosition(e[i].getxPos(), e[i].getyPos());
                            healthBarSprite.draw(batch);
                        }
                        else if(e[i].getHp() == e[i].getMaxhp()){
                            healthBar = new Texture("sprites/fullHealthEntity.png");
                            Sprite healthBarSprite = new Sprite(healthBar);
                            healthBarSprite.setPosition(e[i].getxPos(), e[i].getyPos());
                            healthBarSprite.draw(batch);
                        }
                    }
                }
                
                /*
                for(int i = 0; i < arrowSprites.length; i++){
                    if(arrowSprites[i] != null){
                        arrowSprites[i].setX(arrows[i].getxPos());
                        arrowSprites[i].setY(arrows[i].getyPos());
                        arrowSprites[i].draw(batch);
                    }
                }
                */
                roomChangeSprite.setRegion(roomChangeTextureRegion[0][roomChangeRow]);
                if(roomLoading == true){
                    roomChangeSprite.draw(batch);
                }
                
            batch.end();
	}
        
        public void updateEntitySprite(Entity[] e){
            for(int i = 0; i < e.length; i++){
                    if(e[i] != null){
                        if(e[i].getId() == 0){ //nimmt entity ID -> 0 = Archer || 1 = Swordsman || 2 = Arrow
                            entityTextures[i] = new Texture("sprites/archer.png");
                            archerRegions = TextureRegion.split(entityTextures[i], 48, 48);
                            entitySprites[i] = new Sprite(archerRegions[0][2]);
                            entitySprites[i].setX(e[i].getxPos());
                            entitySprites[i].setY(e[i].getyPos());
                        }
                        if(e[i].getId() == 1){
                            entityTextures[i] = new Texture("sprites/swordsman.png");
                            swordsmanRegions = TextureRegion.split(entityTextures[i], 48, 48);
                            entitySprites[i] = new Sprite(swordsmanRegions[0][2]);
                            entitySprites[i].setX(e[i].getxPos());
                            entitySprites[i].setY(e[i].getyPos());
                        }
                        if(e[i].getId() == 2){
                            Texture tx = new Texture("sprites/sword.png");
                            //swordsmanRegions = TextureRegion.split(entityTextures[i], 48, 48);
                            entitySprites[i] = new Sprite(tx);
                            entitySprites[i].setX(e[i].getxPos());
                            entitySprites[i].setY(e[i].getyPos());
                        }
                    }
                } 
        }
        
        
        public Entity[] playerAttack(Entity e[], Player p, SpriteBatch batch){
            if(p.getDirection() == 0){
                Texture attackTexture = new Texture("sprites/AttackHori.png");
                Sprite attackSprite = new Sprite(attackTexture);
                attackSprite.setX(p.getxPos() - 8f);
                attackSprite.setY(p.getyPos() + 32f);
                
                for(int i = 0; i< e.length ; i++){
                    if(e[i] != null){
                        if(Intersector.overlaps(entitySprites[i].getBoundingRectangle(), attackSprite.getBoundingRectangle())){
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
            else if(p.getDirection()== 1){
                Texture attackTexture = new Texture("sprites/AttackVert.png");
                Sprite attackSprite = new Sprite(attackTexture);
                attackSprite.setX(p.getxPos()+ 32f);
                attackSprite.setY(p.getyPos()- 2f);
                for(int i = 0; i< e.length ; i++){
                    if(entitySprites[i] != null){
                        if(Intersector.overlaps(entitySprites[i].getBoundingRectangle(), attackSprite.getBoundingRectangle())){
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
            else if(p.getDirection()== 2){
                Texture attackTexture = new Texture("sprites/AttackHori.png");
                Sprite attackSprite = new Sprite(attackTexture);
                attackSprite.setX(p.getxPos() - 8f);
                attackSprite.setY(p.getyPos());
                for(int i = 0; i<e.length ; i++){
                    if(entitySprites[i] != null){
                        if(Intersector.overlaps(entitySprites[i].getBoundingRectangle(), attackSprite.getBoundingRectangle())){
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
            else if(p.getDirection()== 3){
                Texture attackTexture = new Texture("sprites/AttackVert.png");
                Sprite attackSprite = new Sprite(attackTexture);
                attackSprite.setX(p.getxPos() - 32f);
                attackSprite.setY(p.getyPos() - 8f);
                for(int i = 0; i < e.length ; i++){
                    if(entitySprites[i] != null){
                        if(Intersector.overlaps(entitySprites[i].getBoundingRectangle(), attackSprite.getBoundingRectangle())){
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
        
        /*
        public void moveItem(int i){
            if(i == 0){
                if(InventoryItemSprites[i] != null){
                    for(int n = 2; n < InventoryItemSprites.length ; n++){
                        if(InventoryItemSprites[n] == null){
                            InventoryItemTextures[n] = InventoryItemTextures[i];
                            InventoryItemSprites[n] = InventoryItemSprites[i];
                        }
                    }
                }
            }
        }
        
        */
        
        
        /*
        public ItemContainer playerPickUp(ItemContainer[] items, Player p){
            for(int i = 0; i < items.length; i++){
                if(items[i] != null){
                    if(Intersector.overlaps(player.getBoundingRectangle(), itemSprite[i].getBoundingRectangle())){
                        player.pickUp(items[i].getItem());
                        items[i] = null;
                    }
                }
                
            }
            return items;
        }
       */
        
        public void cleanUp(){
            music.dispose();
        }
        
        public void startLoadingScreen(){
            roomLoading = true;
            roomChangeTimer.start();
        }
        
        //GETTER
        public PlayerSprite getPlayer(){
            return player;
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
        public boolean getIsLoading(){
            return roomLoading;
        }

        /**
         * @return the m
         */
        public Map getM() {
            return m;
        }
       
}
