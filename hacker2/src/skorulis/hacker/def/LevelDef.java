package skorulis.hacker.def;

import java.util.ArrayList;

public class LevelDef extends BaseDef {

	public ArrayList<ComputerPosDef> computers;
	
	public LevelDef(String name) {
		super(name);
		computers = new ArrayList<ComputerPosDef>();
	}
	
}
