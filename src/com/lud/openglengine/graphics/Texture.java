package com.lud.openglengine.graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.lud.openglengine.Engine;
import com.lud.openglengine.utils.BufferUtilsGL;
import com.lud.openglengine.utils.FileUtils;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
	
	private int width, height;
	private int texture;
	
	private boolean repeat = false;
	
	public Texture(String path) {
		texture = load(path);
	}
	
	public Texture(String path, int width, int height) {
		this.width = width;
		this.height = height;
		
		texture = load(path);
	}
	
	public Texture(String path, boolean repeat) {
		this.repeat = repeat;
		
		texture = load(path);
	}
	
	public Texture(int[] data, int width, int height) {
		/*this.width = width;
		this.height = height;*/
		
		texture = generate(data);
	}
	
	private int load(String path) {		
		BufferedImage image = FileUtils.loadAsImage(path);
		
		width = image.getWidth();
		height = image.getHeight();
		
		int[] pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		
		int[] data = new int[width * height];
		
		for (int i = 0; i < width * height; i++) {
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}
				
		return generate(data);
	}
	
	private int generate(int[] data) {
		int result = glGenTextures();
		
		glBindTexture(GL_TEXTURE_2D, result);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, Engine.useAntialiasing ? GL_LINEAR : GL_NEAREST /*GL_LINEAR is Anti-aliasing turned on, GL_NEAREST is Anti-aliasing turned off*/);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, Engine.useAntialiasing ? GL_LINEAR : GL_NEAREST );
	    
		if (repeat) {
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		}
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtilsGL.createIntBuffer(data));
		glBindTexture(GL_TEXTURE_2D, 0);
		int error = glGetError();
		if (error != GL_NO_ERROR)
			System.out.println("Error: " + error);
		
		return result;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, texture);
	}
	
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
}
