package skorulis.hacker.computer;

import skorulis.hacker.computer.square.CompSquareLayer;
import skorulis.hacker.computer.square.ComputerSquare;
import skorulis.hacker.def.terrain.TerrainLayerDef;

public class ComputerBuilder {

	public Computer computer;
	
	public ComputerBuilder(Computer computer) {
		this.computer = computer;
	}
	
	public Computer build() {
		assignTextures();
		return computer;
	}
	
	public void assignTextures() {
		ComputerSquare square;
		ComputerSquare north,south,east, west;
		for(int i = 0; i < computer.height; ++i) {
			for(int j = 0; j < computer.width; ++j) {
				square = computer.squares[i][j];
				south = i > 0 ? computer.squares[i-1][j] : null;
				north = i < computer.height - 1 ? computer.squares[i+1][j] : null;
				west = j > 0 ? computer.squares[i][j-1] : null;
				east = j < computer.width - 1 ? computer.squares[i][j+1] : null;
				square.assignTextures(north, east, south, west);
			}
		}
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
		ComputerSquare square = computer.squares[y][x];
		return this.placeIn(square, layer);
	}
	
	public CompSquareLayer placeIn(ComputerSquare square, TerrainLayerDef layer) {
		TerrainLayerDef old;
		for(int i = 0; i < square.layers.size(); ++i) {
			old = square.layers.get(i).def;
			if(layer.shouldReplace(old)) {
				CompSquareLayer newLayer = new CompSquareLayer(layer);
				square.layers.set(i, newLayer);
				return newLayer;
			}
		}
		return square.addLayer(layer);
	}
	
	public int width() {
		return computer.width;
	}
	
	public int height() {
		return computer.height;
	}
	
}
