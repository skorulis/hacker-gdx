package skorulis.hacker.computer;



import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import skorulis.hacker.def.ComputerDef;

public class Computer extends Group {

	private static int SQUARE_SIZE = 32;
	
	public ComputerDef def;
	public ComputerSquare squares[];
	
	
	public Computer(ComputerDef def, AssetManager assets) {
		this.def = def;
		squares = new ComputerSquare[def.squares.length];
		for(int i = 0; i < def.squares.length; ++i) { 
			squares[i] = new ComputerSquare(def.squares[i],assets);
			int x = i % def.width;
			int y = i / def.width;
			squares[i].setPosition(x*SQUARE_SIZE, y*SQUARE_SIZE);
			this.addActor(squares[i]);
		}
	}
	
	public void draw(Batch batch, float alpha) {
		//batch.draw(texture, getX(), getY());
		
		drawChildren(batch, alpha);
	}
	
}
