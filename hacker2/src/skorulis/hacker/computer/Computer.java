package skorulis.hacker.computer;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import skorulis.hacker.def.ComputerDef;

public class Computer extends Actor {

	public ComputerDef def;
	public ArrayList<ComputerSquare> squares;
	
	public Computer(ComputerDef def) {
		this.def = def;
	}
	
	public void draw(Batch batch, float alpha) {
		//batch.draw(texture, getX(), getY());
	}
	
}
