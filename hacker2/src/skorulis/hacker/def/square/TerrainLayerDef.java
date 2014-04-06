package skorulis.hacker.def.square;

import skorulis.hacker.def.BaseDef;

public class TerrainLayerDef extends BaseDef {

	public int type;
	public String textureMain;
	
	public TerrainLayerDef(String name) {
		super(name);
	}
	
	public String calculateTexture(CompSquareDef north, CompSquareDef east, CompSquareDef south, CompSquareDef west) {
		return textureMain;
	}
	
	public String[] allTextures() {
		return new String[] {textureMain};
	}
	
}
