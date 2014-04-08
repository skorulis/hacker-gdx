package skorulis.hacker.computer;

import skorulis.hacker.def.ComputerDef;
import skorulis.hacker.def.square.CompSquareLayer;
import skorulis.hacker.def.square.TerrainLayerDef;

public class ComputerBuilder {

	public ComputerDef computer;
	
	public ComputerBuilder(ComputerDef computer) {
		this.computer = computer;
	}
	
	public void fillWith(TerrainLayerDef layer) {
		for(int i = 0 ; i < computer.height; ++i) {
			for(int j = 0; j < computer.width; ++j) {
				computer.squares[i][j].addLayer(layer);
			}
		}
	}
	
	public void makeXWall(TerrainLayerDef layer, int x1, int x2, int y) {
		for(int i = x1; i <= x2; ++i) {
			computer.squares[y][i].addLayer(layer);
		}
	}
	
	public void makeYWall(TerrainLayerDef layer, int y1, int y2, int x) {
		for(int i = y1; i <= y2; ++i) {
			computer.squares[i][x].addLayer(layer);
		}
	}
	
	public void makeEdgeWall(TerrainLayerDef layer) {
		makeXWall(layer, 0, computer.width-1, 0);
		makeXWall(layer, 0, computer.width-1, computer.height-1);
		makeYWall(layer, 0, computer.height-1, 0);
		makeYWall(layer, 0, computer.height-1, computer.width-1);
	}
	
	public CompSquareLayer place(TerrainLayerDef layer, int x, int y) {
		return computer.squares[y][x].place(layer);
	}
	
	public int width() {
		return computer.width;
	}
	
	public int height() {
		return computer.height;
	}
	
}
