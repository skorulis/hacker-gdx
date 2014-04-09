package skorulis.hacker.level;

import java.util.ArrayList;

import skorulis.hacker.avatar.Avatar;
import skorulis.hacker.avatar.AvatarDelegate;
import skorulis.hacker.computer.Computer;
import skorulis.hacker.computer.square.layer.CompSquareLayer;
import skorulis.hacker.computer.square.layer.ConnectionLayer;
import skorulis.hacker.computer.square.ComputerSquare;
import skorulis.hacker.def.terrain.TerrainLayerDef.TerrainAction;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;

public class NetworkLevel extends Group implements AvatarDelegate {

	private final String name;
	
	public ArrayList<NetworkNode> computers;
	public ArrayList<NetworkConnection> connections;
	public NetworkNode entryComputer;
	public ShapeRenderer shapeRenderer;
	
	private ArrayList<Avatar> avatars;
	private Avatar playerAvatar;
	
	
	private Computer openComputer;
	
	public NetworkLevel(String name) {
		this.name = name;
		computers = new ArrayList<NetworkNode>();
		connections = new ArrayList<NetworkConnection>();
		
		shapeRenderer = new ShapeRenderer();
		
		
		avatars = new ArrayList<Avatar>();
	}
	
	public void start() {
		playerAvatar = new Avatar(findEntryComputer(), this);
		this.addActor(playerAvatar);
		avatars.add(playerAvatar);
		avatarDidReachNode(playerAvatar, playerAvatar.currentNode,null);
	}
	
	public void loadTextures(AssetManager assets) {
		for(NetworkNode node: computers) {
			node.loadTextures(assets);
		}
	}
	
	public NetworkNode findComputer(String name) {
		for(NetworkNode cpd : computers) {
			if(cpd.name().equals(name)) {
				return cpd;
			}
		}
		throw new IllegalArgumentException("Could not find computer named " + name);
	}
	
	public NetworkNode findEntryComputer() {
		for (NetworkNode c : computers) {
			if (c.name().equals(entryComputer.name())) {
				return c;
			}
		}
		return null;
	}
	
	public NetworkNode findNode(String name) {
		for(NetworkNode node : computers) {
			if(node.name().equals(name)) {
				return node;
			}
		}
		throw new IllegalArgumentException("Could not find node " + name);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		batch.end();
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		for (NetworkConnection nc : connections) {
			shapeRenderer.line(nc.node1.location, nc.node2.location);
		}
		shapeRenderer.end();

		batch.begin();
		drawChildren(batch, parentAlpha);
	}

	@Override
	public void avatarDidReachNode(Avatar avatar, NetworkNode node, NetworkConnection connection) {
		if (avatar == playerAvatar) {
			if(node.computer != null) {
				System.out.println("REACH");
				
				String squareId = connection.squareIdForNode(node);
				ComputerSquare cs = node.computer.findSquare(squareId);
				
				this.removeActor(avatar);
				avatar.currentComputer = node.computer;
				avatar.setCurrentSquare(cs);
				avatar.currentNode.computer.addActor(avatar);
				avatar.setPosition(cs.getX(), cs.getY());
				openComputer(avatar.currentNode);
			}
		}
	}
	
	public void avatarDidPerformAction(Avatar avatar, Computer computer, ComputerSquare square) {
		CompSquareLayer layer = square.getActionLayer();
		if(layer != null) {
			if(layer.def.action == TerrainAction.EXIT) {
				exitComputer(avatar, computer, square, (ConnectionLayer) layer);
			}
		}
	}
	
	private void exitComputer(Avatar avatar, Computer computer, ComputerSquare square, ConnectionLayer layer) {
		NetworkConnection connection = findConnection(computer, layer.id);
		computer.removeActor(avatar);
		closeComputer(computer);
		this.addActor(avatar);
		avatar.travelAlong(connection);
	}
	
	private void closeComputer(Computer computer) {
		this.openComputer = null;
		this.removeActor(computer);
	}
	
	private void openComputer(NetworkNode node) {
		this.openComputer = node.computer;
		this.addActor(node.computer);
	}
	
	public void computerTap(float x, float y) {
		ComputerSquare cs = openComputer.squareAtLocation(x,y);
		if(cs != null && playerAvatar.currentSquare() != cs && cs.isPassable()) {
			playerAvatar.moveTo(cs);
		}
	}
	
	public void networkTap(float x, float y) {
		for (NetworkNode n : computers) {
			if (n.hit(x, y, true) != null) {
				if (n != playerAvatar.currentNode) {
					NetworkConnection connection = findConnection(playerAvatar.currentNode, n);
					if(connection != null) {
						playerAvatar.travelAlong(connection);
					}
				}
			}
		}
	}
	
	public NetworkConnection findConnection(NetworkNode node1, NetworkNode node2) {
		for(NetworkConnection con : connections) {
			if(con.hasNodes(node1, node2)) {
				return con;
			}
		}
		return null;
	}
	
	public NetworkConnection findConnection(Computer computer, String squareId) {
		for(NetworkConnection con : connections) {
			if(con.matches(computer, squareId)) {
				return con;
			}
		}
		return null;
	}

	public Computer openComputer() {
		return openComputer;
	}
	
	public Avatar playerAvatar() {
		return playerAvatar;
	}
	
	public String name() {
		return name;
	}
	
}
