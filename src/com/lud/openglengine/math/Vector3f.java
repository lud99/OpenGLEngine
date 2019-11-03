package com.lud.openglengine.math;

import com.lud.openglengine.Engine;

public class Vector3f {
	
	public float x, y, z;
	
	public Vector3f() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
	}
	
	public Vector3f(float xyz) {
		this.x = xyz;
		this.y = xyz;
		this.z = xyz;
	}
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f(Vector3f vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	public Vector3f subtract(Vector3f value) {
		return new Vector3f(x - value.x, y - value.y, z - value.z);
	}
	
	public Vector3f add(Vector3f value) {
		return new Vector3f(x + value.x, y + value.y, z + value.z);
	}
	
	public Vector3f multiply(Vector3f value) {
		return new Vector3f(x * value.x, y * value.y, z * value.z);
	}
	
	public Vector3f multiply(float value) {
		return new Vector3f(x * value, y * value, z * value);
	}
	
	public float asMatrixX() {
		return x / Engine.windowWidth * Engine.projectionWidth;
	}
	
	public float asMatrixY() {
		return -y / Engine.windowHeight * Engine.projectionHeight;
	}
	
	public static Vector3f subtract(Vector3f vector1, Vector3f vector2) {
		return new Vector3f(vector1.x - vector2.x, vector1.y - vector2.y, vector1.z - vector2.z);
	}
	
	public static Vector3f add(Vector3f vector1, Vector3f vector2) {
		return new Vector3f(vector1.x + vector2.x, vector1.y + vector2.y, vector1.z + vector2.z);
	}
	
	public static Vector3f multiply(float vector1f, Vector3f vector2) {
		Vector3f vector1 = new Vector3f(vector1f);
		return new Vector3f(vector1.x * vector2.x, vector1.y * vector2.y, vector1.z * vector2.z);
	}
	
	public static Vector3f multiply(Vector3f vector1, Vector3f vector2) {
		return new Vector3f(vector1.x * vector2.x, vector1.y * vector2.y, vector1.z * vector2.z);
	}
}
