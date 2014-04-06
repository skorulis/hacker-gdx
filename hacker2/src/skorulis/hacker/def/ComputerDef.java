package skorulis.hacker.def;

import skorulis.hacker.def.square.CompSquareDef;
import skorulis.hacker.def.square.TerrainLayerDef;


public class ComputerDef extends BaseDef {
	
	public int width,height;
	public CompSquareDef[] squares;
	
	public ComputerDef(String name,int width, int height) {
		super(name);
		this.width = width;
		this.height = height;
		squares = new CompSquareDef[width*height];
		for(int i = 0 ; i < squares.length; ++i) {
			squares[i] = new CompSquareDef();
		}
	}
	
	public void fillWith(TerrainLayerDef layer) {
		for(int i = 0; i < squares.length; ++i) {
			squares[i].addLayer(layer);
		}
	}
	
	public void makeXWall(TerrainLayerDef layer, int x1, int x2, int y) {
		for(int i = x1; i <= x2; ++i) {
			squares[y*width + i].addLayer(layer);
		}
	}
	
}
