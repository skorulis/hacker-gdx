package skorulis.hacker.computer;

import skorulis.hacker.def.square.ComputerSquareDef;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ComputerSquare extends Actor {

	public ComputerSquareDef def;
	
	public ComputerSquare(ComputerSquareDef def) {
		this.def = def;
	}
	
	public void draw(Batch batch, float alpha) {
		//batch.draw(texture, getX(), getY());
	}
	
}
