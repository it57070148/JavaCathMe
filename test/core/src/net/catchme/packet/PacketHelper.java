package net.catchme.packet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.mygdx.game.Game;

public class PacketHelper {

	private static InetAddress host;
	private static DatagramSocket socket = null;

	static {
		try {
			host = InetAddress.getByName(Game.HOST);
			socket = new DatagramSocket();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sendPacket(Packet packet) {
		sendData(packet.getData());
	}

	public static void sendData(final String data) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, host,
							Game.port);
					socket.send(packet);
				} catch (IOException e) {
					System.out.println("Send data to server error.");
					e.printStackTrace();
				}
			}
		}).start();

	}

	public static String readMsg() {
		try {
			byte[] buffer = new byte[65536];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			socket.receive(reply);
			byte[] data = reply.getData();
			String s = new String(data, 0, reply.getLength());
			return s;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
