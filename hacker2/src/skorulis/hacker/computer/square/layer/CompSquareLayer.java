package skorulis.hacker.computer.square.layer;

import skorulis.hacker.computer.square.CompSquareTexture;
import skorulis.hacker.computer.square.ComputerSquare;
import skorulis.hacker.def.terrain.TerrainLayerDef;

public class CompSquareLayer {

	public TerrainLayerDef def;
	public CompSquareTexture texture;
	public String id;
	
	public CompSquareLayer() {
		
	}
	
	public CompSquareLayer(TerrainLayerDef def) {
		this.def = def;
	}
	
	public void setDef(TerrainLayerDef def) {
		this.def = def;
	}
	
	public void calculateTexture(ComputerSquare north,ComputerSquare east, ComputerSquare south, ComputerSquare west) {
		texture = def.calculateTexture(north, east, south, west);
	}
	
}
