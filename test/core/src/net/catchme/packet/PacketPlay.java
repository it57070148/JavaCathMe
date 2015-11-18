package net.catchme.packet;

import com.mygdx.game.Game;

public class PacketPlay extends Packet {

	@Override
	public void process(String data) {
		if (Game.isPlay) {
			return;
		}
		Game.isPlay = true;
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
