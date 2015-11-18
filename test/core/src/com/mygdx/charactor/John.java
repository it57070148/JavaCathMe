package com.mygdx.charactor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class John extends Texture{
	
	Task run;
	SpriteBatch batch;
	
	public int rWidth = 16;
	public int rHeight = 16;
	
	int chkPic = 0;
	int x, y;
	
	public John(int x, int y) {
		super("theif/Tdown1.png");
		this.x = x;
		this.y = y;
	}
	
	public John(SpriteBatch batch) {
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
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_RightStand.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_RightT1.png"), null, false));
			}
			chkPic++;
			;}		
		else{
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/RightStand.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/RightT1.png"), null, false));
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
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_LeftStand.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_LeftT1.png"), null, false));
			}
			chkPic++;
			;}

		
		else{
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/LeftStand.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/LeftT1.png"), null, false));
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
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_upT1.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_upT2.png"), null, false));
			}
			chkPic++;
			;}		
		else{
		
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/upT1.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/upT2.png"), null, false));
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
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_1downT.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_2downT.png"), null, false));
			}
			chkPic++;
			;}
		
		else{
		
			if(chkPic >10){
				if(chkPic > 20){
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/1downT.png"), null, false));
			}
			else{
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/2downT.png"), null, false));
			}
			chkPic++;
			;}
	}
	public void chrCatch() {
		
		
		
		super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("dead.png"), null, false));;
	}
	
	public John run() {
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
