package skorulis.hacker.computer;

import skorulis.hacker.def.square.CompSquareDef;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ComputerSquare extends Actor {

	public CompSquareDef def;
	private TextureRegion[] textures;
	
	public ComputerSquare(CompSquareDef def,AssetManager assets) {
		this.def = def;
		textures = new TextureRegion[def.layers.size()];
		for(int i = 0 ; i < textures.length; ++i) {
			Texture t = assets.get(def.textures.get(i).textureName);
			textures[i] = new TextureRegion(t);
		}
	}
	
	public void draw(Batch batch, float alpha) {
		for(int i = 0; i < textures.length; ++i) {
			batch.draw(textures[i], getX(), getY(),0,0,32,32,1,1,def.textures.get(i).rotation);
		}
	}
	
}
