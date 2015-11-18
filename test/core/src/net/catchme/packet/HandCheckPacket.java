package net.catchme.packet;

import com.mygdx.game.Game;

import net.catchme.packet.PacketManage.Type;

public class HandCheckPacket extends Packet {

	@Override
	public void process(String data) {
		Game.isConnected = true;
		Game.clientId = Integer.parseInt(data);
	}

	@Override
	public String getData() {
		return String.format("%cTEST", Type.HANDCHECK.getId());
	}

}
