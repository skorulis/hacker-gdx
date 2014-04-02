package skorulis.hacker.computer;

import skorulis.hacker.def.ComputerPosDef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class Computer extends Actor implements Disposable {

	private ComputerPosDef def;
	
	private Texture texture;
	
	
	public Computer(ComputerPosDef def) {
		this.def = def;
		texture = new Texture(Gdx.files.internal("data/workgroup.png"));
	}
	
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, def.location.x, def.location.y);
	}
	
	public void dispose() {
		texture.dispose();
	}
	
	
}
