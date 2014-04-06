package skorulis.hacker.def.square;

import skorulis.hacker.def.BaseDef;

public class TerrainLayerDef extends BaseDef {

	public int type;
	public String textureMain;
	
	public TerrainLayerDef(String name) {
		super(name);
	}
	
	public CompSquareTexture calculateTexture(CompSquareDef north, CompSquareDef east, CompSquareDef south, CompSquareDef west) {
		return new CompSquareTexture(textureMain);
	}
	
	public String[] allTextures() {
		return new String[] {textureMain};
	}
	
}
