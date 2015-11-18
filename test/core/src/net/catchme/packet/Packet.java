package net.catchme.packet;

public abstract class Packet {

	abstract public void process(String data);

	abstract public String getData();
}
