package com.lud.openglengine.room;

import java.util.ArrayList;

import com.lud.openglengine.gameobject.GameObject;
import com.lud.openglengine.math.Bounds;

public class RoomManager {
	
	private static Room currentRoom;
	
	public static Room createRoom(Bounds bounds) {
		return new Room(bounds);
	}
	
/*	public static Room loadRoom(String path) {
		return new Room(2000, 2000);
	}
	*/
	public static void setActiveRoom(Room room) {
		currentRoom = room;
	}
	
	public static void update() {
		if (currentRoom == null) return;
		
		ArrayList<GameObject> gameObjects = currentRoom.getGameObjects();
		
		//System.out.println("Size: " + gameObjects.size());
		
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject gameObject = gameObjects.get(i);
			
			if (!gameObject.hasRunInit) {
				gameObject._init();
				
				//System.out.println("Running init " + gameObject.getClass());
			}
			
			gameObject.update();
			gameObject.updateComponents();
		}
		
		for (int i = 0; i < gameObjects.size(); i++) 
			gameObjects.get(i).lateUpdate();
	}
	
	public static void fixedUpdate(float elapsedTime) {
		if (currentRoom == null) return;
		
		ArrayList<GameObject> gameObjects = currentRoom.getGameObjects();
		
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).fixedUpdate(elapsedTime);
			//gameObjects.get(i).updateComponentsFixed();
			gameObjects.get(i).fixedUpdate();
		}
	}
	
	public static void render() {
		if (currentRoom == null) return;
		
		ArrayList<GameObject> gameObjects = currentRoom.getGameObjects();
		
		for (int i = 0; i < gameObjects.size(); i++) 
			gameObjects.get(i).render();
	}
	
	public static Room getActiveRoom() { return currentRoom; }
}
