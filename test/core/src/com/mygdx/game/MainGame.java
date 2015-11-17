package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.stage.GameStageManage;

public class MainGame extends ApplicationAdapter {

	GameStageManage gsm;
	
	public void create() {
		gsm = new GameStageManage();
	}
	
	public void render() {
		gsm.render();
	}
}
