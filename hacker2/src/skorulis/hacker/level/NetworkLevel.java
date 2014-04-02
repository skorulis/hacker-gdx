package skorulis.hacker.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import skorulis.hacker.def.LevelDef;

public class NetworkLevel extends Actor{

	private LevelDef def;
	private Texture texture;
	
	public NetworkLevel(LevelDef def) {
		this.def = def;
		
		texture = new Texture(Gdx.files.internal("data/workgroup.png"));
	}
	
	@Override
    public void draw (Batch batch, float parentAlpha) {
		batch.draw(texture,0,0);
		
	}
	
	
}
