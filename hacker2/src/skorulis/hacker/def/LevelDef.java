package skorulis.hacker.def;

import java.util.ArrayList;

import skorulis.hacker.def.square.CompSquareDef;

public class LevelDef extends BaseDef {

	public ArrayList<NodePosDef> computers;
	public ArrayList<ConnectionDef> connections;
	public NodePosDef entryComputer;
	
	public LevelDef(String name) {
		super(name);
		computers = new ArrayList<NodePosDef>();
		connections = new ArrayList<ConnectionDef>();
	}
	
	public void createConnection(NodePosDef comp1, NodePosDef comp2,CompSquareDef square1, CompSquareDef square2) {
		ConnectionDef cd = new ConnectionDef(comp1, comp2, square1, square2);
		this.connections.add(cd);
	}
	
	public NodePosDef findComputer(String name) {
		for(NodePosDef cpd : computers) {
			if(cpd.name().equals(name)) {
				return cpd;
			}
		}
		throw new IllegalArgumentException("Could not find computer named " + name);
	}
	
}
