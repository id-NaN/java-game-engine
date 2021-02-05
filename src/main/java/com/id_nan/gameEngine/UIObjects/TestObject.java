package com.id_nan.gameEngine.UIObjects;

import com.id_nan.gameEngine.engine.GameInstance;
import com.id_nan.gameEngine.engine.SceneLoadable;
import com.id_nan.gameEngine.engine.events.EventListener;
import com.id_nan.gameEngine.engine.events.MainEventHandler;

import java.awt.*;

// example for an UIObject utilising events and the argument string
// creates a gray box that says "bye from TestObject" when the window is closed
@SceneLoadable
public class TestObject extends UIObject implements EventListener {
	private final Rectangle rectangle;

	public TestObject(GameInstance game, String argument) {
		super(game, argument);

		String[] arguments = argument.split(", ");

		rectangle = new Rectangle(
				Integer.parseInt(arguments[0]),
				Integer.parseInt(arguments[1]),
				Integer.parseInt(arguments[2]),
				Integer.parseInt(arguments[3])
		);

		game.eventHandler.registerListener(this);
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.GRAY);
		graphics.fill(rectangle);
	}

	@Override
	public void onEvent(int eventID, Object event) {
		if (eventID == MainEventHandler.EVENT_GAME_CLOSED) {
			System.out.println("bye from TestObject");
		}
	}
}
