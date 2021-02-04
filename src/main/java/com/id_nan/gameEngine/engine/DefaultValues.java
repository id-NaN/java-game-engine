package com.id_nan.gameEngine.engine;

import java.awt.*;

public class DefaultValues {
	private final GameInstance game;
	public final DFont font;

	public DefaultValues(GameInstance game) {
		this.game = game;

		this.font = new DFont();
	}

	public class DFont {
		private final String fontName;

		public DFont() {
			fontName = game.loadFromConfig("game/defaultFont");
		}

		public Font getFont(int fontSize) {
			return new Font(fontName, Font.PLAIN, fontSize);
		}
	}
}
