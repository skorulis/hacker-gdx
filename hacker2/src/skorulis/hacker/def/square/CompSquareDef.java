package skorulis.hacker.def.square;

import java.util.ArrayList;

public class CompSquareDef {
	
	public ArrayList<CompSquareLayer> layers;
	
	public CompSquareDef() {
		layers = new ArrayList<CompSquareLayer>();
	}
	
	public void addLayer(TerrainLayerDef layer) {
		layers.add(new CompSquareLayer(layer) );
	}
	
	public void clear() {
		layers.clear();
	}
	
	public CompSquareLayer place(TerrainLayerDef layer) {
		TerrainLayerDef old;
		for(int i = 0; i < layers.size(); ++i) {
			old = layers.get(i).def;
			if(layer.shouldReplace(old)) {
				CompSquareLayer newLayer = new CompSquareLayer(layer);
				layers.set(i, newLayer);
				return newLayer;
			}
		}
		return null;
	}
	
	public void assignTextures(CompSquareDef north,CompSquareDef east, CompSquareDef south, CompSquareDef west) {
		for(CompSquareLayer layer : layers) {
			layer.calculateTexture(north, east, south, west);
		}
	}
	
	public boolean hasLayer(String name) {
		for(CompSquareLayer tld : layers) {
			if(tld.def.name().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
}
