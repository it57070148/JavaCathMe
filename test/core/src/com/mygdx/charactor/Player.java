package com.mygdx.charactor;

import com.badlogic.gdx.graphics.Texture;

public interface Player {
	
	public int getOldX();
	public int getOldY();
	public void setOldX(int x);
	public void setOldY(int y);
	public int getX();
	public void setX(int x);
	public int getY();
	public void setY(int y);
	public void plusX();
	public void plusY();
	public void plusX(int a);
	public void plusY(int a);
	public void move();
	public void chrRight(boolean isInvi);
	public void chrLeft(boolean isInvi);
	public void chrUp(boolean isInvi);
	public void chrDown(boolean isInvi);
	public void chrCatch();
	public int getrWidth();
	public void setrWidth(int rWidth);
	public int getrHeight();
	public void setrHeight(int rHeight);
	public void setDirection(int direction);
	public int getDirection();
	
	public void setInvi(boolean invi);
	public boolean getInvi();
	
	public Texture getTexture();
}
