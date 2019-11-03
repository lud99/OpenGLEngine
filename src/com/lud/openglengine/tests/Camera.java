package com.lud.openglengine.tests;

import com.lud.openglengine.Engine;
import com.lud.openglengine.gameobject.GameObject;
import com.lud.openglengine.math.Bounds;
import com.lud.openglengine.math.MathPlus;
import com.lud.openglengine.math.Matrix4f;
import com.lud.openglengine.math.Vector3f;

public class Camera {
	public static Vector3f position = new Vector3f();
	public static float rotation = 0f;
	
	private static Bounds bounds;
	
	public static Matrix4f vwMatrix() {
		return Matrix4f.translate(position.multiply(new Vector3f(-1 * Engine.scale, -1 * Engine.scale, 1))).multiply(Matrix4f.rotate(rotation));
	}
	
	public static void followX(GameObject object, float speed) {
		position.x += (((object.position.x - Engine.baseResolutionWidth / 2f) - position.x + Engine.baseResolutionWidth / 2f) * speed);
		
		if (bounds != null) {	
			float left = position.x - Engine.baseResolutionWidth / 2f;
			float boundsRight = bounds.right - Engine.baseResolutionWidth;
			
			position.x = MathPlus.clamp(left, bounds.left, boundsRight) + Engine.baseResolutionWidth / 2f;
		}
	}
	
	public static void followY(GameObject object, float speed) {
		position.y += (((object.position.y - Engine.baseResolutionHeight / 2f) - position.y + Engine.baseResolutionHeight / 2f) * speed);
		
		if (bounds != null) {	
			float top = position.y - Engine.baseResolutionHeight / 2f;
			float boundsBottom = bounds.bottom - Engine.baseResolutionHeight;
			
			position.y = MathPlus.clamp(top, bounds.top, boundsBottom) + Engine.baseResolutionHeight / 2f;
		}
	}
	
	public static void follow(GameObject object, float speed) {
		followX(object, speed);
		followY(object, speed);
	}
	
	public static void setBounds(Bounds bounds) {
		Camera.bounds = bounds;
	}
	
	public static Bounds getBounds() {
		return bounds;
	}
}
