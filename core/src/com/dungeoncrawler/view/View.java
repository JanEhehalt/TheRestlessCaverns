package com.dungeoncrawler.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class View {
	Texture b;
        Texture t;
        Texture p;
        Sprite button;
        Sprite title;
        Sprite player;
        
	public View() {
                b = new Texture("Button.png");
                t = new Texture("Title.png");
                p = new Texture("Player.png");
                button = new Sprite(b);
                title = new Sprite(t);
                player = new Sprite(p);
                float w = Gdx.graphics.getWidth();
                float h = Gdx.graphics.getHeight();
                float wc = w/2;
                title.setX(wc - (title.getWidth()/2));
                title.setY(h - 200);
                button.setX(wc - (button.getWidth()/2));
                button.setY(400);
                player.setX(200);
                player.setY(200);
	}

        
	public void render (SpriteBatch batch, float x, float y) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                player.setX(player.getX()+x);
                player.setY(player.getY()+y);
                batch.begin();
                title.draw(batch);
                button.draw(batch);
                player.draw(batch);
                batch.end();
	}
        
        
        
        
}
