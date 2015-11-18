package net.catchme.packet;

import java.net.DatagramPacket;

import net.catchme.packet.PacketManage.Type;

public class PlayPacket extends Packet {

	@Override
	public void process(String data, DatagramPacket dp) {
		
	}

	@Override
	public String getData() {
		return String.format("%cPLAY", Type.PLAY.getId());
	}

}
