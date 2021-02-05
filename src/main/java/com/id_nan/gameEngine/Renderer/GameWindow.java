package com.id_nan.gameEngine.Renderer;

import com.id_nan.gameEngine.engine.GameInstance;

import javax.swing.JFrame;
import java.awt.*;

public class GameWindow extends JFrame {
	private final GamePanel panel;
	private final GameInstance game;

	public GameWindow(GameInstance game) {
		super();

		// store GameInstance pointer
		this.game = game;

		// load size from config
		setSize(new Dimension(
				Integer.parseInt(game.loadFromConfig("renderer/frame/size/x")),
				Integer.parseInt(game.loadFromConfig("renderer/frame/size/y"))
		));

		// load size and if fullscreen from config
		setFullscreen(Boolean.parseBoolean(game.loadFromConfig("renderer/frame/fullScreen")));
		setResizable(Boolean.parseBoolean(game.loadFromConfig("renderer/frame/resizable")));

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new GamePanel(game);

		// add panel to JFrame, and start everything
		add(panel);
		setVisible(true);
		panel.startThread();

		// register the MainEventHandler
		registerListeners();
	}

	// set fullscreen based on boolean
	public void setFullscreen(boolean b) {
		if (b) {
			setExtendedState(MAXIMIZED_BOTH);
		} else {
			setExtendedState(NORMAL);
		}
		setUndecorated(b);
	}

	// register MainEventHandler
	public void registerListeners() {
		panel.addKeyListener(game.eventHandler);
		panel.addMouseListener(game.eventHandler);
		panel.addMouseMotionListener(game.eventHandler);
		panel.addMouseWheelListener(game.eventHandler);
		addWindowListener(game.eventHandler);
		addWindowFocusListener(game.eventHandler);
		addWindowStateListener(game.eventHandler);
	}
}
