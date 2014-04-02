package skorulis.hacker.def;

import com.badlogic.gdx.math.Vector2;

public class ComputerPosDef {

	public ComputerDef computer;
	public Vector2 location;
	
	public ComputerPosDef(ComputerDef computer, int x, int y) {
		this.computer = computer;
		this.location = new Vector2(x, y);
	}
	
}
