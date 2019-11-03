package com.lud.openglengine.graphics;

public class RGBA {
	public float r, g, b, a = 255f;
	
	public RGBA(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public RGBA(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 255;
	}
	
	public RGBA(RGB color) {
		r = color.r;
		g = color.g;
		b = color.b;
		a = 255;
	}
	
	public RGBA(RGB color, float a) {
		r = color.r;
		g = color.g;
		b = color.b;
		this.a = a;
	}
	
	public RGBA from0to1() {
		return new RGBA(r / 255, g / 255, b / 255, a / 255);
	}
	
	public RGBA from0to255() {
		return new RGBA(r * 255, g * 255, b * 255, a * 255);
	}
}
