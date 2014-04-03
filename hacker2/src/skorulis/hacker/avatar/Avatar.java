package skorulis.hacker.avatar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Avatar extends Actor {

	private Texture texture;
	
	public Avatar() {
		texture = new Texture(Gdx.files.internal("data/twitter-icon.png"));
	}
	
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, getX(), getY());
	}
}
