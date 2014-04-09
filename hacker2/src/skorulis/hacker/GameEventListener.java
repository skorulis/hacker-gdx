package skorulis.hacker;

import skorulis.hacker.camera.TrackingCamera;
import skorulis.hacker.level.NetworkLevel;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class GameEventListener implements GestureListener{

	public NetworkLevel level;
	public TrackingCamera camera;
	
	public GameEventListener() {
		
	}
	
	@Override
	public boolean longPress(float x, float y) {
		System.out.println("long press");
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		System.out.println("fling");
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// System.out.println("pan " + deltaX + "," + deltaY);
		camera.panned(deltaX, deltaY);
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		System.out.println("pan stop");
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		System.out.println("zoom");
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		System.out.println("pinch");
		return false;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		x -= camera.translation.x;
		y += camera.translation.y;
		y = camera.getStage().getHeight() - y;
		if(level.openComputer() != null) {
			level.computerTap(x, y);
		} else {
			level.networkTap(x, y);
		}
		camera.tapped();
		
		return false;
	}
	
	public void actionClicked() {
		level.playerAvatar().performAction();
		System.out.println("EVE");
	}
	
}
