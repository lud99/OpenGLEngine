package com.lud.openglengine.math;

public class MathPlus {
	public static float clamp(float val, float min, float max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	public static double clamp(double val, double min, double max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	public static float lerp(float a, float b, float f) {
	    return a + f * (b - a);
	}
	
	public static double lerp(double a, double b, double f) {
	    return a + f * (b - a);
	}
	
	public static float lerp(float a, double b, double f) {
	    return (float) (a + f * (b - a));
	}
}
