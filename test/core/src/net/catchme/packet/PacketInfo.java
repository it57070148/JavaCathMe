package net.catchme.packet;

import com.mygdx.game.Game;

public class PacketInfo extends Packet {

	@Override
	public void process(String data) {
		Game.timeRemain = Long.parseLong(data);
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
