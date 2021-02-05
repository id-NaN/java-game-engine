package com.id_nan.gameEngine.Renderer;

import com.id_nan.gameEngine.UIObjects.UIObject;
import com.id_nan.gameEngine.engine.GameInstance;
import com.id_nan.gameEngine.engine.events.MainEventHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;

// AWT panel for the game
public class GamePanel extends Canvas {
	private final GameInstance game;
	private final RenderThread thread;

	public final boolean clearScreen;

	// create RenderThread, save GameInstancePointer and
	public GamePanel(GameInstance game) {
		this.game = game;
		// load from config if the screen should be cleared every frame
		clearScreen = Boolean.parseBoolean(game.loadFromConfig("renderer/panel/refreshScreen"));

		thread = new RenderThread(this, game);
	}

	// start the render thread
	public void startThread() {
		thread.start();
	}

	public void draw() {

		// get buffer strategy
		BufferStrategy bufferStrategy = getBufferStrategy();
		if (bufferStrategy == null) {
			createBufferStrategy(2);
			return;
		}

		// get graphics
		Graphics2D graphics;
		try {
			graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
		} catch (IllegalStateException e) {
			return;
		}

		// clear screen if needed
		if (clearScreen) {
			Dimension size = getSize();
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, size.width, size.height);
		}

		// call render function for every UIObject
		for (UIObject object : game.uiObjects) {
			if (object != null) {
				object.draw(graphics);
			}
		}

		// dispose of graphics and show BufferStrategy
		graphics.dispose();
		bufferStrategy.show();
	}
}
