package com.id_nan.gameEngine.Renderer;

import com.id_nan.gameEngine.UIObjects.UIObject;
import com.id_nan.gameEngine.engine.GameInstance;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GamePanel extends Canvas {
	private final GameInstance game;
	private final RenderThread thread;

	public final boolean clearScreen;

	public GamePanel(GameInstance game) {
		this.game = game;
		clearScreen = Boolean.parseBoolean(game.loadFromConfig("renderer/panel/refreshScreen"));

		thread = new RenderThread(this, game);
	}

	public void startThread() {
		thread.start();
	}

	public void draw() {
		BufferStrategy bufferStrategy = getBufferStrategy();
		if (bufferStrategy == null) {
			createBufferStrategy(2);
			return;
		}

		Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();

		if (clearScreen) {
			Dimension size = getSize();
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, size.width, size.height);
		}

		for (UIObject object : game.uiObjects) {
			if (object != null) {
				object.draw(graphics);
			}
		}

		graphics.dispose();
		bufferStrategy.show();
	}
}
