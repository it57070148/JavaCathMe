package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.stage.GameStageManage.Level;

public class HomeStage extends Stage {

	SpriteBatch sp;
	ShapeRenderer sr;
	Texture texture1;
	Texture texture2;
	Texture texture;
	Rectangle r1;
	Sprite sprite;
	Vector2 mouse;
	int chkpic = 0;

	public HomeStage(GameStageManage gsm) {
		super(gsm);

		sp = new SpriteBatch();
		sr = new ShapeRenderer();
		r1 = new Rectangle(200, 200, 200, 200);
		mouse = new Vector2();

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		texture1 = new Texture(Gdx.files.internal("Menu/Menu.png"));
		texture2 = new Texture(Gdx.files.internal("Menu/Menu1.gif"));
		sprite = new Sprite(texture2);
		Gdx.audio.newMusic(Gdx.files.internal("sounds/Menu.mp3"));
	}

	@Override
	public void render() {
		mouse.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sr.begin(ShapeType.Filled);
		sr.setColor(1, 0, 0, 1);
		
		if (chkpic > 10) {
			if (chkpic > 40) {
				chkpic = 0;
			}
			texture = texture1;
		} else {

			texture = texture2;

		}
		chkpic++;
		sprite = new Sprite(texture);
		sr.rect(r1.x, r1.y, r1.width, r1.height);
		sr.end();
		sp.begin();
		sprite.draw(sp);
		sp.end();
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

			gsm.setStage(Level.LOBBY);
		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
