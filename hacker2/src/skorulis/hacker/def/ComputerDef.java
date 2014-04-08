package skorulis.hacker.def;

import com.badlogic.gdx.scenes.scene2d.Group;

import skorulis.hacker.def.square.ComputerSquare;
import skorulis.hacker.def.square.CompSquareLayer;

public class ComputerDef extends Group {
	
	private String name;
	public int width,height;
	public ComputerSquare[][] squares;
	
	public ComputerDef(String name,int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
		squares = new ComputerSquare[height][width];
		for(int i = 0 ; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				squares[i][j] = new ComputerSquare();
				squares[i][j].setPosition(j*ComputerSquare.SQUARE_SIZE, i*ComputerSquare.SQUARE_SIZE);
			}
		}
	}
	
	public void assignTextures() {
		ComputerSquare square;
		ComputerSquare north,south,east, west;
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				square = squares[i][j];
				south = i > 0 ? squares[i-1][j] : null;
				north = i < height - 1 ? squares[i+1][j] : null;
				west = j > 0 ? squares[i][j-1] : null;
				east = j < width - 1 ? squares[i][j+1] : null;
				square.assignTextures(north, east, south, west);
			}
		}
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
