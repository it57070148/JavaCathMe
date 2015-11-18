package com.mygdx.game;

import java.util.HashMap;

import com.mygdx.charactor.Player;

public class Game {
	final public static String title = "Last Man Standing";
	final public static int width = 960;
	final public static int height = 540;
	final public static boolean debug = true;

	final public static String HOST = "127.0.0.1";
	final public static int port = 9001;

	public static int type = 1;

	public static int clientId;
	public static boolean isJoin = false;
	public static boolean isConnected = false;
	public static boolean isPlay = false;
	
	public static Thread packetThread;

	public static long allByte = 0;
	
	public static long waitTime;
	public static int countPlayer;
	
	public static long timeRemain;
	
	public static enum CharType {
		POLICE(0), THIEF(1);
		private int id;

		private CharType(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}
	
	public static CharType getCharType(int id) {
		for (CharType c: CharType.values()) {
			if (c.getId() == id)
				return c;
		}
		return CharType.POLICE;
	}
	
}
