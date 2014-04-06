package skorulis.hacker.def.square;

import java.util.ArrayList;

import skorulis.hacker.computer.ComputerSquare;

public class CompSquareDef {
	
	public ArrayList<TerrainLayerDef> layers;
	public ArrayList<CompSquareTexture> textures;
	
	public CompSquareDef() {
		layers = new ArrayList<TerrainLayerDef>();
		textures = new ArrayList<CompSquareTexture>();
	}
	
	public void addLayer(TerrainLayerDef layer) {
		layers.add(layer);
	}
	
	public void clear() {
		layers.clear();
	}
	
	public void assignTextures(CompSquareDef north,CompSquareDef east, CompSquareDef south, CompSquareDef west) {
		textures.clear();
		for(TerrainLayerDef def : layers) {
			textures.add(def.calculateTexture(north,east,south,west));
		}
	}
	
	public boolean hasLayer(String name) {
		for(TerrainLayerDef tld : layers) {
			if(tld.name().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
}
