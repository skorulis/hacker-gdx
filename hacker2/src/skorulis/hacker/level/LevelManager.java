package skorulis.hacker.level;

import java.util.HashMap;

import skorulis.hacker.computer.ComputerManager;
import skorulis.hacker.def.DefManager;
import skorulis.hacker.def.LevelDef;

public class LevelManager {

	private final DefManager def;
	private final ComputerManager computers;
	private HashMap<String, LevelDef> levels;
	
	public LevelManager(DefManager def,ComputerManager computers) {
		this.def = def;
		this.computers = computers;
		levels = new HashMap<String, LevelDef>();
		
		createLevels();
	}
	
	private void createLevels() {
		LevelDef level = new LevelDef("l1");
		//level.computers.add(new NodePosDef("c1", getNode("comp"), getComputer("comp1"), 50, 50));
		NetworkNode comp2 = new NetworkNode("c2", def.getNode("comp"), computers.getComputer("comp1"), 300, 200);
		level.computers.add(new NetworkNode("c1", def.getNode("net"),  50, 50));
		level.computers.add(comp2);
		level.computers.add(new NetworkNode("c3", def.getNode("net"), 500, 200));
		level.createConnection(level.findComputer("c1"), comp2, null, "con1");
		level.createConnection(comp2,level.findComputer("c3"), "con2", null);
		level.entryComputer = level.findComputer("c1");
		addLevel(level);
	}
	
	private void addLevel(LevelDef level) {
		levels.put(level.name(), level);
	}
	
	public LevelDef getLevel(String name) {
		LevelDef level = levels.get(name);
		if(level == null) {
			throw new IllegalArgumentException("No level named " + name);
		}
		return level;
	}
	
	
}
