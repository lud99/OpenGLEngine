package com.lud.openglengine.graphics;

public class RGB {
	public float r, g, b;
	
	public RGB(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public RGB from0to1() {
		return new RGB(r / 255, g / 255, b / 255);
	}
	
	public RGB from0to255() {
		return new RGB(r * 255, g * 255, b * 255);
	}
}
