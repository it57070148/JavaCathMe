package com.mygdx.charactor;

public class PoliceData {

	public int rWidth = 16;
	public int rHeight = 16;

	int chkPic = 0;
	int x, y;

	int oldX, oldY;
	
	int direction;
	
	private boolean isInvi;
	
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
}
