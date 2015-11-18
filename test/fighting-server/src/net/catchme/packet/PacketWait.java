package net.catchme.packet;

import java.net.DatagramPacket;

import net.catchme.packet.PacketManage.Type;
import net.catchme.server.GameLobby;

public class PacketWait extends Packet {

	/**
	 * 
	 * NUM:TIME
	 */
	
	@Override
	public void process(String data, DatagramPacket dp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getData() {
		return String.format("%c%d,%d", Type.WAIT.getId(), GameLobby.count(),
				(GameLobby.getStartCountTime() + GameLobby.getCountDown()) - System.currentTimeMillis());
	}

}
