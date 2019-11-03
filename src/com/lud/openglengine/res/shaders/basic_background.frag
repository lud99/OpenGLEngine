#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
} fs_in;

uniform sampler2D tex;
uniform float time;
uniform vec2 speed;
uniform vec2 textureCoordinatesScale;

void main()
{		
	vec2 texturePos = vec2(mod(fs_in.tc.x - (speed.x * time), 1) * textureCoordinatesScale.x, mod(fs_in.tc.y - (speed.y * time), 1) * textureCoordinatesScale.y);
	
	if (texture2D(tex, texturePos).a == 0.0f)
		discard;
	
	color = texture(tex, texturePos);
}