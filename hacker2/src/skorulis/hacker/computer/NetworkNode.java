package skorulis.hacker.computer;

import skorulis.hacker.def.NodePosDef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class NetworkNode extends Actor implements Disposable {

	public NodePosDef def;
	private Texture texture;
	
	
	public NetworkNode(NodePosDef def) {		
		this.def = def;
		texture = new Texture(Gdx.files.internal(def.node.texture));
		this.setPosition(def.location.x - texture.getWidth()/2, def.location.y - texture.getHeight()/2);
		this.setSize(texture.getWidth(), texture.getHeight());
	}
	
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, getX(), getY());
	}

	public void dispose() {
		texture.dispose();
	}
	
	public Actor hit(float x, float y, boolean touchable) {
		//System.out.println("Bounds (" + this.getX() + "," + this.getY() + ") -> (" + this.getWidth() + " , " + this.getHeight() + ")");
		//System.out.println("H (" + x + "," + y + ")");
		if(x >= this.getX() && x <= this.getX() + this.getWidth() && y >= this.getY() && y <= this.getY() + this.getHeight()) {
			//System.out.println("H (" + x + "," + y + ")");
			//System.out.println("name " + this.def.name());
			return this;
		}
		return null;
	}
	
	public String toString() {
		return "computer " + def.name();
	}
	
}
