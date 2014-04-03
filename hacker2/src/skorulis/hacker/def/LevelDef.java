package skorulis.hacker.def;

import java.util.ArrayList;

public class LevelDef extends BaseDef {

	public ArrayList<ComputerPosDef> computers;
	public ArrayList<ConnectionDef> connections;
	public ComputerPosDef entryComputer;
	
	public LevelDef(String name) {
		super(name);
		computers = new ArrayList<ComputerPosDef>();
		connections = new ArrayList<ConnectionDef>();
	}
	
	public void createConnection(String comp1, String comp2) {
		ComputerPosDef cpd1 = findComputer(comp1);
		ComputerPosDef cpd2 = findComputer(comp2);
		
		ConnectionDef cd = new ConnectionDef(cpd1, cpd2);
		this.connections.add(cd);
	}
	
	public ComputerPosDef findComputer(String name) {
		for(ComputerPosDef cpd : computers) {
			if(cpd.name().equals(name)) {
				return cpd;
			}
		}
		throw new IllegalArgumentException("Could not find computer named " + name);
	}
	
}
