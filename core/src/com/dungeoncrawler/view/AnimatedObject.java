/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author jonathan
 */
public class AnimatedObject {
    private Sprite sprite;
    private TextureRegion[][] texture;
    private int frame;
    private int row;
    
    public AnimatedObject(Texture tx, int tileWidth, int tileHeight){
        texture = TextureRegion.split(tx, tileWidth, tileHeight);
        
        frame = (int) (Math.random()*texture[0].length);
        row = (int) (Math.random()*texture.length);
        
        sprite = new Sprite(texture[row][frame]);
    }

    public void updateTexture(){
        if(frame >= texture[0].length - 1){
            frame = 0;
        }
        else{
            frame++;
        }
        
        sprite.setRegion(texture[row][frame]);
        
    }
    
    /**
     * @return the sprite
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * @param sprite the sprite to set
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public void setSpritePosition(int xPos, int yPos){
        sprite.setPosition(xPos, yPos);
    }

    /**
     * @return the texture
     */
    public TextureRegion[][] getTexture() {
        return texture;
    }

    /**
     * @param texture the texture to set
     */
    public void setTexture(TextureRegion[][] texture) {
        this.texture = texture;
    }
}
