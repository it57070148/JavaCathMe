package net.catchme.packet;

import java.net.DatagramPacket;

import net.catchme.packet.PacketManage.Type;

public class PacketEnd extends Packet {

	@Override
	public void process(String data, DatagramPacket dp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getData() {
		return String.format("%ca", Type.END.getId());
	}

}
