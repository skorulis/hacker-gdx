package skorulis.hacker.def;

import java.util.ArrayList;

import skorulis.hacker.level.NetworkNode;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;

public class LevelDef extends Group {

	private final String name;
	
	public ArrayList<NetworkNode> computers;
	public ArrayList<ConnectionDef> connections;
	public NetworkNode entryComputer;
	public ShapeRenderer shapeRenderer;
	
	public LevelDef(String name) {
		this.name = name;
		computers = new ArrayList<NetworkNode>();
		connections = new ArrayList<ConnectionDef>();
		
		shapeRenderer = new ShapeRenderer();
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
	
	public NetworkNode findEntryComputer() {
		for (NetworkNode c : computers) {
			if (c.name().equals(entryComputer.name())) {
				return c;
			}
		}
		return null;
	}
	
	public NetworkNode findNode(String name) {
		for(NetworkNode node : computers) {
			if(node.name().equals(name)) {
				return node;
			}
		}
		throw new IllegalArgumentException("Could not find node " + name);
	}
	
	public String name() {
		return name;
	}
	
}
