package com.mygdx.charactor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer.Task;

public class John extends Texture implements Player {

	Task run;
	SpriteBatch batch;

	private int rWidth = 16;
	private int rHeight = 16;

	int chkPic = 0;
	int x, y;

	int oldX, oldY;
	
	int direction;
	
	private boolean isInvi;
	
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
	
	@Override
	public void move() {
		plusX();
		plusY();
	}

	@Override
	public void chrRight(boolean isInvi) {
		this.isInvi = isInvi;
		direction = 4;
		if (isInvi) {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(
						TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_RightStand.png"), null, false));
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_RightT1.png"), null, false));
			}
			chkPic++;
			;
		} else {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/RightStand.png"), null, false));
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/RightT1.png"), null, false));
			}
			chkPic++;
			;
		}
	}

	@Override
	public void chrLeft(boolean isInvi) {
		this.isInvi = isInvi;
		direction = 3;
		if (isInvi) {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(
						TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_LeftStand.png"), null, false));
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_LeftT1.png"), null, false));
			}
			chkPic++;
			;
		}

		else {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/LeftStand.png"), null, false));
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/LeftT1.png"), null, false));
			}
			chkPic++;
			;
		}
	}

	@Override
	public void chrUp(boolean isInvi) {
		this.isInvi = isInvi;
		direction = 1;
		if (isInvi) {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_upT1.png"), null, false));
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_upT2.png"), null, false));
			}
			chkPic++;
			;
		} else {

			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/upT1.png"), null, false));
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/upT2.png"), null, false));
			}
			chkPic++;
			;
		}
	}

	@Override
	public void chrDown(boolean isInvi) {
		this.isInvi = isInvi;
		direction = 2;
		if (isInvi) {
			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_1downT.png"), null, false));
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/invi_2downT.png"), null, false));
			}
			chkPic++;
			;
		}

		else {

			if (chkPic > 10) {
				if (chkPic > 20) {
					chkPic = 0;
				}
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/1downT.png"), null, false));
			} else {
				chkPic++;
				super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("theif/2downT.png"), null, false));
			}
			chkPic++;
			;
		}
	}

	@Override
	public void chrCatch() {

		super.load(TextureData.Factory.loadFromFile(Gdx.files.internal("dead.png"), null, false));
		;
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

	public int getOldX() {
		return oldX;
	}

	public void setOldX(int oldX) {
		this.oldX = oldX;
	}

	public int getOldY() {
		return oldY;
	}

	public void setOldY(int oldY) {
		this.oldY = oldY;
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
