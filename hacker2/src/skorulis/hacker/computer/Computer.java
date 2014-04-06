package skorulis.hacker.computer;



import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import skorulis.hacker.def.ComputerDef;

public class Computer extends Group {

	
	public ComputerDef def;
	public ComputerSquare squares[][];
	
	
	public Computer(ComputerDef def, AssetManager assets) {
		this.def = def;
		squares = new ComputerSquare[def.height][def.width];
		for(int i = 0; i < def.height; ++i) { 
			for(int j = 0; j < def.width; ++j) {
				squares[i][j] = new ComputerSquare(def.squares[i][j], assets);
				squares[i][j].setPosition(j*ComputerSquare.SQUARE_SIZE, i*ComputerSquare.SQUARE_SIZE);
				this.addActor(squares[i][j]);
			}
		}
	}
	
	public void draw(Batch batch, float alpha) {
		//batch.draw(texture, getX(), getY());
		
		drawChildren(batch, alpha);
	}
	
}
