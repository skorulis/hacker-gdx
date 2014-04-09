package skorulis.hacker.level;

import java.util.HashMap;

import skorulis.hacker.computer.ComputerManager;
import skorulis.hacker.def.DefManager;

public class LevelManager {

	private final DefManager def;
	private final ComputerManager computers;
	private HashMap<String, NetworkLevel> levels;
	
	public LevelManager(DefManager def,ComputerManager computers) {
		this.def = def;
		this.computers = computers;
		levels = new HashMap<String, NetworkLevel>();
		
		createLevels();
	}
	
	private void createLevels() {
		NetworkLevel level = new NetworkLevel("l1");
		LevelBuilder builder = new LevelBuilder(level);
		//level.computers.add(new NodePosDef("c1", getNode("comp"), getComputer("comp1"), 50, 50));
		NetworkNode comp1 = new NetworkNode("c1", def.getNode("net"),  50, 50); 
		NetworkNode comp2 = new NetworkNode("c2", def.getNode("comp"), computers.getComputer("comp1"), 150, 200);
		NetworkNode comp4 = new NetworkNode("c4", def.getNode("comp"), computers.getComputer("comp2"),  50, 300);
		builder.addNode(comp1);
		builder.addNode(comp2);
		builder.addNode(comp4);
		builder.addNode(new NetworkNode("c3", def.getNode("net"), 300, 200));
		builder.createConnection(comp1, comp2, null, "con1");
		builder.createConnection(comp1, comp4, null, "con1");
		builder.createConnection(comp2,level.findComputer("c3"), "con2", null);
		level.entryComputer = comp1;
		addLevel(level);
	}
	
	private void addLevel(NetworkLevel level) {
		levels.put(level.name(), level);
	}
	
	public NetworkLevel getLevel(String name) {
		NetworkLevel level = levels.get(name);
		if(level == null) {
			throw new IllegalArgumentException("No level named " + name);
		}
		return level;
	}
	
	
}
