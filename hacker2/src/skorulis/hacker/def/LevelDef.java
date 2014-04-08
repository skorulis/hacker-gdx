package skorulis.hacker.def;

import java.util.ArrayList;

import skorulis.hacker.level.NetworkNode;

import com.badlogic.gdx.scenes.scene2d.Group;

public class LevelDef extends Group {

	private final String name;
	
	public ArrayList<NetworkNode> computers;
	public ArrayList<ConnectionDef> connections;
	public NetworkNode entryComputer;
	
	public LevelDef(String name) {
		this.name = name;
		computers = new ArrayList<NetworkNode>();
		connections = new ArrayList<ConnectionDef>();
	}
	
	public void createConnection(NetworkNode comp1, NetworkNode comp2,String square1, String square2) {
		ConnectionDef cd = new ConnectionDef(comp1, comp2, square1, square2);
		this.connections.add(cd);
	}
	
	public NetworkNode findComputer(String name) {
		for(NetworkNode cpd : computers) {
			if(cpd.name().equals(name)) {
				return cpd;
			}
		}
		throw new IllegalArgumentException("Could not find computer named " + name);
	}
	
	public String name() {
		return name;
	}
	
}
