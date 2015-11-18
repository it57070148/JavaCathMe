package net.catchme.packet;

import com.mygdx.game.Game;

public class PacketWait extends Packet {

	@Override
	public void process(String data) {
		String[] data_ = data.split(",");
		Game.countPlayer = Integer.parseInt(data_[0]);
		Game.waitTime = Long.parseLong(data_[1]);
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
