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
	
	public CompSquareTexture calculateTexture(ComputerSquare north, ComputerSquare east, ComputerSquare south, ComputerSquare west) {
		return new CompSquareTexture(textureMain);
	}
	
	public String[] allTextures() {
		return new String[] {textureMain};
	}
	
	public boolean shouldReplace(TerrainLayerDef old) {
		if(old.type == this.type) {
			return true;
		}
		if(old.type == TerrainType.SOLID && this.type == TerrainType.PASSABLE) {
			return true;
		}
		return false;
	}
	
}
