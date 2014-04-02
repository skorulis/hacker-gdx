package skorulis.hacker.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import skorulis.hacker.computer.Computer;
import skorulis.hacker.def.ComputerPosDef;
import skorulis.hacker.def.LevelDef;

public class NetworkLevel extends Group {

	private LevelDef def;
	private ArrayList<Computer> computers;
	
	public NetworkLevel(LevelDef def) {
		this.def = def;
		computers = new ArrayList<Computer>();
		for(ComputerPosDef cd : this.def.computers) {
			Computer comp = new Computer(cd);
			this.addActor(comp);
			computers.add(comp);
		}
		
	}
	
	@Override
    public void draw (Batch batch, float parentAlpha) {
		drawChildren(batch, parentAlpha);
	}
	
	
}
