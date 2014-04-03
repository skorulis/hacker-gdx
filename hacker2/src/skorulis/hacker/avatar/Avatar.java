package skorulis.hacker.avatar;

import skorulis.hacker.computer.NetworkNode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Avatar extends Actor {

	private Texture texture;
	private AvatarDelegate delegate;
	public NetworkNode currentComputer;
	public NetworkNode destinationComputer;
	
	public float travelTime;
	public float currentTime;
	
	public Avatar(NetworkNode computer,AvatarDelegate delegate) {
		this.currentComputer = computer;
		this.delegate = delegate;
		texture = new Texture(Gdx.files.internal("data/twitter-icon.png"));
		this.setLocation(computer.def.location);
	}
	
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, getX(), getY());
	}
	
	public void setLocation(Vector2 loc) {
		this.setPosition(loc.x - texture.getWidth()/2, loc.y - texture.getHeight()/2);
	}
	
	public void travelTo(NetworkNode c) {
		destinationComputer = c;
		travelTime = currentComputer.def.location.cpy().sub(destinationComputer.def.location).len();
		travelTime /= 100;
		currentTime = 0;
	}
	
	public void act(float delta) {
		if(destinationComputer != null) {
			currentTime += delta;
			currentTime = Math.min(currentTime, travelTime);
			float pct = currentTime / travelTime;
			Vector2 v1 = currentComputer.def.location.cpy().scl(1-pct);
			Vector2 v2 = destinationComputer.def.location.cpy().scl(pct);
			setLocation(v1.add(v2));
			if(currentTime == travelTime) {
				currentComputer = destinationComputer;
				delegate.avatarDidReachNode(this,currentComputer);
			}
		}
	}
}
