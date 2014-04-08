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
		ComputerBuilder builder = new ComputerBuilder(new Computer("comp1", 7, 7));
		builder.fillWith(def.getSquare("floor"));
		builder.makeEdgeWall(def.getSquare("wall"));
		builder.makeYWall(def.getSquare("wall"), 2, builder.height()-1, 2);
		builder.makeYWall(def.getSquare("wall"), 2, builder.height()-1, 4);
		builder.place(def.getSquare("connection"), 0, 3).id = "con1";
		builder.place(def.getSquare("connection"), builder.width()-1, 3).id = "con2";
		builder.place(def.getSquare("wall"), 2, 2);
		
		builder.computer.assignTextures();
		addComputer(builder.computer);
	}
	
	public void addComputer(Computer cd) {
		computers.put(cd.name(), cd);
	}
	
	public Computer getComputer(String name) {
		Computer comp = computers.get(name);
		if(comp == null) {
			throw new IllegalArgumentException("No computer named " + name);
		}
		return comp;
	}
	
}
