package skorulis.hacker.def;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import skorulis.hacker.def.square.CompSquareDef;
import skorulis.hacker.def.square.EdgeLayerDef;
import skorulis.hacker.def.square.TerrainLayerDef;
import skorulis.hacker.def.square.WallLayerDef;
import skorulis.hacker.def.square.TerrainLayerDef.TerrainType;


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
		WallLayerDef wallDef = new WallLayerDef("wall",TerrainType.SOLID);
		wallDef.textureMain = "data/wall_mid.png";
		wallDef.textureHorizontal = "data/wall_horizontal.png";
		wallDef.textureCornerNE = "data/wall_corner_ne.png";
		wallDef.textureStubN = "data/wall_stub_n.png";
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
	
	private void createComputers() {
		ComputerDef cd = new ComputerDef("comp1", 5, 5);
		cd.fillWith(getSquare("floor"));
		cd.makeXWall(getSquare("wall"), 0, 4, 0);
		cd.makeXWall(getSquare("wall"), 0, 4, 4);
		cd.makeYWall(getSquare("wall"), 0, 4, 0);
		cd.makeYWall(getSquare("wall"), 0, 4, 4);
		cd.place(getSquare("connection"), 0, 2).id = "con1";
		cd.place(getSquare("connection"), 4, 2).id = "con2";
		
		cd.assignTextures();
		addDef(cd);
	}
	
	private void createLevels() {
		LevelDef level = new LevelDef("l1");
		//level.computers.add(new NodePosDef("c1", getNode("comp"), getComputer("comp1"), 50, 50));
		NodePosDef comp2 = new NodePosDef("c2", getNode("comp"), getComputer("comp1"), 300, 200);
		CompSquareDef con1 = comp2.findSquare("con1");
		CompSquareDef con2 = comp2.findSquare("con2");
		level.computers.add(new NodePosDef("c1", getNode("net"),  50, 50));
		level.computers.add(comp2);
		level.computers.add(new NodePosDef("c3", getNode("net"), 500, 200));
		level.createConnection(level.findComputer("c1"), comp2, null, con1);
		level.createConnection(comp2,level.findComputer("c3"), con2, null);
		level.entryComputer = level.findComputer("c1");
		addDef(level);
	}
	
	private void addDef(BaseDef def) {
		if(def instanceof NodeDef) {
			nodes.put(def.name(), (NodeDef)def);
		} else if(def instanceof LevelDef) {
			levels.put(def.name, (LevelDef)def);
		} else if(def instanceof ComputerDef) {
			computers.put(def.name,(ComputerDef)def);
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
