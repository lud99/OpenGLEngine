package com.lud.openglengine.graphics;

import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

import org.dyn4j.geometry.Vector2;

import com.lud.openglengine.math.Matrix4f;
import com.lud.openglengine.math.Vector3f;
import com.lud.openglengine.utils.ShaderUtils;

public class Shader {
	
	public static final int VERTEX_ATTRIB = 0;
	public static final int TCOORD_ATTRIB = 1;
		
	private boolean enabled = false;

	private final int ID;
	private Map<String, Integer> locationCache = new HashMap<String, Integer>();
	
	public Shader(String vertex, String fragment) {
		ID = ShaderUtils.load(vertex, fragment);
	}
	
	public Shader(int ID) {
		this.ID = ID;
	}
		
	public int getUniform(String name) {
		if (locationCache.containsKey(name)) 
			return locationCache.get(name);
		
		int result = glGetUniformLocation(ID, name);
		
		if (result == -1) 
			System.err.println("Could not find uniform valiable '" + name + "'");
		else 
			locationCache.put(name, result);
		
		return result;
	}
	
	public void setUniform1i(String name, int value) {
		if (!enabled) enable();
		glUniform1i(getUniform(name), value);
	}
	
	public void setUniform1f(String name, float value) {
		if (!enabled) enable();
		glUniform1f(getUniform(name), value);
	}
	
	public void setUniform2f(String name, float x, float y) {
		if (!enabled) enable();
		glUniform2f(getUniform(name), x, y);
	}
	
	public void setUniform2f(String name, Vector2 vector) {
		if (!enabled) enable();
		glUniform2f(getUniform(name), (float)vector.x, (float)vector.y);
	}
	
	public void setUniform3f(String name, Vector3f vector) {
		if (!enabled) enable();
		glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}
		
	public void setUniformMat4f(String name, Matrix4f matrix) {
		if (!enabled) enable();
		glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
	}
	
	public void setColor3f(String name, RGB color) {
		if (!enabled) enable();
		glUniform3f(getUniform(name), color.r, color.g, color.b);
	}
	
	public void setColor4f(String name, RGBA color) {
		if (!enabled) enable();
		glUniform4f(getUniform(name), color.r, color.g, color.b, color.a);
	}
	
	public void enable() {
		glUseProgram(ID);
		enabled = true;
	}
	
	public void disable() {
		glUseProgram(0);
		enabled = false;
	}
	
	public int getId() {
		return ID;
	}
}
