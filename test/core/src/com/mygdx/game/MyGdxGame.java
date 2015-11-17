package com.mygdx.game;

import java.time.LocalTime;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.Handle.GameClient;
import com.mygdx.Handle.GameServer;
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
	float r,g,b;
	Texture mouse;
	int speed = 2;
	Task changeColor;
	
	
	GameServer gameServer;
	GameClient user, user2;
	@Override
	public void create () {
		
		Gdx.input.setCursorCatched(true);
		mouse = new Texture("mouse.jpg");
		
		rand = new Random();
				
		
		font = new BitmapFont();
        font.setColor(Color.RED);
        
        //mainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/main.mp3"));
        
		batch = new SpriteBatch();
		batchFix  = new SpriteBatch();
		
		changeColor = Timer.schedule(new Task(){
		    @Override
		    public void run() {
		        r = rand.nextFloat();
		        g = rand.nextFloat();
		        b = rand.nextFloat();
		    }
		}, 0.0f, 0.1f);
		
		gameServer = new GameServer();
		gameServer.runSocket();
		// gameServer.connect();
		//user = new GameClient();
		//user.runSocket();
		//user.connect();	
		//user2 = new GameClient();
		//user2.connect();
		//gameServer.ping();
		/*
		Timer.schedule(new Task(){
		    @Override
		    public void run() {
		    	Gdx.graphics.setTitle(String.format("%s - %d FPS :: Pika x%d y%d :: mouse x%.1f y%.1f :: RGB %.2f %.2f %.2f :: Memory %.2f MB. :: Running time %s :: Developer mode", 
		    			Game.title,
		    			Gdx.graphics.getFramesPerSecond(),
		    			john.getX(),
		    			john.getY(),
		    			//josh.getX(),
		    			//josh.getY(),
		    			posX,
		    			posY,
		    			r,
		    			g,
		    			b,
		    			Gdx.app.getNativeHeap() / 1024.0 / 1024.0,
		    			LocalTime.ofSecondOfDay((System.currentTimeMillis() - startTime) / 1000)));
		    }
		}, 0.0f, 1/60f);
		Gdx.input.setInputProcessor(this);
		*/
		//mainMusic.play();
		//mainMusic.setLooping(true);
		
		// Update Game Logic
		new Thread(new Runnable() {
			public void run() {
				long sTime = System.currentTimeMillis();
				while(true){
					if(sTime > System.currentTimeMillis() - 1000)
						continue;
					// gameServer.ping();
					//user.ping();
					//user2.ping();
					
					// user.updateCharactor("John", john.getX(), john.getY());
					sTime = System.currentTimeMillis();
				}
			}
		}).start();
		
		
		//oldJosh = new Vector2(john.getX(), john.getY());
	}

	@Override
	public void render() {
		
		
		
		
		batchFix.begin();
		if(Game.debug) {
			
			font.draw(batchFix, String.format("Speed: %d :: isAnimation %s", speed, john.isAnimation()), 5, Gdx.graphics.getHeight()-5);
			font.draw(batchFix, String.format("+,- for up-down speed, mouse click for toggle Animation", speed, john.isAnimation()), 5, Gdx.graphics.getHeight()-25);
			font.draw(batchFix, String.format("Camera x%.1f y%.1f :: Zoom %.2f", camera.position.x, camera.position.y, camera.zoom), 5, Gdx.graphics.getHeight()-45);
			//font.draw(batchFix, String.format("Play music: %s level %.1f %%", mainMusic.isPlaying(), mainMusic.getVolume() * 100), 5, Gdx.graphics.getHeight()-65);
		}
		
		batchFix.end();
	}


	

	
}
