package com.id_nan.gameEngine.engine;

public class TimeManager {
	public int FPS;
	public int actualFPS;
	public long nsPerFrame;
	public int TPS;
	public int actualTPS;
	public long nsPerTick;

	public double deltaTime;

	public TimeManager(GameInstance game) {
		setFPS(Integer.parseInt(game.loadFromConfig("timing/FPS")));
		setTPS(Integer.parseInt(game.loadFromConfig("timing/TPS")));
	}

	public void setFPS(int newFPS) {
		FPS = newFPS;
		nsPerFrame = 1_000_000_000 / newFPS;
	}

	public void setTPS(int newTPS) {
		TPS = newTPS;
		nsPerTick = 1_000_000_000 / newTPS;
	}
}
