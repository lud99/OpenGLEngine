package com.lud.openglengine.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.dyn4j.geometry.Vector2;

import com.lud.openglengine.Engine;
import com.lud.openglengine.utils.FileUtils;

public class Tileset {
	/*private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private int[][] tilesInt;*/
	
	private Texture texture;
	
	private int width, height;
	
	private int tileWidth = 16, tileHeight = 16;
	
	private int tilesCountX, tilesCountY;
	
	public Tileset(String path) {
		texture = new Texture(path);
		
		width = texture.getWidth();
		height = texture.getHeight();
		
		System.out.println("Width: " + width + ", Heih: " + height);
		
		tilesCountX = width / tileWidth;
		tilesCountY = height / tileHeight;
		/*BufferedImage image = FileUtils.loadAsImage(path);
		
		int width = image.getWidth();
		int height = image.getHeight();
		
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
		
		int xTileAmount = width / tileWidth;
		int yTileAmount = height / tileHeight;
		
		int[] tilesInt_ = new int[tileWidth * tileHeight];
		
		for (int x = 0; x < tileWidth; x++) {
			for (int y = 0; y < tileHeight; y++) {
				tilesInt_[x+y] = data[x+y];
			}
		}
		
		texture = new Texture()*/
		
		/*tilesInt = new int[xTileAmount * yTileAmount][tileWidth * tileHeight];
				
		for (int t = 0; t < width / tileWidth * height / tileHeight; t++) {			
			for (int i = 0; i < tileWidth * tileHeight; i++) 
				tilesInt[t][i] = data[i + tileWidth * i];
			
			tiles.add(new Tile(tilesInt[t], tileWidth, tileHeight));
		}*/
	}
	

	public Texture getTexture() { return texture; }

	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public int getTileWidth() { return tileWidth; }
	public int getTileHeight() { return tileHeight; }
	
	public int getTilesCountX() { return tilesCountX; }
	public int getTilesCountY() { return tilesCountY; }
}
