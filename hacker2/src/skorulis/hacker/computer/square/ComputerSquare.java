package skorulis.hacker.computer.square;

import java.util.ArrayList;

import skorulis.hacker.def.terrain.TerrainLayerDef.TerrainAction;
import skorulis.hacker.def.terrain.TerrainLayerDef.TerrainType;
import skorulis.hacker.computer.square.layer.CompSquareLayer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ComputerSquare extends Actor {
	
	public ArrayList<CompSquareLayer> layers;
	private TextureRegion[] textures;
	public static int SQUARE_SIZE = 32;
	
	public ComputerSquare() {
		layers = new ArrayList<CompSquareLayer>();
	}
	
	public void loadTextures(AssetManager assets) {
		textures = new TextureRegion[layers.size()];
		for(int i = 0 ; i < textures.length; ++i) {
			Texture t = assets.get(layers.get(i).texture.textureName);
			textures[i] = new TextureRegion(t);
		}
	}
	
	public void draw(Batch batch, float alpha) {
		for(int i = 0; i < textures.length; ++i) {
			batch.draw(textures[i], getX(), getY(),SQUARE_SIZE/2,SQUARE_SIZE/2,SQUARE_SIZE,SQUARE_SIZE,1,1,layers.get(i).texture.rotation);
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
		for(CompSquareLayer layer : layers) {
			if(layer.def.type == TerrainType.SOLID) { 
				return false;
			}
		}
		return true;
	}
	
	public boolean hasLayer(String name) {
		for(CompSquareLayer tld : layers) {
			if(tld.def.name().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public CompSquareLayer getActionLayer() {
		for(CompSquareLayer layer : layers) {
			if(layer.def.action != TerrainAction.NONE) {
				return layer;
			}
		}
		return null;
	}
	
	
	
}
