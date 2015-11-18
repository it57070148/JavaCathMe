package net.catchme.packet;

import java.net.DatagramPacket;

public abstract class Packet {
	
	abstract public void process(String data, DatagramPacket dp);
	
	abstract public String getData();
}
