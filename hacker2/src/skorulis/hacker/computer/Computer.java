package skorulis.hacker.computer;



import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import skorulis.hacker.def.ComputerDef;
import skorulis.hacker.def.square.CompSquareLayer;

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
	
	public void handleTap(float x, float y) {
		ComputerSquare cs = squareAtLocation(x, y);
		if(cs != null) {
			System.out.println("SQUARE");
			
		}
	}
	
	public ComputerSquare squareAtLocation(float xPos, float yPos) {
		if(xPos < 0 || yPos < 0) {
			return null;
		}
		int x = (int) (xPos / ComputerSquare.SQUARE_SIZE);
		int y = (int) (xPos / ComputerSquare.SQUARE_SIZE);
		if(x >= def.width || y >= def.height) {
			return null;
		}
		return squares[y][x];
	}
	
	public ComputerSquare findSquare(String id) {
		for(int i = 0; i < def.height; ++i) { 
			for(int j = 0; j < def.width; ++j) {
				for(CompSquareLayer csl : squares[i][j].def.layers) {
					if(id.equals(csl.id)) {
						return squares[i][j];
					}
				}
			}
		}
		return null;
	}
	
}
