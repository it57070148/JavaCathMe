package com.mygdx.stage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;
import com.mygdx.stage.GameStageManage.Level;

import net.catchme.packet.HandCheckPacket;
import net.catchme.packet.PacketHelper;
import net.catchme.packet.PacketJoin;
import net.catchme.packet.PacketThread;

public class LobbyStage extends Stage {

	
	public LobbyStage(GameStageManage gsm) {
		super(gsm);
	}

	@Override
	public void create() {
		if (Game.packetThread == null) {
			Game.packetThread = new Thread(new PacketThread());
			Game.packetThread.start();
		}
		
		do {
			PacketHelper.sendPacket(new HandCheckPacket());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!Game.isConnected);
		do {
			PacketHelper.sendPacket(new PacketJoin(Game.type));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!Game.isJoin);
		
		Game.timeRemain = 99999;
		
	}

	@Override
	public void render() {
		if (Game.isPlay) {
			Game.isPlay = false;
			gsm.setStage(Level.PLAY);
		}
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
