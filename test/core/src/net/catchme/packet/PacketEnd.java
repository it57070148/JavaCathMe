package net.catchme.packet;

import java.net.DatagramPacket;

import com.mygdx.game.Game;

import net.catchme.packet.PacketManage.Type;

public class PacketEnd extends Packet {

	@Override
	public String getData() {
		return String.format("%c", Type.END.getId());
	}

	@Override
	public void process(String data) {
		System.out.println("Game end");
		Game.timeRemain = -1;		
	}

}
