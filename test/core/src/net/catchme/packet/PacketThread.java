package net.catchme.packet;

import com.mygdx.game.Game;

public class PacketThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			String msg = PacketHelper.readMsg();
			Game.allByte += msg.getBytes().length;
			byte code = msg.getBytes()[0];
			String data = msg.substring(1);

			switch (PacketManage.getType(code)) {

			case HANDCHECK:
				new HandCheckPacket().process(data);
				break;
			case JOIN:
				new PacketJoin().process(data);
				break;
			case PLAY:
				new PacketPlay().process(data);
				break;
			case MOVE:
				new PacketMove().process(data);
				break;
			case WAIT:
				new PacketWait().process(data);
				break;
			case CATCH:
				new PacketCatch().process(data);
				break;
			case INFO:
				new PacketInfo().process(data);
				break;
			case END:
				new PacketEnd().process(data);
				break;
			default:
				break;
			}
		}
	}
}
