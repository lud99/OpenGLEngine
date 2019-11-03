package com.lud.openglengine.gameobject;

import org.dyn4j.geometry.Vector2;

import com.lud.openglengine.Engine;
import com.lud.openglengine.math.Vector3f;

public class Background extends GameObject {
	
	protected float time = 0f;
	
	protected float scale = 1f;
	
	protected Vector2 scrollSpeed = new Vector2(0, 0);
	
	public Background(Vector3f position, float width, float height, float scale) {
		super(position, width, height);
		
		this.scale = scale;

		init();
	}
	
	public Background(Vector3f position, float width, float height) {
		super(position, width, height);

		init();
	}
	
	public void fixedUpdate() {
		time += 0.001f;
	}
	
	public void onRender() {
		getShader().setUniform1f("time", time);
		getShader().setUniform2f("speed", scrollSpeed);
	}
	
	private void init() {
		
		fragmentShaderPath = Engine.ROOT_PATH + "res/shaders/basic_background.frag";
		
		/* Modify the size of the texture */
		tcs = new float[] {
			0, scale, 
			0, 0,
			scale, 0,
			scale, scale
		};
	}
}
