package com.mygdx.charactor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Police extends Texture{
	Task run;
	SpriteBatch batch;
	
	public int rWidth = 12;
	public int rHeight = 12;
	
	int chkPic = 0;
	int x, y;
	
	public Police (int x, int y) {
		super("Police/10.png");
		this.x = x;
		this.y = y;
	}
	public Police(SpriteBatch batch) {
		super("assets/sun.jpg");
		this.batch = batch;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void plusX() {
		x += 1;
	}
	
	public void plusY() {
		y += 1;
	}
	
	public void plusX(int a) {
		x += a;
	}
	
	public void plusY(int a) {
		y += a;
	}
	public void move() {
		plusX();
		plusY();
	}
	
	public void chrRight(boolean isInvi) {
		if(isInvi){
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi8.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi7.png"), null, false));
			}
			chkPic++;
			;}		
		else{
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/8.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/7.png"), null, false));
			}
			chkPic++;
			;}
	}
	
	
	public void chrLeft(boolean isInvi) {
		if(isInvi){
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi4.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi6.png"), null, false));
			}
			chkPic++;
			;}

		
		else{
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/4.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/6.png"), null, false));
			}
			chkPic++;
			;}
	}

	public void chrUp(boolean isInvi) {
		if(isInvi){
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi11.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi12.png"), null, false));
			}
			chkPic++;
			;}		
		else{
		
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/11.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/12.png"), null, false));
			}
			chkPic++;
			;}
	}
	public void chrDown(boolean isInvi) {
		if(isInvi){
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi2.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi3.png"), null, false));
			}
			chkPic++;
			;}
		
		else{
		
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/2.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/3.png"), null, false));
			}
			chkPic++;
			;}
	}
	public void chrCatch() {
		
		
		
		super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("dead.png"), null, false));;
	}
	
	public Police run() {
		run = Timer.schedule(new Task(){
		    @Override
		    public void run() {
		    	//move();
		    }
		}, 0.0f, 1/60f);
		return this;
	}
	
	public void fixOverFlow(int w, int h) {
		if(y > h || y < -super.getHeight()) {
			x = 0;
			y = 0;
		}
		if(x > w || x < -super.getWidth()) {
			x = 0;
			y = 0;
		}
	}
	
	public boolean isAnimation() {
		return run.isScheduled();
	}
	public void toggle() {
		if(run.isScheduled()) {
			run.cancel();
		} else {
			run();
		}
	}

}
