package skorulis.hacker.def;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import skorulis.hacker.def.square.TerrainLayerDef;
import skorulis.hacker.def.square.WallLayerDef;


public class DefManager {

	public Map<String, NodeDef> nodes;
	public Map<String, LevelDef> levels;
	public Map<String, ComputerDef> computers; 
	public Map<String, TerrainLayerDef> squares;
	
	public DefManager() {
		nodes = new HashMap<String, NodeDef>();
		levels = new HashMap<String, LevelDef>();
		computers = new HashMap<String, ComputerDef>();
		squares = new HashMap<String, TerrainLayerDef>();
	
		createSquares();
		createNodes();
		createComputers();
		createLevels();
		
	}
	
	private void createSquares() {
		WallLayerDef wallDef = new WallLayerDef("wall");
		wallDef.textureMain = "data/wall_mid.png";
		wallDef.textureHorizontal = "data/wall_horizontal.png";
		wallDef.textureCornerNE = "data/wall_corner_ne.png";
		wallDef.textureCornerNW = "data/wall_corner_nw.png";
		wallDef.textureCornerSE = "data/wall_corner_se.png";
		wallDef.textureCornerSW = "data/wall_corner_sw.png";
		squares.put(wallDef.name(), wallDef);
		
		TerrainLayerDef csd = new TerrainLayerDef("floor");
		csd.textureMain = "data/floor.png";
		squares.put(csd.name(), csd);
	}
	
	private void createNodes() {
		NodeDef c = new NodeDef("comp");
		c.texture = "data/workgroup.png";
		nodes.put(c.name, c);
	}
	
	private void createComputers() {
		ComputerDef cd = new ComputerDef("comp1", 5, 5);
		cd.fillWith(getSquare("floor"));
		cd.makeXWall(getSquare("wall"), 0, 4, 0);
		cd.makeXWall(getSquare("wall"), 0, 4, 4);
		cd.makeYWall(getSquare("wall"), 0, 4, 0);
		cd.makeYWall(getSquare("wall"), 0, 4, 4);
		cd.assignTextures();
		computers.put(cd.name(), cd);
		
	}
	
	private void createLevels() {
		LevelDef level = new LevelDef("l1");
		level.computers.add(new NodePosDef("c1", getNode("comp"), getComputer("comp1"), 50, 50));
		level.computers.add(new NodePosDef("c2", getNode("comp"), getComputer("comp1"), 300, 200));
		level.computers.add(new NodePosDef("c3", getNode("comp"), 500, 200));
		level.createConnection("c1","c2");
		level.createConnection("c2","c3");
		level.entryComputer = level.findComputer("c1");
		levels.put(level.name, level);
	}
	
	public NodeDef getNode(String name) {
		NodeDef def = nodes.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No node named " + name);
		}
		return def;
	}
	
	public LevelDef getLevel(String name) {
		LevelDef def = levels.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No level named " + name);
		}
		return def;
	}
	
	public ComputerDef getComputer(String name) {
		ComputerDef def = computers.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No computer named " + name);
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
