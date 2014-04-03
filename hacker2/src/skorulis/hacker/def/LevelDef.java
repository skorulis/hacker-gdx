package skorulis.hacker.def;

import java.util.ArrayList;

public class LevelDef extends BaseDef {

	public ArrayList<NodePosDef> computers;
	public ArrayList<ConnectionDef> connections;
	public NodePosDef entryComputer;
	
	public LevelDef(String name) {
		super(name);
		computers = new ArrayList<NodePosDef>();
		connections = new ArrayList<ConnectionDef>();
	}
	
	public void createConnection(String comp1, String comp2) {
		NodePosDef cpd1 = findComputer(comp1);
		NodePosDef cpd2 = findComputer(comp2);
		
		ConnectionDef cd = new ConnectionDef(cpd1, cpd2);
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
