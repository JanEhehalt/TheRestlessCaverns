/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dungeoncrawler.model.Item;
import com.dungeoncrawler.model.entities.Player;

/**
 *
 * @author bfz
 */
public class HudContainer {
    Texture HudTexture;
    Sprite HudSprite;
    Texture[] InventoryItemTextures;
    Sprite[] InventoryItemSprites;
    float[] invXPos;
    float[] invYPos;
    
    public HudContainer(){
    
        HudTexture = new Texture("sprites/hud.png");
        HudSprite = new Sprite(HudTexture);
        HudSprite.setX(-HudSprite.getWidth());
        HudSprite.setY(20f);
        InventoryItemTextures = new Texture[8];
        InventoryItemSprites = new Sprite[8];
        invXPos = new float[8];
        invYPos = new float[8];
        //Equipped 1
        invXPos[0] = HudSprite.getX() + 37f;
        invYPos[0] = HudSprite.getY() + 112f;
        //Equipped 2
        invXPos[1] = HudSprite.getX() + 85f;
        invYPos[1] = HudSprite.getY() + 112f;

        invXPos[2] = HudSprite.getX() + 10f;
        invYPos[2] = HudSprite.getY() + 61f;
        invXPos[3] = HudSprite.getX() + 61f;
        invYPos[3] = HudSprite.getY() + 61f;
        invXPos[4] = HudSprite.getX() + 112f;
        invYPos[4] = HudSprite.getY() + 61f;

        invXPos[5] = HudSprite.getX() + 10f;
        invYPos[5] = HudSprite.getY() + 10f;
        invXPos[6] = HudSprite.getX() + 61f;
        invYPos[6] = HudSprite.getY() + 10f;
        invXPos[7] = HudSprite.getX() + 112f;
        invYPos[7] = HudSprite.getY() + 10f;
    
    }
    
    public void updateInventory(SpriteBatch batch, Player p){
        InventoryItemSprites = new Sprite[8];
                
            Item[][] items = p.getInv().getItem();
            
            for(int x = 0; x < items.length; x++){
                for(int y = 0; y < items[0].length; y++){
                    if(items[x][y] != null){
                        addItem(items[x][y]);
                    }
                }
            }
            batch.begin();
            HudSprite.draw(batch);
            for(int i = 0; i < InventoryItemSprites.length ;i++){
                    if(InventoryItemSprites[i] != null){
                        InventoryItemSprites[i].draw(batch);
                    }
                }
            batch.end();
            
    }
    
    public void addItem(Item item){
            switch(item.getId()){
                case 0:
                    for(int i = 2; i < InventoryItemSprites.length; i++){
                        if(InventoryItemSprites[i] == null){
                            //InventoryItemTextures[i] = new Texture("sprites/itemTest.png");
                            InventoryItemTextures[i] = new Texture("sprites/key.png");
                            InventoryItemSprites[i] = new Sprite(InventoryItemTextures[i]);
                            InventoryItemSprites[i].setX(invXPos[i]);
                            InventoryItemSprites[i].setY(invYPos[i]);
                            break;
                        }
                    }
                    break;
                case 1:
                    for(int i = 2; i < InventoryItemSprites.length; i++){
                        if(InventoryItemSprites[i] == null){
                            //InventoryItemTextures[i] = new Texture("sprites/itemTest.png");
                            InventoryItemTextures[i] = new Texture("sprites/healingPotion.png");
                            InventoryItemSprites[i] = new Sprite(InventoryItemTextures[i]);
                            InventoryItemSprites[i].setX(invXPos[i]);
                            InventoryItemSprites[i].setY(invYPos[i]);
                            break;
                        }
                    }
                    break;
                case 2:
                    for(int i = 2; i < InventoryItemSprites.length; i++){
                        if(InventoryItemSprites[i] == null){
                            //InventoryItemTextures[i] = new Texture("sprites/itemTest.png");
                            InventoryItemTextures[i] = new Texture("sprites/sword.png");
                            InventoryItemSprites[i] = new Sprite(InventoryItemTextures[i]);
                            InventoryItemSprites[i].setX(invXPos[i]);
                            InventoryItemSprites[i].setY(invYPos[i]);
                            break;
                        }
                    }
                    break;
            }
        }
}
