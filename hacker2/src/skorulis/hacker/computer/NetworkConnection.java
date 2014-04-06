package skorulis.hacker.computer;

import skorulis.hacker.def.ConnectionDef;

public class NetworkConnection {

	public ConnectionDef def;
	public NetworkNode node1;
	public NetworkNode node2;
	
	public NetworkConnection(ConnectionDef def) {
		this.def = def;
	}
	
	public boolean hasNodes(NetworkNode n1, NetworkNode n2) {
		return (node1 == n1 && node2 == n2) || (node2 == n1 && node1 == n2);
	}
	
	public NetworkNode otherNode(NetworkNode node) {
		if(node == node1) {
			return node2;
		} else if(node == node2) {
			return node1;
		}
		throw new IllegalArgumentException("Connection does not have node " + node);
	}
	
}
