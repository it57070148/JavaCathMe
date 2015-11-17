package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.charactor.John;
import com.mygdx.charactor.Police;

public class PlayStage extends Stage implements InputProcessor {
	
	
	TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    
    John john;
    Police josh;
    
    SpriteBatch batch;
    
    Vector2 oldJohn;
	//Vector2 oldJosh;
	
	ShapeRenderer sr;
	Music mainMusic;
	float posX, posY, w, h;
	int mapW, mapH;

	int speed = 2;
	
	//Player Status for Packaging Network Data
	boolean dead = false;
	boolean buf = false;
	
	public PlayStage(GameStageManage gsm) {
		super(gsm);
	
		sr = new ShapeRenderer();
		batch = new SpriteBatch();
		w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        posX = w / 2;
        posY = h / 2;
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false,w,h);
        camera.update();
        camera.zoom = 0.5f;
		tiledMap = new TmxMapLoader().load("maps/TheMazeBeta.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		mapW = tiledMap.getProperties().get("width", Integer.class) * 20;
		mapH = tiledMap.getProperties().get("height", Integer.class) * 20;
		
		john = new John((int)(w/2)+30, (int)h/2).run();
		josh = new Police((int)(w/2)+30,(int)h/2).run();

		oldJohn = new Vector2(john.getX(), john.getY());
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
		boolean isColl = false;
		boolean isInvi = false;
		boolean isTrap = false;
        MapObjects mapObj = tiledMap.getLayers().get("obj").getObjects();
        MapObjects mapInvi = tiledMap.getLayers().get("invi").getObjects();
        MapObjects mapW1 = tiledMap.getLayers().get("warp1").getObjects();
        MapObjects mapW2 = tiledMap.getLayers().get("warp2").getObjects();
        MapObjects mapW3 = tiledMap.getLayers().get("warp3").getObjects();
        MapObjects mapW4 = tiledMap.getLayers().get("warp4").getObjects();
        
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
	        	Rectangle rect = new Rectangle(x, y , w, h);
	        	
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
        //check Wrap1
        for(int i = 0; i< mapW1.getCount(); i++) {
        	float x = mapW1.get(i).getProperties().get("x", Float.class);
        	float y = mapW1.get(i).getProperties().get("y", Float.class);
        	float w = mapW1.get(i).getProperties().get("width", Float.class);
        	float h = mapW1.get(i).getProperties().get("height", Float.class);
	        	Rectangle rect = new Rectangle(x, y, w, h);
	        	
	        	sr.rect(rect.x, rect.y, rect.width, rect.height);
	        	
	        	if (rect.overlaps(new Rectangle(john.getX(), john.getY(), john.rWidth, john.rHeight))) {
	        		
	        		john.setX((1206) );
	        		john.setY((338)); 
	        		 break;
	        	}
	        	//System.out.println("Test");
        	//}

        }
      //check Wrap3
        for(int i = 0; i< mapW1.getCount(); i++) {
        	float x = mapW3.get(i).getProperties().get("x", Float.class);
        	float y = mapW3.get(i).getProperties().get("y", Float.class);
        	float w = mapW3.get(i).getProperties().get("width", Float.class);
        	float h = mapW3.get(i).getProperties().get("height", Float.class);
	        	Rectangle rect = new Rectangle(x, y, w, h);
	        	
	        	sr.rect(rect.x, rect.y, rect.width, rect.height);
	        	
	        	if (rect.overlaps(new Rectangle(john.getX(), john.getY(), john.rWidth, john.rHeight))) {
	        		
	        		john.setX((5) );
	        		john.setY((324)); 
	        		 break;
	        	}
	        	//System.out.println("Test");
        	//}

        }
      //check Wrap2
        for(int i = 0; i< mapW1.getCount(); i++) {
        	float x = mapW2.get(i).getProperties().get("x", Float.class);
        	float y = mapW2.get(i).getProperties().get("y", Float.class);
        	float w = mapW2.get(i).getProperties().get("width", Float.class);
        	float h = mapW2.get(i).getProperties().get("height", Float.class);
	        	Rectangle rect = new Rectangle(x, y, w, h);
	        	
	        	sr.rect(rect.x, rect.y, rect.width, rect.height);
	        	
	        	if (rect.overlaps(new Rectangle(john.getX(), john.getY(), john.rWidth, john.rHeight))) {
	        		
	        		john.setX((603) );
	        		john.setY((12)); 
	        		 break;
	        	}
	        	//System.out.println("Test");
        	//}

        }
      //check Wrap4
        for(int i = 0; i< mapW1.getCount(); i++) {
        	float x = mapW4.get(i).getProperties().get("x", Float.class);
        	float y = mapW4.get(i).getProperties().get("y", Float.class);
        	float w = mapW4.get(i).getProperties().get("width", Float.class);
        	float h = mapW4.get(i).getProperties().get("height", Float.class);
	        	Rectangle rect = new Rectangle(x, y, w, h);
	        	
	        	sr.rect(rect.x, rect.y, rect.width, rect.height);
	        	
	        	if (rect.overlaps(new Rectangle(john.getX(), john.getY(), john.rWidth, john.rHeight))) {
	        		
	        		john.setX((603) );
	        		john.setY((618)); 
	        		 break;
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
        //Cheak Trap
      //check hide
       // for(int i = 0; i< mapCatch.getCount(); i++) {
        //	float x = mapCatch.get(i).getProperties().get("x", Float.class);
        	//float y = mapCatch.get(i).getProperties().get("y", Float.class);
        	//float w = mapCatch.get(i).getProperties().get("width", Float.class);
        	//float h = mapCatch.get(i).getProperties().get("height", Float.class);
	        //	Rectangle rect = new Rectangle(x, y, w, h);
	        	
	        	//sr.rect(rect.x, rect.y, rect.width, rect.height);
	        	
	        	//if (rect.overlaps(new Rectangle(john.getX(), john.getY(), john.rWidth, john.rHeight))) {
	        		//isTrap = true;
	        		//john.chrCatch();
	        		
	        		
	        		
	        		// break;
	        	//}
	        	//System.out.println("Test");
        	//}

        //}
        
        sr.end();
        sr.begin();
        sr.rect(john.getX(), john.getY(), john.rWidth, john.rHeight);
        sr.end();
        oldJohn = new Vector2(john.getX(), john.getY());
        
        
        
        if (!isColl) {
			if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
				if(Gdx.input.isKeyPressed(Keys.RIGHT)){
						john.plusX(speed);
						john.chrRight(isInvi); }
				else if (Gdx.input.isKeyPressed(Keys.D)){
					josh.plusX(speed);
					josh.chrDown(isInvi);

				}
				
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
		
		else if(Gdx.input.isKeyPressed(Keys.A)) {
			josh.plusX(-speed);
			josh.chrDown(isInvi);
		}
		else if(Gdx.input.isKeyPressed(Keys.W)) {
			josh.plusY(speed);
			josh.chrDown(isInvi);
		}
		else if(Gdx.input.isKeyPressed(Keys.S)) {
			josh.plusY(-speed);
			josh.chrDown(isInvi);
		}
		
        }
      //Cath Detection
      		if(josh.getX() == john.getX() && john.getY() == josh.getY()){
      			if(Gdx.input.isKeyJustPressed(Keys.C)){
      				
      			josh.setX(50);
      			josh.setY(50);
      			
      			}
      		}
      	
      		batch.begin();
      		batch.setProjectionMatrix(camera.combined);
      		batch.draw(john, john.getX(), john.getY(),john.rWidth,john.rHeight);
      		batch.draw(josh, josh.getX(), josh.getY(),josh.rWidth,josh.rHeight);
      		batch.end();
        moveScreen();
        //updateCamera();
        camera.position.x = john.getX();
        camera.position.y = john.getY();
        System.out.println(john.getX()+" "+john.getY());
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
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
	
	public void updateCamera() {
	
		
		if(camera.position.x > mapW - w / 2)
			camera.position.x = john.getX();
		if(camera.position.y > mapH - h / 2)
			camera.position.y = john.getY();
		if(camera.position.x < w / 2)
			camera.position.x = john.getX();
		if(camera.position.y < h / 2)
			camera.position.y = john.getY();
		
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
		/*
		if(screenX < 0)
			Gdx.input.setCursorPosition(0, screenY);
		if(screenY < mouse.getHeight())
			Gdx.input.setCursorPosition(screenX, mouse.getHeight());
		if(screenX > w - mouse.getWidth())
			Gdx.input.setCursorPosition((int) (w - mouse.getWidth()), screenY);
		if(screenY > h )
			Gdx.input.setCursorPosition(screenX, (int) h);
			*/
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
        
        
        /*
		if(screenX < 0)
			Gdx.input.setCursorPosition(0, Gdx.input.getY());
		if(screenY < mouse.getHeight())
			Gdx.input.setCursorPosition(Gdx.input.getX(), mouse.getHeight());
		if(screenX > w - mouse.getWidth())
			Gdx.input.setCursorPosition((int) (w - mouse.getWidth()), Gdx.input.getY());
		if(screenY > h )
			Gdx.input.setCursorPosition(Gdx.input.getX(), (int) h);
		*/
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

}
