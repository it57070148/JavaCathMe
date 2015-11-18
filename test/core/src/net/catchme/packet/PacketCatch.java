package net.catchme.packet;

import com.mygdx.charactor.PlayerList;
import com.mygdx.stage.PlayStage;

import net.catchme.packet.PacketManage.Type;

public class PacketCatch extends Packet {

	private int clientId;
	
	public PacketCatch() {
		
	}
	
	public PacketCatch(int clientId) {
		this.clientId = clientId;
	}
	
	@Override
	public void process(String data) {
		System.out.println("Get catch");
		PlayStage.player.setX(-5000);
		PlayStage.player.setY(-5000);
	}

	@Override
	public String getData() {
		return String.format("%c%d", Type.CATCH.getId(), clientId);
	}

}
