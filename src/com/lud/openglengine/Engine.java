package com.lud.openglengine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.lud.openglengine.input.Input;
import com.lud.openglengine.input.Mouse;
import com.lud.openglengine.gameobject.GameObject;
import com.lud.openglengine.graphics.RGBA;
import com.lud.openglengine.math.Matrix4f;
import com.lud.openglengine.room.Room;
import com.lud.openglengine.room.RoomManager;
import com.lud.openglengine.utils.BufferUtilsGL;

public class Engine implements Runnable {
	
	public static String ROOT_PATH = "src/com/lud/openglengine/";
	
	public static int windowWidth = 1280;
	public static int windowHeight = 720;
	public static int baseResolutionWidth = 960/2;
	public static float baseResolutionHeight = 540/2;
	public static float projectionWidth = 10;
	public static float projectionHeight = 10.0f * 9.0f / 16.0f;
	
	public static float scale = 1f;
	
	public static String windowName = "";
	
	public static int fixedUpdateFPS = 100;
	
	public static boolean useAntialiasing = false;
	
	public static boolean logFPS = true;
	
	public static Matrix4f pr_matrix;
	
	public static RGBA backgroundColor = new RGBA(0, 0, 0);
	
	private boolean running = false;
	private boolean logToFile = true;
	private boolean useDeltaTime = true;
	private boolean vSync = true;
	
	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private static int FPS = 0;
	
	private Thread thread;
	
	private static long window;
	
	public Engine(int windowWidth, int windowHeight, String windowName) {
		Engine.windowWidth = windowWidth;
		Engine.windowHeight = windowHeight;
		
		Engine.windowName = windowName;
		
		start();
	}
	
	private void start() {
		running = true;
		
		thread = new Thread(this, windowName);
		thread.start();
	}
	
	private void init() {
		if (logToFile) {
			PrintStream out;
			try {
				out = new PrintStream(new FileOutputStream("log.txt"));
				System.setOut(out);
				System.setErr(out);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		if (!glfwInit()) {
			int error = glGetError();
			if (error != GL_NO_ERROR)
				System.out.println("Error: " + error);
			return;
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		if (!vSync) glfwWindowHint(GLFW_DOUBLEBUFFER, GL_FALSE);
		window = glfwCreateWindow(windowWidth, windowHeight, windowName, NULL, NULL);
				
		if (window == NULL) 
			return;
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - windowWidth) / 2, (vidmode.height() - windowHeight) / 2);
		
		glfwSetKeyCallback(window, new Input());
		glfwSetMouseButtonCallback(window, new Mouse());
		
		glfwMakeContextCurrent(window);
		if (!vSync) glfwSwapInterval(0);
		glfwShowWindow(window);
		GL.createCapabilities();
		
		glEnable(GL_DEPTH_TEST);
		glActiveTexture(GL_TEXTURE1);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		System.out.println("OpenGL: " + glGetString(GL_VERSION));		
		
		pr_matrix = Matrix4f.orthographic(-projectionWidth, projectionWidth, -projectionHeight, projectionHeight, -1.0f, 1.0f);
	}
	
	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		double delta = 0.0;
		float elapsedTime = 1f;
		double ns = 1000000000.0 / fixedUpdateFPS;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			update();
			
			if (delta >= 1.0 || !useDeltaTime) {
				fixedUpdate(elapsedTime);
				updates++;
				delta--;
			}
			
			render();
			
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				elapsedTime++;
				
				timer += 1000;
				
				FPS = frames;
				
				if (logFPS) System.out.println(updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
			
			if (glfwWindowShouldClose(window))
				running = false;
		}
		
		glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	private void update() {
		glfwPollEvents();
		
		RoomManager.update();
	}
	
	private void fixedUpdate(float elapsedTime) {
		RoomManager.fixedUpdate(elapsedTime);
	}
	
	private void render() {
		glClearColor(backgroundColor.from0to1().r, backgroundColor.from0to1().g, backgroundColor.from0to1().b, backgroundColor.from0to1().a);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		RoomManager.render();
		
		//Check for OpenGl errors
		int error = glGetError();
		if (error != GL_NO_ERROR)
			System.out.println("Error: " + error);
		
		if (!vSync) 
			glFlush();
		else 
			glfwSwapBuffers(window);
	}
	
	public static void setWindowTitle(String title) {
		glfwSetWindowTitle(window, title);
		
		windowName = title;
	}
	
	public static int getFPS() {
		return FPS;
	}
	
	public static Matrix4f getPrMatrix() { return pr_matrix; }
}
