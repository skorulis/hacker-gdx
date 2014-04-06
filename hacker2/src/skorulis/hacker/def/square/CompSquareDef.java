package skorulis.hacker.def.square;

import java.util.ArrayList;

public class CompSquareDef {
	
	public ArrayList<CompSquareLayer> layers;
	public ArrayList<CompSquareTexture> textures;
	
	public CompSquareDef() {
		layers = new ArrayList<CompSquareLayer>();
		textures = new ArrayList<CompSquareTexture>();
	}
	
	public void addLayer(TerrainLayerDef layer) {
		layers.add(new CompSquareLayer(layer) );
	}
	
	public void clear() {
		layers.clear();
	}
	
	public void place(TerrainLayerDef layer) {
		TerrainLayerDef old;
		for(int i = 0; i < layers.size(); ++i) {
			old = layers.get(i).def;
			if(layer.shouldReplace(old)) {
				layers.set(i, new CompSquareLayer(layer));
				return;
			}
		}
	}
	
	public void assignTextures(CompSquareDef north,CompSquareDef east, CompSquareDef south, CompSquareDef west) {
		textures.clear();
		for(CompSquareLayer layer : layers) {
			textures.add(layer.def.calculateTexture(north,east,south,west));
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
