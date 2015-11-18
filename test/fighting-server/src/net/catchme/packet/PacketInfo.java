package net.catchme.packet;

import java.net.DatagramPacket;

import net.catchme.packet.PacketManage.Type;
import net.catchme.server.GameLobby;

public class PacketInfo extends Packet {

	private long time;
	
	public PacketInfo() {
		time = (GameLobby.getStartPlayTime()+ GameLobby.getPlayTime()) - System.currentTimeMillis();
	}
	
	public PacketInfo(long time) {
		this.time = time;
	}
	@Override
	public void process(String data, DatagramPacket dp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getData() {
		return String.format("%c%d", Type.INFO.getId(), time);
	}

}
