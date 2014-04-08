package skorulis.hacker.computer;

import skorulis.hacker.def.square.CompSquareDef;
import skorulis.hacker.def.square.CompSquareLayer;
import skorulis.hacker.def.square.TerrainLayerDef.TerrainType;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ComputerSquare extends Actor {

	public static int SQUARE_SIZE = 32;
	public CompSquareDef def;
	private TextureRegion[] textures;
	
	public ComputerSquare(AssetManager assets) {
		
	}
	
	public ComputerSquare(CompSquareDef def,AssetManager assets) {
		this.def = def;
		textures = new TextureRegion[def.layers.size()];
		for(int i = 0 ; i < textures.length; ++i) {
			Texture t = assets.get(def.layers.get(i).texture.textureName);
			textures[i] = new TextureRegion(t);
		}
	}
	
	public void draw(Batch batch, float alpha) {
		for(int i = 0; i < textures.length; ++i) {
			batch.draw(textures[i], getX(), getY(),SQUARE_SIZE/2,SQUARE_SIZE/2,SQUARE_SIZE,SQUARE_SIZE,1,1,def.layers.get(i).texture.rotation);
		}
	}
	
	public Vector2 getLocation() {
		return new Vector2(getX(),getY());
	}
	
	public Vector2 getCentreLoc() {
		return new Vector2(getX() + SQUARE_SIZE/2,getY() + SQUARE_SIZE/2);
	}
	
	public String toString() {
		return "(" + getX() / SQUARE_SIZE + "," + getY() / SQUARE_SIZE + ")"; 
	}
	
	public boolean isPassable() {
		for(CompSquareLayer layer : def.layers) {
			if(layer.def.type == TerrainType.SOLID) { 
				return false;
			}
		}
		return true;
	}
	
}
