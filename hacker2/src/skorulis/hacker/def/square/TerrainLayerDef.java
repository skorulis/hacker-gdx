package skorulis.hacker.def.square;

import skorulis.hacker.def.BaseDef;

public class TerrainLayerDef extends BaseDef {

	public enum TerrainType {
		GROUND,
		SOLID,
		PASSABLE,
	}
	
	public TerrainType type;
	public String textureMain;
	
	public TerrainLayerDef(String name,TerrainType type) {
		super(name);
		this.type = type;
	}
	
	public CompSquareTexture calculateTexture(CompSquareDef north, CompSquareDef east, CompSquareDef south, CompSquareDef west) {
		return new CompSquareTexture(textureMain);
	}
	
	public String[] allTextures() {
		return new String[] {textureMain};
	}
	
}
