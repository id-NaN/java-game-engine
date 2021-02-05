package com.id_nan.gameEngine.UIObjects;

import com.id_nan.gameEngine.engine.GameInstance;

import java.awt.*;

public abstract class UIObject {
	protected GameInstance game;

	public UIObject(GameInstance game) {
		this.game = game;
	}

	public UIObject(GameInstance game, String argument) {
		this.game = game;
	}

	// called every tick
	public void tick() {}
	// called every frame
	public void draw(Graphics2D graphics) {}
}
