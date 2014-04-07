package skorulis.hacker.pathfinding;

import java.util.List;

import skorulis.hacker.computer.ComputerSquare;

public class ComputerPath {

	private List<PathNodeInfo> nodes;
	private int nodeIndex;
	
	public ComputerPath(List<PathNodeInfo> nodes) {
		this.nodes = nodes;
		nodeIndex = 0;
	}
	
	public ComputerSquare currentNode() {
		return this.nodes.get(nodeIndex).square;
	}
	
	public ComputerSquare nextNode() {
		return this.nodes.get(nodeIndex + 1).square;
	}
	
	public void next() {
		
	}
	
	private void update() {
		
	}
	
}
