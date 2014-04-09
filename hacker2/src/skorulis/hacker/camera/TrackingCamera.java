package skorulis.hacker.camera;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

import skorulis.hacker.avatar.Avatar;
import skorulis.hacker.level.NetworkLevel;

public class TrackingCamera extends Actor{

	private Avatar avatar;
	private NetworkLevel level;
	public Vector3 translation;
	
	public TrackingCamera(NetworkLevel level, Avatar avatar) {
		this.level = level;
		this.avatar = avatar;
		translation = new Vector3();
	}
	
	public void act(float delta) {
		level.act(delta);
	}
	
	public void draw(Batch batch, float parentAlpha) {
		Matrix4 transform = new Matrix4();
		transform.translate(translation);
		batch.setTransformMatrix(transform);
		level.draw(batch, parentAlpha);
		
	}
	
}
