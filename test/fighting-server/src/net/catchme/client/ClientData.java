package net.catchme.client;

import java.net.DatagramPacket;

public class ClientData {

	private DatagramPacket dp;
	
	private long lastRecv;
	private int x, y;
	private int type;
	
	private boolean isPlay = false;
	private int clientId;
	private int direction;
	
	private boolean isCatch = false;
	public ClientData(int clientId, DatagramPacket dp) {
		this.dp = dp;
		this.clientId = clientId;
	}
	
	public final DatagramPacket getDp() {
		return dp;
	}
	
	public final int getClientId() {
		return clientId;
	}

	public long getLastRecv() {
		return lastRecv;
	}

	public void setLastRecv(long lastRecv) {
		this.lastRecv = lastRecv;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isPlay() {
		return isPlay;
	}

	public void setPlay(boolean isPlay) {
		this.isPlay = isPlay;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isCatch() {
		return isCatch;
	}

	public void setCatch(boolean isCatch) {
		this.isCatch = isCatch;
	}
}
