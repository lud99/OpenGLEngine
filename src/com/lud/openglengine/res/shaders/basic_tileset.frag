#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
} fs_in;

uniform sampler2D tex;
uniform vec2 textureCoordinatesScale;
uniform vec2 tileOffset;
uniform vec2 tileSize;

void main()
{	
	vec2 texturePos = vec2(fs_in.tc.x + tileOffset.x * textureCoordinatesScale.x, fs_in.tc.y + tileOffset.y * textureCoordinatesScale.y);
	
	if (texture2D(tex, texturePos).a == 0.0f || (fs_in.tc.x > tileSize.x || fs_in.tc.y > tileSize.y))
		discard;
		
	color = texture(tex, texturePos);
}