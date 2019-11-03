package com.lud.openglengine.graphics;

public class Animation {
	
	private Texture[] textures;
	private int[] frames;
	
	private float speed = 1f;
	
	private int passedTime = 0, timer = 0;
	
	private int currentFrame = 0;
	
	public Animation(String[] texturePaths) {
		int[] autoFrames = new int[texturePaths.length];
		for (int i = 0; i < autoFrames[i]; i++)
			autoFrames[i] = i;
		
		init(texturePaths, autoFrames);
	}
	
	public Animation(String[] texturePaths, int[] frames) {
		init(texturePaths, frames);
	}
	
	private void init(String[] texturePaths, int[] frames) {
		//Create all textures
		textures = new Texture[texturePaths.length];
		
		for (int i = 0; i < texturePaths.length; i++) {
			textures[i] = new Texture(texturePaths[i]);
		}
		
		this.frames = frames; 
	}
	
	public void render(VertexArray mesh) {
		Texture texture = textures[currentFrame];
		
		texture.bind();
		mesh.render();
		texture.unbind();
	}
	
	public void render(VertexArray mesh, int frame) {
		Texture texture = textures[frame];
		
		texture.bind();
		mesh.render();
		texture.unbind();
	}
		
	public void play(VertexArray mesh) {
		Texture texture = textures[currentFrame];
		
		texture.bind();
		mesh.render();
		texture.unbind();
		
		if (timer >= speed) {
			currentFrame = frames[passedTime % frames.length];
			
			passedTime++;
			
			timer = 0;
		} else {
			timer++;		
		}
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}
	
}
