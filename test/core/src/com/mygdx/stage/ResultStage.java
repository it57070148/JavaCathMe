package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ResultStage extends Stage {

	SpriteBatch sp;
	ShapeRenderer sr;
	Texture texture;
	Rectangle r1;
	Sprite sprite;
	Vector2 mouse;
	public ResultStage(GameStageManage gsm) {
		super(gsm);
	
		sp = new SpriteBatch();
		sr = new ShapeRenderer();
		r1 = new Rectangle(200, 200, 200, 200);
		mouse = new Vector2();
		
	}
	@Override
	public void create() {
		
			texture = new Texture(Gdx.files.internal("Menu/Menu.png"));
			sprite = new Sprite(texture);
			Music mainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/Menu.mp3"));
			mainMusic.play();
			mainMusic.setLooping(true);
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

}
