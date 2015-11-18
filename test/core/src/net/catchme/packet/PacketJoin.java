package net.catchme.packet;

import com.mygdx.game.Game;

import net.catchme.packet.PacketManage.Type;

public class PacketJoin extends Packet {

	private int type;

	public void joinPacket(int type) {

	}

	public PacketJoin() {

	}

	public PacketJoin(int type) {
		this.type = type;
	}

	@Override
	public void process(String data) {
		if (data.equals("ok")) {
			Game.isJoin = true;
		}
	}

	@Override
	public String getData() {
		return String.format("%c%s", Type.JOIN.getId(), type);
	}

}
