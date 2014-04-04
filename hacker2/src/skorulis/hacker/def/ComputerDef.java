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
	
}
