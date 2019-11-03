package com.lud.openglengine.tests.level;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import com.lud.openglengine.Engine;
import com.lud.openglengine.gameobject.GameObject;
import com.lud.openglengine.graphics.RGBA;
import com.lud.openglengine.math.Vector3f;
import com.lud.openglengine.math.physics.RigidbodyComponent;
import com.lud.openglengine.tests.Pixel;

public class Solid extends GameObject {
	public Solid(Vector3f position, float width, float height) {
		super(position, width, height);
		
		useTextures = false;
		color = new RGBA(50f, 50f, 50f, 255);
		
		rigidbodyComponent = new RigidbodyComponent(this);
		
		Body ground = new Body();
		ground.addFixture(Geometry.createRectangle(Pixel.toMeter(width*2f*Engine.scale), Pixel.toMeter(height*2*Engine.scale)), 1f, 0.25f, 0.0f);
		ground.translate(Pixel.toMeter(position.x), Pixel.toMeter(position.y));
		ground.setGravityScale(0);
		ground.setMass(MassType.FIXED_LINEAR_VELOCITY);
		
		ground.setActive(true);
		ground.setAsleep(true);
		
		rigidbodyComponent.addRigidbody(ground, width, height);
		
		rigidbodyComponent.isStatic = true;
	}
}
