package skorulis.hacker.def;

import com.badlogic.gdx.scenes.scene2d.Group;

import skorulis.hacker.computer.ComputerSquare;
import skorulis.hacker.def.square.CompSquareDef;
import skorulis.hacker.def.square.CompSquareLayer;

public class ComputerDef extends Group {
	
	private String name;
	public int width,height;
	public CompSquareDef[][] squares;
	
	public ComputerDef(String name,int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
		squares = new CompSquareDef[height][width];
		for(int i = 0 ; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				squares[i][j] = new CompSquareDef();
				squares[i][j].setPosition(j*CompSquareDef.SQUARE_SIZE, i*CompSquareDef.SQUARE_SIZE);
			}
		}
	}
	
	public void assignTextures() {
		CompSquareDef square;
		CompSquareDef north,south,east, west;
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
	
	public CompSquareDef findSquare(String id) {
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
