package skorulis.hacker.pathfinding;

import java.util.List;
import com.badlogic.gdx.math.Vector2;

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
	
	public MovementInfo getMovement(float speed) {
		Vector2 v1 = nodes.get(nodeIndex).square.getCentreLoc();
		Vector2 v2 = nodes.get(nodeIndex + 1).square.getCentreLoc();
		MovementInfo mi = new MovementInfo(v1, v2, speed);
		return mi;
	}
	
	public MovementInfo next(float speed) {
		nodeIndex ++;
		return getMovement(speed);
	}
	
	public boolean finished() {
		return nodeIndex == this.nodes.size() - 2;
	}
	
}
