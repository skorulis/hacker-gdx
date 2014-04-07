package skorulis.hacker.pathfinding;

import skorulis.hacker.computer.ComputerSquare;

public class PathNodeInfo {
	
	public PathNodeInfo parent;
	public ComputerSquare square;
	public int value;
	public int heuristic;
	
	public PathNodeInfo(ComputerSquare square) {
		this.square = square;
	}
	
	public PathNodeInfo(ComputerSquare square,PathNodeInfo parent) {
		this.square = square;
		this.parent = parent;
	}
	
	public int score() {
		return value + heuristic;
	}
	
}
