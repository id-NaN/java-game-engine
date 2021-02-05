package com.id_nan.gameEngine.engine;

import java.awt.*;

public class DefaultValues {
	private final GameInstance game;
	public final DFont font;

	// save GameInstance pointer and load the values
	public DefaultValues(GameInstance game) {
		this.game = game;

		this.font = new DFont();
	}

	public class DFont {
		private final String fontName;

		// load font name from config
		public DFont() {
			fontName = game.loadFromConfig("game/defaultFont");
		}

		// get the default font with specified font size
		public Font getFont(int fontSize) {
			return new Font(fontName, Font.PLAIN, fontSize);
		}
	}
}
