package skorulis.hacker.computer.square;

import skorulis.hacker.def.terrain.TerrainLayerDef;

public class CompSquareLayer {

	public TerrainLayerDef def;
	public CompSquareTexture texture;
	public String id;
	
	public CompSquareLayer(TerrainLayerDef def) {
		this.def = def;
	}
	
	public void calculateTexture(ComputerSquare north,ComputerSquare east, ComputerSquare south, ComputerSquare west) {
		texture = def.calculateTexture(north, east, south, west);
	}
	
}
