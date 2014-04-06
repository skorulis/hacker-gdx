package skorulis.hacker.def.square;

import java.util.ArrayList;

public class CompSquareDef {
	
	public ArrayList<TerrainLayerDef> layers;
	
	public CompSquareDef() {
		layers = new ArrayList<TerrainLayerDef>();
	}
	
	public void addLayer(TerrainLayerDef layer) {
		layers.add(layer);
	}
	
	public void clear() {
		layers.clear();
	}
	
}
