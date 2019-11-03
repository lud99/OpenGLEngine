package com.lud.openglengine.tests;

public class Pixel {
	public static float meter = 30f;
	
	public static float toMeter(float pixel) {
		return pixel / meter;
	}
}
