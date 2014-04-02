package skorulis.hacker.def;

import java.util.HashMap;
import java.util.Map;


public class DefManager {

	public Map<String, ComputerDef> computers;
	public Map<String, LevelDef> levels;
	
	public DefManager() {
		computers = new HashMap<String, ComputerDef>();
		levels = new HashMap<String, LevelDef>();
	
		createComputers();
		createLevels();
		
	}
	
	private void createComputers() {
		ComputerDef c = new ComputerDef("c1");
		computers.put(c.name, c);
		
	}
	
	private void createLevels() {
		LevelDef level = new LevelDef("l1");
		level.computers.add(new ComputerPosDef(getComputer("c1"), 10, 10));
		levels.put(level.name, level);
	}
	
	public ComputerDef getComputer(String name) {
		ComputerDef def = computers.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No computer named " + name);
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
	
	
}
