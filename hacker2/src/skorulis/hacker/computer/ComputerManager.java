package skorulis.hacker.computer;

import java.util.HashMap;

import skorulis.hacker.def.ComputerDef;
import skorulis.hacker.def.DefManager;

public class ComputerManager {

	private final DefManager def;
	private HashMap<String, ComputerDef> computers;
	
	public ComputerManager(DefManager def) {
		this.def = def;
		computers = new HashMap<String, ComputerDef>();
		createComputers();
	}
	
	private void createComputers() {
		ComputerDef cd = new ComputerDef("comp1", 7, 7);
		cd.fillWith(def.getSquare("floor"));
		cd.makeEdgeWall(def.getSquare("wall"));
		cd.makeYWall(def.getSquare("wall"), 2, cd.height-1, 2);
		cd.makeYWall(def.getSquare("wall"), 2, cd.height-1, 4);
		cd.place(def.getSquare("connection"), 0, 3).id = "con1";
		cd.place(def.getSquare("connection"), cd.width-1, 3).id = "con2";
		cd.place(def.getSquare("wall"), 2, 2);
		
		cd.assignTextures();
		addComputer(cd);
	}
	
	public void addComputer(ComputerDef cd) {
		computers.put(cd.name(), cd);
	}
	
	public ComputerDef getComputer(String name) {
		ComputerDef comp = computers.get(name);
		if(comp == null) {
			throw new IllegalArgumentException("No computer named " + name);
		}
		return comp;
	}
	
}
