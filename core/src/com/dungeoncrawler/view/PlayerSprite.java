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
public class PlayerSprite {
    
    // 0: pants, 1: body, 2: head, 3: hair, 4: collision
    private Sprite[] sprites;
    private Rectangle collisionSprite;
    private TextureRegion[][][] regions;
    private  int[] frames;
    
    public PlayerSprite(Texture[] textures){
        sprites = new Sprite[4];
        regions = new TextureRegion[4][][];
        frames = new int[4];
        
        for(int i = 0; i < regions.length; i++){
            regions[i] = TextureRegion.split(textures[i], 32, 64);
            sprites[i] = new Sprite(textures[i]);
        }
        
        collisionSprite = new Rectangle(0, 0, 32, 16);
    }
    
    public void update(int xPos, int yPos){
        for(int i = 0; i < sprites.length; i++){
            sprites[i].setPosition(xPos, yPos);
        }
        
        collisionSprite.setPosition(xPos, yPos);
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
}
