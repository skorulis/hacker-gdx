package skorulis.hacker.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class UIManager {

	public Stage stage;
	
	public UIManager() {
		stage = new Stage(new ScreenViewport());
	}
	
	public void render() {
		stage.draw();
	}
	
	public void update(float delta) {
		stage.act(delta);
	}
	
	public void resize(int width, int height) {
		stage.getViewport().update(width, height,true);
	}
	
}
