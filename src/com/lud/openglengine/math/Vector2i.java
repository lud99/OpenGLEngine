package com.lud.openglengine.math;

import com.lud.openglengine.Engine;

public class Vector2i {
	
	public int x, y, z;
	
	public Vector2i() {
		x = 0;
		y = 0;
	}
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public float asMatrixX() {
		return x / Engine.windowWidth * Engine.projectionWidth;
	}
	
	public float asMatrixY() {
		return -y / Engine.windowHeight * Engine.projectionHeight;
	}
}
