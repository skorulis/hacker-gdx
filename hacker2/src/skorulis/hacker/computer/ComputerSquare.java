package skorulis.hacker.computer;

import skorulis.hacker.def.square.ComputerSquareDef;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ComputerSquare extends Actor {

	public ComputerSquareDef def;
	private Texture texture;
	
	public ComputerSquare(ComputerSquareDef def,AssetManager assets) {
		this.def = def;
		texture = assets.get(def.textureName);
	}
	
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, getX(), getY());
	}
	
}
