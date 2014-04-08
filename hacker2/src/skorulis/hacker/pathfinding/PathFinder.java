package skorulis.hacker.pathfinding;

import java.util.ArrayList;
import java.util.Collections;

import skorulis.hacker.computer.Computer;
import skorulis.hacker.computer.square.ComputerSquare;

public class PathFinder {

	private Computer computer;
	private ComputerSquare from;
	private ComputerSquare to;
	private ArrayList<PathNodeInfo> openList;
	private ArrayList<PathNodeInfo> closedList;
	
	public PathFinder(Computer computer, ComputerSquare from, ComputerSquare to) {
		this.computer = computer;
		this.from = from;
		this.to = to;
		openList = new ArrayList<PathNodeInfo>();
		closedList = new ArrayList<PathNodeInfo>();
	}
	
	public ComputerPath generatePath() {
		PathNodeInfo pni = new PathNodeInfo(from);
		pni.heuristic = calculateHeuristic(pni.square, to);
		openList.add(pni);
		
		while(true) {
			pni = removeNext();
			if(pni == null) {
				break;
			} else if(squaresEqual(pni.square, to)) {
				return buildPath(pni);
			} else {
				evaluate(pni);
			}	
		}
		
		return null;
	}
	
	private ComputerPath buildPath(PathNodeInfo pni) {
		ArrayList<PathNodeInfo> nodes = new ArrayList<PathNodeInfo>();
		while(pni != null) {
			nodes.add(pni);
			pni = pni.parent;
		}
		Collections.reverse(nodes);
		return new ComputerPath(nodes);
	}
	
	private PathNodeInfo removeNext() {
		if(openList.isEmpty()) {
			return null;
		}
		PathNodeInfo best = openList.get(0);
		for(PathNodeInfo pni : openList) {
			boolean better = pni.score() < best.score() || (pni.score() == best.score() && pni.value > best.value); 
			if(better) {
				best = pni;
			}
		}
		openList.remove(best);
		return best;
	}
	
	private void evaluate(PathNodeInfo parentNode) {
		closedList.add(parentNode);
		ArrayList<ComputerSquare> adj = computer.adjacentSquares(parentNode.square);
		for(ComputerSquare cs : adj) {
			if(!cs.isPassable()) {
				continue;
			}
			
			PathNodeInfo pni = new PathNodeInfo(cs,parentNode);
			pni.value = parentNode.value + calculateHeuristic(parentNode.square, pni.square);
			pni.heuristic = calculateHeuristic(pni.square, to);
			PathNodeInfo match = findNode(cs);
			
			if(match == null) {
				openList.add(pni);
			} else {
				if(pni.value < match.value) {
					openList.remove(match);
					closedList.remove(match);
					openList.add(pni);
				}
			}
		}
	}
	
	private PathNodeInfo findNode(ComputerSquare square) {
		for(PathNodeInfo pni : openList) {
			if(squaresEqual(pni.square, square)) {
				return pni;
			}
		}
		
		for(PathNodeInfo pni : closedList) {
			if(squaresEqual(pni.square, square)) {
				return pni;
			}
		}
		
		return null;
	}
	
	private boolean squaresEqual(ComputerSquare s1, ComputerSquare s2) {
		return s1.getX() == s2.getX() && s1.getY() == s2.getY();
	}
	
	private int calculateHeuristic(ComputerSquare square, ComputerSquare dest) {
		if(square == null) { throw new IllegalArgumentException("Missing from"); }
		if(dest == null) { throw new IllegalArgumentException("Missing dest"); }
		int x = (int) (dest.getX() - square.getX());
		int y = (int) (dest.getY() - square.getY());
		x = Math.abs(x);
		y = Math.abs(y);
		
		int min = Math.min(x, y);
		int max = Math.max(x, y);
		
		int diff = max - min;
		
		return (int)(diff + (max - diff) * 1.4);
	}
	
}
