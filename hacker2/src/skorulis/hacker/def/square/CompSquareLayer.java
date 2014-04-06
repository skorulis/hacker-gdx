package skorulis.hacker.def.square;

public class CompSquareLayer {

	public TerrainLayerDef def;
	public CompSquareTexture texture;
	public String id;
	
	public CompSquareLayer(TerrainLayerDef def) {
		this.def = def;
	}
	
	public void calculateTexture(CompSquareDef north,CompSquareDef east, CompSquareDef south, CompSquareDef west) {
		texture = def.calculateTexture(north, east, south, west);
	}
	
}
