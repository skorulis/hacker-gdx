package skorulis.hacker.def;

import skorulis.hacker.def.square.CompSquareDef;
import skorulis.hacker.def.square.TerrainLayerDef;


public class ComputerDef extends BaseDef {
	
	public int width,height;
	public CompSquareDef[][] squares;
	
	public ComputerDef(String name,int width, int height) {
		super(name);
		this.width = width;
		this.height = height;
		squares = new CompSquareDef[height][width];
		for(int i = 0 ; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				squares[i][j] = new CompSquareDef();
			}
		}
	}
	
	public void fillWith(TerrainLayerDef layer) {
		for(int i = 0 ; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				squares[i][j].addLayer(layer);
			}
		}
	}
	
	public void makeXWall(TerrainLayerDef layer, int x1, int x2, int y) {
		for(int i = x1; i <= x2; ++i) {
			squares[y][i].addLayer(layer);
		}
	}
	
	public void assignTextures() {
		CompSquareDef square;
		CompSquareDef north,south,east, west;
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				square = squares[i][j];
				north = i > 0 ? squares[i-1][j] : null;
				south = i < height - 1 ? squares[i+1][j] : null;
				west = j > 0 ? squares[i][j-1] : null;
				east = j < width - 1 ? squares[i][j+1] : null;
				square.assignTextures(north, east, south, west);
			}
		}
	}
	
}
