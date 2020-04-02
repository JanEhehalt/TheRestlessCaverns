package com.dungeoncrawler.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class View extends ApplicationAdapter {
	SpriteBatch batch;
	Texture b;
        Texture t;
        Sprite button;
        Sprite title;
        Map map;
        TiledMapRenderer tmr;
        OrthographicCamera camera;

        
	@Override
	public void create () {
		batch = new SpriteBatch();
                b = new Texture("Button.png");
                t = new Texture("Title.png");
                button = new Sprite(b);
                title = new Sprite(t);
                float w = Gdx.graphics.getWidth();
                float h = Gdx.graphics.getHeight();
                float wc = w/2;
                title.setX(wc - (title.getWidth()/2));
                title.setY(h - 200);
                button.setX(wc - (button.getWidth()/2));
                button.setY(400);
                
                map = new Map();
                tmr = new OrthogonalTiledMapRenderer(map.getMap());
                
                camera = new OrthographicCamera(1, h/w);
                camera.position.x = 200;
                camera.position.y = 200;

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                tmr.setView(camera);
                tmr.render();
                
                camera.zoom = 1000f;
                camera.update();
                batch.setProjectionMatrix(camera.combined);
                
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
