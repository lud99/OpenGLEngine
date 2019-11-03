#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
} fs_in;

uniform sampler2D tex;
uniform vec2 textureCoordinatesScale;

void main()
{	
	vec2 texturePos = vec2(fs_in.tc.x * textureCoordinatesScale.x, fs_in.tc.y * textureCoordinatesScale.y);
	
	if (texture2D(tex, texturePos).a == 0.0f)
		discard;
		
	color = texture(tex, texturePos);
}