package skorulis.hacker.computer;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import skorulis.hacker.def.ComputerDef;
import skorulis.hacker.def.square.CompSquareLayer;
import skorulis.hacker.def.square.ComputerSquare;

public class Computer extends Group {
	
	public ComputerDef def;
	
	public Computer(ComputerDef def, AssetManager assets) {
		this.def = def;
		for(int i = 0; i < def.height; ++i) { 
			for(int j = 0; j < def.width; ++j) {
				def.squares[i][j].loadTextures(assets);
				this.addActor(def.squares[i][j]);
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
		if(x >= def.width || y >= def.height) {
			return null;
		}
		return def.squares[y][x];
	}
	
	public ComputerSquare findSquare(String id) {
		for(int i = 0; i < def.height; ++i) { 
			for(int j = 0; j < def.width; ++j) {
				for(CompSquareLayer csl : def.squares[i][j].layers) {
					if(id.equals(csl.id)) {
						return def.squares[i][j];
					}
				}
			}
		}
		return null;
	}
	
	public ArrayList<ComputerSquare> adjacentSquares(ComputerSquare square) {
		int x = (int) (square.getX() / ComputerSquare.SQUARE_SIZE);
		int y = (int) (square.getY() / ComputerSquare.SQUARE_SIZE);
		
		ArrayList<ComputerSquare> ret = new ArrayList<ComputerSquare>();
		
		for(int i = y - 1; i <= y + 1; ++i) {
			for(int j = x - 1; j <= x + 1; ++j) {
				if(i < 0 || i >= def.height || j < 0 || j >= def.width) {
					continue;
				}
				if(i == y && j == x) {
					continue;
				}
				ret.add(def.squares[i][j]);
			}
		}
		return ret;
	}
	
}
