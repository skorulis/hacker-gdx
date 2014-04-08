package skorulis.hacker.level;

import java.util.HashMap;

import skorulis.hacker.def.DefManager;

public class LevelManager {

	private final DefManager def;
	private HashMap<String, NetworkLevel> levels;
	
	public LevelManager(DefManager def) {
		this.def = def;
		levels = new HashMap<String, NetworkLevel>();
		createLevels();
	}
	
	private void createLevels() {
		
	}
	
	public NetworkLevel getLevel(String name) {
		NetworkLevel level = levels.get(name);
		if(level == null) {
			throw new IllegalArgumentException("No level named " + name);
		}
		return level;
	}
	
	
}
