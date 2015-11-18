package com.mygdx.charactor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Police extends Texture implements Player {
	Task run;
	SpriteBatch batch;

	public int rWidth = 16;
	public int rHeight = 16;

	int chkPic = 0;
	int x, y;

	int oldX, oldY;
	
	int direction;
	
	private boolean isInvi;
	
	public Police(int x, int y) {
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
		oldX = this.x;
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		oldY = this.y;
		this.y = y;
	}

	public void plusX() {
		oldX = this.x;
		x += 1;
	}

	public void plusY() {
		oldY = this.y;
		y += 1;
	}

	public void plusX(int a) {
		oldX = this.x;
		x += a;
	}

	public void plusY(int a) {
		oldY = this.y;
		y += a;
	}

	public void move() {
		plusX();
		plusY();
	}

	public void chrRight(boolean isInvi) {
		this.isInvi = isInvi;
		direction = 4;
		if (isInvi) {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi8.png"), null, false));
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi7.png"), null, false));
			}
			chkPic++;
			;
		} else {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/8.png"), null, false));

			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/7.png"), null, false));

			}
			chkPic++;
			;
		}
	}

	public void chrLeft(boolean isInvi) {
		this.isInvi = isInvi;
		direction = 3;
		if (isInvi) {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi4.png"), null, false));
				
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi6.png"), null, false));
				
			}
			chkPic++;
			;
		}

		else {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/4.png"), null, false));
				
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/6.png"), null, false));
				
			}
			chkPic++;
			;
		}
	}

	public void chrUp(boolean isInvi) {
		this.isInvi = isInvi;
		direction = 1;
		if (isInvi) {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi10.png"), null, false));
				
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi11.png"), null, false));
				
			}
			chkPic++;
			;
		} else {

			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/11.png"), null, false));
				
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/12.png"), null, false));
				
			}
			chkPic++;
			;
		}
	}

	public void chrDown(boolean isInvi) {
		this.isInvi = isInvi;
		direction = 2;
		if (isInvi) {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi2.png"), null, false));
				
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/invi3.png"), null, false));
			
			}
			chkPic++;
			;
		}

		else {

			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/2.png"), null, false));
				
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("Police/3.png"), null, false));
				
			}
			chkPic++;
			;
		}
	}

	public void chrCatch() {

		super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("dead.png"), null, false));
		;
	}

	public Police run() {
		run = Timer.schedule(new Task() {
			@Override
			public void run() {
				// move();
			}
		}, 0.0f, 1 / 60f);
		return this;
	}

	public void fixOverFlow(int w, int h) {
		if (y > h || y < -super.getHeight()) {
			x = 0;
			y = 0;
		}
		if (x > w || x < -super.getWidth()) {
			x = 0;
			y = 0;
		}
	}

	public boolean isAnimation() {
		return run.isScheduled();
	}

	public void toggle() {
		if (run.isScheduled()) {
			run.cancel();
		} else {
			run();
		}
	}

	public int getrWidth() {
		return rWidth;
	}

	public void setrWidth(int rWidth) {
		this.rWidth = rWidth;
	}

	public int getrHeight() {
		return rHeight;
	}

	public void setrHeight(int rHeight) {
		this.rHeight = rHeight;
	}

	@Override
	public int getDirection() {
		return direction;
	}

	@Override
	public Texture getTexture() {
		return this;
	}

	@Override
	public int getOldX() {
		// TODO Auto-generated method stub
		return oldX;
	}

	@Override
	public int getOldY() {
		// TODO Auto-generated method stub
		return oldY;
	}

	@Override
	public void setOldX(int x) {
		oldX = x;
		
	}

	@Override
	public void setOldY(int y) {
		// TODO Auto-generated method stub
		oldY = y;
	}

	@Override
	public void setDirection(int direction) {
		this.direction = direction;		
	}
	@Override
	public void setInvi(boolean invi) {
		this.isInvi = invi;
	}

	@Override
	public boolean getInvi() {
		// TODO Auto-generated method stub
		return isInvi;
	}
}
