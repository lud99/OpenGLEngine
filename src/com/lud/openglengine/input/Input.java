package com.lud.openglengine.input;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {
	
	public static int[] keys = new int[65536];
	
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = action;
	}
	
	public static boolean isKeyPressed(int keycode) {
		//If the specified key was pressed
		boolean isPressed = keys[keycode] == GLFW_PRESS;
		if (isPressed) { 
			//Release the key
			keys[keycode] = GLFW_RELEASE;
		}
		 
		return isPressed;
	}
	
	public static boolean isKeyDown(int keycode) {
		return keys[keycode] != GLFW_RELEASE;
	}
}
