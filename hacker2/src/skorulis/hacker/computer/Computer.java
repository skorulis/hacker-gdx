package skorulis.hacker.computer;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import skorulis.hacker.computer.square.CompSquareLayer;
import skorulis.hacker.computer.square.ComputerSquare;

public class Computer extends Group {
	
	private String name;
	public int width,height;
	public ComputerSquare[][] squares;
	
	public Computer(String name,int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
		squares = new ComputerSquare[height][width];
		for(int i = 0 ; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				squares[i][j] = new ComputerSquare();
				squares[i][j].setPosition(j*ComputerSquare.SQUARE_SIZE, i*ComputerSquare.SQUARE_SIZE);
				this.addActor(squares[i][j]);
			}
		}
	}
	
	public void loadTextures(AssetManager assets) {
		for(int i = 0; i < height; ++i) { 
			for(int j = 0; j < width; ++j) {
				squares[i][j].loadTextures(assets);
			}
		}
	}
	
	public void draw(Batch batch, float alpha) {
		drawChildren(batch, alpha);
	}
	
	public ComputerSquare squareAtLocation(float xPos, float yPos) {
		if(xPos < 0 || yPos < 0) {
			return null;
		}
		int x = (int) (xPos / ComputerSquare.SQUARE_SIZE);
		int y = (int) (yPos / ComputerSquare.SQUARE_SIZE);
		if(x >= width || y >= height) {
			return null;
		}
		return squares[y][x];
	}

	public ArrayList<ComputerSquare> adjacentSquares(ComputerSquare square) {
		int x = (int) (square.getX() / ComputerSquare.SQUARE_SIZE);
		int y = (int) (square.getY() / ComputerSquare.SQUARE_SIZE);
		
		ArrayList<ComputerSquare> ret = new ArrayList<ComputerSquare>();
		
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
	
	public ComputerSquare findSquare(String id) {
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				for(CompSquareLayer layer : squares[i][j].layers) {
					if(id.equals(layer.id)) {
						return squares[i][j];
					}
				}
			}
		}
		return null;
	}
	
	public String name() {
		return name;
	}
	
}
