package com.id_nan.gameEngine;


import com.id_nan.gameEngine.Renderer.GamePanel;
import com.id_nan.gameEngine.Renderer.GameWindow;
import com.id_nan.gameEngine.engine.GameInstance;

public class Main {
	// create GameInstance and window
	public static void main(String[] args) {
		new GameWindow(new GameInstance());
	}
}
