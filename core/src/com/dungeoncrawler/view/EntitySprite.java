/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author jonathan
 */
public class EntitySprite {
    
    // 0: pants, 1: body, 2: head, 3: hair, 4: collision
    private Sprite[] sprites;
    private Rectangle collisionSprite;
    private Rectangle fullCollisionSprite;
    private TextureRegion[][][] regions;
    private  int[] frames;
    
    // 0: links, 1: rechts
    private int direction;
    
    public EntitySprite(Texture[] textures){
        sprites = new Sprite[1];
        regions = new TextureRegion[1][][];
        frames = new int[2];
        direction = 0;
        
        for(int i = 0; i < regions.length; i++){
            regions[i] = TextureRegion.split(textures[i], 64, 64);
            sprites[i] = new Sprite(regions[i][0][0]);
        }
        
        collisionSprite = new Rectangle(0, 0, 32, 16);
        fullCollisionSprite = sprites[0].getBoundingRectangle();
    }
    
    public void updateIdle(){
        frames[1] = 0;
        
        if(frames[0] >= 9){
            frames[0] = 0;
        }
        else{
            frames[0]++;
        }
        
        sprites[0].setRegion(regions[0][0][frames[0]]);
        updateFlip();
    }
    
    public void updateWalking(){
        frames[0] = 0;
        
        if(frames[1] >= 9){
            frames[1] = 0;
        }
        else{
            frames[1]++;
        }
        
        sprites[0].setRegion(regions[0][2][frames[1]]);
        updateFlip();
    }
    
    public void updateFlip(){
        
        if(direction == 0 && !sprites[0].isFlipX()){
            sprites[0].flip(true, false);
        }
        else if(direction == 1 && sprites[0].isFlipX()){
            sprites[0].flip(false, false);
        }
    }
    
    public void update(int xPos, int yPos){
        for(int i = 0; i < sprites.length; i++){
            sprites[i].setPosition(xPos - 16, yPos);
        }
        
        updateCollision(xPos, yPos);
        
    }
    
    public void updateCollision(int xPos, int yPos){
        collisionSprite.setPosition(xPos, yPos);
    }
    public void updateCollisionX(int xPos){
        collisionSprite.setX(xPos);
    }
    public void updateCollisionY(int yPos){
        collisionSprite.setY(yPos);
    }

    /**
     * @return the sprites
     */
    public Sprite[] getSprites() {
        return sprites;
    }

    /**
     * @param sprites the sprites to set
     */
    public void setSprites(Sprite[] sprites) {
        this.sprites = sprites;
    }

    /**
     * @return the collisionSprite
     */
    public Rectangle getCollisionSprite() {
        return collisionSprite;
    }

    /**
     * @param collisionSprite the collisionSprite to set
     */
    public void setCollisionSprite(Rectangle collisionSprite) {
        this.collisionSprite = collisionSprite;
    }

    /**
     * @return the regions
     */
    public TextureRegion[][][] getRegions() {
        return regions;
    }

    /**
     * @param regions the regions to set
     */
    public void setRegions(TextureRegion[][][] regions) {
        this.regions = regions;
    }

    /**
     * @return the frames
     */
    public int[] getFrames() {
        return frames;
    }

    /**
     * @param frames the frames to set
     */
    public void setFrames(int[] frames) {
        this.frames = frames;
    }

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return the fullCollisionSprite
     */
    public Rectangle getFullCollisionSprite() {
        return fullCollisionSprite;
    }
}
