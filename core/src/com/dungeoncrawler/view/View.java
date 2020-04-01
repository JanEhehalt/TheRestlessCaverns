package com.dungeoncrawler.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class View extends ApplicationAdapter {
	SpriteBatch batch;
	Texture b;
        Texture t;
        Sprite button;
        Sprite title;
        
	@Override
	public void create () {
		batch = new SpriteBatch();
                b = new Texture("button.png");
                t = new Texture("title.png");
                button = new Sprite(b);
                title = new Sprite(t);
                title.setX(0);
                title.setY(0);
                button.setX(75);
                button.setY(150);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
		batch.begin();
                title.draw(batch);
                button.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
                
	}
}
