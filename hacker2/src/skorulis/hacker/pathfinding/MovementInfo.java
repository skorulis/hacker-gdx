package skorulis.hacker.pathfinding;

import skorulis.hacker.computer.square.ComputerSquare;

import com.badlogic.gdx.math.Vector2;

public class MovementInfo {

	public float travelTime;
	public float currentTime;
	
	public Vector2 startLoc;
	public Vector2 endLoc;
	
	public ComputerSquare destSquare;
	
	public MovementInfo(Vector2 startLoc, Vector2 endLoc,float speed) {
		this.startLoc = startLoc.cpy();
		this.endLoc = endLoc.cpy();
		this.travelTime = startLoc.cpy().sub(endLoc).len() / speed;
	}
	
	public Vector2 update(float delta) {
		currentTime += delta;
		currentTime = Math.min(currentTime, travelTime);
		
		float pct = currentTime / travelTime;
		Vector2 v1 = startLoc.cpy().scl(1-pct);
		Vector2 v2 = endLoc.cpy().scl(pct);
		
		return v1.add(v2);
	}
	
	public boolean finished() {
		return currentTime == travelTime;
	}
	
}
