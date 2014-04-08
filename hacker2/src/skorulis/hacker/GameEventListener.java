package skorulis.hacker;

import skorulis.hacker.level.NetworkLevel;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class GameEventListener implements GestureListener{

	public NetworkLevel level;
	
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
		level.translation.x += deltaX;
		level.translation.y -= deltaY;
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
		x -= level.translation.x;
		y += level.translation.y;
		y = level.getStage().getHeight() - y;
		if(level.openComputer != null) {
			level.computerTap(x, y);
		} else {
			level.networkTap(x, y);
		}
		
		return false;
	}
	
}
