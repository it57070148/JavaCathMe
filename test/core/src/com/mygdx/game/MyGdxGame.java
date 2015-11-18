package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import com.mygdx.charactor.John;

public class MyGdxGame extends ApplicationAdapter {

	final long startTime = System.currentTimeMillis();
	BitmapFont font;
	SpriteBatch batch;
	SpriteBatch batchFix;
	John john;
	John josh;
	Random rand;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	float r, g, b;
	Texture mouse;
	int speed = 2;
	Task changeColor;

	@Override
	public void create() {

		Gdx.input.setCursorCatched(true);
		mouse = new Texture("mouse.jpg");

		rand = new Random();

		font = new BitmapFont();
		font.setColor(Color.RED);

		// mainMusic =
		// Gdx.audio.newMusic(Gdx.files.internal("sounds/main.mp3"));

		batch = new SpriteBatch();
		batchFix = new SpriteBatch();

		changeColor = Timer.schedule(new Task() {
			@Override
			public void run() {
				r = rand.nextFloat();
				g = rand.nextFloat();
				b = rand.nextFloat();
			}
		}, 0.0f, 0.1f);

		// Update Game Logic
		new Thread(new Runnable() {
			@Override
			public void run() {
				long sTime = System.currentTimeMillis();
				while (true) {
					if (sTime > System.currentTimeMillis() - 1000) {
						continue;
						// gameServer.ping();
						// user.ping();
						// user2.ping();
					}

					// user.updateCharactor("John", john.getX(), john.getY());
					sTime = System.currentTimeMillis();
				}
			}
		}).start();

	}

	@Override
	public void render() {

		batchFix.begin();
		if (Game.debug) {

			font.draw(batchFix, String.format("Speed: %d :: isAnimation %s", speed, john.isAnimation()), 5,
					Gdx.graphics.getHeight() - 5);
			font.draw(batchFix,
					String.format("+,- for up-down speed, mouse click for toggle Animation", speed, john.isAnimation()),
					5, Gdx.graphics.getHeight() - 25);
			font.draw(batchFix,
					String.format("Camera x%.1f y%.1f :: Zoom %.2f", camera.position.x, camera.position.y, camera.zoom),
					5, Gdx.graphics.getHeight() - 45);
		}

		batchFix.end();
	}

}
