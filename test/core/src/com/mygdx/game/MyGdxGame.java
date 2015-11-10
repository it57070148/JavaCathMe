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

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	
	final long startTime = System.currentTimeMillis();
	BitmapFont font;
	SpriteBatch batch;
	SpriteBatch batchFix;
	John john;
	Random rand;
	TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
	float r,g,b;
	Texture mouse;
	int speed = 2;
	Task changeColor;
	Vector2 oldJohn;
	
	ShapeRenderer sr;
	Music mainMusic;
	float posX, posY, w, h;
	int mapW, mapH;
	
	GameServer gameServer;
	GameClient user, user2;
	@Override
	public void create () {
		sr = new ShapeRenderer();
		Gdx.input.setCursorCatched(true);
		mouse = new Texture("mouse.jpg");
		w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        posX = w / 2;
        posY = h / 2;
		rand = new Random();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,w,h);
        camera.update();
		tiledMap = new TmxMapLoader().load("maps/test2.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		mapW = tiledMap.getProperties().get("width", Integer.class) * 16;
		mapH = tiledMap.getProperties().get("height", Integer.class) * 16;
		
		
		font = new BitmapFont();
        font.setColor(Color.RED);
        
        mainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/main.mp3"));
        
		batch = new SpriteBatch();
		batchFix  = new SpriteBatch();
		john = new John().run();
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
		Timer.schedule(new Task(){
		    @Override
		    public void run() {
		    	Gdx.graphics.setTitle(String.format("%s - %d FPS :: Pika x%d y%d :: mouse x%.1f y%.1f :: RGB %.2f %.2f %.2f :: Memory %.2f MB. :: Running time %s :: Developer mode", 
		    			Game.title,
		    			Gdx.graphics.getFramesPerSecond(),
		    			john.getX(),
		    			john.getY(),
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
		
		mainMusic.play();
		mainMusic.setLooping(true);
		
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
		
		oldJohn = new Vector2(john.getX(), john.getY());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
		boolean isColl = false;
		boolean isInvi = false;
        MapObjects mapObj = tiledMap.getLayers().get("obj").getObjects();
        MapObjects mapInvi = tiledMap.getLayers().get("invi").getObjects();
        sr.setAutoShapeType(true);
    	sr.begin();
    	sr.setColor(1, 0, 0, 1);
    	sr.setProjectionMatrix(camera.combined);
        for(int i = 0; i< mapObj.getCount(); i++) {
        	float x = mapObj.get(i).getProperties().get("x", Float.class);
        	float y = mapObj.get(i).getProperties().get("y", Float.class);
        	float w = mapObj.get(i).getProperties().get("width", Float.class);
        	float h = mapObj.get(i).getProperties().get("height", Float.class);
        	//if (mapObj.get(i) instanceof RectangleMapObject) {
        		//RectangleMapObject obj = (RectangleMapObject) mapObj.get(i);
	        	Rectangle rect = new Rectangle(x, y + h, w, h);
	        	
	        	sr.rect(rect.x, rect.y, rect.width, rect.height);
	        	
	        	if (rect.overlaps(new Rectangle(john.getX(), john.getY(), john.rWidth, john.rHeight))) {
	        		isColl = true;
	        		john.setX((int) oldJohn.x);
	        		john.setY((int) oldJohn.y); 
	        		// break;
	        	}
	        	//System.out.println("Test");
        	//}

        }
        //check hide
        for(int i = 0; i< mapInvi.getCount(); i++) {
        	float x = mapInvi.get(i).getProperties().get("x", Float.class);
        	float y = mapInvi.get(i).getProperties().get("y", Float.class);
        	float w = mapInvi.get(i).getProperties().get("width", Float.class);
        	float h = mapInvi.get(i).getProperties().get("height", Float.class);
	        	Rectangle rect = new Rectangle(x, y, w, h);
	        	
	        	sr.rect(rect.x, rect.y, rect.width, rect.height);
	        	
	        	if (rect.overlaps(new Rectangle(john.getX(), john.getY(), john.rWidth, john.rHeight))) {
	        		isInvi = true;
	        		//john.setX((int) oldJohn.x);
	        		//john.setY((int) oldJohn.y); 
	        		// break;
	        	}
	        	//System.out.println("Test");
        	//}

        }
        sr.end();
        sr.begin();
        sr.rect(john.getX(), john.getY(), john.rWidth, john.rHeight);
        sr.end();
        oldJohn = new Vector2(john.getX(), john.getY());
        
        if (!isColl) {
			if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
				john.plusX(speed);
				john.chrRight(isInvi);
			}
		else if(Gdx.input.isKeyPressed(Keys.LEFT)) {
				john.plusX(-speed);
				john.chrLeft(isInvi);
			}
		else if(Gdx.input.isKeyPressed(Keys.UP)) {
				john.plusY(speed);
				john.chrUp(isInvi);
			
			}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)) {
				john.plusY(-speed);
				john.chrDown(isInvi);
			}
        }
		moveScreen();
		
		john.fixOverFlow(mapW, mapH);
		
		UpdateCamera();
	
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.draw(john, john.getX(), john.getY(),john.rWidth,john.rHeight);
		batch.end();
		
		batchFix.begin();
		if(Game.debug) {
			
			font.draw(batchFix, String.format("Speed: %d :: isAnimation %s", speed, john.isAnimation()), 5, Gdx.graphics.getHeight()-5);
			font.draw(batchFix, String.format("+,- for up-down speed, mouse click for toggle Animation", speed, john.isAnimation()), 5, Gdx.graphics.getHeight()-25);
			font.draw(batchFix, String.format("Camera x%.1f y%.1f :: Zoom %.2f", camera.position.x, camera.position.y, camera.zoom), 5, Gdx.graphics.getHeight()-45);
			font.draw(batchFix, String.format("Play music: %s level %.1f %%", mainMusic.isPlaying(), mainMusic.getVolume() * 100), 5, Gdx.graphics.getHeight()-65);
		}
		if(Gdx.input.isCursorCatched())
			batchFix.draw(mouse, posX, h - posY);
		batchFix.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.PLUS)
			speed++;
		else if(keycode == Keys.MINUS)
			speed--;
		if(keycode == Keys.ESCAPE) {
			Gdx.input.setCursorCatched(!Gdx.input.isCursorCatched());
			Gdx.input.setCursorPosition((int)posX, (int)posY);
		}
		if(speed <= 0)
			speed = 1;
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(!Gdx.input.isCursorCatched())
			Gdx.input.setCursorCatched(true);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(button == Buttons.RIGHT)
			john.toggle();
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(!Gdx.input.isCursorCatched())
			return false;
		camera.translate(posX-screenX, -(posY-screenY));
		//fixOverFlow();
		if(screenX < 0)
			Gdx.input.setCursorPosition(0, screenY);
		if(screenY < mouse.getHeight())
			Gdx.input.setCursorPosition(screenX, mouse.getHeight());
		if(screenX > w - mouse.getWidth())
			Gdx.input.setCursorPosition((int) (w - mouse.getWidth()), screenY);
		if(screenY > h )
			Gdx.input.setCursorPosition(screenX, (int) h);
		posX = Gdx.input.getX();
        posY = Gdx.input.getY();
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		posX = Gdx.input.getX();
        posY = Gdx.input.getY();
		if(!Gdx.input.isCursorCatched())
			return false;
		Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX() , Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        
		if(screenX < 0)
			Gdx.input.setCursorPosition(0, Gdx.input.getY());
		if(screenY < mouse.getHeight())
			Gdx.input.setCursorPosition(Gdx.input.getX(), mouse.getHeight());
		if(screenX > w - mouse.getWidth())
			Gdx.input.setCursorPosition((int) (w - mouse.getWidth()), Gdx.input.getY());
		if(screenY > h )
			Gdx.input.setCursorPosition(Gdx.input.getX(), (int) h);
		posX = Gdx.input.getX();
        posY = Gdx.input.getY();
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		System.out.println(amount);
		camera.zoom += amount * 0.03f;
		return false;
	}
	
	public void moveScreen() {
		if(Gdx.input.isTouched() || !Gdx.input.isCursorCatched())
			return;
		if(posX < 32) {
			camera.translate(-16, 0);
		}
		if(posX > w - 32) {
			camera.translate(16, 0);
		}
		if(posY < 32) {
			camera.translate(0, 16);
		}
		if(posY > h - 32) {
			camera.translate(0, -16);
		}
	}
	
	public void UpdateCamera() {
	
		
		if(camera.position.x > mapW - w / 2)
			camera.position.x = john.getX();
		if(camera.position.y > mapH - h / 2)
			camera.position.y = john.getY();
		if(camera.position.x < w / 2)
			camera.position.x = john.getX();
		if(camera.position.y < h / 2)
			camera.position.y = john.getY();
		
	}
	
}
