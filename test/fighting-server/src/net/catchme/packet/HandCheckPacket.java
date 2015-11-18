package net.catchme.packet;

import java.net.DatagramPacket;

import net.catchme.client.Client;
import net.catchme.client.ClientData;
import net.catchme.packet.PacketManage.Type;

public class HandCheckPacket extends Packet {

	private DatagramPacket dp;
	@Override
	public void process(String data, DatagramPacket dp) {

		Client.add(dp);
		this.dp = dp;
		PacketHelper.sendData(getData(), dp);
	}

	@Override
	public String getData() {
		return String.format("%c%d", Type.HANDCHECK.getId(), Client.get(dp).getClientId());
	}

}
