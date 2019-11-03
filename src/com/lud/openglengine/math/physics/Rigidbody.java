package com.lud.openglengine.math.physics;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

public class Rigidbody extends Body {
	public Vector2 velocityInterpolation = new Vector2(0.25f, 0.25f);
    public float rotationInterpolation = 0.25f;
}
