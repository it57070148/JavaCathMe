package net.catchme.packet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PacketHelper {
	
	private static DatagramSocket socket;
	
	public static void load(DatagramSocket socket) {
		PacketHelper.socket = socket;
	}
	
	public static void sendPacket(Packet packet, DatagramPacket dp) {
		sendData(packet.getData(), dp);
	}
	
	public static void sendData(String data, DatagramPacket dp) {

		try {
			DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, dp.getAddress(), dp.getPort());
			socket.send(packet);
		} catch (IOException e) {
			System.out.println("Send data to server error.");
			e.printStackTrace();
		}
	}
	
	public static void broadcast(String data) {
		
	}
	
	public static void lobbyBroadcast(String data) {
		
	}
}
