package com.lud.openglengine.tests.level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.ContinuousDetectionMode;
import org.dyn4j.dynamics.Settings;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;

import com.lud.openglengine.Engine;
import com.lud.openglengine.Main;
import com.lud.openglengine.gameobject.GameObject;
import com.lud.openglengine.math.Bounds;
import com.lud.openglengine.math.Vector3f;
import com.lud.openglengine.room.Room;
import com.lud.openglengine.room.RoomManager;
import com.lud.openglengine.tests.Camera;
import com.lud.openglengine.tests.Pixel;
import com.lud.openglengine.tests.Player;
import com.lud.openglengine.tests.Tilesets;

public class Level extends GameObject {
		
	public static ArrayList<Ground> groundTiles = new ArrayList<Ground>();
	
	public static Player player;
	
	public static int tileSize = 8;
	
	public static Vector2 maximumVelocity = new Vector2(Pixel.toMeter(600), Pixel.toMeter(1500));
	
	public Level() {
		//Generate as empty object
		super(GameObject.States.EMPTY);
		
		Room room = RoomManager.getActiveRoom();
				
		World world = room.getWorld();
		
		world.setGravity(new Vector2(0, 1*9.81f * 12 * Pixel.meter));
		world.setUserData(new Vector2(Engine.baseResolutionWidth*1.5f, Engine.baseResolutionWidth*1.5f));
		
		Settings settings = new Settings();
		settings.setContinuousDetectionMode(ContinuousDetectionMode.ALL);
		settings.setStepFrequency(0.005);
		settings.setAutoSleepingEnabled(true);
		settings.setMaximumRotation(360*1.5);
		
		world.setSettings(settings);
		
		int groundAmount = 100;
	
		//Create all ground tiles
		for (float i = -groundAmount/2f; i <= groundAmount/2f; i++) {
			Ground groundTile = new Ground(new Vector3f(i * tileSize * 1.99f, Main.baseResolutionHeight-tileSize, 0), tileSize, tileSize);
			
			//groundTile.texturePath = Engine.ROOT_PATH + "res/images/tiles/grass-top.png";
			
			//groundTile.fragmentShaderPath = Engine.ROOT_PATH + "res/shaders/basic_texture.frag";
			
			groundTiles.add(groundTile);
		}/*
		
		for (int y = 1; y < 5; y++) {
			
			Ground groundTile = new Ground(new Vector3f(-2f * tileSize * 1.99f, Main.baseResolutionHeight-tileSize*y*2+tileSize, 0), tileSize, tileSize);
			
		groundTile.texturePath = Engine.ROOT_PATH + "res/images/tiles/grass-top.png";
			
			groundTile.fragmentShaderPath = Engine.ROOT_PATH + "res/shaders/basic_texture.frag";
			
			groundTiles.add(groundTile);
		}*/
		
		new Ground(new Vector3f(0, Main.baseResolutionHeight-tileSize*2, 0), tileSize, tileSize);
				
		player = new Player(new Vector3f(0f, Main.baseResolutionHeight-tileSize*8, 0.1f), tileSize*1.0f, tileSize*1.0f);
		
		new StarsBackground(new Vector3f(0, 1, 0), room.getWidth(), room.getWidth(), 7.5f);
				
		Camera.setBounds(room.getBounds());
		
		//Camera.position.x = -100f;
	}
	
	public void onBeforeInit() {
		Tilesets.loadAll();
	}
	
	public void fixedUpdate(float elapsedTime) {
	//	System.out.println("Fixed update, " + elapsedTime);
	//	System.out.println(elapsedTime);
		//world.getSettings().setStepFrequency(0.5f);
			//for (int i = 0; i < 100; i++) world.step(i);
		RoomManager.getActiveRoom().getWorld().update(elapsedTime);
		
		/*if (Input.isKeyPressed(GLFW_KEY_K)) {
			t = 0f;
		
			robot.mouseMove(100, 100);
			robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
			robot.keyPress(KeyEvent.VK_D);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			robot.mouseMove(100, 800);
			robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
			//robot.keyPress(KeyEvent.VK_O);
		} else if (t > 100 * 1f ){
			robot.keyRelease(KeyEvent.VK_D);
			robot.keyRelease(KeyEvent.VK_O);
		}
		System.out.println(t);
		t++;*/

	}
	
	public void update() {
		//Camera.position.x = player.position.x;
	//	Camera.follow(player, 0.05f);
		
		Engine.setWindowTitle("Test Game | FPS: " + Integer.toString(Engine.getFPS()));
	}
}
