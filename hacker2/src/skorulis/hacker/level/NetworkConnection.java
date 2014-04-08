package skorulis.hacker.level;


public class NetworkConnection {

	public NetworkNode node1;
	public NetworkNode node2;
	public String squareId1;
	public String squareId2;
	
	public NetworkConnection(NetworkNode node1, NetworkNode node2, String square1, String square2) {
		this.node1 = node1;
		this.node2 = node2;
		this.squareId1 = square1;
		this.squareId2 = square2;
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
	
	public String squareIdForNode(NetworkNode node) {
		if(node == node1) {
			return squareId1;
		} else {
			return squareId2;
		}
	}
	
	
}
