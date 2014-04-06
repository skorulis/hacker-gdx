package skorulis.hacker.computer;

import skorulis.hacker.def.square.CompSquareDef;
import skorulis.hacker.def.square.TerrainLayerDef;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ComputerSquare extends Actor {

	public CompSquareDef def;
	private Texture[] textures;
	
	public ComputerSquare(CompSquareDef def,AssetManager assets) {
		this.def = def;
		textures = new Texture[def.layers.size()];
		for(int i = 0 ; i < textures.length; ++i) {
			textures[i] = assets.get(def.layers.get(i).mainTexture);
		}
	}
	
	public void draw(Batch batch, float alpha) {
		for(Texture t : textures) {
			batch.draw(t, getX(), getY());
		}
		
	}
	
}
