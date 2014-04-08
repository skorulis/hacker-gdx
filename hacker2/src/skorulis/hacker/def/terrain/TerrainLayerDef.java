package skorulis.hacker.def.terrain;

import skorulis.hacker.computer.square.CompSquareLayer;
import skorulis.hacker.computer.square.CompSquareTexture;
import skorulis.hacker.computer.square.ComputerSquare;
import skorulis.hacker.def.BaseDef;

public class TerrainLayerDef extends BaseDef {

	public enum TerrainAction {
		NONE,
		EXIT
	}
	
	public enum TerrainType {
		GROUND,
		SOLID,
		PASSABLE,
	}
	
	public TerrainAction action;
	public TerrainType type;
	public String textureMain;
	public Class<CompSquareLayer> layerClass;
	
	public TerrainLayerDef(String name,TerrainType type) {
		super(name);
		this.type = type;
		this.action = TerrainAction.NONE;
		layerClass = CompSquareLayer.class;
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
	
	public CompSquareLayer createLayerInstance() {
		CompSquareLayer csl = null;
		try {
			csl = this.layerClass.newInstance();
			csl.setDef(this);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return csl;
	}
	
}
