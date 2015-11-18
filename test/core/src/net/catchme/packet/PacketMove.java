package net.catchme.packet;

import com.mygdx.charactor.PlayerList;

import net.catchme.packet.PacketManage.Type;

public class PacketMove extends Packet {

	/**
	 * 
	 * CliendId,Type,X,Y,Direction
	 */

	private int clientId;
	private int type;
	private int x;
	private int y;
	private int direction;
	private int hide;
	public PacketMove() {
		
	}
	
	public PacketMove(int clientId, int type, int x, int y, int direction, int hide) {
		this.clientId = clientId;
		this.type = type;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.hide = hide;
	}
	
	@Override
	public void process(String data) {
		String[] data_ = data.split(",");
		int clientId = Integer.parseInt(data_[0]);
		int type = Integer.parseInt(data_[1]);
		int x = Integer.parseInt(data_[2]);
		int y = Integer.parseInt(data_[3]);
		int direction = Integer.parseInt(data_[4]);
		int hide = Integer.parseInt(data_[5]);
		PlayerList.add(clientId, type, x, y, direction, hide);
	}

	@Override
	public String getData() {
		return String.format("%c%d,%d,%d,%d,%d,%d", Type.MOVE.getId(), clientId, type, x, y, direction, hide);
	}

}
