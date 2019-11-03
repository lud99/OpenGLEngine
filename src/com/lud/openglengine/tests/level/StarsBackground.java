package com.lud.openglengine.tests.level;

import org.dyn4j.geometry.Vector2;

import com.lud.openglengine.Engine;
import com.lud.openglengine.gameobject.Background;
import com.lud.openglengine.gameobject.GameObject;
import com.lud.openglengine.math.Vector3f;

public class StarsBackground extends Background {

	public StarsBackground(Vector3f position, float width, float height, float scale) {
		super(position, width, height, scale);
		
		texturePath = Engine.ROOT_PATH + "res/images/backgrounds/stars.png";
		
		scrollSpeed = new Vector2(1f, 0f);
	}
}
