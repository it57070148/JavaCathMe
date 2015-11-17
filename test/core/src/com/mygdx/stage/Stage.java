package com.mygdx.stage;

public abstract class Stage {

	public GameStageManage gsm;
	
	public Stage(GameStageManage gsm) {
		this.gsm = gsm;
	}
	
	abstract public void create();
	abstract public void render();
	abstract public void dispose();
	
}
