package net.catchme.packet;

import java.net.DatagramPacket;

import net.catchme.client.Client;
import net.catchme.packet.PacketManage.Type;

public class PacketCatch extends Packet {

	@Override
	public void process(String data, DatagramPacket dp) {
		System.out.println(data);
		try {
			Client.get(Integer.parseInt(data)).setCatch(true);
		} catch (NullPointerException e) {
			e.getMessage();
		}
		PacketHelper.sendData(String.format("%c%s", Type.CATCH.getId(), data), Client.get(Integer.parseInt(data)).getDp());
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
