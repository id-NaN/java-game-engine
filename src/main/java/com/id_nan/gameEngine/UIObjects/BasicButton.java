package com.id_nan.gameEngine.UIObjects;

import com.id_nan.gameEngine.engine.GameInstance;
import com.id_nan.gameEngine.engine.SceneLoadable;
import com.id_nan.gameEngine.engine.events.EventListener;
import com.id_nan.gameEngine.engine.events.MainEventHandler;
import com.id_nan.gameEngine.util.MathUtil;

import java.awt.*;
import java.awt.event.MouseEvent;

// example for an UIObject utilising events and the argument string
// detects if you are hovering above it and fills itself with grey if you are
@SceneLoadable
public class BasicButton extends UIObject implements EventListener {
	private Rectangle drawRectangle;
	private boolean hovering;

	public BasicButton(GameInstance game, String argument) {
		super(game);
		game.eventHandler.registerListener(this);

		String[] argumentList = argument.split(", ");
		drawRectangle = new Rectangle(
				Integer.parseInt(argumentList[0]),
				Integer.parseInt(argumentList[1]),
				Integer.parseInt(argumentList[2]),
				Integer.parseInt(argumentList[3])
		);
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.GRAY);
		if (hovering) {
			graphics.fill(drawRectangle);
		}
		graphics.draw(drawRectangle);
	}

	@Override
	public void onEvent(int eventID, Object event) {
		if (eventID == MainEventHandler.EVENT_MOUSE_MOVE) {

			MouseEvent mouseEvent;
			try {
				mouseEvent = (MouseEvent) event;
			} catch (ClassCastException e) {
				return;
			}
			//noinspection SuspiciousNameCombination
			hovering = MathUtil.isBetween(drawRectangle.x, mouseEvent.getX(), drawRectangle.x + drawRectangle.width) && MathUtil.isBetween(drawRectangle.y, mouseEvent.getY(), drawRectangle.y + drawRectangle.height);
		}
	}
}
