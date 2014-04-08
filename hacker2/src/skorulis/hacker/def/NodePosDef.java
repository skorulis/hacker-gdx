package skorulis.hacker.def;

import skorulis.hacker.computer.Computer;
import skorulis.hacker.computer.square.ComputerSquare;

import com.badlogic.gdx.math.Vector2;

public class NodePosDef extends BaseDef {

	public NodeDef node;
	public Computer computer;
	public Vector2 location;
	
	public NodePosDef(String name, NodeDef node, int x, int y) {
		super(name);
		this.node = node;
		this.location = new Vector2(x, y);
	}
	
	public NodePosDef(String name, NodeDef node, Computer comp, int x, int y) {
		this(name,node,x,y);
		this.computer = comp;
	}
	
	public ComputerSquare findSquare(String id) {
		if(computer == null) {
			return null;
		}
		return computer.findSquare(id);
	}
	
}
