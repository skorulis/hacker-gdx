package skorulis.hacker.computer;

import skorulis.hacker.def.ConnectionDef;

public class NetworkConnection {

	public ConnectionDef def;
	public NetworkNode node1;
	public NetworkNode node2;
	
	public NetworkConnection(ConnectionDef def) {
		this.def = def;
	}
	
}
