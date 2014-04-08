package skorulis.hacker.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameUI extends Group implements UIScreen {

	public TextButton actionButton;
	
	public GameUI(Skin skin) {
		actionButton = new TextButton("Action", skin);
		actionButton.setWidth(80);
		actionButton.setHeight(50);
		
		addActor(actionButton);
		
		actionButton.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {
				
			}
			
		});
	}

	@Override
	public void resize(int width, int height) {
		actionButton.setX(width - actionButton.getWidth());
	}
	
}
