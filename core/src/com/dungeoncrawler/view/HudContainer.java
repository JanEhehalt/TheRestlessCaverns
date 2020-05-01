/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    int selected;
    Texture selectedTexture;
    Sprite selectedSprite;
    
    Texture playerHealthTexture;
    Sprite playerHealthSprite;
    float playerHealthX;
    float playerHealthY;
    
    public HudContainer(){
        
        
        selectedTexture = new Texture("sprites/selected.png");
        selectedSprite = new Sprite(selectedTexture);
        HudTexture = new Texture("sprites/hud.png");
        HudSprite = new Sprite(HudTexture);
        HudSprite.setX(-HudSprite.getWidth());
        HudSprite.setY(20f);
        InventoryItemTextures = new Texture[8];
        InventoryItemSprites = new Sprite[8];
        invXPos = new float[8];
        invYPos = new float[8];
        
        playerHealthTexture = new Texture("sprites/playerHealthBar.png");
        playerHealthSprite = new Sprite();
        playerHealthX = HudSprite.getX()+36f;
        playerHealthY = HudSprite.getY()+260f;
        
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
        
        selected = 2;
        selectedSprite.setX(invXPos[selected] - 2f);
        selectedSprite.setY(invYPos[selected] - 2f);
        
        
        
    }
    
    public void updateHud(SpriteBatch batch, Player p){
        InventoryItemSprites = new Sprite[8];
                
            Item[] items = p.getInv().getItem();
            
            for(int x = 0; x < items.length; x++){
                    if(items[x] != null){
                        addItem(items[x], x);
                    }
            }
            selectedSprite.setX(invXPos[selected] - 2f);
            selectedSprite.setY(invYPos[selected] - 2f);
            
            float n = p.getHp() / p.getMaxhp();
            int newWidth = (int) (n * playerHealthTexture.getWidth());
            TextureRegion[][] playerHealthRegion = TextureRegion.split(playerHealthTexture,newWidth, playerHealthTexture.getHeight());
            playerHealthSprite = new Sprite(playerHealthRegion[0][0]);
            playerHealthSprite.setPosition(playerHealthX, playerHealthY);
            
            batch.begin();
            HudSprite.draw(batch);
            for(int i = 0; i < InventoryItemSprites.length ;i++){
                    if(InventoryItemSprites[i] != null){
                        InventoryItemSprites[i].draw(batch);
                    }
                }
            selectedSprite.draw(batch);
            playerHealthSprite.draw(batch);
            batch.end();
            
            
    }
    
    public void addItem(Item item, int x){
            switch(item.getId()){
                case 0:
                    if(InventoryItemSprites[x] == null){
                        //InventoryItemTextures[i] = new Texture("sprites/itemTest.png");
                        InventoryItemTextures[x] = new Texture("sprites/key.png");
                        InventoryItemSprites[x] = new Sprite(InventoryItemTextures[x]);
                        InventoryItemSprites[x].setX(invXPos[x]);
                        InventoryItemSprites[x].setY(invYPos[x]);
                        break;
                    }
                    break;
                case 1:
                    if(InventoryItemSprites[x] == null){
                        //InventoryItemTextures[i] = new Texture("sprites/itemTest.png");
                        InventoryItemTextures[x] = new Texture("sprites/healingPotion.png");
                        InventoryItemSprites[x] = new Sprite(InventoryItemTextures[x]);
                        InventoryItemSprites[x].setX(invXPos[x]);
                        InventoryItemSprites[x].setY(invYPos[x]);
                        break;
                    }
                    break;
                case 2:
                        if(InventoryItemSprites[x] == null){
                            //InventoryItemTextures[i] = new Texture("sprites/itemTest.png");
                            InventoryItemTextures[x]= new Texture("sprites/sword.png");
                            InventoryItemSprites[x] = new Sprite(InventoryItemTextures[x]);
                            InventoryItemSprites[x].setX(invXPos[x]);
                            InventoryItemSprites[x].setY(invYPos[x]);
                            break;
                        }
                    break;
            }
        }
    
    public void setSelected(int i){
            selected = i;
    }
}
