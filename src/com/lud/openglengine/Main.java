package com.lud.openglengine;

import com.lud.openglengine.graphics.RGBA;
import com.lud.openglengine.math.Bounds;
import com.lud.openglengine.room.Room;
import com.lud.openglengine.room.RoomManager;
import com.lud.openglengine.tests.Pixel;
import com.lud.openglengine.tests.level.Level;

public class Main {
	
	public static int baseResolutionWidth = 128, baseResolutionHeight = 72;
	
	public void start() {	
		Room room = RoomManager.createRoom(new Bounds(-200f, 200f, -500f, Engine.baseResolutionHeight/2f));
		
		RoomManager.setActiveRoom(room);
		
		new Level();
	}
		
	public static void main(String[] args) {
		float scale = 14f;
		
		int width = (int)(baseResolutionWidth * scale);
		int height = (int)(baseResolutionHeight * scale);
		
		new Engine(width, height, "Test Game");
		
		Engine.baseResolutionWidth = baseResolutionWidth;
		Engine.baseResolutionHeight = baseResolutionHeight;
		
		Engine.scale = scale;
		
		Pixel.meter = 10f;
		
		Engine.backgroundColor = new RGBA(0, 0, 5);
		
		Engine.logFPS = false;
		
		new Main().start();
	}
}
