package com.id_nan.gameEngine.Renderer;

import com.id_nan.gameEngine.engine.GameInstance;

public class RenderThread extends Thread {
	private final GamePanel panel;
	private final GameInstance game;

	public RenderThread(GamePanel panel, GameInstance game) {
		this.panel = panel;
		this.game = game;
	}

	@Override
	public void run() {
		int FPSCounter = 0;
		long timeNow;
		long lastUpdateTime = System.nanoTime();
		while (game.running) {
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
