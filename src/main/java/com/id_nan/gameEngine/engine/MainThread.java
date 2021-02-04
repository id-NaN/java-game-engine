package com.id_nan.gameEngine.engine;

public class MainThread extends Thread {
	GameInstance game;

	public MainThread(GameInstance game) {
		this.game = game;
	}

	@Override
	public void run() {
		int TPSCounter = 0;
		long timeNow;
		long lastUpdateTime = System.nanoTime();
		while (game.running) {
			timeNow = System.nanoTime();

			// update TPS value
			TPSCounter++;
			if (lastUpdateTime + 1_000_000_000 < timeNow) {
				game.timing.actualTPS = TPSCounter;
				TPSCounter = 0;
				lastUpdateTime += 1_000_000_000;
				System.out.println(game.timing.actualTPS);
			}

			game.tick();

			// wait for next tick
			long waitTime = game.timing.nsPerTick - (System.nanoTime() - timeNow);
			try {
				Thread.sleep(waitTime / 1_000_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// update deltaTime
			game.timing.deltaTime = (System.nanoTime() - timeNow) / 1_000_000_000d;
		}
	}
}
