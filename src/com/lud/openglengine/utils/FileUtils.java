package com.lud.openglengine.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class FileUtils {

	private FileUtils() {
		
	}
	
	public static enum Image {
		Pixels, Width, Height
	}
	
	public static String loadAsString(String file) {
		StringBuilder result = new StringBuilder();
		
		try {
			InputStream in = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String buffer = "";
			
			while ((buffer = reader.readLine()) != null) {
				result.append(buffer + "\n");
			}
			
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	public static BufferedImage loadAsImage(String path) {
		try {
			InputStream in = new FileInputStream(path);
			return ImageIO.read(in);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
