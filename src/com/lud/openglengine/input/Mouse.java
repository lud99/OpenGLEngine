package com.lud.openglengine.input;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Mouse extends GLFWMouseButtonCallback{

	public static int[] buttons = new int[16];

	public void invoke(long window, int button, int action, int mods) {
		buttons[button] = action;
	}
	
	public static boolean isButtonPressed(int button) {
		//If the specified key was pressed
		boolean isPressed = buttons[button] == GLFW_PRESS;
		if (isPressed) { 
			//Release the button
			buttons[button] = GLFW_RELEASE;
		}
		 
		return isPressed;
	}
	
	public static boolean isButtonDown(int button) {
		return buttons[button] != GLFW_RELEASE;
	}

}
