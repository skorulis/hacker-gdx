package skorulis.hacker.avatar;

import skorulis.hacker.computer.NetworkNode;
import skorulis.hacker.def.ConnectionDef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Avatar extends Actor {

	private Texture texture;
	private AvatarDelegate delegate;
	public NetworkNode currentNode;
	public ConnectionDef currentConnection;
	public NetworkNode destinationNode;
	
	public float travelTime;
	public float currentTime;
	
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
		travelTime = currentNode.def.location.cpy().sub(destinationNode.def.location).len();
		travelTime /= speed;
		currentTime = 0;
	}
	
	public void act(float delta) {
		if(destinationNode != null) {
			currentTime += delta;
			currentTime = Math.min(currentTime, travelTime);
			float pct = currentTime / travelTime;
			Vector2 v1 = currentNode.def.location.cpy().scl(1-pct);
			Vector2 v2 = destinationNode.def.location.cpy().scl(pct);
			setLocation(v1.add(v2));
			if(currentTime == travelTime) {
				currentNode = destinationNode;
				destinationNode = null;
				delegate.avatarDidReachNode(this,currentNode);
			}
		}
	}
}
