package net.catchme.packet;

import java.net.DatagramPacket;

import net.catchme.client.Client;
import net.catchme.packet.PacketManage.Type;
import net.catchme.server.GameLobby;

public class JoinPacket extends Packet {

	@Override
	public void process(String data, DatagramPacket dp) {
		Client.setJoin(dp);

		Client.get(dp).setType(Integer.parseInt(data));
		PacketHelper.sendData(getData(), dp);
	}

	@Override
	public String getData() {
		return String.format("%cok", Type.JOIN.getId());
	}

}
