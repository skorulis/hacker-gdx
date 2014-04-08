package skorulis.hacker.level;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;

import skorulis.hacker.avatar.Avatar;
import skorulis.hacker.avatar.AvatarDelegate;
import skorulis.hacker.computer.Computer;
import skorulis.hacker.computer.square.ComputerSquare;
import skorulis.hacker.def.ConnectionDef;
import skorulis.hacker.def.LevelDef;

public class NetworkLevel extends Group implements Disposable,
		AvatarDelegate {

	private LevelDef def;
	private ArrayList<Avatar> avatars;
	private ArrayList<NetworkConnection> connections;
	private Avatar playerAvatar;
	
	public Vector3 translation;
	private Computer openComputer;

	public NetworkLevel(LevelDef def, AssetManager assets) {
		this.def = def;

		translation = new Vector3();
		avatars = new ArrayList<Avatar>();
		connections = new ArrayList<NetworkConnection>();

		buildLevel(assets);

	}
	
	public void start() {
		playerAvatar = new Avatar(def.findEntryComputer(), this);
		this.addActor(playerAvatar);
		avatars.add(playerAvatar);
		avatarDidReachNode(playerAvatar, playerAvatar.currentNode,null);
	}

	private void buildLevel(AssetManager assets) {
		for (NetworkNode cd : this.def.computers) {
			cd.loadTextures(assets);
			this.addActor(cd);
		}
		for(ConnectionDef cd : this.def.connections) {
			NetworkConnection connection = new NetworkConnection(cd);
			connection.node1 = def.findNode(cd.comp1.name());
			connection.node2 = def.findNode(cd.comp2.name());
			connections.add(connection);
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Matrix4 transform = new Matrix4();
		transform.translate(translation);
		batch.end();
		def.shapeRenderer.setTransformMatrix(transform);
		def.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		def.shapeRenderer.begin(ShapeType.Line);
		def.shapeRenderer.setColor(Color.RED);
		for (NetworkConnection nc : connections) {
			def.shapeRenderer.line(nc.def.comp1.location, nc.def.comp2.location);
		}
		def.shapeRenderer.end();

		batch.begin();
		batch.setTransformMatrix(transform);
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
		for (NetworkNode n : def.computers) {
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

	public void dispose() {

	}
	
	public Computer openComputer() {
		return openComputer;
	}
	
	public Avatar playerAvatar() {
		return playerAvatar;
	}

}
