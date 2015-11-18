package net.catchme.server;

import java.time.LocalDateTime;

public class Log {

	public static void info(String data) {
		System.out.printf("%s: %s %n", LocalDateTime.now(), data);
	}
}
