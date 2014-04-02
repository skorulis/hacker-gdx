package skorulis.hacker;

import skorulis.hacker.def.DefManager;
import skorulis.hacker.level.NetworkLevel;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HackerGame implements ApplicationListener {
	
	private Stage stage;
	private DefManager defManager;
	private NetworkLevel level;
	
	@Override
	public void create() {		
		defManager = new DefManager();
		
		level = new NetworkLevel(defManager.getLevel("l1"));
		
		stage = new Stage(new ScreenViewport());
	    Gdx.input.setInputProcessor(stage);
	    
	    stage.addActor(level);
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
