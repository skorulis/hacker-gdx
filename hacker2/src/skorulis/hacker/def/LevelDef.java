package skorulis.hacker.def;

import java.util.ArrayList;

public class LevelDef extends BaseDef {

	public ArrayList<ComputerPosDef> computers;
	public ArrayList<ConnectionDef> connections;
	
	public LevelDef(String name) {
		super(name);
		computers = new ArrayList<ComputerPosDef>();
		connections = new ArrayList<ConnectionDef>();
	}
	
	public void createConnection(String comp1, String comp2) {
		ComputerPosDef cpd1 = null;
		ComputerPosDef cpd2 = null;
		for(ComputerPosDef cpd : computers) {
			if(cpd.name().equals(comp1)) {
				cpd1 = cpd;
			} else if(cpd.name().equals(comp2)) {
				cpd2 = cpd;
			}
		}
		if(cpd1 == null || cpd2 == null) {
			throw new IllegalArgumentException("Could not connect " + comp1 + " to " + comp2);
		}
		ConnectionDef cd = new ConnectionDef(cpd1, cpd2);
		this.connections.add(cd);
	}
	
}
