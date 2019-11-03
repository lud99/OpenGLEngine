package com.lud.openglengine.tests;

import com.lud.openglengine.Engine;
import com.lud.openglengine.graphics.Tileset;

public class Tilesets {
	public static Tileset Grass;
	
	public static void loadAll() {
		//System.out.println("Load");
		Grass = new Tileset(Engine.ROOT_PATH + "res/images/tiles/grass_tiles_debug.png");
		System.out.println(Grass);
	}
}
