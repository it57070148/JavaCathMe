package net.catchme.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import net.catchme.client.Client;
import net.catchme.packet.HandCheckPacket;
import net.catchme.packet.JoinPacket;
import net.catchme.packet.PacketCatch;
import net.catchme.packet.PacketHelper;
import net.catchme.packet.PacketManage;
import net.catchme.packet.PacketMove;

public class Server {
	
	private int port;
	private DatagramSocket sock = null;
	private Thread server;
	private PacketManage pm;
	
	private Thread gameLobby;
	private Thread findTimeout;
	public Server(int port) {
		this.port = port;
		pm = new PacketManage();
		try {
			sock = new DatagramSocket(port);
			PacketHelper.load(sock);
			server = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						byte[] buffer = new byte[65536];
						DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
						connected(packet);
					}
				}
			});
			
			gameLobby = new Thread(new Lobby());
			
			findTimeout = new Thread(new Runnable() {
				public void run() {
					while(true) {
						//Client.removeTimeout();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			server.start();
			gameLobby.start();
			findTimeout.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	private void connected(final DatagramPacket packet) {
		String msg = readMsg(packet);
		
		byte code = msg.getBytes()[0];
		String data = msg.substring(1);
		
		// System.out.println("Client: " + packet.getAddress() + ":" + packet.getPort());
		Client.update(packet);
		switch(PacketManage.getType(code)) {
		
			case HANDCHECK:
				new HandCheckPacket().process(data, packet);
				break;
			case JOIN:
				new JoinPacket().process(data, packet);
				break;
			case MOVE:
				new PacketMove().process(data, packet);
				break;
			case CATCH:
				new PacketCatch().process(data, packet);
				break;
			default:
				System.out.println("Unknow packet: " + msg);
				break;
		}
	}
	
	public String readMsg(DatagramPacket packet) {
		try {
			sock.receive(packet);
			byte[] data = packet.getData();
			String s = new String(data, 0, packet.getLength());
			return s;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
