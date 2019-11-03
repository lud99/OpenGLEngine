package com.lud.openglengine.math.physics;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

import com.lud.openglengine.Engine;
import com.lud.openglengine.gameobject.Component;
import com.lud.openglengine.gameobject.GameObject;
import com.lud.openglengine.math.MathPlus;
import com.lud.openglengine.room.RoomManager;
import com.lud.openglengine.tests.Camera;
import com.lud.openglengine.tests.Pixel;
import com.lud.openglengine.tests.Player;
import com.lud.openglengine.tests.level.Level;

public class RigidbodyComponent extends Component {
	
	private GameObject parent;
	
	public Body rigidbody;
	
	public Vector2 velocityInterpolation = new Vector2(1f, 1f);
    public float rotationInterpolation = 1f;
    
    public float width, height;
    
	public boolean alwaysActive = false;
	public boolean isStatic = false;

	public RigidbodyComponent(GameObject parent) {
		super(parent);
		
		this.parent = parent;
	}
	
	public void addRigidbody(Body rigidbody, float width, float height) {
		this.rigidbody = rigidbody;
		this.width = width;
		this.height = height;
		
		RoomManager.getActiveRoom().getWorld().addBody(rigidbody);
	}
		
	public void update() {
		if (rigidbody == null) return;
		
		if (outOfBounds() && !alwaysActive) {
			parent.render = false;
			rigidbody.setActive(false);
			//System.out.println("Dont render");
		} else {
			parent.render = true;
			rigidbody.setActive(true);
		}
		
		if (isStatic) {
			rigidbody.setLinearVelocity(0, 0);
			rigidbody.setAngularVelocity(0);
			rigidbody.clearAccumulatedForce();
			rigidbody.clearForce();
			rigidbody.clearTorque();
			rigidbody.clearAccumulatedTorque();
			
			return;
		}
				
		Vector2 position = rigidbody.getTransform().getTranslation();
		float rotation = (float)-Math.toDegrees(rigidbody.getTransform().getRotation());
		Vector2 velocity = rigidbody.getLinearVelocity();

		if (Level.maximumVelocity.x > 0f && Math.abs(velocity.x) > Level.maximumVelocity.x) {
			rigidbody.setLinearVelocity(Level.maximumVelocity.x * Math.signum(velocity.x), velocity.y);

			parent.position.x += Level.maximumVelocity.x * Math.signum(velocity.x) / Pixel.meter / 10f;
		}
		
		if (Level.maximumVelocity.y > 0f && Math.abs(velocity.y) > Level.maximumVelocity.y) {
			rigidbody.setLinearVelocity(velocity.x, Level.maximumVelocity.y * Math.signum(velocity.y));
						
			parent.position.y += Level.maximumVelocity.y * Math.signum(velocity.y) / Pixel.meter / 10f;
		}
		
		/*if ((Level.maximumVelocity.x > 0f && Math.abs(velocity.x) > Level.maximumVelocity.x) && Level.maximumVelocity.y > 0f && Math.abs(velocity.y) > Level.maximumVelocity.y) {
			rigidbody.clearAccumulatedForce();
			rigidbody.clearForce();
		}*/
		
		if ((Level.maximumVelocity.x > 0f && Math.abs(velocity.x) > Level.maximumVelocity.x) || Level.maximumVelocity.y > 0f && Math.abs(velocity.y) > Level.maximumVelocity.y) 
			return;
		
		parent.position.x = MathPlus.lerp(parent.position.x, position.x * Pixel.meter, velocityInterpolation.x);
		parent.position.y = MathPlus.lerp(parent.position.y, position.y * Pixel.meter, velocityInterpolation.y);
		
		parent.rotation = MathPlus.lerp(parent.rotation, rotation, rotationInterpolation);
	}
	
	private boolean outOfBounds() {
		Vector2 position = rigidbody.getTransform().getTranslation().multiply(Pixel.meter);
		
		double left = position.x - width;
		double right = position.x + width;
		double top = position.y - height;
		double bottom = position.y + height;
		
		
		Vector2 boundsDimensions = (Vector2) RoomManager.getActiveRoom().getWorld().getUserData();
		float boundsLeft = (float) (Camera.position.x - boundsDimensions.x);
		float boundsRight = (float) (Camera.position.x + boundsDimensions.x);
		float boundsTop = (float) (Camera.position.y - boundsDimensions.y );
		float boundsBottom = (float) (Camera.position.y + boundsDimensions.y);
		
		if (right < boundsLeft || left > boundsRight || bottom < boundsTop || top > boundsBottom) 
			return true;
		
		return false;
	}
}
