package skorulis.hacker.computer.square;

import java.util.ArrayList;

import skorulis.hacker.def.square.TerrainLayerDef;
import skorulis.hacker.def.square.TerrainLayerDef.TerrainType;

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
	
	//DEF
	
	public CompSquareLayer addLayer(TerrainLayerDef layer) {
		CompSquareLayer csl = new CompSquareLayer(layer);
		layers.add(csl);
		return csl;
	}
	
	public void clear() {
		layers.clear();
	}
	
	public CompSquareLayer place(TerrainLayerDef layer) {
		TerrainLayerDef old;
		for(int i = 0; i < layers.size(); ++i) {
			old = layers.get(i).def;
			if(layer.shouldReplace(old)) {
				CompSquareLayer newLayer = new CompSquareLayer(layer);
				layers.set(i, newLayer);
				return newLayer;
			}
		}
		return addLayer(layer);
	}
	
	public void assignTextures(ComputerSquare north,ComputerSquare east, ComputerSquare south, ComputerSquare west) {
		for(CompSquareLayer layer : layers) {
			layer.calculateTexture(north, east, south, west);
		}
	}
	
	public boolean hasLayer(String name) {
		for(CompSquareLayer tld : layers) {
			if(tld.def.name().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
}
