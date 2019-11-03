package com.lud.openglengine.tests;

import static org.lwjgl.glfw.GLFW.*;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import com.lud.openglengine.Engine;
import com.lud.openglengine.gameobject.GameObject;
import com.lud.openglengine.graphics.Tileset;
import com.lud.openglengine.input.Input;
import com.lud.openglengine.math.Vector3f;
import com.lud.openglengine.math.physics.RigidbodyComponent;
import com.lud.openglengine.tests.level.Level;
import com.sun.javafx.scene.traversal.Direction;

public class Player extends GameObject {
	private float density = 0.001f/2;
	
	private float movementSpeed = 1500f * 0.01f / Level.tileSize / 2, jumpForce = -5000f * Pixel.meter *0.01f / Level.tileSize / 2;
	
	Body body;
	
	float time = 0f;
	
	public Player(Vector3f position, float width, float height) {
		super(position, width, height);
		
		useTextures = true;
		
		//color = new RGBA(100, 0, 0, 255);
		texturePath = Engine.ROOT_PATH + "res/images/tiles/grass.png";
		
		textureCoordinatesScale.x = -1f;
		
		//fragmentShaderPath = Engine.ROOT_PATH + "res/shaders/background.frag";
		
		//textureRepeat = true;
		
		/* Physics */
		
		rigidbodyComponent = new RigidbodyComponent(this);

		body = new Body();
		body.addFixture(Geometry.createRectangle(Pixel.toMeter(width*2), Pixel.toMeter(height*2)), density, 0.25f, 0f);
		body.translate(Pixel.toMeter(position.x), Pixel.toMeter(position.y));
		body.setMass(MassType.NORMAL);
		body.setAngularDamping(1000);
				
		rigidbodyComponent.velocityInterpolation = new Vector2(0.5f, 0.5f);
		
		rigidbodyComponent.addRigidbody(body, width, height);
		rigidbodyComponent.alwaysActive = true;
		
		/* Normal (Looking left) */
		
		tcs = new float[] {
			0, 1, 
			0, 0,
			1, 0,
			1, 1
		};
		
		/* Flipped (Looking right) */
		
		/*tcs = new float[] {
			0, 1, 
			0, 0,
			-1, 0,
			-1, 1
		};*/
	}
	
	public void update() {		
		Vector2 force = new Vector2(0, 0);
		if (Input.isKeyDown(GLFW_KEY_A)) 
			force.x = -movementSpeed;
		if (Input.isKeyDown(GLFW_KEY_D))
			force.x = movementSpeed;
		if (Input.isKeyDown(GLFW_KEY_W)) 
			force.y = -movementSpeed;
		if (Input.isKeyDown(GLFW_KEY_S)) 
			force.y = movementSpeed;
		
		if (Input.isKeyPressed(GLFW_KEY_SPACE)) {
			force.y = jumpForce;
			System.out.println("JUMP!!");
		}
		
		if (Input.isKeyDown(GLFW_KEY_E)) {
			/*float[] t = new float[] {
				0, 1, 
				0, 0,
				1, 0,
				1, 1
			};
			
			getMesh().modifyTextureCoordinates(t);*/
		}
			

		rigidbodyComponent.rigidbody.applyForce(force);
	}
	
	public void fixedUpdate() {time+=0.005f;}
	
	/*public void onRender() {
		getShader().setUniform1f("time", time);
		getShader().setUniform2f("speed", 1f, 0f);
	}*/
}
