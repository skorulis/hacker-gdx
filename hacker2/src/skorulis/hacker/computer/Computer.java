package skorulis.hacker.computer;

import skorulis.hacker.def.ComputerPosDef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class Computer extends Actor implements Disposable {

	private ComputerPosDef def;
	private Texture texture;
	
	
	public Computer(ComputerPosDef def) {		
		this.def = def;
		texture = new Texture(Gdx.files.internal("data/workgroup.png"));
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
		//System.out.println("Bounds (" + this.getOriginX() + "," + this.getOriginY() + ") -> (" + this.getRight() + " , " + this.getTop() + ")");
		//System.out.println("H (" + x + "," + y + ")");
		if(x >= this.getOriginX() && x <= this.getOriginX() + this.getWidth() && y >= this.getOriginY() && y <= this.getOriginY() + this.getHeight()) {
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
