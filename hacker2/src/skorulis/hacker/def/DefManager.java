package skorulis.hacker.def;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import skorulis.hacker.def.square.EdgeLayerDef;
import skorulis.hacker.def.square.TerrainLayerDef;
import skorulis.hacker.def.square.WallLayerDef;
import skorulis.hacker.def.square.TerrainLayerDef.TerrainType;

public class DefManager {

	public Map<String, NodeDef> nodes;
	public Map<String, TerrainLayerDef> squares;
	
	public DefManager() {
		nodes = new HashMap<String, NodeDef>();
		squares = new HashMap<String, TerrainLayerDef>();
	
		createSquares();
		createNodes();
		
	}
	
	private void createSquares() {
		WallLayerDef wallDef = new WallLayerDef("wall",TerrainType.SOLID);
		wallDef.textureMain = "data/wall_mid.png";
		wallDef.textureHorizontal = "data/wall_horizontal.png";
		wallDef.textureCornerNE = "data/wall_corner_ne.png";
		wallDef.textureStubN = "data/wall_stub_n.png";
		wallDef.textureCornerNWE = "data/wall_corner_nwe.png";
		addDef(wallDef);
		
		TerrainLayerDef csd = new TerrainLayerDef("floor",TerrainType.GROUND);
		csd.textureMain = "data/floor.png";
		addDef(csd);
		
		EdgeLayerDef eld = new EdgeLayerDef("connection", TerrainType.PASSABLE);
		eld.textureMain = "data/node_n.png";
		addDef(eld);
	}
	
	private void createNodes() {
		NodeDef c = new NodeDef("comp");
		c.texture = "data/workgroup.png";
		addDef(c);
		
		c = new NodeDef("net");
		c.texture = "data/net.png";
		addDef(c);
	}
	
	private void addDef(BaseDef def) {
		if(def instanceof NodeDef) {
			nodes.put(def.name(), (NodeDef)def);
		} else if(def instanceof TerrainLayerDef) {
			squares.put(def.name, (TerrainLayerDef)def);
		} else {
			throw new IllegalArgumentException("Don't know where to put " + def);
		}
	}
	
	public NodeDef getNode(String name) {
		NodeDef def = nodes.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No node named " + name);
		}
		return def;
	}
	
	public TerrainLayerDef getSquare(String name) {
		TerrainLayerDef def = squares.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No square named " + name);
		}
		return def;
	}
	
	public List<String> allTextures() {
		HashSet<String> set = new HashSet<String>();
		for(NodeDef nd : this.nodes.values()) {
			set.add(nd.texture);
		}
		for(TerrainLayerDef csd : this.squares.values()) {
			set.addAll(Arrays.asList(csd.allTextures()));
		}
		
		return new ArrayList<String>(set);
	}
	
}
