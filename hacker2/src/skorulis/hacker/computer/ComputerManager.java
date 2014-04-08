package skorulis.hacker.computer;

import java.util.HashMap;

import skorulis.hacker.def.DefManager;

public class ComputerManager {

	private final DefManager def;
	private HashMap<String, Computer> computers;
	
	public ComputerManager(DefManager def) {
		this.def = def;
		computers = new HashMap<String, Computer>();
		createComputers();
	}
	
	private void createComputers() {
		
	}
	
	public Computer getComputer(String name) {
		Computer comp = computers.get(name);
		if(comp == null) {
			throw new IllegalArgumentException("No computer named " + name);
		}
		return comp;
	}
	
}
