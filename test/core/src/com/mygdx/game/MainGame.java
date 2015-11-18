package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.stage.GameStageManage;

public class MainGame extends ApplicationAdapter {

	GameStageManage gsm;
	BitmapFont font;
	private SpriteBatch batch;
	@Override
	public void create() {
		gsm = new GameStageManage();
		batch = new SpriteBatch();    
		font = new BitmapFont();
        font.setColor(Color.RED);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.render();
		batch.begin();
        font.draw(batch, Game.allByte + ":" + Game.waitTime + ":" + Game.countPlayer, 10, 25);
        font.draw(batch, "Time remain: " + Game.timeRemain, 10, 45);
        batch.end();
	}
}
