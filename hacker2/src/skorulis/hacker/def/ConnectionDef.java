package skorulis.hacker.def;

import skorulis.hacker.level.NetworkNode;

public class ConnectionDef {

	public NetworkNode comp1;
	public NetworkNode comp2;
	public String squareId1;
	public String squareId2;
	
	public ConnectionDef(NetworkNode comp1, NetworkNode comp2, String square1, String square2) {
		this.comp1 = comp1;
		this.comp2 = comp2;
		this.squareId1 = square1;
		this.squareId2 = square2;
	}
	
}
