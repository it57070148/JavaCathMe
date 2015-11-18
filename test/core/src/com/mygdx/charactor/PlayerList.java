package com.mygdx.charactor;

import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Game;

import net.catchme.packet.PacketCatch;
import net.catchme.packet.PacketHelper;

public class PlayerList {

	private static HashMap<Integer, Player> player = new HashMap<Integer, Player>();

	public static void add(final int clientId, final int type, final int x, final int y, final int direction, final int hide) {
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				if (player.containsKey(clientId)) {
					move(clientId, x, y, direction, hide);
					return;
				}
				switch (Game.getCharType(type)) {
				case POLICE:
					add(clientId, new Police(x, y), direction, hide);
					break;
				case THIEF:
					add(clientId, new John(x, y), direction, hide);
					break;
				}
			}
		});

	}

	public static void add(int clientId, Player p, int direction, int hide) {
		if (player.containsKey(clientId)) {
			move(clientId, p.getX(), p.getY(), direction, hide);
			return;
		}
		player.put(clientId, p);
	}

	public static void move(int clientId, int x, int y, int direction, int hide) {
		if (!player.containsKey(clientId)) {
			return;
		}
		Player p = player.get(clientId);
		p.setX(x);
		p.setY(y);
		
		boolean h = (hide == 1) ? true : false;
		if (direction == 1) {
			p.chrUp(h);
		}
		if (direction ==2 ) {
			p.chrDown(h);
		}
		if (direction == 3) {
			p.chrLeft(h);
		}
		if (direction == 4) {
			p.chrRight(h);
		}
	}

	public static void draw(Batch batch, Matrix4 cam, Player pl) {
		Circle c = new Circle(pl.getX(), pl.getY(), 80);
		for (Entry<Integer, Player> p : player.entrySet()) {
			Player py = p.getValue();
			if (py.getInvi() && !c.contains(py.getX(), py.getY())) {
				System.out.println(py.getInvi());
				continue;
			}
			batch.begin();
			batch.setProjectionMatrix(cam);
			batch.draw(py.getTexture(), py.getX(), py.getY(), py.getrWidth(), py.getrHeight());
			batch.end();
		}

	}
	
	public static void checkCatch(Player p) {
		for (Entry<Integer, Player> py: player.entrySet()) {
			Player p2 = py.getValue();
			if (new Rectangle(p.getX(), p.getY(), p.getrWidth(), p.getrHeight()).overlaps(
				 new Rectangle(p2.getX(), p2.getY(), p2.getrWidth(), p2.getrHeight()))) {
				System.out.println("Catchhhhhh");
				PacketHelper.sendPacket(new PacketCatch(py.getKey()));
			}
		}
	}
}
