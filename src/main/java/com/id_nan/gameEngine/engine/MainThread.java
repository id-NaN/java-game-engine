package com.id_nan.gameEngine.engine;

public class MainThread extends Thread {
	GameInstance game;

	// save gameInstance pointer
	public MainThread(GameInstance game) {
		this.game = game;
	}

	@SuppressWarnings("BusyWait")
	@Override

	public void run() {
		// count ticks to announce every second
		int TPSCounter = 0;
		// create current time variable
		long timeNow;
		// save the time at which the last tick happened
		long lastUpdateTime = System.nanoTime();
		//start main loop
		while (game.running) {
			// save time at start of tick
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
			if (waitTime > 0) {
				try {
					Thread.sleep(waitTime / 1_000_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
            // update deltaTime
			game.timing.deltaTime = (System.nanoTime() - timeNow) / 1_000_000_000d;
		}
	}
}
