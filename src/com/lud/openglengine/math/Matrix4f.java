package com.lud.openglengine.math;

import java.nio.FloatBuffer;

import com.lud.openglengine.utils.BufferUtilsGL;

public class Matrix4f {
	
	public static final int SIZE = 4 * 4;
	public float[] elements = new float[SIZE];
	
	/* Matrix
	 * 
	 * https://www.youtube.com/watch?v=1y7-9-QJKz4
	 * 
	 * index = [x + y * size] or [x (row) + y (column) * 4 (width)]
	 * 
	 * [0, 0, 0, 0]
	 * [0, 0, 0, 0]
	 * [0, 0, 0, 0]
	 * [0, 0, 0, 0]
	 * 
	 */
	
	public Matrix4f() {
		
	}
	
	public static Matrix4f identity() {
		Matrix4f result = new Matrix4f();
		
		for (int i = 0; i < SIZE; i++) {
			result.elements[i] = 0.0f;
		}
		
		/* Set Matrix Diagonal
		 * 
		 * index = [x (row) + y (column) * 4 (width]
		 * 
		 * [1, 0, 0, 0]
		 * [0, 1, 0, 0]
		 * [0, 0, 1, 0]
		 * [0, 0, 0, 1]
		 * 
		 */
		
		result.elements[0 + 0 * 4] = 1.0f;
		result.elements[1 + 1 * 4] = 1.0f;
		result.elements[2 + 2 * 4] = 1.0f;
		result.elements[3 + 3 * 4] = 1.0f;
		
		return result;
	}
	
	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f result = identity();
		
		result.elements[0 + 0 * 4] = 2.0f / (right - left);
		result.elements[1 + 1 * 4] = 2.0f / (top - bottom);
		result.elements[2 + 2 * 4] = 2.0f / (near - far);
		
		result.elements[0 + 3 * 4] = (left + right) / (left - right);
		result.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
		result.elements[2 + 3 * 4] = (far + near) / (far - near);
		
		return result;
	}
	
	public static Matrix4f translate(Vector3f vector) {
		Matrix4f result = identity();
		
		result.elements[0 + 3 * 4] = vector.asMatrixX();
		result.elements[1 + 3 * 4] = vector.asMatrixY();
		result.elements[2 + 3 * 4] = vector.z;
		
		return result;
	}
	
	public static Matrix4f rotate(float angle) {
		Matrix4f result = identity();
		
		
		float r = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(r);
		float sin = (float) Math.sin(r);
		
		result.elements[0 + 0 * 4] = cos;
		result.elements[1 + 0 * 4] = sin;
		
		result.elements[0 + 1 * 4] = -sin;
		result.elements[1 + 1 * 4] = cos;
		
		return result;
	}
	

	/* Matrix Multiplication
	 * 
	 * Matrix A = [9, 2, 8, 1]
	 *			  [1, 9, 3, 7]
	 *			  [5, 5, 1, 6]
	 *			  [7, 2, 0, 8]
	 *
	 * Matrix B = [1, 0, 0, 0]
	 *			  [0, 1, 0, 0]
	 *			  [0, 0, 1, 0]
	 *			  [0, 0, 0, 1]
	 *
	 * Multiply each of Matrix A's rows with each of Matrix B's columns, and add the them together.
	 * Once Matrix A's first row has been done multiplying with, it moves down by one.
	 * 
	 * Example:		 [9, 2, 8, 1] * [1]
	 *			 					[0]
	 *			 					[0]
	 *			 					[0]
	 *
	 * 				 9*1 + 2*0 + 8*0 + 1*0
	 * 
	 * 
 	 * Example:		 [9, 2, 8, 1] * [0]
	 *			 					[1]
	 *			 					[0]
	 *			 					[0]
	 *
	 * 				 9*0 + 2*1 + 8*0 + 1*0
	 * 
	 
	 * 
	 * If using "column major ordering", you Multiply each of Matrix B's rows with 
	 * each of Matrix A's columns (flipped). In OpenGL, column major ordering is used.
	 * 
	 * 
	 */
	
	public Matrix4f multiply(Matrix4f matrix) {
		Matrix4f result = new Matrix4f();
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				float sum = 0.0f;
				for (int e = 0; e < 4; e++) {
					sum += this.elements[x + e * 4] * matrix.elements[e + y * 4];
				}
				
				result.elements[x + y * 4] = sum;
			}
		}
		
		return result;
	}
	
	public FloatBuffer toFloatBuffer() {
		return BufferUtilsGL.createFloatBuffer(elements);
	}
}
