package net.catchme.server;

import net.catchme.packet.PacketEnd;
import net.catchme.packet.PacketHelper;
import net.catchme.packet.PacketInfo;
import net.catchme.packet.PacketWait;

public class Lobby implements Runnable {

	private int player;
	
	public void run() {
		while (true) {
			if (GameLobby.isStartCountDown()) {
				if (GameLobby.countThief() < 1 || GameLobby.countPolice() < 1) {
					GameLobby.setStartCountDown(false);
					continue;
				}
				if (GameLobby.getStartCountTime() + GameLobby.getCountDown() < System.currentTimeMillis()) {
					GameLobby.setPlay(true);
					GameLobby.setStartCountDown(false);
					
					GameLobby.setStartPlayTime(System.currentTimeMillis());
					GameLobby.setPlayTime(30000);
				}
				GameLobby.broadcast(new PacketWait().getData());
				System.out.println((GameLobby.getStartCountTime() + GameLobby.getCountDown()) - System.currentTimeMillis());
				
			}
			
			if (GameLobby.isPlay()) {
				if ( GameLobby.isCatchAll() || (GameLobby.getStartPlayTime() + GameLobby.getPlayTime()) < System.currentTimeMillis()) {
					GameLobby.setPlay(false);
					GameLobby.broadcast(new PacketEnd().getData());
					GameLobby.broadcast(new PacketEnd().getData());
					GameLobby.broadcast(new PacketEnd().getData());
					GameLobby.broadcast(new PacketEnd().getData());
					GameLobby.broadcast(new PacketEnd().getData());
					GameLobby.clear();
					
					System.out.println("End ja" + GameLobby.isCatchAll());
					continue;
				}
				GameLobby.broadcast(new PacketInfo().getData());
			} else {
				if (GameLobby.countThief() >= 1 && GameLobby.countPolice() >=1 && player != GameLobby.count()) {
					GameLobby.setCountDown(3000);
					GameLobby.setStartCountDown(true);
					GameLobby.setStartCountTime(System.currentTimeMillis());
				}
			}
			player = GameLobby.count();
			// System.out.println(GameLobby.countThief() + ":" + GameLobby.countPolice());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
