package com.lud.openglengine.gameobject;

import java.util.HashSet;
import java.util.Set;

import org.dyn4j.geometry.Vector2;

import com.lud.openglengine.Engine;
import com.lud.openglengine.graphics.RGBA;
import com.lud.openglengine.graphics.Shader;
import com.lud.openglengine.graphics.Texture;
import com.lud.openglengine.graphics.Tile;
import com.lud.openglengine.graphics.Tileset;
import com.lud.openglengine.graphics.VertexArray;
import com.lud.openglengine.math.Matrix4f;
import com.lud.openglengine.math.Vector3f;
import com.lud.openglengine.math.physics.RigidbodyComponent;
import com.lud.openglengine.room.RoomManager;
import com.lud.openglengine.tests.Camera;
import com.lud.openglengine.tests.Shaders;
import com.lud.openglengine.tests.Tilesets;

public class GameObject {
	
	public Vector3f position = new Vector3f();
	
	public float customZ = -1f;
	
	public float rotation = 0;
	public float width = 10f, height = 10f;
	
	public float textureWidth = width, textureHeight = height;
	
	public int tilesetIndex = 0;
	
	public Vector2 textureCoordinatesScale = new Vector2(1, 1);
	
	public RGBA color;
	
	public String vertexShaderPath = Engine.ROOT_PATH + "res/shaders/basic.vert";
	public String fragmentShaderPath = Engine.ROOT_PATH + "res/shaders/basic_color.frag";
	public String texturePath;
	
	public String defaultTextureFragmentShaderPath = Engine.ROOT_PATH + "res/shaders/basic_texture.frag";
	public String defaultColorFragmentShaderPath = Engine.ROOT_PATH + "res/shaders/basic_color.frag";
	
	public Set<Component> components = new HashSet<Component>();
	
	public RigidbodyComponent rigidbodyComponent;
	
	public Tileset tileset;
		
	public boolean useTextures = true;
	public boolean hasRunInit = false;
	
	public boolean isUnique = false;
	
	public boolean render = true;
	
	public boolean textureRepeat = false;
	
	public boolean useTileset = false;
	
	public float[] tcs = new float[0];
	
	public Shapes shape = Shapes.RECTANGLE;
	
	public static enum States {
		EMPTY
	}
	
	public static enum Shapes {
		RECTANGLE, RIGHT_TRIANGLE, ISOSCELES_TRIANGLE, EQUILATERAL_TRIANGLE, CIRCLE
	}
	
	private VertexArray mesh;
	private Texture texture;
	
	private Shader shader;
	
	private States state;
	
	public GameObject() { add(); }
	
	public GameObject(States state) { 
		this.state = state;
		
		if (state == States.EMPTY) {
			render = false;
			useTextures = false;
		}
		
		add();
	}
		
	public GameObject(Vector3f position, float width, float height) {
		this.position = position;
		
		this.width = width;
		this.height = height;
		
		add();
	}
	
	public void _init() {
		onBeforeInit();
		
		if (state == States.EMPTY) {
			hasRunInit = true;
			
			start();
			
			return;
		}
		
		float matrixWidth = width / Engine.windowWidth * Engine.projectionWidth * Engine.scale;
		float matrixHeight = height / Engine.windowHeight * Engine.projectionHeight * Engine.scale;
		
		float[] vertices = new float[] {
			-matrixWidth, -matrixHeight, position.z,
			-matrixWidth,  matrixHeight, position.z,
			matrixWidth,  matrixHeight, position.z,
			matrixWidth, -matrixHeight, position.z
		};
		
		byte[] indices = new byte[] {
			0, 1, 2,
			2, 3, 0
		};
		
		if (tcs.length == 0) {
			tcs = new float[] {
				0, 1, 
				0, 0,
				1, 0,
				1, 1
			};
		}
		
		/*if (shape == Shapes.RIGHT_TRIANGLE) {
			vertices= new float[] {
				-matrixWidth / 1, matrixHeight, position.z,
				matrixWidth / 1,  -matrixHeight, position.z,
				-matrixWidth / 1,  -matrixHeight, position.z
			};
			
			indices = new byte[] {
				0, 1, 2
			};
		} else if (shape == Shapes.ISOSCELES_TRIANGLE) {
			vertices= new float[] {
				matrixWidth, -matrixHeight, position.z,
				-matrixWidth,  -matrixHeight, position.z,
				matrixWidth,  matrixHeight, position.z
			};
			
			indices = new byte[] {
				0, 1, 2
			};
		} else if (shape == Shapes.EQUILATERAL_TRIANGLE) {
			vertices= new float[] {
				matrixWidth * 0, -matrixHeight * 0, position.z,
				matrixWidth,  -matrixHeight * 0, position.z,
				matrixWidth * 0.5f,  matrixHeight, position.z
			};
			
			indices = new byte[] {
				0, 1, 2
			};
		}*/
				
		mesh = new VertexArray(vertices, indices, tcs);
		
		//Default the fragment shader to the basic texture shader if no other fragment is specified
		if (useTextures && fragmentShaderPath.equals(defaultColorFragmentShaderPath)) fragmentShaderPath = defaultTextureFragmentShaderPath;
		
		
		//Check if a shader for this object already exists, and if so use it.
		
		String key = getClass().getName();
		
		//If a shader for this object already exists
		if (Shaders.shaders.containsKey(key) && !isUnique) {
			System.out.println("Shader already exists, using that one instead");
			
			shader = new Shader(Shaders.shaders.get(key));
		} else { //Create a new shader otherwise, and add it to the list
			System.out.println("Shader doesn't exist, creating one now (" + key + ")");
			
			shader = new Shader(vertexShaderPath, fragmentShaderPath);
			Shaders.shaders.put(key, shader.getId());
		}
		
		if (useTextures) {
			if (!useTileset) texture = new Texture(texturePath, textureRepeat);
			
			shader.setUniform1i("tex", 1);		
		}

		shader.setUniformMat4f("pr_matrix", Engine.getPrMatrix());
		
		hasRunInit = true;

		start();
	}
	
	public void onBeforeInit() { }
	public void start() { }
	public void update() { }
	public void lateUpdate() { }
	public void fixedUpdate() { }
	public void fixedUpdate(float elapsedTime) { }
	public void onRender() { }
	
	public void updateComponents() {
		if (rigidbodyComponent != null) rigidbodyComponent.update();
	}
	
	public void updateComponentsFixed() {
		//System.out.println("Component update");
		//if (rigidbodyComponent != null) rigidbodyComponent.update();
	}
	
	public void render() {
		if (!render || !hasRunInit || shader == null) return;
		
		Matrix4f matrix = 
				Matrix4f.translate(new Vector3f(position.x * Engine.scale, position.y * Engine.scale, customZ >= 0 ? customZ : position.z))
				.multiply(Matrix4f.rotate(rotation));
		
		shader.enable();
		
		onRender();
		
		shader.setUniformMat4f("vw_matrix", Camera.vwMatrix());
		shader.setUniformMat4f("ml_matrix", matrix);
		
		if (!useTextures) {
			shader.setColor4f("fragColor", color.from0to1());
		} else {
			shader.setUniform2f("textureCoordinatesScale", (float)textureCoordinatesScale.x, (float)textureCoordinatesScale.y);
		}

		if (useTextures) {
			if (!useTileset) texture.bind();
			else {
				shader.setUniform2f("tileOffset", 1f / tileset.getTilesCountX() * tilesetIndex, 1f / tileset.getTilesCountY() * tilesetIndex);
				shader.setUniform2f("tileSize", 1f / tileset.getTilesCountX(), 1f / tileset.getTilesCountY());
				
				shader.setUniformMat4f("tilePositionCorrection_matrix", Matrix4f.translate(new Vector3f(
						(tileset.getWidth() - tileset.getTileWidth()) / 2f * Engine.scale,
						(tileset.getHeight() - tileset.getTileHeight()) / 2f * Engine.scale, 0
				)));
				
				tileset.getTexture().bind();
			}
		}
		
		mesh.render();
		
		if (useTextures) {
			if (!useTileset) texture.unbind();
			else tileset.getTexture().unbind();
		}
		
		shader.disable();
		
		renderRigidbody();
	}
	
	private void renderRigidbody() {
		/*for (Component component : components) {
			if (component.getClass().equals(RigidbodyComponent.class)) {
				
			}
		}*/
	}
	
	private void add() {
		RoomManager.getActiveRoom().addObject(this);
	}
	
	public Component addComponent(Component component) {
		components.add(component);
		
		return component;
	}	
	
	protected VertexArray getMesh() {
		return mesh;
	}
	
	protected Shader getShader() {
		return shader;
	}
}
