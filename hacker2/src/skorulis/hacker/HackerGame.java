package skorulis.hacker;

import java.util.List;

import skorulis.hacker.def.DefManager;
import skorulis.hacker.level.NetworkLevel;
import skorulis.hacker.ui.MainUI;
import skorulis.hacker.ui.UIManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HackerGame implements ApplicationListener {
	
	private Stage stage;
	private DefManager defManager;
	private NetworkLevel level;
	private AssetManager assets;
	private boolean loading;
	private UIManager ui;
	
	@Override
	public void create() {		
		defManager = new DefManager();
		assets = new AssetManager();
		
		List<String> textures = defManager.allTextures();
		for(String s : textures) {
			assets.load(s, Texture.class);
		}
		
		stage = new Stage(new ScreenViewport());
		loading = true;
		
		ui = new UIManager();
	}
	
	private void startLevel() {
		level = new NetworkLevel(defManager.getLevel("l1"),assets);
		
		GestureDetector gd = new GestureDetector(level);
	    Gdx.input.setInputProcessor(gd);
		//Gdx.input.setInputProcessor(stage);
	    
	    stage.addActor(level);
	    level.start();
	    
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(loading) {
			if(assets.update()) {
				startLevel();
				loading = false;
			}
			return;
		}
		float delta = Gdx.graphics.getDeltaTime();
		stage.act(delta);
	    stage.draw();
	    
	    ui.render();
	    ui.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		ui.resize(width, height);
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
		assets.dispose();
	}
}
