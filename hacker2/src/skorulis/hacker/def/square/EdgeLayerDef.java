package skorulis.hacker.def.square;

import skorulis.hacker.computer.square.CompSquareTexture;
import skorulis.hacker.computer.square.ComputerSquare;

public class EdgeLayerDef extends TerrainLayerDef {

	public EdgeLayerDef(String name, TerrainType type) {
		super(name,type);
	}
	
	public CompSquareTexture calculateTexture(ComputerSquare north, ComputerSquare east, ComputerSquare south, ComputerSquare west) {
		if(north == null) {
			return new CompSquareTexture(textureMain);
		} else if(east == null) {
			return new CompSquareTexture(textureMain,270);
		} else if(south == null) {
			return new CompSquareTexture(textureMain,180);
		} else if(west == null) {
			return new CompSquareTexture(textureMain,90);
		}
		return new CompSquareTexture(textureMain);
	}
	
}
