package skorulis.hacker.ui;

import skorulis.hacker.GameEventListener;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameUI extends Group implements UIScreen {

	public TextButton actionButton;
	public final GameEventListener gameListener;
	
	public GameUI(Skin skin,final GameEventListener gameListener) {
		actionButton = new TextButton("Action", skin);
		actionButton.setWidth(80);
		actionButton.setHeight(50);
		
		addActor(actionButton);
		
		this.gameListener = gameListener;
		
		actionButton.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {
				gameListener.actionClicked();
			}
			
		});
	}

	@Override
	public void resize(int width, int height) {
		actionButton.setX(width - actionButton.getWidth());
	}
	
}
