package skorulis.hacker.def;

import com.badlogic.gdx.math.Vector2;

public class ComputerPosDef extends BaseDef {

	public ComputerDef computer;
	public Vector2 location;
	
	public ComputerPosDef(String name, ComputerDef computer, int x, int y) {
		super(name);
		this.computer = computer;
		this.location = new Vector2(x, y);
	}
	
}
