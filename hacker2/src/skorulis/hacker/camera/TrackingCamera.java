package skorulis.hacker.camera;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

import skorulis.hacker.avatar.Avatar;
import skorulis.hacker.level.NetworkLevel;

public class TrackingCamera extends Actor{

	private Avatar avatar;
	private NetworkLevel level;
	public Vector3 translation;
	public Vector2 lastLoc;
	public boolean panning;
	
	public TrackingCamera(NetworkLevel level, Avatar avatar) {
		this.level = level;
		this.avatar = avatar;
		translation = new Vector3();
		lastLoc = new Vector2(avatar.getX(), avatar.getY());
		
	}
	
	public void act(float delta) {
		if(!panning) {
			float x = -avatar.getX() + getStage().getWidth()/2;
			float y = -avatar.getY() + getStage().getHeight()/2;
			
			Vector2 diff = new Vector2(x - translation.x, y - translation.y);
			Vector2 diffNorm = diff.cpy().nor().scl(200);
			
			if(diff.len() > 2) {
				translation.x += diffNorm.x * delta;
				translation.y += diffNorm.y * delta;
			}
		}
		
		level.act(delta);
		lastLoc.x = avatar.getX(); lastLoc.y = avatar.getY();
	}
	
	public void draw(Batch batch, float parentAlpha) {
		Matrix4 transform = new Matrix4();
		transform.translate(translation);
		batch.setTransformMatrix(transform);
		level.draw(batch, parentAlpha);
		
	}
	
	public void panned(float x, float y) {
		translation.x += x;
		translation.y -= y;
		panning = true;
	}
	
	public void tapped() {
		panning = false;
	}
	
}
