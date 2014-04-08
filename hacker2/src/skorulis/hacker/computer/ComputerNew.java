package skorulis.hacker.computer;

import java.util.ArrayList;

import skorulis.hacker.def.square.CompSquareLayer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class ComputerNew extends Group {

	public ComputerSquareNew squares[][];
	public int height;
	public int width;
	
	public ComputerNew(int width, int height, AssetManager assets) {
		this.width = width;
		this.height = height;
		squares = new ComputerSquareNew[height][width];
		for(int i = 0; i < height; ++i) { 
			for(int j = 0; j < width; ++j) {
				squares[i][j] = new ComputerSquareNew(assets);
				squares[i][j].setPosition(j*ComputerSquare.SQUARE_SIZE, i*ComputerSquare.SQUARE_SIZE);
				this.addActor(squares[i][j]);
			}
		}
	}
	
	public void draw(Batch batch, float alpha) {
		drawChildren(batch, alpha);
	}
	
	public ComputerSquareNew squareAtLocation(float xPos, float yPos) {
		if(xPos < 0 || yPos < 0) {
			return null;
		}
		int x = (int) (xPos / ComputerSquareNew.SQUARE_SIZE);
		int y = (int) (yPos / ComputerSquareNew.SQUARE_SIZE);
		if(x >= width || y >= height) {
			return null;
		}
		return squares[y][x];
	}
	
	/*public ComputerSquareNew findSquare(String id) {
		for(int i = 0; i < height; ++i) { 
			for(int j = 0; j < width; ++j) {
				for(CompSquareLayer csl : squares[i][j].def.layers) {
					if(id.equals(csl.id)) {
						return squares[i][j];
					}
				}
			}
		}
		return null;
	}*/
	
	public ArrayList<ComputerSquareNew> adjacentSquares(ComputerSquare square) {
		int x = (int) (square.getX() / ComputerSquare.SQUARE_SIZE);
		int y = (int) (square.getY() / ComputerSquare.SQUARE_SIZE);
		
		ArrayList<ComputerSquareNew> ret = new ArrayList<ComputerSquareNew>();
		
		for(int i = y - 1; i <= y + 1; ++i) {
			for(int j = x - 1; j <= x + 1; ++j) {
				if(i < 0 || i >= height || j < 0 || j >= width) {
					continue;
				}
				if(i == y && j == x) {
					continue;
				}
				ret.add(squares[i][j]);
			}
		}
		return ret;
	}
	
}
