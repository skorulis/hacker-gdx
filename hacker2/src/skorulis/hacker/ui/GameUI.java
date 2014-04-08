package skorulis.hacker.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameUI extends Group {

	public TextButton actionButton;
	
	public GameUI(Skin skin) {
		actionButton = new TextButton("Action", skin);
		actionButton.setWidth(100);
		actionButton.setHeight(100);
		
		addActor(actionButton);
	}
	
}
