package skorulis.hacker.computer;



import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import skorulis.hacker.def.ComputerDef;

public class Computer extends Group {

	public ComputerDef def;
	public ComputerSquare squares[];
	
	
	public Computer(ComputerDef def, AssetManager assets) {
		this.def = def;
		squares = new ComputerSquare[def.squares.length];
		for(int i = 0; i < def.squares.length; ++i) { 
			squares[i] = new ComputerSquare(def.squares[i],assets);
			this.addActor(squares[i]);
		}
	}
	
	/*public void draw(Batch batch, float alpha) {
		for(ComputerSquare s : squares) {
			
		}
		//batch.draw(texture, getX(), getY());
	}*/
	
}
