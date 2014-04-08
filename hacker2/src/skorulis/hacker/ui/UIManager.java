package skorulis.hacker.ui;

import skorulis.hacker.GameEventListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class UIManager {

	public Stage stage;
	private GameUI gameUI;
	private Skin skin;
	
	public UIManager(GameEventListener gameListener) {
		stage = new Stage(new ScreenViewport());
		skin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));
		gameUI = new GameUI(skin,gameListener);
		stage.addActor(gameUI);
	}
	
	public void render() {
		stage.draw();
	}
	
	public void update(float delta) {
		stage.act(delta);
	}
	
	public void resize(int width, int height) {
		stage.getViewport().update(width, height,true);
		gameUI.resize(width, height);
	}
	
}
