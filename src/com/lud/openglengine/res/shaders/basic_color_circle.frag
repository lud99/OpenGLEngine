#version 330 core

layout (location = 0) out vec4 color;

uniform vec4 fragColor;
uniform vec3 position;
uniform float radius;

void main()
{
	//if (position.y > 500.0f || radius < 0.0f)
		//discard;
		
		
	color = fragColor;
}