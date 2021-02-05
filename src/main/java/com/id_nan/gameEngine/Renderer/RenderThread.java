package com.id_nan.gameEngine.Renderer;

import com.id_nan.gameEngine.engine.GameInstance;

public class RenderThread extends Thread {
	private final GamePanel panel;
	private final GameInstance game;

	// store GameInstance and GamePanel pointer
	public RenderThread(GamePanel panel, GameInstance game) {
		this.panel = panel;
		this.game = game;
	}

	@SuppressWarnings("BusyWait")
	@Override
	public void run() {
		// count frames to announce every second
		int FPSCounter = 0;
		// create current time variable
		long timeNow;
		// save the time at which the last frame happened
		long lastUpdateTime = System.nanoTime();
		//start main loop
		while (game.running) {
			// save time at start of tick
			timeNow = System.nanoTime();

			// update FPS value
			FPSCounter++;
			if (lastUpdateTime + 1_000_000_000 < timeNow) {
				game.timing.actualFPS = FPSCounter;
				FPSCounter = 0;
				lastUpdateTime += 1_000_000_000;
				System.out.println(game.timing.actualFPS);
			}

			panel.draw();

			// wait for next tick
			long waitTime = game.timing.nsPerFrame - (System.nanoTime() - timeNow);
			try {
				Thread.sleep(waitTime / 1_000_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
