package com.lud.openglengine.gameobject;

public class Component {
	public GameObject parent;
	
	public Component(GameObject parent) {
		this.parent = parent;
	}
	
	public void update() { }
}
