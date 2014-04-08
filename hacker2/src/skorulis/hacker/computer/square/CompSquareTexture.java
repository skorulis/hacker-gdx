package skorulis.hacker.computer.square;

public class CompSquareTexture {

	public String textureName;
	public float rotation;
	
	public CompSquareTexture(String textureName) {
		this.textureName = textureName;
	}
	
	public CompSquareTexture(String textureName, float rotation) {
		this(textureName);
		this.rotation = rotation;
	}
	
}
