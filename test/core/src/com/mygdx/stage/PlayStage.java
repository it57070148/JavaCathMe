package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.charactor.John;
import com.mygdx.charactor.Player;
import com.mygdx.charactor.PlayerList;
import com.mygdx.charactor.Police;
import com.mygdx.game.Game;
import com.mygdx.game.Game.CharType;
import com.mygdx.stage.GameStageManage.Level;

import net.catchme.packet.HandCheckPacket;
import net.catchme.packet.PacketHelper;
import net.catchme.packet.PacketJoin;
import net.catchme.packet.PacketMove;
import net.catchme.packet.PacketThread;

public class PlayStage extends Stage implements InputProcessor {

	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;

	John john;
	Police josh;
	Pixmap pix;
	SpriteBatch batch;

	Vector2 oldJohn;
	Vector2 oldJosh;

	ShapeRenderer sr;
	Music mainMusic;
	float posX, posY, w, h;
	int mapW, mapH;

	private Thread serverThread;
	int speed = 2;

	// Player Status for Packaging Network Data
	boolean dead = false;
	boolean buf = false;

	private int type;

	public static Player player;
	private Vector2 oldPlayer;

	boolean isColl = false;
	boolean isInvi = false;

	public PlayStage(GameStageManage gsm) {
		super(gsm);

		sr = new ShapeRenderer();
		batch = new SpriteBatch();
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		posX = w / 2;
		posY = h / 2;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
		camera.zoom = 0.5f;
		tiledMap = new TmxMapLoader().load("maps/TheMazeBeta.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		mapW = tiledMap.getProperties().get("width", Integer.class) * 20;
		mapH = tiledMap.getProperties().get("height", Integer.class) * 20;
		if (Game.getCharType(Game.type) == Game.CharType.POLICE) {
			player = new Police((int) (w / 2) + 30, (int) h / 2);
		} else {
			player = new John((int) (w / 2) + 30, (int) h / 2);
		}
		oldPlayer = new Vector2(player.getX(), player.getY());
		
		
	}

	@Override
	public void create() {

	}

	@Override
	public void render() {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Game.timeRemain < 0) {
			System.out.println(Game.timeRemain);
			this.gsm.setStage(Level.RESULT);
			return;
		}
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		isColl = false;
		isInvi = false;

		//check(player);
		sr.setAutoShapeType(true);
		sr.begin();
		sr.setColor(1, 0, 0, 1);
		sr.setProjectionMatrix(camera.combined);

		sr.end();
		sr.begin();
		sr.rect(player.getX(), player.getY(), player.getrHeight(), player.getrHeight());
		sr.end();
		check(player);
		if (!isColl) {
			oldPlayer.set(player.getX(), player.getY());
			if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				player.plusX(speed);
				player.chrRight(isInvi);

			} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				player.plusX(-speed);
				player.chrLeft(isInvi);
			} else if (Gdx.input.isKeyPressed(Keys.UP)) {
				player.plusY(speed);
				player.chrUp(isInvi);

			} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				player.plusY(-speed);
				player.chrDown(isInvi);
			}

		}
		
		if (Game.getCharType(Game.type) == CharType.POLICE &&Gdx.input.isKeyJustPressed(Keys.C)) {
			PlayerList.checkCatch(player);
		}
		


		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.draw(player.getTexture(), player.getX(), player.getY(), player.getrHeight(), player.getrHeight());
		batch.end();
		PlayerList.draw(batch, camera.combined, player);
		moveScreen();
		// updateCamera();
		camera.position.x = player.getX();
		camera.position.y = player.getY();
		
		int invi = player.getInvi() ? 1 : 0;

		PacketHelper.sendPacket(new PacketMove(Game.clientId, Game.type, player.getX(), player.getY(), player.getDirection(), invi));
	
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void moveScreen() {
		if (Gdx.input.isTouched() || !Gdx.input.isCursorCatched()) {
			return;
		}
		if (posX < 32) {
			camera.translate(-16, 0);
		}
		if (posX > w - 32) {
			camera.translate(16, 0);
		}
		if (posY < 32) {
			camera.translate(0, 16);
		}
		if (posY > h - 32) {
			camera.translate(0, -16);
		}
	}

	public void updateCamera() {

		if (camera.position.x > mapW - w / 2) {
			camera.position.x = john.getX();
		}
		if (camera.position.y > mapH - h / 2) {
			camera.position.y = john.getY();
		}
		if (camera.position.x < w / 2) {
			camera.position.x = john.getX();
		}
		if (camera.position.y < h / 2) {
			camera.position.y = john.getY();
		}

	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.PLUS) {
			speed++;
		} else if (keycode == Keys.MINUS) {
			speed--;
		}
		if (keycode == Keys.ESCAPE) {
			Gdx.input.setCursorCatched(!Gdx.input.isCursorCatched());
			Gdx.input.setCursorPosition((int) posX, (int) posY);
		}
		if (speed <= 0) {
			speed = 1;
		}
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
		if (!Gdx.input.isCursorCatched()) {
			Gdx.input.setCursorCatched(true);
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (!Gdx.input.isCursorCatched()) {
			return false;
		}
		camera.translate(posX - screenX, -(posY - screenY));
		// fixOverFlow();
		/*
		 * if(screenX < 0) Gdx.input.setCursorPosition(0, screenY); if(screenY <
		 * mouse.getHeight()) Gdx.input.setCursorPosition(screenX,
		 * mouse.getHeight()); if(screenX > w - mouse.getWidth())
		 * Gdx.input.setCursorPosition((int) (w - mouse.getWidth()), screenY);
		 * if(screenY > h ) Gdx.input.setCursorPosition(screenX, (int) h);
		 */
		posX = Gdx.input.getX();
		posY = Gdx.input.getY();
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		posX = Gdx.input.getX();
		posY = Gdx.input.getY();
		if (!Gdx.input.isCursorCatched()) {
			return false;
		}
		Vector3 touchPos = new Vector3();
		touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchPos);

		/*
		 * if(screenX < 0) Gdx.input.setCursorPosition(0, Gdx.input.getY());
		 * if(screenY < mouse.getHeight())
		 * Gdx.input.setCursorPosition(Gdx.input.getX(), mouse.getHeight());
		 * if(screenX > w - mouse.getWidth()) Gdx.input.setCursorPosition((int)
		 * (w - mouse.getWidth()), Gdx.input.getY()); if(screenY > h )
		 * Gdx.input.setCursorPosition(Gdx.input.getX(), (int) h);
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

	public void check(Player p) {
		checkHide(p);
		checkObj(p);
		checkWarp1(p);
		checkWarp2(p);
		checkWarp3(p);
		checkWarp4(p);
	}

	public void checkWarp1(Player p) {
		MapObjects mapW1 = tiledMap.getLayers().get("warp1").getObjects();

		// check Wrap1
		for (int i = 0; i < mapW1.getCount(); i++) {
			float x = mapW1.get(i).getProperties().get("x", Float.class);
			float y = mapW1.get(i).getProperties().get("y", Float.class);
			float w = mapW1.get(i).getProperties().get("width", Float.class);
			float h = mapW1.get(i).getProperties().get("height", Float.class);
			Rectangle rect = new Rectangle(x, y, w, h);

			// sr.rect(rect.x, rect.y, rect.width, rect.height);

			if (rect.overlaps(new Rectangle(p.getX(), p.getY(), p.getrWidth(), p.getrHeight()))) {

				p.setX((1195));
				p.setY((328));
				break;
			}
		}
	}

	public void checkWarp2(Player p) {
		MapObjects mapW2 = tiledMap.getLayers().get("warp2").getObjects();

		// check Wrap2
		for (int i = 0; i < mapW2.getCount(); i++) {
			float x = mapW2.get(i).getProperties().get("x", Float.class);
			float y = mapW2.get(i).getProperties().get("y", Float.class);
			float w = mapW2.get(i).getProperties().get("width", Float.class);
			float h = mapW2.get(i).getProperties().get("height", Float.class);
			Rectangle rect = new Rectangle(x, y, w, h);

			// sr.rect(rect.x, rect.y, rect.width, rect.height);

			if (rect.overlaps(new Rectangle(p.getX(), p.getY(), p.getrWidth(), p.getrHeight()))) {

				p.setX((603));
				p.setY((12));
				break;
			}

		}
	}

	public void checkWarp3(Player p) {
		MapObjects mapW3 = tiledMap.getLayers().get("warp3").getObjects();

		// check Wrap3
		for (int i = 0; i < mapW3.getCount(); i++) {
			float x = mapW3.get(i).getProperties().get("x", Float.class);
			float y = mapW3.get(i).getProperties().get("y", Float.class);
			float w = mapW3.get(i).getProperties().get("width", Float.class);
			float h = mapW3.get(i).getProperties().get("height", Float.class);
			Rectangle rect = new Rectangle(x, y, w, h);

			// sr.rect(rect.x, rect.y, rect.width, rect.height);

			if (rect.overlaps(new Rectangle(p.getX(), p.getY(), p.getrWidth(), p.getrHeight()))) {

				p.setX((5));
				p.setY((324));
				break;
			}
		}
	}

	public void checkWarp4(Player p) {
		MapObjects mapW4 = tiledMap.getLayers().get("warp4").getObjects();

		// check Wrap4
		for (int i = 0; i < mapW4.getCount(); i++) {
			float x = mapW4.get(i).getProperties().get("x", Float.class);
			float y = mapW4.get(i).getProperties().get("y", Float.class);
			float w = mapW4.get(i).getProperties().get("width", Float.class);
			float h = mapW4.get(i).getProperties().get("height", Float.class);
			Rectangle rect = new Rectangle(x, y, w, h);

			// sr.rect(rect.x, rect.y, rect.width, rect.height);

			if (rect.overlaps(new Rectangle(p.getX(), p.getY(), p.getrWidth(), p.getrHeight()))) {

				p.setX((603));
				p.setY((618));
				break;
			}
		}
	}

	public void checkHide(Player p) {
		MapObjects mapInvi = tiledMap.getLayers().get("invi").getObjects();
		// check hide
		for (int i1 = 0; i1 < mapInvi.getCount(); i1++) {
			float x = mapInvi.get(i1).getProperties().get("x", Float.class);
			float y = mapInvi.get(i1).getProperties().get("y", Float.class);
			float w = mapInvi.get(i1).getProperties().get("width", Float.class);
			float h = mapInvi.get(i1).getProperties().get("height", Float.class);
			Rectangle rect = new Rectangle(x, y, w, h);

			// sr.rect(rect.x, rect.y, rect.width, rect.height);

			if (rect.overlaps(new Rectangle(p.getX(), p.getY(), p.getrWidth(), p.getrHeight()))) {
				isInvi = true;

			}
			
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.GOLD);
			sr.rect(rect.x, rect.y, rect.width, rect.height);
			sr.end();

		}
	}

	public void checkObj(Player p) {
		MapObjects mapObj = tiledMap.getLayers().get("obj").getObjects();
		for (int i = 0; i < mapObj.getCount(); i++) {
			float x = mapObj.get(i).getProperties().get("x", Float.class);
			float y = mapObj.get(i).getProperties().get("y", Float.class);
			float w = mapObj.get(i).getProperties().get("width", Float.class);
			float h = mapObj.get(i).getProperties().get("height", Float.class);
			Rectangle rect = new Rectangle(x, y, w, h);

			// sr.rect(rect.x, rect.y, rect.width, rect.height);

			if (rect.overlaps(new Rectangle(p.getX(), p.getY(), p.getrWidth(), p.getrHeight()))) {
				isColl = true;
				p.setX((int) oldPlayer.x);
				p.setY((int) oldPlayer.y);
				System.out.println(p.getOldX() + ":" + p.getOldY());
				// break;
			}
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.BLUE);
			sr.rect(rect.x, rect.y, rect.width, rect.height);
			sr.end();

		}
	}
}
