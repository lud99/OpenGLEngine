package com.lud.openglengine.math;

public class Bounds {
		
	public float left, right, top, bottom;
	
	public Bounds() {
		left = 0.0f;
		right = 0.0f;
		top = 0.0f;
		bottom = 0.0f;
	}
	
	public Bounds(float left, float right, float top, float bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}
}
