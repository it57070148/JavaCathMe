package com.mygdx.stage;

public class GameStageManage {
	
	enum Level {
		HOME,
		PLAY
	}
	
	private Stage stage;;
	
	public GameStageManage() {
		setStage(Level.HOME);
	}
	
	public void render() {
		stage.render();
	}
	
	public void setStage(Level level) {
		if (stage != null) {
			stage.dispose();
		}
		
		switch(level) {
			case HOME:
				stage = new HomeStage(this);
				break;
			case PLAY:
				stage = new PlayStage(this);
				break;
		}
		
		stage.create();
	}
}
