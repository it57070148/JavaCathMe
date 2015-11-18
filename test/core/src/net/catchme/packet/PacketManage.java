package net.catchme.packet;

public class PacketManage {

	public static enum Type {

		UNKNOW((byte) -1), JOIN((byte) 0), MOVE((byte) 1), CATCH((byte) 2), LOGOUT((byte) 3), HANDCHECK((byte) 4), PLAY(
				(byte) 5), WAIT((byte) 6), INFO((byte) 7), END((byte) 8);

		private byte id;

		private Type(byte id) {
			this.id = id;
		}

		public byte getId() {
			return id;
		}
	}

	public static Type getType(byte id) {
		for (Type t : Type.values()) {
			if (t.id == id) {
				return t;
			}
		}
		return Type.UNKNOW;
	}
}
