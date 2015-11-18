package net.catchme.server;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Iterator;

import net.catchme.client.ClientData;
import net.catchme.packet.PacketHelper;
import net.catchme.packet.PlayPacket;

public class GameLobby {

	public static enum Maps {
		Island(0),
		TheMazeBeta(1);
		
		private int id;
		private Maps(int id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}
	}
	
	private static ArrayList<ClientData> client = new ArrayList<>();
	
	private static boolean isPlay;
	
	private static int countDown;
	private static boolean startCountDown;
	private static long startCountTime;
	
	private static long startPlayTime;
	private static int playTime;
	
	
	private static Maps map;
	
	public static boolean isStartCountDown() {
		return startCountDown;
	}

	public static void setStartCountDown(boolean startCountDown) {
		GameLobby.startCountDown = startCountDown;
	}

	public static void addClient(ClientData c) {
		client.add(c);
	}
	
	public synchronized static void removeClient(ClientData c) {
		Iterator<ClientData> iter = client.iterator();
		while(iter.hasNext()) {
			if (iter.next() == c) {
				iter.remove();
				break;
			}
		}
	}
	public static boolean isPlay() {
		return isPlay;
	}
	
	public static void setPlay(boolean isPlay) {
		if (isPlay) {
			broadcast(new PlayPacket().getData());
		}
		GameLobby.isPlay = isPlay;
	}
	
	public synchronized static void broadcast(String data) {
		for (ClientData cd: client) {
			PacketHelper.sendData(data, cd.getDp());
		}
	}
	
	public synchronized static void broadcast(DatagramPacket dp, String data) {
		for (ClientData cd: client) {
			if (cd.getDp().getAddress().getHostAddress().equals(dp.getAddress().getHostAddress()) 
					&& cd.getDp().getPort() == dp.getPort())
				continue;
			PacketHelper.sendData(data, cd.getDp());
		}
	}
	
	public static int count() {
		return client.size();
	}

	public static int getCountDown() {
		return countDown;
	}

	public static void setCountDown(int countDown) {
		GameLobby.countDown = countDown;
	}

	public static long getStartCountTime() {
		return startCountTime;
	}

	public static void setStartCountTime(long startCountTime) {
		GameLobby.startCountTime = startCountTime;
	}
	
	public synchronized static int countPolice() {
		int total = 0;
		for (ClientData cd: client) {
			if (cd.getType() == 0)
				total += 1;
		}
		return total;
	}
	
	public synchronized static int countThief() {
		int total = 0;
		for (ClientData cd: client) {
			if (cd.getType() == 1)
				total += 1;
		}
		return total;
	}

	public static Maps getMap() {
		return map;
	}

	public static void setMap(Maps map) {
		GameLobby.map = map;
	}

	public static long getStartPlayTime() {
		return startPlayTime;
	}

	public static void setStartPlayTime(long startPlayTime) {
		GameLobby.startPlayTime = startPlayTime;
	}

	public static int getPlayTime() {
		return playTime;
	}

	public static void setPlayTime(int playTime) {
		GameLobby.playTime = playTime;
	}
	
	public synchronized static void clear() {
		for (ClientData cd: client) {
			cd.setPlay(false);
			cd.setCatch(false);
		}
		client.clear();
	}
	
	public static boolean isCatchAll() {
		int total = 0;
		for (ClientData cd: client) {
			if (cd.isCatch() && cd.getType() == 1) {
				total += 1;
			}
		}
		return total == countThief();
	}
}
