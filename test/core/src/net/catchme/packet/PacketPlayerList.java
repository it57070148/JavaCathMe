package net.catchme.packet;

public class PacketPlayerList extends Packet {

	/**
	 * ClientId,Type,
	 */
	
	@Override
	public void process(String data) {
		String[] dat = data.split(":");
	}

	@Override
	public String getData() {
		return null;
	}

}
