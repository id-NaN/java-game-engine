package com.id_nan.gameEngine.util;

public class MathUtil {
	public static boolean isBetween(double x, double y, double z) {
		return x < y && y < z;
	}

	public static boolean isBetween(int x, int y, int z) {
		return x < y && y < z;
	}
}
