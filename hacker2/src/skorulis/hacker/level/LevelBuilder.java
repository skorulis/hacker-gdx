package skorulis.hacker.level;

import skorulis.hacker.def.ConnectionDef;
import skorulis.hacker.def.LevelDef;

public class LevelBuilder {

	public LevelDef level;
	
	public LevelBuilder(LevelDef level) {
		this.level = level;
	}
	
	public void addNode(NetworkNode node) {
		level.computers.add(node);
	}
	
	public void createConnection(NetworkNode comp1, NetworkNode comp2,String square1, String square2) {
		ConnectionDef cd = new ConnectionDef(comp1, comp2, square1, square2);
		level.connections.add(cd);
	}
	
}
