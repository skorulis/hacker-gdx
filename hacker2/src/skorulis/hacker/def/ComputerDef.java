package skorulis.hacker.def;

import skorulis.hacker.def.square.ComputerSquareDef;


public class ComputerDef extends BaseDef {
	
	public int width,height;
	public ComputerSquareDef[] squares;
	
	public ComputerDef(String name,int width, int height) {
		super(name);
		this.width = width;
		this.height = height;
		squares = new ComputerSquareDef[width*height];
	}
	
	public void fillWith(ComputerSquareDef square) {
		for(int i = 0; i < squares.length; ++i) {
			squares[i] = square;
		}
	}
	
	public void makeXWall(ComputerSquareDef square, int x1, int x2, int y) {
		for(int i = x1; i <= x2; ++i) {
			squares[y*width + i] = square;
		}
	}
	
}
