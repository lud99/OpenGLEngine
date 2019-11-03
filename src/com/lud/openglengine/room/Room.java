package com.lud.openglengine.room;

import java.util.ArrayList;

import org.dyn4j.dynamics.World;

import com.lud.openglengine.gameobject.GameObject;
import com.lud.openglengine.math.Bounds;

public class Room {
	
	private float width, height;
	
	private Bounds bounds = new Bounds();
	
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private World world = new World();
	
	/*public Room(Bounds bounds) {
		this.width = width;
		this.height = height;
		
		bounds = new Bounds(-width/2f, width/2f, -height/2, height/2);
	}*/
	
	public Room(Bounds bounds) {
		this.bounds = bounds;
		
		width = Math.abs(bounds.left) + Math.abs(bounds.right);
		height = Math.abs(bounds.top) + Math.abs(bounds.bottom);
	}
	
	public void addObject(GameObject object) {
		gameObjects.add(object);
	}
	
	public World getWorld() { return world; }
	
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public float getWidth() { return width; }
	public float getHeight() { return height; }
	
	public Bounds getBounds() { return bounds; }
	
	public void setWidth(float width) { this.width = width; }
	public void setHeight(float height) { this.height = height; }
	
	public void setBounds(Bounds bounds) { this.bounds = bounds; }
}
