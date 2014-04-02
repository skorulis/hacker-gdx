package skorulis.hacker.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;

import skorulis.hacker.computer.Computer;
import skorulis.hacker.def.ComputerPosDef;
import skorulis.hacker.def.ConnectionDef;
import skorulis.hacker.def.LevelDef;

public class NetworkLevel extends Group implements Disposable {

	private LevelDef def;
	private ArrayList<Computer> computers;
	private ShapeRenderer shapeRenderer;
	
	public NetworkLevel(LevelDef def) {
		this.def = def;
		computers = new ArrayList<Computer>();
		for(ComputerPosDef cd : this.def.computers) {
			Computer comp = new Computer(cd);
			this.addActor(comp);
			computers.add(comp);
		}
		
		shapeRenderer = new ShapeRenderer();
		
	}
	
	@Override
    public void draw (Batch batch, float parentAlpha) {
		
		batch.end();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		for(ConnectionDef cd : def.connections) {
			shapeRenderer.line(cd.comp1.location, cd.comp2.location);
		}
		shapeRenderer.end();
		
		batch.begin();
		drawChildren(batch, parentAlpha);
	}
	
	public void dispose() {
		for(Computer c : computers) {
			c.dispose();
		}
	}
	
	
}
