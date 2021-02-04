package com.id_nan.gameEngine.UIObjects;

import com.id_nan.gameEngine.engine.GameInstance;

import java.awt.*;

public class TestObject extends UIObject {
	private Rectangle rectangle;

	public TestObject(GameInstance game, String argument) {
		super(game, argument);

		String[] arguments = argument.split(", ");

		rectangle = new Rectangle(
				Integer.parseInt(arguments[0]),
				Integer.parseInt(arguments[1]),
				Integer.parseInt(arguments[2]),
				Integer.parseInt(arguments[3])
		);
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.GRAY);
		graphics.fill(rectangle);
	}
}
