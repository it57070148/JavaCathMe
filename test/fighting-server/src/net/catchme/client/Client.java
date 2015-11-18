package net.catchme.client;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Iterator;

import net.catchme.packet.PacketHelper;
import net.catchme.server.GameLobby;
import net.catchme.server.Log;

public class Client {

	private static ArrayList<ClientData> client = new ArrayList<>();
	private static final int TIMEOUT = 10000;
	private static int clientId = 0;
	
	public synchronized static void add(DatagramPacket dp) {
		for (ClientData cd: client) {
			if (cd.getDp().getAddress().getHostAddress().equals(dp.getAddress().getHostAddress()) 
					&& cd.getDp().getPort() == dp.getPort()) {
				return;
			}
		}
		
		client.add(new ClientData(clientId, dp));
		clientId += 1;
	}
	
	public static final ArrayList<ClientData> getAll() {
		return client;
	}
	
	public synchronized static ClientData get(DatagramPacket dp) {
		
		for (ClientData cd: client) {
			if (cd.getDp().getAddress().getHostAddress().equals(dp.getAddress().getHostAddress()) 
					&& cd.getDp().getPort() == dp.getPort()) {
				return cd;
			}
		}
		
		return null;
	}
	
	public synchronized static ClientData get(int clientId) {
		
		for (ClientData cd: client) {
			if (cd.getClientId() == clientId) {
				return cd;
			}
		}
		
		return null;
	}
	
	public synchronized static void setJoin(DatagramPacket dp) {
		if (GameLobby.isPlay())
			return;
		
		
		for (ClientData cd: client) {
			if (cd.getDp().getAddress().getHostAddress().equals(dp.getAddress().getHostAddress()) 
					&& cd.getDp().getPort() == dp.getPort()) {
				if (cd.isPlay())
					return;
				cd.setPlay(true);
				GameLobby.addClient(cd);
				return;
			}
		}
	}
	
	public synchronized static void setPos(DatagramPacket dp) {
		
	}
	
	public synchronized static void broadcast(String data) {
		for (ClientData cd: client) {
			PacketHelper.sendData(data, cd.getDp());
		}
	}
	
	public synchronized static void update(DatagramPacket dp) {
		for (ClientData cd: client) {
			if (cd.getDp().getAddress().getHostName().equals(dp.getAddress().getHostAddress()) 
					&& cd.getDp().getPort() == dp.getPort()) {
				cd.setLastRecv(System.currentTimeMillis());
				break;
			}
		}
	}
	
	public synchronized static void updateInfo(DatagramPacket dp, int type, int x, int y, int direction) {
		for (ClientData cd: client) {
			if (cd.getDp().getAddress().getHostName().equals(dp.getAddress().getHostAddress()) 
					&& cd.getDp().getPort() == dp.getPort()) {
				cd.setType(type);
				cd.setX(x);
				cd.setY(y);
				cd.setDirection(direction);
				break;
			}
		}
	}
	
	public synchronized static void removeTimeout() {
		Iterator<ClientData> iter = client.iterator();
		
		while(iter.hasNext()) {
			ClientData cd = iter.next();
			if (cd.getLastRecv() + TIMEOUT < System.currentTimeMillis()) {
				Log.info(String.format("%s:%d is timeout.", cd.getDp().getAddress().getHostAddress(),
						cd.getDp().getPort()));
				if (cd.isPlay()) {
					GameLobby.removeClient(cd);
				}
				iter.remove();
			}
		}
	}
}
