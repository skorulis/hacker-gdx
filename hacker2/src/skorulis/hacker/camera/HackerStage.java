package skorulis.hacker.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class HackerStage extends Stage {

	private SpriteBatch batch;
	
	public HackerStage() {
		batch = new SpriteBatch();
	}
	
	public void draw () {
		Camera camera = getViewport().getCamera();
		camera.update();
		if (!getRoot().isVisible()) return;
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		getRoot().draw(batch, 1);
		batch.end();
	}
	
}
