package skorulis.hacker.avatar;

import skorulis.hacker.computer.Computer;
import skorulis.hacker.computer.ComputerSquare;
import skorulis.hacker.computer.NetworkConnection;
import skorulis.hacker.computer.NetworkNode;
import skorulis.hacker.pathfinding.ComputerPath;
import skorulis.hacker.pathfinding.MovementInfo;
import skorulis.hacker.pathfinding.PathFinder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Avatar extends Actor {

	private Texture texture;
	private AvatarDelegate delegate;
	
	//Network data
	public NetworkNode currentNode;
	public NetworkConnection currentConnection;
	public NetworkNode destinationNode;
	
	//Computer data
	public Computer currentComputer;
	public ComputerSquare currentSquare;
	public ComputerSquare destinationSquare;
	public ComputerPath currentPath;
	
	public MovementInfo movement;
	
	public float speed = 200;
	
	public Avatar(NetworkNode computer,AvatarDelegate delegate) {
		this.currentNode = computer;
		this.delegate = delegate;
		texture = new Texture(Gdx.files.internal("data/eye.png"));
		this.setLocation(computer.def.location);
	}
	
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, getX(), getY());
	}
	
	public void setLocation(Vector2 loc) {
		this.setPosition(loc.x - texture.getWidth()/2, loc.y - texture.getHeight()/2);
	}
	
	public void travelTo(NetworkNode c) {
		destinationNode = c;
		movement = new MovementInfo(currentNode.def.location, destinationNode.def.location, speed);
	}
	
	public void travelAlong(NetworkConnection connection) {
		currentConnection = connection;
		travelTo(connection.otherNode(currentNode));
	}
	
	public void moveTo(ComputerSquare square) {
		destinationSquare = square;
		PathFinder finder = new PathFinder(currentComputer,currentSquare,destinationSquare);
		currentPath = finder.generatePath();
		movement = currentPath.getMovement(speed/2 );
	}
	
	public void act(float delta) {
		if(destinationNode != null) {
			moveInNetwork(delta);
		} else if(currentPath != null) {
			moveInComputer(delta);
		}
	}
	
	private void moveInNetwork(float delta) {
		setLocation(movement.update(delta));
		if(movement.finished()) {
			currentNode = destinationNode;
			destinationNode = null;
			delegate.avatarDidReachNode(this,currentNode,currentConnection);
			currentConnection = null;
			movement = null;
		}
	}
	
	private void moveInComputer(float delta) {
		setLocation(movement.update(delta));
		if(movement.finished()) {
			currentSquare = currentPath.nextNode();
			if(currentPath.finished()) {
				movement = null;
				currentPath = null;
			} else {
				movement = currentPath.next(speed/2);
			}
		}
		
	}
	
	
}
