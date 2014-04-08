package skorulis.hacker.level;

import java.util.ArrayList;

import skorulis.hacker.computer.Computer;
import skorulis.hacker.computer.NetworkConnection;
import skorulis.hacker.computer.square.ComputerSquare;
import skorulis.hacker.def.NodeDef;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NetworkNode extends Actor {

	private String name;
	public NodeDef node;
	public Computer computer;
	public Texture texture;
	public Vector2 location;
	public ArrayList<NetworkConnection> connections;
	
	public NetworkNode(String name, NodeDef node, int x, int y) {
		this.name = name;
		this.node = node;
		this.location = new Vector2(x, y);
		connections = new ArrayList<NetworkConnection>();
	}
	
	public void loadTextures(AssetManager assets) {
		texture = (assets.get(node.texture, Texture.class));
		this.setPosition(location.x - texture.getWidth() / 2,
				location.y - texture.getHeight() / 2);
		this.setSize(texture.getWidth(), texture.getHeight());
		if(computer != null) {
			computer.loadTextures(assets);
		}
	}
	
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, getX(), getY());
	}
	
	public NetworkNode(String name, NodeDef node, Computer comp, int x, int y) {
		this(name,node,x,y);
		this.computer = comp;
	}
	
	public ComputerSquare findSquare(String id) {
		if(computer == null) {
			return null;
		}
		return computer.findSquare(id);
	}
	
	public String name() {
		return name;
	}
	
	public Actor hit(float x, float y, boolean touchable) {
		// System.out.println("Bounds (" + this.getX() + "," + this.getY() +
		// ") -> (" + this.getWidth() + " , " + this.getHeight() + ")");
		// System.out.println("H (" + x + "," + y + ")");
		if (x >= this.getX() && x <= this.getX() + this.getWidth()
				&& y >= this.getY() && y <= this.getY() + this.getHeight()) {
			// System.out.println("H (" + x + "," + y + ")");
			// System.out.println("name " + this.def.name());
			return this;
		}
		return null;
	}

	public String toString() {
		return "computer " + name();
	}
	
}
