package com.lud.openglengine.tests.level;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import com.lud.openglengine.Engine;
import com.lud.openglengine.gameobject.GameObject;
import com.lud.openglengine.graphics.Tileset;
import com.lud.openglengine.math.Vector3f;
import com.lud.openglengine.math.physics.RigidbodyComponent;
import com.lud.openglengine.tests.Pixel;
import com.lud.openglengine.tests.Tilesets;

public class Ground extends GameObject {
	
	private float tempWidth, tempHeight;
	
	public Ground(Vector3f position, float width, float height) {
		super(position, width, height);
		
		vertexShaderPath = Engine.ROOT_PATH + "res/shaders/basic_tileset.vert";
		fragmentShaderPath = Engine.ROOT_PATH + "res/shaders/basic_tileset.frag";
		
		rigidbodyComponent = new RigidbodyComponent(this);
		
		Body ground = new Body();
		ground.addFixture(Geometry.createRectangle(Pixel.toMeter(width*2f), Pixel.toMeter(height*2)), 1f, 0.25f, 0.0f);
		ground.translate(Pixel.toMeter(position.x), Pixel.toMeter(position.y));
		ground.setGravityScale(0);
		ground.setMass(MassType.FIXED_LINEAR_VELOCITY);
		
		ground.setActive(true);
		ground.setAsleep(true);
		
		rigidbodyComponent.addRigidbody(ground, width, height);
		
		rigidbodyComponent.isStatic = true;
	}
	
	public void onBeforeInit() {
		useTileset = true;
		tilesetIndex = 2;
		tileset = Tilesets.Grass;
		
		tempWidth = width;
		tempHeight = height;
		
		width =  tileset.getWidth() / 2;
		height = tileset.getHeight() / 2;
	}
	
	public void start() {
		width = tempWidth;
		height = tempHeight;
	}
}
