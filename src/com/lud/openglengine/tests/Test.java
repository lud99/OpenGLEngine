package com.lud.openglengine.tests;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import com.lud.openglengine.Engine;
import com.lud.openglengine.gameobject.GameObject;
import com.lud.openglengine.graphics.RGB;
import com.lud.openglengine.graphics.RGBA;
import com.lud.openglengine.math.Vector3f;
import com.lud.openglengine.math.physics.RigidbodyComponent;
import com.lud.openglengine.tests.level.Level;

public class Test extends GameObject {
	public Test(Vector3f position, float width, float height) {
		super(position, width, height);
		
		useTextures = false;
		color = new RGBA(50, 255, 0, 55);
		fragmentShaderPath = Engine.ROOT_PATH + "res/shaders/basic_color_circle.frag";
		shape = GameObject.Shapes.CIRCLE;
		
	//	rotationOffset = -90f;
		
		rigidbodyComponent = new RigidbodyComponent(this);
		
		Body body = new Body();
		//body.addFixture(Geometry.createEquilateralTriangle(Pixel.toMeter(width * 1.0f)/*, Pixel.toMeter(height * 2)*/),1f, 0f, 0.0f);
		
		body.addFixture(Geometry.createRectangle(Pixel.toMeter(width*2), Pixel.toMeter(height*2)), 1f, 0f, 0.5f);
		body.translate(Pixel.toMeter(position.x), Pixel.toMeter(position.y));
		body.setMass(MassType.INFINITE);
		body.setAngularDamping(5f);

		rigidbodyComponent.addRigidbody(body, width, height);
	}
}
