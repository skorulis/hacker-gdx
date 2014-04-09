package skorulis.hacker.level;

import skorulis.hacker.computer.Computer;
import skorulis.hacker.computer.square.ComputerSquare;


public class NetworkConnection {

	public NetworkNode node1;
	public NetworkNode node2;
	private String squareId1;
	private String squareId2;
	
	public ComputerSquare square1;
	public ComputerSquare square2;
	
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
	
	public boolean matches(Computer computer, String squareId) {
		if(node1.computer == computer && squareId.equals(squareId1)) {
			return true;
		} else if(node2.computer == computer && squareId.equals(squareId2)) {
			return true;
		}
		return false;
	}
	
	
}
