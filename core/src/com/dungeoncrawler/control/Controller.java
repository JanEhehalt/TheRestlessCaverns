/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungeoncrawler.control;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dungeoncrawler.view.View;
import com.dungeoncrawler.model.Dungeon;
import com.dungeoncrawler.model.DungeonGenerator;
import com.dungeoncrawler.model.entities.Player;
import com.dungeoncrawler.model.entities.Archer;
import com.badlogic.gdx.utils.Timer;

public class Controller extends ApplicationAdapter implements InputProcessor{
    SpriteBatch batch;
    Dungeon d;
    DungeonGenerator dg;
    View v;
    Player p; 
    Archer a;
    Timer tAttack;
    Timer coords;
    
    @Override
    public void create(){
        batch = new SpriteBatch();
        p = new Player(200f, 200f);
        d = new Dungeon(p);
        v = new View();
        p.setxPos(v.getPlayerX());
        p.setyPos(v.getPlayerY());
        
        dg = new DungeonGenerator();
        dg.ichWillSpielen();
        
        d = new Dungeon(p);
        a = new Archer(500f, 200f, 1);
        a.setxPos(v.getArcherX());
        a.setyPos(v.getArcherY());
        
        Gdx.input.setInputProcessor(this);
        tAttack = new Timer();
        tAttack.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        if(p.getxPos() == a.getxPos()){
                            if(p.getyPos() > a.getyPos()){
                                a.attack(0); //UP
                                if(v.getArrowTravel() == 0){
                                v.arrow(a,0);
                                }
                            }
                            else if(p.getyPos() < a.getyPos()){
                                a.attack(2); //DOWN
                                if(v.getArrowTravel() == 0){
                                v.arrow(a,2);
                                }
                            }
                            
                        }
                        if(p.getyPos() == a.getyPos()){
                            if(p.getxPos() > a.getxPos()){
                                a.attack(1); //RIGHT
                                if(v.getArrowTravel() == 0){
                                v.arrow(a,1);
                                }
                            }
                            else if(p.getxPos() < a.getxPos()){
                                a.attack(3); //LEFT
                                if(v.getArrowTravel() == 0){
                                v.arrow(a,3);
                                }
                            }
                        }
                        else{
                            a.rdmMove();
                        }
                     }
                },0,0.5f);
        
        coords = new Timer();
                
                coords.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        System.out.println("Player:");
                        System.out.println("X:" + p.getxPos());
                        System.out.println("Y:" + p.getyPos());
                        System.out.println("Archer:");
                        System.out.println("X:" + a.getxPos());
                        System.out.println("Y:" + a.getyPos());
                        
                    }
                },0,2f);
    }
    
    @Override
    public void render(){
        v.setPlayerX(p.getxPos());
        v.setPlayerY(p.getyPos());
        v.setArcherX(a.getxPos());
        v.setArcherY(a.getyPos());
        v.render(batch, p , a);
    }
    
    @Override
	public void dispose () {
            batch.dispose();
	}
        
    @Override
    public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.LEFT){
                    p.setMovementX(-3);
                    p.move();
                }
                
                if(keycode == Input.Keys.RIGHT){
                    p.setMovementX(3);
                    p.move();
                }
                
                if(keycode == Input.Keys.UP){
                    p.setMovementY(3);
                    p.move();
                }
                
                if(keycode == Input.Keys.DOWN){
                    p.setMovementY(-3);
                    p.move();
                } 
                if(keycode == Input.Keys.W){
                    
                }
                return true;
    }

    @Override
    public boolean keyUp(int keycode) {
                if(keycode == Input.Keys.LEFT){
                    p.setMovementX(0);
                    p.move();
                    v.tlinksstop();
                }
                
                if(keycode == Input.Keys.RIGHT){
                    p.setMovementX(0);
                    p.move();
                    v.trechtsstop();
                }
                
                if(keycode == Input.Keys.DOWN){
                    p.setMovementY(0);
                    p.move();
                    v.tuntenstop();
                } 
                
                if(keycode == Input.Keys.UP){
                    p.setMovementY(0);
                    p.move();
                    v.tobenstop();
                }
                return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
