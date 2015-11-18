package net.catchme.packet;

import java.net.DatagramPacket;

import net.catchme.client.Client;
import net.catchme.packet.PacketManage.Type;
import net.catchme.server.GameLobby;
import net.catchme.server.Log;

public class PacketMove extends Packet {

	private int clientId;
	private int type;
	private int x;
	private int y;
	private int direction;
	private int hide;

	public PacketMove() {
		
	}
	
	public PacketMove(int clientId, int type, int x, int y, int direction, int hide) {
		this.clientId = clientId;
		this.type = type;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.hide = hide;

	}
	
	@Override
	public void process(String data, DatagramPacket dp) {
		if (Client.get(dp).isPlay())
			GameLobby.broadcast(dp, String.format("%c%s", Type.MOVE.getId(), data));
	}

	@Override
	public String getData() {
		return String.format("%c%d,%d,%d,%d,%d", Type.MOVE.getId(), clientId, type, x, y, direction);
	}

}
