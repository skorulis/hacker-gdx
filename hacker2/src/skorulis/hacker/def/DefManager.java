package skorulis.hacker.def;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import skorulis.hacker.def.square.ComputerSquareDef;


public class DefManager {

	public Map<String, NodeDef> nodes;
	public Map<String, LevelDef> levels;
	public Map<String, ComputerDef> computers; 
	public Map<String, ComputerSquareDef> squares;
	
	public DefManager() {
		nodes = new HashMap<String, NodeDef>();
		levels = new HashMap<String, LevelDef>();
		computers = new HashMap<String, ComputerDef>();
		squares = new HashMap<String, ComputerSquareDef>();
	
		createSquares();
		createNodes();
		createComputers();
		createLevels();
		
	}
	
	private void createSquares() {
		ComputerSquareDef csd = new ComputerSquareDef("wall");
		
		squares.put(csd.name(), csd);
	}
	
	private void createNodes() {
		NodeDef c = new NodeDef("comp");
		c.texture = "data/workgroup.png";
		nodes.put(c.name, c);
	}
	
	private void createComputers() {
		ComputerDef cd = new ComputerDef("comp1", 5, 5);
		
		computers.put(cd.name(), cd);
		
	}
	
	private void createLevels() {
		LevelDef level = new LevelDef("l1");
		level.computers.add(new NodePosDef("c1", getNode("comp"), 50, 50));
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
	
	public ComputerSquareDef getSquare(String name) {
		ComputerSquareDef def = squares.get(name);
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
		
		return new ArrayList<String>(set);
	}
	
}
