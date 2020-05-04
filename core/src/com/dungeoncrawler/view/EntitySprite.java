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
import com.dungeoncrawler.model.Entity;

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
    private int attackState;
    
    
    
    // 0: links, 1: rechts
    private int direction;
    
    public EntitySprite(Texture[] textures, int width, int height){
        sprites = new Sprite[1];
        regions = new TextureRegion[1][][];

        
        // 0: idle, 1: walking, 2: attack
        frames = new int[3];
        direction = 0;
        attackState = 0;
        
        for(int i = 0; i < sprites.length; i++){
            regions[i] = TextureRegion.split(textures[i], width, height);
            sprites[i] = new Sprite(regions[i][0][0]);
            collisionSprite = new Rectangle(0, 0, 32, 16);
        }
        
        this.fullCollisionSprite = sprites[0].getBoundingRectangle();
    }
    
    public void updateAnimation(Entity e){
            if(e != null){
                boolean moves = false;
            if(e.getMovementX() != 0 || e.getMovementY() != 0){
                moves = true;
            }
            
            direction = e.getDirection();

            if(attackState == 1){
                updateAttack();
            }
            else if(moves){
                updateWalking();
            }
            else{
                updateIdle();
            }
        }
    }
    
    public void updateAttack(){
        frames[0] = 0;
        frames[1] = 0;
        
        if(frames[2] >= 6){
            frames[2] = 0;
            attackState = 2;
        }
        else{
            frames[2]++;
            
            sprites[0].setRegion(regions[0][3][frames[2]]);
            updateFlip();
        }
    }
    
    
    
    public void updateIdle(){
        frames[1] = 0;
        frames[2] = 0;
        
        if(frames[0] >= 9){
            frames[0] = 0;
        }
        else{
            frames[0]++;
        }
        
        sprites[0].setRegion(regions[0][2][frames[0]]);
        updateFlip();
    }
    
    public void updateWalking(){
        frames[0] = 0;
        frames[2] = 0;
        
        if(frames[1] >= 9){
            frames[1] = 0;
        }
        else{
            frames[1]++;
        }
        
        sprites[0].setRegion(regions[0][0][frames[1]]);
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
        getFullCollisionSprite().setPosition(xPos, yPos);
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
    
    public int getAttackState(){
        return this.attackState;
    }
    
    public void resetAttackState(){
        this.attackState = 0;
    }
    
    public void startAttack(){
        this.attackState = 1;
    }
}
